	$(function() {
		$(".list-group-item").removeClass("active");
		$("#skillmap").addClass("active");
		$("#img_myskills").attr("src",contextPath+"/assets/images/xxmb2.png");
		programmes();
	});
	
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
					}else{
						var temp ="<div>预览文件不存在</div>";
						$("#ifarme").html(temp);
					}
				}else{
					var temp ="<div>预览文件不存在</div>";
					$("#ifarme").html(temp);
				}				
			}
		});
	}
	
	function docview(filename,filepath){
		var strFileUrl=window.location.protocol +"\/\/"+window.location.host+filepath;
		var strOfficeApps="http://docview.1daoyun.com"; 
		//var strFileUrl = "http://dx.1daoyun.com/upload/train/1503325857970.xlsx"; // 测试文件地址
		/* if(strFileUrl.indexOf("dx.1daoyun.com") != -1){ */
			var temp="<iframe id='strurl' style='width: 100%; height: -webkit-fill-available;'></iframe>"
			$("#ifarme").html(temp);
			var strUrl =strOfficeApps+"/op/embed.aspx?src="+encodeURIComponent(strFileUrl);
			$("#strurl").attr('src',strUrl)
		/* }else{
			var temp ="<div style='width: 100%; ' >预览文件不在dx.1daoyun.com域名下，不支持预览</div>";
			$("#ifarme").html(temp);
		} */
	}
	