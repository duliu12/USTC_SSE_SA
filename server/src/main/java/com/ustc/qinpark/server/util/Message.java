package com.ustc.qinpark.server.util;

import com.alibaba.fastjson.JSON;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int type;
    private Object data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public int hashCode() {
        return type + data.hashCode();
    }
}
