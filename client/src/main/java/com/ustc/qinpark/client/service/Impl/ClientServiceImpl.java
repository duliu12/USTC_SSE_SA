package com.ustc.qinpark.client.service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.ustc.qinpark.client.dto.ClientsDTO;
import com.ustc.qinpark.client.entity.Client;
import com.ustc.qinpark.client.entity.Server;
import com.ustc.qinpark.client.exception.ClientException;
import com.ustc.qinpark.client.global.ErrorCode;
import com.ustc.qinpark.client.global.Global;
import com.ustc.qinpark.client.repository.ClientRepository;
import com.ustc.qinpark.client.service.ClientService;
import com.ustc.qinpark.client.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Value("${register.api}")
    String registerApi;

    @Override
    public ClientsDTO listAllClient(Integer pageIndex,Integer pageSize) {
        List<Client> clients = clientRepository.findAll();
        int start=(pageIndex-1)*pageSize;
        int end=(start+pageSize)>clients.size()?clients.size():(start+pageSize);
        ClientsDTO clientsDTO = new ClientsDTO(clients.subList(start,end), clients.size());
        return clientsDTO;
    }

    @Override
    public Client findClientById(int id) {
        Client client = clientRepository.findOneById(id);
        return client;
    }

    @Override
    public void update(Client client) {
        checkClient(client);
        clientRepository.save(client);
    }

    @Override
    public void add(Client client) {
        checkClient(client);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        client.setStatus(Global.STATUS_OFFLINE);
        client.setCurrSize(0);
        client.setDate(date);
        clientRepository.save(client);
    }

    private void checkClient(Client client) {
        if (null == client.getName() || "".equals(client.getName()))
            throw new ClientException(ErrorCode.CLIENT_INVALID, "client name empty");
        if (null == client.getAddress() || "".equals(client.getAddress()))
            throw new ClientException(ErrorCode.CLIENT_INVALID, "client address empty");
        if (client.getMaxSize() <= 0)
            throw new ClientException(ErrorCode.CLIENT_INVALID, "client maxsize invalid");
    }

    @Override
    public void deleteClientById(int id) {
        Client client = clientRepository.findOneById(id);
        clientRepository.delete(client);
    }

    @Override
    public void changeStatus(int id, String status) {
        if (status.equals(Global.STATUS_ONLINE) || status.equals(Global.STATUS_OFFLINE)) {
            Client client = clientRepository.findOneById(id);
            client.setStatus(status);
            clientRepository.save(client);
        }
    }

    @Override
    public void saveStatus(int id, String status, int currsize) {
        checkStatus(status);
        Client client = clientRepository.findOneById(id);
        client.setStatus(status);
        if (status.equals(Global.STATUS_OFFLINE))
            client.setCurrSize(0);
        client.setCurrSize(currsize);
        clientRepository.save(client);
    }

    private void checkStatus(String status) {
        if (!(status.equals(Global.STATUS_OFFLINE) || status.equals(Global.STATUS_ONLINE)))
            throw new ClientException(ErrorCode.STATUS_INVALID, "status={}", status);
    }

    public Server getServer() {
        String res = HttpUtil.doGet(registerApi);
        Server server = new Server();
        try {
            JSONObject response = JSON.parseObject(res);
            if (!response.getInteger("code").equals(ErrorCode.SUCCESS.getCode()))
                throw new ClientException(ErrorCode.REGISTER_ERROR, "reponse={}", res);
            JSONObject data = response.getJSONObject("data");
            if (null == data)
                throw new ClientException(ErrorCode.REGISTER_ERROR, "data=null");
            server.setId(data.getInteger("id"));
            server.setApi(data.getString("api"));
            server.setName(data.getString("name"));
            server.setIp(data.getString("ip"));
        } catch (Exception e) {
            log.info("parse error");
        }
        return server;
    }
}
