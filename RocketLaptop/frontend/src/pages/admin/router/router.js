import { createRouter, createWebHistory } from "vue-router";
import { nextTick } from "vue";
import routes from "@/pages/admin/router/routes";
import Swal from "sweetalert2";
import axios from "@/axios/axiosSetting";

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach(function (to, from, next) {
  const authorityCheck = async () => {
    const res = await axios.get("/api/admin/authorityCheck");
    if (res.data.status === 200) {
      console.log(res);
      next();
    } else {
      Swal.fire({
        icon: "error",
        title: "권한 오류",
        text: "권한 있는 계정으로 로그인 해주세요",
        allowOutsideClick: false,
      }).then(() => {
        location.replace("http://localhost:8080/main/loginView");
      });
    }
  };
  authorityCheck();
});

router.afterEach((to) => {
  const title =
    to.matched[0].meta.title === undefined ? "RocketLaptop" : to.meta.title;
  nextTick(() => {
    document.title = title;
  });
});

export default router;
