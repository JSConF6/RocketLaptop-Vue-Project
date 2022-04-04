<template>
  <div class="container-fluid">
    <div class="row">
      <div class="login-container mx-auto">
        <div class="mx-auto login-form">
          <MainLogo
            width="40%"
            height="20%"
            viewBox="0 0 850 350"
            preserveAspectRatio="none"
            class="mt-3 me-5"
          />
          <h1 class="text-start">Login</h1>
          <form name="loginform" @submit.prevent="loginProcess">
            <div class="row mb-3">
              <div class="col-sm-12">
                <input
                  type="text"
                  class="form-control border-top-0 border-start-0 border-end-0"
                  v-model="user.id"
                  placeholder="ID"
                />
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-sm-12">
                <input
                  type="password"
                  class="form-control border-top-0 border-start-0 border-end-0"
                  v-model="user.password"
                  placeholder="Password"
                />
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-sm-12">
                <div class="d-flex justify-content-between">
                  <div>
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="remember"
                      v-model="user.remeber"
                    />
                    <label class="form-check-label" for="remember"
                      >&nbsp;아이디 저장</label
                    >
                  </div>
                </div>
              </div>
            </div>
            <button type="submit" class="btn btn-dark rounded-pill w-75 fs-5">
              Login
            </button>
            <div class="mt-2">
              <router-link to="/main/register">회원가입</router-link>
              &nbsp;/&nbsp;
              <router-link to="/main/findpwd">비밀번호 찾기</router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "@/axios/axiosSetting";
import Swal from "sweetalert2";
import { useRouter } from "vue-router";
import cookies from "vue-cookies";
import { useStore } from "vuex";

export default {
  name: "LoginView",
  emits: ["getSession"],
  setup(props, context) {
    context.emit("getSession", "");

    const router = useRouter();

    const store = useStore();

    let user = reactive({
      id: "",
      password: "",
      remeber: false,
    });

    const getCookie = () => {
      const cookie_value = cookies.get("saveid");
      console.log("가져온 쿠키값 = " + cookie_value);
      if (cookie_value != null) {
        user.remeber = true;
        user.id = cookie_value;
      } else {
        user.remeber = false;
      }
    };

    getCookie();

    const loginProcess = async () => {
      const frm = new FormData();
      frm.append("user_id", user.id);
      frm.append("user_password", user.password);
      try {
        const res = await axios.post("/api/login", frm);
        console.log(res);
        if (res.data.status === 200) {
          console.log("remeber = " + user.remeber);
          if (user.remeber) {
            cookies.set("saveid", user.id, "1d");
          } else {
            cookies.remove("saveid");
          }
          console.log(res.data);
          store.dispatch("userStore/login", res.data.data);
          router.push({ name: "MainView" });
        } else {
          Swal.fire({
            icon: "error",
            title: "로그인",
            text: "아이디 또는 패스워드를 확인해주세요.",
            allowOutsideClick: false,
          });
          user.id = "";
          user.password = "";
        }
      } catch (error) {
        console.log(error);
      }
    };

    return {
      user,
      loginProcess,
    };
  },
};
</script>

<style scoped>
a {
  color: gray;
  text-decoration: none;
}

.login-form {
  height: 500px;
  width: 45%;
  margin-top: 5%;
  margin-bottom: 10%;
}

.login-container {
  width: 1200px;
}
</style>
