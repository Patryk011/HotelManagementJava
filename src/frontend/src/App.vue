<template>
  {{customer}}
</template>

<script>
import {ref, onMounted} from "vue";


let customer = ref("")

onMounted(async () => {
  const response = await fetch("/api/customers");
  customer.value = await response.text();
})
</script>

<style>

</style>
