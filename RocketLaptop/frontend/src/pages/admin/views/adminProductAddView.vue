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
              v-model="product.product_code"
            />
          </div>
          <div class="mb-3">
            <label for="category" class="form-label">카테고리</label>
            <select class="form-select" v-model="product.category_code">
              <option
                v-for="(item, index) in categoryList"
                :key="index"
                :value="item.category_code"
              >
                {{ item.category_name }}
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
          <div
            v-for="(num, index) in 5"
            :key="index"
            class="mb-3 img-container"
          >
            <label :for="`imageFile${index}`" class="form-label fs-4">
              <span v-if="num == 1">대표이미지</span>
              <span v-if="num == 2 || num == 3 || num == 4">이미지</span>
              <span v-if="num == 5">상세설명</span><br />
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
import { onBeforeUpdate, reactive, ref } from "vue";
import Swal from "sweetalert2";
import axios from "@/axios/axiosSetting";
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    const images = ref([]);
    const show = ref([]);

    const product = reactive({
      product_name: "",
      product_code: "",
      category_code: "",
      product_price: "",
      product_sales: 0,
    });

    const image_value = ref([]);

    const image_file = ref([]);

    const image_type = ref([]);

    const upload = (index, event) => {
      let imgSize = event.target.files[0].size;

      if (!imgSizeCheck(imgSize)) {
        image_file.value[index].value = "";
        return false;
      }

      if (imgCheck(event.target.files[0], index)) {
        image_value.value[index] = event.target.files[0].name;
        show.value[index] = true;
        if (index + 1 == 1) {
          image_type.value[index] = {
            image_name: event.target.files[0].name,
            image_type: 1,
          };
        } else if (index + 1 == 2 || index + 1 == 3 || index + 1 == 4) {
          image_type.value[index] = {
            image_name: event.target.files[0].name,
            image_type: 2,
          };
        } else if (index + 1 == 5) {
          image_type.value[index] = {
            image_name: event.target.files[0].name,
            image_type: 3,
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
      image_value.value[index] = "";
      images.value[index] = "";
      show.value[index] = false;
      image_file.value[index].value = "";
      image_type[index] = {};
    };

    onBeforeUpdate(() => {
      image_file.value = [];
    });

    const productAdd = async () => {
      if (product.product_code === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 코드를 입력해주세요",
          allowOutsideClick: false,
        });
        return false;
      }

      if (product.product_name === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 이름을 입력해주세요",
          allowOutsideClick: false,
        });
        return false;
      }

      if (product.product_price === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 가격을 입력해주세요",
          allowOutsideClick: false,
        });
        return false;
      }

      if (images.value[0] === undefined || images.value[0] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "대표 이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
        return false;
      }

      if (images.value[1] === undefined || images.value[1] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
        return false;
      }

      if (images.value[2] === undefined || images.value[2] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
        return false;
      }

      if (images.value[3] === undefined || images.value[3] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
        return false;
      }

      if (images.value[4] === undefined || images.value[4] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상세설명 이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
        return false;
      }
      try {
        let frm = new FormData();

        for (let i = 0; i < images.value.length; i++) {
          frm.append("images", images.value[i]);
        }

        frm.append("image_type", JSON.stringify(image_type.value));
        frm.append("product_code", product.product_code);
        frm.append("category_code", product.category_code);
        frm.append("product_name", product.product_name);
        frm.append("product_price", product.product_price);

        const res = await axios.post("api/admin/product/add", frm, {
          headers: { "Content-Type": "multipart/form-data;charset=UTF-8" },
        });
        if (res.data.status === 200) {
          Swal.fire({
            icon: "success",
            title: "상품등록",
            text: "상품등록 완료",
            allowOutsideClick: false,
          }).then(() => {
            router.replace({
              name: "adminProductListView",
            });
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "상품등록",
            text: "상품등록 실패",
            allowOutsideClick: false,
          }).then(() => {
            router.replace({ name: "adminProductAddView" });
          });
        }
      } catch (error) {
        console.log(error);
      }
    };

    let categoryList = reactive([]);

    const getCategoryList = async () => {
      const res = await axios.get("/api/admin/category/list");
      for (let i = 0; i < res.data.data.categoryList.length; i++) {
        categoryList[i] = res.data.data.categoryList[i];
      }
      product.category_code = categoryList[0].category_code;
    };
    getCategoryList();

    const productAddCancel = (event) => {
      event.preventDefault();
      router.go(-1);
    };

    return {
      product,
      productAddCancel,
      categoryList,
      show,
      upload,
      image_value,
      remove,
      image_file,
      productAdd,
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
