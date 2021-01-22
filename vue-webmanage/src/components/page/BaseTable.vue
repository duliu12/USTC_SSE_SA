<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 青停停车场
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-select v-model="query.serverId" placeholder="请选择服务器" class="handle-select mr10">
                    <el-option v-for="item in server " :key="item.serverId" :label="item.name" :value="item.serverId"></el-option>
                </el-select>
                <el-input v-model="query.name" placeholder="停车场名称" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="clientId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="name" label="停车场名称"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="currSize" label="当前负载"></el-table-column>
                <el-table-column prop="remainSize" label="剩余车位"></el-table-column>
                <el-table-column label="状态" align="center">
                    <template slot-scope="scope">
                        <el-tag
                            :type="scope.row.status==='online'?'success':(scope.row.status==='offline'?'danger':'')"
                        >{{scope.row.status=="online"?"在线":"离线"}}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-lx-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >修改</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-lx-down"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >下线</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { fetchData } from '../../api/index';
import bus from '../common/bus';
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                serverId: null,
                name: '',
                pageIndex: 1,
                pageSize: 5
            },
            server: [],
            serverId:null,
            clients: [],
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1,
            type:{
                    heartbeat:0,
                    client_online:1,
                    client_offline:2,
                    client_load_add:3,
                    client_load_sub:4,
                    server_online:5,
                    server_offline:6
            }
        };
    },
    created() {
        this.getServer();
        var that=this;
        bus.$on('client',(msg)=>{
            that.handleBus(msg)
        })
    },
    methods: {
        getServer() {
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
        getData(serverId,idx,size){
            if(this.query.serverId==null)
            {
                this.$message.error("请选择服务器!")
                return
            }
            var url="client/list/"+serverId+"/"+idx+"/"+size
            var method="get"
            fetchData(url,method,null).then(res => {
                console.log(res);
                if(res.code==10000)
                {
                    //this.query.pageIndex=1
                    //this.pageTotal=Math.ceil(this.clients.length/this.query.pageSize)
                    this.pageTotal=res.data.totalSize;
                    this.tableData=[];
                    this.tableData=res.data.clients;
                    this.serverId=serverId;
                    if(this.tableData.length==0)
                        this.$message.warning("暂时没有数据")
                }
                else this.$message.error("get data fail")
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.getData(this.query.serverId,this.query.pageIndex,this.query.pageSize)

        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要下线吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.handleOffline(this.serverId,row.clientId)
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        // 编辑操作
        handleEdit(index, row) {
            // this.idx = index;
            // this.form = row;
            // this.editVisible = true;
            this.$message.warning("修改不可用")
        },
        // 保存编辑
        saveEdit() {
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
        },
        // 分页导航
        handlePageChange(val) {
             this.$set(this.query, 'pageIndex', val);
             this.getData(this.query.serverId,this.query.pageIndex,this.query.pageSize);
        },
        handleOffline(serverId,clientId){
            var url="client/offline/"+serverId+"/"+clientId
            var method="get"
            fetchData(url,method,null).then(res => {
                console.log(res);
                if(res.code==10000)
                {
                    //this.query.pageIndex=1
                    //this.pageTotal=Math.ceil(this.clients.length/this.query.pageSize)
                    this.$message.success("下线成功")
                }
                else this.$message.error("下线失败")
            });
        },
        handleBus(msg){
            console.log(msg)
            switch(msg.type)
            {
                case this.type.client_online:
                     var found=false
                     this.tableData.forEach((item,index)=>{
                        if(item.clientId==msg.data.clientId)
                        {
                            console.log("id="+item.clientId+",status="+item.status)
                            item.status="online"
                            found=true
                            return false
                        }
                     })
                     if(!found)
                    {
                        this.getData(this.query.serverId,this.query.pageIndex,this.query.pageSize)
                    }
                    break;
                case this.type.client_offline:
                    this.tableData.forEach((item,index)=>{
                        if(item.clientId==msg.data.clientId)
                        {
                            item.currSize=msg.data.currSize
                            item.remainSize=msg.data.maxSize-msg.data.currSize
                            item.status="offline"
                            return false
                        }
                    })
                    break;
                case this.type.client_load_add:
                case this.type.client_load_sub:
                    this.tableData.forEach((item,index)=>{
                        if(item.clientId==msg.data.clientId)
                        {
                            item.currSize=msg.data.currSize;
                            item.remainSize=msg.data.maxSize-msg.data.currSize
                            return false
                        }
                    })
                    break;
                case this.type.server_online:
                        var server={
                            serverId:msg.data.serverId,
                            name:msg.data.name
                        }
                        this.server.push(server)
                        this.$message(server.name+"上线了")
                    break;
                case this.type.server_offline:
                        var found=false;
                        var idx;
                        var name;
                        this.server.forEach((item,index)=>{
                            if(item.serverId==msg.data.serverId)
                            {
                                console.log("id="+item.serverId)
                                idx=index
                                name=item.name
                                found=true
                                return false
                            }
                        })
                        if(found)
                        {
                            this.server.splice(idx,1)
                            this.$message(name+"已下线")
                        }
                    break;
                default: break;
            }

        },
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
