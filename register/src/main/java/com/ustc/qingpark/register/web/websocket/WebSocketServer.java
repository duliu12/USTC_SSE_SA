package com.ustc.qingpark.register.web.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.register.entity.Server;
import com.ustc.qingpark.register.global.Global;
import com.ustc.qingpark.register.service.Impl.ServerServiceImpl;
import com.ustc.qingpark.register.service.ServerService;
import com.ustc.qingpark.register.util.Message;
import com.ustc.qingpark.register.util.ServiceUtil;
import com.ustc.qingpark.register.util.WebNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
@Component
@Slf4j
@ServerEndpoint("/server/online/{serverId}")
public class WebSocketServer {
    private static int onlineCount=0;
    private static ConcurrentHashMap<Integer, WebSocketServer> serverMap=new ConcurrentHashMap<>();
    private Session session;
    private Integer serverId;
    private Server server;
    //private int status;
    public static int ONLINE=1;
    public static int OFFLINE=0;

    @Value("${websocket.maxTimeout}")
    long maxTimeout;
    ServerService serverService=WebSocketConfig.getBean("serverService", ServerService.class);
//     ServerService serverService= ServiceUtil.getServerService();
    @OnOpen
    public void open(Session session,@PathParam("serverId") int serverId) throws IOException {
        this.session=session;
        session.setMaxIdleTimeout(maxTimeout);
        this.serverId=serverId;
        if(!serverMap.contains(serverId))
        {
           // serverMap.remove(serverId);
            serverMap.put(serverId,this);
            addOnlineCount();
        }
        this.server=new Server();
        this.server.setStatus(ONLINE);
        serverMap.forEach((k,v)-> System.out.println("server id have "+k));
        // System.out.println(this.client==null);
        log.info("server "+serverId+" connecting to register ");
        Message serverInfo=new Message();
        serverInfo.setType(Global.TYPE_REGISTER_SERVERINFO);
        sendMessage(serverInfo.toString());
    }
    @OnClose
    public void close(){
        log.info("server "+serverId+" offline");
        //log.warn("has?"+clientMap.contains(client));
        if(serverMap.containsKey(serverId))
        {
            log.info("serverid:"+serverId+" has offline");
//            log.info(this.session.);
           // serverService.serverOffline();
            serverMap.get(serverId).getServer().setStatus(OFFLINE);
            Message serverOffline=new Message();
            serverOffline.setType(Global.TYPE_NOTIFY_SERVER_OFFLINE);
            serverOffline.setData(server);
            WebNotifier.notifyWeb(serverOffline);
           // subOnlineCount();
        }
        //else throw new ServerException(ErrorCode.CLIENT_NOT_ONLINE,"clientId={}",clientId);
    }
    @OnMessage
    public void message(String message) throws IOException {
        log.info("收到："+message);
        JSONObject json= JSON.parseObject(message);
        switch (json.getInteger("type")){
            case Global.TYPE_REGISTER_HEARTBEAT:
                log.info("heartbeat:"+this.serverId);
                //serverService.serverHeartBeat(client);
                break;
            case Global.TYPE_REGISTER_SERVERINFO:
                JSONObject data=json.getJSONObject("data");
                //this.server=new Server();
                server.setId(this.serverId);
                server.setIp(data.getString("ip"));
                server.setName(data.getString("name"));
                server.setApi(data.getString("api"));
                server.setMaxSize(data.getInteger("maxSize"));
                server.setCurrSize(0);
                log.info("ip="+server.getIp());
                log.info("server data received now start heartbeat");
                Message heartbeat=new Message();
                heartbeat.setType(Global.TYPE_REGISTER_HEARTBEAT);
                sendMessage(heartbeat.toString());
                Message serverOnline=new Message();
                serverOnline.setType(Global.TYPE_NOTIFY_SERVER_ONLINE);
                serverOnline.setData(server);
                WebNotifier.notifyWeb(serverOnline);
                break;
            case Global.TYPE_REGISTER_SERVER_LOAD_ADD:
                int addSize=json.getInteger("data");
                this.server.increaseCurrSize();
                log.info("add data: currsize="+addSize+",this.currsize="+this.server.getCurrSize());
                //server.setCurrSize(currSize);
                serverService.serverLoadChange(this.server,Global.TYPE_SERVER_LOAD_PEAK);
                break;
            case Global.TYPE_REGISTER_SERVER_LOAD_SUB:
                int subSize=json.getInteger("data");
                this.server.decreaseCurrSize();
                log.info("add data: currsize="+subSize+",this.currsize="+this.server.getCurrSize());
                //server.setCurrSize(currSize);
                serverService.serverLoadChange(this.server,Global.TYPE_SERVER_LOAD_VALLEY);

            default: break;
        }
    }
    @OnError
    public void error(Session session,Throwable error){
        log.error("server:"+serverId+" error!,info:"+error.getMessage());
        error.printStackTrace();
    }
    public void sendMessage(String message)throws IOException
    {
        log.info("send message:"+message+" to server "+serverId);
        this.session.getBasicRemote().sendText(message);
    }
    public static void close(int serverId) throws IOException {
        WebSocketServer server=serverMap.get(serverId);
        if(server!=null)
        {
            server.session.close();
        }
    }
    public static void send(String message,int serverId) throws IOException {
        if(serverId==-1)//群发消息
        {
            for(WebSocketServer webSocketServer:WebSocketServer.serverMap.values())
            {
                webSocketServer.sendMessage(message);
            }
        }else{
            WebSocketServer server=serverMap.get(serverId);
            if(server!=null)
                server.sendMessage(message);
        }
    }
    public static synchronized void addOnlineCount(){
        onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        if(onlineCount>0)
            onlineCount--;
    }
    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public Server getServer() {
        return server;
    }
    public static ConcurrentHashMap getServerMap(){
        return serverMap;
    }
}
