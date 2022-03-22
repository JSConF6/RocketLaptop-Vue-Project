import { createApp } from "vue";
import App from "@/pages/admin/App";
import router from "@/pages/admin/router/router";
import store from "@/pages/admin/store";
import MainLogo from "@/components/svg/Logo.vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

library.add(fab, far, fas);

const app = createApp(App);

app.use(router);
app.use(store);
app.component("MainLogo", MainLogo);
app.component("font-awesome-icon", FontAwesomeIcon);
app.mount("#app");
