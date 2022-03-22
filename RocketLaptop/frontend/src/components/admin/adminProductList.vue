<template>
  <main class="mt-5 vw-50">
    <div class="container-fluid">
      <div class="d-flex align-items-center">
        <MainLogo
          width="20%"
          height="30%"
          viewBox="0 0 850 350"
          preserveAspectRatio="none"
        />
        <span class="fs-1">상품목록</span>
      </div>
      <p class="mt-3 d-flex justify-content-between align-items-center">
        <span class="fs-5">상품 갯수 : {{ listCount }}개</span>
        <router-link class="btn btn-primary" to="/admin/product/add"
          >상품등록</router-link
        >
      </p>
      <div
        v-show="listCount > 0"
        class="row row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-1 row-cols-xl-2 g-4"
      >
        <div class="col-6" v-for="(item, index) in productList" :key="index">
          <div class="card mb-3">
            <div class="row g-0">
              <div class="col-md-4">
                <img
                  :src="
                    require(`@/assets/imgFiles/upload${item.product_img_name}`)
                  "
                  class="img-fluid rounded-start w-100 h-100"
                />
              </div>
              <div class="col-md-8">
                <div
                  class="card-body w-100 h-100 d-flex flex-column justify-content-center"
                >
                  <h3 class="card-title mt-3 mb-3 product-title">
                    <router-link to="#">{{ item.product_name }}</router-link>
                  </h3>
                  <p class="card-text">
                    카테고리 : <span class="badge bg-dark">노트북</span>&nbsp;
                    <span class="badge bg-dark">{{ item.category_name }} </span>
                  </p>
                  <p class="card-text">
                    상품가격 : {{ item.product_price }} 원
                  </p>
                  <p class="card-text">
                    상품 등록일 : {{ item.product_reg_date }}
                  </p>
                  <p class="card-text text-end">
                    <a class="btn btn-primary" href="#">상세보기</a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-show="listCount === 0" class="mb-5">
        <h1 class="text-center">등록된 상품이 없습니다.</h1>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: "adminProductList",

  props: {
    productList: {
      type: Array,
      required: true,
    },
    listCount: {
      type: Number,
      required: true,
    },
  },

  setup() {},
};
</script>

<style scoped>
.product-title a {
  color: black;
  text-decoration: none;
}
</style>
