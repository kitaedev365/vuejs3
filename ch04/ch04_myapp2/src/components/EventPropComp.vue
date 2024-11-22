<template>
    <div class="box">
      <h1 class="title">Event Propagation</h1>
      <div @click="handleParentClick">
        Parent
        <button @click.stop="handleChildClick">Child (Stops Propagation)</button>
      </div>
    </div>
  </template>
  <script>
  export default {
    setup() {
      const handleParentClick = () => {
        alert('Parent clicked');
      };
      const handleChildClick = () => {
        alert('Child clicked');
      };
      return {
        handleParentClick,
        handleChildClick,
      };
    },
  };
  </script>  