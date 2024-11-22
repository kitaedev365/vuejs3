<template>
    <div class="box">
      <h1 class="title">Event Handlers</h1><hr>
      <button @click="handleClick">Method Handler</button><hr>
      <button @click="showInlineAlert">Inline Handler</button><hr>
      <input @input="handleInput" placeholder="Event Object Handling" />
    </div>
  </template>
  <script>
  export default {
    setup() {
      const handleClick = () => {
        alert('Method Handler Clicked!');
      };
      const showInlineAlert = () => {
        window.alert('Inline Handler');  // window.alert() 명시적으로 호출
      };
      const handleInput = (event) => {
        console.log('Input event:', event.target.value);
      };
      return {
        handleClick,
        showInlineAlert,
        handleInput,
      };
    },
  };
  </script>  