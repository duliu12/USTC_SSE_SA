package com.ustc.qingpark.webmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientLoadQueryDTO {
    Integer clientId;
    String date;
}
