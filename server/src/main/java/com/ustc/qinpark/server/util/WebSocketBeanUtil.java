package com.ustc.qinpark.server.util;

import com.ustc.qinpark.server.entity.Server;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;

public class WebSocketBeanUtil {
    private static Server server;
    private static WebSocketClient client;

    @Autowired
    public WebSocketBeanUtil(Server server, WebSocketClient client) {
        this.server = server;
        this.client = client;
    }

    ;

    //    @Autowired
//    public void setServer(Server server){
//        this.server=server;
//    }
//    @Autowired
//    public void setWebSocketClient(WebSocketClient client)
//    {
//        this.client=client;
//    }
    public static Server getServer() {
        return server;
    }

    public static WebSocketClient getWebSocketClient() {
        return client;
    }


}
