package com.ustc.qinpark.server.web.controller;

import com.ustc.qinpark.server.dto.ApiDTO;
import com.ustc.qinpark.server.util.Result;
import com.ustc.qinpark.server.util.Results;
import com.ustc.qinpark.server.util.WebNotifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebController {
    @RequestMapping("/webapi")
    public Result setWebApi(@RequestBody ApiDTO api)
    {
        WebNotifier.setWebNotifyApi(api.getWebapi());
        return Results.success(null);
    }
}
