package com.ustc.qingpark.webmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerLoadDTO2 {
    Integer total;
    Integer peak;
    Integer valley;
    String date;
}
