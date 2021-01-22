package com.ustc.qinpark.server.dao;

import com.ustc.qinpark.server.entity.ClientLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientLoadRepository extends JpaRepository<ClientLoad, Integer> {
    ClientLoad findOneByClientIdAndTypeAndTimeLike(int clientId, int type, String time);
//   @Modifying
//   @Query("update ClientLoad load set load.load=load.load+1 " +
//           "where load.clientId=?1 and load.time like?2 and load.type=?3")
//   void increaseTotal(int clientId,String time,int type);
}
