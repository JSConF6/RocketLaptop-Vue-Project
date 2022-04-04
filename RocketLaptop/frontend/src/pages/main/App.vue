<template>
  <Nav />

  <router-view @getSession="getSession"></router-view>

  <Footer />
</template>

<script>
import Footer from "@/components/Footer.vue";
import Nav from "@/components/Nav.vue";
import axios from "@/axios/axiosSetting";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";

export default {
  components: { Nav, Footer },
  setup() {
    const store = useStore();
    const router = useRouter();

    const getSession = async (value) => {
      if (value === "") {
        try {
          const res = await axios.post("/api/logout");
          if (res.data.status === 200) {
            store.dispatch("userStore/logout");
            router.push({ name: "LoginView" });
          }
        } catch (error) {
          console.log(error);
        }
      } else {
        try {
          const res = await axios.get("/api/getSession");
          console.log(res.data);
          if (res.data.status === 200) {
            store.dispatch("userStore/login", res.data.data);
          } else {
            console.log("사용자 정보 조회 실패");
            Swal.fire({
              icon: "error",
              title: "세션 만료",
              text: "세션이 만료되었습니다. 다시 로그인 해주세요.",
              allowOutsideClick: false,
            });
            store.dispatch("userStore/logout");
            router.push({ name: "LoginView" });
          }
        } catch (error) {
          console.log(error);
        }
      }
    };

    return {
      getSession,
    };
  },
};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

@media (max-width: 992px) {
  #cart-icon > .cart-icon-title,
  #cuser-icon > .cuser-icon-title,
  #puser-icon > .puser-icon-title,
  #admin-icon > .admin-icon-title,
  #mypage-icon > .mypage-icon-title {
    display: none;
  }

  #cart-icon > #cart-badge {
    left: 55px;
  }
}

#search-bar {
  width: 50%;
}

@media (max-width: 1270px) {
  #search-bar {
    width: 40%;
  }
}

@media (max-width: 750px) {
  #search-bar {
    display: none;
  }
}

.dropdown:hover .dropdown-menu {
  display: block;
  margin-top: 0;
}

#cart-badge {
  left: 75px;
}
</style>
