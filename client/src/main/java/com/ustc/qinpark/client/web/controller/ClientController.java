package com.ustc.qinpark.client.web.controller;

import com.ustc.qinpark.client.dto.ClientsDTO;
import com.ustc.qinpark.client.entity.Client;
import com.ustc.qinpark.client.service.ClientService;
import com.ustc.qinpark.client.util.Result;
import com.ustc.qinpark.client.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping("/clients/{pageIndex}/{pageSize}")
    public Result listClient(@PathVariable("pageIndex")Integer pageIndex,
                             @PathVariable("pageSize")Integer pageSize) {
        ClientsDTO clientList = clientService.listAllClient(pageIndex,pageSize);
        return Results.success(clientList);
    }

    @RequestMapping("/client/{id}")
    public Result findClientById(@PathVariable("id") int id) {
        Client client = clientService.findClientById(id);
        return Results.success(client);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateClient(@RequestBody Client client) {
        clientService.update(client);
        return Results.success(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addClient(@RequestBody Client client) {
        client.setId(0);
        clientService.add(client);
        return Results.success(null);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result deleteClient(@PathVariable("id") int id) {
        clientService.deleteClientById(id);
        return Results.success(null);
    }

    @RequestMapping(value = "/status/{id}/{status}", method = RequestMethod.POST)
    public Result changeStatus(@PathVariable("id") int id, @PathVariable("status") String status) {
        clientService.changeStatus(id, status);
        return Results.success(null);
    }

    @RequestMapping(value = "/save/{id}/{status}/{currSize}", method = RequestMethod.POST)
    public Result saveStatus(@PathVariable("id") int id, @PathVariable("status") String status, @PathVariable("currSize") int currsize) {
        clientService.saveStatus(id, status, currsize);
        return Results.success(null);
    }

}
