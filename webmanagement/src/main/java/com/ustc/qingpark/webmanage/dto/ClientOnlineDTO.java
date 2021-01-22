package com.ustc.qingpark.webmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientOnlineDTO {
    String name;
    String time;
    String status;
}
