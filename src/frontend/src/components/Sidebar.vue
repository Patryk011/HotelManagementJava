<template>
<aside :class="`${is_expanded ? 'is-expanded' : ''}`">



<div class="menu-toggle-wrap">
  <button class="menu-toggle" @click="ToggleMenu">
    <img src="../assets/arrow.png" width="42" height="42" class="icon">
  </button>
</div>

<h3>Menu</h3>
<div class="menu">
  <router-link to="/" class="button">
    <img src="../assets/home.png" width="42" height="42" class="icon">
    <span class="text">Home</span>
  </router-link>
  <router-link to="/customers" class="button">
    <img src="../assets/customer.png" width="42" height="42" class="icon">
    <span class="text">Customers</span>
  </router-link>
  <router-link to="/rooms" class="button">
    <img src="../assets/rooms.png" width="42" height="42" class="icon">
    <span class="text">Rooms</span>
  </router-link>
  <router-link to="/hotel" class="button">
    <img src="../assets/hotel.png" width="42" height="42" class="icon">
    <span class="text">Hotel</span>
  </router-link>
  <router-link to="/send" class="button">
    <img src="../assets/send.png" width="42" height="42" class="icon">
    <span class="text">Send Email</span>
  </router-link>
  <router-link to="/email" class="button">
    <img src="../assets/email.png" width="42" height="42" class="icon">
    <span class="text">Email</span>
  </router-link>

</div>



<div class="flex"></div>


</aside>
</template>

<script setup>
import { ref } from 'vue'


const is_expanded = ref(localStorage.getItem("is_expanded") === "true")

const ToggleMenu = () => {
  is_expanded.value = !is_expanded.value
  localStorage.setItem("is_expanded", is_expanded.value)
}
</script>

<style lang="scss" scoped>
aside {
  display: flex;
  flex-direction: column;

  background-color: var(--dark);
  color: var(--light);

  width: calc(2rem + 32px);
  overflow: hidden;
  min-height: 100vh;
  padding: 1rem;

  transition: 0.2s ease-in-out;

  .flex {
    flex: 1 1 0%;
  }



  .menu-toggle-wrap {
    display: flex;
    justify-content: flex-end;
    margin-bottom: 1rem;

    position: relative;
    top: 0;
    transition: 0.2s ease-in-out;

    .menu-toggle {
      transition: 0.2s ease-in-out;
      .icon {
        font-size: 2rem;
        color: var(--light);
        transition: 0.2s ease-out;
      }

      &:hover {
        .icon {
          color: var(--primary);
          transform: translateX(0.5rem);
        }
      }
    }
  }

  h3, .button .text {
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
  }

  h3 {
    color: var(--grey);
    font-size: 0.875rem;
    margin-bottom: 0.5rem;
    text-transform: uppercase;
  }

  .menu {
    margin: 0 -1rem;

    .button {
      display: flex;
      align-items: center;
      text-decoration: none;

      transition: 0.2s ease-in-out;
      padding: 0.5rem 1rem;

      .icon {
        font-size: 2rem;
        color: var(--light);
        transition: 0.2s ease-in-out;
      }
      .text {
        color: var(--light);
        transition: 0.2s ease-in-out;
      }

      &:hover {
        background-color: var(--dark-alt);

        .icon, .text {
          color: var(--primary);
        }
      }

      &.router-link-exact-active {
        background-color: var(--dark-alt);
        border-right: 5px solid var(--primary);

        .icon, .text {
          color: var(--primary);
        }
      }
    }
  }

  .footer {
    opacity: 0;
    transition: opacity 0.3s ease-in-out;

    p {
      font-size: 0.875rem;
      color: var(--grey);
    }
  }

  &.is-expanded {
    width: var(--sidebar-width);



      .menu-toggle {
        transform: rotate(-180deg);
      }


    h3, .button .text {
      opacity: 1;
    }

    .button {
      .icon {
        margin-right: 1rem;
      }
    }


    .footer {
      opacity: 0;
    }
  }



  @media (max-width: 1024px) {
    position: absolute;
    z-index: 99;
  }
}

</style>