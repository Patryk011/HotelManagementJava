<template>
    <div class="container">
        <h1 v-if="!showForm" class="text-center">Customer List</h1>

        <table v-if=!showForm class="table table-striped">
            <thead>
            <th>Customer ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            </thead>
            <tbody>
            <tr v-for="customer in customers" :key="customer.id">
                <td>{{ customer.id }}</td>
                <td>{{ customer.firstName }}</td>
                <td>{{ customer.lastName }}</td>
                <td>{{ customer.email }}</td>
            </tr>
            </tbody>
        </table>
      <button v-if="!showForm" class="btn btn-primary" @click="showForm = true">Add Customer</button>

      <form v-if="showForm" @submit="addCustomer" class="customer-form">
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
        <button type="button" class="btn btn-secondary cancel-customer-form" @click="showForm = false">Cancel</button>
      </form>
    </div>
</template>

<script>
export default {
    name: 'Customers',
    data() {
        return {
            showForm: false,
            customers: [],
            newCustomer: {
              firstName: '',
              lastName: '',
              email: ''
            }
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
            // Clear the form fields after successful addition
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

        async getCustomers() {
            try {
                const response = await fetch('/api/customers/all');
                const data = await response.json();
                this.customers = data;
            } catch (error) {
                console.error('Error during fetch data:', error);
            }
        },
    },
    created() {
        this.getCustomers();
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

.customer-form {
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

.add-customer-form {
  position: absolute;
  top: 276px;
  left: 600px;
}

.cancel-customer-form {
  position: absolute;
  top: 276px;
  left: 770px;
}


</style>