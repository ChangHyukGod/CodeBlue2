import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  {

    path: "/",
    component: HomeView,
  },
  {
    path: "/faq",
    component: () => import("../views/FaqMain.vue"),
  },
  {
    path: '/register',
    component: () => import('../views/auth/RegisterView.vue')
  },
  {
    path: '/login',
    component: () => import('../views/auth/LoginView.vue')
  },
  {
    path: '/findEmail',
    component: () => import('../views/auth/FindEmail.vue')
  },
  {
    path: '/findPassword',
    component: () => import('../views/auth/FindPassword.vue')
  },
  {
    path: '/review',
    component: () => import('../views/review/ReviewMain.vue')
  },
  {
    path: '/add-review',
    component: () => import('../views/review/AddReview.vue')
  }
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
