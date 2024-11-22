<template>
  <div class="box">
    <h1 class="title">Event Handling</h1>
    <button @click="handleClick">Click Me</button><hr>
    <button @dblclick="handleDblClick">Double Click Me</button><hr>
    <div
      @mouseover="handleMouseOver"
      @mouseout="handleMouseOut"
      class="notification"
    >
      Mouse Over/Out
    </div>
    <input @keydown="handleKeydown" @keyup="handleKeyup" placeholder="Key events" />
    <input @input="handleInput" placeholder="Input Event" />
    <input @change="handleChange" placeholder="Change Event" />
    <input @focus="handleFocus" @blur="handleBlur" placeholder="Focus/Blur" /><hr>
    <form @submit.prevent="handleSubmit">
      <input type="text" v-model="text" placeholder="Submit Form" />
      <button type="submit">Submit</button>
    </form>
    <div @scroll="handleScroll" style="height: 100px; overflow: auto;">
      <div style="height: 300px;">Scroll Me</div>
    </div>
    <div @contextmenu.prevent="handleContextMenu">Right Click Me (Context Menu)</div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const text = ref('');

    const handleClick = () => alert('Clicked!');
    const handleDblClick = () => alert('Double Clicked!');
    const handleMouseOver = () => console.log('Mouse Over');
    const handleMouseOut = () => console.log('Mouse Out');
    const handleKeydown = (event) => console.log('Key Down:', event.key);
    const handleKeyup = (event) => console.log('Key Up:', event.key);
    const handleInput = (event) => console.log('Input:', event.target.value);
    const handleChange = (event) => console.log('Change:', event.target.value);
    const handleFocus = () => console.log('Focused');
    const handleBlur = () => console.log('Blurred');
    const handleSubmit = () => alert(`Form submitted with value: ${text.value}`);
    const handleScroll = (event) => console.log('Scrolling:', event.target.scrollTop);
    const handleContextMenu = () => console.log('Context menu opened');

    return {
      text,
      handleClick,
      handleDblClick,
      handleMouseOver,
      handleMouseOut,
      handleKeydown,
      handleKeyup,
      handleInput,
      handleChange,
      handleFocus,
      handleBlur,
      handleSubmit,
      handleScroll,
      handleContextMenu,
    };
  },
};
</script>