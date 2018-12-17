<template>
    <div class="assign-container">
        <div class="admin-dialog__title">
            <h2>分配角色</h2>
        </div>
        <div class="roles-transfer-container">
            <el-transfer
                v-model="employeeRoleArray"
                :data="transferRoles"
                :titles="['未拥有', '已拥有']"
                :button-texts="['移除', '分配']"></el-transfer>
        </div>
        <div class="admin-dialog__title">
            <h2>分配权限</h2>
        </div>
        <div class="tree-container">
            <el-row class="switch-row">
                <span>超级管理员</span>
                <el-switch
                    v-model="isSuperAdmin"
                    active-color="#13ce66"
                    inactive-color="#ccc">
                </el-switch>
            </el-row>
            <el-row>
                <el-col :span="2">
                    <div class="tree-tags-symbol">
                        <div>
                            <el-tag type="success"></el-tag>
                            <span>目录</span>
                        </div>
                        <div>
                            <el-tag type="warning"></el-tag>
                            <span>菜单</span>
                        </div>
                        <div>
                            <el-tag></el-tag>
                            <span>按钮</span>
                        </div>
                        <div>
                            <el-tag type="danger"></el-tag>
                            <span>禁用</span>
                        </div>
                    </div>
                </el-col>
                <el-col :span="22">
                    <el-tree :data="authorityDatas"
                        :props="defaultProps"
                        ref="authTree"
                        show-checkbox
                        :default-expanded-keys="employeeAuthArray"
                        :default-checked-keys="employeeAuthArray"
                        node-key="authcode">
                        <div class="custom-tree-node" slot-scope="{ node, data }">
                            <span v-if="data.authStatus === '0'" class="custom-tree-node__disable-label">{{ node.label }}</span>
                            <span v-else-if="data.authType === '0'" class="custom-tree-node__dir-label">{{ node.label }}</span>
                            <span v-else-if="data.authType === '1'" class="custom-tree-node__menu-label">{{ node.label }}</span>
                            <span v-else class="custom-tree-node__button-label">{{ node.label }}</span>
                        </div>
                    </el-tree>
                </el-col>
            </el-row>
        </div>

        <div class="button-footer">
            <el-button type="primary" @click="saveAuth">保 存</el-button>
            <el-button type="primary" @click="cancelAssign">取 消</el-button>
        </div>
    </div>
</template>

<script>

    import {authApi} from '@/service/api'
    import mockData from '@/mock/data.js'

    export default {
        name: 'assignEmployeeAuthManage',
        data() {
            return {
                defaultProps: {
                    label: "authName",
                    children: "children"
                },
                isSuperAdmin: false,
                employeeId: null,
                roleArray: [],
                employeeRoleArray: [],
                employeeAuthArray: [],
                authorityDatas: []
            }
        },
        created() {
            this.employeeId = this.$route.params.employeeId;
            this.roleArray = mockData.roleArray;
            this.authorityDatas = mockData.authArray;
            this.getData();
            this.employeeRoleArray = [1];
            this.employeeAuthArray = ["100100100", "100100200"];
        },
        computed: {
            transferRoles() {
                let array = [];
                this.roleArray.forEach(role => {
                    array.push({
                        label: role.roleName,
                        key: role.roleId,
                        disabled: role.roleStatus === '0'
                    });
                });
                return array;
            }
        },
        methods: {
            // 获取 列表
            getData() {
                // var that = this;
                // getRequest("/employee/get/list", this.searchParams).then((res) => {
                //     this.authorityDatas = res.data.data
                //     this.total = res.data.total
                // })
                // .catch(function (error) {
                //     that.$message.error('获取员工列表失败')
                // })
            },
            saveAuth() {
                var keys = this.$refs.authTree.getCheckedKeys();
                console.log(keys)
                console.log(this.$refs.authTree.getHalfCheckedKeys())
            },
            cancelAssign() {
                this.pageOver();
            }
        }
    }

</script>

<style scoped>
.assign-container {
    margin: 20px;
}
.admin-dialog__title {
    margin-top: 30px;
}
.roles-transfer-container, .tree-container, .button-footer {
    margin-left: 100px;
}
.switch-row {
    margin-top: 20px;
    margin-bottom: 20px;
    color: #606266;
    font-weight: bold;
}
.custom-tree-node__dir-label {
    color: #67c23a;
}
.custom-tree-node__menu-label {
    color: #e6a23c;
}
.custom-tree-node__button-label {
    color: #409EFF;
}
.custom-tree-node__disable-label {
    color: #f56c6c;
}
.tree-tags-symbol div {
    vertical-align: middle;
    color: rgb(175, 175, 175);
    font-size: 16px;
}
.tree-tags-symbol .el-tag {
    display: inline;
    padding: 0 15px;
    margin-right: 10px;
}
.button-footer {
    margin-top: 20px;
}
</style>
