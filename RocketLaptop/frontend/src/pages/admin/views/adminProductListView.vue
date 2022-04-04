<template>
  <ProductList :productList="productList" :listCount="listCount" />

  <Paging :pageList="pageList" :pageHandler="pageHandler" @getList="getList" />
</template>

<script>
import ProductList from "@/components/admin/adminProductList.vue";
import Paging from "@/components/paging.vue";
import { ref } from "vue";
import axios from "@/axios/axiosSetting";

export default {
  name: "adminProductListView",
  components: { ProductList, Paging },
  setup() {
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
        productList.value = res.data.data.productList;
        listCount.value = res.data.data.pageHandler.listCount;
        pageHandler.value = res.data.data.pageHandler;

        pageList.value = [];
        for (
          let i = res.data.data.pageHandler.startPage;
          i <= res.data.data.pageHandler.endPage;
          i++
        ) {
          pageList.value.push(i);
        }
      } catch (error) {
        console.log(error);
      }
    };
    getList();

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
