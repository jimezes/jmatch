<template>
  <AppLayout title="Home">
      {{ JSON.stringify(filteredJobs) }}

       <div v-if="error_message" style="color:red;" class="mt-3 text-center">
                  {{error_message}}
        </div>
        <div v-if="loading" class="mt-3 text-center">
            Loading...
        </div>
  </AppLayout>
</template>

<script>
import axios from 'axios'
import AppLayout from './layout/AppLayout.vue'

export default {
  name: 'Home',
  components: { AppLayout },
  props:{
    skills:Array
  },
  data() {
    return {
        filteredJobs:undefined
    }
  },
  methods: {
   async fetchJobsBySkills() {
    this.loading = true;
    try {
        const response = await axios.post('/api/jobs/by_skill', [
        "Java", "Spring Boot", "SQL"
        ]);

        const data = response.data;
        this.loading = false;

        if (Array.isArray(data)) {
        // Successfully received job list
        this.filteredJobs = data;
        console.log("Matching jobs:", data);
        } else {
        this.error_message = "Unexpected response format.";
        }
    } catch (error) {
        this.loading = false;
        this.error_message = "Failed to fetch jobs. Try again later.";
        console.error("Job fetch error:", error);
    }
    }
  },
  mounted(){
    this.fetchJobsBySkills();
  }
}
</script>
