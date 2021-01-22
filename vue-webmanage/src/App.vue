<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>
<style>
    @import "./assets/css/main.css";
    @import "./assets/css/color-dark.css";     /*深色主题*/
    /*@import "./assets/css/theme-green/color-green.css";   浅绿色主题*/
</style>
<script>
    import bus from './components/common/bus';
    export default {
        data: function(){
            return {
            	type:{
            		heartbeat:0,
                    client_online:1,
                    client_offline:2,
                    client_load_add:3,
                    client_load_sub:4
            	}
            }
        },
        created(){
        	//console.log("ok")
        	this.localSocket()
        },
        methods:{
			localSocket() {
				//console.log("ok2")
     				let that = this;
     				if ("WebSocket" in window) {
        				that.ws = new WebSocket("ws://localhost:8084/1");
        				that.ws.onopen = function () {
           					console.log('websocket连接成功');
           					//console.log(that.homePage.totalLoad)
                            bus.$emit('home',"nihaoma")
                            bus.$emit('client',"wohenhao")
        				};
        				that.ws.onmessage=function(e){
                            //bus.$emit('home',e.data)
        					var msg=JSON.parse(e.data)
        					switch(msg.type)
        					{
        						case that.type.heartbeat:
        						var	reply={
        								type:that.type.heartbeat
        							}
        							that.ws.send(JSON.stringify(reply));
        							break;
                                // case that.type.client_online:
                                //     bus.$emit('home',msg.data)
                                //     bug.$emit('home')
                                //     break;
                                // case that.type.client_offline:
                                //     bus.$emit()
                                //     break;
                                // case that.type.client_load_change:
                                //     break;
        						default:
                                    bus.$emit('home',msg)
                                    bus.$emit('client',msg)
                                break;
        					}
        				}
        				that.ws.onclose = function () {
           					// 关闭 websocket
           					console.log("连接已关闭...");
           //断线重新连接
          					setTimeout(() => {
              				that.localSocket();
           					}, 2000);
        				};
    				} 
    				else 
    				{
        			// 浏览器不支持 WebSocket
        				console.log("您的浏览器不支持 WebSocket!");
        				this.openNotificationWithIcon('error', '浏览器', '您的浏览器不支持显示消息请更换', 1,1)
     				}
  			}
        }
    }
</script>