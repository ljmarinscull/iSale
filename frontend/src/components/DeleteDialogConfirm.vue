<template>
<div
    v-if="showing"
    class="fixed inset-0 w-full h-screen flex items-center justify-center bg-semi-75"
  >
    <div class="relative w-full max-w-2xl shadow-lg  bg-gray-200 rounded-lg p-8">
     <div class="flex justify-between items-center pb-3">
          <p class="text-2xl font-bold">Confirm Dialog</p>
          <button @click.prevent="close('no')" class="cursor-pointer z-50">
            <svg class="fill-current text-black" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
              <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"></path>
            </svg>
          </button>
        </div>
      <slot />
      <!--Footer-->
        <div class="flex justify-end pt-2">
          <button @click.prevent="close('no')" class="px-4 bg-transparent p-3 rounded-lg text-gray-500 hover:bg-gray-100 hover:text-indigo-400 mr-2">No</button>
          <button @click.prevent="close('yes')" class="px-4 bg-red-500 p-3 rounded-lg text-white hover:bg-red-400">Yes</button>
        </div>
    </div>
  </div>
  
</template>

<script>
export default {
    props: {
    showing: {
      required: true,
      type: Boolean
    }
  },
  methods: {
    close(value) {
      this.$emit('close',value);
    }
  }
}
</script>

 