package com.ustc.qingpark.register.dao;

import com.ustc.qingpark.register.entity.ServerLoad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerLoadRepository extends JpaRepository<ServerLoad,Integer> {
    ServerLoad findOneByServerIdAndTypeAndTimeLike(int serverId,int type,String time);
}
