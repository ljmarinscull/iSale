<template>
  <header class="sm:flex sm:items-center sm:justify-between sm:px-4 sm:py-3">
    <div class="flex items-center justify-between px-4 py-3 sm:p-0">
      <div class="flex items-center text-pink-400">
        <img class="h-12" src="/images/site-icon.svg" alt="iSale">
        <h2 class="text-2xl ml-2">iSale</h2>
      </div>
      <div class="sm:hidden">
        <button @click="isOpen = !isOpen" type="button" class="block text-gray-500 hover:text-black focus:text-black focus:outline-none">
          <svg class="h-6 w-6 fill-current" viewBox="0 0 24 24">
            <path v-if="isOpen" fill-rule="evenodd" d="M18.278 16.864a1 1 0 0 1-1.414 1.414l-4.829-4.828-4.828 4.828a1 1 0 0 1-1.414-1.414l4.828-4.829-4.828-4.828a1 1 0 0 1 1.414-1.414l4.829 4.828 4.828-4.828a1 1 0 1 1 1.414 1.414l-4.828 4.829 4.828 4.828z"/>
            <path v-if="!isOpen" fill-rule="evenodd" d="M4 5h16a1 1 0 0 1 0 2H4a1 1 0 1 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2z"/>
          </svg>
        </button>
      </div>
    </div>
    <nav :class="isOpen ? 'block' : 'hidden'" class="px-2 pt-2 pb-4 sm:flex sm:p-0 sm:justify-around sm:items-center ">
      <div v-if="!status.loggedIn" class="sm:flex">
      <router-link to="/home" class="block px-2 py-1 text-gray-500 font-semibold rounded hover:text-pink-400">Home</router-link>
      <router-link to="/about"  class="mt-1 block px-2 py-1 text-gray-500 font-semibold rounded hover:text-pink-400 sm:mt-0 sm:ml-2">About</router-link>
      <router-link to="/contact"  class="mt-1 block px-2 py-1 text-gray-500 font-semibold rounded hover:text-pink-400 sm:mt-0 sm:ml-2">Contact</router-link>
      </div>
      <hr class="mt-2"/>
      <div class="sm:flex sm:ml-8">
        <button v-if="!status.loggedIn" class="bg-transparent hover:bg-pink-400 text-pink-500 font-semibold hover:text-white mt-1 focus:outline-none py-2 px-4 mr-2 border border-pink-500 hover:border-transparent rounded-full" @click="gotoSignUp">Sign Up</button>
        <button v-if="!status.loggedIn" class="bg-transparent hover:bg-pink-400 text-pink-500 font-semibold hover:text-white sm:mt-1 focus:outline-none py-2 px-4 border border-pink-500 hover:border-transparent rounded-full" @click="gotoSignIn" >Sign In</button>
        <div v-if="user && status.loggedIn" class="sm:flex sm:justify-center sm:items-center text-xl text-gray-600 mr-4 ">
          Welcome, <strong class="ml-2"> {{user.name}}</strong>
        </div>
        <div class="flex mt-2 sm:mt-0"  v-if="status.loggedIn">
          <button class="bg-transparent hover:bg-pink-400 text-pink-500 font-semibold hover:text-white focus:outline-none py-2 px-4 border border-pink-500 hover:border-transparent rounded-full" @click="onProfile" >Profile</button>
          <button class="bg-transparent hover:bg-pink-400 text-pink-500 font-semibold hover:text-white focus:outline-none py-2 px-4 border border-pink-500 hover:border-transparent rounded-full  ml-2" @click="onLogOut" >Logout</button>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
import {mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      isOpen: false,
    }
  },
  computed: {
    ...mapState("auth", ["status","user"]),
  },
  methods: {
    ...mapActions("auth", ["logout"]),
    async onLogOut(){
      await this.logout();
      setTimeout(()=>{
        this.$router.push('/home')
      },500)
    },
    async onProfile(){
      this.$router.push('profile')
    },
    gotoSignUp(){
      this.$router.push('/signup').catch(()=>{});
    },
    gotoSignIn(){
      this.$router.push('/login').catch(()=>{});
    }
  },
}
</script>
