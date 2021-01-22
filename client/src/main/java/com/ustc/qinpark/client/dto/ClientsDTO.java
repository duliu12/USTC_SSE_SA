package com.ustc.qinpark.client.dto;

import com.ustc.qinpark.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsDTO {
    List<Client> clients;
    int pageTotal;
}
