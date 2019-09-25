var tagnames = [ "" ];
$(function() {
	$("#hcourse").addClass("active")
	initpage();
});

var pageSize=10;

function initpage() {
	num_entries = $("#totalcount").val() == 0 ? 1 : $("#totalcount").val();
	$("#Pagination").pagination(num_entries, {
		num_edge_entries: 0,
		num_display_entries: 5,
		callback : pageselectCallback
	});
}
// 分页处理
function pagination(currentPageNo){
	var listCount = $("#totalcount").val() == 0 ? 1 : $("#totalcount").val(); // 总条数
	var pageCount = Math.ceil(listCount / pageSize); // 总页数
	if(pageCount == 1){ // 禁左右
		$("#Pagination").children("li").first().removeClass("onactive ").addClass("disabled");
		$("#Pagination").children("li:last-child").removeClass("onactive ").addClass("disabled");
	}else if(currentPageNo == pageCount){ // 禁右
		$("#Pagination").children("li:last-child").removeClass("onactive").addClass("disabled");
	}else if(currentPageNo == 1){ // 禁左
		$("#Pagination").children("li").first().removeClass("onactive").addClass("disabled");
	}
}

function checktext(str,check){
	var flag = str.indexOf(check);
	if (flag > 0) {
		return true;
	} else {
		return false;
	}
}

function pageselectCallback(page_index, jq) {
	var tmp = "";
	var currentPageNo = page_index + 1;
	var categoryname = $("#category").val();
	var org = $("#org").val();
	var status = $("#status").val();
	var data = {
		currentPageNo : currentPageNo,
		categoryname : categoryname,
		tagnames : tagnames,
		orgid : org,
		status : status
	};
	
	pagination(currentPageNo);
	
	$.ajax({
		url : contextPath+"/course/pagination",
		type : "post",
		data : data,
		traditional : true,
		success : function(s) {
			if (s.success) {
				var slist = s.other;
				json = eval(slist);
				for ( var i = 0; i < json.length; i++) {
					var describle =(json[i].describle == null||json[i].describle == "") ? "暂无描述" : json[i].describle;
					var img = json[i].thumb;
					if (!img) {
						img = contextPath+'/assets/images/coursePicture.png';
					} else {
						if (version == "0") {
							img = json[i].thumb;
						} else {
							var flag = checktext(img,"assets");
							if (flag == true) {
								img = json[i].thumb;
							} else {
								img = visiturl + json[i].thumb;
							}
						}
					}
					
					var headimg = json[i].headimg;
					if (!headimg) {
						headimg = contextPath+'/assets/images/user.jpg';
					} else {
						if (version == "0") {
							headimg = json[i].headimg;
						} else {
							var flag = checktext(headimg,"assets");
							if (flag == true) {
								headimg = json[i].headimg;
							} else {
								headimg = visiturl + json[i].headimg;
							}
						}
					}
						
					tmp += "<div class='col-md-12' style='margin:20px 0;background: #fff;padding: 23px 0 0 0;'>"
							+ "<div style=';margin: 0 auto;position: relative;padding-right:5px;padding-left:5px'>"
							+ "<li class='course-li' style='width:100%!important;height:auto;min-height:150px;'>"
							+ "<div class='col-md-3'><a href='"+contextPath+"/course/courseinfo/"+json[i].courseid+"'>"
							+ "<img src='"
							+ img
							+ "' class='img-responsive' style='width:100%;'>"
					
					tmp += "<div class='sgz-time'>"
							+ "</div>"
							+ "</a></div>"
							+"<div class='col-md-9'>"
							+ "<div class='' style='padding:0'>"
							+ "<div  style='overflow: hidden'><a style='font-size:20px;color:#333' title="+json[i].basecoursename+"["	+ json[i].coursename + "]"+ " href='"+contextPath+"/course/courseinfo/"+json[i].courseid+"'>"
							+  json[i].basecoursename +	 "</a><a style='font-size:20px;color:#333;'  title="+json[i].basecoursename+"["	+ json[i].coursename + "]"+ " href='"+contextPath+"/course/courseinfo/"+json[i].courseid+"'>"+"["	+ json[i].coursename + "]"+ "</a></div>"											  							
							tmp +="<div style='margin-top:10px;'><img src='"+ headimg +"' style='float: left; border-radius: 50%; width: 35px;height: 35px;'/>"
							if(json[i].realname==null|| json[i].realname==""){
								tmp +="<div style='margin-left: 19px;float:left;margin-top: 7px;' >"+json[i].username+"</div>"	
							}else{
								tmp +="<div style='margin-left:19px;float:left;margin-top: 7px;' >"+json[i].realname+"</div>"	
							}	
							var endtime =json[i].endtime;
							if(endtime==null){
								endtime="";
							}
							tmp +="<div style='margin-left:19px;float:left;margin-top: 7px;min-width:200px;' ><div style='height:20px;'><span style='float:left;font-size: 12px;color: #b7b7b7;'>"+ json[i].starttime +"</span><span style='float:right;font-size: 12px;color: #b7b7b7;'>"+ endtime +"</span></div>"
							+	" <div class='course-progress-bar'> <div class='progress-bar  progress-bar-success' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: "+ json[i].progress +"%;'></div></div></div><div class='clearfix'></div></div>"								
							
							tmp +="<div style='height:40px;overflow:hidden;margin-top:8px'>"+ describle+"</div>"
							if(json[i].logoimg==null|| json[i].logoimg==""){
								tmp +="<div class='course_pl' style='float:left;font-size:12px'><img src='"+ucurl+"/assets/images/jg.png' class='orgimg'/></div>"
							}else{
								if (version == "0") {
									tmp +="<div class='course_pl' style='float:left;font-size:12px'><img src='"+ucurl+ json[i].logoimg +"' class='orgimg'/></div>"
								} else {
									tmp +="<div class='course_pl' style='float:left;font-size:12px'><img src='"+visiturl+ json[i].logoimg +"' class='orgimg'/></div>"
								}
							} 
							tmp +="<div><div class='course_pl' style='float:left;font-size:12px' title='"+ json[i].orgname +"'>"
							if(json[i].orgname.length>16){
								tmp += json[i].orgname.substring(0,16) +"...</div>"
							}else{
								tmp += json[i].orgname + "</div>"
							}
							
							tmp +="<div class='course_pl' style='float:right;font-size:12px;margin-left: 40px;'><i class='glyphicon glyphicon-user'></i>"+ json[i].studentcount +"</div>"
							tmp += "<div class='clearfix'></div></div></div></div></li><div class='clearfix'></div></div></div>";
				}
				tmp += '<div class="clearfix"></div>'
				$("#course-list").html(tmp);
				$(".course-picture img").addClass("carousel-inner img-responsive");
			}
		}
	});
	return false;
}

function selCategory(obj) {
	$(".category").removeClass("active");
	$("#" + obj.id).addClass("active");
	$(".tag").removeClass("tagon");
	var categoryname = obj.title;
	$("#category").val(categoryname);
	gettotalcount();
}

function selOrg(obj) {
	$(".org").removeClass("active");
	$("#" + obj.id).addClass("active");
	$(".tag").removeClass("tagon");
	var orgid = obj.id;
	$("#org").val(orgid.substring(4,orgid.length));
	gettotalcount();
}

function selStatus(obj) {
	$(".status").removeClass("active");
	$("#" + obj.id).addClass("active");
	$(".tag").removeClass("tagon");
	var status = obj.id;
	$("#status").val(status.substring(7,status.length));
	gettotalcount();
}

function selTag(obj) {
	if (obj.id == "tag-0") {
		$(".tag").removeClass("active");
		$("#tag-0").addClass("active");
	} else if (obj.className == "cattag tag") {
		$("#tag-0").removeClass("active");
		$("#" + obj.id).addClass("active");
	} else if (obj.className == "cattag tag active") {
		$("#tag-0").removeClass("active");
		$("#" + obj.id).removeClass("active");
	}
	var tagsexist = $(".cattag.tag.active");
	if(tagsexist==""||tagsexist.length==0){
		$("#tag-0").addClass("active");
	}
	var tags = $(".cattag.tag.active");
	tagnames = new Array();
	for ( var i = 0; i < tags.length; i++) {
		tagnames[i] = tags[i].title;
	}
	gettotalcount();
}

function gettotalcount() {
	var categoryname = $("#category").val();
	var org = $("#org").val();
	var status = $("#status").val();
	var data = {
		currentPageNo : 1,
		categoryname : categoryname,
		tagnames : tagnames,
		orgid : org,
		status : status
	};
	$.ajax({
		url : contextPath+"/course/gettotalcount",
		type : "post",
		data : data,
		traditional : true,
		success : function(s) {
			$("#totalcount").val(s.other);
			initpage();
		}
	});
}