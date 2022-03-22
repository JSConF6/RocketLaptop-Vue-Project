<template>
  <nav class="navbar navbar-light bg-light justify-content-between ps-0 pe-0">
    <div class="container-fluid pe-0">
      <router-link
        to="/main"
        class="navbar-brand text-black"
        style="width: 210px"
      >
        <MainLogo
          width="100%"
          height="100%"
          viewBox="0 0 900 350"
          preserveAspectRatio="none"
        />
      </router-link>
      <div class="input-group" id="search-bar">
        <button
          class="btn btn-outline-secondary dropdown-toggle"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          <span>전체</span>
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#">전체</a></li>
          <li><a class="dropdown-item" href="#">상품명</a></li>
          <li><a class="dropdown-item" href="#">카테고리명</a></li>
        </ul>
        <input
          type="text"
          class="form-control"
          aria-label="Text input with 2 dropdown buttons"
        />
        <button class="btn btn-outline-secondary" type="button">
          <router-link to="/search"
            ><font-awesome-icon icon="magnifying-glass" size="lg"
          /></router-link>
        </button>
      </div>
      <ul class="nav">
        <li class="nav-item position-relative">
          <router-link
            class="nav-link d-flex flex-column align-items-center text-secondary"
            id="cart-icon"
            to="/main/user/cart"
          >
            <font-awesome-icon icon="cart-shopping" size="2x" />
            <span
              class="position-absolute top-5 translate-middle badge rounded-pill bg-danger"
              id="cart-badge"
            >
              0
              <span class="visually-hidden">unread messages</span>
            </span>
            <span class="fs-5 cart-icon-title">장바구니</span>
          </router-link>
        </li>
        <li v-if="!user.user_name" class="nav-item">
          <router-link
            class="nav-link d-flex flex-column align-items-center text-secondary"
            id="cuser-icon"
            to="/main/loginView"
          >
            <font-awesome-icon icon="circle-user" size="2x" />
            <span class="fs-5 cuser-icon-title">로그인</span>
          </router-link>
        </li>
        <div v-if="user.user_role === 'ROLE_ADMIN'" class="d-flex">
          <li class="nav-item">
            <a
              class="nav-link d-flex flex-column align-items-center text-secondary"
              id="mypage-icon"
              href="/admin"
            >
              <font-awesome-icon icon="circle-user" size="2x" />
              <span class="fs-5 mypage-icon-title">관리자</span>
            </a>
          </li>
          <li class="nav-item">
            <a
              class="nav-link d-flex flex-column align-items-center text-secondary"
              id="cuser-icon"
              @click="logoutProcess"
              href="#"
            >
              <font-awesome-icon icon="right-from-bracket" size="2x" />
              <span class="fs-5 cuser-icon-title">로그아웃</span>
            </a>
          </li>
        </div>
        <div v-if="user.user_role === 'ROLE_USER'" class="d-flex">
          <li class="nav-item">
            <router-link
              class="nav-link d-flex flex-column align-items-center text-secondary"
              id="mypage-icon"
              to="/main/user/mypage"
            >
              <font-awesome-icon icon="circle-user" size="2x" />
              <span class="fs-5 mypage-icon-title">마이페이지</span>
            </router-link>
          </li>
          <li class="nav-item">
            <a
              class="nav-link d-flex flex-column align-items-center text-secondary"
              id="cuser-icon"
              @click="logoutProcess"
              href="#"
            >
              <font-awesome-icon icon="right-from-bracket" size="2x" />
              <span class="fs-5 cuser-icon-title">로그아웃</span>
            </a>
          </li>
        </div>
        <li class="nav-item" v-if="!user.user_name">
          <router-link
            class="nav-link d-flex flex-column align-items-center text-secondary"
            id="puser-icon"
            to="/main/register"
          >
            <font-awesome-icon icon="user-plus" size="2x" />
            <span class="fs-5 puser-icon-title">회원가입</span>
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
  <div
    class="navbar navbar-expand-lg navbar-light bg-light border-top border-bottom border-3"
  >
    <div class="container-fluid">
      <a href="#" class="navbar-brand"></a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div
        class="collapse navbar-collapse justify-content-center"
        id="navbarNav"
      >
        <ul class="nav justify-content-center align-items-center">
          <li class="nav-item me-3">
            <div class="dropdown">
              <button
                class="btn text-secondary d-flex align-items-center"
                type="button"
                id="dropdownMenu2"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <font-awesome-icon icon="bars" size="2x" />
                <span class="fs-4">&nbsp;&nbsp;카테고리</span>
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                <li>
                  <button class="dropdown-item" type="button">
                    <router-link to="/main/category">전체</router-link>
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button">
                    <router-link to="/main/category">노트북</router-link>
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button">
                    <router-link to="/main/category">스마트폰</router-link>
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button">
                    <router-link to="/main/category">카메라</router-link>
                  </button>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link me-3 text-secondary fs-4"
              to="/main/bestproduct"
              >베스트상품</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link me-3 text-secondary fs-4"
              to="/main/newproduct"
              >신규상품</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link me-3 text-secondary fs-4"
              to="/main/notice"
              >공지사항</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link me-3 text-secondary fs-4"
              to="/main/question"
              >문의사항</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import { useStore } from "vuex";
import axios from "@/axios/axiosSetting";

export default {
  name: "MainNav",
  componente: {},
  setup() {
    const router = useRouter();

    const store = useStore();

    let user = store.getters["userStore/getUser"];

    const logoutProcess = async () => {
      try {
        const res = await axios.post("/api/logout");
        if (res.data.status === 200) {
          Swal.fire({
            title: "로그아웃",
            html: "로그아웃 완료.",
            icon: "error",
            allowOutsideClick: false,
          });
          store.dispatch("userStore/logout");
          router.push({ name: "LoginView" });
        }
      } catch (error) {
        console.log(error);
      }
    };

    return {
      logoutProcess,
      user,
    };
  },
};
</script>

<style scoped>
a {
  color: gray;
  text-decoration: none;
}
</style>
