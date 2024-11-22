<template>
  <div class="grid-container">
    <h1>상품 목록</h1><hr />
    <button @click="createProduct" class="button primary">상품 등록</button>
    <div class="grid-x grid-margin-x small-up-2 medium-up-3">
      <div class="cell" v-for="product in paginatedProducts" :key="product.pno">
        <div class="card">
          <img v-if="product.img1" :src="`../assets/${product.img1}`" alt="상품 이미지" />
          <div class="card-section">
            <h4>{{ product.pname }}</h4><p>카테고리: {{ product.cate }}</p>
            <p>{{ product.pcontent }}</p>
            <button class="button" @click="viewDetail(product.pno)">상세보기</button>
            <button class="button" @click="editProduct(product.pno)">수정</button>
            <button class="button alert" @click="deleteProduct(product.pno)">삭제</button>
          </div>
        </div>
      </div>
    </div>
    <ul class="pagination text-center">
      <li v-if="currentPage > 1" class="pagination-previous">
        <a @click="changePage(currentPage - 1)">Previous</a>
      </li>
      <li v-for="page in totalPages" :key="page" :class="{ 'current': page === currentPage }">
        <a @click="changePage(page)">{{ page }}</a>
      </li>
      <li v-if="currentPage < totalPages" class="pagination-next">
        <a @click="changePage(currentPage + 1)">Next</a>
      </li>
    </ul>
  </div>
</template>
<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useMainStore } from '@/store';
const productStore = useMainStore();
const { fetchProducts, products } = productStore;
const currentPage = ref(1);
const pageSize = 6;
onMounted(fetchProducts);
const paginatedProducts = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  return products.slice(start, start + pageSize);
});
const totalPages = computed(() => Math.ceil(products.length / pageSize));
const changePage = (page) => { currentPage.value = page; };
const router = useRouter();
const viewDetail = (pno) => router.push({ name: 'ProductDetail', params: { pno: pno } });
const createProduct = () => router.push({ name: 'CreateProduct' });
const editProduct = (pno) => router.push({ name: 'EditProduct', params: { pno: pno } });
const deleteProduct = async (pno) => {
  await productStore.deleteProduct(pno);
  fetchProducts();
};
</script>    