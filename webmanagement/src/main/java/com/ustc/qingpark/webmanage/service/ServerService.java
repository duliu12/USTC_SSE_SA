package com.ustc.qingpark.webmanage.service;

import com.ustc.qingpark.webmanage.dto.*;
import com.ustc.qingpark.webmanage.entity.Server;

import java.util.List;

public interface ServerService {
    ServerLoadDTO getMaxLoad();
    List<Server> listServer();
    List<ServerLoadDTO2> listWeekLoad(ServerLoadQueryDTO server);
    List<ServerIndexDTO> listIndex();
}
