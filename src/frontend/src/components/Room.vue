<template>
    <div class="container">
        <h1 class="text-center">Room List</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Room ID</th>
                <th>Type</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="room in rooms" :key="room.id">
                <td>{{ room.id }}</td>
                <td>{{ room.type }}</td>
                <td>{{ room.description }}</td>
                <td>{{ room.price }}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    name: 'Rooms',
    data() {
        return {
            rooms: [],
        };
    },
    methods: {
        async getRooms() {
            try {
                const response = await fetch('/api/room/all');
                const data = await response.json();
                this.rooms = data;
            } catch (error) {
                console.error('Błąd podczas pobierania danych:', error);
            }
        },
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

</style>