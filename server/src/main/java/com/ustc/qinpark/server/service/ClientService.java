package com.ustc.qinpark.server.service;

import com.ustc.qinpark.server.dto.ClientDTO;
import com.ustc.qinpark.server.entity.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface ClientService {
    void clientOnline(Client client);

    void clientOffline(Client client);

    void clientUpdate(Client cLient, Client newclient);

    void clientLoadChange(Client client, int type);

    void clientHeartBeat(Client client);

    ArrayList<ClientDTO> listClients();

}
