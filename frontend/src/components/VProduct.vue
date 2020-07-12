<template>
  <div class="w-full md:w-1/3 p-6 flex flex-col flex-shrink">
    <div class="flex-1 bg-white rounded-t rounded-b-none overflow-hidden shadow">
        <img :src="imageUrl" alt="Photo" class="w-full h-32 object-contain mt-4">
        <p class="w-full font-bold text-xl text-gray-800 pt-6 px-6">{{product.title}}</p>
        <p class="text-gray-800 text-base px-6 mb-5">{{product.description}}</p>
    </div>
    <div class="mt-auto bg-white rounded-b rounded-t-none overflow-hidden shadow p-6">
      <div class="flex items-center justify-between">
        <button
          class="mx-auto lg:mx-0 text-red-600 font-bold rounded-full mt-4 py-4 px-8 shadow-xl focus:outline-none"
			@click.prevent="onDelete"
        >Delete</button>
        <button
          class="mx-auto lg:mx-0 text-pink-600 font-bold rounded-full mt-4 py-4 px-8 shadow-xl focus:outline-none"
			@click.prevent="onEdit"
        >Edit</button>
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
          id: 1,
          title: "unknow",
          subtitle: "Lorem ipsum dolor sit amet.",
          description:
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at ipsum eu nunc commodo posuere et sit amet ligula."
        };
      }
    }
  },
  methods: {
    onDelete() {
      this.$emit("onDelete", this.product.id);
    },
    onEdit() {
    this.$emit("onEdit", this.product.id);
    }
  },
  computed: {
    imageUrl(){
      return (this.product && this.product.imageUrl == "" ) ? "/images/noimage.jpg" : this.product.imageUrl
    }
  },
};
</script>

