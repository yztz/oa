<template>
  <div class="user-container">
    <div class="global-container">
      <el-button ref="refresh" @click="delayLoad" icon="el-icon-refresh-right" class="add-new"></el-button>
      <el-button @click="openEditor(null)" type="primary" class="add-new">添加用户</el-button>
      <transition name="el-fade-in">
        <el-button @click="deleteSelections" v-show="selections && selections.length > 0" type="danger" class="del-sel">
          删除选中
        </el-button>
      </transition>
    </div>

    <el-dialog
      :visible.sync="editorVisible"
      :title="editorTitle">
      <el-form
        ref="editor"
        :model="this.selected">
        <el-form-item prop="userName" label="用户名">
          <el-input v-model="selected.userName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input v-model="selected.password" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="电话号码">
          <el-input v-model="selected.phone" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="管理员">
          <el-switch v-model="selected.userType" active-value="1" inactive-color="0" autocomplete="off"></el-switch>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="closeEditor(false)">取 消</el-button>
        <el-button @click="closeEditor(true)" type="primary">确 定</el-button>
      </div>
    </el-dialog>
    <div class="table-container">
      <el-table
        v-loading="loading"
        ref="table"
        @selection-change="handleSelectionChange"
        class="table"
        row-key="id"
        :border="true"
        :stripe="true"
        :data="users">

        <el-table-column
          label="选择"
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
          width="300"
          align="center"
          prop="id"
          label="ID"/>

        <el-table-column
          width="300"
          align="center"
          prop="userName"
          label="用户名"/>

        <el-table-column
          align="center"
          width="300"
          prop="password"
          label="密码"/>

        <el-table-column
          align="center"
          prop="phone"
          label="电话"/>

        <el-table-column
          align="center"
          fixed="right"
          label="操作">
          <template v-slot="scope">
            <el-button @click="openEditor(scope.row)" type="primary" size="small">编辑</el-button>
            <el-button @click="deleteUser(scope.row)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>
    <div class="page-container">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="load"
        :current-page.sync="pageIndex"
        :page-count="totalPage">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {query, deleteUsers, update, add} from "@/api/user";

const EDITOR_MODE_EDIT = 0
const EDITOR_MODE_NEW = 1
export default {
  name: "index",
  computed: {
    editorTitle() {
      return this.mode === EDITOR_MODE_EDIT ? '用户编辑' : '新建用户'
    }
  },

  data() {
    return {
      mode: -1,
      loading: false,
      editorVisible: false,
      backup: null,
      selected: {},
      selections: [],
      users: [],
      username: '',
      pageIndex: 1,
      totalPage: 0,
      pageSize: 10,
    }
  },
  mounted() {
    this.load();
  },
  methods: {

    success(msg) {
      this.$message.success(msg)
    },

    handleSelectionChange(val) {
      this.selections = val;
    },

    __delete(items) {
      let ids = items.map(item => item.id)
      deleteUsers({ids}).then(res => {
        this.load()
        this.$refs.table.clearSelection()
        this.success(res.message)
      })
    },

    deleteUser(item) {
      this.$confirm('确认删除此用户？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() => {
        this.__delete([item])
      }).catch(()=>{

      })
    },

    deleteSelections() {
      this.$confirm('确认删除所有选中用户？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() => {
        this.__delete(this.selections)
      }).catch(()=>{

      })
    },

    openEditor(item) {
      this.mode = item == null ? EDITOR_MODE_NEW : EDITOR_MODE_EDIT
      this.backup = item
      this.selected = {...item}
      this.editorVisible = true
    },

    closeEditor(save) {
      if (save) {
        if (this.mode === EDITOR_MODE_EDIT) { // 编辑模式
          update(this.selected).then(res => {
            Object.assign(this.backup, res.data)
            this.success(res.message)
            this.editorVisible = false
          })
        } else if (this.mode === EDITOR_MODE_NEW) { // 新建模式
          add(this.selected).then(res => {
            this.load()
            this.success(res.message)
            this.editorVisible = false
          })
        }
      } else {
        this.editorVisible = false
      }
    },

    __load() {
      query({
        username: this.username,
        pageIndex: this.pageIndex,
        pageSize: this.pageSize
      }).then(res => {
        let data = res.data;
        console.log(data)
        this.users = data.data
        this.pageIndex = data.pageIndex
        this.totalPage = data.totalPage
        this.pageSize = data.pageSize
        this.loading = false
        this.$refs.refresh.$el.blur()
      }).catch(err => {
        console.log("load err" + err)
      });
    },

    load() {
      this.loading = true
      this.__load()
    },

    delayLoad() {
      this.loading = true
      setTimeout(this.__load, 500)
    }
  }
}
</script>

<style lang="scss" scoped>
.user-container {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}
.global-container {
  margin: 20px;
}

.table-container {
  width: 100%;

  .table {
    margin-left: 20px;
  }
}

.page-container {
  width: 100%;
  display: flex;
  justify-content: center;
  bottom: 20px;
  position: absolute;
}

</style>
