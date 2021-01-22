package com.ustc.qingpark.webmanage.dao;

import com.ustc.qingpark.webmanage.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
    Admin findByNameAndPassword(String name,String password);
    Admin findByName(String name);
}
