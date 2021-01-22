package com.ustc.qinpark.client.service;

import com.ustc.qinpark.client.dto.ClientsDTO;
import com.ustc.qinpark.client.entity.Client;
import com.ustc.qinpark.client.entity.Server;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface ClientService {

    ClientsDTO listAllClient(Integer pageIndex,Integer pageSize);

    Client findClientById(int id);

    void update(Client client);

    void add(Client client);

    void deleteClientById(int id);

    void changeStatus(int id, String status);

    void saveStatus(int id, String status, int currsize);

    Server getServer();
}
