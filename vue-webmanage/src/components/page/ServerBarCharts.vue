<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-pie-chart"></i> 服务器历史状态
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="container">
            <el-form ref="form" :model="form" label-width="80px">
                   <!--  <el-form-item label="表单名称">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item> -->
                     <el-form-item label="服务器">
                        <el-col :span="5">
                            <el-select v-model.number="form.serverId"  placeholder="请选择服务器">
                                <el-option v-for="item in server " :key="item.serverId" :label="item.name" :value="item.serverId"></el-option>
                                <!-- <el-option :key="form.park[0]['clientId']" :label="form.park[0].name" :value="form.park[0]['clientId']"></el-option> -->
                          <!--       <el-option key="imoo1" label="imoo2" value="imoo3"></el-option> -->
                            </el-select>
                        </el-col>
                        <el-col :span="5" >
                            <el-date-picker
                                type="date"
                                placeholder="选择日期"
                                v-model="form.date"
                                value-format="yyyy-MM-dd"
                                style="width: 100%;"
                            ></el-date-picker>
                        </el-col>
                         <el-button type="primary" @click="onSubmit" style="margin-left: 10px">查询</el-button>
                         <el-button  @click="exportCharts" style="margin-left: 10px">打印</el-button>
                          <i class="el-icon-lx-info" style="margin-left: 2px" >选择某天所在的一周</i>
                    </el-form-item>
            </el-form>
            <div class="schart-box">
               <!--  <div class="content-title">折线图</div> -->
                <schart class="schart" canvasId="bar" :options="options"></schart>
            </div>
<!--             <div class="schart-box">
                <div class="content-title">饼状图</div>
                <schart class="schart" canvasId="pie" :options="options3"></schart>
            </div>
            <div class="schart-box">
                <div class="content-title">环形图</div>
                <schart class="schart" canvasId="ring" :options="options4"></schart>
            </div> -->
        </div>
    </div>
</template>

<script>
import Schart from 'vue-schart';
import { saveAs } from 'file-saver';
import { fetchData } from '../../api/index';
export default {
    name: 'basecharts',
    components: {
        Schart
    },
    data() {
        return {
            form:{
                serverId:null,
                date: '',
            },
            server:[],
            options: {
                type: 'line',
                title: {
                    text: ''
                },
                bgColor: '#fbfbfb',
                labels: [],
                datasets: [
                    // {
                    //     label: '上线车库总量',
                    //     //fillColor: 'rgba(241, 49, 74, 0.5)',
                    //     data: []
                    // },
                    // {
                    //     label: '峰值',
                    //     data: []
                    // },
                    {
                        label: '峰值',
                        data: []
                    }
                ]
            },
            title:'历史负载'
           
        };
    },
    methods:{
        onSubmit(){
            if(this.form.serverId==null||this.form.date=='')
            {
                this.$message.error("请选择服务器和日期")

            }
            else
            {
                var name=""
                this.server.forEach((item,index)=>{
                    if(item.serverId==this.form.serverId)
                    {
                        name=item.name
                        return false
                    }
                })
                this.options.title.text=name+this.title
                this.options.labels=[]
                this.options.datasets[0].data=[]
                // this.options.datasets[1].data=[]
                // this.options.datasets[2].data=[]
                var url="server/weekload"
                var method="post"
                var data={
                    serverId:this.form.serverId,
                    date:this.form.date
                }
                fetchData(url,method,data).then(res => {
                    console.log(res);
                    if(res.code==10000)
                    {
                        if(res.data.length==0)
                        {
                            this.$message.warning("没有数据哦！")
                        }
                        else
                        {
                            res.data.forEach(item=>{
                                                var datelist=item.date.split("-")
                                                var date=datelist[1]+"月"+datelist[2]+"日"
                                                this.options.labels.push(date)
                                                this.options.datasets[0].data.push(item.peak==null?0:item.peak)
                                                // this.options.datasets[1].data.push(item.peak==null?0:item.peak)
                                                // this.options.datasets[2].data.push(item.valley==null?0:item.valley)
                                                })
                        }
                    }
                    else this.$message.warn("get data fail")
                });
            }
        },
        getClient(){
            var url="server/index"
            var method="get"
            fetchData(url,method,null).then(res => {
                console.log(res);
                if(res.code==10000)
                {
                    this.server=res.data
                }
                else this.$message.error("get data fail")
            });
        },
        exportCharts(){

            if (typeof Blob !== 'function') {
                this.$message.error('您的浏览器不支持！请使用最新版本chrome/firefox浏览器!')
                return
            }
            const canvas = document.getElementById('bar');
            var filename=this.options.title.text+'.png'
            try {
                    canvas.toBlob((blob) => {
                        saveAs(
                            blob,
                            filename
                        )
                    })
                } catch (e) {
                    console.error(e)
                    this.$message.error('导出失败！')
                }
        }
    },
    created(){
       this.getClient();
    }
};
</script>

<style scoped>
.schart-box {
    display: inline-block;
    margin: 20px;
}
.schart {
    width: 600px;
    height: 400px;
}
.content-title {
    clear: both;
    font-weight: 400;
    line-height: 50px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
}
</style>