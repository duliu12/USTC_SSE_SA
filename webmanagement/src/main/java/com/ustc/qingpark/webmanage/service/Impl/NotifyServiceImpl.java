package com.ustc.qingpark.webmanage.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.webmanage.dto.ServerIndexDTO;
import com.ustc.qingpark.webmanage.entity.Server;
import com.ustc.qingpark.webmanage.global.Global;
import com.ustc.qingpark.webmanage.service.NotifyService;
import com.ustc.qingpark.webmanage.util.Message;
import com.ustc.qingpark.webmanage.util.ServerCache;
import com.ustc.qingpark.webmanage.web.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("notifyService")
@Slf4j
public class NotifyServiceImpl implements NotifyService {
    @Override
    public void messageTest(String message, int clientId) throws IOException {
        WebSocketServer.send(message,clientId);
    }

    @Override
    public void handleMessage(Message message, int clientId)throws IOException {
        switch (message.getType()){
            case Global.TYPE_NOTIFY_SERVER_ONLINE:
                Server server =new Server();
                //log.info("server="+message.toString());
                JSONObject o1= JSON.parseObject(JSON.toJSONString(message.getData()));
                server.setId(o1.getInteger("id"));
                server.setIp(o1.getString("ip"));
                server.setName(o1.getString("name"));
                server.setApi(o1.getString("api"));
                server.setCurrSize(o1.getInteger("currSize"));
                server.setMaxSize(o1.getInteger("maxSize"));
                server.setStatus(o1.getInteger("status"));
                ServerCache.getServerCache().put(server.getId(),server);
                ServerIndexDTO serverIdx=new ServerIndexDTO(server.getId(),server.getName());
                Message message2=new Message(message.getType(),serverIdx);
                WebSocketServer.send(message2.toString(),clientId);
                break;
            case Global.TYPE_NOTIFY_SERVER_OFFLINE:
                JSONObject o2=(JSONObject) message.getData();
                int serverId=((JSONObject)o2).getInteger("serverId");
                Server server1=ServerCache.getServerCache().get(serverId);
                if(server1!=null)
                    server1.setStatus(Global.STATUS_OFFLINE);
                WebSocketServer.send(message.toString(),clientId);
                break;
            default:
                WebSocketServer.send(message.toString(),clientId);
                break;
        }

    }
}
