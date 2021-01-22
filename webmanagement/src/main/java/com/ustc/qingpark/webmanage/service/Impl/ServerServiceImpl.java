package com.ustc.qingpark.webmanage.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.webmanage.dao.ServerLoadRepository;
import com.ustc.qingpark.webmanage.dto.*;
import com.ustc.qingpark.webmanage.entity.ClientLoad;
import com.ustc.qingpark.webmanage.entity.Server;
import com.ustc.qingpark.webmanage.entity.ServerLoad;
import com.ustc.qingpark.webmanage.exception.ManageException;
import com.ustc.qingpark.webmanage.global.ErrorCode;
import com.ustc.qingpark.webmanage.global.Global;
import com.ustc.qingpark.webmanage.service.ServerService;
import com.ustc.qingpark.webmanage.util.HttpUtil;
import com.ustc.qingpark.webmanage.util.ServerCache;
import com.ustc.qingpark.webmanage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service("serverService")
public class ServerServiceImpl implements ServerService {
    @Value("${register.ip}")
    String registerIp;
    @Value("${register.serverlistapi}")
    String serverApi;
    @Autowired
    TimeUtil timeUtil;
    @Autowired
    ServerLoadRepository serverLoadRepository;
    @Override
    public ServerLoadDTO getMaxLoad() {
        List<Server>serverList=listServer();
        float maxLoadRate=0.0f;
        int currSize=0,maxSize=0;
        for (Server server:serverList
        ) {
            //Server server=(Server)o;
            float loadRate=server.getCurrSize()/server.getMaxSize();
            if(loadRate>maxLoadRate)
            {
                currSize=server.getCurrSize();
                maxSize=server.getCurrSize();
                maxLoadRate=loadRate;
            }
        }
        ServerLoadDTO slDTO=new ServerLoadDTO();
        slDTO.setMaxLoadRate(maxLoadRate);
        return slDTO;
    }

    @Override
    public List<Server> listServer() {
        List<Server> serverList = new ArrayList<>();
        ConcurrentHashMap<Integer,Server> serverCache=ServerCache.getServerCache();
        serverCache.forEach((k,v)->{
            serverList.add(v);
        });
       return serverList;
    }

    @Override
    public List<ServerLoadDTO2> listWeekLoad(ServerLoadQueryDTO server) {
        Calendar cal=Calendar.getInstance();
        String date[]=server.getDate().split("-");
        cal.set(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]));
        int day_of_week=cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE,-day_of_week);
        int DAY_OF_ONE_WEEK=7,DAY=1;
        List<ServerLoadDTO2>serverLoadDTOList=new ArrayList<>();
        for(int i=0;i<DAY_OF_ONE_WEEK;i++)
        {
            cal.add(Calendar.DATE,DAY);
            ServerLoadDTO2 serverLoadDTO=new ServerLoadDTO2();
            String datelike=timeUtil.formatDate(cal.getTime());
            serverLoadDTO.setDate(datelike);
            List<ServerLoad> loads=serverLoadRepository.findAllByTimeLikeAndServerId(datelike+"%",server.getServerId());
            loads.forEach((v)->{
                switch (v.getType()){
                    case Global.TYPE_CLIENT_LOAD_TOTAL:
                        serverLoadDTO.setTotal(v.getLoad());break;
                    case   Global.TYPE_CLIENT_LOAD_PEAK:
                        serverLoadDTO.setPeak(v.getLoad());break;
                    case Global.TYPE_CLIENT_LOAD_VALLEY:
                        serverLoadDTO.setValley(v.getLoad());break;
                    default:break;
                }
            });
            serverLoadDTOList.add(serverLoadDTO);
        }
        return serverLoadDTOList;
    }

    @Override
    public List<ServerIndexDTO> listIndex() {
        List<ServerIndexDTO> serverIndexDTOList=new ArrayList<>();
        ConcurrentHashMap<Integer,Server> serverCache=ServerCache.getServerCache();
        serverCache.forEach((k,v)->{
            ServerIndexDTO serverIndexDTO=new ServerIndexDTO(v.getId(),v.getName());
            serverIndexDTOList.add(serverIndexDTO);
        });
        return serverIndexDTOList;
    }
}
