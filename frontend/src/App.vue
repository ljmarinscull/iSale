<template>
  <div id="app" class="antialiased">
      <super-nav-bar/>
      <router-view></router-view>
      <super-footer/>
  </div>
</template>

<script>

export default {
  name: 'app',
  components: {
    SuperNavBar : () => import("@/components/SuperNavBar.vue"),
    SuperFooter : () => import("@/components/Footer.vue")
  },
}
</script>

<style src="./assets/tailwind.css"></style>
