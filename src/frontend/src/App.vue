<template>
  <div id="app" class="main">
    <div class="navbar">
      <router-link to="/">Home</router-link>
      <router-link to="/customers">Customers</router-link>
      <router-link to="/rooms">Rooms</router-link>
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style lang="scss">
.main {
  margin: 0 auto;
  text-align: center;
  position: relative;

  .navbar {
    position: fixed;
    left: 0;
    top: 0;
    height: 100vh;
    width: 100px;
    background-color: #f0f0f0;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 20px;

    a {
      margin-bottom: 10px;
      color: #000;
      text-decoration: none;

      &:hover {
        color: #333;
      }
    }
  }
}
</style>