import { authService } from '../../services/auth.service';
import router from '../../router';

const user = JSON.parse(localStorage.getItem('user'));
const state = user ? { status: { loggedIn: true }, user } : { status: {}, user: {} };

const getters = {
	user: (state) => state.user
};

const actions = {
	async login({ dispatch, commit }, { username, password }) {
		try {
			commit('LOGIN_REQUEST', username)
			
			const user = await authService.login(username, password);

			commit('LOGIN_SUCCESS', user);
			setTimeout(()=>	{
				router.push({ path: '/app/dashboard' })
			},1000);
			
		} catch (error) {
			handleError(error, commit, dispatch)
		}
	},
	async signup({ dispatch, commit }, user){
		try {
			await authService.signup(user);
			dispatch('alert/success', 'Successful registration', { root: true });
		} catch (error) {
			handleError(error, commit, dispatch)
		}
	},
	async updateProfile({ dispatch, commit }, user){
		try {
			await authService.updateProfile(user);
			dispatch('alert/success', 'Successful update', { root: true });
		} catch (error) {
			handleError(error, commit, dispatch)
		}
	},
	async logout({ commit }) {
		commit('LOGOUT');
	},
	async updateUser({ commit }, user) {
		commit('UPDATE_USER', user);
	}
};

const mutations = {
	LOGIN_REQUEST(state, user) {
		state.user.name = user;
	},
	UPDATE_USER(state, user) {
		state.user = user;
		localStorage.setItem('user',JSON.stringify(user))
	},
	LOGIN_SUCCESS(state, user) {
		state.status = { loggedIn: true };
		state.user = user;
	},
	LOGIN_FAILURE(state) {
		state.status = {};
		state.user = null;
	},
	LOGOUT(state) {
		state.status.loggedIn = false;
		state.user = null;
		localStorage.removeItem('user')
		localStorage.removeItem('token')
	}
};

function handleError(err, commit,dispatch){
    if (err.message === "Network Error") {
        dispatch('alert/error', "Revise su conexión a internet.", { root: true });
    } else {
		if(err.response){
			const message = err.response.data.message;
			commit('LOGIN_FAILURE', message);
			dispatch('alert/error', message, { root: true });
		} else{
			dispatch('alert/error', "An unexpected error has occurred", { root: true });
		}
    }
}

export const auth = {
	namespaced: true,
	state,
	actions,
	mutations,
	getters
};
