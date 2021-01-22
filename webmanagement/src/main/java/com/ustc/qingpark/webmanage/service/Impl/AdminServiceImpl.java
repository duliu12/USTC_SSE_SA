package com.ustc.qingpark.webmanage.service.Impl;

import com.ustc.qingpark.webmanage.dao.AdminRepository;
import com.ustc.qingpark.webmanage.dto.AdminDTO;
import com.ustc.qingpark.webmanage.entity.Admin;
import com.ustc.qingpark.webmanage.exception.ManageException;
import com.ustc.qingpark.webmanage.global.ErrorCode;
import com.ustc.qingpark.webmanage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public AdminDTO login(String name, String password) {
        Admin admin=adminRepository.findByName(name);
        if(null==admin)
            throw new ManageException(ErrorCode.NO_SUCH_ADMIN,"name={}",name);
        if(!password.equals(admin.getPassword()))
        throw new ManageException(ErrorCode.PASSWORD_WRONG,"name={} password={}",name,password);
        AdminDTO adminDTO=new AdminDTO();
        adminDTO.setName(admin.getName());
        adminDTO.setIp(admin.getIp());
        adminDTO.setTime(admin.getTime());
        return adminDTO;
    }
}
