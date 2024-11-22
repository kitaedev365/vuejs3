<template>
  <div class="grid-container">
    <h1>{{ product.pname }}</h1><hr/>
    <img v-if="product.img1" :src="product.img1" alt="이미지 1" />
    <img v-if="product.img2" :src="product.img2" alt="이미지 2" />
    <img v-if="product.img3" :src="product.img3" alt="이미지 3" />
    <p>카테고리: {{ product.cate }}</p><p>내용: {{ product.pcontent }}</p>
    <p>등록일: {{ product.resdate }}</p><p>조회수: {{ product.hits }}</p>
    <button class="button" @click="goBack">목록으로 돌아가기</button>
    <button class="button" @click="editProduct">수정</button>
    <button class="button alert" @click="deleteProduct">삭제</button>
  </div>
</template>
<script setup>
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useMainStore } from '@/store';
const productStore = useMainStore();
const route = useRoute();
const router = useRouter();
const pno = route.params.pno;
onMounted(() => { productStore.fetchProductDetail(pno); });
const product = productStore.productDetail;
const goBack = () => router.push({ name: 'ProductList' });
const editProduct = () => router.push({ name: 'EditProduct', params: { pno: pno } });
const deleteProduct = async () => {
  await productStore.deleteProduct(pno);
  goBack();
};
</script>    