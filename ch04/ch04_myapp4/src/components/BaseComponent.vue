<template>
  <div :class="$style.container">
    <h1>Vue 3 App with PureCSS</h1>
    <slot></slot> <!-- 기본 슬롯 -->
  </div>
</template>
<style module>
.container {
  padding: 20px;
  background-color: #f4f4f4;
}
</style>