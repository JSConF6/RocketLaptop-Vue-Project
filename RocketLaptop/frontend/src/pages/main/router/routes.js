import { HomeRouters } from "@/pages/main/router/home.js";
import { UserRouters } from "@/pages/main/router/user.js";
import { ProductRouters } from "@/pages/main/router/product.js";

const routes = [...HomeRouters, ...UserRouters, ...ProductRouters];

export default routes;
