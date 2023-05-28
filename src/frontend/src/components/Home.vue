<template>
  <main id="Home-page" class="home">
    <h1>Home</h1>
    <div class="statistics">
      <div class="statistic-box reservation-box">
        <h2>{{ reservationsCount }}</h2>
        <p>Reservations</p>
      </div>
      <div class="statistic-box customer-box">
        <h2>{{ customersCount }}</h2>
        <p>Customers</p>
      </div>
      <div class="statistic-box hotel-box">
        <h2>{{ hotelsCount }}</h2>
        <p>Hotels</p>
      </div>
      <div class="statistic-box room-box">
        <h2>{{ roomsCount }}</h2>
        <p>Rooms</p>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      reservationsCount: 0,
      customersCount: 0,
      hotelsCount: 0,
      roomsCount: 0
    };
  },
  async created() {
    try {
      const [reservationsResponse, customersResponse, hotelsResponse, roomsResponse] = await Promise.all([
        fetch('/api/reservation/all'),
        fetch('/api/customers/all'),
        fetch('/api/hotel/all'),
        fetch('/api/room/all')
      ]);

      this.reservationsCount = (await reservationsResponse.json()).length;
      this.customersCount = (await customersResponse.json()).length;
      this.hotelsCount = (await hotelsResponse.json()).length;
      this.roomsCount = (await roomsResponse.json()).length;
    } catch (error) {
      console.error('Failed to fetch counts:', error);
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  text-align: center;
}

.statistics {
  display: flex;
  justify-content: space-around;
  margin-top: 100px;
}

.statistic-box {
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 20px;
  width: 300px;
  height: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;

}

.reservation-box {
  background-color: #16a4b8;
}

.customer-box {
  background-color: #27a747;
}

.hotel-box {
  background-color: #fdc008;
}

.room-box {
  background-color: #dd3544;
}
</style>
