<template>
  <div class="email-list">
    <h2 class="email-list__title">MailBox</h2>
    <ul class="email-list__items">
      <li v-for="email in emails" :key="email.id" class="email-list__item">
        <h3 class="email-list__item-title">{{ email.subject }}</h3>
        <p class="email-list__item-from">From: {{ extractEmailFromAddress(email.from) }}</p>
        <p class="email-list__item-date">Received Date: {{ formatDate(email.receivedDate) }}</p>
        <p class="email-list__item-content">{{ email.content }}</p>
        <button class="email-list__item-delete-button" @click="deleteEmail(email.id)">X</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emails: []
    };
  },
  mounted() {
    this.fetchEmails();
  },
  methods: {
    async fetchEmails() {
      try {
        const response = await fetch('/api/email/all');
        const data = await response.json();
        this.emails = data;
      } catch (error) {
        console.error('Błąd podczas pobierania danych:', error);
      }
    },
    async deleteEmail(index) {
      try {
        const response = await fetch(`/api/email/${index}`, {
          method: 'DELETE',
        });

        if (response.ok) {

          this.emails.splice(index, 1);
        } else {
          console.error('Błąd podczas usuwania wiadomości:', response.status);
        }
      } catch (error) {
        console.error('Błąd podczas wysyłania żądania:', error);
      }
    },


    extractEmailFromAddress(address) {
      const emailRegex = /(?<=<)[^<>]+(?=>)/;
      const match = address.match(emailRegex);
      return match ? match[0] : address;
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' };
      const date = new Date(dateString);
      return date.toLocaleDateString(undefined, options);
    }
  }
}
</script>

<style scoped lang="scss">

.email-list {
  margin: 0 auto;
  padding: 20px;
}

.email-list__title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 10px;
}

.email-list__items {
  list-style: none;
  padding: 0;
  width: 700px;
}

.email-list__item {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  position: relative;
  margin-bottom: 10px;
}

.email-list__item-title {
  text-align: center;
  font-size: 18px;
  margin-bottom: 5px;
}

.email-list__item-from,
.email-list__item-date {
  font-size: 14px;
  margin-bottom: 3px;
}

.email-list__item-content {
  font-size: 14px;
}

.email-list__item-delete-button {
  color: #000000;
  border: none;
  padding: 5px;
  border-radius: 50%;
  cursor: pointer;
  position: absolute;
  top: 5px;
  right: 5px;
  font-weight: bold;
}



</style>