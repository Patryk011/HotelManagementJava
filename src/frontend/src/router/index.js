import { createRouter, createWebHistory } from 'vue-router';
import Customer from "@/components/Customer.vue";
import Room from "@/components/Room.vue";
import Home from "@/components/Home.vue";
import Hotel from "@/components/Hotel.vue";
import Email from "@/components/Email.vue";
import EmailReceiver from "@/components/EmailReceiver.vue"
import Reservation from "@/components/Reservation.vue";
import Payment from "@/components/Payment.vue";
import Login from "@/components/Login.vue"



const routes = [
    { path: '/login', component: Login },
    { path: '/customers', component: Customer },
    { path: '/rooms', component: Room },
    { path: '/hotel', component: Hotel},
    { path: '/', component: Home },
    { path: '/send', component: Email},
    { path: '/email', component: EmailReceiver},
    { path: '/reservation', component: Reservation},
    { path: '/payment', component: Payment}
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


router.beforeEach((to, from, next) => {
    // lista stron, które nie wymagają logowania
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    // jeśli próbuje wejść na stronę wymagającą logowania i nie jest zalogowany, przekieruj na stronę logowania
    if (authRequired && !loggedIn) {
        return next('/login');
    }

    next();
});


export default router