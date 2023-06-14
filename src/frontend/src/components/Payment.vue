<template>
  <div class="container">
    <h1 class="text-center">Payment List</h1>

    <table class="table table-striped">
      <thead>
      <tr>
        <th>ID</th>
        <th>Customer ID</th>
        <th>Amount</th>
        <th>Paid</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="payment in payments" :key="payment.id">
        <td>{{ payment.id }}</td>
        <td> {{ payment.customerId }}</td>
        <td>{{ payment.amount }}</td>
        <td>{{ payment.paid }}</td>
        <td>{{ payment.status }}</td>
        <td>
          <button class="btn btn-primary" @click="markPaymentAsPaid(payment.id)" :disabled="payment.paid">Pay</button>
          <button class="btn btn-warning cancel" @click="markPaymentAsUnPaid(payment.id)" :disabled="false">Cancel</button>

        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'Payment',
  data() {
    return {
      payments: [],
    };
  },
  methods: {



    async getAllPayments() {
      try {
        const response = await fetch('/api/payment/all');
        if (response.ok) {
          const data = await response.json();
          this.payments = data;
        } else {
          console.error('Failed to fetch payments:', response.statusText);
        }
      } catch (error) {
        console.error('Error during fetching payments:', error);
      }
    },
    async markPaymentAsPaid(paymentId) {
      try {
        const response = await fetch(`/api/payment/${paymentId}/markAsPaid`, {
          method: 'PUT',
        });
        if (response.ok) {
          const data = await response.json();
          const paymentIndex = this.payments.findIndex((payment) => payment.id === data.id);
          if (paymentIndex !== -1) {
            this.payments[paymentIndex] = data;
          }
        } else {
          console.error('Failed to mark payment as paid:', response.statusText);
        }
      } catch (error) {
        console.error('Error during marking payment as paid:', error);
      }
    },
    async markPaymentAsUnPaid(paymentId) {
      try {
        const response = await fetch(`/api/payment/${paymentId}/markAsUnpaid`, {
          method: 'PUT',
        });
        if (response.ok) {
          const data = await response.json();
          const paymentIndex = this.payments.findIndex((payment) => payment.id === data.id);
          if (paymentIndex !== -1) {
            this.payments[paymentIndex] = data;
          }
        } else {
          console.error('Failed to mark payment as unpaid:', response.statusText);
        }
      } catch (error) {
        console.error('Error during marking payment as unpaid:', error);
      }
    },

  },
  created() {
    this.getAllPayments();
  },
};
</script>

<style lang="scss" scoped>
.container {
  max-width: 1300px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.text-center {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;

  th,
  td {
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
}

.btn-primary {
  background: linear-gradient(to right, #3498db, #2980b9);
}

.btn-primary:hover {
  box-shadow: 0px 5px 15px 0px rgba(0, 0, 0, 0.1);
  transform: translateY(-3px);
}

.btn-warning {
  background: linear-gradient(45deg, #ffd31d, #ff9d00);
}

.btn-warning:hover {
  box-shadow: 0px 5px 15px 0px rgba(0, 0, 0, 0.1);
  transform: translateY(-3px);
}

.cancel {
  margin-left: 10px;
}
</style>
