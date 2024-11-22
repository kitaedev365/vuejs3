<template>
  <div>
    <div @click="handleParentClick" class="container parent">
      Parent
      <button @click.stop="handleButtonClick" class="btn btn-primary child">
          Click Me
      </button>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    handleParentClick ( event ) {
      console.log(event.target);
      console.log(event.type);
      alert("Parent div clicked");
    },
    handleButtonClick () {
      console.log("Event Propagation");
      alert("Button clicked");
    }
  }
};
</script>
<style>
.container {
  padding: 32px;
  width: 240px;
}
</style>
