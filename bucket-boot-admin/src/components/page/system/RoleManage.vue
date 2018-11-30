<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>角色管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-form ref="form" :model="searchParams" label-width="80px">
                    <el-row :gutter="15">
                        <el-col :span="6">
                            <el-form-item label="角色名称:">
                                <el-input v-model="searchParams.roleName" placeholder="角色名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-button type="primary" icon="search" @click="search">查询</el-button>
                        </el-col>
                    </el-row>
                </el-form>

                <el-row>
                    <el-col >
                        <el-button-group>
                            <el-button type="primary" @click="showAddDialog">新增</el-button>
                            <el-button type="primary" @click="showEditDialog" :disabled="rowSelected">修改</el-button>
                            <el-button type="primary" @click="showAssignAuthDialog" :disabled="rowSelected">分配权限</el-button>
                            <el-button type="danger" @click="alertDelete" :disabled="rowSelected">删除</el-button>
                        </el-button-group>

                    </el-col>
                </el-row>
            </div>
            <div class="table-wrapper">
                <el-table :data="roleDatas" stripe class="table" ref="roleTable">
                    <el-table-column
                        label="选择"
                        width="50">
                        <template slot-scope="scope">
                            <el-radio v-model="currentRole" :label="scope.row"><i></i></el-radio>
                        </template>
                    </el-table-column>
                    <el-table-column prop="roleName" label="角色名称" width="150px">
                    </el-table-column>
                    <el-table-column prop="roleStatus" label="是否有效" width="100px">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.roleStatus === '1'" type="success">有效</el-tag>
                            <el-tag v-else type="danger">无效</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="roleDesc" label="描述" width="auto">
                    </el-table-column>
                </el-table>
                <div class="pagination">
                    <el-pagination background
                        @current-change="handleCurrentChange"
                        @size-change="handleSizeChange"
                        :current-page="searchParams.pageNum"
                        :page-sizes="[10, 20, 30, 50, 100]"
                        :page-size="searchParams.pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total">
                    </el-pagination>
                </div>
            </div>

        </div>


        <!-- 新增模拟弹出框 -->
        <el-dialog :visible.sync="addVisible" width="30%">
            <div slot="title" class="admin-dialog__title">
                <h2>添加角色</h2>
            </div>
            <el-form ref="addRoleForm" :model="roleForm" :rules="roleValdateRules" label-width="80px">
                <el-form-item label="角色名称" prop="roleName">
                    <el-input v-model="roleForm.roleName"></el-input>
                </el-form-item>
                <el-form-item label="是否有效" prop="roleStatus">
                    <el-select v-model="roleForm.roleStatus">
                        <el-option value="1" label="有效"></el-option>
                        <el-option value="0" label="无效"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="描述" prop="roleDesc">
                    <el-input v-model="roleForm.roleDesc" type="textarea" :rows="3"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelAddRole">取 消</el-button>
                <el-button type="primary" @click="saveRole('addRoleForm')">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 编辑模拟弹出框 -->
        <el-dialog :visible.sync="editVisible" @closed="cancelEditRole" width="30%">
            <div slot="title" class="admin-dialog__title">
                <h2>修改角色信息</h2>
            </div>
            <el-form ref="editRoleForm" :model="roleForm" :rules="roleValdateRules" label-width="80px">
                <el-form-item label="角色名称" prop="roleName">
                    <el-input v-model="roleForm.roleName"></el-input>
                </el-form-item>
                <el-form-item label="是否有效" prop="roleStatus">
                    <el-select v-model="roleForm.roleStatus">
                        <el-option value="1" label="有效"></el-option>
                        <el-option value="0" label="无效"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="描述" prop="roleDesc">
                    <el-input v-model="roleForm.roleDesc" type="textarea" :rows="3"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelEditRole">取 消</el-button>
                <el-button type="primary" @click="saveRole('addRoleForm')">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 分配权限模拟弹出框 -->
        <el-dialog :visible.sync="authVisible" @closed="cancelAssignAuthRole" width="30%">
            <div slot="title" class="admin-dialog__title">
                <h2>分配权限</h2>
            </div>

            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelAssignAuthRole">取 消</el-button>
                <el-button type="primary" @click="assignAuth">提 交</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>

    import {postRequest} from '../../../utils/api'
    import {getRequest} from '../../../utils/api'
    import {putRequest} from '../../../utils/api'
    import {deleteRequest} from '../../../utils/api'
    import mockData from '../../../mock/data.js'

    export default {
        name: 'roleManage',
        data() {
            return {
                roleLoading: true,
                currentRole: null,
                roleDatas: [],
                total: 0,
                multipleSelection: [],
                searchParams: {
                    pageNum: 1,
                    pageSize: 10
                },
                addVisible: false,
                editVisible: false,
                authVisible: false,
                roleForm: {
                },
                roleValdateRules: {
                    roleName: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.getData();
        },
        computed: {
            rowSelected() {
                return this.currentRole == null;
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.searchParams.pageNum = val;
                this.getData();
            },
            handleSizeChange(val) {
                this.searchParams.pageSize = val;
                this.getData();
            },
            // 获取 列表
            getData() {
                this.roleDatas = mockData.roleArray;
                // var that = this;
                // getRequest("/role/get/list", this.searchParams).then((res) => {
                //     this.roleDatas = res.data.data
                //     this.total = res.data.total
                // })
                // .catch(function (error) {
                //     that.$message.error('获取角色列表失败')
                // })
            },
            search() {
                this.getData();
            },
            showAddDialog(index, row) {
                this.addVisible = true;
                this.roleForm = {roleStatus: '1'};
            },
            showEditDialog(index, row) {
                this.editVisible = true;
                this.roleForm = Object.assign({}, this.currentRole);
            },
            showAssignAuthDialog() {
                this.authVisible = true;
            },
            cancelAddRole() {
                this.addVisible = false;
            },
            cancelEditRole() {
                this.editVisible = false;
            },
            cancelAssignAuthRole() {
                this.authVisible = false;
            },
            // 保存新增
            saveRole(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // postRequest("/role/add", this.roleForm).then((res) => {
                        //     if (res.data.success) {
                        //         this.$message.success("添加角色完成");
                        //         this.addVisible = false;
                        //         this.getData();
                        //     } else {
                        //         this.$message.error("新增失败");
                        //     }
                        // })
                    }
                });
            },
            alertDelete() {
                this.$confirm('是否确认删除该角色信息?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    // 取消删除
                });
            },
            assignAuth() {
            }
        }
    }

</script>

<style scoped>
</style>
