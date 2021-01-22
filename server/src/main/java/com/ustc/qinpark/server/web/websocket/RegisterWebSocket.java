package com.ustc.qinpark.server.web.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qinpark.server.entity.Server;
import com.ustc.qinpark.server.global.Global;
import com.ustc.qinpark.server.util.HeartbeatTask;
import com.ustc.qinpark.server.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URI;

@Configuration
@Component
@Slf4j
public class RegisterWebSocket {
    @Value("${register.onlineapi}")
    String onlineApi;
    @Value("${qingparkserver.id}")
    int id;
    @Autowired
    Server server;
    @Autowired
    HeartbeatTask heartbeatTask;

    @Bean("webSocketClient")
    public WebSocketClient getClient() {
        WebSocketClient client = null;
        try {
            System.out.println("url=" + onlineApi + id);
            client = new WebSocketClient(new URI(onlineApi + id)) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    log.info("connect to register success");
                }

                @Override
                public void onMessage(String s) {
                    log.info(s);
                    JSONObject json = JSON.parseObject(s);
                    int type = json.getInteger("type");
                    switch (type) {
                        case Global.TYPE_REGISTER_HEARTBEAT:
                            log.info("register heartbeat");
                            heartbeatTask.startHeartbeat(this);
                            break;
                        case Global.TYPE_REGISTER_SERVERINFO:
                            Message serverInfo = new Message();
                            serverInfo.setType(Global.TYPE_REGISTER_SERVERINFO);
                            serverInfo.setData(server);
                            //System.out.println("b");break;
                            send(serverInfo.toString());
//                        case 3:System.out.println("c");break;
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {

                    log.error("connection close");
                    log.error("i=" + i + ",s=" + s + ",b=" + b);
                    heartbeatTask.stopHeartbeat();
                }

                @Override
                public void onError(Exception e) {
                    log.error("error!" + e.getMessage());
                }

            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
