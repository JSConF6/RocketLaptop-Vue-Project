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
          <span class="fs-1">상품수정</span>
        </div>
        <form class="product_add_form" @submit.prevent="productModify">
          <div class="mb-3">
            <label for="productCode" class="form-label">상품코드</label>
            <input
              type="text"
              class="form-control"
              id="productCode"
              placeholder="상품코드를 입력하세요"
              :value="productDetail.product_code"
              disabled
            />
          </div>
          <div class="mb-3">
            <label for="category" class="form-label">카테고리</label>
            <select
              class="form-select"
              :value="productDetail.category_code"
              disabled
            >
              <option :value="productDetail.category_code">
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
              placeholder="상품이름을 입력하세요"
              v-model="productDetail.product_name"
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
              v-model="productDetail.product_price"
            />
          </div>
          <div
            v-for="(imgFile, index) in imgFiles"
            :key="index"
            class="mb-3 img-container"
          >
            <label :for="`imageFile${index}`" class="form-label fs-4">
              <span v-if="index == 0">대표이미지</span>
              <span v-if="index == 1 || index == 2 || index == 3">이미지</span>
              <span v-if="index == 4">상세설명</span><br />
              <span class="btn btn-primary me-2">이미지 첨부</span>
              <input
                class="form-control"
                :id="`imageFile${index}`"
                type="file"
                :ref="
                  (el) => {
                    if (el) image_file[index] = el;
                  }
                "
                accept="image/*"
                @change="upload(index, $event)"
              />
            </label>
            <span>{{ image_value[index] }}</span>
            <img
              src="@/assets/imgFiles/image/remove.png"
              alt="이미지삭제"
              class="image_remove"
              v-show="show[index]"
              @click="remove(index)"
            />
          </div>
          <div class="d-flex justify-content-end mb-3">
            <button type="submit" class="btn btn-primary me-3">수정</button>
            <button class="btn btn-danger" @click="productModifyCancel">
              취소
            </button>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import { onBeforeUpdate, ref } from "vue";
import Swal from "sweetalert2";
import axios from "@/axios/axiosSetting";
import { useRouter, useRoute } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    const product_code = useRoute().params.product_code;

    const images = ref([]);
    const show = ref([]);

    const image_value = ref([]);

    const image_file = ref([]);

    const image_type = ref([]);

    let productDetail = ref({});
    let imgFiles = ref([]);
    let original_name = ref("");

    let check = ref([]);

    const upload = (index, event) => {
      let imgSize = event.target.files[0].size;

      if (!imgSizeCheck(imgSize)) {
        image_file.value[index].value = "";
        return false;
      }

      if (imgCheck(event.target.files[0], index)) {
        image_value.value[index] = event.target.files[0].name;
        show.value[index] = true;
        check.value[index] = 1;
        if (index + 1 == 1) {
          image_type.value[index] = {
            image_name: event.target.files[0].name,
            image_type: 1,
            image_num: imgFiles.value[index].product_img_num,
          };
        } else if (index + 1 == 2 || index + 1 == 3 || index + 1 == 4) {
          image_type.value[index] = {
            image_name: event.target.files[0].name,
            image_type: 2,
            image_num: imgFiles.value[index].product_img_num,
          };
        } else if (index + 1 == 5) {
          image_type.value[index] = {
            image_name: event.target.files[0].name,
            image_type: 3,
            image_num: imgFiles.value[index].product_img_num,
          };
        }
      } else {
        image_file.value[index].value = "";
      }
    };

    function imgSizeCheck(imgSize) {
      let maxSize = 10 * 1024 * 1024;
      if (imgSize > maxSize) {
        Swal.fire({
          icon: "warning",
          title: "파일등록",
          text: "이미지 용량은 10MB 이내로 첨부 하실 수 있습니다.",
          allowOutsideClick: false,
        });
        return false;
      }
      return true;
    }

    function imgCheck(file, index) {
      let pattern = /(gif|jpg|jpeg|png)$/i;
      if (pattern.test(file.name)) {
        console.log(file);
        images.value[index] = file;
        return true;
      } else {
        Swal.fire({
          icon: "warning",
          title: "파일등록",
          text: "확장자는 gif, jpg, jpeg, png가 가능합니다.",
          allowOutsideClick: false,
        });
        return false;
      }
    }

    const remove = (index) => {
      console.log(index);
      check.value[index] = 0;
      console.log(check.value[index]);
      image_value.value[index] = "";
      images.value[index] = "";
      show.value[index] = false;
      image_file.value[index].value = "";
      image_type[index] = {};
    };

    onBeforeUpdate(() => {
      image_file.value = [];
    });

    const productModify = async () => {
      if (productDetail.value.product_name === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 이름을 입력해주세요",
          allowOutsideClick: false,
        });
        return false;
      }

      if (productDetail.value.product_price === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 가격을 입력해주세요",
          allowOutsideClick: false,
        });
        return false;
      }

      if (images.value[0] === undefined || images.value[0] === "") {
        if (check.value[0] === 0) {
          Swal.fire({
            icon: "warning",
            title: "상품등록",
            text: "대표 이미지를 첨부해주세요.",
            allowOutsideClick: false,
          });
          return false;
        }
      }

      if (images.value[1] === undefined || images.value[1] === "") {
        if (check.value[1] === 0) {
          Swal.fire({
            icon: "warning",
            title: "상품등록",
            text: "이미지를 첨부해주세요.",
            allowOutsideClick: false,
          });
          return false;
        }
      }

      if (images.value[2] === undefined || images.value[2] === "") {
        if (check.value[2] === 0) {
          Swal.fire({
            icon: "warning",
            title: "상품등록",
            text: "이미지를 첨부해주세요.",
            allowOutsideClick: false,
          });
          return false;
        }
      }
      if (images.value[3] === undefined || images.value[3] === "") {
        if (check.value[3] === 0) {
          Swal.fire({
            icon: "warning",
            title: "상품등록",
            text: "이미지를 첨부해주세요.",
            allowOutsideClick: false,
          });
          return false;
        }
      }

      if (images.value[4] === undefined || images.value[4] === "") {
        if (check.value[4] === 0) {
          Swal.fire({
            icon: "warning",
            title: "상품등록",
            text: "상세설명 이미지를 첨부해주세요.",
            allowOutsideClick: false,
          });
          return false;
        }
      }
      try {
        let frm = new FormData();

        for (let i = 0; i < images.value.length; i++) {
          frm.append("images", images.value[i]);
        }
        console.log(image_type);
        frm.append("image_type", JSON.stringify(image_type.value));
        frm.append("product_code", productDetail.value.product_code);
        frm.append("category_code", productDetail.value.category_code);
        frm.append("product_name", productDetail.value.product_name);
        frm.append("product_price", productDetail.value.product_price);

        const res = await axios.post(
          `api/admin/product/modify/${product_code}`,
          frm,
          {
            headers: { "Content-Type": "multipart/form-data;charset=UTF-8" },
          }
        );
        if (res.data.status === 200) {
          Swal.fire({
            icon: "success",
            title: "상품수정",
            text: "상품 수정 완료",
            allowOutsideClick: false,
          }).then(() => {
            router.replace(`/admin/product/detail/${product_code}`);
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "상품수정",
            text: "상품 수정 실패",
            allowOutsideClick: false,
          }).then(() => {
            router.replace(`/admin/product/detail/${product_code}`);
          });
        }
      } catch (error) {
        console.log(error);
      }
    };

    const product_detail = async () => {
      const res = await axios.get(`/api/admin/product/detail/${product_code}`);
      if (res.data.status === 200) {
        console.log(res);
        productDetail.value = res.data.data.productDetail;
        imgFiles.value = res.data.data.fileList;
        for (let i = 0; i < imgFiles.value.length; i++) {
          image_value.value[i] = imgFiles.value[i].product_img_original_name;
          show.value[i] = true;
        }
      } else {
        Swal.fire({
          icon: "warning",
          title: "상품 수정",
          text: "상품 정보를 가져오지 못했습니다.",
          allowOutsideClick: false,
        }).then(() => {
          router.replace({ name: "adminProductListView" });
        });
      }
    };

    product_detail();

    const productModifyCancel = (event) => {
      event.preventDefault();
      router.go(-1);
    };

    return {
      productModifyCancel,
      show,
      upload,
      image_value,
      remove,
      image_file,
      productModify,
      productDetail,
      imgFiles,
      original_name,
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
