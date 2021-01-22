<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 设备列表
                    <p>说明：该列表只是为了模拟多台设备的功能，不是真正意义上的设备管理</p>
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
                <el-input v-model="query.name" placeholder="设备名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-lx-add" @click="handleAdd">添加设备</el-button>
                <el-button class="handle-refresh"  @click="handleRefresh">刷新</el-button>
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
                <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="name" label="设备名"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="maxSize" label="最大容量"></el-table-column>
                <el-table-column prop="currSize" label="当前容量"></el-table-column>
                <el-table-column label="设备状态" align="center">
                    <template slot-scope="scope">
                        <el-tag
                            :type="scope.row.status==='online'?'success':(scope.row.status==='offline'?'warning':'')"
                        >{{scope.row.status}}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column prop="date" label="注册时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-lx-hot"
                            @click="handleStart(scope.row.id)"
                        >启动</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button>
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
                <el-form-item label="设备名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item label="最大容量">
                    <el-input v-model="form.maxSize"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 添加弹出框 -->
        <el-dialog title="添加设备" :visible.sync="addVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="设备名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item label="最大容量">
                    <el-input v-model="form.maxSize"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { fetchData } from '../../api/index';
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                address: '',
                name: '',
                pageIndex: 1,
                pageSize: 5
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            addVisible:false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            var url="clients/"+this.query.pageIndex+"/"+this.query.pageSize
            var method='get'
            fetchData(url,method,null).then(res => {
               // console.log(res);
                this.tableData = res.data.clients;
                this.pageTotal = res.data.pageTotal;
                //console.log(res.data);
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        handleRefresh(){
            this.getData()
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除“'+this.tableData[index].name+'”吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    var url="delete/"+this.tableData[index].id
                    var method='delete'
                    fetchData(url,method,null).then(res => {
                        if(res.code==10000)
                        {
                            this.$message.success('删除成功');
                            this.tableData.splice(index, 1);
                        }else
                        {
                            this.$message.error('删除失败')
                        }
                    });
                    
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
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        handleStart(index) {
          this.$router.push({name: 'park', params: {id: index}})
        },
        handleAdd() {
         //   this.idx = index;
            this.form = {};
            this.addVisible = true;
        },
        // 保存编辑
        saveEdit() {
            var url="update"
            var method="post"
            fetchData(url,method,this.form).then(res => {
                if(res.code==10000)
                {
                    this.$message.success('修改成功');
                    this.editVisible = false;
                }else
                {
                    this.$message.error('修改失败，'+res.msg)
                }
                this.handleRefresh()
            });
        },
        saveAdd() {
            var url="add"
            var method="post"
            fetchData(url,method,this.form).then(res => {
                if(res.code==10000)
                {
                    this.$message.success('添加成功');
                    this.addVisible = false;
                }else
                {
                    this.$message.error('添加失败，'+res.msg)
                }
                this.handleRefresh()
            });
            
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        }
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
.handle-refresh{
    float:right;
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
