package com.ustc.qingpark.webmanage.web.controller;

import com.ustc.qingpark.webmanage.dto.AdminDTO;
import com.ustc.qingpark.webmanage.entity.Admin;
import com.ustc.qingpark.webmanage.service.AdminService;
import com.ustc.qingpark.webmanage.util.Message;
import com.ustc.qingpark.webmanage.util.Result;
import com.ustc.qingpark.webmanage.util.Results;
import com.ustc.qingpark.webmanage.web.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    public Result login(@RequestParam("name")String name,@RequestParam("password")String password)
    {
        AdminDTO admin =adminService.login(name,password);
        return Results.success(admin);
    }

}
