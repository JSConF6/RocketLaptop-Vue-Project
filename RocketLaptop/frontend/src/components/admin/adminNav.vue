<template>
  <header>
    <div
      class="d-flex flex-column flex-shrink-0 p-3 bg-light vh-100 shadow-lg fixed-top"
      style="width: 280px"
    >
      <a
        href="/main"
        class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
      >
        <MainLogo
          width="100%"
          height="100%"
          viewBox="0 0 850 350"
          preserveAspectRatio="none"
        />
      </a>
      <hr />
      <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
          <router-link
            to="/admin"
            :class="
              pageName === 'home'
                ? 'active nav-link link-dark fs-5 admin-link'
                : 'nav-link link-dark fs-5 admin-link'
            "
            id="home"
            @click="setPageName('home')"
          >
            <font-awesome-icon icon="house" />&nbsp;&nbsp;대시보드
          </router-link>
        </li>
        <li>
          <router-link
            to="/admin/notice/list"
            :class="
              pageName === 'notice'
                ? 'active nav-link link-dark fs-5 admin-link'
                : 'nav-link link-dark fs-5 admin-link'
            "
            id="notice"
            @click="setPageName('notice')"
          >
            <font-awesome-icon icon="clipboard" />&nbsp;&nbsp;공지사항
          </router-link>
        </li>
        <li>
          <router-link
            to="/admin/question/list"
            :class="
              pageName === 'question'
                ? 'active nav-link link-dark fs-5 admin-link'
                : 'nav-link link-dark fs-5 admin-link'
            "
            id="question"
            @click="setPageName('question')"
          >
            <font-awesome-icon icon="question" />&nbsp;&nbsp;문의사항
          </router-link>
        </li>
        <li>
          <router-link
            to="/admin/order/list"
            :class="
              pageName === 'order'
                ? 'active nav-link link-dark fs-5 admin-link'
                : 'nav-link link-dark fs-5 admin-link'
            "
            id="order"
            @click="setPageName('order')"
          >
            <font-awesome-icon icon="calculator" />&nbsp;&nbsp;주문목록
          </router-link>
        </li>
        <li>
          <router-link
            to="/admin/product/list"
            :class="
              pageName === 'product'
                ? 'active nav-link link-dark fs-5 admin-link'
                : 'nav-link link-dark fs-5 admin-link'
            "
            id="product"
            @click="setPageName('product')"
          >
            <font-awesome-icon icon="boxes-stacked" />&nbsp;&nbsp;상품목록
          </router-link>
        </li>
        <li>
          <router-link
            to="/admin/user/list"
            :class="
              pageName === 'user'
                ? 'active nav-link link-dark fs-5 admin-link'
                : 'nav-link link-dark fs-5 admin-link'
            "
            id="user"
            @click="setPageName('user')"
          >
            <font-awesome-icon icon="circle-user" />&nbsp;&nbsp;유저목록
          </router-link>
        </li>
      </ul>
      <hr />
      <div class="dropdown">
        <a
          href="#"
          class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle fs-5"
          id="dropdownUser2"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          <font-awesome-icon icon="user-gear" />&nbsp;&nbsp;
          <strong>관리자</strong>
        </a>
        <ul
          class="dropdown-menu text-small shadow"
          aria-labelledby="dropdownUser2"
        >
          <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
        </ul>
      </div>
    </div>
  </header>
</template>

<script>
import { onBeforeMount, ref } from "vue";

export default {
  name: "adminSidebarView",
  setup() {
    const pageName = ref("home");

    onBeforeMount(() => {
      pageName.value = sessionStorage.getItem("page");
    });

    const setPageName = (page) => {
      pageName.value = page;
      sessionStorage.setItem("page", pageName.value);
    };

    return {
      pageName,
      setPageName,
    };
  },
};
</script>

<style></style>
