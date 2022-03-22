import MainView from "@/pages/main/views/home/MainView";
import LoginView from "@/pages/main/views/home/LoginView.vue";
import RegisterView from "@/pages/main/views/home/RegisterView.vue";
import QuestionView from "@/pages/main/views/home/QuestionView.vue";
import NoticeView from "@/pages/main/views/home/NoticeView.vue";

export const HomeRouters = [
  {
    path: "/main",
    name: "MainView",
    component: MainView,
    meta: { title: "RocketLaptop - 메인" },
  },
  {
    path: "/main/loginView",
    name: "LoginView",
    component: LoginView,
    meta: { title: "RocketLaptop - 로그인" },
  },
  {
    path: "/main/register",
    name: "RegisterView",
    component: RegisterView,
    meta: { title: "RocketLaptop - 회원가입" },
  },
  {
    path: "/main/notice",
    name: "NoticeView",
    component: NoticeView,
    meta: { title: "RocketLaptop - 공지사항" },
  },
  {
    path: "/main/question",
    name: "QuestionView",
    component: QuestionView,
    meta: { title: "RocketLaptop - 문의사항" },
  },
];
