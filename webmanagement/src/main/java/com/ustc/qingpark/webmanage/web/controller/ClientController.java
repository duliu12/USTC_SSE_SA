package com.ustc.qingpark.webmanage.web.controller;

import com.ustc.qingpark.webmanage.dto.*;
import com.ustc.qingpark.webmanage.service.ClientService;
import com.ustc.qingpark.webmanage.util.Result;
import com.ustc.qingpark.webmanage.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {
    @Autowired
    ClientService clientService;
    @RequestMapping("/list/{serverId}/{pageIndex}/{pageSize}")
    public Result listClient(@PathVariable("serverId")Integer serverId,
                             @PathVariable("pageIndex")Integer pageIndex,
                             @PathVariable("pageSize")Integer pageSize
    ){
        ClientListDTO clientDTOList=clientService.listClient(serverId,pageIndex,pageSize);
        return Results.success(clientDTOList);
    }
    @RequestMapping("/onlinehistory")
    public Result onlineHistory(){
        List<ClientOnlineDTO> clientOnlineDTOS=clientService.onlineHistory();
        return Results.success(clientOnlineDTOS);
    }
    @RequestMapping("/totalLoad")
    public Result totalLoad(){
        Integer total=clientService.totalLoad();
        return Results.success(total);
    }
    @RequestMapping("/available")
    public Result totalAvailable(){
        ClientTotalDTO total=clientService.totalAvailable();
        return Results.success(total);
    }
    @RequestMapping("/index")
    public Result listIndex(){
        List<ClientIndexDTO>clientIndexDTOS=clientService.listClientIndex();
        return Results.success(clientIndexDTOS);
    }
    @RequestMapping(value = "/weekload",method = RequestMethod.POST)
    public Result listWeekLoad(@RequestBody ClientLoadQueryDTO client)
    {
        List<ClientLoadDTO> clientLoadDTOList=clientService.listWeekLoad(client);
        return Results.success(clientLoadDTOList);
    }
    @RequestMapping(value = "/dayload",method = RequestMethod.POST)
    public Result listDayLoad(@RequestBody ClientLoadQueryDTO client)
    {
        List<ClientLoadDTO> clientLoadDTOList=clientService.listDayLoad(client);
        return Results.success(clientLoadDTOList);
    }
    @RequestMapping("/offline/{serverId}/{clientId}")
    public Result OfflineClient(@PathVariable("serverId") int serverId,@PathVariable("clientId") int clientId)
    {
        clientService.offline(serverId,clientId);
        return Results.success(null);
    }

}
