package com.ustc.qinpark.server.entity;

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
    int currSize;
    int maxSize;
}
