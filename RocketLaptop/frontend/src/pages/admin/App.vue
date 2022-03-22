<template>
  <adminNav />

  <router-view></router-view>

  <adminFooter />
</template>

<script>
import adminNav from "@/components/admin/adminNav.vue";
import adminFooter from "@/components/admin/adminFooter.vue";
export default {
  name: "App",
  components: { adminNav, adminFooter },
  setup() {},
};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}

main,
footer {
  margin-left: 285px;
}

a {
  color: black;
  text-decoration: none;
}

@media (max-width: 710px) {
  header {
    display: none;
  }
  main,
  footer {
    margin-left: 0px;
  }
}
</style>
