<template>
  <AppLayout title="Login">
     <div style="display:flex;flex-direction:row;justify-content:center;align-items:center;width:100%;height:100%;">
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
              <div v-if="error_message" style="color:red;" class="mt-3 text-center">
                  {{error_message}}
              </div>
              <div v-if="loading" class="mt-3 text-center">
                  Loading...
              </div>
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
      email: '',
      password: '',
      error_message:undefined,
      loading:false
    }
  },

  methods: {
    async doLogin() {
      this.loading = true;
      try {
        const response = await axios.post('/api/login', {
          email: this.email,
          password: this.password
        })
        let data = response.data;
        this.loading = false;
        //console.log(JSON.stringify(data));
        if(data.status == 1){
          localStorage.setItem("token",data.token);
          this.$router.push("/");
        }
        else{
          this.error_message = data.message;
        }
      } catch (error) {
        this.message = 'Login failed. Make sure that you used the correct credentials.';
        console.error(error)
      }
    }
  }
}
</script>
