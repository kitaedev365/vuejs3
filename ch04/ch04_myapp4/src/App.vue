<template>
  <div>
    <BaseComponent />
    <ProviderComponent>
      <InjectedComponent />
    </ProviderComponent>
    <DynamicComponent />
    <TeleportComponent />
    <AsyncComponent />
  </div>
</template>
<script setup>
import BaseComponent from './components/BaseComponent.vue';
import ProviderComponent from './components/ProviderComponent.vue';
import InjectedComponent from './components/InjectedComponent.vue';
import DynamicComponent from './components/DynamicComponent.vue';
import TeleportComponent from './components/TeleportComponent.vue';
import AsyncComponent from './components/AsyncComponent.vue';
</script>
<style>
#app {
  width:960px;
  margin: 0 auto;
}
body {
  font-family: Arial, sans-serif;
}
</style>