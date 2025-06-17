<template>
   <div class="card mb-4 shadow-sm">
    <div class="card-body">
      <h6 class="card-title">{{ job.title }}</h6>
      <p class="card-text">{{ job.description }}</p>

      <div class="mt-3">
        <span 
          v-for="(skill, index) in job.skills" 
          :key="index" 
          class="badge"
        >
          {{ skill }}
        </span>
      </div>
    </div>
  </div>


</template>
<script>
 export default{
    props:{
        job:Object
    }
 }
</script>

<style scoped>

</style>