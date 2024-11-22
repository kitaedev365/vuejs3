<template>
    <div>
      <h1>고객 목록</h1>
      <table class="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(customer, index) in customers" :key="index">
            <td>{{ customer.name }}</td>
            <td>{{ customer.email }}</td>
            <td>{{ customer.phone }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  // 고객 데이터를 하드코딩 (예시)
  const customers = ref([
    { name: '김기태', email: 'kkt@example.com', phone: '123-456-7890' },
    { name: '송미선', email: 'sms@example.com', phone: '987-654-3210' },
    { name: '유순복', email: 'ysb@example.com', phone: '555-123-4567' }
  ]);
  </script>
  
  <style scoped>
  h1 {
    margin-bottom: 20px;
  }
  
  .table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .table th, .table td {
    padding: 10px;
    border: 1px solid #ccc;
    text-align: left;
  }
  
  .table th {
    background-color: #f4f4f4;
  }
  </style>