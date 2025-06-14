<template>
  <AppLayout title="Login Page">
     <div class="card shadow-sm p-4" style="max-width: 400px; width: 100%;">
          <h3 class="text-center mb-4">Login</h3>
          <form>
            <div class="mb-3">
              <label for="email" class="form-label">Email address</label>
              <input
                type="email"
                class="form-control"
                v-model="email"
                placeholder="name@example.com"
                required
              />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Password</label>
              <input
                type="password"
                class="form-control"
                placeholder="Enter your password"
                required
                v-model="password"
              />
            </div>
            <div class="d-grid">
              <button type="button" @click="doLogin" class="btn btn-primary">Log In</button>
            </div>
          </form>
          <div class="mt-3 text-center">
            <a href="#">Forgot password?</a>
          </div>
        </div>
  </AppLayout>
</template>

<script>
import axios from 'axios'
import AppLayout from './layout/AppLayout.vue'

export default {
  name: 'Login',
  components: { AppLayout },

  data() {
    return {
      username: '',
      password: ''
    }
  },

  methods: {
    async doLogin() {
      try {
        const response = await axios.post('/api/login', {
          username: this.username,
          password: this.password
        })
        let data = response.data
      } catch (error) {
        this.message = 'Login failed.'
        console.error(error)
      }
    }
  }
}
</script>
