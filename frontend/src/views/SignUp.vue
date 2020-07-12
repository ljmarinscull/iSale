<template>
  <div class="flex-column h-auto w-full p-8 bg-gray-200">
    <div class="flex justify-center items-center">
    <alert v-if="alertMessage" :type="alertType" :color="alertColor" :message="alertMessage" />
    </div>
    <h2 class="w-full my-4 text-3xl font-bold leading-tight text-center text-gray-800">Sign Up</h2>

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
            <!--   <input
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="tel"
              type="number"
              v-model="credentials.phone"
              placeholder="14734075470"
            />-->
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
        <div class="mb-2">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Username</label>
          <ValidationProvider name="email" rules="required|email" v-slot="{ errors }">
            <input
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="email"
              type="email"
              v-model="credentials.username"
              placeholder="Username"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
        <div class="mb-4">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Password</label>
          <ValidationProvider name="email" rules="required|passwordsize" v-slot="{ errors }">
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
          >Sign Up</button>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script>
import { ValidationProvider, ValidationObserver } from "vee-validate";
import { required, email } from "vee-validate/dist/rules";
import { extend } from "vee-validate";
import { mapActions, mapGetters } from "vuex";
import { TheMask } from "vue-the-mask";

extend("email", {
  ...email,
  message: "Enter a valid email"
});

extend("required", {
  ...required,
  message: "This field is required"
});

extend("name", value => {
  const regexp = /^[A-Za-z]{1,10}$/;
  if (value.match(regexp)) return true;

  return "This is a invalid name";
});

extend("passwordsize", value => {
  if (value.length >= 8) return true;

  return "Enter at least 8 characters";
});

extend("phonesize", value => {
  if (value.length >= 10 && value.length <= 12 )
    return true;
  return "Enter a phone number should have between 10 and 12 characters";
});

extend("namesize", value => {
  if (value.length >= 4) return true;

  return "Enter at least 4 characters";
});

export default {
  name: "signup",
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
      username: "",
      password: ""
    }
  }),
  methods: {
    ...mapActions("auth", ["signup"]),
    ...mapActions({ clearAlert: "alert/clear" }),
    onSubmit() {
      this.signup(this.credentials);
    }
  },
  computed: {
    ...mapGetters("alert", ["alertType", "alertMessage", "alertColor"])
  }
};
</script>

