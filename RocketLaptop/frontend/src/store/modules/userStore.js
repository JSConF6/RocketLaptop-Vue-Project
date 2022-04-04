export const userStore = {
  namespaced: true,
  state: () => ({ user_id: "", user_name: "", user_role: "" }),
  getters: {
    getUser(state) {
      return state;
    },
  },
  mutations: {
    setUser(state, value) {
      console.log("userStore : setUser");
      state.user_id = value.user_id;
      state.user_name = value.user_name;
      state.user_role = value.user_role;
      console.log(state);
    },
    clearUser(state) {
      console.log(state);
      state.user_id = "";
      state.user_name = "";
      state.user_role = "";
    },
  },
  actions: {
    login({ commit }, value) {
      console.log("userStore : login");
      commit("setUser", value);
    },
    logout({ commit }) {
      console.log("userStore : logout");
      commit("clearUser");
    },
  },
};
