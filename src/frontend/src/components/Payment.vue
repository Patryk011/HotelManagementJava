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
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="payment in payments" :key="payment.id">
        <td>{{ payment.id }}</td>
        <td> {{ payment.customerId }}</td>
        <td>{{ payment.amount }}</td>
        <td>{{ payment.paid }}</td>
        <td>
          <button class="btn btn-primary" @click="markPaymentAsPaid(payment.id)" :disabled="payment.paid">Pay</button>
          <button class="btn btn-warning" @click="markPaymentAsUnPaid(payment.id)" :disabled="!payment.paid">Cancel</button>
          <button class="btn btn-danger" @click="deletePayment(payment.id)">Delete</button>
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
        const response = await fetch(`/api/payment/${paymentId}/pay`, {
          method: 'POST',
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
        const response = await fetch(`/api/payment/${paymentId}/cancel`, {
          method: 'POST',
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
    async deletePayment(paymentId) {
      try {
        const response = await fetch(`/api/payment/${paymentId}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          this.payments = this.payments.filter((payment) => payment.id !== paymentId);
        } else {
          console.error('Failed to delete payment:', response.statusText);
        }
      } catch (error) {
        console.error('Error during deleting payment:', error);
      }
    },
  },
  created() {
    this.getAllPayments();
  },
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

.btn-primary[disabled],
.btn-warning[disabled],
.btn-danger[disabled] {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
