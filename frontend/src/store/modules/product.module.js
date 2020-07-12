import { productService } from '../../services/product.service';
import router from '../../router';

const state = {
	products: null,
	populars: null,
	userProducts: null
};

const getters = {
	products: (state) => (state.products == null || state.products.length == 0) ? null : state.products,
	populars: (state) => (state.populars == null || state.populars.length == 0) ? null : state.populars,
	userProducts: (state) => (state.userProducts == null || state.userProducts.length == 0) ? null : state.userProducts,
};

const actions = {
	getProductsForHome({ dispatch }){
		dispatch('getPopularsProducts')
		dispatch('getProducts')
	},
	async getPopularsProducts({ commit, dispatch }) {
		try {
			const products = await productService.getPopularsProducts();
			if (products && products.length > 0) {
				commit('SET_POPULARS', products);
			}
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async getProducts({ commit, dispatch }) {
		try {
			const products = await productService.getProducts();
			if (products && products.length > 0) {
				commit('SET_PRODUCTS', products);
			}
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async deleteProduct({ commit, dispatch },id) {
		try {
			await productService.deleteProduct(id);
			commit('DELETE_PRODUCT', id);			
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async getUserProducts({ commit, dispatch }) {
		try {
			const products = await productService.getUserProducts();
			if (products && products.length > 0) {
				commit('SET_USER_PRODUCTS', products);
			}
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async createProduct({ commit,dispatch }, product) {
		try {
			const p = await productService.createProduct(product);
			commit('ADD_PRODUCT', p)
			dispatch('alert/success', "Product created successfull", { root: true });
			router.push('/app/dashboard')
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async editProduct({ dispatch }, product) {
		try {
			await productService.editProduct(product);
			dispatch('alert/success', "Product updated successfull", { root: true });
			router.push('/app/dashboard')
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async getProduct({ dispatch }, id) {
		try {
			const p = await productService.getProduct(id);
			return p;
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async getPublicProduct({ dispatch }, id) {
		try {
			const product = await productService.getPublicProduct(id);
			if(product){
                return product
			}
			router.push("/public-error");
		} catch (error) {
			handleError(error,dispatch)
		}
	},
	async addProductView({ dispatch }, id) {
		try {
			await productService.addProductView(id);
		} catch (error) {
			handleError(error,dispatch)
		}
	}
};

const mutations = {
	SET_PRODUCTS(state, products) {
		state.products = products;
	},
	SET_POPULARS(state, products) {
		state.populars = products;
	},
	SET_USER_PRODUCTS(state, products) {
		state.userProducts = products;
	},
	ADD_PRODUCT(state, product) {
		state.userProducts.push(product);
	},
	DELETE_PRODUCT(state, id) {
		const criteria = (it) => it.id == id;
		const index = state.userProducts.findIndex(criteria);
		state.userProducts.splice(index, 1)
	}
};

function handleError(error, dispatch){
	JSON.stringify('handleError->'+error)

    if (!error.response) {
        dispatch('alert/error', "Check your connection to the server", { root: true });
	} else {
		const msg = error.response.data.message
		const err = error.response.data.error

		if(error.message === "Network Error" || msg === "Network Error"){
			dispatch('alert/error', "Check your internet connection", { root: true });
		} else if(err === "InvalidAuthTokenException"){
				dispatch('auth/logout', null,{ root: true }) 
				setTimeout(()=> {
					router.push({ path: '/login' })
				},2000)
		} else if (msg && typeof(msg) !== 'string'){
            dispatch('alert/error', `${msg[0]}.`, { root: true });
		} else if(msg){
			dispatch('alert/error', msg, { root: true });
		}
    }
}
export const product = {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
};
