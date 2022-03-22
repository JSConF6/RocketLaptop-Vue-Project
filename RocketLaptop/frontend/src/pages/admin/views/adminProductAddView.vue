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
            <span id="thumbnail_value">{{ img_name.thumbnail_name }}</span>
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
            <span id="image1_value">{{ img_name.image1_name }}</span>
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
            <span id="image2_value">{{ img_name.image2_name }}</span>
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
            <span id="image3_value">{{ img_name.image3_name }}</span>
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
            <span id="details_value">{{ img_name.details_name }}</span>
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
import { reactive, ref } from "vue";
import Swal from "sweetalert2";
import axios from "@/axios/axiosSetting";
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    const product = reactive({
      product_name: "",
      product_code: "",
      category_code: "",
      product_price: "",
      product_sales: 0,
    });

    const img_name = reactive({
      thumbnail_name: "",
      image1_name: "",
      image2_name: "",
      image3_name: "",
      details_name: "",
    });

    const image_upload = reactive([]);

    const thumbnail_remove = ref(false);
    const image1_remove = ref(false);
    const image2_remove = ref(false);
    const image3_remove = ref(false);
    const details_remove = ref(false);

    let categoryList = reactive([]);

    const thumbnail_upload = ref(null);
    const image1_upload = ref(null);
    const image2_upload = ref(null);
    const image3_upload = ref(null);
    const details_upload = ref(null);

    const img_change = (event) => {
      event.preventDefault();

      let imgSize = event.target.files[0].size;

      if (!imgSizeCheck(imgSize, event.target.classList[1])) {
        return false;
      }

      if (event.target.classList[1] === "thumbnail_upload") {
        if (imgCheck(event.target.files[0], 0)) {
          img_name.thumbnail_name = event.target.files[0].name;
          thumbnail_remove.value = true;
        } else {
          thumbnail_upload.value.value = "";
        }
      } else if (event.target.classList[1] === "image1_upload") {
        if (imgCheck(event.target.files[0], 1)) {
          img_name.image1_name = event.target.files[0].name;
          image1_remove.value = true;
        } else {
          image1_upload.value.value = "";
        }
      } else if (event.target.classList[1] === "image2_upload") {
        if (imgCheck(event.target.files[0], 2)) {
          img_name.image2_name = event.target.files[0].name;
          image2_remove.value = true;
        } else {
          image2_upload.value.value = "";
        }
      } else if (event.target.classList[1] === "image3_upload") {
        if (imgCheck(event.target.files[0], 3)) {
          img_name.image3_name = event.target.files[0].name;
          image3_remove.value = true;
        } else {
          image3_upload.value.value = "";
        }
      } else if (event.target.classList[1] === "details_upload") {
        if (imgCheck(event.target.files[0], 4)) {
          img_name.details_name = event.target.files[0].name;
          details_remove.value = true;
        } else {
          details_upload.value.value = "";
        }
      }
    };

    function imgSizeCheck(imgSize, type) {
      let maxSize = 10 * 1024 * 1024;
      if (imgSize > maxSize) {
        Swal.fire({
          icon: "warning",
          title: "파일등록",
          text: "이미지 용량은 10MB 이내로 첨부 하실 수 있습니다.",
          allowOutsideClick: false,
        });

        if (type === "thumbnail_upload") {
          thumbnail_upload.value.value = "";
        } else if (type === "image1_upload") {
          image1_upload.value.value = "";
        } else if (type === "image2_upload") {
          image2_upload.value.value = "";
        } else if (type === "image3_upload") {
          image3_upload.value.value = "";
        } else if (type === "details_upload") {
          details_upload.value.value = "";
        }
        return false;
      }
      return true;
    }

    function imgCheck(file, num) {
      let pattern = /(gif|jpg|jpeg|png)$/i;
      if (pattern.test(file.name)) {
        image_upload[num] = file;
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

    const upload = (event) => {
      event.preventDefault();
      if (event.target.value === "thumbnail") {
        thumbnail_upload.value.click();
      } else if (event.target.value === "image1") {
        image1_upload.value.click();
      } else if (event.target.value === "image2") {
        image2_upload.value.click();
      } else if (event.target.value === "image3") {
        image3_upload.value.click();
      } else if (event.target.value === "details") {
        details_upload.value.click();
      }
    };

    const remove = (event) => {
      if (event.target.classList[0] === "thumbnail_remove") {
        imgRemove("thumbnail");
      } else if (event.target.classList[0] === "image1_remove") {
        imgRemove("image1");
      } else if (event.target.classList[0] === "image2_remove") {
        imgRemove("image2");
      } else if (event.target.classList[0] === "image3_remove") {
        imgRemove("image3");
      } else if (event.target.classList[0] === "details_remove") {
        imgRemove("details");
      }
    };

    function imgRemove(type) {
      if (type === "thumbnail") {
        image_upload[0] = "";
        img_name.thumbnail_name = "";
        thumbnail_upload.value.value = "";
        thumbnail_remove.value = false;
      } else if (type === "image1") {
        image_upload[1] = "";
        img_name.image1_name = "";
        image1_upload.value.value = "";
        image1_remove.value = false;
      } else if (type === "image2") {
        image_upload[2] = "";
        img_name.image2_name = "";
        image2_upload.value.value = "";
        image2_remove.value = false;
      } else if (type === "image3") {
        image_upload[3] = "";
        img_name.image3_name = "";
        image3_upload.value.value = "";
        image3_remove.value = false;
      } else if (type === "details") {
        image_upload[4] = "";
        img_name.details_name = "";
        details_upload.value.value = "";
        details_remove.value = false;
      }
    }

    const productAdd = async () => {
      if (product.product_code === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 코드를 입력해주세요",
          allowOutsideClick: false,
        });
      } else if (product.product_name === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 이름을 입력해주세요",
          allowOutsideClick: false,
        });
      } else if (product.product_price === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 가격을 입력해주세요",
          allowOutsideClick: false,
        });
      } else if (image_upload[0] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "썸네일 이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
      } else if (
        image_upload[1] === "" &&
        image_upload[2] === "" &&
        image_upload === ""
      ) {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상품 이미지 하나 이상 첨부해주세요.",
          allowOutsideClick: false,
        });
      } else if (image_upload[4] === "") {
        Swal.fire({
          icon: "warning",
          title: "상품등록",
          text: "상세설명 이미지를 첨부해주세요.",
          allowOutsideClick: false,
        });
      }

      try {
        let frm = new FormData();

        for (let i = 0; i < image_upload.length; i++) {
          frm.append("image_upload", image_upload[i]);
        }

        frm.append("product_code", product.product_code);
        frm.append("category_code", product.category_code);
        frm.append("product_name", product.product_name);
        frm.append("product_price", product.product_price);
        const res = await axios.post("api/admin/product/add", frm, {
          headers: { "Content-Type": "multipart/form-data;charset=UTF-8" },
        });
        if (res.data.status === 200) {
          router.push({
            name: "adminProductListView",
            params: { page: "productAdd" },
          });
        }
      } catch (error) {
        console.log(error);
      }
    };

    const getCategoryList = async () => {
      const res = await axios.get("/api/admin/category/list");
      for (let i = 0; i < res.data.body.categoryList.length; i++) {
        categoryList[i] = res.data.body.categoryList[i];
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
      productAdd,
      productAddCancel,
      categoryList,
      img_change,
      upload,
      remove,
      image_upload,
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
