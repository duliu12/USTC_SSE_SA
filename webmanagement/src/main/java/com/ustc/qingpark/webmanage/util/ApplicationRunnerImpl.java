package com.ustc.qingpark.webmanage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.webmanage.entity.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Value("${qingparkserver.webapi}")
    String webApi;
    @Value("${server.port}")
    int port;
    @Value("${register.webapi}")
    String registerWebApi;
    @Value("${register.ip}")
    String registerIp;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        String param="http://"+IpUtil.INTERNET_IP+":"+port+"/notify/message";
        String registerUrl=registerIp+registerWebApi;
        JSONObject data=new JSONObject();
        data.put("webapi",param);
        JSONObject res1=HttpUtil.httpPost(registerUrl,data);
        log.info("register notifier:"+res1.toJSONString());
        ConcurrentHashMap<Integer, Server>serverCache=ServerCache.getServerCache();
        serverCache.forEach((k,v)->{
            String url="http://"+v.getIp()+webApi;
           JSONObject res= HttpUtil.httpPost(url, data);
           log.info("server "+v.getId()+" notifier:"+res.toJSONString());
        });
    }
}
