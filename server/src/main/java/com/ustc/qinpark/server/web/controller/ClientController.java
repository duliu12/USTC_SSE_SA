package com.ustc.qinpark.server.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ustc.qinpark.server.dto.ClientDTO;
import com.ustc.qinpark.server.entity.Client;
import com.ustc.qinpark.server.global.Global;
import com.ustc.qinpark.server.service.ClientService;
import com.ustc.qinpark.server.util.Message;
import com.ustc.qinpark.server.util.Result;
import com.ustc.qinpark.server.util.Results;
import com.ustc.qinpark.server.web.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping("/offline/{clientId}")
    public Result handleClientOffline(@PathVariable("clientId") int clientId) throws IOException {
        Message clientOffline = new Message(Global.TYPE_CLIENT_OFFLINE, null);
        WebSocketServer.close(clientId);
        return Results.success(null);
    }

    @RequestMapping("/list")
    public Result listClient() {
        ArrayList<ClientDTO> clinets = clientService.listClients();
        return Results.success(clinets);
    }
}
