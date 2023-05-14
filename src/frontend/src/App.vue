<template>
  <div>
    <ul>
      <li v-for="customerItem in customer" :key="customerItem.id">
        {{ customerItem.firstName }} {{ customerItem.lastName }} - {{ customerItem.email }}
      </li>
    </ul>
  </div>
</template>

<script>
import {ref, onMounted} from "vue";


export default {
  setup() {
    const customer = ref("");

    onMounted(async () => {
      const response = await fetch("https://localhost:8080/customers");
      const data = await response.json();
      customer.value = data;
      console.log(data);
    });

    return {
      customer
    };
  }
};
</script>

<style>

</style>
