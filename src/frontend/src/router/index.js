import { createRouter, createWebHistory } from 'vue-router';
import Customer from "@/components/Customer.vue";
import Room from "@/components/Room.vue";
import Home from "@/components/Home.vue";



const routes = [
    { path: '/customers', component: Customer },
    { path: '/rooms', component: Room },
    { path: '/', component: Home }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


export default router