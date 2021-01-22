package com.ustc.qingpark.webmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Integer clientId;
    private String name;
    private String ip;
    private String address;
    private Integer maxSize;
    private Integer currSize;
    private Integer totalLoad;
    private Integer status;

    public void increaseLoad() {
        this.totalLoad++;
    }

    public void increaseCurrSize() {
        this.currSize++;
    }

    public void decreaseCurrSize() {
        this.currSize--;
    }
}
