import { createRouter, createWebHistory } from 'vue-router';
import Customer from "@/components/Customer.vue";
import Room from "@/components/Room.vue";
import Home from "@/components/Home.vue";
import Hotel from "@/components/Hotel.vue";
import Email from "@/components/Email.vue";



const routes = [
    { path: '/customers', component: Customer },
    { path: '/rooms', component: Room },
    { path: '/hotel', component: Hotel},
    { path: '/', component: Home },
    { path: '/email', component: Email}
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


export default router