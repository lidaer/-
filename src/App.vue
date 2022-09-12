<template>
  <Header/>
  <Package/>


</template>

<style>

</style>

<script>
import Header from "@/components/Header";
import Package from "@/components/Package";

export default {
  name:"layout",
  components:{
    Package,
    Header,
  }
}
</script>
