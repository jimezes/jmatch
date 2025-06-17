<template>
  <AppLayout title="Home">
        <SkillSelector @selected-skill="handleSelectedSkills"/>

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
import SkillSelector from './SkillSelector.vue';

export default {
  name: 'Home',
  components: { AppLayout,SkillSelector },
  props:{
  
  },
  data() {
    return {
        selectedSkills:undefined,
        filteredJobs:undefined
    }
  },
  methods: {
   async fetchJobsBySkills() {
    this.loading = true;
    try {
        const response = await axios.post('/api/jobs/by_skill', {
           skills:this.selectedSkills
        });

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
    },
    handleSelectedSkills(skills){
        console.log("received skills: "+JSON.stringify(skills));
        this.selectedSkills = skills;
        this.fetchJobsBySkills();
    }
  },
  mounted(){
    //this.fetchJobsBySkills();
  }
}
</script>
