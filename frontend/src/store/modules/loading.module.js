const state = {
    loading: false,
};

const actions = {
    hide({ commit }) {
        commit('HIDE');
    },
    show({ commit }) {
        commit('SHOW');
    }
};

const mutations = {
    HIDE(state) {
        state.loading = false;
    },
    SHOW(state) {
        state.loading = true;
    }
};

const getters = {
    loadingStatus : state => state.loading
};

export const loading = {
    namespaced: true,
    state,
    actions,
    getters,
    mutations
};