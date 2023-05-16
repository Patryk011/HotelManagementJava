import { createApp } from 'vue';

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
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
