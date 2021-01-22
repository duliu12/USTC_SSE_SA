package com.ustc.qingpark.register.global;

public class Global {
    public static final int TYPE_CLIENT_ONLINE=0;
    public static final int TYPE_CLIENT_OFFLINE=1;
    public static final int TYPE_HEARTBEAT=2;//消息类型：心跳包
    public static final int TYPE_CLIENT_UPDATE=3;//客户端信息变化
    public static final int TYPE_CLIENT_LOAD_CHANGE=4;//客户端当前负载变化
    public static final int TYPE_REGISTER_SERVERINFO=5;
    public static final int TYPE_REGISTER_HEARTBEAT=6;
    public static final int TYPE_REGISTER_SERVER_LOAD_ADD=7;
    public static final int TYPE_REGISTER_SERVER_LOAD_SUB=8;
    public static final int SIZE_EMPTY=0;
    public static final int TYPE_CLIENT_LOAD_DAILY=0;//数据库中存的表示server load的type，日常监控
    public static final int TYPE_CLIENT_LOAD_PEAK=1;//数据库中存的表示server load的type 表示每日峰值
    public static final int TYPE_CLIENT_LOAD_VALLEY=2;//每日谷值
    public static final int TYPE_CLIENT_LOAD_TOTAL=3;//每日总量
    public static final int TYPE_SERVER_LOAD_DAILY=0;//数据库中存的表示server load的type，日常监控
    public static final int TYPE_SERVER_LOAD_PEAK=1;//数据库中存的表示server load的type 表示每日峰值
    public static final int TYPE_SERVER_LOAD_VALLEY=2;//每日谷值
    public static final int TYPE_SERVER_LOAD_TOTAL=3;//每日总量
    public static final int TYPE_NOTIFY_SERVER_ONLINE=5;
    public static final int TYPE_NOTIFY_SERVER_OFFLINE=6;
}
