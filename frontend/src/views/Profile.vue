<template>
  <div class="flex-column h-auto w-full p-8 bg-gray-200">
    <div class="flex justify-center items-center">
      <alert v-if="alertMessage" :type="alertType" :color="alertColor" :message="alertMessage" />
    </div>
    <h2 class="w-full my-4 text-3xl font-bold leading-tight text-center text-gray-800">Edit profile</h2>

    <ValidationObserver v-slot="{ invalid }" class="flex justify-center items-center">
      <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" @submit.prevent="onSubmit">
        <div class="mb-2">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="name">Name</label>
          <ValidationProvider name="name" rules="required|name|namesize" v-slot="{ errors }">
            <input
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="name"
              type="text"
              v-model="credentials.name"
              placeholder="Name"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
        <div class="mb-2">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="tel">Phone</label>
          <ValidationProvider name="tel" rules="required|phonesize" v-slot="{ errors }">
            <the-mask
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="tel"
              :mask="['+# (###) ###-####','+## (##) ###-###','+## (###) ###-####']"
              placeholder="1(473) 307 5470"
              v-model="credentials.phone"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
        <div class="mb-4">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Password</label>
          <ValidationProvider name="email" rules="passwordsize" v-slot="{ errors }">
            <input
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              v-model="credentials.password"
              placeholder="********"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
        <div class="flex items-center justify-end">
          <button
            class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
            v-bind:class="{ 'opacity-50 cursor-not-allowed': invalid }"
            :disabled="invalid"
          >Edit</button>
        </div>
      </form>
    </ValidationObserver>
    <div class="flex justify-center">
       <router-link class=".underline text-blue-400" to="/app/dashboard" exact>
            Go to Dashboard page
        </router-link>
    </div>
  </div>
</template>

<script>
import { ValidationProvider, ValidationObserver } from "vee-validate";
import { extend } from "vee-validate";
import { required } from "vee-validate/dist/rules";

import { mapActions, mapGetters } from "vuex";
import { TheMask } from "vue-the-mask";

extend("name", value => {
  const regexp = /^[A-Za-z]{1,10}$/;
  if (value.match(regexp)) return true;

  return "This is a invalid name";
});

extend("required", {
  ...required,
  message: "This field is required"
});

extend("passwordsize", value => {
  if (value.length >= 8) return true;

  return "Enter at least 8 characters";
});

extend("phonesize", value => {
  if (value.length >= 10 && value.length <= 12) return true;
  return "Enter a phone number should have between 10 and 12 characters";
});

extend("namesize", value => {
  if (value.length >= 4) return true;

  return "Enter at least 4 characters";
});

export default {
  name: "edit-profile",
  components: {
    ValidationProvider,
    ValidationObserver,
    TheMask,
    Alert: () => import("@/components/Alert.vue")
  },
  data: () => ({
    credentials: {
      name: "",
      phone: "",
      password: ""
    }
  }),
  methods: {
    ...mapActions("user", ["updateUserProfile"]),
    ...mapActions({ clearAlert: "alert/clear" }),
    async onSubmit() {
      const currentProfile = Object.assign({}, this.credentials);

      if (currentProfile.password.length == 0)
        delete currentProfile["password"];

      if (currentProfile.name == this.user.name) 
        delete currentProfile["name"];

      if (currentProfile.phone == this.user.phone)
        delete currentProfile["phone"];

      const newUser = this.updateUserProfile(currentProfile);
      if (newUser) {
        this.credentials.password = "";
      }
    }
  },
  computed: {
    ...mapGetters("alert", ["alertType", "alertMessage", "alertColor"]),
    ...mapGetters("auth", ["user"])
  },
  created() {
    this.credentials.name = this.user.name;
    this.credentials.phone = this.user.phone;
  }
};
</script>

