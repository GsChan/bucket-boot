import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/403',
            component: resolve => require(['../components/page/403.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['../components/page/404.vue'], resolve)
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: 'home' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '首页' }
                },
                {
                    path: '/employeeManage',
                    component: resolve => require(['../components/page/system/EmployeeManage.vue'], resolve),
                    meta: { title: '员工管理' }
                },
                {
                    path: '/authManage',
                    component: resolve => require(['../components/page/system/authorityManage.vue'], resolve),
                    meta: { title: '权限管理' }
                }
            ]
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
