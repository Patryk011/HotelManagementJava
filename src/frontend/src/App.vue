<template>
  <Customer/>
  <Rooms/>
</template>

<script>
import Customer from "@/components/Customer.vue";
import Rooms from "@/components/Room.vue";

export default {
    name: 'App',
    components: {
        Rooms,
        Customer
    }

}
</script>

<style>

</style>