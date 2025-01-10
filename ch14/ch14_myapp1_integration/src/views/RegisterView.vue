<template>
    <div class="box">
      <h2 class="title is-3">Register</h2>
      <form @submit.prevent="handleRegister">
        <div class="field">
          <label class="label">Username</label>
          <div class="control">
            <input v-model="username" class="input" placeholder="Username" required />
          </div>
        </div>
        <div class="field">
          <label class="label">Email</label>
          <div class="control">
            <input v-model="email" type="email" class="input" placeholder="Email" required />
          </div>
        </div>
        <div class="field">
          <label class="label">Password</label>
          <div class="control">
            <input v-model="password" type="password" class="input" placeholder="Password" required />
          </div>
        </div>
        <button type="submit" class="button is-primary">Register</button>
      </form>
    </div>
  </template>
  <script setup>
  import { ref } from 'vue'
  import AuthService from '../services/AuthService'
  import { useRouter } from 'vue-router'
  const username = ref('')
  const email = ref('')
  const password = ref('')
  const router = useRouter()
  async function handleRegister() {
    try {
      await AuthService.register({ username: username.value, email: email.value, password: password.value })
      router.push('/login')
    } catch (error) {
      console.error('Registration failed', error)
    }
  }
  </script>  