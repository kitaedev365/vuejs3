<template>
    <div class="box">
      <h1 class="title">Event Modifiers</h1><hr>
      <button @click.stop="handleStop">Stop Propagation</button><hr>
      <button @click.prevent="handlePrevent">Prevent Default</button><hr>
      <button @click.once="handleOnce">Click Once</button><hr>
      <input @keyup.self="handleSelfKeyup" placeholder="Keyup only on self" /><hr>
      <div @scroll.passive="handlePassiveScroll" style="height: 100px; overflow: auto;">
        <div style="height: 300px;">Passive Scroll</div>
      </div>
    </div>
  </template>
  <script>
  export default {
    setup() {
      const handleStop = () => alert('Propagation stopped!');
      const handlePrevent = () => alert('Default prevented!');
      const handleOnce = () => alert('Clicked once!');
      const handleSelfKeyup = (event) => console.log('Keyup on input:', event.key);
      const handlePassiveScroll = (event) => console.log('Passive scrolling:', event.target.scrollTop);
      return {
        handleStop,handlePrevent,handleOnce,handleSelfKeyup,handlePassiveScroll,
      };
    },
  };
  </script>  