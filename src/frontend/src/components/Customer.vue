<template>
  <div class="container">
    <h1 v-if="!showForm && !reservationTable" class="text-center">Customer List</h1>
    <h1 v-else-if="reservationTable" class="text-center">Reservations {{ selectedCustomer.firstName }} {{selectedCustomer.lastName}}, ID: {{selectedCustomer.id}} </h1>
    <div v-if="!showForm && !reservationTable" class="form-group">
      <label for="filterEmail">Find by Email:</label>
      <input type="email" class="form-control" id="filterEmail" v-model="filterEmail" @input="getCustomers" placeholder="Enter email">
    </div>

    <table v-if="!showForm && !reservationTable" class="table table-striped">
      <thead>
      <th>Customer ID</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
      <th>Reservations</th>
      <th>Actions</th>
      </thead>
      <tbody>
      <tr v-for="customer in customers" :key="customer.id">
        <td>{{ customer.id }}</td>
        <td>{{ customer.firstName }}</td>
        <td>{{ customer.lastName }}</td>
        <td>{{ customer.email }}</td>
        <td>
          <button class="btn btn-primary" @click="showReservations(customer.id)">Reservations</button>
        </td>
        <td>
          <button class="btn btn-primary" @click="editCustomer(customer)">Edit</button>
          <button class="btn btn-danger" @click="deleteCustomer(customer.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>

    <button v-if="!showForm && !reservationTable" class="btn btn-primary" @click="showForm = true">Add Customer</button>

    <form v-if="showForm && !isEditing" @submit="addCustomer" class="customer-form">
      <h1 class="text-form">Customer form</h1>
      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" id="firstName" v-model="newCustomer.firstName" required>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" id="lastName" v-model="newCustomer.lastName" required>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" v-model="newCustomer.email" required>
      </div>
      <button type="submit" class="btn btn-primary add-customer-form">Add Customer</button>
      <button type="button" class="btn btn-secondary cancel-customer-form" @click="cancelEdit">Cancel</button>
    </form>

    <form v-if="showForm && isEditing" @submit="saveCustomer" class="customer-form">
      <h1 class="text-form">Edit Customer</h1>
      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" id="firstName" v-model="editedCustomer.firstName" required>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" id="lastName" v-model="editedCustomer.lastName" required>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" v-model="editedCustomer.email" required>
      </div>
      <button type="submit" class="btn btn-primary">Save</button>
      <button type="button" class="btn btn-secondary" @click="cancelEdit">Cancel</button>
    </form>

    <table v-if="reservationTable" class="table table-striped">
      <thead>
      <tr>
        <th>ID</th>
        <th>Customer Email</th>
        <th>Room ID</th>
        <th>Hotel ID</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
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
      </tr>
      </tbody>
    </table>
    <button v-if="reservationTable" class="btn btn-secondary" @click="reservationTable = false;">Back</button>
  </div>
</template>

<script>
export default {
  name: 'Customers',
  data() {
    return {
      showForm: false,
      reservationTable: false,
      isEditing: false,
      customers: [],
      selectedCustomer: {},
      reservations: [],
      newCustomer: {
        firstName: '',
        lastName: '',
        email: ''
      },
      editedCustomer: {
        id: '',
        firstName: '',
        lastName: '',
        email: ''
      },
      filterEmail: '',
    };
  },
  methods: {
    async addCustomer(event) {
      event.preventDefault();

      try {
        const response = await fetch('/api/customers', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.newCustomer)
        });

        if (response.ok) {
          const data = await response.json();
          this.customers.push(data);

          this.newCustomer.firstName = '';
          this.newCustomer.lastName = '';
          this.newCustomer.email = '';
        } else {
          console.error('Failed to add customer:', response.statusText);
        }
      } catch (error) {
        console.error('Error during adding customer:', error);
      }
    },

    async deleteCustomer(customerId) {
      try {
        const response = await fetch(`/api/customers/${customerId}`, {
          method: 'DELETE'
        });

        if (response.ok) {
          this.customers = this.customers.filter(customer => customer.id !== customerId);
        } else {
          console.error('Failed to delete customer:', response.statusText);
        }
      } catch (error) {
        console.error('Error during deleting customer:', error);
      }
    },

    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString();
    },

    async getCustomers() {
      try {
        let url = 'api/customers/all';
        if (this.filterEmail) {
          url = 'api/customers/filter?email=' + encodeURIComponent(this.filterEmail);
        }
        const response = await fetch(url);
        const data = await response.json();
        this.customers = data;
      } catch (error) {
        console.error('Error during fetching data:', error);
      }
    },

    async getCustomerReservations(customerId) {
      try {
        const response = await fetch(`/api/customers/${customerId}/reservations`);
        const data = await response.json();
        this.reservations = data;
        this.reservationTable = true;
      } catch (error) {
        console.error('Error during fetching data:', error);
      }
    },

    showReservations(customerId) {
      const customer = this.customers.find(customer => customer.id === customerId);
      if (customer) {
        this.selectedCustomer = customer;
        this.getCustomerReservations(customerId);
      }
    },

    editCustomer(customer) {
      this.isEditing = true;
      this.editedCustomer.id = customer.id;
      this.editedCustomer.firstName = customer.firstName;
      this.editedCustomer.lastName = customer.lastName;
      this.editedCustomer.email = customer.email;
      this.showForm = true;
    },

    async saveCustomer(event) {
      event.preventDefault();

      try {
        const response = await fetch(`/api/customers/${this.editedCustomer.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.editedCustomer)
        });

        if (response.ok) {
          const updatedCustomer = await response.json();
          const index = this.customers.findIndex(customer => customer.id === updatedCustomer.id);
          if (index !== -1) {
            this.customers.splice(index, 1, updatedCustomer);
          }
          this.resetForm();
        } else {
          console.error('Failed to update customer:', response.statusText);
        }
      } catch (error) {
        console.error('Error during updating customer:', error);
      }
    },

    cancelEdit() {
      this.resetForm();
    },

    resetForm() {
      this.showForm = false;
      this.isEditing = false;
      this.newCustomer.firstName = '';
      this.newCustomer.lastName = '';
      this.newCustomer.email = '';
      this.editedCustomer.id = '';
      this.editedCustomer.firstName = '';
      this.editedCustomer.lastName = '';
      this.editedCustomer.email = '';
    },
  },
  created() {
    this.getCustomers();
  },
};
</script>

<style lang="scss" scoped>
.container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
}

.text-center {
  text-align: center;
}

.table {
  margin-top: 50px;
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

  tbody tr:nth-child(odd) {
    background-color: #f9f9f9;
  }
}

.customer-form {
  margin-top: 20px;
  padding: 20px;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 300px !important;
}

.form-group {
  margin-top: 50px;
  display: flex;
  flex-direction: row;
  align-items: center;

  label {
    text-align: left;
    margin-right: 10px;
  }

  .form-control {
    width: 300px;
    padding: 8px;
    border: 1px solid black;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 10px;
  }
}

.add-customer-form {
  position: relative;
  top: 0px;
  left: -50px;
}

.cancel-customer-form {
  position: relative;
  top: -38px;
  left: 70px;
}
</style>
