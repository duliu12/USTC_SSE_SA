package com.ustc.qingpark.webmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientLoadDTO {
    Integer total;
    Integer peak;
    Integer valley;
    String date;
}
