<template>
  <div class="antialiased">
      <router-view :key="$route.fullPath"></router-view>
  </div>
</template>


<script>

export default {
  name: 'layout',
}
</script>