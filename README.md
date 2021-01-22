# USTC_SSE_SA2020



中国科学技术大学软件学院 软件体系结构大型实验

一个模拟停车场的分布式实时监控系统

采用SpringBoot+Vue开发

## 系统说明

### 项目组成

| 项目名称      | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| vue-client    | 停车场客户端程序的前端页面                                   |
| client        | 停车场客户端程序的后端工程（实现客户端的CRUD）               |
| server        | 停车场系统服务器，用以和client保持连接实时获取状态           |
| register      | 停车场系统的注册器，类似于手游的服务器列表功能。用于发现server和负载均衡。 |
| webmanage     | 停车场后台管理系统的后端工程                                 |
| vue-webmanage | 停车场管理系统的前端页面                                     |

### 工作流程

1. register上线。
2. server上线（server项目启动）并发送消息通知register（自动），register将其加入其维护的服务器列表，此后server和register通过websocket保持长链接，并以一定的频率发送心跳包。
3. webmanage后台系统后端启动，会自动向register请求server的地址，依此server获取后台管理系统的地址，用以在client发生变化时实时通知后台管理系统更新UI页面。
4. webmanage前端页面启动，会和webmanage后台管理后端保持websocket长链接，并在client状态发生改变时候通过此长链接来更新UI页面，使用了vue的emit和on函数机制进行消息的传递。
5. client上线，会自动选择一台可用的服务器，点击上线即可实现实时监控
6. 上述几点中项目的启动流程只有register需要保持最先启动，剩余项目启动先后顺序无要求，但是有一点，server与register保持的连接断开以后只能重启工程重连

### 组件间通信机制

本项目设计多个组件，其通信采用json格式，对于http的接口，一般采用如下格式

```javascript
{
	'code':
	'msg':
	'data':
}
```

其中code和msg表示对应的状态码和提示文本，在global/ErrorCode.java中定义，data为返回的数据

对于采用websocket维持的长链接，之间的通信也采用json格式，将其序列化成字符串后在网络中传递，其格式如下

```javascript
{
	'type':
	'data':
}
```

type字段在global/Global.java中定义，data为返回数据

## 系统演示截图

见img目录

## 数据库脚本

见sql目录

park.sql为client项目使用到数据库

其他项目使用到数据库在parkserver.sql中

### 运行命令

SpringBoot项目直接在idea中打开运行即可

vue项目首先需要安装依赖，再运行。执行以下命令

```bash
npm install
npm run serve
```

