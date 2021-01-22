package com.ustc.qingpark.webmanage.dao;

import com.ustc.qingpark.webmanage.entity.ClientOnline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientOnlineRepository extends JpaRepository<ClientOnline,Integer> {
   Page<ClientOnline> findAll(Pageable pageable);
}
