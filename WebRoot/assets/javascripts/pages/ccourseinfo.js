$(function(){
			$("#allli li").removeClass("u-curtab");
			$("#introduce").addClass("u-curtab");
			$('#indicatorContainer').radialIndicator({
			    initValue: 0,
			    percentage: true,  //显示%
			    barWidth: 3,
			    radius: 18 //设置内圆半径
			});
			isfav();
			getCourseProgress();
		})
		
		// 取课程的学习进度
		function getCourseProgress(){
			$.ajax({
				url : contextPath+"/course/getCourseProgress",
				type : "post",
				data : {
					userid : userid,
					courseid :courseid
				},
				success : function(s) {
					if(s.success){
						var result = s.other;
						var unitid = result.unitId;
						var unitName = result.unitName;
						var courseProgress = result.courseProgress;
						if(unitid > 0){
							$("#start").addClass("hidden");
					        $("#goon").removeClass("hidden");
						}else{
							$("#goon").addClass("hidden");
					        $("#start").removeClass("hidden");
						}
						// 跟新课程进度条
					 	var radialObj = $('#indicatorContainer').data('radialIndicator');
				        radialObj.animate(courseProgress * 100);
				        var url = contextPath+"/myself/"+courseid+"/gotounit/"+ unitid;
				        $("#goon").attr("href", url);
				        $("#lastplace").html(unitName);
					}else{ // 没有学习记录
						console.log(s.msg);
						var tag = false;
						// 取第一个单元
						var str_unit = $(".lesson-item")[0];
						if(str_unit){
							var str_item = str_unit.id;
							var index = str_item.indexOf('-');
							if(index != -1){
								var unitId = str_item.substring(index+1);
								var url = contextPath+"/myself/"+courseid+"/gotounit/"+ unitId;
								$("#start").attr("href", url);
							}else{
								tag = true;
							}
						}else{
							tag = true;
						}
						if(tag){
							$("#start").addClass("disabled");
						}
						$("#start").removeClass("hidden");
					}
				}
			});
		}
		
		function isfav(){
			var data = {
				courseid : courseid
			};
			$.ajax({
				url : contextPath+"/user/cancelfavorite",
				type : "post",
				data : data,
				success : function(s) {
					var temp="";
					if (s.success) {
						temp="<a class='btn btn-sm' href='"+contextPath+"/user/delfavorite/"+courseid+"' title='取消收藏'><i class='glyphicon glyphicon-star' style='color: black'></i></a>"
					}else{
						temp="<a class='btn btn-sm' href='"+contextPath+"/user/savefavorite/"+courseid+"' title='收藏'><i class='glyphicon glyphicon-star-empty' style='color: black'></i></a>"
					}
					$("#isfav").html(temp);
				}
			});
	}
	function quitecourse1(id,ispub){
		if(ispub==1){
			alert("私有课程不可退出");
		}else{
			location=contextPath+"/user/quitcourse/"+courseid;
		}
	}
	
	function check(courseid,unitid,type){	
		location.href=contextPath+"/myself/"+courseid+"/gotounit/"+unitid;		
	}
