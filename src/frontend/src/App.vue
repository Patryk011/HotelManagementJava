<template>
  <div id="app" class="app">
    <Sidebar v-if="loggedIn" />
    <router-view></router-view>
  </div>
</template>

<script>
import Sidebar from "@/components/Sidebar.vue";

export default {
  name: 'App',
  components: {Sidebar},
  data() {
    return {
      loggedIn: false, // Ustaw początkowo na false
    };
  },
  mounted() {
    // Sprawdź, czy użytkownik jest zalogowany (np. na podstawie tokena uwierzytelniającego przechowywanego w local storage)
    const user = localStorage.getItem('user');
    if (user) {
      this.loggedIn = true;
    }
  },
};
</script>

<style lang="scss">
:root {
  --primary: #4ade80;
  --primary-alt: #22c55e;
  --grey: #64748b;
  --dark: #1e293b;
  --dark-alt: #334155;
  --light: #f1f5f9;
  --sidebar-width: 300px;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Fira sans', sans-serif;
}

body {
  background: var(--light);
}

button {
  cursor: pointer;
  appearance: none;
  border: none;
  outline: none;
  background: none;
}

.app {
  display: flex;

  main {
    flex: 1 1 0;
    padding: 2rem;

    @media (max-width: 1024px) {
      padding-left: 6rem;
    }
  }
}
</style>