package com.ustc.qingpark.register.util;

import com.ustc.qingpark.register.entity.Server;
import com.ustc.qingpark.register.service.Impl.ServerServiceImpl;
import com.ustc.qingpark.register.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class ServiceUtil {

  //  @Autowired
    //@Qualifier("serverService")
    private static ServerService serverService;
   // @Qualifier("serverService")
    @Autowired
    public static void setServerService(ServerService serverService)
    {
        ServiceUtil.serverService=serverService;
    }
    public static ServerService getServerService(){
        return serverService;
    }
}
