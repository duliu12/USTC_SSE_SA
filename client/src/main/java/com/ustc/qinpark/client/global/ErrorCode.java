package com.ustc.qinpark.client.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(10000, "ok"),
    NOT_AUTHED(10001, "auth not allowed"),
    CLIENT_INVALID(10002, "客户端参数非法"),
    STATUS_INVALID(10003, "客户端状态非法"),
    REGISTER_ERROR(10004, "服务器数据异常"),
    STATUS_50X(10010, "internal server error");
    private int code;
    private String msg;

}
