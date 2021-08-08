<template>
  <router-view></router-view>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { onMounted } from '@vue/runtime-core';
export default {
  name: 'App',
  components: {
  },
  setup() {
    const data = reactive({
    });
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
