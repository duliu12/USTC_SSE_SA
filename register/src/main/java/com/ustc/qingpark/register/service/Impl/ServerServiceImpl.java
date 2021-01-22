package com.ustc.qingpark.register.service.Impl;

import com.ustc.qingpark.register.dao.ServerLoadRepository;
import com.ustc.qingpark.register.entity.Server;
import com.ustc.qingpark.register.entity.ServerLoad;
import com.ustc.qingpark.register.exception.RegisterException;
import com.ustc.qingpark.register.global.ErrorCode;
import com.ustc.qingpark.register.global.Global;
import com.ustc.qingpark.register.service.ServerService;
import com.ustc.qingpark.register.util.TimeUtil;
import com.ustc.qingpark.register.web.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import static com.ustc.qingpark.register.web.websocket.WebSocketServer.OFFLINE;
import static com.ustc.qingpark.register.web.websocket.WebSocketServer.ONLINE;


@Service("serverService")
@Slf4j
public class ServerServiceImpl implements ServerService {

    @Autowired
    TimeUtil timeUtil;
    @Autowired
    ServerLoadRepository serverLoadRepository;
    @Override
    public void serverOnline() {
        System.out.println("server online");
    }

    @Override
    public ArrayList<Server> listServer() {
        Map<Integer,WebSocketServer> serverMap= WebSocketServer.getServerMap();
        ArrayList<Server> serverArray=new ArrayList<>();
        serverMap.forEach((k,v)->{
            serverArray.add(v.getServer());
        });
        return serverArray;
    }

    @Override
    public Server getBalancedServer() {
        ArrayList<Server> servers=listServer();
        if (servers.size()==0)
            throw new RegisterException(ErrorCode.SERVER_EMPTY,"no server available");
        servers.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Server server1=(Server)o1,server2=(Server) o2;
                return (server1.getMaxSize()-server1.getCurrSize())-(server2.getMaxSize()-server2.getCurrSize());
            }
        });
        //servers.sort(Comparator.comparing((Server::getMaxSize-Server::getCurrSize)));
        Server server=servers.get(servers.size()-1);
        if(server.getCurrSize()>=server.getMaxSize())
            throw new RegisterException(ErrorCode.SERVER_BUSY,
                    "server id={},currsize={},maxsize={}",
                    server.getId(),server.getCurrSize(),server.getMaxSize());
        if(server.getStatus()==OFFLINE)
        {
            int i=servers.size()-2;
            for(;i>=0;i++)
            {
                server=servers.get(i);
                if(server.getMaxSize()>server.getCurrSize()&&server.getStatus()==ONLINE)
                    break;
            }
            if(i<=0)
                throw new RegisterException(ErrorCode.SERVER_EMPTY,"no server available");
        }
        return server;
    }

    @Override
    public void offlineServer(int serverId) throws IOException {
       WebSocketServer.close(serverId);
      // server;
    }

    @Override
    public void serverLoadChange(Server server, int type) {
        log.info("server 的id="+server.getId());
        Date date = new Date();
        String time = timeUtil.formatDate(date);
        String timelike = time + "%";
        ServerLoad load;

        switch (type) {
            case Global.TYPE_SERVER_LOAD_DAILY:
                time = timeUtil.formatDateTime(date);

                load = new ServerLoad();
                load.setServerLoad(server.getCurrSize());
                load.setServerId(server.getId());
                load.setTime(time);
                load.setType(type);
                load.setServerName("");
                serverLoadRepository.save(load);
                break;
            case Global.TYPE_SERVER_LOAD_PEAK:
                log.info("peak:clientid=" + server.getId() + " type=" + type + ",time=" + time);
                load = serverLoadRepository.findOneByServerIdAndTypeAndTimeLike(
                        server.getId(), type, timelike);
                time = timeUtil.formatDateTime(date);
                if (null == load) {

                    load = new ServerLoad();
                    load.setServerLoad(server.getCurrSize());
                    load.setServerId(server.getId());
                    load.setTime(time);
                    load.setType(type);
                    load.setServerName("");
                    serverLoadRepository.save(load);
                } else if (server.getCurrSize() > load.getServerLoad()) {
                    load.setServerLoad(server.getCurrSize());
                    load.setTime(time);
                    serverLoadRepository.save(load);

                }
                break;
            case Global.TYPE_SERVER_LOAD_VALLEY:
                load = serverLoadRepository.findOneByServerIdAndTypeAndTimeLike(
                        server.getId(), type, timelike
                );
                time = timeUtil.formatDateTime(date);
                if (null == load) {
                    // time=timeUtil.formatDateTime(date);
                    load = new ServerLoad();
                    load.setServerLoad(server.getCurrSize());
                    load.setServerId(server.getId());
                    load.setTime(time);
                    load.setType(type);
                    load.setServerName("");
                    serverLoadRepository.save(load);
                } else if (server.getCurrSize() < load.getServerLoad()) {
                    load.setServerLoad(server.getCurrSize());
                    load.setTime(time);
                    serverLoadRepository.save(load);
                }
                break;
        }
    }
}
