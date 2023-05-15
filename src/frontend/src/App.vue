<template>
    <div id="app" class="main">
        <h1>Hotel management</h1>

        <button class="button button-customer" @click="goToCustomers">Go to Customers</button>
        <button class="button button-room" @click="goToRooms">Go to Rooms</button>
        <button class="button button-back" @click="goBack">Back</button>

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
        goBack() {
            this.$router.push('/');
        },
    },
};
</script>

<style>
.main {
    margin: 0 auto;
    text-align: center;
    position: relative;
}
.button {
    position: absolute;
    left: 0;
}

.button-customer {
    top: 250px;
    left: 60px
}

.button-room {
    top: 300px;
    left: 60px
}

.button-back {
    top: 350px;
    left: 60px
}
</style>