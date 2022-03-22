<template>
  <div class="container-fluid">
    <footer
      class="row row-cols-5 py-3 my-5 border-top justify-content-center align-items-center"
    >
      <div class="col pt-4">
        <a
          href="/"
          class="d-flex align-items-center mb-3 link-dark text-decoration-none justify-content-center"
        >
          <MainLogo
            width="40%"
            height="40%"
            viewBox="0 0 850 350"
            preserveAspectRatio="none"
          />
        </a>
        <p class="text-muted">© 2022</p>
      </div>

      <div class="col pt-3">
        <h5 class="mb-3">Info</h5>
        <ul class="nav flex-column">
          <li class="nav-item mb-3"><span>010-1234-1234</span></li>
          <li class="nav-item mb-3"><span>help@rocket.com</span></li>
        </ul>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "MainFooter",
  setup() {},
};
</script>

<style></style>
