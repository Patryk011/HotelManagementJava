<template>
  <div class="container">
    <h1 v-if="!showForm" class="text-center">Hotel List</h1>

    <table v-if="!showForm" class="table table-striped">
      <thead>
      <th>Hotel ID</th>
      <th>Name</th>
      <th>Address</th>
      </thead>
      <tbody>
      <tr v-for="hotel in hotels" :key="hotel.id">
        <td>{{ hotel.id }}</td>
        <td>{{ hotel.name }}</td>
        <td>{{ hotel.address }}</td>
      </tr>
      </tbody>
    </table>
    <button v-if="!showForm" class="btn btn-primary" @click="showForm = true">Add Hotel</button>

    <form v-if="showForm" @submit="addHotel" class="hotel-form">
      <h1 class="text-form">Hotel Form</h1>
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" v-model="newHotel.name" required>
      </div>
      <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" class="form-control" id="address" v-model="newHotel.address" required>
      </div>
      <button type="submit" class="btn btn-primary add-hotel-form">Add Hotel</button>
      <button type="button" class="btn btn-secondary cancel-hotel-form" @click="showForm = false">Cancel</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Hotels',
  data() {
    return {
      showForm: false,
      hotels: [],
      newHotel: {
        name: '',
        address: ''
      }
    };
  },
  methods: {
    async addHotel(event) {
      event.preventDefault();

      try {
        const response = await fetch('/api/hotel', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.newHotel)
        });

        if (response.ok) {
          const data = await response.json();
          this.hotels.push(data);
          // Clear the form fields after successful addition
          this.newHotel.name = '';
          this.newHotel.address = '';
        } else {
          console.error('Failed to add hotel:', response.statusText);
        }
      } catch (error) {
        console.error('Error during adding hotel:', error);
      }
    },

    async getHotels() {
      try {
        const response = await fetch('/api/hotel/all');
        const data = await response.json();
        this.hotels = data;
      } catch (error) {
        console.error('Error during fetch data:', error);
      }
    },
  },
  created() {
    this.getHotels();
  },
};
</script>

<style lang="scss" scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.text-center {
  text-align: center;
}

.table {
  width: 100%;
  border-collapse: collapse;

  th,
  td {
    padding: 10px;
    border: 1px solid #ccc;
  }

  th {
    background-color: #f0f0f0;
    font-weight: bold;
  }

  &-striped {
    tbody {
      tr:nth-child(odd) {
        background-color: #f9f9f9;
      }
    }
  }

  .hotel-form {
    margin-top: 20px;
    border: 3px solid blue;
    padding: 20px;
    border-radius: 4px;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 300px !important;
  }

  .form-group {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .form-group label {
    text-align: left;
    margin-right: 10px;
  }

  .form-control {
    width: 200px;
    padding: 8px;
    border: 1px solid black;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 10px;
  }

  .form {
    position: absolute;
    top: 276px;
    left: 600px;
  }

  .add-hotel-form {
    position: relative;
    top: 0px;
    left: -50px;
  }

  .cancel-hotel-form {
    position: relative;
    top: -38px;
    left: 70px;
  }
}
</style>