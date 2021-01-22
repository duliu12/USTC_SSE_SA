package com.ustc.qinpark.server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(10000, "ok"),
    CLIENT_NOT_ONLINE(10001, "客户端不在线，无法下线"),
    REGISTER_FAIL(10002, "注册到注册器失败"),
    SERVER_NOT_ONLINE(10003, "服务器未上线，无法下线"),
    STATUS_50X(10010, "internal server error");
    private int code;
    private String msg;

}
