<template>
  <div class="container">
    <h1 v-if="!showForm && !editForm" class="text-center">Reservation List</h1>

    <table v-if="!showForm && !editForm" class="table table-striped">
      <thead>
      <tr>
        <th>ID</th>
        <th>Customer Email</th>
        <th>Room ID</th>
        <th>Hotel ID</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="reservation in reservations" :key="reservation.id">
        <td>{{ reservation.id }}</td>
        <td>{{ reservation.customerEmail }}</td>
        <td>{{ reservation.roomId }}</td>
        <td>{{ reservation.hotelId }}</td>
        <td>{{ formatDate(reservation.startDate) }}</td>
        <td>{{ formatDate(reservation.endDate) }}</td>
        <td>{{ reservation.status }}</td>
        <td>
          <button class="btn btn-primary" @click="showEditForm(reservation.id)">Edit</button>
          <button class="btn btn-danger delete" @click="cancelReservation(reservation.id)">Cancel</button>
        </td>
      </tr>
      </tbody>
    </table>
    <button v-if="!showForm && !editForm" class="btn btn-primary" @click="showForm = true">Create Reservation</button>

    <form v-if="showForm" @submit="createReservation" class="reservation-form">
      <h1 class="text-form">Reservation Form</h1>
      <div class="form-group">
        <label for="customerEmail">Customer Email:</label>
        <input type="text" class="form-control" id="customerEmail" v-model="newReservation.customerEmail" required>
      </div>
      <div class="form-group">
        <label for="roomId">Room ID:</label>
        <input type="number" class="form-control" id="roomId" v-model="newReservation.roomId" required>
      </div>
      <div class="form-group">
        <label for="hotelId">Hotel ID:</label>
        <input type="number" class="form-control" id="hotelId" v-model="newReservation.hotelId" required>
      </div>
      <div class="form-group">
        <label for="startDate">Start Date:</label>
        <input type="date" class="form-control" id="startDate" v-model="newReservation.startDate" required>
      </div>
      <div class="form-group">
        <label for="endDate">End Date:</label>
        <input type="date" class="form-control" id="endDate" v-model="newReservation.endDate" required>
      </div>
      <div class="form-group">
        <label for="status">Status:</label>
        <input type="text" class="form-control" id="status" v-model="newReservation.status" required>
      </div>
      <button type="submit" class="btn btn-primary add-reservation-form">Add Reservation</button>
      <button type="button" class="btn btn-secondary cancel-reservation-form" @click="showForm = false">Cancel</button>
    </form>

    <form v-if="editForm" @submit="updateReservation" class="reservation-form">
      <h1 class="text-form">Edit Reservation</h1>
      <div class="form-group">
        <label for="customerEmail">Customer Email:</label>
        <input type="text" class="form-control" id="customerEmail" v-model="editReservation.customerEmail">
      </div>
      <div class="form-group">
        <label for="roomId">Room ID:</label>
        <input type="number" class="form-control" id="roomId" v-model="editReservation.roomId">
      </div>
      <div class="form-group">
        <label for="hotelId">Hotel ID:</label>
        <input type="number" class="form-control" id="hotelId" v-model="editReservation.hotelId">
      </div>
      <div class="form-group">
        <label for="startDate">Start Date:</label>
        <input type="date" class="form-control" id="startDate" v-model="editReservation.startDate">
      </div>
      <div class="form-group">
        <label for="endDate">End Date:</label>
        <input type="date" class="form-control" id="endDate" v-model="editReservation.endDate">
      </div>
      <div class="form-group">
        <label for="status">Status:</label>
        <input type="text" class="form-control" id="status" v-model="editReservation.status">
      </div>
      <button type="submit" class="btn btn-primary edit-reservation-form">Update Reservation</button>
      <button type="button" class="btn btn-secondary cancel-reservation-form" @click="editForm = false">Cancel</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Reservations',
  data() {
    return {
      showForm: false,
      editForm: false,
      reservations: [],
      newReservation: {
        customerEmail: '',
        roomId: null,
        hotelId: null,
        startDate: null,
        endDate: null,
        status: ''
      },
      editReservation: {
        id: null,
        customerEmail: '',
        roomId: null,
        hotelId: null,
        startDate: null,
        endDate: null,
        status: ''
      }
    };
  },
  methods: {
    async createReservation(e) {
      e.preventDefault();

      try {
        const response = await fetch('/api/reservation', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.newReservation)
        });

        if (response.ok) {
          const data = await response.json();
          this.reservations.push(data);
          this.newReservation.customerEmail = '';
          this.newReservation.roomId = null;
          this.newReservation.hotelId = null;
          this.newReservation.startDate = null;
          this.newReservation.endDate = null;
          this.newReservation.status = '';
        } else {
          console.error('Failed to add reservation:', response.statusText);
        }
      } catch (error) {
        console.error('Error during adding reservation:', error);
      }
    },

    async updateReservation(e) {
      e.preventDefault();

      try {
        const response = await fetch(`/api/reservation/${this.editReservation.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.editReservation)
        });

        if (response.ok) {
          const data = await response.json();
          const index = this.reservations.findIndex(reservation => reservation.id === this.editReservation.id);
          if (index !== -1) {
            this.reservations.splice(index, 1, data);
          }

          this.editForm = false;
        } else {
          console.error('Failed to update reservation:', response.statusText);
        }
      } catch (error) {
        console.error('Error during updating reservation:', error);
      }
    },

    async showEditForm(reservationId) {
      try {
        const response = await fetch(`/api/reservation/${reservationId}`);
        const data = await response.json();

        this.editReservation = {
          id: data.id,
          customerEmail: data.customerEmail,
          roomId: data.roomId,
          hotelId: data.hotelId,
          startDate: data.startDate,
          endDate: data.endDate,
          status: data.status
        };
        this.editForm = true;
      } catch (error) {
        console.error('Error during showing edit form:', error);
      }
    },

    async cancelReservation(reservationId) {
      try {
        const response = await fetch(`/api/reservation/${reservationId}`, {
          method: 'DELETE'
        });
        if (response.ok) {
          const index = this.reservations.findIndex(reservation => reservation.id === reservationId);
          if (index !== -1) {
            this.reservations.splice(index, 1);
          }
        } else {
          console.error('Failed to cancel reservation:', response.statusText);
        }
      } catch (error) {
        console.error('Error during canceling reservation:', error);
      }
    },

    async getAllReservations() {
      try {
        const response = await fetch('/api/reservation/all');
        const data = await response.json();
        this.reservations = data;
      } catch (error) {
        console.error('Error during fetching reservations:', error);
      }
    },

    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString();
    }
  },
  created() {
    this.getAllReservations();
  }
};
</script>

<style scoped>
.container {
  max-width: 1000px;
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

.reservation-form {
  margin-top: 20px;
  padding: 20px;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 350px !important;
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

.add-reservation-form {
  position: relative;
  top: 0px;
  left: -50px;
}

.cancel-reservation-form {
  position: relative;
  top: -38px;
  left: 70px;
}

.delete {
  margin-left: 10px;
}
</style>
