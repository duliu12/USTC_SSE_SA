package com.ustc.qingpark.register.service;

import com.ustc.qingpark.register.entity.Server;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


//@Service
public interface ServerService {
    void serverOnline();
    ArrayList<Server> listServer();

    /**
     *
     * @return 剩余负载最大的server
     */
    Server getBalancedServer();
    void offlineServer(int serverId) throws IOException;
    void serverLoadChange(Server server,int type);
}
