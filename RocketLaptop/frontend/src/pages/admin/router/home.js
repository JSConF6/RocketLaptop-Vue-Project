import adminMainView from "@/pages/admin/views/adminMainView";
import adminNoticeListView from "@/pages/admin/views/adminNoticeListView";
import adminOrderListView from "@/pages/admin/views/adminOrderListView";
import adminProductListView from "@/pages/admin/views/adminProductListView";
import adminQuestionListView from "@/pages/admin/views/adminQuestionListView";
import adminUserListView from "@/pages/admin/views/adminUserListView";
import adminProductAddView from "@/pages/admin/views/adminProductAddView";
import adminProductDetailView from "@/pages/admin/views/adminProductDetailView";
import adminProducModifyView from "@/pages/admin/views/adminProductModifyView";

export const HomeRouters = [
  {
    path: "/admin",
    name: "adminMainView",
    component: adminMainView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/notice/list",
    name: "adminNoticeListView",
    component: adminNoticeListView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/question/list",
    name: "adminQuestionListView",
    component: adminQuestionListView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/order/list",
    name: "adminOrderListView",
    component: adminOrderListView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/product/list",
    name: "adminProductListView",
    component: adminProductListView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/product/detail/:product_code",
    name: "adminProductDetailView",
    component: adminProductDetailView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/product/add",
    name: "adminProductAddView",
    component: adminProductAddView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/product/modify/:product_code",
    name: "adminProducModifyView",
    component: adminProducModifyView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
  {
    path: "/admin/user/list",
    name: "adminUserListView",
    component: adminUserListView,
    meta: { title: "RocketLaptop - 관리자페이지" },
  },
];
