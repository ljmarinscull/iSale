import { userService } from '../../services/user.service';
import router from '../../router';

const state = {
};

const getters = {
}

const actions = {
    async updateUserProfile({ dispatch }, user) {
        try {
            const newUser = await userService.updateUserProfile(user)
            dispatch('alert/success', "Success update", { root: true });
            dispatch('auth/updateUser', newUser, { root: true });
            return newUser
        } catch (err) {
            handleError(err, dispatch)
        }
    }
};

function handleError(error, dispatch){
    if (!error.response) {
        dispatch('alert/error', "Check your connection to the server", { root: true });
	} else {
		const msg = error.response.data.message
		const err = error.response.data.error

		if(error.message === "Network Error" || msg === "Network Error"){
			dispatch('alert/error', "Check your internet connection", { root: true });
		} else if(err === "InvalidAuthTokenException"){
				dispatch('auth/logout',null,{ root: true }) 
				setTimeout(()=> {
					router.push({ path: '/login' })
				},2000)
		} else if (msg && typeof(msg) !== 'string'){
            dispatch('alert/error', `${msg[0]}.`, { root: true });
		} else {
            dispatch('alert/error', msg, { root: true });
        }
    }
}


export const user = {
    namespaced: true,
    state,
    getters,
    actions,
};