<template>
  <div class="flex-column h-auto w-full p-8 bg-gray-200">

    <div class="flex justify-center items-center">
    <alert v-if="alertMessage" :type="alertType" :color="alertColor" :message="alertMessage" />
    </div>

    <h2 class="w-full my-4 text-3xl font-bold leading-tight text-center text-gray-800">Login</h2>

    <ValidationObserver v-slot="{ invalid }" class="flex justify-center items-center">
      <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" @submit.prevent="onSubmit">
        <div class="mb-4">
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
        <div class="mb-6">
          <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Password</label>
          <ValidationProvider name="email" rules="required|passwordsize" v-slot="{ errors }">
            <input
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              v-model="credentials.password"
              placeholder="*******"
            />
            <p class="text-red-500 text-xs italic" v-if="errors">{{ errors[0] }}</p>
          </ValidationProvider>
        </div>
        <div class="flex items-center justify-between">
          <a
            class="inline-block align-baseline font-bold text-sm text-pink-500 hover:text-pink-800"
            href="#"
          >Forgot Password?</a>
          <button
            class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            v-bind:class="{ 'opacity-50 cursor-not-allowed': invalid }"
            type="submit"
            :disabled="invalid"
          >Sign In</button>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script>
import { ValidationProvider, ValidationObserver } from "vee-validate";
import { required, email } from "vee-validate/dist/rules";
import { extend } from "vee-validate";
import { mapGetters } from "vuex";

extend("email", {
  ...email,
  message: "Enter a valid email"
});

extend("required", {
  ...required,
  message: "This field is required"
});

extend("passwordsize", value => {
  if (value.length >= 8) return true;

  return "Enter at least 8 characters";
});

export default {
  name: "login",
  components: {
    ValidationProvider,
    ValidationObserver,
    Alert: () => import("@/components/Alert.vue")
  },
  data: () => ({
    credentials: {
      username: "",
      passsword: ""
    }
  }),
  methods: {
    onSubmit() {
      this.$store.dispatch('auth/login',this.credentials);
    }
  },
  computed: {
    ...mapGetters("alert", ["alertType", "alertMessage", "alertColor"])
  }
};
</script>

