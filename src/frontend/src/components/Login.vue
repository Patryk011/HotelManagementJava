<template>
  <div>
    <form @submit.prevent="onSubmit">
      <input v-model="form.username" type="text" placeholder="Username" required>
      <input v-model="form.password" type="password" placeholder="Password" required>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    onSubmit() {
      fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.form),
      })
          .then((response) => {
            if (response.ok) {
              this.$router.push('/'); // przekieruj do strony głównej
            } else {
              console.log("Error during login");
            }
          }).catch((error) => {
        console.error("Error:", error);
      });
    },
  },
}
</script>

<style>



</style>