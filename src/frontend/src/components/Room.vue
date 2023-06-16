<template>
  <div class="container">
    <h1 v-if="!showForm && !editForm" class="text-center">Room List</h1>

    <table v-if="!showForm && !editForm && !showAvailableRooms" class="table table-striped">
      <thead>
      <th>Room ID</th>
      <th>Type</th>
      <th>Room Number</th>
      <th>Vacancy</th>
      <th>Price per night</th>
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
          <button class = "btn btn-primary " @click="showEditForm(room.id)">Edit</button>
          <button class = "btn btn-danger delete" @click="deleteRoom(room.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>

    <table v-if="showAvailableRooms && !editForm && !showForm" class="table table-striped">
      <thead>
      <tr>
        <th>Room ID</th>
        <th>Type</th>
        <th>Room Number</th>
        <th>Vacancy</th>
        <th>Price per night</th>
        <th>Hotel ID</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="room in availableRooms" :key="room.id">
        <td>{{ room.id }}</td>
        <td>{{ room.type }}</td>
        <td>{{ room.number }}</td>
        <td>{{ room.free == true ? "Yes" : "No" }}</td>
        <td>{{ room.price }}</td>
        <td>{{ room.hotelId }}</td>
        <td>
          <button class = "btn btn-primary " @click="showEditForm(room.id)">Edit</button>
          <button class = "btn btn-danger delete" @click="deleteRoom(room.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>

    <button v-if="!showForm && !editForm" class="btn btn-primary" @click="showForm = true">Add Room</button>
    <button v-if="!showForm && !editForm" class="btn btn-primary available" @click="toggleAvailableRooms">{{ showAvailableRooms ? 'Back' : 'Show Available Rooms' }}</button>


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
      <div class="button-container">
      <button type="submit" class="btn btn-primary add-room-form">Add Room</button>
      <button type="button" class="btn btn-secondary cancel-room-form" @click="showForm = false">Cancel</button>
      </div>
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
      <div class="edit-button-container">
      <button type="submit" class="btn btn-primary edit-room-form">Update Room</button>
      <button type="button" class="btn btn-secondary cancel-room-form" @click="editForm = false">Cancel</button>
        </div>
    </form>
    <div v-if="showError" class="alert alert-danger">
      {{ errorMessage }}

    </div>
  </div>
</template>

<script>
export default {
  name: 'Rooms',
  data() {
    return {
      showForm: false,
      editForm: false,
      showAvailableRooms: false,
      errorMessage: '',
      showError: false,
      errorTimeout: null,
      rooms: [],
      newRoom: {
        type: '',
        number: null,
        isFree: null,
        price: null,
        hotelId: null
      },
      availableRooms: [],
    };
  },
  methods: {

    clearError() {
      clearTimeout(this.errorTimeout);
      this.showError = false;
    },


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
          const errorData = await response.text();
          this.errorMessage = errorData;
          this.showError = true;



          this.errorTimeout = setTimeout(() => {
            this.clearError();
          }, 3500);
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
          const availableRoomIndex = this.availableRooms.findIndex(room => room.id === this.editRoom.id);
          if (availableRoomIndex !== -1) {
            this.availableRooms.splice(availableRoomIndex, 1, data);
          }

          this.editForm = false;
        } else {
          const errorData = await response.text();
          this.errorMessage = errorData;
          this.showError = true;



          this.errorTimeout = setTimeout(() => {
            this.clearError();
          }, 3500);
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

    async getAvailableRooms() {
      try {
        const response = await fetch('/api/room/available');
        const data = await response.json();
        this.availableRooms = data;
      } catch (error) {
        console.error('Error during fetching available rooms:', error);
      }
    },

    toggleAvailableRooms() {
      this.showAvailableRooms = !this.showAvailableRooms;
      if (this.showAvailableRooms) {
        this.getAvailableRooms();
      }
    },

    async deleteRoom(roomId) {
      try {
        const response = await fetch(`/api/room/${roomId}`, {
          method: 'DELETE'
        });
        if (response.ok) {
          const index = this.rooms.findIndex(room => room.id === roomId);
          if (index !== -1) {
            this.rooms.splice(index, 1);
          }
        } else {
          console.error('Failed to delete room:',  response.statusText);
        }
      } catch(error) {
        console.error('Error during delete room:', error);
      }
    }




  },
  created() {
    this.getRooms();
    this.getAvailableRooms();
  },
};
</script>
<style lang="scss" scoped>
.container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;

  .text-center {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
  }

  .table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;

    th, td {
      padding: 10px;
      border: 1px solid #ddd;
    }

    th {
      background-color: #f0f0f0;
      color: #333;
    }

    tbody tr:nth-child(odd) {
      background-color: #f9f9f9;
    }

    tbody tr:hover {
      background-color: #f2f2f2;
    }
  }

  .btn {
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s;

    &.btn-primary {
      background: linear-gradient(to right, #3498db, #2980b9);


      &.available {
        margin-left: 10px;
      }

      &:hover {
          box-shadow: 0px 5px 15px 0px rgba(0,0,0,0.1);
          transform: translateY(-3px);
        }

      }



    &.btn-danger {
      background: linear-gradient(45deg, #ed213a, #93291e);

      &:hover {
        box-shadow: 0px 5px 15px 0px rgba(0,0,0,0.1);
        transform: translateY(-3px);
      }

    }

    &.btn-secondary {
      background: #bbb;

      &:hover {
        background: #999;
      }
    }
  }

  .room-form {
    margin-top: 20px;
    padding: 20px;
    background: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: flex-start;

    .form-group {
      display: flex;
      flex-direction: column;
      margin-bottom: 15px;
      width: 100%;

      label {
        margin-bottom: 5px;
        font-size: 14px;
        color: #333;
      }

      .form-control {
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        width: 100%;
        transition: border-color 0.2s, box-shadow 0.2s;

        &:focus {
          border-color: #888;
          box-shadow: 0 0 5px rgba(136, 136, 136, 0.5);
        }
      }
    }

    .button-container {

      display: flex;
      justify-content: space-between;
      width: 25%;

      .add-room-form,
      .edit-room-form,
      .cancel-room-form {
        margin-top: 10px;

        button {
          margin-right: 10px;
        }
      }
    }

    .edit-button-container {
      display: flex;
      justify-content: space-between;
      width: 30%;
      margin-top: 40px;
    }
  }

  .delete {
    margin-left: 10px;
  }
}

.alert-danger {
  margin-top: 10px;

}

</style>