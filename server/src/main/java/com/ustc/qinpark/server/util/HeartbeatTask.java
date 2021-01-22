package com.ustc.qinpark.server.util;

import com.ustc.qinpark.server.global.Global;
import com.ustc.qinpark.server.web.websocket.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
@Configuration
@Slf4j
public class HeartbeatTask {
    @Qualifier("schedule")
    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    private ScheduledFuture<?> future;

    public void startHeartbeat(WebSocketClient client) {
        Message heartbeat = new Message();
        heartbeat.setType(Global.TYPE_REGISTER_HEARTBEAT);
        future = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                if (client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                    client.send(heartbeat.toString());
                    log.info("send heartbeat");
                } else {
                    log.error("socket is not open send fail");
                    future.cancel(true);
                }
            }
        }, new CronTrigger("0 0/1 * * * ? "));
        //future.get()
    }

    public void stopHeartbeat() {
        // if(future.isCancelled())
        future.cancel(true);
    }

}
