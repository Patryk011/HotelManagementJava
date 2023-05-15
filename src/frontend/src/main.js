import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import Customer from './components/Customer.vue';
import Room from './components/Room.vue';

const routes = [
    { path: '/customers', component: Customer },
    { path: '/rooms', component: Room },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);
app.use(router);
app.mount('#app');
