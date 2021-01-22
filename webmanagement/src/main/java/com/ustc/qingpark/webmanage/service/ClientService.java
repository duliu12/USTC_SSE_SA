package com.ustc.qingpark.webmanage.service;

import com.ustc.qingpark.webmanage.dto.*;
import com.ustc.qingpark.webmanage.entity.Client;
import com.ustc.qingpark.webmanage.entity.Server;

import java.util.List;

public interface ClientService {
   // List<ClientDTO> listClient(int serverId);
    List<ClientOnlineDTO> onlineHistory();
    Integer totalLoad();
    ClientTotalDTO totalAvailable();
    Integer totalClient();
    ClientListDTO listClient(Integer serverId,int pageIndex,int pageSize);
    List<ClientIndexDTO> listClientIndex();
    List<ClientLoadDTO> listWeekLoad(ClientLoadQueryDTO client);
    List<ClientLoadDTO> listDayLoad(ClientLoadQueryDTO client);
    void offline(int serverId,int clientId);
}
