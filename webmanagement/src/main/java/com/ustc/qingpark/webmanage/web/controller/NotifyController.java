package com.ustc.qingpark.webmanage.web.controller;

import com.ustc.qingpark.webmanage.global.Global;
import com.ustc.qingpark.webmanage.service.NotifyService;
import com.ustc.qingpark.webmanage.util.Message;
import com.ustc.qingpark.webmanage.util.Result;
import com.ustc.qingpark.webmanage.util.Results;
import com.ustc.qingpark.webmanage.web.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/notify")
@Slf4j
public class NotifyController {
    @Autowired
    NotifyService notifyService;
    @RequestMapping("/message/{clientId}/{message}")
    public Result sendMessage(@PathVariable("clientId") int clientId, @PathVariable("message")String message) throws IOException {
       notifyService.messageTest(message,clientId);
        return Results.success(null);
    }
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public Result sendMessage(@RequestBody Message message) throws IOException {
        log.info("收到的是:"+message.toString());
        //WebSocketServer.send(message.toString(),-1);
        notifyService.handleMessage(message, Global.ID_ALL_CLIENTS);
        return Results.success(null);
    }
}
