<template>
  <div>
    <h1 class="title is-1">E-commerce 플랫폼에 오신 것을 환영합니다.</h1>
    <hr/>
    
    <!-- Error Notification -->
    <div v-if="error" class="notification is-danger">{{ error }}</div>

    <div v-else class="columns is-multiline">
      <div v-for="product in products" :key="product.id" class="column is-half-tablet is-one-third-desktop">
        <div class="card">
          <div class="card-content">
            <h3 class="title is-4">{{ product.name }}</h3>
            <p>{{ product.description }}</p>
            <p class="subtitle is-5">{{ product.price }} 원</p>
          </div>

          <footer class="card-footer">
            <!-- 장바구니 담기 -->
            <button 
              v-if="isAuthenticated" 
              class="button is-link card-footer-item" 
              @click="addToCart(product)"
            >
              장바구니 담기
            </button>

            <!-- 로그인하지 않은 경우 -->
            <p v-else class="card-footer-item">로그인 후 장바구니에 담을 수 있습니다.</p>

            <!-- 구매하기 버튼 -->
            <router-link v-if="isAuthenticated" 
              :to="{ name: 'ProductView', params: { id: product.id } }" 
              class="button is-info card-footer-item"
            >
              구매하기
            </router-link>
          </footer>
        </div>
      </div>
    </div>

    <!-- 관리자일 경우 상품 등록 폼 표시 -->
    <div v-if="isAdmin" class="container">
      <div class="card-content">
        <h3 class="title is-4">새로운 상품 등록</h3>
        <form @submit.prevent="submitNewProduct">
          <div class="field">
            <label class="label">상품명</label>
            <div class="control">
              <input class="input" v-model="newProduct.name" type="text" required />
            </div>
          </div>

          <div class="field">
            <label class="label">상품 설명</label>
            <div class="control">
              <textarea class="textarea" v-model="newProduct.description" required></textarea>
            </div>
          </div>

          <div class="field">
            <label class="label">가격</label>
            <div class="control">
              <input class="input" v-model="newProduct.price" type="number" required />
            </div>
          </div>

          <div class="field">
            <label class="label">재고 수량</label>
            <div class="control">
              <input class="input" v-model="newProduct.stock" type="number" required />
            </div>
          </div>

          <div class="field">
            <label class="label">상품 이미지</label>
            <div class="control">
              <input class="input" type="file" @change="handleFileUpload" />
            </div>
          </div>

          <div class="field">
            <div class="control">
              <button type="submit" class="button is-primary">상품 등록</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import ProductService from '../services/ProductService'
import { useAuthStore } from '../store/auth'  // auth store 사용하여 로그인 상태 관리
import CartService from '../services/CartService'  // 장바구니 서비스 import

const authStore = useAuthStore()

// computed를 사용하여 isAuthenticated 및 isAdmin 상태를 반영
const isAuthenticated = computed(() => authStore.isAuthenticated)
const isAdmin = computed(() => {
  console.log('Is Admin:', authStore.user?.role === 'ADMIN');  // 로그로 체크
  return authStore.user && authStore.user.role === 'ADMIN'  // 관리자인지 여부를 체크
})
// 상태 변수
const products = ref([])
const error = ref(null)
const newProduct = ref({
  name: '',
  description: '',
  price: 0,
  stock: 0,
  img: null
})
const file = ref(null)

// 제품 목록 불러오기 함수
async function fetchProducts() {
  try {
    products.value = await ProductService.fetchProducts() // ProductService에서 제품 목록을 가져옵니다
  } catch (err) {
    error.value = '제품 목록을 불러오는 데 실패했습니다. 네트워크 상태를 확인하세요.'
    console.error(err)
  }
}

// 장바구니에 제품 추가 함수
async function addToCart(product) {
  if (!isAuthenticated.value) {
    alert('로그인 후 장바구니에 담을 수 있습니다.')
    return
  }

  try {
    // 장바구니에 제품을 추가하는 로직
    const response = await CartService.addToCart(product)
    console.log('Product added to cart:', response)
    alert('장바구니에 상품이 추가되었습니다.')
  } catch (err) {
    console.error('Error adding product to cart:', err)
    alert('장바구니에 상품을 추가하는데 실패했습니다.')
  }
}

// 새로운 상품 등록 함수
async function submitNewProduct() {
  const formData = new FormData()
  formData.append('name', newProduct.value.name)
  formData.append('description', newProduct.value.description)
  formData.append('price', newProduct.value.price)
  formData.append('stock', newProduct.value.stock)
  if (file.value) {
    formData.append('img', file.value)
  }

  try {
    await ProductService.createProduct(formData)
    alert('새로운 상품이 등록되었습니다.')
    fetchProducts()  // 상품 목록 갱신
    newProduct.value = { name: '', description: '', price: 0, stock: 0, img: null }
    file.value = null
  } catch (err) {
    console.error('상품 등록 실패:', err)
    alert('상품 등록에 실패했습니다.')
  }
}

// 이미지 업로드 처리
function handleFileUpload(event) {
  file.value = event.target.files[0]
}

onMounted(() => {
  fetchProducts()  // 제품 목록을 컴포넌트가 로드될 때 가져옵니다.
  authStore.initialize();  // 앱이 처음 로드될 때 localStorage에서 상태를 초기화
})
</script>

<style scoped>
.card {
  min-height: 250px;
  height: 260px;
  max-height: 300px;
}

.button.is-link {
  max-width: 120px;
  display: inline-block;
  font-size: 0.85rem;
  margin-left: 28px;
  margin-top: 18px;
}

.button.is-info {
  max-width: 120px;
  display: inline-block;
  font-size: 0.85rem;
  margin-left: 28px;
  margin-top: 18px;
}

.card-footer-item {
  font-style: italic;
  color: #999;
  text-align: center;
}
</style>