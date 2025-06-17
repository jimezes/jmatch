<template>
  <div>
    <h3>Select Skills</h3>
    <div v-if="loading">Loading skills...</div>
    <div v-else-if="error">{{ error }}</div>
    <div v-else>
      <ul class="skill-list">
        <li v-for="skill in skills" :key="skill">
            <label>
            <input 
                type="checkbox" 
                :value="skill" 
                v-model="selectedSkills" 
            />
            {{ skill }}
            </label>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
   
  },

  data() {
    return {
      skills: [],
      selectedSkills:[],
      loading: false,
      error: null,
    };
  },

  mounted() {
    this.fetchSkills();
  },

  methods: {
    async fetchSkills() {
      this.loading = true;
      this.error = null;
      try {
        const response = await fetch('/api/skills');
        const data = await response.json();
        this.skills = data;
      } catch (err) {
        this.error = err.message || 'Error loading skills';
      } finally {
        this.loading = false;
      }
    },
  },

  watch: {
    selectedSkills(newVal) {
      this.$emit('selected-skill', newVal);
    },
  },
};
</script>

<style scoped>
    ul {
        list-style: none;
        padding-left: 0;
    }

    li {
        margin-bottom: 6px;
    }

    .skill-list {
        display: grid;
        grid-template-columns: repeat(3, 1fr); /* 3 equal columns */
        gap: 8px;
        padding-left: 0;
        list-style: none;
    }

    .skill-list li {
        margin-bottom: 6px;
    }
</style>
