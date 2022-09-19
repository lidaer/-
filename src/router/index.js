import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Header from "@/components/Header";
import Package from "@/components/International";
import International from "@/components/International";
import Domestic from "@/components/Domestic";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/domestic',
    name: '',
    component: Domestic
  },
  {
    path: '/international',
    name: '',
    component: International
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
