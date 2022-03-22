const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,

  //outputDir : npm run build로 빌드 시에 파일이 생성되는 위치
  outputDir: "../src/main/resources/static",

  //indexPath : index.html 파일이 생성될 위치
  indexPath: "../templates/index.html",

  //SpringBoot 서버 접속 주소
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8090",
        changeOrigin: true,
      },
    },
  },

  runtimeCompiler: true,
  pages: {
    main: {
      entry: "src/pages/main/main.js",
      template: "public/main.html",
      filename: "main.out.html",
    },
    admin: {
      entry: "src/pages/admin/main.js",
      template: "public/admin.html",
      filename: "admin.out.html",
    },
  },

  chainWebpack: (config) => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  },
});
