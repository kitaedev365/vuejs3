<template>
  <div class="grid-container">
    <h1>상품 등록</h1><hr />
    <form @submit.prevent="submitProduct">
      <label>카테고리:</label><input type="text" v-model="cate" required />
      <label>상품명:</label><input type="text" v-model="pname" required />
      <label>상품 설명:</label><textarea v-model="pcontent" required></textarea>
      <label>이미지 1:</label><input type="file" @change="handleFileUpload('img1')" />
      <button type="submit" class="button primary">등록하기</button>
    </form>
    <button @click="goBack" class="button secondary">목록으로 돌아가기</button>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useMainStore } from '@/store';
const productStore = useMainStore();
const cate = ref('');
const pname = ref('');
const pcontent = ref('');
const img1 = ref(null);
const router = useRouter();
const handleFileUpload = (imgField) => (event) => { img1.value = event.target.files[0]; };
const submitProduct = async () => {
  const formData = new FormData();
  formData.append('cate', cate.value);
  formData.append('pname', pname.value);
  formData.append('pcontent', pcontent.value);
  formData.append('img1', img1.value);
  await productStore.createProduct(formData);
  router.push({ name: 'ProductList' });
};
const goBack = () => router.push({ name: 'ProductList' });
</script>    