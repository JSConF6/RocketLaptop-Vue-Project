import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import { userStore } from "@/store/modules/userStore";

export default createStore({
  modules: { userStore },
  plugins: [createPersistedState()],
});
