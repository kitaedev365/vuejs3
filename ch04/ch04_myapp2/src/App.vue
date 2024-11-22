<template>
  <section class="section">
    <div class="container">
      <h1 class="title">Vue 3 Events Example</h1>
      <EventComp />
      <InlineEventComp />
      <EventHandleComp />
      <EventObjectComp />
      <EventPropComp />
      <EventModifierComp />
    </div>
  </section>
</template>
<script>
import EventComp from './components/EventComp.vue';
import InlineEventComp from './components/InlineEventComp.vue';
import EventHandleComp from './components/EventHandleComp.vue';
import EventObjectComp from './components/EventObjectComp.vue';
import EventPropComp from './components/EventPropComp.vue';
import EventModifierComp from './components/EventModifierComp.vue';
export default {
  components: {
    EventComp, InlineEventComp, EventHandleComp, EventObjectComp, EventPropComp, EventModifierComp,
  },
};
</script>