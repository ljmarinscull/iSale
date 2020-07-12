import Vue from 'vue'
import Vuex from 'vuex';
import { auth } from './modules/auth.module';
import { user } from './modules/user.module';
import { alert } from './modules/alert.module';
import { loading } from './modules/loading.module';
import { product } from './modules/product.module';

Vue.use(Vuex)

export const store = new Vuex.Store({
    modules: {
        auth,
        user,
        product,
        alert,
        loading,
    }
});

export default store