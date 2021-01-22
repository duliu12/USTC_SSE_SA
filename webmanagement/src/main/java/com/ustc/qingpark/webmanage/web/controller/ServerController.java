package com.ustc.qingpark.webmanage.web.controller;

import com.ustc.qingpark.webmanage.dto.*;
import com.ustc.qingpark.webmanage.service.ServerService;
import com.ustc.qingpark.webmanage.util.Result;
import com.ustc.qingpark.webmanage.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server")
@CrossOrigin
public class ServerController {
    @Autowired
    ServerService serverService;
    @RequestMapping("/maxload")
    public Result maxLoad(){
        ServerLoadDTO serverLoadDTO=serverService.getMaxLoad();
        return Results.success(serverLoadDTO);
    }
    @RequestMapping(value = "/index")
    public Result listIndex(){
        List<ServerIndexDTO> serverIndexDTOList=serverService.listIndex();
        return Results.success(serverIndexDTOList);
    }
    @RequestMapping(value = "/weekload",method = RequestMethod.POST)
    public Result listWeekLoad(@RequestBody ServerLoadQueryDTO server)
    {
        List<ServerLoadDTO2> serverLoadDTOList=serverService.listWeekLoad(server);
        return Results.success(serverLoadDTOList);
    }
}
