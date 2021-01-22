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
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/table',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/BaseTable.vue'),
                    meta: { title: '停车场状态' }
                },
                
                {
                    // vue-schart组件
                    path: '/parkbarcharts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/ParkBarCharts.vue'),
                    meta: { title: '停车场历史记录' }
                },
                {
                    // vue-schart组件
                    path: '/parklinecharts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/ParkLineCharts.vue'),
                    meta: { title: '停车场流量统计' }
                },
                {
                    // vue-schart组件
                    path: '/serverbarcharts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/ServerBarCharts.vue'),
                    meta: { title: '服务器历史记录' }
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                }
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
