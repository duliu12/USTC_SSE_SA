package com.ustc.qingpark.register.web.controller;

import com.ustc.qingpark.register.dto.ApiDTO;
import com.ustc.qingpark.register.util.Result;
import com.ustc.qingpark.register.util.Results;
import com.ustc.qingpark.register.util.WebNotifier;
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
