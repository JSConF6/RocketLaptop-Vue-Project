<template>
  <div class="container-fluid mb-5">
    <div class="row">
      <div class="col-10 mx-auto">
        <div class="d-flex align-items-center mt-e mb-3">
          <MainLogo
            width="10%"
            height="20%"
            viewBox="0 0 850 350"
            preserveAspectRatio="none"
            class="mt-3"
          />
          <span class="fs-1">전체상품</span>
        </div>
        <div
          class="row row-cols-1 row-cols-md-3 row-cols-sm-2 row-cols-lg-4 row-cols-xl-5 g-4"
        >
          <div class="col">
            <div class="card h-100 w-100">
              <router-link to="/main/productdetail"
                ><img
                  src="@/assets/imgFiles/image/280.jpg"
                  class="card-img-top"
                  alt="..."
              /></router-link>
              <div class="card-body">
                <p class="card-text">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </p>
                <router-link to="/main/productdetail"
                  ><h4 class="card-title mb-3">삼성 갤럭시 10+</h4></router-link
                >
                <p class="card-text fs-4">
                  <span>219,000원</span>
                </p>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="card h-100 w-100">
              <router-link to="/productdetail"
                ><img
                  src="@/assets/imgFiles/image/280.jpg"
                  class="card-img-top"
                  alt="..."
              /></router-link>
              <div class="card-body">
                <p class="card-text">
                  <span class="badge bg-dark">스마트폰</span>&nbsp;
                  <span class="badge bg-dark">삼성</span>
                </p>
                <router-link to="/main/productdetail"
                  ><h4 class="card-title mb-3">삼성 갤럭시 10+</h4></router-link
                >
                <p class="card-text fs-4">
                  <span>219,000원</span>
                </p>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="card h-100 w-100">
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a short card.</p>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="card h-100 w-100">
              <img
                src="@/assets/imgFiles/image/280.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">
                  This is a longer card with supporting text below as a natural
                  lead-in to additional content.
                </p>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="card h-100 w-100">
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">
                  This is a longer card with supporting text below as a natural
                  lead-in to additional content. This content is a little bit
                  longer.
                </p>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="card h-100 w-100">
              <img
                src="@/assets/imgFiles/image/281.jpg"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">
                  This is a longer card with supporting text below as a natural
                  lead-in to additional content. This content is a little bit
                  longer.
                </p>
              </div>
            </div>
          </div>
        </div>
        <nav aria-label="Page navigation example" class="p-0">
          <ul class="pagination justify-content-center mt-3">
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active">
              <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CategoryProductView",
  setup() {},
};
</script>

<style scoped>
.card > .card-body > a {
  color: #2c3e50;
  text-decoration: none;
}
</style>
