<template>
  <div>
    <div
      id="carouselExampleIndicators"
      class="carousel slide"
      data-bs-ride="carousel"
    >
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="@/assets/imgFiles/image/banner3.png"
            class="d-block w-100"
            width="200"
            height="500"
          />
        </div>
        <div class="carousel-item">
          <img
            src="@/assets/imgFiles/image/banner3.png"
            class="d-block w-100"
            width="200"
            height="500"
          />
        </div>
        <div class="carousel-item">
          <img
            src="@/assets/imgFiles/image/banner3.png"
            class="d-block w-100"
            width="200"
            height="500"
          />
        </div>
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div class="container-fluid">
      <div class="row justify-content-center">
        <div class="col col-10">
          <div class="d-flex justify-content-between mt-4 align-items-center">
            <h1>새로운 상품</h1>
            <router-link to="/main/newproduct" class="text-secondary"
              ><font-awesome-icon icon="angle-right" size="2x"
            /></router-link>
          </div>
        </div>
        <swiper
          :slidesPerView="1"
          :spaceBetween="10"
          :pagination="{
            clickable: true,
          }"
          :breakpoints="{
            '800': {
              slidesPerView: 2,
              spaceBetween: 20,
            },
            '1250': {
              slidesPerView: 3,
              spaceBetween: 40,
            },
            '1600': {
              slidesPerView: 4,
              spaceBetween: 50,
            },
            '2000': {
              slidesPerView: 5,
              spaceBetween: 50,
            },
          }"
          :modules="modules"
          class="mySwiper col-10"
        >
          <swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <router-link to="/main/productdetail"
                ><img
                  src="@/assets/imgFiles/image/280.jpg"
                  class="card-img-top"
                  alt="..."
              /></router-link>
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <router-link to="/main/productdetail" class="btn btn-secondary"
                  >상세보기</router-link
                >&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-primary position-absolute fs-6"
                style="top: 2%; left: 77%"
                >New</span
              >
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </h6>
                <p class="card-text fs-6">삼성전자 갤럭시 노트10+</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">423,000원</span>
              </div>
            </div></swiper-slide
          >
        </swiper>
      </div>
      <div class="row justify-content-center mb-5">
        <div class="col col-10">
          <div class="d-flex justify-content-between mt-4 align-items-center">
            <h1>베스트 상품</h1>
            <router-link to="/main/bestproduct" class="text-secondary"
              ><font-awesome-icon icon="angle-right" size="2x"
            /></router-link>
          </div>
        </div>
        <swiper
          :slidesPerView="1"
          :spaceBetween="10"
          :pagination="{
            clickable: true,
          }"
          :breakpoints="{
            '800': {
              slidesPerView: 2,
              spaceBetween: 20,
            },
            '1250': {
              slidesPerView: 3,
              spaceBetween: 40,
            },
            '1600': {
              slidesPerView: 4,
              spaceBetween: 50,
            },
            '2000': {
              slidesPerView: 5,
              spaceBetween: 50,
            },
          }"
          :modules="modules"
          class="mySwiper col-10"
        >
          <swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <router-link to="/main/productdetail"
                ><img
                  src="@/assets/imgFiles/image/281.jpg"
                  class="card-img-top"
                  alt="..."
              /></router-link>
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <router-link to="/main/productdetail" class="btn btn-secondary"
                  >상세보기</router-link
                >&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          ><swiper-slide
            ><div class="card" style="width: 18rem">
              <span
                class="badge bg-danger position-absolute fs-6"
                style="top: 2%; left: 77%"
                >Best</span
              >
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h6 class="card-title">
                  <span class="badge bg-dark">노트북</span>&nbsp;
                  <span class="badge bg-dark">ASUS</span>
                </h6>
                <p class="card-text fs-6">에이수스 2022 노트북</p>
                <a href="#" class="btn btn-secondary">상세보기</a>&nbsp;
                <span class="card-text">1,592,000원</span>
              </div>
            </div></swiper-slide
          >
        </swiper>
      </div>
    </div>
  </div>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";

// Import Swiper styles
import "swiper/css";

import "swiper/css/pagination";
import "swiper/css/navigation";

import "@/assets/styles/swiper.css";

// import required modules
import { Pagination } from "swiper";

export default {
  name: "MainView",
  components: { Swiper, SwiperSlide },

  setup() {
    const onSwiper = (swiper) => {
      console.log(swiper);
    };
    const onSlideChange = () => {
      console.log("slide change");
    };

    return {
      onSwiper,
      onSlideChange,
      modules: [Pagination],
    };
  },
};
</script>

<style>
.swiper-pagination {
  height: 3%;
  position: static;
  margin-bottom: 10px;
}
</style>
