	$(function(){
		$("#createLiveForm").Validform({
			ajaxPost: true,
			tiptype: 3,
			datatype: {
				"time":function(gets,obj,curform,regxp){
					// 参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
					if(30<=gets && gets<=1440){return true;}
					return false;
				}
			},
			callback: function(data) {
				$("#Validform_msg").hide();
				$("#createLiveModal").modal("hide");
				if(data.success){
					getunits();
				}else{
					alert(data.msg);
				}
			}
		});
	});

	// 显示model
	function showCreateLive(){
		if(typeof($("#tree").treeview(true).getSelected()[0]) == "undefined"){
			alert("请选择节");
			return;
		}
		if(typeof($("#tree").treeview(true).getSelected()[0].parentId) == "undefined"){
			alert("请选择节");
			return;
		}
		var chapid = getchapid();
		$("#createLiveForm")[0].reset(); // 清空样式
		$("#live_chapterid").val(chapid);
		$("#createLiveModal").modal("show");
	}
	
