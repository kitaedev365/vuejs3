<template>
  <div>
    <dashboard-header />
    <main class="grid-container">
      <div class="grid-x grid-margin-x">
        <div class="cell medium-12">
          <h1>Vue Dashboard</h1>
          <dashboard-main />
          <parent-component />
        </div>
      </div>
    </main>
    <dashboard-footer />
    <notification-component />
  </div>
</template>
<script setup>
import DashboardHeader from './components/DashboardHeader.vue';
import DashboardMain from './components/DashboardMain.vue';
import DashboardFooter from './components/DashboardFooter.vue';
import ParentComponent from './components/ParentComponent.vue';
import NotificationComponent from './components/NotificationComponent.vue';
</script>
<style scoped>
h1 {
  color: #333;
}
</style>