<template>

  <div style="font-weight: bold;color: aqua;text-align: center;line-height: 150px;font-size: xxx-large" >
   国航行李计算器
  </div>

    <el-menu
        :default-active="$route.path"
        class="el-menu-demo"
        router
        mode="horizontal"
        @select="handleSelect"
    >
      <el-menu-item index="/domestic"> 国内航班 </el-menu-item>
      <el-menu-item index="/international"> 国际航班 </el-menu-item>

    </el-menu>


</template>

<script>
export default {
  name: "Header",

  methods: {
    handleSelect(){

    }
  }
}
</script>

<style scoped>

</style>
