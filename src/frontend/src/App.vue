<template>
    <div id="app">
        <h1>Main View</h1>
        <button @click="goToCustomers">Go to Customers</button>
        <button @click="goToRooms">Go to Rooms</button>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: 'App',
    methods: {
        goToCustomers() {
            this.$router.push('/customers');
        },
        goToRooms() {
            this.$router.push('/rooms');
        },
    },
};
</script>

<style>
/* Twoje style CSS tutaj */
</style>