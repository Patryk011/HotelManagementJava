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

</style>