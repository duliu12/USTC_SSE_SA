package com.ustc.qingpark.register.util;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int type;
    private Object data;
    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
    @Override
    public int hashCode(){
        return type+data.hashCode();
    }
}
