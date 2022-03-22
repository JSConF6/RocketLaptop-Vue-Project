import NewProductView from "@/pages/main/views/product/NewProductView.vue";
import BestProductView from "@/pages/main/views/product/BestProductView.vue";
import ProductDetailView from "@/pages/main/views/product/ProductDetailView.vue";
import CategoryView from "@/pages/main/views/product/CategoryView.vue";
import SearchView from "@/pages/main/views/product/SearchView.vue";

export const ProductRouters = [
  {
    path: "/main/newproduct",
    name: "NewProductView",
    component: NewProductView,
    meta: { title: "RocketLaptop - 새로운 상품" },
  },
  {
    path: "/main/bestproduct",
    name: "BestProductView",
    component: BestProductView,
    meta: { title: "RocketLaptop - 베스트 상품" },
  },
  {
    path: "/main/productdetail",
    name: "ProductDetailView",
    component: ProductDetailView,
    meta: { title: "RocketLaptop - 상품 상세정보" },
  },
  {
    path: "/main/search",
    name: "SearchView",
    component: SearchView,
    meta: { title: "RocketLaptop - 상품 검색" },
  },
  {
    path: "/main/category",
    name: "CategoryView",
    component: CategoryView,
    meta: { title: "RocketLaptop - 상품 카테고리" },
  },
];
