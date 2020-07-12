const state = {
    color: null,
    message: null,
    type: null
};

const actions = {
    success({ commit }, message) {
        commit('SUCCESS', message);
        setTimeout(()=>
        {
            commit('CLEAR');
        },5000);
    },
    error({ commit }, message) {
        commit('ERROR', message);
        setTimeout(()=>
        {
            commit('CLEAR');
        },5000);
    },
    clear({ commit }) {
        commit('CLEAR');
    }
};

const mutations = {
    SUCCESS(state, message) {
        state.type = 'Success: ';
        state.message = message;
        state.color = 'green'
    },
    ERROR(state, message) {
        state.type = 'Error: ';
        state.message = message;
        state.color = 'red'
    },
    CLEAR(state) {
        state.type = null;
        state.message = null;
        state.color = null;
    }
};

const getters = {
    alertType : state => state.type,
    alertMessage : state => state.message,
    alertColor: state => state.color,
};

export const alert = {
    namespaced: true,
    state,
    actions,
    getters,
    mutations
};