package com.ustc.qingpark.webmanage.service;

import com.ustc.qingpark.webmanage.dto.AdminDTO;
import com.ustc.qingpark.webmanage.entity.Admin;

public interface AdminService {
    AdminDTO login(String name, String password);
}
