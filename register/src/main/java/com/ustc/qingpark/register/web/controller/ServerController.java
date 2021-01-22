package com.ustc.qingpark.register.web.controller;

import com.ustc.qingpark.register.service.ServerService;
import com.ustc.qingpark.register.util.Result;
import com.ustc.qingpark.register.util.Results;
import com.ustc.qingpark.register.util.ServiceUtil;
import com.ustc.qingpark.register.web.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    ServerService serverService;
//    ServerService serverService= ServiceUtil.getServerService();
    @GetMapping("/list")
    public Result listServer(){
        return Results.success(serverService.listServer());
    }
    @GetMapping("/balanced_server")
    public Result balancedServer(){
        return Results.success(serverService.getBalancedServer());
    }
    @RequestMapping(value = "/offline/{serverId}",method = RequestMethod.GET)
    public Result offlineServer(@PathVariable("serverId")int serverId) throws IOException {
        serverService.offlineServer(serverId);
        return Results.success(null);
    }
}
