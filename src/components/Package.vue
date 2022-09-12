<template>

  <div style="padding-left: 50px;padding-top: 50px">

    <!-- Form -->
    <el-button type="success" @click="add()"
    >添加行李
    </el-button
    >

  </div>

  <el-dialog v-model="dialogFormVisible" title="Shipping address">

    <el-form :model="form">

      <el-form-item label="区域">
        <el-select v-model="form.district" placeholder="请选择区域">
          <el-option label="区域一" value=1 />
          <el-option label="区域二" value=2 />
          <el-option label="区域三" value=3 />
          <el-option label="区域四" value=4 />
          <el-option label="区域五" value=5 />
        </el-select>
      </el-form-item>


      <el-form-item label="重量（KG）">
        <el-input v-model="form.weight"/>
      </el-form-item>

      <el-form-item label="尺寸（CM）">
        <el-input v-model="form.size"/>
      </el-form-item>



    </el-form>

    <div style="color: dimgray;font-size: xx-small">
      注：<br/>
      区域一：美洲（除美国/加拿大外）/加勒比海地区与欧洲/非洲/中东/亚洲/西南太平洋之间的航线；<br/>
      区域二：欧洲/中东与非洲/亚洲/西南太平洋之间航线；日本与西南太平洋之间航线；日本/西南太平洋与亚洲（不含日本及西南太平洋）/非洲之间航线；<br/>
      区域三：加拿大与美洲（除美国/加拿大外）/加勒比海地区/欧洲/非洲/中东/亚洲/西南太平洋之间航线；<br/>
      区域四：美国（含夏威夷）与美洲（除美国外）/加勒比海地区/欧洲/非洲/中东/亚洲/西南太平洋之间航线；<br/>
      区域五：非洲与亚洲（除日本外)之间航线；欧洲与中东之间航线；亚洲（除日本)内航线；美洲（除美国/加拿大）及加勒比海地区内航线；上述未列明的航线;<br/>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save()"
        >保存</el-button
        >
      </span>
    </template>



  </el-dialog>


  <br>
  <br>


  <div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="district" label="区域" width="180"/>
      <el-table-column prop="weight" label="重量（KG）" width="180"/>
      <el-table-column prop="size" label="尺寸（CM）"/>

      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >Edit</el-button
          >
          <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.row.id)"
          >Delete</el-button
          >
        </template>
      </el-table-column>

    </el-table>


  </div>

  <br/>
  <br/>
  <div style="padding-left: 20px"> 总价格： <br/>
    人民币：{{price[0]}}       <br/>
    或美元：{{price[1]}}
  </div>

</template>

<script>
import request from "core-js/internals/queue";
import axios from "axios";

export default {
  name: "Package",

  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: '140px',
      form: {
        region: '',
        weight: '',
        size: '',
        district: 0
      },

      tableData: [],
      price: []
    }
  },

  mounted() {
    this.init()
  },

  methods: {
    init() {
      axios.get("/api/bag").then(res => {
        console.log(res)
        this.tableData = res.data
        this.calculator()
      })
    },

    add() {
      this.dialogFormVisible = true
      this.form = {}
    },

    save() {
      this.dialogFormVisible = false
      axios.post("/api/bag", this.form).then(res => {
        if(res.data===1){
          this.$message.success("添加成功");
        }else{
          this.$message.error("改行李过重或过大，不能托运");
        }
        this.init()
      })
    },
    calculator(){
      axios.get("/api/bag/calculator").then(res => {
        this.price = res.data
      })
    },

    handleEdit(row){

    },

    handleDelete(id){
      console.log(id)
      axios.delete('/api/bag/'+id).then(res => {
        this.$message.success("删除成功");
        this.init()
      })
    }

  }

}

</script>

<style scoped>

</style>
