<template>
  <div class="container mx-auto h-screen flex-column p-8 bg-gray-200">
    <h2
      class="w-full my-4 sm:my-0 text-3xl leading-tight text-center md:text-left text-gray-800"
    >{{productTitle}}</h2>
    <div class="w-full mt-4 flex flex-wrap justify-center items-center">
      <div class="max-w-md md:w-1/3 pr-4">
        <img :src="imageUrl" alt="Image" />
      </div>
      <div class="w-full md:w-2/3">
        <p class="text-gray-800 my-8 md:my-0">{{productDescription}}</p>
      </div>
    </div>
    <hr class="my-4 border border-gray-400" />
    <h2 class="text-lg">Contact details</h2>
    <div class="mb-4">
      <div class="text-gray-600">{{contactDetails.username}}</div>
      <div class="text-gray-600"><span>+</span>{{contactDetails.phone}}</div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "product-details",
  data: () => ({
    product: null
  }),
  methods: {
    ...mapActions("product", ["getPublicProduct", "addProductView"]),
    ...mapActions({ clearAlert: "alert/clear" })
  },
  computed: {
    productTitle() {
      return this.product ? this.product.title : "unknow";
    },
    productDescription() {
      return this.product ? this.product.description : "unknow";
    },
    rawProductImageUrl() {
      return this.product && "/images/noimage.jpg";
    },
    imageUrl(){
      return this.rawProductImageUrl == "" ? "/images/noimage.jpg" : this.rawProductImageUrl
    },
    contactDetails() {
      return this.product && this.product.user
        ? this.product.user
        : {
            phone: "unknow",
            username: "unknow"
          };
    }
  },
  async created() {
    try {
      this.product = await this.getPublicProduct(this.$route.params.id);
      if (this.product) {
        setTimeout(() => {
          if (this.$route.params.id) this.addProductView(this.$route.params.id);
        }, 10000);
      } else {
        this.$router.push("/public-error");
      }
    } catch (error) {
      this.$router.push("/public-error");
    }
  }
};
</script>

