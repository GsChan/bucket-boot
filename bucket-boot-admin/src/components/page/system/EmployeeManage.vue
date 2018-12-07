<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>员工管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-form ref="form" :model="searchParams" label-width="60px">
                    <el-row :gutter="15">
                        <el-col :span="6">
                            <el-form-item label="登陆ID:">
                                <el-input v-model="searchParams.loginName" placeholder="登陆ID"></el-input>
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
                            <el-button type="primary" @click="toAssignAuth" :disabled="rowSelected">分配权限</el-button>
                            <el-button type="danger" @click="alertDelete" :disabled="rowSelected">删除</el-button>
                        </el-button-group>

                    </el-col>
                </el-row>
            </div>
            <div class="table-wrapper">
                <el-table :data="employeeDatas" stripe class="table" ref="employeeTable">
                    <el-table-column
                        label="选择"
                        width="50">
                        <template slot-scope="scope">
                            <el-radio v-model="currentEmployee" :label="scope.row"><i></i></el-radio>
                        </template>
                    </el-table-column>
                    <el-table-column prop="loginName" label="登陆ID" width="150px">
                    </el-table-column>
                    <el-table-column prop="empNo" label="员工编号" width="150px">
                    </el-table-column>
                    <el-table-column prop="empName" label="员工姓名"  width="150px">
                    </el-table-column>
                    <el-table-column prop="sex" label="性别" width="70px">
                        <template slot-scope="scope">
                            {{ scope.row.sex === '0' ? '男' : '女'}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="tel" label="电话" width="150px">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱" width="150px" >
                    </el-table-column>
                    <el-table-column prop="empStatus" label="是否有效" width="100px">
                        <template slot-scope="scope">
                            {{ scope.row.empStatus === '0' ? '无效' : '有效'}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="isFrozen" label="是否锁定" width="100px">
                        <template slot-scope="scope">
                            {{ scope.row.isFrozen === '0' ? '否' : '是'}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTime" :formatter="dateFormatter" label="创建时间" width="180px">
                    </el-table-column>
                    <el-table-column prop="updateTime" :formatter="dateFormatter" label="修改时间" width="auto">
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
                <h2>添加员工</h2>
            </div>
            <el-form ref="addEmployeeForm" :model="employeeForm" :rules="employeeValdateRules" label-width="80px">
                <el-form-item label="登陆ID" prop="loginName">
                    <el-input v-model="employeeForm.loginName"></el-input>
                </el-form-item>
                <el-form-item label="登陆密码" prop="password">
                    <el-input v-model="employeeForm.password" type="password"></el-input>
                </el-form-item>
                <el-form-item label="员工编号" prop="empNo">
                    <el-input v-model="employeeForm.empNo"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="empName">
                    <el-input v-model="employeeForm.empName"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="employeeForm.sex">
                        <el-radio label="0">男</el-radio>
                        <el-radio label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input v-model="employeeForm.tel"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="employeeForm.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelAddEmployee">取 消</el-button>
                <el-button type="primary" @click="saveEmployee('addEmployeeForm')">提 交</el-button>
            </span>
        </el-dialog>

        <!-- 编辑模拟弹出框 -->
        <el-dialog :visible.sync="editVisible" @closed="cancelEditEmployee" width="30%">
            <div slot="title" class="admin-dialog__title">
                <h2>修改员工信息</h2>
            </div>
            <el-form ref="editEmployeeForm" :model="employeeForm" :rules="employeeValdateRules" label-width="80px">
                <el-form-item label="登陆ID" prop="loginName">
                    <el-input v-model="employeeForm.loginName"></el-input>
                </el-form-item>
                <el-form-item label="员工编号" prop="empNo">
                    <el-input v-model="employeeForm.empNo"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="empName">
                    <el-input v-model="employeeForm.empName"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="employeeForm.sex">
                        <el-radio label="0">男</el-radio>
                        <el-radio label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input v-model="employeeForm.tel"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="employeeForm.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelEditEmployee">取 消</el-button>
                <el-button type="primary" @click="saveEmployee('addEmployeeForm')">提 交</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>

    import {employeeApi} from '@/service/api.js'
    import mockData from '@/mock/data'

    export default {
        name: 'employeeManage',
        data() {
            return {
                currentEmployee: null,
                employeeDatas: [],
                total: 0,
                multipleSelection: [],
                searchParams: {
                    pageNum: 1,
                    pageSize: 10
                },
                addVisible: false,
                editVisible: false,
                employeeForm: {
                },
                employeeValdateRules: {
                    loginName: [
                        { required: true, message: '请输入登陆ID', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, pattern: '([A-Za-z0-9_]{6,16})', message: '密码为6-16位的数字、字母或下划线组合', trigger: 'blur' }
                    ],
                    empNo: [
                        { required: true, message: '请输入员工编号', trigger: 'blur' }
                    ],
                    empName: [
                        { required: true, message: '请输入员工姓名', trigger: 'blur' }
                    ],
                    sex: [
                        { required: true, message: '请输入选择性别', trigger: 'change' }
                    ],
                    tel: [
                        { required: true, message: '请输入员工电话', trigger: 'blur' },
                        { pattern: '(^(\d{3,4}-)?\d{7,8})$|(1[0-9]{10})', message: '输入的电话格式错误', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入员工邮箱', trigger: 'blur' },
                        { type: 'email', message: '输入的邮箱格式错误', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.getData();
        },
        computed: {
            rowSelected() {
                return this.currentEmployee == null;
            }
        },
        methods: {
            dateFormatter(row, column, cellValue, index) {
                return this.$moment(new Date(cellValue)).format("YYYY-MM-DD HH:mm:ss")
            },
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
                this.employeeDatas = mockData.employeeArray;
                var that = this;

                // employeeApi.getEmployees(this.searchParams).then((res) => {
                //     this.employeeDatas = res.data.data
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
                this.employeeForm = {sex: '0'};
            },
            showEditDialog(index, row) {
                this.editVisible = true;
                this.employeeForm = Object.assign({}, this.currentEmployee);
            },
            cancelAddEmployee() {
                this.addVisible = false;
            },
            cancelEditEmployee() {
                this.editVisible = false;
            },
            toAssignAuth() {
                this.callNewPage(`/employeeManage/assignAuth/${this.currentEmployee.empId}`);
            },
            // 保存新增
            saveEmployee(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        employeeApi.saveEmployees(this.employeeForm).then((res) => {
                            if (res.data.success) {
                                this.$message.success("添加员工完成");
                                this.addVisible = false;
                                this.getData();
                            } else {
                                this.$message.error("新增失败");
                            }
                        })
                    }
                });
            },
            alertDelete() {
                this.$confirm('是否确认删除该员工信息?', '提示', {
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
