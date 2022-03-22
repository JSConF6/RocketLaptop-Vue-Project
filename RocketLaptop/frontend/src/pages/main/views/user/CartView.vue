<template>
  <div class="container-fluid">
    <div class="d-flex mt-3 align-items-center justify-content-center">
      <MainLogo
        width="15%"
        height="20%"
        viewBox="0 0 850 350"
        preserveAspectRatio="none"
        class="mt-3"
      />
      <h1>장바구니</h1>
    </div>
    <div class="row">
      <div class="col-12 product-cartlist mt-5">
        <div class="col-xs-10 h-100">
          <table class="table table-striped border">
            <tr>
              <th><input type="checkbox" /></th>
              <th>상품이미지</th>
              <th>상품이름</th>
              <th>상품가격</th>
              <th>주문개수</th>
              <th>배송비</th>
              <th>삭제</th>
            </tr>
            <tr class="align-middle">
              <td><input type="checkbox" /></td>
              <td style="width: 100px">
                <img
                  src="@/assets/imgFiles/image/280.jpg"
                  width="80px"
                  height="80px"
                />
              </td>
              <td>갤럭시 10+</td>
              <td>1,500,000원</td>
              <td>3개</td>
              <td>2,500원</td>
              <td>
                <button type="button" class="btn btn-danger">삭제</button>
              </td>
            </tr>
            <tr class="align-middle">
              <td><input type="checkbox" /></td>
              <td style="width: 100px">
                <img
                  src="@/assets/imgFiles/image/280.jpg"
                  width="80px"
                  height="80px"
                />
              </td>
              <td>갤럭시 10+</td>
              <td>1,500,000원</td>
              <td>3개</td>
              <td>2,500원</td>
              <td>
                <button type="button" class="btn btn-danger">삭제</button>
              </td>
            </tr>
            <tr class="align-middle">
              <td><input type="checkbox" /></td>
              <td style="width: 100px">
                <img
                  src="@/assets/imgFiles/image/280.jpg"
                  width="80px"
                  height="80px"
                />
              </td>
              <td>갤럭시 10+</td>
              <td>1,500,000원</td>
              <td>3개</td>
              <td>2,500원</td>
              <td>
                <button type="button" class="btn btn-danger">삭제</button>
              </td>
            </tr>
            <tr class="align-middle">
              <td><input type="checkbox" /></td>
              <td style="width: 100px">
                <img
                  src="@/assets/imgFiles/image/280.jpg"
                  width="80px"
                  height="80px"
                />
              </td>
              <td>갤럭시 10+</td>
              <td>1,500,000원</td>
              <td>3개</td>
              <td>2,500원</td>
              <td>
                <button type="button" class="btn btn-danger">삭제</button>
              </td>
            </tr>
          </table>
          <td>
            <div class="d-flex">
              <button
                type="button"
                class="btn btn-secondary justify-content-start"
              >
                선택삭제
              </button>
            </div>
            <div class="d-flex justify-content-end">
              <h1>총 금액 : 1,500,000원</h1>
            </div>
          </td>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CartView",
  setup() {},
};
</script>

<style scoped>
.product-cartlist {
  height: 800px;
}

.cart-payment {
  height: 800px;
}
</style>
