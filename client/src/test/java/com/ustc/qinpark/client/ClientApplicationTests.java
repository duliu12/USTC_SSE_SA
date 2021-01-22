package com.ustc.qinpark.client;

import com.ustc.qinpark.client.util.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

    @Test
    void contextLoads() {
        String url = "http://localhost:8083/server/balanced_server";
        String result = HttpUtil.doGet(url);
        System.out.println(result);
    }

}
