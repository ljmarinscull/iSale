<template>
  <div class="container mx-auto flex-column p-8 bg-gray-200">
    <alert v-if="alertMessage" :type="alertType" :color="alertColor" :message="alertMessage" />
    <h2
      class="w-full my-4 text-3xl font-bold leading-tight text-center text-gray-800"
    >Create product</h2>
    <ValidationObserver v-slot="{ invalid }" class="flex justify-center items-center">
      <form
        class="w-full max-w-md bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
        @submit.prevent="onSubmit"
      >
        <div class="mb-4">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="title">Title</label>
          <ValidationProvider name="title" rules="required|nameMin|nameMax" v-slot="{ errors }">
            <input
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="title"
              type="text"
              v-model="product.title"
              placeholder="Title"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
        <div class="mb-6">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="description">Description</label>
          <ValidationProvider
            name="description"
            rules="required|descriptionMin|descriptionMax"
            v-slot="{ errors }"
          >
            <textarea
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="description"
              type="text"
              v-model="product.description"
              placeholder="Description"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>

        <div class="flex justify-between text-blue-400 items-center text-center mb-10 relative">
          <input
            type="file"
            name="photo"
            :disabled="isSaving"
            @change="filesChange($event.target.name, $event.target.files);"
            accept="image/*"
            class="opacity-0 absolute max-w-md h-8 cursor-pointer"
          />
          <p v-if="isInitial">Click here to browse an image</p>
          <p v-if="isSaving">
            Uploaded!
            <strong>{{file.name}}</strong>
          </p>
        </div>
        <div class="flex items-center justify-end">
          <router-link class=".underline text-gray-800 mr-4" to="/app/dashboard" exact>Cancel</router-link>
          <button
            class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
            v-bind:class="{ 'opacity-50 cursor-not-allowed': invalid }"
            :disabled="invalid"
          >Create</button>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script>
import { ValidationProvider, ValidationObserver } from "vee-validate";
import { required } from "vee-validate/dist/rules";
import { extend } from "vee-validate";
import { mapActions, mapGetters } from "vuex";

const STATUS_INITIAL = 0,
  STATUS_SAVING = 1;

extend("required", {
  ...required,
  message: "This field is required"
});

extend("name", value => {
  const regexp = /^(([A-Za-z]+)*([A-Za-z]+)?\s)+([A-Za-z]+)*([A-Za-z]+)?$/;
  if (value.match(regexp)) return true;

  return "This is a invalid name";
});

extend("nameMax", value => {
  if (value.length <= 50) return true;

  return "The name is too long";
});

extend("descriptionMax", value => {
  if (value.length <= 250) return true;

  return "The description is too long";
});

extend("descriptionMin", value => {
  if (value.length >= 5) return true;

  return "The description is too short";
});

extend("nameMin", value => {
  if (value.length >= 5) return true;

  return "Enter at least 5 characters";
});

export default {
  name: "create-product",
  components: {
    ValidationProvider,
    ValidationObserver,
    Alert: () => import("@/components/Alert.vue")
  },
  data: () => ({
    product: {
      title: "",
      description: ""
    },
    uploadError: null,
    currentStatus: STATUS_INITIAL,
    file: null,
  }),
  methods: {
    ...mapActions("product", ["createProduct"]),
    ...mapActions({ clearAlert: "alert/clear" }),
    onSubmit() {
      
      const formData = new FormData()
      formData.append('file', this.file)
      formData.append('title', this.product.title)
      formData.append('description',this.product.description)

      this.$store.dispatch('product/createProduct',formData);
    },
    filesChange(fieldName, fileList) {
      if (!fileList.length) return;

      const fsize = fileList[0].size;
      const file = Math.round(fsize / 1024);
      
      if (file > 1024) {
        alert("File too Big, please select a file less than 1mb");
        return;
      }

      this.currentStatus = STATUS_SAVING;
      this.file = fileList[0];
    }
  },
  computed: {
    ...mapGetters("alert", ["alertType", "alertMessage", "alertColor"]),
    isInitial() {
      return this.currentStatus === STATUS_INITIAL;
    },
    isSaving() {
      return this.currentStatus === STATUS_SAVING;
    }
  }
};
</script>