<template>
  <div class="container">
    <h1 v-if="!showForm && !editForm" class="text-center">Room List</h1>

    <table v-if="!showForm && !editForm" class="table table-striped">
      <thead>
      <th>Room ID</th>
      <th>Type</th>
      <th>Room Number</th>
      <th>Vacancy</th>
      <th>Price</th>
      <th>Hotel ID</th>
      <th>Actions</th>
      </thead>
      <tbody>
      <tr v-for="room in rooms" :key="room.id">
        <td>{{ room.id }}</td>
        <td>{{ room.type }}</td>
        <td>{{ room.number }}</td>
        <td>{{ room.free == true ? "Yes" : "No" }}</td>
        <td>{{ room.price }}</td>
        <td>{{ room.hotelId }}</td>
        <td>
          <button class = "btn btn-primary" @click="showEditForm(room.id)">Edit</button></td>
      </tr>
      </tbody>
    </table>
    <button v-if="!showForm && !editForm" class="btn btn-primary" @click="showForm = true">Add Room</button>

    <form v-if="showForm" @submit="addRoom" class="room-form">
      <h1 class="text-form">Room Form</h1>
      <div class="form-group">
        <label for="type">Type:</label>
        <input type="text" class="form-control" id="type" v-model="newRoom.type" required>
      </div>
      <div class="form-group">
        <label for="number">Room Number:</label>
        <input type="number" class="form-control" id="number" v-model="newRoom.number" required>
      </div>

      <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" class="form-control" id="price" v-model="newRoom.price" required>
      </div>
      <div class="form-group">
        <label for="hotelId">Hotel ID:</label>
        <input type="number" class="form-control" id="hotelId" v-model="newRoom.hotelId" required>
      </div>
      <button type="submit" class="btn btn-primary add-room-form">Add Room</button>
      <button type="button" class="btn btn-secondary cancel-room-form" @click="showForm = false">Cancel</button>
    </form>

    <form v-if="editForm" @submit="updateRoom" class="room-form">
      <h1 class="text-form">Edit Room</h1>
      <div class="form-group">
        <label for="type">Type:</label>
        <input type="text" class="form-control" id="type" v-model="editRoom.type" >
      </div>
      <div class="form-group">
        <label for="number">Room Number:</label>
        <input type="number" class="form-control" id="number" v-model="editRoom.number" >
      </div>
      <div class="form-group">
        <label for="vacancy">Vacancy:</label>
        <select class="form-control" id="vacancy" v-model="editRoom.free">
          <option :value="true">Yes</option>
          <option :value="false">No</option>
        </select>
      </div>

      <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" class="form-control" id="price" v-model="editRoom.price" >
      </div>
      <div class="form-group">
        <label for="hotelId">Hotel ID:</label>
        <input type="number" class="form-control" id="hotelId" v-model="editRoom.hotelId" >
      </div>
      <button type="submit" class="btn btn-primary edit-room-form">Update Room</button>
      <button type="button" class="btn btn-secondary cancel-room-form" @click="editForm = false">Cancel</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Rooms',
  data() {
    return {
      showForm: false,
      editForm: false,

      rooms: [],
      newRoom: {
        type: '',
        number: null,
        isFree: null,
        price: null,
        hotelId: null
      },
    };
  },
  methods: {
    async addRoom(e) {
      e.preventDefault();

      try {
        const response = await fetch('/api/room', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.newRoom)
        });

        if (response.ok) {
          const data = await response.json();
          this.rooms.push(data);
          this.newRoom.type = '';
          this.newRoom.number = null;
          this.newRoom.isFree = null;
          this.newRoom.price = null;
          this.newRoom.hotelId = null;
        } else {
          console.error('Failed to add room:', response.statusText);
        }
      } catch (error) {
        console.error('Error during adding room:', error);
      }
    },

    async updateRoom(e) {
      e.preventDefault();

      try {
        const response = await fetch(`/api/room/${this.editRoom.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.editRoom)
        });

        if (response.ok) {
          const data = await response.json();
          const index = this.rooms.findIndex(room => room.id === this.editRoom.id);
          if (index !== -1) {
            this.rooms.splice(index, 1, data);
          }

          this.editForm = false;
        } else {
          console.error('Failed to update room:', response.statusText);
        }
      } catch (error) {
        console.error('Error during updating room:', error);
      }
    },

    async showEditForm(roomId) {
      try {
        const response = await fetch(`/api/room/${roomId}`);
        const data = await response.json();

        this.editRoom = {
          id: data.id,
          type: data.type,
          number: data.number,
          isFree: data.isFree,
          price: data.price,
          hotelId: data.hotelId
        };
        this.editForm = true;
      } catch (error) {
        console.error('Error during showing edit form:', error);
      }
    },

    async getRooms() {
      try {
        const response = await fetch('/api/room/all');
        const data = await response.json();
        this.rooms = data;
      } catch (error) {
        console.error('Error during fetch data:', error);
      }
    },

    async editRoom(roomId) {
      try {
        const response = await fetch(`/api/room/${roomId}`);
        const data = await response.json();

        this.newRoom = {
          type: data.type,
          number: data.number,
          isFree: data.isFree,
          price: data.price,
          hotelId: data.hotelId
        };
        this.showForm = true;
      } catch (error) {
        console.error('Error during editing room:', error);
      }
      }


  },
  created() {
    this.getRooms();
  },
};
</script>
<style scoped>
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
}

.table th,
.table td {
  padding: 10px;
  border: 1px solid #ccc;
}

.table th {
  background-color: #f0f0f0;
  font-weight: bold;
}

.table-striped tbody tr:nth-child(odd) {
  background-color: #f9f9f9;
}

.room-form {
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

.add-room-form {
  position: relative;
  top: 0px;
  left: -50px;
}

.cancel-room-form {
  position: relative;
  top: -38px;
  left: 70px;
}
</style>