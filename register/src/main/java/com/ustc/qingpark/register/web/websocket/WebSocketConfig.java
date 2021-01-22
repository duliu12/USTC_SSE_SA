package com.ustc.qingpark.register.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@Component
@Slf4j
public class WebSocketConfig implements ApplicationContextAware {
    private static volatile ApplicationContext ctx;
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketConfig.ctx=applicationContext;
    }


    public static <T>T getBean(String name,Class<T> clazz){
        return ctx.getBean(name,clazz);
    }
}
