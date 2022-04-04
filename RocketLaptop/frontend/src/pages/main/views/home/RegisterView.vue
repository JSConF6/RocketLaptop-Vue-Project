<template>
  <div class="container-fluid">
    <div class="row">
      <div class="register-container mx-auto">
        <div class="register-form mx-auto">
          <div class="d-flex justify-content-center align-items-center mb-5">
            <MainLogo
              width="20%"
              height="10%"
              viewBox="0 0 850 350"
              preserveAspectRatio="none"
              class="mt-3"
            />
            <span class="fs-1">회원가입</span>
          </div>
          <form
            class="w-50 mx-auto"
            name="joinForm"
            @submit.prevent="joinProcess"
          >
            <div class="row mb-3">
              <label for="user-name" class="text-start ps-0">아이디</label>
              <input
                type="text"
                class="form-control"
                placeholder="영문자 숫자 _로 5~12자 입력하세요."
                required
                v-model="join.user_id"
                maxlength="12"
              />
              <span :class="id_color">{{ id_message }}</span>
            </div>

            <div class="row mb-3">
              <label for="user-name" class="text-start ps-0">이름</label>
              <input
                type="text"
                class="form-control"
                placeholder="한글 공백없이 입력해주세요."
                required
                v-model="join.user_name"
                maxlength="15"
              />
            </div>
            <div class="row mb-3">
              <label for="user-email" class="text-start ps-0">이메일</label>
              <input
                type="email"
                class="form-control"
                placeholder="이메일 주소를 입력해주세요. ex) rocket@naver.com"
                v-model="join.user_email"
                required
              />
              <span :class="email_color">{{ email_message }}</span>
            </div>
            <div class="row mb-3">
              <label for="user-phone" class="text-start ps-0">생년월일</label>
              <input
                type="text"
                class="form-control"
                placeholder="-포함해서 입력해주세요. ex) 2022-01-01"
                v-model="join.user_birth"
                required
              />
              <span :class="birth_color">{{ birth_message }}</span>
            </div>
            <div class="row mb-3">
              <label for="user-phone" class="text-start ps-0">휴대전화</label>
              <input
                type="text"
                class="form-control"
                placeholder="-포함해서 입력해주세요. ex) 010-1111-2222"
                v-model="join.user_phone"
                required
              />
              <span :class="phone_color">{{ phone_message }}</span>
            </div>
            <div class="row mb-3">
              <label for="user-password" class="text-start ps-0"
                >비밀번호</label
              >
              <input
                type="password"
                class="form-control"
                placeholder="비밀번호를 입력해주세요."
                v-model="join.user_password"
                required
              />
            </div>
            <div class="row mb-3">
              <label for="user-rePassword" class="text-start ps-0"
                >비밀번호 확인</label
              >
              <input
                type="password"
                class="form-control"
                placeholder="비밀번호를 다시 입력해주세요."
                v-model="join.user_repassword"
                required
              />
            </div>
            <div class="row mb-3">
              <div class="text-start ps-0">
                <label>성별&nbsp;&nbsp;</label>
                <input
                  class="form-check-input"
                  type="radio"
                  value="남성"
                  v-model="join.user_gender"
                  checked
                />
                남성 &nbsp;
                <input
                  class="form-check-input"
                  type="radio"
                  value="여성"
                  v-model="join.user_gender"
                />
                여성
              </div>
            </div>
            <div class="row mb-3">
              <button type="submit" class="btn btn-secondary fs-4">
                회원가입
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, ref, watch } from "vue";
import { useRouter } from "vue-router";
import axios from "@/axios/axiosSetting";
import Swal from "sweetalert2";

export default {
  name: "RegisterView",
  setup() {
    const join = reactive({
      user_id: "",
      user_name: "",
      user_email: "",
      user_password: "",
      user_repassword: "",
      user_birth: "",
      user_phone: "",
      user_gender: "남성",
    });
    const id_message = ref("");
    const email_message = ref("");
    const phone_message = ref("");
    const birth_message = ref("");
    const id_color = ref("");
    const email_color = ref("");
    const phone_color = ref("");
    const birth_color = ref("");
    const router = useRouter();

    const idCheck = async () => {
      try {
        console.log("idCheck = " + join.user_id);
        const res = await axios.get("/api/register/idCheck/" + join.user_id);
        console.log("res.data = " + res.data);
        if (res.data == 1) {
          id_message.value = "이미 사용중인 아이디 입니다.";
          id_color.value = "red";
        } else {
          id_message.value = "사용 가능한 아이디 입니다.";
          id_color.value = "green";
        }
      } catch (err) {
        console.log(err);
      }
    };

    watch(
      () => join.user_id,
      (next, prev) => {
        console.log("watch(id) : " + prev + " => " + next);
        const pattern = /^\w{5,12}$/;
        if (!pattern.test(join.user_id)) {
          id_message.value = "영문자 숫자 _로 5~12자 가능합니다.";
          id_color.value = "red";
        } else {
          idCheck();
        }
      }
    );

    watch(
      () => join.user_phone,
      (next, prev) => {
        console.log("watch(phone) : " + prev + " => " + next);
        const pattern = /^\d{3}-\d{3,4}-\d{4}$/;
        if (!pattern.test(join.user_phone)) {
          phone_message.value = "전화번호 형식이 맞지 않습니다.";
          phone_color.value = "red";
        } else {
          phone_message.value = "전화번호 형식이 일치합니다.";
          phone_color.value = "green";
        }
      }
    );

    watch(
      () => join.user_birth,
      (next, prev) => {
        console.log("watch(birth) : " + prev + " => " + next);
        const pattern =
          /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
        if (!pattern.test(join.user_birth)) {
          birth_message.value = "생년월일 형식이 맞지 않습니다.";
          birth_color.value = "red";
        } else {
          birth_message.value = "생년월일 형식이 일치합니다.";
          birth_color.value = "green";
        }
      }
    );

    watch(
      () => join.user_email,
      (next, prev) => {
        console.log("watch(email) : " + prev + " => " + next);
        const pattern = /^\w+@\w+[.]\w{3}$/;
        if (!pattern.test(join.user_email)) {
          email_message.value = "이메일 형식이 맞지 않습니다.";
          email_color.value = "red";
        } else {
          email_message.value = "이메일 형식이 일치합니다.";
          email_color.value = "green";
        }
      }
    );

    const joinProcess = async () => {
      if (id_color.value == "red") {
        Swal.fire({
          icon: "error",
          title: "회원가입",
          text: "아이디를 확인하세요.",
          allowOutsideClick: false,
        });
        join.user_id = "";
      } else if (email_color.value == "red") {
        Swal.fire({
          icon: "error",
          title: "회원가입",
          text: "이메일을 확인하세요.",
          allowOutsideClick: false,
        });
        join.user_email = "";
      } else if (phone_color.value == "red") {
        Swal.fire({
          icon: "error",
          title: "회원가입",
          text: "전화번호를 확인하세요.",
          allowOutsideClick: false,
        });
        join.user_phone = "";
      } else if (birth_color.value == "red") {
        Swal.fire({
          icon: "error",
          title: "회원가입",
          text: "생년월일을 확인하세요.",
          allowOutsideClick: false,
        });
        join.user_birth = "";
      } else if (join.user_password != join.user_repassword) {
        Swal.fire({
          icon: "error",
          title: "회원가입",
          text: "비밀번호를 확인하세요.",
          allowOutsideClick: false,
        });
        join.user_password = "";
        join.user_repassword = "";
      } else {
        console.log("성별 : " + join.user_gender);
        try {
          const res = await axios.post("/api/register", join);
          console.log(res.data);
          if (res.data.status === 200) {
            Swal.fire({
              icon: "success",
              title: "회원가입",
              text: "회원 가입을 축하합니다.",
              allowOutsideClick: false,
            });
            router.push({
              name: "LoginView",
            });
          } else {
            Swal.fire({
              icon: "error",
              title: "회원가입",
              text: "회원 가입에 실패했습니다. 다시 시도해주세요.",
              allowOutsideClick: false,
            });
            router.push({
              name: "RegisterView",
            });
          }
        } catch (err) {
          console.log(err);
        }
      }
    };

    return {
      join,
      joinProcess,
      id_message,
      email_message,
      phone_message,
      birth_message,
      id_color,
      email_color,
      phone_color,
      birth_color,
    };
  },
};
</script>

<style scoped>
.red {
  color: red;
}
.green {
  color: green;
}

.register-form {
  height: 1000px;
  margin-top: 3%;
}

.register-container {
  width: 1200px;
}
</style>
