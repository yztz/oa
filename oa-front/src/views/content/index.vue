<template>
  <div class="user-container">
    <div class="global-container">
      <el-select
        style="margin-right: 10px"
        value-key="id"
        @change="load"
        placeholder="请选择分类"
        v-model="categoryID">
        <el-option
          v-for="item in categories"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      <el-button ref="refresh" @click="delayLoad" icon="el-icon-refresh-right"></el-button>
      <el-button class="add-btn" @click="openEditor(null)" type="primary">添加产品
      </el-button>
      <transition name="el-fade-in">
        <el-button @click="deleteSelections" v-show="selections && selections.length > 0" type="danger" class="del-sel">
          删除选中
        </el-button>
      </transition>
    </div>

    <el-dialog
      :close-on-click-modal="false"
      :visible.sync="editorVisible"
      :title="editorTitle">
      <el-form
        ref="editor"
        :rules="editorRules"
        :model="this.selected">

        <el-form-item prop="name" label="货号">
          <el-input v-model="selected.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="comment" label="备注">
          <el-input v-model="selected.comment" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="categoryID" label="类别">
          <el-select
            @change="$forceUpdate()"
            v-model="selected.categoryID">
            <el-option
              v-for="item in categories"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="picName" label="图片">
          <el-upload
            ref="upload"
            action="#"
            :file-list="fileList"
            :http-request="uploadFile"
            :limit="1"
            list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
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
        :data="products">

        <el-table-column
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
          align="center"
          prop="name"
          label="产品货号"/>

        <el-table-column
          align="center"
          prop="time"
          label="上架时间"/>


        <el-table-column
          align="center"
          prop="comment"
          label="备注"/>

        <el-table-column
          align="center"
          fixed="right"
          label="操作">
          <template v-slot="scope">
            <el-button @click="openEditor(scope.row)" type="primary" size="small">编辑</el-button>
            <el-button @click="deleteProduct(scope.row)" type="danger" size="small">删除</el-button>
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
import {queryAll} from '@/api/category'
import {query, add, update, deleteProducts} from '@/api/product'
import {getUploadUrl, upload, getPicture, getPictureURL} from '@/api/file'
import {validUsername} from "@/utils/validate";

const EDITOR_MODE_EDIT = 0
const EDITOR_MODE_NEW = 1
export default {
  name: "index",
  computed: {
    uploadURL() {
      return getUploadUrl()
    },

    editorNew() {
      return this.mode === EDITOR_MODE_NEW
    },

    editorTitle() {
      return this.mode === EDITOR_MODE_EDIT ? '产品编辑' : '添加产品'
    },
  },


  data() {
    const validateName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入货号'))
      } else {
        callback()
      }
    }
    const validatePicname = (rule, value, callback) => {
      if(this.$refs.upload.uploadFiles.length === 0)
        callback(new Error('请上传文件'))
      callback()
    }
    return {
      mode: -1,
      loading: false,
      editorVisible: false,
      selected: {},
      selections: [],
      categories: [],
      categoryID: '',
      products: [],
      username: '',
      pageIndex: 1,
      totalPage: 0,
      pageSize: 10,
      fileList: [],
      editorRules: {
        name: [{required: true, trigger: 'blur', validator: validateName}],
        picName: [{required: true, trigger: 'blur', validator: validatePicname}],
      }
    }
  },


  mounted() {
    this.load()
    this.getCategories();
  },
  methods: {

    success(msg) {
      this.$message.success(msg)
    },

    getCategories() {
      queryAll().then(res => {
        this.categories = res.data
      })
    },

    handleSelectionChange(val) {
      this.selections = val;
    },


    uploadFile(params) {
      console.log(params)
      let file = params.file
      let fileType = file.type
      let isImage = fileType.indexOf('image') !== -1

      console.log(this.$refs.upload.uploadFiles)
      if (!isImage) {
        this.$refs.upload.uploadFiles = []
        return
      }
      let formData = new FormData();
      formData.append("file", file)

      return upload(formData).then(res => {
        console.log(res)
        this.selected.picName = res.data
      })
    },

    __delete(items) {
      let ids = items.map(item => item.id)
      deleteProducts({ids}).then(res => {
        this.load()
        this.$refs.table.clearSelection()
        this.success(res.message)
      })
    },

    deleteProduct(item) {
      this.$confirm('确认删除此产品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() => {
        this.__delete([item])
      }).catch(()=>{

      })
    },

    deleteSelections() {
      this.$confirm('确认删除所有选中产品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() => {
        this.__delete(this.selections)
      }).catch(()=>{

      })
    },

    openEditor(item) {
      this.mode = item == null ? EDITOR_MODE_NEW : EDITOR_MODE_EDIT
      this.selected = {...item}
      this.selected.categoryID = item ? item.categoryID : this.categoryID
      if (item) {
        this.fileList.push({name: item.picName, url: getPictureURL(item.picName)})
      }
      this.editorVisible = true
    },

    closeEditor(save) {
      if (save) {
        this.$refs.editor.validate(valid => {
          if(valid) {
            this.$refs.upload.clearFiles()
            this.fileList.length = 0
            if (this.mode === EDITOR_MODE_EDIT) { // 编辑模式
              update(this.selected).then(res => {
                this.load()
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
            return false
          }
        })
      } else {
        this.$refs.upload.clearFiles()
        this.fileList.length = 0
        this.editorVisible = false
      }
    },

    __load() {
      query({
        cid: this.categoryID,
        pageIndex: this.pageIndex,
        pageSize: this.pageSize
      }).then(res => {
        let data = res.data;
        console.log(data)
        this.products = data.data
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
  position: relative;
  margin: 20px;
  width: 100%;

  //.add-btn {
  //  position: absolute;
  //  right: 50px;
  //}
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
