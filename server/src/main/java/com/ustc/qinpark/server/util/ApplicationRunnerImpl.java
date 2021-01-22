package com.ustc.qinpark.server.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Value("${server.port}")
    int port;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url="http://localhost:"+port+"/register/online";
        String res=HttpUtil.doGet(url);
        log.info("register:"+res);
    }
}
