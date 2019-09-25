		
		
		function programmes(){
			$.ajax({
				url : contextPath+"/course/getplanbyuser",
				type : "post",
				success : function(s) {
					if(s.success){
						var list = s.other;
						if(list.length>=1){
							var json=list[0];
							var filename=json.planname;
							var filepath=json.filepath;
							docview(filename,filepath);
						}
					}else{
						alert("文件不存在");
					}				
				}
			});
		}
		
		function docview(filename,filepath){
			var strFileUrl=window.location.protocol +"\/\/"+window.location.host+filepath;
			var strOfficeApps="http://docview.1daoyun.com"; 
			//var strFileUrl = "http://dx.1daoyun.com/upload/train/1503325857970.xlsx"; // 测试文件地址
			/* if(strFileUrl.indexOf("dx.1daoyun.com") != -1){ 	*/
				var strUrl =strOfficeApps+"/op/embed.aspx?src="+encodeURIComponent(strFileUrl);
				$("#strurl").attr('src',strUrl)
				$("#myModalLabel").html(filename);
				$("#read").modal("show");
			/* }else{
				alert("预览文件不在dx.1daoyun.com域名下，不支持预览");
			} */
		}