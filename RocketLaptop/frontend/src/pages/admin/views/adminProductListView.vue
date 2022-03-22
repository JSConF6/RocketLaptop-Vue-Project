<template>
  <ProductList :productList="productList" :listCount="listCount" />

  <Paging :pageList="pageList" :pageHandler="pageHandler" @getList="getList" />
</template>

<script>
import ProductList from "@/components/admin/adminProductList.vue";
import Paging from "@/components/paging.vue";
import { ref } from "vue";
import axios from "@/axios/axiosSetting";
import { useRouter, useRoute } from "vue-router";
import Swal from "sweetalert2";

export default {
  name: "adminProductListView",
  components: { ProductList, Paging },
  setup() {
    const router = useRouter();
    const page = useRoute().params.page;

    const productList = ref([]);
    let limit = 4;
    let listCount = ref(0);
    const currentPage = ref(1);
    let pageList = ref([]);
    const pageHandler = ref({});

    const getList = async (page = currentPage.value) => {
      try {
        const res = await axios.get(
          `/api/admin/product/list?page=${page}&limit=${limit}`
        );
        console.log(res.data);
        productList.value = res.data.body.productList;
        listCount.value = res.data.body.pageHandler.listCount;
        pageHandler.value = res.data.body.pageHandler;

        pageList.value = [];
        for (
          let i = res.data.body.pageHandler.startPage;
          i <= res.data.body.pageHandler.endPage;
          i++
        ) {
          pageList.value.push(i);
        }
      } catch (error) {
        console.log(error);
      }
    };
    getList();

    if (page === "productAdd") {
      console.log("PreviousPage : " + page);
      Swal.fire({
        icon: "success",
        title: "상품등록",
        text: "상품등록 완료",
        allowOutsideClick: false,
      }).then(() => {
        router.go();
      });
    }

    return {
      getList,
      pageList,
      currentPage,
      productList,
      pageHandler,
      listCount,
    };
  },
};
</script>

<style scoped>
.product-title a {
  color: black;
  text-decoration: none;
}
</style>
