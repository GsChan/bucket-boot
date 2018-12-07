import Vue from 'vue';
import Router from 'vue-router';
import {mixin} from '@/utils/utils';
import p403 from '@/components/page/403';
import p404 from '@/components/page/404';
import home from '@/components/common/Home';
import login from '@/components/page/Login';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: '首页',
            redirect: '/dashboard'
        },
        {
            path: '/login',
            name: '登陆',
            component: mixin(login)
        },
        {
            path: '/403',
            name: '权限限制',
            component: p403
        },
        {
            path: '/404',
            name: '404',
            component: p404
        },
        {
            path: '/',
            name: 'home',
            component: home,
            children:[
                {
                    path: '/dashboard',
                    name: '系统首页',
                    component: mixin(require('@/components/page/Dashboard.vue').default)
                },
                {
                    path: '/employeeManage',
                    name: '员工管理',
                    component: mixin(require('@/components/page/system/EmployeeManage.vue').default)
                },
                {
                    path: '/authManage',
                    name: '权限管理',
                    component: mixin(require('@/components/page/system/authorityManage.vue').default)
                },
                {
                    path: '/roleManage',
                    name: '角色管理',
                    component: mixin(require('@/components/page/system/roleManage.vue').default)
                }
            ]
        },
        {
            path: '/roleManage/assignAuth/:roleId',
            name: '分配权限',
            component: mixin(require('@/components/page/system/assignAuth.vue').default)
        },
        {
            path: '/employeeManage/assignAuth/:employeeId',
            name: '分配权限',
            component: mixin(require('@/components/page/system/assignEmployeeAuth.vue').default)
        },
        {
            path: '*',
            name: '*',
            redirect: '/404'
        }
    ]
})
