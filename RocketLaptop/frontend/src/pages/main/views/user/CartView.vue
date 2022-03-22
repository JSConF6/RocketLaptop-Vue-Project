<template>
  <div class="container-fluid">
    <div class="d-flex mt-3 align-items-center justify-content-center">
      <MainLogo
        width="15%"
        height="20%"
        viewBox="0 0 850 350"
        preserveAspectRatio="none"
        class="mt-3 mb-3"
      />
      <h1>장바구니</h1>
    </div>
    <div class="container padding-bottom-3x mb-1">
      <div class="table-responsive shopping-cart">
        <table class="table">
          <thead style="border-top: 3px solid">
            <tr>
              <th>상품명</th>
              <th class="text-center">주문갯수</th>
              <th class="text-center">배송비</th>
              <th class="text-center">총가격</th>
              <th class="text-center">
                <a class="btn btn-sm btn-outline-danger" href="#">전체삭제</a>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <div class="product-item">
                  <a class="product-thumb" href="#"
                    ><img src="@/assets/imgFiles/image/280.jpg" alt="Product"
                  /></a>
                  <div class="product-info text-start">
                    <h4 class="product-title">
                      <a href="#">갤럭시 10+</a>
                    </h4>
                    <span class="fs-6 mt-2 mb-2">카테고리: 삼성</span>
                    <span class="fs-6">상품가격: 219,000원</span>
                  </div>
                </div>
              </td>
              <td class="text-center">
                <div class="count-input">
                  <input
                    type="text"
                    value="1"
                    class="form-control text-center bg-white"
                    disabled
                  />
                </div>
              </td>
              <td class="text-center text-lg text-medium">2,500원</td>
              <td class="text-center text-lg text-medium">221,500원</td>
              <td class="text-center">
                <a
                  class="remove-from-cart"
                  href="#"
                  data-toggle="tooltip"
                  title=""
                  data-original-title="Remove item"
                  ><font-awesome-icon icon="trash"
                /></a>
              </td>
            </tr>
            <tr>
              <td>
                <div class="product-item">
                  <a class="product-thumb" href="#"
                    ><img
                      src="https://via.placeholder.com/220x180/5F9EA0/000000"
                      alt="Product"
                  /></a>
                  <div class="product-info">
                    <h4 class="product-title">
                      <a href="#">Daily Fabric Cap</a>
                    </h4>
                    <span><em>Size:</em> XL</span
                    ><span><em>Color:</em> Black</span>
                  </div>
                </div>
              </td>
              <td class="text-center">
                <div class="count-input">
                  <select class="form-control">
                    <option>1</option>
                    <option selected="">2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                </div>
              </td>
              <td class="text-center text-lg text-medium">$24.89</td>
              <td class="text-center">—</td>
              <td class="text-center">
                <a
                  class="remove-from-cart"
                  href="#"
                  data-toggle="tooltip"
                  title=""
                  data-original-title="Remove item"
                  ><font-awesome-icon icon="trash"
                /></a>
              </td>
            </tr>
            <tr>
              <td>
                <div class="product-item">
                  <a class="product-thumb" href="#"
                    ><img
                      src="https://via.placeholder.com/220x180/9932CC/000000"
                      alt="Product"
                  /></a>
                  <div class="product-info">
                    <h4 class="product-title">
                      <a href="#">Cole Haan Crossbody</a>
                    </h4>
                    <span><em>Size:</em> -</span
                    ><span><em>Color:</em> Turquoise</span>
                  </div>
                </div>
              </td>
              <td class="text-center">
                <div class="count-input">
                  <select class="form-control">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                </div>
              </td>
              <td class="text-center text-lg text-medium">$200.00</td>
              <td class="text-center">—</td>
              <td class="text-center">
                <a
                  class="remove-from-cart"
                  href="#"
                  data-toggle="tooltip"
                  title=""
                  data-original-title="Remove item"
                  ><font-awesome-icon icon="trash"
                /></a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="shopping-cart-footer">
        <div class="column"></div>
        <div class="column text-lg fs-3">
          전체가격: <span class="text-medium">221,500원</span>
        </div>
      </div>
      <div class="shopping-cart-footer">
        <div class="text-end">
          <a class="btn btn-primary" href="#">주문하기</a>
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

.shopping-cart,
.wishlist-table,
.order-table {
  margin-bottom: 20px;
}

.shopping-cart .table,
.wishlist-table .table,
.order-table .table {
  margin-bottom: 0;
}

.shopping-cart .btn,
.wishlist-table .btn,
.order-table .btn {
  margin: 0;
}

.shopping-cart > table > thead > tr > th,
.shopping-cart > table > thead > tr > td,
.shopping-cart > table > tbody > tr > th,
.shopping-cart > table > tbody > tr > td,
.wishlist-table > table > thead > tr > th,
.wishlist-table > table > thead > tr > td,
.wishlist-table > table > tbody > tr > th,
.wishlist-table > table > tbody > tr > td,
.order-table > table > thead > tr > th,
.order-table > table > thead > tr > td,
.order-table > table > tbody > tr > th,
.order-table > table > tbody > tr > td {
  vertical-align: middle !important;
}

.shopping-cart > table thead th,
.wishlist-table > table thead th,
.order-table > table thead th {
  padding-top: 17px;
  padding-bottom: 17px;
}

.shopping-cart .remove-from-cart,
.wishlist-table .remove-from-cart,
.order-table .remove-from-cart {
  display: inline-block;
  color: #ff5252;
  font-size: 18px;
  line-height: 1;
  text-decoration: none;
}

.shopping-cart .count-input,
.wishlist-table .count-input,
.order-table .count-input {
  display: inline-block;
  width: 100%;
  width: 86px;
}

.shopping-cart .product-item,
.wishlist-table .product-item,
.order-table .product-item {
  display: table;
  width: 100%;
  min-width: 150px;
  margin-top: 5px;
  margin-bottom: 3px;
}

.shopping-cart .product-item .product-thumb,
.shopping-cart .product-item .product-info,
.wishlist-table .product-item .product-thumb,
.wishlist-table .product-item .product-info,
.order-table .product-item .product-thumb,
.order-table .product-item .product-info {
  display: table-cell;
  vertical-align: top;
}

.shopping-cart .product-item .product-thumb,
.wishlist-table .product-item .product-thumb,
.order-table .product-item .product-thumb {
  width: 130px;
  padding-right: 20px;
}

.shopping-cart .product-item .product-thumb > img,
.wishlist-table .product-item .product-thumb > img,
.order-table .product-item .product-thumb > img {
  display: block;
  width: 100%;
}

@media screen and (max-width: 860px) {
  .shopping-cart .product-item .product-thumb,
  .wishlist-table .product-item .product-thumb,
  .order-table .product-item .product-thumb {
    display: none;
  }
}

.shopping-cart .product-item .product-info span,
.wishlist-table .product-item .product-info span,
.order-table .product-item .product-info span {
  display: block;
  font-size: 13px;
}

.shopping-cart .product-item .product-info span > em,
.wishlist-table .product-item .product-info span > em,
.order-table .product-item .product-info span > em {
  font-weight: 500;
  font-style: normal;
}

.shopping-cart .product-item .product-title,
.wishlist-table .product-item .product-title,
.order-table .product-item .product-title {
  margin-bottom: 6px;
  padding-top: 5px;
  font-size: 16px;
  font-weight: 500;
}

.shopping-cart .product-item .product-title > a,
.wishlist-table .product-item .product-title > a,
.order-table .product-item .product-title > a {
  transition: color 0.3s;
  color: #374250;
  line-height: 1.5;
  text-decoration: none;
}

.shopping-cart .product-item .product-title > a:hover,
.wishlist-table .product-item .product-title > a:hover,
.order-table .product-item .product-title > a:hover {
  color: #0da9ef;
}

.shopping-cart .product-item .product-title small,
.wishlist-table .product-item .product-title small,
.order-table .product-item .product-title small {
  display: inline;
  margin-left: 6px;
  font-weight: 500;
}

.wishlist-table .product-item .product-thumb {
  display: table-cell !important;
}

@media screen and (max-width: 576px) {
  .wishlist-table .product-item .product-thumb {
    display: none !important;
  }
}

.shopping-cart-footer {
  display: table;
  width: 100%;
  padding: 10px 0;
  border-top: 1px solid #e1e7ec;
}

.shopping-cart-footer > .column {
  display: table-cell;
  padding: 5px 0;
  vertical-align: middle;
}

.shopping-cart-footer > .column:last-child {
  text-align: right;
}

.shopping-cart-footer > .column:last-child .btn {
  margin-right: 0;
  margin-left: 15px;
}

@media (max-width: 768px) {
  .shopping-cart-footer > .column {
    display: block;
    width: 100%;
  }
  .shopping-cart-footer > .column:last-child {
    text-align: center;
  }
  .shopping-cart-footer > .column .btn {
    width: 100%;
    margin: 12px 0 !important;
  }
}

.coupon-form .form-control {
  display: inline-block;
  width: 100%;
  max-width: 235px;
  margin-right: 12px;
}

.form-control-sm:not(textarea) {
  height: 36px;
}
</style>
