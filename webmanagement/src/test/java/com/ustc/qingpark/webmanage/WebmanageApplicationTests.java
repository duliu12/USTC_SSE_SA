package com.ustc.qingpark.webmanage;

import com.ustc.qingpark.webmanage.dto.ClientIndexDTO;
import com.ustc.qingpark.webmanage.dto.ClientLoadDTO;
import com.ustc.qingpark.webmanage.dto.ClientLoadQueryDTO;
import com.ustc.qingpark.webmanage.dto.ClientOnlineDTO;
import com.ustc.qingpark.webmanage.mapper.ClientMapper;
import com.ustc.qingpark.webmanage.service.ClientService;
import com.ustc.qingpark.webmanage.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
class WebmanageApplicationTests {

    @Autowired
    ClientService clientService;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    TimeUtil timeUtil;
    @Test
    void contextLoads() {
        List<ClientOnlineDTO> clientOnlineDTOList=clientService.onlineHistory();
        for (ClientOnlineDTO co:clientOnlineDTOList
             ) {
            System.out.println(co.getName()+" "+co.getTime()+" "+co.getStatus());
        }
    }
    @Test
    void totalLOAD() {
       int totalLOAD=clientService.totalLoad();
       System.out.println(totalLOAD);
    }
    @Test
    void testMapper(){
        List<ClientIndexDTO>clientIndexDTOList=clientMapper.listClient();
        clientIndexDTOList.forEach((v)->{
            System.out.println(v.getName());
        });
    }
    @Test
    void testCal()
    {
        ClientLoadQueryDTO client=new ClientLoadQueryDTO();
        client.setClientId(1);
        client.setDate("2020-12-11");
        List<ClientLoadDTO> list=clientService.listDayLoad(client);
        list.forEach((v)->{
            System.out.println("日期："+v.getDate()+" 总量:"+v.getTotal()
                   );
        });
    }


}
