package com.ustc.qinpark.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    Integer clientId;
    String name;
    String address;
    int remainSize;
    int currSize;
    int status;
}
