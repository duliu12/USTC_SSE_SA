package com.ustc.qinpark.server.web.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qinpark.server.entity.Client;
import com.ustc.qinpark.server.entity.Server;
import com.ustc.qinpark.server.exception.ServerException;
import com.ustc.qinpark.server.global.ErrorCode;
import com.ustc.qinpark.server.global.Global;
import com.ustc.qinpark.server.service.ClientService;
import com.ustc.qinpark.server.service.Impl.ClientServiceImpl;
import com.ustc.qinpark.server.util.Message;
import com.ustc.qinpark.server.util.WebNotifier;
import com.ustc.qinpark.server.util.WebSocketBeanUtil;
import com.ustc.qinpark.server.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@ServerEndpoint("/park/{clientId}")
@Slf4j
public class WebSocketServer {
    //private  int onlineCount=0;
    private Server server = WebSocketConfig.getBean("server", Server.class);
    // private Server server= WebSocketBeanUtil.getServer();
    private static ConcurrentHashMap<Integer, WebSocketServer> clientMap = new ConcurrentHashMap<>();
    private Session session;
    private Integer clientId;
    private Client client;
    public static int ONLINE = 1;
    public static int OFFLINE = 0;
    private WebSocketClient webSocketClient = WebSocketConfig.getBean("webSocketClient", WebSocketClient.class);
    //    private WebSocketClient webSocketClient=WebSocketBeanUtil.getWebSocketClient();
    @Value("${websocket.maxTimeout}")
    long maxTimeout;
    ClientService clientService = WebSocketConfig.getBean("clientServiceImpl", ClientServiceImpl.class);

    @OnOpen
    public void open(Session session, @PathParam("clientId") Integer clientId) throws IOException {
        log.info("websocketcloent null?" + (webSocketClient == null));
        this.clientId = clientId;
        //this.client=new Client(clientId,name,"",address,maxSize,currSize,Global.SIZE_EMPTY);
        this.client = new Client();
        this.client.setClientId(clientId);
        this.client.setCurrSize(Global.SIZE_EMPTY);
        this.client.setTotalLoad(Global.SIZE_EMPTY);
        InetSocketAddress ipaddress = WebSocketUtil.getRemoteAddress(session);
        this.client.setIp(ipaddress.getAddress().getHostAddress());
        log.info("ip 地址是：" + ipaddress.getAddress().getHostAddress());
        this.client.setStatus(ONLINE);
        addOnlineCount();
        this.session = session;
        session.setMaxIdleTimeout(maxTimeout);
        if (!clientMap.contains(clientId)) {
            clientMap.put(clientId, this);
        }
        clientMap.forEach((k, v) -> System.out.println("client id have " + k));
        // System.out.println(this.client==null);
        // clientService.clientOnline(this.client);
        log.info("client " + clientId + " connecting to server ");
        Message clientInfo = new Message();
        clientInfo.setType(Global.TYPE_CLIENT_INFO);
        sendMessage(clientInfo.toString());
        Message webNotify=new Message();
    }

    @OnClose
    public void close() {
        log.info("client " + clientId + " offline");
        //log.warn("has?"+clientMap.contains(client));
        if (clientMap.containsKey(clientId)) {
            log.info("clientid:" + clientId + " has offline");
//            log.info(this.session.);
            clientService.clientOffline(client);
            client.setStatus(OFFLINE);
            client.setCurrSize(0);
            // clientMap.remove(clientId);
            subOnlineCount();
            Message msg_offline=new Message(Global.TYPE_NOTIFY_OFFLINE,client);
            WebNotifier.notifyWeb(msg_offline);
        }
        //else throw new ServerException(ErrorCode.CLIENT_NOT_ONLINE,"clientId={}",clientId);
    }

    @OnMessage
    public void message(String message) throws IOException {
        log.info("收到：" + message);
        JSONObject json = JSON.parseObject(message);
        switch (json.getInteger("type")) {
            case Global.TYPE_CLIENT_HEARTBEAT:
                log.info("heartbeat:" + this.clientId);
                clientService.clientHeartBeat(client);
                break;
            case Global.TYPE_CLIENT_UPDATE:
                log.info("client:" + this.clientId + " update");
                Client newclient = (Client) json.get("data");
                clientService.clientUpdate(client, newclient);
//                client.setName(client.getName());
//                client.setAddress(newclient.getAddress());
//                client.setMaxSize(newclient.getMaxSize());
                notifyWeb();
                //通知后台管理系统
                break;
            case Global.TYPE_CLIENT_LOAD_ADD:
                int addSize = json.getJSONObject("data").getInteger("currSize");
                //this.client.setTotalLoad(this.client.getTotalLoad()+1);
                this.client.increaseLoad();
                this.client.increaseCurrSize();
                log.info("client:" + clientId + " load add,currsize=" + addSize);
                log.info("client total load=" + client.getTotalLoad());
                clientService.clientLoadChange(client, Global.TYPE_CLIENT_LOAD_PEAK);
                clientService.clientLoadChange(client, Global.TYPE_CLIENT_LOAD_TOTAL);
//                client.setCurrSize(currSize);
                //通知后台管理系统
                Message msg_add=new Message(Global.TYPE_NOTIFY_LOAD_ADD,this.client);
                WebNotifier.notifyWeb(msg_add);
                break;
            case Global.TYPE_CLIENT_LOAD_SUB:
                int subSize = json.getJSONObject("data").getInteger("currSize");
                //this.client.setTotalLoad(this.client.getTotalLoad()+1);
                this.client.decreaseCurrSize();
                log.info("client:" + clientId + " load sub,currsize=" + subSize);
                clientService.clientLoadChange(client, Global.TYPE_CLIENT_LOAD_VALLEY);
                //通知后台管理系统
                Message msg_sub=new Message(Global.TYPE_NOTIFY_LOAD_SUB,client);
                WebNotifier.notifyWeb(msg_sub);
                break;
            case Global.TYPE_CLIENT_INFO:
                JSONObject data = json.getJSONObject("data");
                this.client.setName(data.getString("name"));
                this.client.setMaxSize(data.getInteger("maxSize"));
                this.client.setAddress(data.getString("address"));
                clientService.clientOnline(client);
                Message message1 = new Message();
                message1.setType(Global.TYPE_CLIENT_HEARTBEAT);
                sendMessage(message1.toString());
                message1.setType(Global.TYPE_NOTIFY_ONLINE);
                message1.setData(this.client);
                WebNotifier.notifyWeb(message1);
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
        if (clientId == -1)//群发消息
        {
            for (WebSocketServer webSocketServer : WebSocketServer.clientMap.values()) {
                webSocketServer.sendMessage(message);
            }
        } else {
            WebSocketServer server = clientMap.get(clientId);
            if (server != null)
                server.sendMessage(message);
        }
    }

    public synchronized void addOnlineCount() {
        log.info("server null?" + (server == null));
        log.info("server size null?" + server.getCurrSize());
        server.setCurrSize(server.getCurrSize() + 1);
        notifyRegister(Global.TYPE_REGISTER_SERVER_LOAD_ADD);
    }

    public synchronized void subOnlineCount() {
        if (server.getCurrSize() > 0) {
            server.setCurrSize(server.getCurrSize() - 1);
            notifyRegister(Global.TYPE_REGISTER_SERVER_LOAD_SUB);
        }
    }

    public synchronized int getOnlineCount() {
        return server.getCurrSize();
    }

    public Client getClient() {
        return client;
    }

    public static Map<Integer, WebSocketServer> getClientMap() {
        return clientMap;
    }

    public void notifyRegister(int type) {
        if (webSocketClient.getReadyState() == WebSocket.READYSTATE.OPEN) {
            Message serverload = new Message();
            serverload.setType(type);
            serverload.setData(server.getCurrSize());
            log.info("notify register:" + serverload.toString());
            webSocketClient.send(serverload.toString());
        }
    }

    public void notifyWeb() {

    }
}
