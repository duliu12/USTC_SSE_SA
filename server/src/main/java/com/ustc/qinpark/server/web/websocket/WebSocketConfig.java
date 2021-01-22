package com.ustc.qinpark.server.web.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qinpark.server.entity.Server;
import com.ustc.qinpark.server.global.Global;
import com.ustc.qinpark.server.util.HeartbeatTask;
import com.ustc.qinpark.server.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpointConfig;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * 开启websocket支持
 */
@Configuration
@Component
@Slf4j
public class WebSocketConfig implements ApplicationContextAware {

    @Value("${server.port}")
    int port;
    @Value("${qingparkserver.id}")
    int serverId;
    @Value("${qingparkserver.name}")
    String serverName;
    @Value("${qingparkserver.api}")
    String api;
    @Value("${qingparkserver.maxsize}")
    int maxSize;
    private static volatile ApplicationContext ctx;

    @Bean("server")
    public Server getServer() throws UnknownHostException {
        String ip = IpUtil.INTERNET_IP + ":" + port;

        return new Server(serverId, ip, serverName, api, 0, maxSize);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean("schedule")
    public ThreadPoolTaskScheduler getScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        return new ThreadPoolTaskScheduler();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketConfig.ctx = applicationContext;
    }


    public static <T> T getBean(String name, Class<T> clazz) {
        return ctx.getBean(name, clazz);
    }

}
