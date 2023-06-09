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
          <button class="btn btn-danger cancel" @click="cancelReservation(reservation.id)">Cancel</button>
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
      <div class="button-container">
      <button type="submit" class="btn btn-primary add-reservation-form">Add Reservation</button>
      <button type="button" class="btn btn-secondary cancel-reservation-form" @click="showForm = false">Cancel</button>
      </div>
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

<style lang="scss" scoped>
.container {
  max-width: 1300px;
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

  .reservation-form {
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

      .add-reservation-form,
      .edit-reservation-form,
      .cancel-reservation-form {
        margin-top: 20px;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background 0.3s;
      }
    }
  }
  .cancel {
    margin-left: 10px;
  }
}
</style>
