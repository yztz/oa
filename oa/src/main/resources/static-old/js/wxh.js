var wxh = {
	ctxPath: "",
	item: "",
	addCtx: function(ctx){
		if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
	},
	/**
	 * 关闭layer弹出层
	 */
	shutdownLayer: function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	},
	/**
	 * 检查表中选项是否选中
	 */
	check: function(table_id){
		var selected = $("#" + table_id).bootstrapTable('getSelections');
		if(selected.length == 0){
			layer.msg("请先选中表格中的某一记录！",{time:2000,offset:"200px"})
			return false;
		}else{
			this.item = selected[0];
			return true;
		}
	},
	
}



















