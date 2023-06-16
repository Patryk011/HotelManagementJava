<template>
  <div class="login-container">
  <div class="login">
    <h2>Login</h2>
    <input type="text" v-model="username" placeholder="Username">
    <input type="password" v-model="password" placeholder="Password">
    <button @click="login">Login</button>
  </div>
    <div v-if="showError" class="alert alert-danger">
      {{ errorMessage }}
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showError: false,
      errorMessage: '',
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
              return response.json();
            } else {
              throw new Error('Error during login');
            }
          })
          .then((userData) => {
            sessionStorage.setItem('username', userData.username);
            sessionStorage.setItem('role', userData.role);
            this.$router.push('/');
          })
          .catch((error) => {
            this.showError = true;
            this.errorMessage = "Incorrect username or password.";
            console.error("Error:", error);
          });
    },
  },
}
</script>



<style scoped>
.login-container {

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0 auto;
}

.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 350px;
  padding: 30px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  margin-bottom: 30px;
}

input {
  margin-bottom: 15px;
  padding: 10px;
  width: 100%;
  border: none;
  border-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

button {
  margin-top: 15px;
  padding: 10px 20px;
  border: none;
  background-color: #007bff;
  color: #ffffff;
  cursor: pointer;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

button:hover {
  background-color: #0056b3;
}

.alert-danger {
  margin-top: 10px;
  text-align: center;
}
</style>