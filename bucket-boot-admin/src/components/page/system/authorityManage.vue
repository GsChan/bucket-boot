<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-form ref="form" :model="searchParams" label-width="80px">
                    <el-row :gutter="15">
                        <el-col :span="6">
                            <el-form-item label="权限名称:">
                                <el-input v-model="searchParams.authName" placeholder="权限名称"></el-input>
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
                            <el-button type="danger" @click="alertDelete" :disabled="rowSelected">删除</el-button>
                        </el-button-group>

                    </el-col>
                </el-row>
            </div>
            <div class="table-wrapper">
                <el-table :data="authorityDatas" stripe>
                    <el-table-column
                        label="选择"
                        width="50" align="center">
                        <template slot-scope="scope">
                            <el-radio v-model="currentAuthority" :label="scope.row"><i></i></el-radio>
                        </template>
                    </el-table-column>
                    <el-table-tree-column
                        file-icon="table-tree-icon-file"
                        folder-icon="table-tree-icon-folder"
                        treeKey="authcode"
                        childNumKey="childNum"
                        parentKey="parentCode"
                        levelKey="level"
                        prop="authName" label="权限名称" width="250">
                    </el-table-tree-column>
                    <el-table-column prop="authUrl" label="权限链接" width="200">
                        <template slot-scope="scope">
                            {{!!scope.row.authUrl ? scope.row.authUrl : '#'}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="authType" label="权限类型" width="120" align="center">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.authType === '0'" type="success">目录</el-tag>
                            <el-tag v-else-if="scope.row.authType === '1'" type="warning">菜单</el-tag>
                            <el-tag v-else>按钮</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="authStatus" label="是否可用" width="120" align="center">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.authStatus === '1'" type="success">可用</el-tag>
                            <el-tag v-else type="danger">不可用</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="authIsShow" label="是否显示" width="120" align="center">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.authIsShow === '1'" type="success">显示</el-tag>
                            <el-tag v-else type="danger">不显示</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="collapsed" label="是否展开" width="120" align="center">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.collapsed === '1'" type="success">展开</el-tag>
                            <el-tag v-else type="danger">不展开</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="authDesc" label="描述" width="auto"></el-table-column>
                </el-table>
            </div>

        </div>

        <!-- 新增模拟弹出框 -->
        <el-dialog :visible.sync="addVisible"
            width="40%">
            <div slot="title" class="admin-dialog__title">
                <h2>添加权限</h2>
            </div>
            <el-form ref="addAuthority" :model="authorityForm" :rules="authorityValdateRules" label-width="80px">
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="权限名称" prop="authName">
                            <el-input v-model="authorityForm.authName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否可用" prop="authStatus">
                            <el-select v-model="authorityForm.authStatus">
                                <el-option value="1" label="可用"></el-option>
                                <el-option value="0" label="不可用"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="权限链接" prop="authUrl">
                            <el-input v-model="authorityForm.authUrl"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否可见" prop="authIsShow">
                            <el-select v-model="authorityForm.authIsShow" width="100%">
                                <el-option value="1" label="可见"></el-option>
                                <el-option value="0" label="不可见"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="权限类型" prop="authType">
                            <el-select v-model="authorityForm.authType" default-first-option>
                                <el-option value="0" label="目录权限"></el-option>
                                <el-option value="1" label="菜单权限"></el-option>
                                <el-option value="2" label="按钮权限"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否展开" prop="collapsed">
                            <el-select v-model="authorityForm.collapsed">
                                <el-option value="1" label="展开"></el-option>
                                <el-option value="0" label="不展开"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col>
                        <el-form-item label="权限描述" prop="authDesc">
                            <el-input v-model="authorityForm.authDesc"
                                type="textarea"
                                :rows="4"
                                placeholder="请输入内容"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelAddAuthority">取 消</el-button>
                <el-button type="primary" @click="saveAuthority('addAuthority')">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 编辑模拟弹出框 -->
        <el-dialog :visible.sync="editVisible" @closed="cancelEditAuthority" width="40%">
            <div slot="title" class="admin-dialog__title">
                <h2>修改权限信息</h2>
            </div>
            <el-form ref="editAuthorityForm" :model="authorityForm" :rules="authorityValdateRules" label-width="80px">
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="权限名称" prop="authName">
                            <el-input v-model="authorityForm.authName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否可用" prop="authStatus">
                            <el-select v-model="authorityForm.authStatus">
                                <el-option value="1" label="可用"></el-option>
                                <el-option value="0" label="不可用"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="权限链接" prop="authUrl">
                            <el-input v-model="authorityForm.authUrl"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否可见" prop="authIsShow">
                            <el-select v-model="authorityForm.authIsShow" width="100%">
                                <el-option value="1" label="可见"></el-option>
                                <el-option value="0" label="不可见"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="权限类型" prop="authType">
                            <el-select v-model="authorityForm.authType" default-first-option>
                                <el-option value="0" label="目录权限"></el-option>
                                <el-option value="1" label="菜单权限"></el-option>
                                <el-option value="2" label="按钮权限"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否展开" prop="collapsed">
                            <el-select v-model="authorityForm.collapsed">
                                <el-option value="1" label="展开"></el-option>
                                <el-option value="0" label="不展开"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col>
                        <el-form-item label="权限描述" prop="authDesc">
                            <el-input v-model="authorityForm.authDesc"
                                type="textarea"
                                :rows="4"
                                placeholder="请输入内容"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelEditAuthority">取 消</el-button>
                <el-button type="primary" @click="saveAuthority('addAuthority')">提 交</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>

    import {authApi} from '@/service/api'
    import ElTableTreeColumn  from 'element-tree-grid'
    import mockData from '@/mock/data.js'

    export default {
        name: 'authManage',
        components: {
            ElTableTreeColumn
        },
        data() {
            return {
                currentAuthority: null,
                authorityDatas: [],
                total: 0,
                searchParams: {
                    pageNum: 1,
                    pageSize: 10
                },
                addVisible: false,
                editVisible: false,
                authorityForm: {
                },
                authorityValdateRules: {
                    authName: [
                        { required: true, message: '请输入权限名称', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.getData();
        },
        computed: {
            rowSelected() {
                return this.currentAuthority == null;
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
                this.authorityDatas = mockData.authArray;
                // var that = this;
                // getRequest("/employee/get/list", this.searchParams).then((res) => {
                //     this.authorityDatas = res.data.data
                //     this.total = res.data.total
                // })
                // .catch(function (error) {
                //     that.$message.error('获取员工列表失败')
                // })
            },
            search() {
                this.getData();
            },
            showAddDialog(index, row) {
                this.addVisible = true;
                this.authorityForm = {
                    authType: "0",
                    authStatus: "1",
                    authIsShow: "1",
                    collapsed: "1",
                    parentCode: !!this.currentAuthority ? this.currentAuthority.authcode : null,
                    level: !!this.currentAuthority ? parseInt(this.currentAuthority.level) + 1 + '' : '0'
                };
            },
            showEditDialog(index, row) {
                this.editVisible = true;
                this.authorityForm = Object.assign({}, this.currentAuthority);
            },
            cancelAddAuthority() {
                this.addVisible = false;
            },
            cancelEditAuthority() {
                this.editVisible = false;
            },
            // 保存新增
            saveAuthority(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // postRequest("/employee/add", this.authorityForm).then((res) => {
                        //     if (res.data.success) {
                        //         this.$message.success("添加员工完成");
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
                this.$confirm('是否确认删除该权限信息?', '提示', {
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
            }
        }
    }

</script>

<style scoped>
</style>
