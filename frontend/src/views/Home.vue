<template>
  <section class="bg-gray-200 py-8">
    <div class="flex justify-center items-center">
      <alert v-if="alertMessage" :type="alertType" :color="alertColor" :message="alertMessage" />
    </div>

    <h1
      class="w-full my-2 text-5xl font-bold leading-tight text-center text-gray-800"
    >Popular products</h1>
    <div v-if="populars" class="container mx-auto flex flex-wrap">
      <product v-for="(item,index) in populars" :product="item" :key="index" />
    </div>
    <div v-else class="h-screen mx-auto text-center flex flex-col justify-center items-center">
      <h1 class="text-6xl font-bold text-black">Empty list :(</h1>
      <p
        class="text-2xl text-gray-600"
      >Sorry, we don't have any registered populars products to show</p>
    </div>

    <h1
      class="w-full mb-2 mt-10 text-5xl font-bold leading-tight text-center text-gray-800"
    >Products</h1>
    <div v-if="products" class="container mx-auto flex flex-wrap">
      <product v-for="(item,index) in products" :product="item" :key="index" />
    </div>
    <div v-else class="flex flex-col justify-center items-center h-screen w-full text-center">
      <h1 class="text-6xl font-bold text-black">Empty list :(</h1>
      <p class="text-2xl text-gray-600">Sorry, we don't have any registered products to show</p>
    </div>
  </section>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "home",
  components: {
    Product: () => import("@/components/Product.vue"),
    Alert: () => import("@/components/Alert.vue")
  },
  data() {
    return {
      
    };
  },
  methods: {
    ...mapActions("product", ["getProductsForHome"]),
    
  },
  computed: {
    ...mapGetters("product", ["populars", "products"]),
    ...mapGetters("alert", ["alertType", "alertMessage", "alertColor"]),
  },
  created() {
    this.getProductsForHome();
  }
};
</script>
