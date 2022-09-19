<template>
  <Header/>
  <router-view/>
</template>

<style>

</style>

<script>
import Header from "@/components/Header";
import Package from "@/components/International";

export default {
  name:"layout",
  components:{
    Package,
    Header,
  }
}
</script>
