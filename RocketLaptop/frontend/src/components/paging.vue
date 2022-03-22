<template>
  <footer>
    <nav class="d-flex justify-content-center" v-if="pageHandler.listCount > 0">
      <ul class="pagination">
        <li v-if="pageHandler.page <= 1" class="page-item">
          <a class="page-link gray">&laquo;</a>
        </li>
        <li v-else-if="pageHandler.page > 1" class="page-item">
          <a
            style="cursor: pointer"
            class="page-link"
            @click="getList(pageHandler.page - 1)"
            >&laquo;</a
          >
        </li>
        <li
          v-for="page in pageList"
          :key="page"
          class="page-item"
          :class="pageHandler.page === page ? 'active gray' : ''"
        >
          <a
            :style="pageHandler.page === page ? '' : 'cursor: pointer'"
            class="page-link"
            @click="getList(page)"
            >{{ page }}</a
          >
        </li>
        <li v-if="pageHandler.page >= pageHandler.maxPage" class="page-item">
          <a class="page-link gray">&raquo;</a>
        </li>
        <li
          v-else-if="pageHandler.page < pageHandler.maxPage"
          class="page-item"
        >
          <a
            style="cursor: pointer"
            class="page-link"
            @click="getList(pageHandler.page + 1)"
            >&raquo;</a
          >
        </li>
      </ul>
    </nav>
  </footer>
</template>

<script>
export default {
  name: "pagingView",
  props: {
    pageList: {
      type: Array,
      required: true,
    },
    pageHandler: {
      type: Object,
      required: true,
    },
  },
  emits: ["getList"],
  setup(props, context) {
    const getList = (page) => {
      context.emit("getList", page);
    };

    return {
      getList,
    };
  },
};
</script>

<style scoped>
.gray {
  color: gray;
}
</style>
