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

</style>