<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 设备详情
                </el-breadcrumb-item>
                <!-- <el-breadcrumb-item>设备详情</el-breadcrumb-item> -->
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="data" label-width="80px">
                    <el-form-item label="设备名称">
                        <el-input class="el-form1" v-model="data.name" :disabled="component.editable"></el-input>
                        <el-button class="btn-online" type='primary' @click="handleOnline($event)" :disabled="component.onlineable" >
                            {{component.onlineval}}
                        </el-button>
                        <el-tag class="status" :type="data.status==='online'?'success':(data.status==='offline'?'warning':'')">
                            {{data.status}}
                        </el-tag>
                    </el-form-item>
                    <el-form-item label="设备地址">
                        <el-input class="el-form1" v-model="data.address"  :disabled="component.editable"></el-input>
                    </el-form-item>
                     <el-form-item label="最大容量">
                        <el-input class="el-form1" v-model="data.maxSize" :disabled="component.editable" ></el-input>
                    </el-form-item>
                    <el-form-item label="当前负载">
                        <el-input class="el-form1" v-model="data.currSize" disabled></el-input>
                            <el-button class="btn-add" type="primary" icon="el-icon-lx-add" @click="handleAdd()" :disabled="component.addable" >{{component.addval}}</el-button>
                            <el-button type="danger"icon="el-icon-lx-move" @click="handleSub()" :disabled="component.subable">{{component.subval}}</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" :disabled="component.submitable" @click="handleSubmit()" >提交修改</el-button>
                        <el-button @click="handleCancle()" :disabled="component.cancelable">取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
import { fetchData } from '../../api/index';
export default {
    name: 'baseform',
    data() {
        return {
            id:10,
            client:{
                // 10:{
                //     data:{
                //         id:10,
                //         name: '123',
                //         address: '456',
                //         maxSize: 100,
                //         currSize:0,
                //         status: 'offline'
                //     },
                //     component:{
                //         editable:false,
                //         addable: true,
                //         subable:true,
                //         submitable:false,
                //         cancelable:false,
                //         onlineable:false,
                //         addval: "车辆入库",
                //         subval:"车辆出库",
                //         onlineval:"上线",
                //         timer:null,
                //         time:0,
                //     },
                //     server:
                //     {
                //         id:1,
                //         ip: '127.0.0.1:8082',
                //         name: '华东服务器',
                //         api: '/park'
                //     },
                //     websocket:null,
                //     heartbeattimer:null
                // }
            },
            data: {
                id: 0,
                name: '',
                address: '',
                maxSize: null,
                currSize: 0,
                status:'offline'
            },
            component:{
                editable:false,
                addable: true,
                subable:true,
                submitable:false,
                cancelable:false,
                onlineable:false,
                addval: "车辆入库",
                subval:"车辆出库",
                onlineval:"上线",
                timer:null,
                time:0
            },
            server:{},
            websocket:{},
            heartbeattimer:null,
            messagetype:{
                type_client_online: 0,
                type_client_offline: 1,
                type_client_heartbeat: 2,
                type_client_update: 3,
                type_client_load_add: 4,
                type_client_load_sub: 5,
                type_client_info: 6
            }
        };
    },
    created(){
        this.id=this.$route.params.id
        this.getData(this.id)
    },
    mounted(){
       // console.log("我被调用了")
    },
    destroyed(){
        for(var id in this.client)
        {
            console.log(id)
            this.saveStatus(id,true)
            if(this.client[id]['websocket']!=null)
            {
                this.client[id]['websocket'].close()
                this.client[id]['websocket']=null
            }
        }
    },
    watch:{
        //var that=this
        '$route'(to,from){
            // if(from.name=='park'&&this.data.status=='online')
            // {
            //    // this.saveStatus()
            // }
            // for(var key in this.client)
            // {
            //     console.log("key="+key)
            //     console.log(this.client[key]['component'])
            // }
            //saveStatus()
            if(from.name=='park')
            {
                var nowid=this.id
                this.client[nowid]['data']=JSON.parse(JSON.stringify(this.data))
                this.client[nowid]['component']=JSON.parse(JSON.stringify(this.component))
                this.saveStatus(nowid,false)
            }
            if(to.name=='park')
            {
                this.id=to.params.id
                var id=this.id
                if(this.client[id]==null)
                    this.getData(id)
                else
                {
                    //console.log(this.id)
                    this.data=this.client[id]['data']
                    //console.log(this.client[this.id]['component'])
                    this.component=this.client[id]['component']
                }
                // console.log("to 的id："+id)
                // console.log(this.client[id]['component'])
            }
        }
    },
    methods: {
        getval(id,type,key){
            return this.client[id][type][key];
        },
        onSubmit() {
            this.$message.success('提交成功！');
        },
        getData(id) {
             //var id=this.id
           //this.id=id
            if(this.client[id]==null)
            {
                this.client[id]={
                    data:{},
                    component:{},
                    websocket:null,
                    server:{},
                    heartbeattimer:null
                }
            }
                this.client[id]['data']=this.data
                this.client[id]['component']=this.component
                var url="client/"+id;
                var method='get'
                fetchData(url,method,null).then(res => {
                    if(res.data!=null)
                    {
                        this.client[id]['data'] = res.data;
                        this.data=this.client[id]['data']
                       // console.log(this.client[this.id]['data'])
                   //this.client[this.]
                        var serverapi="server"
                        var method="get"
                        fetchData(serverapi,method,null).then(res=>{
                            if(res.data!=null)
                                this.client[id]['server']=res.data
                            //console.log(res.data)
                        })
 
                        if(this.client[id]['data']['status']=='offline')
                        {    
                            this.setOffline(id)
                            //this.component=this.client[this.id]['component']
                        }
                        else if(this.client[id]['data']['status']=='online')
                        {
                            this.setOnline(id)
                            //this.component=this.client[this.id]['component']
                        }
                    }
                    else
                    {
                        this.client[id]['data']={}
                        this.$message.error('数据非法')
                        this.$router.go(-1);
                    }
               // console.log(res.list);
                });
               
          //  }
            //this.data=this.client[this.id]['data']
            

        },
        saveStatus(id,offline){
            var url="save/"+this.client[id]['data']['id']+"/"+this.client[id]['data']['status']+"/"+this.client[id]['data']['currSize']
            if(offline)
                url="save/"+this.client[id]['data']['id']+"/offline/"+this.client[id]['data']['currSize']
            var method="post"
            fetchData(url,method,this.client[id]['data']).then(res => {
                if(res.code==10000)
                {}   // this.$message.success('状态自动保存成功')
                else
                    this.$message.error(res.msg)
              // this.submitable=false
            });
        },
        setOnline(id){
            this.client[id]['data']['status']='online'
            this.client[id]['component']['editable']=true
            this.client[id]['component']['onlineval']="下线"
            this.client[id]['component']['addable']=false
            this.client[id]['component']['subable']=false
            this.client[id]['component']['submitable']=true
            this.client[id]['component']['cancelable']=true
            this.client[id]['component']['onlineable']=false
            this.component=this.client[id]['component']
        },
        setOffline(id){
            this.client[id]['data']['status']='offline'
            this.client[id]['component']['editable']=false
            this.client[id]['component']['onlineval']="上线"
            this.client[id]['component']['addable']=true
            this.client[id]['component']['subable']=true
            this.client[id]['component']['submitable']=false
            this.client[id]['component']['cancelable']=false
            this.client[id]['component']['onlineable']=false
            this.client[id]['data']['currSize']=0
            this.component=this.client[id]['component']
        },
        handleOnline(e){
            var id=this.id
            //console.log(this.data.status)
            this.client[id]['component']['onlineable']=true
            this.client[id]['component']['onlineval']="waiting..."
            if(this.client[id]['data']['status']=='offline')
            {
                //console.log('我被执行了')
                // var wsapi="ws://"+this.client[id]['server']['ip']+this.client[id]['server']['api']+"/"+id+"/"+this.client[id]['data']['name']+"/"+this.client[id]['data']['address']+"/"+this.client[id]['data']['maxSize']+"/"+this.client[id]['data']['currSize']
                    var wsapi="ws://"+this.client[id]['server']['ip']+this.client[id]['server']['api']+"/"+id
                    this.client[id]['websocket']=new WebSocket(wsapi)
                    this.client[id]['websocket'].onmessage=this.websocketonmessage
                    this.client[id]['websocket'].onopen=this.websocketonopen
                    this.client[id]['websocket'].onclose=this.websocketonclose
                    this.client[id]['websocket'].onerror=this.websocketonerror
                var url="status/"+id+"/online"
                var method="post"
                //后端请求上线后设置
                if(true)//打开websocket成功后，修改上线状态
                {
                    fetchData(url,method,null).then(res => {
                        if(res.code==10000)//上线成功
                        {
                            this.setOnline(id)

                        }
                        else
                        {
                            this.setOffline(id)
                            //断开websocket连接或者重试
                        }
               // console.log(res.list);
                    });
                }
            }
            else if(this.client[id]['data']['status']=='online')
            {

                if(this.client[id]['websocket']!=null)
                {
                    this.client[id]['websocket'].close()
                    clearInterval(this.client[id]['heartbeattimer'])
                    this.client[id]['heartbeattimer']=null
                }
                var url="status/"+id+"/offline"
                var method="post"
                if(true)//关闭websocket成功后，修改上线状态
                {
                    fetchData(url,method,null).then(res => {
                        if(res.code==10000)//上线成功
                        {
                            this.setOffline(id)

                        }
                        else
                        {
                            this.setOnline(id)
                            //断开websocket连接或者重试
                        }
               // console.log(res.list);
                    });
                }
            }
        },
        handleAdd(){
            //console.log('add')
            var id=this.id
            if(this.client[id]['data']['currSize']<this.client[id]['data']['maxSize'])
            {    
                //this.addable=true
                this.client[id]['component']['addable']=true;
                this.client[id]['component']['subable']=true;
                this.client[id]['component']['time']=10
                this.addval=this.time+"s后可用"
                if(!this.client[id]['component']['timer'])
                {
                    this.client[id]['component']['timer']=setInterval(()=>{
                        if(this.client[id]['component']['time']>0)
                        {
                            this.client[id]['component']['time']--
                            this.client[id]['component']['addable']=true
                            this.client[id]['component']['subable']=true
                            this.client[id]['component']['addval']=this.client[id]['component']['time']+"s后可用"

                        }else
                        {
                            this.client[id]['component']['time']=0
                            this.client[id]['component']['addable']=false
                            this.client[id]['component']['subable']=false
                            this.client[id]['component']['addval']="车辆入库"
                            clearInterval(this.client[id]['component']['timer'])
                            this.client[id]['component']['timer']=null

                        }
                    },1000)
                }
               // console.log(this)
                this.client[id]['data']['currSize']++
                var message={
                    type: this.messagetype.type_client_load_add,
                    data: {
                        'currSize': this.client[id]['data']['currSize']
                    }
                }
                this.client[id]['websocket'].send(JSON.stringify(message))
                this.$message.success('入库成功')

            }
            else
            {
                this.$message.error('入库失败,请稍后重试');
            }

        },
        handleSub(){
            var id=this.id
            if(this.client[id]['data']['currSize']>0)
            {    
                this.client[id]['component']['addable']=true;
                this.client[id]['component']['subable']=true;
                this.client[id]['component']['time']=10
                this.subval=this.time+"s后可用"
                if(!this.client[id]['component']['timer'])
                {
                    this.client[id]['component']['timer']=setInterval(()=>{
                        if(this.client[id]['component']['time']>0)
                        {
                            this.client[id]['component']['time']--
                            this.client[id]['component']['addable']=true
                            this.client[id]['component']['subable']=true
                            this.client[id]['component']['subval']=this.client[id]['component']['time']+"s后可用"

                        }else
                        {
                            this.client[id]['component']['time']=0
                            this.client[id]['component']['addable']=false
                            this.client[id]['component']['subable']=false
                            this.client[id]['component']['subval']="车辆出库"
                            clearInterval(this.client[id]['component']['timer'])
                            this.client[id]['component']['timer']=null

                        }
                    },1000)
                }
                this.client[id]['data']['currSize']--
                var message={
                    type: this.messagetype.type_client_load_sub,
                    data: {
                        'currSize': this.client[id]['data']['currSize']
                    }
                }
                this.client[id]['websocket'].send(JSON.stringify(message))
                this.$message.warning('出库成功')
                this.client[id]['component']['subable']=true;

            }
            else
            {
                this.$message.error('车库为空，出库失败');
            }
        },
        handleSubmit(){
            var id=this.id
            console.log(this.client[id]['data'])
            var url="update/"
            var method="post"
            fetchData(url,method,this.client[id]['data']).then(res => {
                if(res.code==10000)
                    this.$message.success('修改成功')
                else
                    this.$message.error(res.msg)
              // this.submitable=false
            });
        },
        handleCancle(){
            var id=this.$route.params.id
            var url="client/"+id;
            var method='get'
            fetchData(url,method,null).then(res => {
                if(res.data!=null)
                 {
                   this.client[id]['data'] = res.data;
                   if(this.client[id]['data']['status']=='offline')
                    {    
                        this.client[id]['component']['onlineable']=false
                        this.client[id]['component']['onlineval']='上线'
                        this.client[id]['component']['editable']=false
                        this.client[id]['component']['submitable']=false
                    }else
                    {
                        this.client[id]['component']['onlineable']=false
                        this.client[id]['component']['onlineval']='下线'
                        this.client[id]['component']['editable']=true
                        this.client[id]['component']['submitable']=true
                    }
                    this.data=this.client[id]['data']
                    this.component=this.client[id]['component']

                 }
                else
                {
                    this.client[id]['data']={}
                    this.$message.error('数据非法')
                    this.$router.go(-1);
                }
               // console.log(res.list);
            });
        },
        websocketonopen(){

        },
        websocketonclose(e){
            console.log(e)
            //var id=e.url
            //console.log(e.target==this.client[1]['websocket'])
            for(var key in this.client)
                if(e.target==this.client[key]['websocket'])
                var id=key
            //console.log("w s id:"+id)
            this.$message.warning("您已掉线")
            if(this.client[id]['websocket']!=null)
            {
                this.client[id]['websocket'].close()
                clearInterval(this.client[id]['heartbeattimer'])
                this.client[id]['heartbeattimer']=null
            }
            console.log('status:'+this.client[id]['websocket'].readyState)
            this.setOffline(id)
            this.saveStatus(id,true)
            console.log("连接关闭了")
        },
        websocketonmessage(e){
            var res=JSON.parse(e.data)
            for(var key in this.client)
                if(e.target==this.client[key]['websocket'])
                    var id=key
            var type=res.type
            switch(type)
            {
                case this.messagetype.type_heartbeat:// 2 stand for type_heartbeat
                        //console.log('我被执行了')
                        var heartbeat={
                                'type':2,
                                'data':null
                            }
                        this.client[id]['heartbeattimer']=setInterval(()=>{
                            if(this.client[id]['websocket']!=null&&this.client[id]['websocket'].readyState==1)
                           { 
                                this.client[id]['websocket'].send(JSON.stringify(heartbeat));
                                console.log("id "+id+" send heartbeat")
                            }else{
                                clearInterval(this.client[id]['heartbeattimer'])
                                this.client[id]['heartbeattimer']=null
                            }
                        },100000)
                    break;
                case this.messagetype.type_client_info:
                    var info={
                        type: this.messagetype.type_client_info,
                        data:{
                                'name':this.client[id]['data']['name'],
                                'address':this.client[id]['data']['address'],
                                'maxSize': this.client[id]['data']['maxSize']
                        }
                    }
                    if(this.client[id]['websocket']!=null&&this.client[id]['websocket'].readyState==1)
                    {
                        this.client[id]['websocket'].send(JSON.stringify(info));
                        console.log("send:"+JSON.stringify(info));
                    }
                default: break;
            }
        },
        websocketonerror(){

        }
    }
};
</script>
<style scoped>
.el-form1{
    width: 50%
}
.btn-online{
    margin-left: 20px
}
.btn-add{
    margin-left: 20px
}
.status{
    margin-left: 10px
}
</style>