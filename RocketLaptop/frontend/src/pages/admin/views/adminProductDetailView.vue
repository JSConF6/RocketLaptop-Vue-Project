<template>
  <main class="mt-5 vw-50">
    <div class="container-fluid">
      <div class="w-75 mx-auto">
        <div class="d-flex align-items-center mb-3">
          <MainLogo
            width="20%"
            height="30%"
            viewBox="0 0 850 350"
            preserveAspectRatio="none"
          />
          <span class="fs-1">상품 상세정보</span>
        </div>
        <form>
          <div class="mb-3">
            <label for="productCode" class="form-label">상품코드</label>
            <input
              type="text"
              class="form-control"
              id="productCode"
              :value="productDetail.product_code"
              disabled
            />
          </div>
          <div class="mb-3">
            <label for="category" class="form-label">카테고리</label>
            <select class="form-select" disabled>
              <option :value="productDetail.categoty_code">
                {{ productDetail.category_name }}
              </option>
            </select>
          </div>
          <div class="mb-3">
            <label for="productName" class="form-label">상품이름</label>
            <input
              type="text"
              class="form-control"
              id="productName"
              :value="productDetail.product_name"
              disabled
            />
          </div>
          <div class="mb-3">
            <label for="productPrice" class="form-label">상품가격</label>
            <input
              type="text"
              class="form-control"
              id="productPrice"
              :value="productDetail.product_price"
              disabled
            />
          </div>
          <div class="mb-3">
            <div class="mb-3 img-container">
              <label for="formFile" class="form-label fs-4">상세설명</label>
              <br />
              <span id="details_value">{{ original_name }}</span>
            </div>
          </div>
          <div class="row row-cols-1 row-cols-md-2 g-4">
            <div class="col" v-for="(img, index) in imgFiles" :key="index">
              <div
                class="card"
                v-if="img.product_img_type === 1 || img.product_img_type === 2"
              >
                <div class="card-body">
                  <h5 class="card-title" v-if="img.product_img_type === 1">
                    대표이미지
                  </h5>
                  <h5 class="card-title" v-if="img.product_img_type === 2">
                    상품이미지
                  </h5>
                  <img
                    :src="
                      require(`@/assets/imgFiles/upload${img.product_img_name}`)
                    "
                    class="card-img-top"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-end mb-3 mt-3">
            <router-link
              class="btn btn-secondary me-3"
              :to="`/admin/product/modify/${productDetail.product_code}`"
            >
              수정
            </router-link>
            <button class="btn btn-danger me-3" @click="productDeleteBtn">
              삭제
            </button>
            <button class="btn btn-primary" @click="productListBtn">
              목록
            </button>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import { ref } from "vue";
import { useRoute } from "vue-router";
import axios from "@/axios/axiosSetting";
import Swal from "sweetalert2";
import { useRouter } from "vue-router";

export default {
  name: "ProductDetailView",
  setup() {
    const router = useRouter();

    let productDetail = ref({});
    let imgFiles = ref([]);
    let original_name = ref("");

    const product_code = useRoute().params.product_code;

    const product_detail = async () => {
      const res = await axios.get(`/api/admin/product/detail/${product_code}`);
      if (res.data.status === 200) {
        console.log(res);
        productDetail.value = res.data.body.productDetail;
        imgFiles.value = res.data.body.fileList;
        for (let i = 0; i < imgFiles.value.length; i++) {
          if (imgFiles.value[i].product_img_type === 3) {
            original_name.value = imgFiles.value[i].product_img_original_name;
          }
        }
      } else {
        Swal.fire({
          icon: "warning",
          title: "상품 상세정보",
          text: "상품 정보를 가져오지 못했습니다.",
          allowOutsideClick: false,
        }).then(() => {
          router.push({ name: "adminProductListView" });
        });
      }
    };

    product_detail();

    const productListBtn = (event) => {
      event.preventDefault();
      router.go(-1);
    };

    const productDeleteBtn = (event) => {
      event.preventDefault();
      Swal.fire({
        title: "상품을 삭제하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#df2525",
        cancelButtonColor: "gray",
        confirmButtonText: "삭제",
        cancelButtonText: "닫기",
        allowOutsideClick: false,
      }).then((result) => {
        if (result.isConfirmed) {
          try {
            const productDelete = async () => {
              const res = await axios.delete(
                `/api/admin/product/delete/${product_code}`
              );
              if (res.data.status === 200) {
                Swal.fire({
                  icon: "success",
                  title: "상품삭제",
                  text: "상품 삭제 완료했습니다.",
                  allowOutsideClick: false,
                }).then(() => {
                  router.push({ name: "adminProductListView" });
                });
              } else {
                Swal.fire({
                  icon: "error",
                  title: "상품삭제",
                  text: "상품 삭제 실패했습니다.",
                  allowOutsideClick: false,
                }).then(() => {
                  router.go();
                });
              }
            };
            productDelete();
          } catch (error) {
            console.log(error);
          }
        }
      });
    };

    return {
      productDetail,
      imgFiles,
      productListBtn,
      productDeleteBtn,
      original_name,
    };
  },
};
</script>

<style scoped>
input[type="file"] {
  display: none;
}

.img-container {
  border: 1px solid #ced4da;
  padding: 16px;
  border-radius: 5px;
}

.product_img {
  width: 100%;
  height: 100%;
}
</style>
