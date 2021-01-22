package com.ustc.qingpark.register.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeUtil {
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
   public String formatDate(Date date){
        return dateFormat.format(date);
    }
    public String formatDateTime(Date date){
       return dateTimeFormat.format(date);
    }
}
