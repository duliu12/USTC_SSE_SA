package com.ustc.qinpark.server.service.Impl;

import com.ustc.qinpark.server.dao.ClientLoadRepository;
import com.ustc.qinpark.server.dao.ClientOnlineRepository;
import com.ustc.qinpark.server.dto.ClientDTO;
import com.ustc.qinpark.server.entity.Client;
import com.ustc.qinpark.server.entity.ClientLoad;
import com.ustc.qinpark.server.entity.ClientOnline;
import com.ustc.qinpark.server.global.Global;
import com.ustc.qinpark.server.service.ClientService;
import com.ustc.qinpark.server.util.TimeUtil;
import com.ustc.qinpark.server.web.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.ustc.qinpark.server.web.websocket.WebSocketServer.ONLINE;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    WebSocketClient webSocketClient;
    @Autowired
    ClientLoadRepository clientLoadRepository;
    @Autowired
    ClientOnlineRepository clientOnlineRepository;
    @Autowired
    TimeUtil timeUtil;

    @Override
    public void clientOnline(Client client) {
        //webSocketClient.send();
        //System.out.println("online clienname="+client.getName());
        String time = timeUtil.formatDateTime(new Date());
        ClientOnline clientOnline = new ClientOnline();
        clientOnline.setClientId(client.getClientId());
        clientOnline.setClientName(client.getName());
        clientOnline.setTime(time);
        clientOnline.setStatus(Global.STATUS_ONLINE);
        // else clientOnline.setStatus(Global.STATUS_OFFLINE);
        // System.out.println("online name="+clientOnline.getClientName());
        clientOnlineRepository.save(clientOnline);
    }

    @Override
    public void clientOffline(Client client) {
        String time = timeUtil.formatDateTime(new Date());
        ClientOnline clientOnline = new ClientOnline();
        clientOnline.setClientId(client.getClientId());
        clientOnline.setClientName(client.getName());
        clientOnline.setTime(time);
        clientOnline.setStatus(Global.STATUS_OFFLINE);
        //  System.out.println("offline name="+clientOnline.getClientName());
        clientOnlineRepository.save(clientOnline);
    }

    @Override
    public void clientUpdate(Client client, Client newclient) {

    }

    @Override
    public void clientLoadChange(Client client, int type) {
        Date date = new Date();
        String time = timeUtil.formatDate(date);
        String timelike = time + "%";
        ClientLoad load;
        switch (type) {
            case Global.TYPE_CLIENT_LOAD_DAILY:
                time = timeUtil.formatDateTime(date);

                load = new ClientLoad();
                load.setLoad(client.getTotalLoad());
                load.setClientId(client.getClientId());
                load.setTime(time);
                load.setType(type);
                clientLoadRepository.save(load);
                break;
            case Global.TYPE_CLIENT_LOAD_PEAK:
                log.info("peak:clientid=" + client.getClientId() + " type=" + type + ",time=" + time);
                load = clientLoadRepository.findOneByClientIdAndTypeAndTimeLike(
                        client.getClientId(), type, timelike);
                time = timeUtil.formatDateTime(date);
                if (null == load) {

                    load = new ClientLoad();
                    load.setLoad(client.getCurrSize());
                    load.setClientId(client.getClientId());
                    load.setTime(time);
                    load.setType(type);
                    clientLoadRepository.save(load);
                } else if (client.getCurrSize() > load.getLoad()) {
                    load.setLoad(client.getCurrSize());
                    load.setTime(time);
                    clientLoadRepository.save(load);

                }
                break;
            case Global.TYPE_CLIENT_LOAD_VALLEY:
                load = clientLoadRepository.findOneByClientIdAndTypeAndTimeLike(
                        client.getClientId(), type, timelike
                );
                time = timeUtil.formatDateTime(date);
                if (null == load) {
                    // time=timeUtil.formatDateTime(date);
                    load = new ClientLoad();
                    load.setLoad(client.getCurrSize());
                    load.setClientId(client.getClientId());
                    load.setTime(time);
                    load.setType(type);
                    clientLoadRepository.save(load);
                } else if (client.getCurrSize() < load.getLoad()) {
                    load.setLoad(client.getCurrSize());
                    load.setTime(time);
                    clientLoadRepository.save(load);
                }
                break;
            case Global.TYPE_CLIENT_LOAD_TOTAL:
                load = clientLoadRepository.findOneByClientIdAndTypeAndTimeLike(
                        client.getClientId(), type, timelike
                );
                if (null == load) {
                    // time=timeUtil.formatDate(date);
                    load = new ClientLoad();
                    load.setLoad(client.getCurrSize());
                    load.setClientId(client.getClientId());
                    load.setTime(time);
                    load.setType(type);
                    clientLoadRepository.save(load);
                } else {
//                    clientLoadRepository.increaseTotal(
//                            client.getClientId(), timelike, type
//                    );
                    load.setLoad(load.getLoad() + 1);
                    clientLoadRepository.save(load);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void clientHeartBeat(Client client) {

    }

    @Override
    public ArrayList<ClientDTO> listClients() {
        ArrayList<ClientDTO> clients = new ArrayList<>();
        Map<Integer, WebSocketServer> clientMap = WebSocketServer.getClientMap();
        clientMap.forEach((k, v) -> {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setClientId(v.getClient().getClientId());
            clientDTO.setName(v.getClient().getName());
            clientDTO.setAddress(v.getClient().getAddress());
            clientDTO.setRemainSize(v.getClient().getMaxSize() - v.getClient().getCurrSize());
            clientDTO.setCurrSize(v.getClient().getCurrSize());
            clientDTO.setStatus(v.getClient().getStatus());
            clients.add(clientDTO);
        });
        return clients;
    }
}
