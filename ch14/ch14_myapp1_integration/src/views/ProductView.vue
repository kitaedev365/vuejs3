<template>
    <div class="container">
      <h1 class="title">{{ product.name }}</h1>
      <p>{{ product.description }}</p>
      <p class="subtitle">{{ product.price }} 원</p>
      <button class="button is-primary" @click="goToPurchase">구매하기</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  import ProductService from '../services/ProductService'
  
  const product = ref({})
  const route = useRoute()
  const router = useRouter()
  
  // 제품 ID 가져오기
  const productId = route.params.id
  
  async function fetchProduct() {
    try {
      product.value = await ProductService.getProductById(productId)
    } catch (error) {
      console.error('Failed to fetch product:', error)
    }
  }
  
  // 구매 페이지로 이동
  function goToPurchase() {
    router.push({ name: 'SaleView', params: { id: product.value.id } })
  }
  
  onMounted(() => {
    fetchProduct()
  })
  </script>