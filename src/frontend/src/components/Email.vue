<template>
  <div class="container">
    <h1 class="title">Sending e-mail</h1>
    <form @submit.prevent="sendEmail" class="email-form">
      <label for="to">To:</label>
      <input type="email" id="to" v-model="email.to" required>
      <br>
      <label for="subject">Subject:</label>
      <input type="text" id="subject" v-model="email.subject" required>
      <br>
      <label for="message">Message:</label>
      <textarea id="message" class="email-message" v-model="email.message" required></textarea>
      <br>
      <button type="submit">Send</button>
    </form>
  </div>
</template>

<script>
import { reactive } from 'vue';

export default {
  name: 'EmailForm',
  setup() {

    const email = reactive({
      to: '',
      subject: '',
      message: '',
    });


    const sendEmail = async () => {
      try {
        const response = await fetch('/api/email/send', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(email),
        });

        if (response.ok) {
          console.log('Wiadomość e-mail została wysłana');
          email.to = '';
          email.subject = '';
          email.message = '';


        } else {
          console.error('Błąd podczas wysyłania wiadomości e-mail:', response.statusText);

        }
      } catch (error) {
        console.error('Błąd podczas wysyłania wiadomości e-mail:', error);

      }
    };

    return {
      email,
      sendEmail,
    };
  },
};
</script>



<style scoped lang="scss">
.container {
  text-align: center;
  justify-content: center;
  align-items: center;
}

.title {

  margin-bottom: 20px;
}

.email-form {
  text-align: left;
  margin: 50px auto;
  width: 450px;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 4px;
}
.email-form input:focus,
.email-form textarea:focus {
  outline-color: #728FCE;
  outline-width: 2px;
  outline-style: solid;
}







label {
  font-weight: bold;
  margin-bottom: 10px;
  }


input[type="email"],
input[type="text"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-bottom: 10px;
}

button[type="submit"] {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}

.email-message {
  height: 100px;
}





</style>
