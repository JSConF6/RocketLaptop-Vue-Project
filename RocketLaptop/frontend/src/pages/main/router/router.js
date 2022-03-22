import { createRouter, createWebHistory } from "vue-router";
import { nextTick } from "vue";
import routes from "@/pages/main/router/routes";

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.afterEach((to) => {
  const title =
    to.matched[0].meta.title === undefined ? "RocketLaptop" : to.meta.title;
  nextTick(() => {
    document.title = title;
  });
});

export default router;
