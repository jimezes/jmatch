<template>
  <div class="container-fluid">
    <div class="row bg-primary text-white p-3">
      <div class="col">
        <h1 class="h4 mb-0">{{ title }}</h1>
      </div>
    </div>
    <div class="row">
      <nav class="col-md-3 col-lg-2 bg-light p-3 vh-100 border-end">
        <ul class="nav flex-column">
          <li v-for="link in filterAuthLinks" :key="link.text" class="nav-item">
            <a :href="link.url" class="nav-link">{{ link.text }}</a>
          </li>
        </ul>
        <button v-if="token" class="btn btn-primary" @click="doLogout()">Logout</button>
      </nav>
      <main class="col-md-9 col-lg-10 p-4">
        <slot></slot>
      </main>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppLayout',
  props: {
    title: {
      type: String,
      default: 'JMatch'
    }
  },
  data() {
    return {
      navLinks: [
        { text: 'Home', url: '/' },
        { text: 'Login', url: '/login' },
        { text: 'Register', url: '/register' }
      ],
      token:undefined
    }
  },
  computed:{
    filterAuthLinks(){
      this.token = localStorage.getItem("token");
      var results = [];
      for(let i=0;i<this.navLinks.length;i++){
        let current = this.navLinks[i];
        if(current.text == "Home"){
          if(this.token != undefined){
               results.push(current);
          }       
        }
        else{
          results.push(current);
        }
      }
      return results;
    }
  },
  methods:{
    doLogout(){
        localStorage.removeItem("token");
        this.$router.push("/login");
    }
  }
}
</script>

<style scroped>
  @media (max-width: 767.98px) {
    nav {
      max-height: 180px;
      overflow-y: auto;
    }
  }
</style>
