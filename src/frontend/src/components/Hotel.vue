<template>
  <div class="container">
    <h1 v-if="!showForm && !showRoomsTable" class="text-center">Hotel List</h1>
    <h1 v-else-if="showRoomsTable" class="text-center">Rooms for {{ selectedHotel.name }} (Hotel-ID: {{ selectedHotel.id }})</h1>

    <table v-if="!showForm && !showRoomsTable" class="table table-striped">
      <thead>
      <th>Hotel ID</th>
      <th>Name</th>
      <th>Address</th>
      <th>Rooms</th>
      <th>Actions</th>
      </thead>
      <tbody>
      <tr v-for="hotel in hotels" :key="hotel.id">
        <td>{{ hotel.id }}</td>
        <td>{{ hotel.name }}</td>
        <td>{{ hotel.address }}</td>
        <td>
          <button class="btn btn-primary" @click="showRooms(hotel.id)">Show Rooms</button>
        </td>
        <td>
          <button class="btn btn-primary" @click="editHotel(hotel)">Edit</button>
          <button class="btn btn-danger" @click="deleteHotel(hotel.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>

    <table v-if="showRoomsTable" class="table table-striped">
      <thead>
      <th>Room ID</th>
      <th>Type</th>
      <th>Room Number</th>
      <th>Vacancy</th>
      <th>Price</th>
      </thead>
      <tbody>
      <tr v-for="room in rooms" :key="room.id">
        <td>{{ room.id }}</td>
        <td>{{ room.type }}</td>
        <td>{{ room.number }}</td>
        <td>{{ room.free ? "Yes" : "No" }}</td>
        <td>{{ room.price }}</td>
      </tr>
      </tbody>
    </table>
    <button v-if="showRoomsTable" class="btn btn-secondary" @click="showRoomsTable = false">Back</button>
    <button v-if="!showForm && !showRoomsTable" class="btn btn-primary" @click="showForm = true">Add Hotel</button>

    <form v-if="showForm && !isEditing" @submit="addHotel" class="hotel-form">
      <h1 class="text-form">Add Hotel</h1>
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" v-model="newHotel.name" required>
      </div>
      <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" class="form-control" id="address" v-model="newHotel.address" required>
      </div>
      <button type="submit" class="btn btn-primary">Save</button>
      <button type="button" class="btn btn-secondary" @click="cancelEdit">Cancel</button>
    </form>

    <form v-if="showForm && isEditing" @submit="saveHotel" class="hotel-form">
      <h1 class="text-form">Edit Hotel</h1>
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" v-model="editedHotel.name" required>
      </div>
      <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" class="form-control" id="address" v-model="editedHotel.address" required>
      </div>
      <button type="submit" class="btn btn-primary">Save</button>
      <button type="button" class="btn btn-secondary" @click="cancelEdit">Cancel</button>
    </form>
  </div>
</template>



<script>
export default {
  name: 'Hotels',
  data() {
    return {
      showForm: false,
      showRoomsTable: false,
      isEditing: false,
      hotels: [],
      rooms: [],
      selectedHotel: {},
      newHotel: {
        name: '',
        address: ''
      },
      editedHotel: {
        id: '',
        name: '',
        address: '',
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

          this.newHotel.name = '';
          this.newHotel.address = '';
        } else {
          console.error('Failed to add hotel:', response.statusText);
        }
      } catch (error) {
        console.error('Error during adding hotel:', error);
      }
    },

    async deleteHotel(hotelId) {
      try {
        const response = await fetch(`/api/hotel/${hotelId}`, {
          method: 'DELETE'
        });

        if (response.ok) {
          this.hotels = this.hotels.filter(hotel => hotel.id !== hotelId);
        } else {
          console.error('Failed to delete hotel:', response.statusText);
        }
      } catch (error) {
        console.error('Error during deleting hotel:', error);
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

    async getHotelRooms(hotelId) {
      try {
        const response = await fetch(`/api/hotel/${hotelId}/rooms`);
        const data = await response.json();
        this.rooms = data;
        this.showRoomsTable = true;
      } catch (error) {
        console.error('Error during fetch data:', error);
      }
    },

    editHotel(hotel) {
      this.isEditing = true;
      this.editedHotel.id = hotel.id;
      this.editedHotel.name = hotel.name;
      this.editedHotel.address = hotel.address;
      this.showForm = true;
    },

    async saveHotel(event) {
      event.preventDefault();

      try {
        const response = await fetch(`/api/hotel/${this.editedHotel.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.editedHotel)
        });

        if (response.ok) {
          const updatedHotel = await response.json();
          const index = this.hotels.findIndex(hotel => hotel.id === updatedHotel.id);
          if (index !== -1) {
            this.hotels.splice(index, 1, updatedHotel);
          }
          this.resetForm();
        } else {
          console.error('Failed to update hotel:', response.statusText);
        }
      } catch (error) {
        console.error('Error during updating hotel:', error);
      }
    },

    cancelEdit() {
      this.resetForm();
    },

    resetForm() {
      this.showForm = false;
      this.isEditing = false;
      this.newHotel.name = '';
      this.newHotel.address = '';
      this.editedHotel.id = '';
      this.editedHotel.name = '';
      this.editedHotel.address = '';
    },

    showRooms(hotelId) {
      const hotel = this.hotels.find(hotel => hotel.id === hotelId);
      if (hotel) {
        this.selectedHotel = hotel;
        this.getHotelRooms(hotelId);
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