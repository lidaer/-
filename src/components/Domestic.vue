<template>

  <div style="padding-left: 50px;padding-top: 50px">

    <div>
      票类型：
      <el-select v-model="ticket" placeholder="票类型">
        <el-option label="头等" value=1 />
        <el-option label="公务" value=2 />
        <el-option label="经济" value=3 />
      </el-select>

      会员卡类型
      <el-select v-model="card" placeholder="会员卡类型" >
        <el-option label="凤凰知音终身白金卡、白金卡" value=1 />
        <el-option label="凤凰知音金卡、银卡" value=2 />
        <el-option label="星空联盟金卡" value=3 />
      </el-select>

      票价
      <el-input
          v-model="ticketPrice"
          autosize
          type="textarea"
          placeholder="Please input"
      />

    </div>


    <br/>
    <br/>

      <!-- Form -->
      <el-button type="success" @click="add()"
      >添加行李
      </el-button
      >

    <el-button type="success" @click="init()"
    >计算价格
    </el-button
    >


    <el-dialog v-model="dialogFormVisible" title="Shipping address">

      <el-form :model="form">


        <el-form-item label="重量（KG）">
          <el-input v-model="form.weight"/>
        </el-form-item>

        <el-form-item label="长（CM）">
          <el-input v-model="form.length"/>
        </el-form-item>

        <el-form-item label="宽（CM）">
          <el-input v-model="form.width"/>
        </el-form-item>

        <el-form-item label="高（CM）">
          <el-input v-model="form.height"/>
        </el-form-item>

      </el-form>


      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save()"
        >保存</el-button
        >
      </span>
      </template>

    </el-dialog>

  </div>


  <br>
  <br>


  <div>
    <el-table :data="tableData" stripe style="width: 100%">

      <el-table-column prop="weight" label="重量（KG）" width="180"/>
      <el-table-column prop="length" label="长（CM）"/>
      <el-table-column prop="width" label="宽（CM）"/>
      <el-table-column prop="height" label="高（CM）"/>

      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)"
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

  托运价格：{{price}}

</template>

<script>
import axios from "axios";

export default {
  name: "Domestic",
  data(){
    return{
      dialogFormVisible: false,
      form: {
        weight: "",
        length: "",
        width: "",
        height: "",
      },
      ticketPrice: "0",
      ticket: "1",
      card: "1",
      tableData: [],
      price: 0
    }
  },

  mounted() {
    this.init()
  },

  methods: {
    add(){
      this.dialogFormVisible=true,
      this.form={}
    },
    save() {
      this.dialogFormVisible = false
      axios.post("/api/domestic", this.form).then(res => {
        if(res.data===1){
          this.$message.success("添加成功");
        }else{
          this.$message.error("改行李过重或过大，不能托运");
        }
        this.init()
      })
    },

    init() {
      axios.get("/api/domestic").then(res => {
        console.log(res)
        this.tableData = res.data
      })
      this.getPrice()
    },

    handleDelete(id){
      console.log(id)
      axios.delete('/api/domestic/'+id).then(res => {
        this.$message.success("删除成功");
        this.init()
      })
    },
    handleEdit(row){
      this.form=row;
      this.dialogFormVisible=true;
      this.init()
    },

    getPrice(){
      axios.get('/api/domestic/getPrice'+"/"+this.card+"/"+this.ticket+"/"+this.ticketPrice).then(res => {
        this.price=res.data;
      })

    }
  }
}
</script>

<style scoped>

</style>
