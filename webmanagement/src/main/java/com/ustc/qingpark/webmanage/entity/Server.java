package com.ustc.qingpark.webmanage.entity;

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
    int status;
    public void increaseCurrSize(){
        currSize++;
    }
    public void decreaseCurrSize(){
        currSize--;
    }
}
