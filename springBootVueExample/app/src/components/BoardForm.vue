<template>
  <div class="container">
    <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" v-model="data.board.title" class="form-control" id="title" placeholder="제목 입력">
    </div>
    <div class="mb-3">
        <label for="contents" class="form-label">내용</label>
        <textarea v-model="data.board.contents" class="form-control" id="contents" name="contents" rows="3"></textarea>
    </div>
    <div class="mb-3">
        <label for="uploadFile" class="form-label">파일 첨부</label>
        <input class="form-control" v-on:change="changeFile" type="file" id="uploadFile" name="uploadFile">
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-secondary me-md-2" type="button">취소</button>
        <button class="btn btn-primary" type="button" v-on:click="save">저장</button>
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
      board: {
          boardType: 'COMMUNITY', // 강제 주입
          title: null,
          contents: null,
          uploadFile: null
      }
    });
    const save = () => {
        var formData = new FormData(); // multipart 전송
        formData.append('boardType', data.board.boardType);
        if(data.board.title != null) {
            formData.append('title', data.board.title);
        }
        if(data.board.contents != null) {
            formData.append('contents', data.board.contents);
        }
        if(data.board.uploadFile != null) {
            formData.append('uploadFile', data.board.uploadFile);
        }
        fetch('http://localhost:8080/save', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(response => {
            console.log(response);
            data.boardList = response.data;
        });
    };
    const changeFile = (e) => {
        console.log(e.target.files);
        data.board.uploadFile = e.target.files[0];
    }
    onMounted(() => {
    });
    return {
      data: data,
      changeFile: changeFile,
      save: save
    }
  }
}
</script>

<style>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
</style>
