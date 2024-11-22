<template>
  <div class="grid-container">
    <h1>상품 수정</h1><hr />
    <form @submit.prevent="submitEdit">
      <label>카테고리:</label><input type="text" v-model="product.cate" required />
      <label>상품명:</label><input type="text" v-model="product.pname" required />
      <label>상품 설명:</label><textarea v-model="product.pcontent" required></textarea>
      <label>이미지 1:</label><input type="file" @change="handleFileUpload('img1')" />
      <label>이미지 2:</label><input type="file" @change="handleFileUpload('img2')" />
      <label>이미지 3:</label><input type="file" @change="handleFileUpload('img3')" />
      <button type="submit" class="button primary">수정하기</button>
      <button @click="goBack" class="button secondary">취소</button>
    </form>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useMainStore } from '@/store';
const productStore = useMainStore();
const route = useRoute();
const router = useRouter();
const pno = route.params.id;
const product = ref({
  cate: '', pname: '', pcontent: '', img1: null, img2: null, img3: null,
});
onMounted(async () => {
  await productStore.fetchProductDetail(pno);
  Object.assign(product.value, productStore.productDetail);
});
const handleFileUpload = (imgField) => (event) => {
  product.value[imgField] = event.target.files[0];
};
const submitEdit = async () => {
  const formData = new FormData();
  formData.append('cate', product.value.cate);
  formData.append('pname', product.value.pname);
  formData.append('pcontent', product.value.pcontent);
  if (product.value.img1) formData.append('img1', product.value.img1);
  if (product.value.img2) formData.append('img2', product.value.img2);
  if (product.value.img3) formData.append('img3', product.value.img3);
  await productStore.updateProduct(pno, formData);
  router.push({ name: 'ProductList' });
};
const goBack = () => router.push({ name: 'ProductList' });
</script>    