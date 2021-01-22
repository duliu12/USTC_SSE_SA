package com.ustc.qingpark.register.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(10000,"ok"),
    SERVER_NOT_ONLINE(10001,"服务器不在线，无法下线"),
    REGISTER_FAIL(10002,"请求注册器失败"),
    SERVER_EMPTY(10003,"没有可用的服务器"),
    SERVER_BUSY(10004,"服务器繁忙"),
    NO_SUCH_ADMIN(10005,"该用户不存在"),
    PASSWORD_WRONG(10006,"密码错误"),
    STATUS_50X(10010,"internal server error");
    private int code;
    private String msg;

}
