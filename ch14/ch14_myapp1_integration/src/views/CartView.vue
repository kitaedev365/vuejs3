<template>
  <div>
    <h2 class="title is-3">{{ username }}님 장바구니</h2>
    <div v-if="cartItems.length === 0">Your cart is empty</div>
    <div v-for="item in cartItems" :key="item.id" class="box">
      <div class="columns">
        <div class="column is-half">{{ item.product.name }}</div>
        <div class="column is-one-quarter">{{ item.product.price }}원</div>
        <div class="column is-one-quarter">
          <button @click="removeFromCart(item)" class="button is-danger">Remove</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const cartItems = ref([])
const username = localStorage.getItem("username"); // 실제 사용자 이름으로 대체하거나 props로 받기

async function fetchCartItems() {
  try {
    const response = await axios.get(`http://localhost:8084/api/carts/user/${username}`)
    cartItems.value = response.data
  } catch (error) {
    console.error('Error fetching cart items:', error)
  }
}

async function removeFromCart(item) {
  try {
    await axios.delete(`http://localhost:8084/api/carts/${item.id}`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('user')}`
      }
    })
    cartItems.value = cartItems.value.filter(cartItem => cartItem.id !== item.id)
  } catch (error) {
    console.error('Error removing item from cart:', error)
  }
}

// 컴포넌트가 마운트되었을 때 데이터 가져오기
onMounted(fetchCartItems)
</script>