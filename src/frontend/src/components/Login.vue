<template>
  <div class="login-container">
  <div class="login">
    <h2>Login</h2>
    <input type="text" v-model="username" placeholder="Username">
    <input type="password" v-model="password" placeholder="Password">
    <button @click="login">Login</button>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    login() {
      fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: this.username,
          password: this.password,
        }),
      })
          .then((response) => {
            if (response.ok) {
              return response.text();
            } else {
              throw new Error('Error during login');
            }
          })
          .then((username) => {
            sessionStorage.setItem('username', username);
            this.$router.push('/'); // przekieruj do strony głównej
          })
          .catch((error) => {
            console.error("Error:", error);
          });
    },
  },
}
</script>

<style scoped>
.login-container {
 margin: 0 auto;
}

.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;

}

input {
  margin: 10px 0;
  padding: 10px;
  width: 200px;
}

button {
  padding: 10px 20px;
  border: none;
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>
