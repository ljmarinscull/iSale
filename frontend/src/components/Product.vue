<template>
  <div class="w-full md:w-1/3 p-6 flex flex-col flex-shrink">
    <div class="flex-1 bg-white rounded-t rounded-b-none overflow-hidden shadow">
      <router-link 
		:to="`/product-details/${product.id}`" 
		class="flex flex-wrap no-underline hover:no-underline">
        <img :src="imageUrl" alt="Photo" class="w-full h-32 pt-4 object-contain">
        <p class="w-full font-bold text-xl text-gray-800 pt-4 px-6">{{product.title}}</p>
        <p class="text-gray-800 text-base px-6 w-full leading-tight overflow-hidden h-16"
        >{{product.description}}</p>
      </router-link>
      <div class="flex justify-between text-gray-800 text-base px-4 py-4">
        <i class="fa fa-eye" aria-hidden="true"> {{product.views}}</i>
        <i class="fa fa-calendar" aria-hidden="true"> {{product.createdAt}}</i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    product: {
      required: true,
      type: Object,
      default: function() {
        return {
          title: "unknow",
          subtitle: "Lorem ipsum dolor sit amet.",
          description:
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at ipsum eu nunc commodo posuere et sit amet ligula."
        };
      }
    }
  },
  computed: {
    imageUrl(){
      return (this.product && this.product.imageUrl == "" ) ? "/images/noimage.jpg" : this.product.imageUrl
    }
  },
};
</script>