<template>
    <div class="box">
      <h1 class="title">Event Object</h1><hr>
      <input @keyup="handleKeyup" placeholder="Key Events" /><hr>
      <button @click="handleClick">Click with Event Object</button>
    </div>
  </template>
  <script>
  export default {
    setup() {
      const handleKeyup = (event) => {
        console.log('Event type:', event.type);
        console.log('Key pressed:', event.key);
      };
      const handleClick = (event) => {
        event.preventDefault();
        console.log('Event target:', event.target);
        console.log('Event type:', event.type);
      };
      return {
        handleKeyup,
        handleClick,
      };
    },
  };
  </script>