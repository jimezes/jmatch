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
          <li v-for="link in navLinks" :key="link.text" class="nav-item">
            <a :href="link.url" class="nav-link">{{ link.text }}</a>
          </li>
        </ul>
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
      ]
    }
  }
}
</script>
