import Vue from 'vue';
import Router from 'vue-router';
import HomeView from '../views/HomeView.vue';
import ProjectManagement from '../views/ProjectManagement.vue';
import CustomerPage from '../views/CustomerPage.vue';
import ManagementPage from '../views/ManagementPage.vue'; // 관리 페이지 컴포넌트 임포트

Vue.use(Router);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/staff',
    name: 'ProjectManagement',
    component: ProjectManagement,
  },
  {
    path: '/customer',
    name: 'CustomerPage',
    component: CustomerPage,
  },
  {
    path: '/management',
    name: 'ManagementPage',
    component: ManagementPage,
  },
];

const router = new Router({
  mode: 'history',
  routes,
});

export default router;