<template>
    <div class="container">
        <h1 class="text-center">Customer List</h1>
        <table class="table table-striped">
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
    </div>
</template>

<script>
export default {
    name: 'Customers',
    data() {
        return {
            customers: [],
        };
    },
    methods: {
        async getCustomers() {
            try {
                const response = await fetch('/api/customers/all');
                const data = await response.json();
                this.customers = data;
            } catch (error) {
                console.error('Błąd podczas pobierania danych:', error);
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

</style>