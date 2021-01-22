package com.ustc.qingpark.webmanage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.webmanage.entity.Server;
import com.ustc.qingpark.webmanage.exception.ManageException;
import com.ustc.qingpark.webmanage.global.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
public class ServerCache {

   private static String registerIp;

    private static String serverApi;
    private static ConcurrentHashMap<Integer, Server>serverCache=new ConcurrentHashMap<>();
    public static boolean isCached=false;
    @Value("${register.ip}")
    public void setRegisterIp(String ip)
    {
        registerIp=ip;
    }
    @Value("${register.serverlistapi}")
    public void setServerApi(String api){
        serverApi=api;
    }
    public static ConcurrentHashMap<Integer,Server> getServerCache(){
        if(!ServerCache.isCached)
        {

            log.info("request url="+registerIp+serverApi);
            String res = HttpUtil.doGet(registerIp+serverApi);
            log.info(res);
            JSONObject resJSON = JSON.parseObject(res);
            if (resJSON.getInteger("code")==null||!resJSON.getInteger("code").equals(ErrorCode.SUCCESS.getCode()))
                throw new ManageException(ErrorCode.REGISTER_FAIL, "res={}", res);
            JSONArray servers = resJSON.getJSONArray("data");
            for (Object o : servers
            ) {
                Server server =new Server();
                JSONObject o1=(JSONObject) o;
                server.setId(o1.getInteger("id"));
                server.setIp(o1.getString("ip"));
                server.setName(o1.getString("name"));
                server.setApi(o1.getString("api"));
                server.setCurrSize(o1.getInteger("currSize"));
                server.setMaxSize(o1.getInteger("maxSize"));
                server.setStatus(o1.getInteger("status"));
                serverCache.put(server.getId(),server);
            }
            isCached=true;
        }
        return serverCache;
    }
}
