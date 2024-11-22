<template>
    <div class="box">
      <h1 class="title">Inline Events</h1><hr>
      <button @click="showAlert">Click Inline</button><hr>
      <button @click="handleClick('With Data')">Click with Data Binding</button><hr>
      <button @click.prevent="handleClick('With Prevent')">Click with Prevent</button>
    </div>
  </template>
  <script>
  export default {
    setup() {
      const showAlert = () => {
        window.alert('Button clicked!');
      };
      const handleClick = (message) => {
        alert(message);
      };
      return {
        showAlert, handleClick,
      };
    },
  };
  </script>  