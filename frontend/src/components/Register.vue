<template>
  <AppLayout title="Registration">
     <div style="display:flex;flex-direction:row;justify-content:center;align-items:center;width:100%;height:100%;">
         <div class="card shadow-sm p-4" style="max-width: 400px; width: 100%;">
              <h3 class="text-center mb-4">Register</h3>
              <form>
                <div class="mb-3">
                  <label for="first_name" class="form-label">First name</label>
                  <input
                    type="text"
                    class="form-control"
                    v-model="first_name"
                    placeholder="John"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="last_name" class="form-label">Last name</label>
                  <input
                    type="text"
                    class="form-control"
                    v-model="last_name"
                    placeholder="Doe"
                    required
                  />
                </div>
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

                <div class="mb-3">
                  <label for="password_confirm" class="form-label">Repeat password</label>
                  <input
                    type="password"
                    class="form-control"
                    placeholder="Retype your password"
                    required
                    v-model="password_confirm"
                  />
                </div>
                <div class="d-grid">
                  <button type="button" @click="doRegister" class="btn btn-primary">Register</button>
                </div>
              </form>
              <div v-if="error_message" class="mt-3 text-center" style="color:red;">
                  {{ error_message }}
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
  name: 'Register',
  components: { AppLayout },

  data() {
    return {
      first_name:'',
      last_name:'',
      email: '',
      password: '',
      password_confirm: '',
      error_message:undefined,
      loading:false
    }
  },

  methods: {
    async doRegister() {
      this.loading = true;
      try {
        const response = await axios.post('/api/register', {
          firstName: this.first_name,
          lastName: this.last_name,
          email: this.email,
          password: this.password,
          passwordConfirm: this.password_confirm
        })
        let data = response.data;
        this.loading = false;
        if(data.status == 1){
          //handle successfull request
        }
        else{
          this.error_message = data.message;
        }
        //console.log(JSON.stringify(data));

      } catch (error) {
        this.message = 'Registration failed. Make sure that you have filled all neccessary information.'
        console.error(error)
      }
    }
  }
}
</script>
