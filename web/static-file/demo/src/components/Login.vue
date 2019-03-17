<template>
  <div
    id="page"
    style="note"
  >
    <el-container style="height:100%">
      <el-header style="height:15%"></el-header>
      <el-main style="height:75%">
        <el-aside
          width="400px"
          style="float:right"
          class="login-aside"
        >
          <span class="love-span">爱情总是在不经意之间来临</span>
          <div class="demo-input-suffix">账号：
            <el-input
              placeholder="请输入内容"
              v-model="input10"
              clearable
              class="account-input"
              style="account"
            >
            </el-input>
          </div>
          <div class="demo-input-suffix">账号：
            <el-input
              placeholder="请输入密码"
              v-model="input11"
              show-password
              class="passwd-input"
              style="passwd"
            ></el-input>
          </div>
          <el-button type="primary">注册</el-button>
          <el-button type="success">登录</el-button>
        </el-aside>

      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
console.log("执行login.vue");
import Vue from "vue";

export default Vue.extend({
  name: "Login",
  methods(){

  },
  data() {
    return {
      msg: "Welcome to Your Vue.js App",
      note: {
        backgroundImage: "url(../assets/background.jpg)",
        backgroundPosition: "center center",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover"
      },
      account: "",
      passwd: ""
    };
  }
});
</script>

<style>
#page {
  width: 100%;
  height: 100%;
  background-image: url("../assets/background.jpg");
  height: 900px;
}
.el-header,
.el-footer {
  text-align: center;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  width: 400px;
  height: 400px;
  padding: 20px;
  box-sizing: unset;
}

.el-main {
  text-align: center;
  height: 100%;
}
body {
  margin: 0;
  padding: 0;
  height: 100%;
}
body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.account-input {
  height: 100px;
  width: 80%;
}
.passwd-input {
  height: 100px;
  width: 80%;
}
.login-aside {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.love-span {
  font-size: 56px;
}
</style>