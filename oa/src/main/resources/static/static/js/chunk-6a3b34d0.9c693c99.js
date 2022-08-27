(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6a3b34d0"],{"1b49":function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"user-container"},[n("div",{staticClass:"global-container"},[n("el-select",{staticStyle:{"margin-right":"10px"},attrs:{"value-key":"id",placeholder:"请选择分类"},on:{change:e.load},model:{value:e.categoryID,callback:function(t){e.categoryID=t},expression:"categoryID"}},e._l(e.categories,(function(e){return n("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1),n("el-button",{ref:"refresh",attrs:{icon:"el-icon-refresh-right"},on:{click:e.delayLoad}}),n("el-button",{staticClass:"add-btn",attrs:{type:"primary"},on:{click:function(t){return e.openEditor(null)}}},[e._v("添加产品 ")]),n("transition",{attrs:{name:"el-fade-in"}},[n("el-button",{directives:[{name:"show",rawName:"v-show",value:e.selections&&e.selections.length>0,expression:"selections && selections.length > 0"}],staticClass:"del-sel",attrs:{type:"danger"},on:{click:e.deleteSelections}},[e._v(" 删除选中 ")])],1)],1),n("el-dialog",{attrs:{"close-on-click-modal":!1,visible:e.editorVisible,title:e.editorTitle},on:{"update:visible":function(t){e.editorVisible=t}}},[n("el-form",{ref:"editor",attrs:{rules:e.editorRules,model:this.selected}},[n("el-form-item",{attrs:{prop:"name",label:"货号"}},[n("el-input",{attrs:{autocomplete:"off"},model:{value:e.selected.name,callback:function(t){e.$set(e.selected,"name",t)},expression:"selected.name"}})],1),n("el-form-item",{attrs:{prop:"comment",label:"备注"}},[n("el-input",{attrs:{autocomplete:"off"},model:{value:e.selected.comment,callback:function(t){e.$set(e.selected,"comment",t)},expression:"selected.comment"}})],1),n("el-form-item",{attrs:{prop:"categoryID",label:"类别"}},[n("el-select",{on:{change:function(t){return e.$forceUpdate()}},model:{value:e.selected.categoryID,callback:function(t){e.$set(e.selected,"categoryID",t)},expression:"selected.categoryID"}},e._l(e.categories,(function(e){return n("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),n("el-form-item",{attrs:{prop:"picName",label:"图片"}},[n("el-upload",{ref:"upload",attrs:{action:"#","file-list":e.fileList,"http-request":e.uploadFile,limit:1,"list-type":"picture"}},[n("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")])],1)],1)],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(t){return e.closeEditor(!1)}}},[e._v("取 消")]),n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.closeEditor(!0)}}},[e._v("确 定")])],1)],1),n("div",{staticClass:"table-container"},[n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"table",staticClass:"table",attrs:{"row-key":"id",border:!0,stripe:!0,data:e.products},on:{"selection-change":e.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection",width:"55"}}),n("el-table-column",{attrs:{align:"center",prop:"name",label:"产品货号"}}),n("el-table-column",{attrs:{align:"center",prop:"time",label:"上架时间"}}),n("el-table-column",{attrs:{align:"center",prop:"comment",label:"备注"}}),n("el-table-column",{attrs:{align:"center",fixed:"right",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(n){return e.openEditor(t.row)}}},[e._v("编辑")]),n("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(n){return e.deleteProduct(t.row)}}},[e._v("删除")])]}}])})],1)],1),n("div",{staticClass:"page-container"},[n("el-pagination",{attrs:{background:"",layout:"prev, pager, next","current-page":e.pageIndex,"page-count":e.totalPage},on:{"current-change":e.load,"update:currentPage":function(t){e.pageIndex=t},"update:current-page":function(t){e.pageIndex=t}}})],1)],1)},o=[],i=n("5530"),l=(n("d81d"),n("c405")),r=n("b775");function c(e){return Object(r["a"])({url:"/news/query",method:"get",params:e})}function s(e){return Object(r["a"])({url:"/news/add",method:"post",data:e})}function u(e){return Object(r["a"])({url:"/news/update",method:"post",data:e})}function d(e){return Object(r["a"])({url:"/news/delete",method:"get",params:e})}function f(e){return Object(r["a"])({url:"/file/upload",method:"post",data:e,headers:{"Content-Type":"multipart/form-data"}})}function p(e){return"/OASystem/api/file/fetch/"+e}function m(){return"/OASystem/api/file/upload"}n("61f7");var g=0,h=1,b={name:"index",computed:{uploadURL:function(){return m()},editorNew:function(){return this.mode===h},editorTitle:function(){return this.mode===g?"产品编辑":"添加产品"}},data:function(){var e=this,t=function(e,t,n){t?n():n(new Error("请输入货号"))},n=function(t,n,a){0===e.$refs.upload.uploadFiles.length&&a(new Error("请上传文件")),a()};return{mode:-1,loading:!1,editorVisible:!1,selected:{},selections:[],categories:[],categoryID:"",products:[],username:"",pageIndex:1,totalPage:0,pageSize:10,fileList:[],editorRules:{name:[{required:!0,trigger:"blur",validator:t}],picName:[{required:!0,trigger:"blur",validator:n}]}}},mounted:function(){this.load(),this.getCategories()},methods:{success:function(e){this.$message.success(e)},getCategories:function(){var e=this;Object(l["d"])().then((function(t){e.categories=t.data}))},handleSelectionChange:function(e){this.selections=e},uploadFile:function(e){var t=this;console.log(e);var n=e.file,a=n.type,o=-1!==a.indexOf("image");if(console.log(this.$refs.upload.uploadFiles),o){var i=new FormData;return i.append("file",n),f(i).then((function(e){console.log(e),t.selected.picName=e.data}))}this.$refs.upload.uploadFiles=[]},__delete:function(e){var t=this,n=e.map((function(e){return e.id}));d({ids:n}).then((function(e){t.load(),t.$refs.table.clearSelection(),t.success(e.message)}))},deleteProduct:function(e){var t=this;this.$confirm("确认删除此产品？","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(){t.__delete([e])})).catch((function(){}))},deleteSelections:function(){var e=this;this.$confirm("确认删除所有选中产品？","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(){e.__delete(e.selections)})).catch((function(){}))},openEditor:function(e){this.mode=null==e?h:g,this.selected=Object(i["a"])({},e),this.selected.categoryID=e?e.categoryID:this.categoryID,e&&this.fileList.push({name:e.picName,url:p(e.picName)}),this.editorVisible=!0},closeEditor:function(e){var t=this;e?this.$refs.editor.validate((function(e){if(!e)return!1;t.$refs.upload.clearFiles(),t.fileList.length=0,t.mode===g?u(t.selected).then((function(e){t.load(),t.success(e.message),t.editorVisible=!1})):t.mode===h&&s(t.selected).then((function(e){t.load(),t.success(e.message),t.editorVisible=!1}))})):(this.$refs.upload.clearFiles(),this.fileList.length=0,this.editorVisible=!1)},__load:function(){var e=this;c({cid:this.categoryID,pageIndex:this.pageIndex,pageSize:this.pageSize}).then((function(t){var n=t.data;console.log(n),e.products=n.data,e.pageIndex=n.pageIndex,e.totalPage=n.totalPage,e.pageSize=n.pageSize,e.loading=!1,e.$refs.refresh.$el.blur()})).catch((function(e){console.log("load err"+e)}))},load:function(){this.loading=!0,this.__load()},delayLoad:function(){this.loading=!0,setTimeout(this.__load,500)}}},v=b,y=(n("7ced"),n("2877")),x=Object(y["a"])(v,a,o,!1,null,"6508c157",null);t["default"]=x.exports},"7ced":function(e,t,n){"use strict";n("a9b7")},a9b7:function(e,t,n){},c405:function(e,t,n){"use strict";n.d(t,"c",(function(){return o})),n.d(t,"d",(function(){return i})),n.d(t,"e",(function(){return l})),n.d(t,"a",(function(){return r})),n.d(t,"b",(function(){return c}));var a=n("b775");function o(e){return Object(a["a"])({url:"/category/query",method:"get",params:e})}function i(){return Object(a["a"])({url:"/category/queryall",method:"get"})}function l(e){return Object(a["a"])({url:"/category/update",method:"post",data:e})}function r(e){return Object(a["a"])({url:"/category/add",method:"post",data:e})}function c(e){return Object(a["a"])({url:"/category/delete",method:"get",params:e})}}}]);