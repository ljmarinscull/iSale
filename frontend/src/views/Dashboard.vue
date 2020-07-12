<template>
  <div class="w-full px-2 pt-4 pb-12 text-gray-800 bg-gray-200">
    <delete-dialog-confirm :showing="showDeleteConfirm" @close="onClose" >
      <h2>Are you sure you want to delete this item?</h2>
    </delete-dialog-confirm>
    <h2 class="w-full my-4 text-3xl font-bold leading-tight text-center text-gray-800">Your products</h2>
    <div class="flex justify-end">
      <router-link
        class="bg-pink-600 hover:bg-pink-400 text-white font-semibold hover:text-white sm:mt-1 focus:outline-none my-2 mr-3 py-2 px-4 border border-pink-500 rounded-full"
        to='/app/create-product'
      >Create</router-link>
    </div>
    <div v-if="userProducts" class="w-full flex flex-wrap">
      <v-product
        v-for="(item,index) in userProducts"
        :product="item"
        :key="index"
        @onDelete="onDeleteProductAction"
        @onEdit="onEditProductAction"
      />
    </div>
    <div v-else class="h-screen text-center bg-gray-200 flex flex-col justify-center items-center ">
        <h1 class="text-6xl font-bold text-black">Empty list :(</h1>
        <p class="text-2xl text-gray-600">Add new products to your list.</p>
    </div>
  </div>
</template>

<script>

import { mapActions, mapGetters } from "vuex";

export default {
  name: 'dashboard',
  components: {
    VProduct: () => import("@/components/VProduct.vue"),
    DeleteDialogConfirm: () => import("@/components/DeleteDialogConfirm.vue")
  },
  data() {
    return {
      showDeleteConfirm: false,
      currentId: -1
      }
  },
  methods: {
    ...mapActions("product", ["getUserProducts"]),
    onClose(value){
      if(value === "yes")
          this.$store.dispatch('product/deleteProduct',this.currentId)
      this.showDeleteConfirm = false;
      this.currentId = -1
    },
    onDeleteProductAction(value) {
        this.showDeleteConfirm = true;
        this.currentId = value
    },
    onEditProductAction(value) {
      this.$router.push({ name: 'edit-product', params:{ id: value }})
    }
  },
  computed: {
      ...mapGetters("product", ["userProducts"])
  },
   created() {
    this.getUserProducts();
  }
};
</script>