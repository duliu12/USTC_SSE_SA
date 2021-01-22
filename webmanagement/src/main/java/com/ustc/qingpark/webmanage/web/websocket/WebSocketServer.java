package com.ustc.qingpark.webmanage.web.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.webmanage.entity.Client;
import com.ustc.qingpark.webmanage.entity.Server;
import com.ustc.qingpark.webmanage.exception.ManageException;
import com.ustc.qingpark.webmanage.global.ErrorCode;
import com.ustc.qingpark.webmanage.global.Global;
import com.ustc.qingpark.webmanage.util.Message;
import com.ustc.qingpark.webmanage.web.websocket.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket server to connect with client
 */
@Component
//@ServerEndpoint("/park/{clientId}/{name}/{address}/{maxSize}/{currSize}")
@ServerEndpoint("/{clientId}")
@Slf4j
public class WebSocketServer { ;
    private static ConcurrentHashMap<Integer, WebSocketServer> clientMap = new ConcurrentHashMap<>();
    private Session session;
    private Integer clientId;
    //private Client client;
    public static int ONLINE = 1;
    public static int OFFLINE = 0;
    @Value("${websocket.maxTimeout}")
    long maxTimeout;
   // ClientService clientService = WebSocketConfig.getBean("clientServiceImpl", ClientServiceImpl.class);

    @OnOpen
    public void open(Session session, @PathParam("clientId") Integer clientId) throws IOException {
        this.session=session;
        this.clientId=clientId;
        clientMap.put(clientId,this);
        log.info("connect to server");
        Message heartbeat=new Message();
        heartbeat.setType(Global.TYPE_WEB_HEARTBEAT);
        sendMessage(heartbeat.toString());

    }

    @OnClose
    public void close() {
       log.info("CLOSED");
    }

    @OnMessage
    public void message(String message) throws IOException {
        log.info("收到"+message);
        JSONObject json = JSON.parseObject(message);
        switch (json.getInteger("type")) {
            case Global.TYPE_WEB_HEARTBEAT:
                log.info("heartbeat:" + this.clientId);
               // clientService.clientHeartBeat(client);
                break;

            default:
                break;
        }
    }

    @OnError
    public void error(Session session, Throwable error) {
        log.error("client:" + clientId + " error!,info:" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        log.info("send message:" + message + " to client " + clientId);
        this.session.getBasicRemote().sendText(message);
    }

    public static void close(int clientId) throws IOException {
        WebSocketServer server = clientMap.get(clientId);
        if (server != null) {
            server.session.close();
        }
    }

    public static void send(String message, int clientId) throws IOException {
        if(clientId==Global.ID_ALL_CLIENTS)
        {
            clientMap.forEach((k,v)->{
                try {
                    v.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
       else if(clientMap.get(clientId)!=null)
            clientMap.get(clientId).sendMessage(message);
        else log.info("client "+clientId+"not exist");
    }

    

    public static Map<Integer, WebSocketServer> getClientMap() {
        return clientMap;
    }

}
