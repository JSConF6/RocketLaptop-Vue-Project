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
          <span class="fs-1">상품등록</span>
        </div>
        <form class="product_add_form" @submit.prevent="productAdd">
          <div class="mb-3">
            <label for="productCode" class="form-label">상품코드</label>
            <input
              type="text"
              class="form-control"
              id="productCode"
              placeholder="상품코드를 입력하세요"
              :value="product.product_code"
              disabled
            />
          </div>
          <div class="mb-3">
            <label for="category" class="form-label">카테고리</label>
            <select class="form-select" disabled>
              <option>{{ product.category_name }}</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="productName" class="form-label">상품이름</label>
            <input
              type="text"
              class="form-control"
              id="productName"
              placeholder="상품이름을 입력하세요"
              v-model="product.product_name"
            />
          </div>
          <div class="mb-3">
            <label for="productPrice" class="form-label">상품가격</label>
            <input
              type="text"
              class="form-control"
              id="productPrice"
              pattern="[0-9]+"
              placeholder="상품가격을 숫자로 입력하세요"
              v-model="product.product_price"
            />
          </div>
          <div class="mb-3 img-container">
            <label for="formFile" class="form-label fs-4">대표 이미지</label
            ><br />
            <button
              class="btn btn-primary image_btn me-2"
              value="thumbnail"
              id="thumbnail_btn"
              @click="upload"
            >
              이미지 첨부
            </button>
            <input
              class="form-control thumbnail_upload"
              type="file"
              ref="thumbnail_upload"
              @change="img_change"
              accept="image/*"
            />
            <span id="thumbnail_value">{{
              imgFiles[0].product_img_original_name
            }}</span>
            <img
              src="@/assets/imgFiles/image/remove.png"
              alt="이미지삭제"
              class="thumbnail_remove image_remove"
              v-show="thumbnail_remove"
              @click="remove"
            />
          </div>
          <div class="mb-3 img-container">
            <label for="formFile" class="form-label fs-4">상품 이미지1</label>
            <br />
            <button
              class="btn btn-primary image_btn me-2"
              value="image1"
              id="image1_btn"
              @click="upload"
            >
              이미지 첨부
            </button>
            <input
              class="form-control image1_upload"
              type="file"
              ref="image1_upload"
              @change="img_change"
              accept="image/*"
            />
            <span id="image1_value">{{
              imgFiles[1].product_img_original_name
            }}</span>
            <img
              src="@/assets/imgFiles/image/remove.png"
              alt="이미지삭제"
              class="image1_remove image_remove"
              v-show="image1_remove"
              @click="remove"
            />
          </div>
          <div class="mb-3 img-container">
            <label for="formFile" class="form-label fs-4">상품 이미지2</label>
            <br />
            <button
              class="btn btn-primary image_btn me-2"
              value="image2"
              id="image2_btn"
              @click="upload"
            >
              이미지 첨부
            </button>
            <input
              class="form-control image2_upload"
              type="file"
              ref="image2_upload"
              @change="img_change"
              accept="image/*"
            />
            <span id="image2_value">{{
              imgFiles[2].product_img_original_name
            }}</span>
            <img
              src="@/assets/imgFiles/image/remove.png"
              alt="이미지삭제"
              class="image2_remove image_remove"
              v-show="image2_remove"
              @click="remove"
            />
          </div>
          <div class="mb-3 img-container">
            <label for="formFile" class="form-label fs-4">상품 이미지3</label>
            <br />
            <button
              class="btn btn-primary image_btn me-2"
              value="image3"
              id="image3_btn"
              @click="upload"
            >
              이미지 첨부
            </button>
            <input
              class="form-control image3_upload"
              type="file"
              ref="image3_upload"
              @change="img_change"
              accept="image/*"
            />
            <span id="image3_value">{{
              imgFiles[3].product_img_original_name
            }}</span>
            <img
              src="@/assets/imgFiles/image/remove.png"
              alt="이미지삭제"
              class="image3_remove image_remove"
              v-show="image3_remove"
              @click="remove"
            />
          </div>
          <div class="mb-3 img-container">
            <label for="formFile" class="form-label fs-4">상세설명</label>
            <br />
            <button
              class="btn btn-primary image_btn me-2"
              value="details"
              id="details_btn"
              @click="upload"
            >
              이미지 첨부
            </button>
            <input
              class="form-control details_upload"
              type="file"
              ref="details_upload"
              @change="img_change"
              accept="image/*"
            />
            <span id="details_value">{{
              imgFiles[4].product_img_original_name
            }}</span>
            <img
              src="@/assets/imgFiles/image/remove.png"
              alt="이미지삭제"
              class="details_remove image_remove"
              v-show="details_remove"
              @click="remove"
            />
          </div>
          <div class="d-flex justify-content-end mb-3">
            <button type="submit" class="btn btn-primary me-3">등록</button>
            <button class="btn btn-danger" @click="productAddCancel">
              취소
            </button>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import { ref, reactive } from "vue";
import Swal from "sweetalert2";
import axios from "@/axios/axiosSetting";
import { useRouter, useRoute } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    const product_code = useRoute().params.product_code;

    let product = ref({});

    const img_name = reactive({
      thumbnail_name: "",
      image1_name: "",
      image2_name: "",
      image3_name: "",
      details_name: "",
    });

    let imgFiles = ref([]);

    const thumbnail_remove = ref(false);
    const image1_remove = ref(false);
    const image2_remove = ref(false);
    const image3_remove = ref(false);
    const details_remove = ref(false);

    const thumbnail_upload = ref(null);
    const image1_upload = ref(null);
    const image2_upload = ref(null);
    const image3_upload = ref(null);
    const details_upload = ref(null);

    const productAddCancel = (event) => {
      event.preventDefault();
      router.go(-1);
    };

    const getDetail = async () => {
      const res = await axios.get(`/api/admin/product/detail/${product_code}`);
      if (res.data.status === 200) {
        console.log(res);
        product.value = res.data.body.productDetail;
        imgFiles.value = res.data.body.fileList;
      } else {
        Swal.fire({
          icon: "warning",
          title: "상품 상세정보",
          text: "상품 정보를 가져오지 못했습니다.",
          allowOutsideClick: false,
        }).then(() => {
          router.replace({ name: "adminProductListView" });
        });
      }
    };

    getDetail();

    return {
      product,
      productAddCancel,
      imgFiles,
      img_name,
      thumbnail_remove,
      image1_remove,
      image2_remove,
      image3_remove,
      details_remove,
      thumbnail_upload,
      image1_upload,
      image2_upload,
      image3_upload,
      details_upload,
    };
  },
};
</script>

<style scoped>
input[type="file"] {
  display: none;
}

.image_remove {
  width: 30px;
}

.img-container {
  border: 1px solid #ced4da;
  padding: 16px;
  border-radius: 5px;
}
</style>
