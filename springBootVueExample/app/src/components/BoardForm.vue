<template>
  <div class="container">
    <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="email" class="form-control" id="title" placeholder="제목 입력">
    </div>
    <div class="mb-3">
        <label for="contents" class="form-label">내용</label>
        <textarea class="form-control" id="contents" rows="3"></textarea>
    </div>
    <div class="mb-3">
        <label for="uploadFile" class="form-label">파일 첨부</label>
        <input name="uploadFile" class="form-control" type="file" id="uploadFile">
    </div>
  </div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { onMounted } from '@vue/runtime-core';
export default {
  name: 'BoardForm',
  components: {
  },
  setup() {
    const data = reactive({
      boardList: []
    });
    // const save = () => {
    // };
    onMounted(() => {
    });
    return {
      data: data
    }
  }
}
</script>

<style>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
</style>
