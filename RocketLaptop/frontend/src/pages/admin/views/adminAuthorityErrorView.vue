<template>
  <div></div>
</template>

<script>
import Swal from "sweetalert2";

export default {
  setup() {
    Swal.fire({
      icon: "error",
      title: "권한 오류",
      text: "접속하시려는 페이지의 권한이 없는 계정입니다.",
      allowOutsideClick: false,
    }).then(() => {
      location.replace("http://localhost:8080/main");
    });
  },
};
</script>

<style></style>
