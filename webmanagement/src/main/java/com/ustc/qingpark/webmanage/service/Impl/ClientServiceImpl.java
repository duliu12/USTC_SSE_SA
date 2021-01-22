package com.ustc.qingpark.webmanage.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ustc.qingpark.webmanage.dao.ClientLoadRepository;
import com.ustc.qingpark.webmanage.dao.ClientOnlineRepository;
import com.ustc.qingpark.webmanage.dto.*;
import com.ustc.qingpark.webmanage.entity.ClientLoad;
import com.ustc.qingpark.webmanage.entity.ClientOnline;
import com.ustc.qingpark.webmanage.entity.Server;
import com.ustc.qingpark.webmanage.exception.ManageException;
import com.ustc.qingpark.webmanage.global.ErrorCode;
import com.ustc.qingpark.webmanage.global.Global;
import com.ustc.qingpark.webmanage.mapper.ClientMapper;
import com.ustc.qingpark.webmanage.service.ClientService;
import com.ustc.qingpark.webmanage.util.HttpUtil;
import com.ustc.qingpark.webmanage.util.ServerCache;
import com.ustc.qingpark.webmanage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientOnlineRepository clientOnlineRepository;
    @Autowired
    ClientLoadRepository clientLoadRepository;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    TimeUtil timeUtil;
    @Value("${register.ip}")
    String registerIp;
    @Value("${register.serverlistapi}")
    String serverApi;
    @Value("${qingparkserver.clientlistapi}")
    String clientListApi;
    @Value("${qingparkserver.clientofflineapi}")
    String clientOfflineApi;

    @Override
    public ClientListDTO listClient(Integer serverId,int pageIndex,int pageSize) {
        Server server=ServerCache.getServerCache().get(serverId);
        if(null==server)
            throw new ManageException(ErrorCode.SERVER_FAIL,"");
        String res=HttpUtil.doGet("http://"+server.getIp()+clientListApi);
        JSONObject resJSON=JSON.parseObject(res);
        if(null==resJSON.getInteger("code")||!resJSON.getInteger("code").equals(ErrorCode.SUCCESS.getCode()))
            throw new ManageException(ErrorCode.SERVER_FAIL,"");
        JSONArray clients=resJSON.getJSONArray("data");
        List<ClientDTO>clientDTOList=new ArrayList<>();
        for (Object o:clients
             ) {
            JSONObject client=(JSONObject) o;
            ClientDTO c=new ClientDTO();
            c.setClientId(client.getInteger("clientId"));
            c.setName(client.getString("name"));
            c.setAddress(client.getString("address"));
            c.setRemainSize(client.getInteger("remainSize"));
            c.setCurrSize(client.getInteger("currSize"));
            c.setStatus(client.getInteger("status")==Global.STATUS_ONLINE?Global.ONLINE:Global.OFFLINE);
            clientDTOList.add(c);
        }
        int start=(pageIndex-1)*pageSize;
        int end=(start+pageSize)>clientDTOList.size()?clientDTOList.size():(start+pageSize);
        ClientListDTO clientList=new ClientListDTO();
        clientList.setClients(clientDTOList.subList(start,end));
        clientList.setTotalSize(clientDTOList.size());
        return clientList;

    }

    @Override
    public List<ClientOnlineDTO> onlineHistory() {
        List<ClientOnlineDTO>clientOnlineDTOS=new ArrayList<>();
        Sort sort=Sort.by(Sort.Direction.DESC,"time");
        Pageable page= PageRequest.of(0,5,sort) ;
        Page<ClientOnline> res=clientOnlineRepository.findAll(page);
        for (ClientOnline co:res.getContent()
             ) {
            ClientOnlineDTO clientOnlineDTO=new ClientOnlineDTO();
            clientOnlineDTO.setName(co.getClientName());
            clientOnlineDTO.setTime(co.getTime());
            clientOnlineDTO.setStatus(co.getStatus());
            clientOnlineDTOS.add(clientOnlineDTO);
        }
        return clientOnlineDTOS;
    }

    @Override
    public Integer totalLoad() {
        String today=timeUtil.formatDate(new Date())+"%";
        //String today="2020-12-11%";
        List<ClientLoad> clientLoads=clientLoadRepository.findAllByTimeLikeAndType(today, Global.TYPE_CLIENT_LOAD_TOTAL);
        Integer totalLoad=0;
        for (ClientLoad cl:clientLoads
             ) {
            totalLoad+=cl.getLoad();
        }
        return totalLoad;
    }

    @Override
    public ClientTotalDTO totalAvailable() {
        AtomicInteger totalAvailable= new AtomicInteger();
        AtomicInteger totalCurrSize= new AtomicInteger();
        AtomicReference<Float> maxLoad= new AtomicReference<>(0.0f);
        ConcurrentHashMap<Integer,Server>servers=ServerCache.getServerCache();
        servers.forEach((k,v)->{
            if(v.getStatus()==Global.STATUS_ONLINE)
            {
                String res=HttpUtil.doGet("http://"+v.getIp()+clientListApi);
                JSONObject resJSON=JSON.parseObject(res);
                if(null==resJSON.getInteger("code")||!resJSON.getInteger("code").equals(ErrorCode.SUCCESS.getCode()))
                    throw new ManageException(ErrorCode.SERVER_FAIL,"");
                JSONArray clients=resJSON.getJSONArray("data");
                for (Object o:clients
                     ) {
                    JSONObject o1=(JSONObject)o;
                    totalAvailable.addAndGet(o1.getInteger("remainSize"));
                    totalCurrSize.addAndGet(o1.getInteger("currSize"));
                    float currLoad=o1.getInteger("currSize")/
                            (o1.getInteger("currSize")+o1.getInteger("remainSize"));
                    if(currLoad> maxLoad.get())
                        maxLoad.set(currLoad);
                }
            }
        });
        ClientTotalDTO clientTotalDTO=new ClientTotalDTO();
        clientTotalDTO.setTotalAvailable(totalAvailable.get());
        clientTotalDTO.setTotalCurrSize(totalCurrSize.get());
        clientTotalDTO.setMaxLoad(maxLoad.get());
        return clientTotalDTO;
    }
    @Override
    public Integer totalClient() {
        String res= HttpUtil.doGet(serverApi);
        JSONObject resJSON= JSON.parseObject(res);
        if(!resJSON.getInteger("code").equals(ErrorCode.SUCCESS.getCode()))
            throw new ManageException(ErrorCode.REGISTER_FAIL,"res={}",res);
        JSONArray servers=resJSON.getJSONArray("data");
        Integer total=0;
        for (Object o:servers
        ) {
            Server server=(Server)o;
            total+=server.getCurrSize();
        }
        return total;
    }


    @Override
    public List<ClientIndexDTO> listClientIndex() {
        List<ClientIndexDTO> clientIndexDTOS=clientMapper.listClient();
        return clientIndexDTOS;
    }

    @Override
    public List<ClientLoadDTO> listWeekLoad(ClientLoadQueryDTO client) {
        Calendar cal=Calendar.getInstance();
        String date[]=client.getDate().split("-");
        cal.set(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]));
        int day_of_week=cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE,-day_of_week);
        int DAY_OF_ONE_WEEK=7,DAY=1;
        List<ClientLoadDTO>clientLoadDTOList=new ArrayList<>();
        for(int i=0;i<DAY_OF_ONE_WEEK;i++)
        {
            cal.add(Calendar.DATE,DAY);
            ClientLoadDTO clientLoadDTO=new ClientLoadDTO();
            String datelike=timeUtil.formatDate(cal.getTime());
            clientLoadDTO.setDate(datelike);
            List<ClientLoad> loads=clientLoadRepository.findAllByTimeLikeAndClientId(datelike+"%",client.getClientId());
            loads.forEach((v)->{
               switch (v.getType()){
                   case Global.TYPE_CLIENT_LOAD_TOTAL:
                       clientLoadDTO.setTotal(v.getLoad());break;
                    case   Global.TYPE_CLIENT_LOAD_PEAK:
                       clientLoadDTO.setPeak(v.getLoad());break;
                    case Global.TYPE_CLIENT_LOAD_VALLEY:
                       clientLoadDTO.setValley(v.getLoad());break;
                   default:break;
               }
            });
            clientLoadDTOList.add(clientLoadDTO);
        }
        return clientLoadDTOList;
    }

    @Override
    public List<ClientLoadDTO> listDayLoad(ClientLoadQueryDTO client) {
        List<ClientLoadDTO> clientLoadDTOList=new ArrayList<>();
        List<ClientLoad> clientLoads=clientLoadRepository.findAllByTimeLikeAndTypeAndClientId(
                client.getDate()+"%",
                Global.TYPE_CLIENT_LOAD_DAILY,
                client.getClientId()
        );
        clientLoads.forEach((v)->{
            ClientLoadDTO clientLoadDTO=new ClientLoadDTO();
            String time=v.getTime().split(" ")[1];
            clientLoadDTO.setDate(time);
            clientLoadDTO.setTotal(v.getLoad());
            clientLoadDTOList.add(clientLoadDTO);
        });
        return clientLoadDTOList;
    }

    @Override
    public void offline(int serverId, int clientId) {
        Server server=ServerCache.getServerCache().get(serverId);
        if(server==null||server.getStatus()==Global.STATUS_OFFLINE)
            throw new ManageException(ErrorCode.SERVER_FAIL,"123");
        String res=HttpUtil.doGet("http://"+server.getIp()+clientOfflineApi+clientId);
        JSONObject resJSON=JSON.parseObject(res);
        if(!resJSON.getInteger("code").equals(ErrorCode.SUCCESS.getCode()))
        {
            throw new ManageException(ErrorCode.SERVER_FAIL,"res={}",res);
        }
    }


}
