package com.ustc.qinpark.server.global;

import javax.servlet.http.PushBuilder;
import java.security.PublicKey;

public class Global {
    public static final int TYPE_CLIENT_ONLINE = 0;
    public static final int TYPE_CLIENT_OFFLINE = 1;
    public static final int TYPE_CLIENT_HEARTBEAT = 2;//消息类型：心跳包
    public static final int TYPE_CLIENT_UPDATE = 3;//客户端信息变化
    public static final int TYPE_CLIENT_LOAD_ADD = 4;//客户端当前负载变化,+1
    public static final int TYPE_CLIENT_LOAD_SUB = 5;//客户端当前负载变化,-1
    public static final int TYPE_CLIENT_INFO = 6;
    public static final int TYPE_REGISTER_SERVERINFO = 5;//向register发送server信息
    public static final int TYPE_REGISTER_HEARTBEAT = 6;//向register发送心跳包
    public static final int TYPE_REGISTER_SERVER_LOAD_ADD = 7;//
    public static final int TYPE_REGISTER_SERVER_LOAD_SUB = 8;//
    public static final int SIZE_EMPTY = 0;
    public static final int TYPE_CLIENT_LOAD_DAILY = 0;
    public static final int TYPE_CLIENT_LOAD_PEAK = 1;
    public static final int TYPE_CLIENT_LOAD_VALLEY = 2;
    public static final int TYPE_CLIENT_LOAD_TOTAL = 3;
    public static final int TYPE_NOTIFY_ONLINE=1;
    public static final int TYPE_NOTIFY_OFFLINE=2;
    public static final int TYPE_NOTIFY_LOAD_ADD=3;
    public static final int TYPE_NOTIFY_LOAD_SUB=4;
    public static final String STATUS_ONLINE = "online";
    public static final String STATUS_OFFLINE = "offline";
}
