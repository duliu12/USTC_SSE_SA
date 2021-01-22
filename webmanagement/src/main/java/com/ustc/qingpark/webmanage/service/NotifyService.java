package com.ustc.qingpark.webmanage.service;

import com.ustc.qingpark.webmanage.util.Message;

import java.io.IOException;

public interface NotifyService {
    void messageTest(String message,int clientId) throws IOException;
    void handleMessage(Message message,int clientId) throws IOException;
}
