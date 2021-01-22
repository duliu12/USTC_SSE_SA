package com.ustc.qingpark.webmanage.mapper;

import com.ustc.qingpark.webmanage.dto.ClientIndexDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientMapper {
    List<ClientIndexDTO> listClient();
}
