<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../../assets/img/img.jpg" class="user-avator" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">{{name}}</div>
                            <div>{{role}}</div>
                        </div>
                    </div>
                    <div class="user-info-list">
                        上次登录时间：
                        <span>2020-12-21</span>
                    </div>
                    <div class="user-info-list">
                        上次登录ip：
                        <span>192.168.43.102</span>
                    </div>
                </el-card>
                <el-card shadow="hover" style="height:252px;">
                    <div slot="header" class="clearfix">
                        <span>系统详情</span>
                    </div>服务器最高负载
                    <el-progress :percentage="71.3" color="#42b983"></el-progress>停车场最高负载
                    <el-progress :percentage="maxLoad" color="#f1e05a"></el-progress>停车场总负载
                    <el-progress :percentage="totalLoadRate"></el-progress>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{totalLoad}}</div>
                                    <div>今日入库车辆</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-lx-notice grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{totalAvailable}}</div>
                                    <div>可用车位总量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-lx-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{totalCurrSize}}</div>
                                    <div>车库当前总负载</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-card shadow="hover" style="height:400px;">
                    <div slot="header" class="clearfix">
                        <span>客户端记录</span>
                        <!-- <el-button style="float: right; padding: 3px 0" type="text">添加</el-button> -->
                    </div>
                    <el-table :show-header="false" :data="onlineHistory" style="width:100%;">
<!--                         <el-table-column width="40">
                            <template slot-scope="scope">
                                <el-checkbox v-model="scope.row.status"></el-checkbox>
                            </template>
                        </el-table-column> -->

                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item">{{scope.row.name}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" style="text-align:right">{{scope.row.time}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="80">
                            <template slot-scope="scope">
                                <el-tag class="status" :type="scope.row.status==='online'?'success':(scope.row.status==='offline'?'warning':'')">{{scope.row.status=='online'?'上线':'下线'}}
                                </el-tag>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import bus from '../common/bus';
import { fetchData } from '../../api/index';
export default {
    name: 'dashboard',
    data() {
        return {
            name: localStorage.getItem('ms_username'),
            onlineHistory: [],
            totalLoad:'-',
            totalAvailable:'-',
            totalCurrSize:'-',
            maxLoad:0.0,
            totalLoadRate:0.0,
            type:{
                    heartbeat:0,
                    client_online:1,
                    client_offline:2,
                    client_load_add:3,
                    client_load_sub:4
            }
        };
    },
    computed: {
        role() {
            return this.name === 'admin' ? '超级管理员' : '普通用户';
        }
    },
    created() {
        // this.handleListener();
        // this.changeDate();
        this.getOnlineData();
        this.getTotalLoad();
        this.getAvailable();
        var that=this;
        bus.$on('home',(msg)=>{
            that.handleBus(msg)
        })
    },
    // activated() {
    //     this.handleListener();
    // },
    // deactivated() {
    //     window.removeEventListener('resize', this.renderChart);
    //     bus.$off('collapse', this.handleBus);
    // },
    methods: {
        changeDate() {
            const now = new Date().getTime();
            this.data.forEach((item, index) => {
                const date = new Date(now - (6 - index) * 86400000);
                item.name = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
            });
        },
        getOnlineData(){
            var url="client/onlinehistory"
            var method="get"
            fetchData(url,method,null).then(res => {
                console.log(res);
                if(res.code==10000)
                {
                    this.onlineHistory=res.data
                }
                else this.$message.error("get data fail")
            });
        },
        getTotalLoad(){
            var url="client/totalLoad"
            var method="get"
            fetchData(url,method,null).then(res => {
                console.log(res);
                if(res.code==10000)
                {
                    this.totalLoad=res.data
                }
                else this.$message.error("get data fail")
            });
        },
        getAvailable(){
            var url="client/available"
            var method="get"
            fetchData(url,method,null).then(res => {
                console.log(res);
                if(res.code==10000)
                {
                    this.totalAvailable=res.data.totalAvailable;
                    this.totalCurrSize=res.data.totalCurrSize;
                    this.maxLoad=res.data.maxLoad;
                    if(res.data.totalAvailable==0)
                        this.totalLoadRate=0
                    else
                    this.totalLoadRate=parseFloat((res.data.totalCurrSize/res.data.totalAvailable).toFixed(2))
                }
                else this.$message.error("get data fail")
            });
        },
        handleBus(msg){
            console.log(msg)
            switch(msg.type)
            {
                case this.type.client_online:
                case this.type.client_offline:
                        this.getOnlineData();
                case this.type.client_load_add:
                case this.type.client_load_sub:
                        this.getTotalLoad();
                        this.getAvailable();
                    break;
                default:break;
            }
        }
    }
};
</script>


<style scoped>
.el-row {
    margin-bottom: 20px;
}

.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
}

.grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}

.grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
}

.grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
    background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
}

.user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
}

.user-avator {
    width: 120px;
    height: 120px;
    border-radius: 50%;
}

.user-info-cont {
    padding-left: 50px;
    flex: 1;
    font-size: 14px;
    color: #999;
}

.user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
}

.user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
}

.user-info-list span {
    margin-left: 70px;
}

.mgb20 {
    margin-bottom: 20px;
}

.todo-item {
    font-size: 14px;
}

.todo-item-del {
    text-decoration: line-through;
    color: #999;
}

.schart {
    width: 100%;
    height: 300px;
}
</style>
