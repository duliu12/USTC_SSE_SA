package com.ustc.qinpark.client.entity;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    int id;
    String ip;
    String name;
    String api;
}
