import CartView from "@/pages/main/views/user/CartView.vue";
import MyPageView from "@/pages/main/views/user/MyPageView.vue";

export const UserRouters = [
  {
    path: "/main/user/cart",
    name: "CartView",
    component: CartView,
    meta: { title: "RocketLaptop - 장바구니" },
  },
  {
    path: "/main/user/mypage",
    name: "MyPageView",
    component: MyPageView,
    meta: { title: "RocketLaptop - 마이페이지" },
  },
];
