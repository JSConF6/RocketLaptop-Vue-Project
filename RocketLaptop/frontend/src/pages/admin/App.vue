<template>
  <adminNav />

  <router-view></router-view>

  <adminFooter />
</template>

<script>
import adminNav from "@/components/admin/adminNav.vue";
import adminFooter from "@/components/admin/adminFooter.vue";
import { provide } from "vue";
export default {
  name: "App",
  components: { adminNav, adminFooter },
  setup() {
    const priceComma = (price) => {
      return String(price).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

    const productRegDate = (date) => {
      return date.substring(0, 10);
    };

    provide("priceComma", priceComma);
    provide("productRegDate", productRegDate);
  },
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
