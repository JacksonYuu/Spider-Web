(function() {
	$(document).ready(function() {
				if (localStorage.getItem("content") !== null) {
					$("#color-settings-body-color").attr("href",
							localStorage.getItem("content"));
				}
				if ((localStorage.getItem("contrast") !== null)
						&& !$("body").hasClass("contrast-background")) {
					$("body")[0].className = $("body")[0].className.replace(
							/(^|\s)contrast.*?(\s|$)/g, " ").replace(/\s\s+/g,
							" ").replace(/(^\s|\s$)/g, "");
					$("body").addClass(localStorage.getItem("contrast"));
				}
				$(".color-settings-body-color > a").hover(
						function() {
							$("#color-settings-body-color").attr("href",
									$(this).data("change-to"));
							return localStorage.setItem("content", $(this)
									.data("change-to"));
						});
				return $(".color-settings-contrast-color > a").hover(
						function() {
							$('body')[0].className = $('body')[0].className
									.replace(/(^|\s)contrast.*?(\s|$)/g, ' ')
									.replace(/\s\s+/g, ' ').replace(
											/(^\s|\s$)/g, '');
							$('body').addClass($(this).data("change-to"));
							return localStorage.setItem("contrast", $(this)
									.data("change-to"));
						});
			});

}).call(this);

$(function() {
	var count = $(".fch").height();
	$(".fch1").height(count);

	$("#main-nav ul li a").click(function() {
		$("#main-nav ul li a").removeClass("selected");
		$(this).addClass("selected");
	});

	$("#main-nav a").each(function() {
		$this = $(this);
		var a = window.location.pathname;
		if (a.indexOf($this[0].pathname) > -1) {
			$this.parent().addClass("left-li-background");
			$this.addClass("left-li-background1");
		}
	});
	$("#searchkeyword").keyup(function(){
	    if(event.keyCode == 13){
	    	search_file();
	    }
	});

	$("#openinfor").click(function(){
		$("#toggleinfor").toggle()
	})
});

function toggleshow(){
	$("#toggleinfor").show();
}

var i=0;
function lastcourseload(){
	if(i==0){
		$.ajax({
			type: "post",
			data: {
			},
			url: "/user/getlastcourse",
			success:function(s){
				var name = s.other[0];
				var courseid = s.other[1];
				var chapterid = s.other[2];
				var unitid = s.other[3];
				var str = "";
				str += "<span style=\"float:left;width:125px;color:#7B7B7B;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;\">"+name+"</span>";
				str += "<span style=\"float:right;\"><a href=\"/myself/"+courseid+"/"+chapterid+"/"+unitid+"/"+chapterid+"/studyenter\">继续</a></span>";
				$("#lastcourse").html(str);
				i++;
			}
		});
		i++;
	}
}

function togglehide(){
	$("#toggleinfor").hide();
}

function colorshow(id){
	$("#"+id).css('background-color','#E7E7E8');
}

function colorhide(id){
	$("#"+id).css('background-color','#f5f5f5');
}

var u = navigator.userAgent, app = navigator.appVersion;
var a=u.indexOf('Trident');
if(u.indexOf('Trident') > -1){
	/* alert('请切换至极速模式浏览') */
	if(!+'\v1' && !'1'[0]){ 
		alert("系统检测到您浏览器版本过低，可能会影响到您浏览页面建议使用极速模式浏览") 
		} 
	if(navigator.appVersion.match(/8./i)=="8."){alert("系统检测到您浏览器版本过低可能会影响到您浏览页面效果，建议使用'极速模式'浏览") }
	}

 function orgmanage(){
	location.href = contextPath+"/orgadmin/collagebasecouse";
}
 
 /* 输入学校名模糊匹配 
	*/

	function searchkeywordMatch(obj){
		$("#NameList").css('border','0px solid #e3e4e5');
		var str = $('#searchkeyword').val();
		var len = nameList.length;
		var arry = [];
		str=$.trim(str) 
		if(str==""){
			$("#NameList").empty();
			return;
		}
		for(var i=0;i<len;i++){
		    if(nameList[i].indexOf(str)>=0){
		        arry.push(nameList[i]);
		    }
		}
		$("#NameList").empty();
		if(arry.length != 0){
		for(var i=0;i< arry.length;i++){
			$("#NameList").append('<li class="dropdownMenu searchlist" style="cursor:pointer" onmousedown="clickDropDownMenu(this)">'+arry[i]+'</li>')
		}
		$("#NameList").css('display','block');
		$("#NameList").css('border','1px solid #e3e4e5');
		}
	}

	var nameList = ["Python语言","Linux服务器配置与管理"
	                ,"JSP开发","Java语言","云计算导论"
	                ,"Linux Shell编程实训","新能源汽车大数据分析案例开发"
	                ,"微信小程序","虚拟化与云计算平台构建"
	                ,"云安全技术与应用","Linux操作管理与应用"
	                ,"JavaWeb 云应用开发","云计算数据中心运维"
	                ,"云计算网络技术与应用","云存储技术与应用"
	                ,"腾讯云综合运维","Hadoop大数据平台构建与应用"
	                ,"大数据平台构建和运维","Unity虚拟现实开发教程","Hadoop大数据分析案例开发","Hadoop大数据数据处理"
	                ,"Java Web云应用开发/云存储技术与应用","软件定义网络SDN技术与实践","公有云综合实战（腾讯云）"
	                ,"虚拟化技术与应用","Docker容器技术与应用","OpenStack云计算基础架构平台技术与应用","Android云存储客户端开发"
	                ];
	 
	function clickDropDownMenu(data){
		$("#searchkeyword").val('');
		$("#searchkeyword").attr('value',$(data).text());
		$("#searchkeyword").val($(data).text());
		$("#NameList").css('display','none');
	}
	
	 function enterprisemanage(){
		 location.href = "/enterpriseadmin/enterpriseinfo";
	} 
	 


	//当前时间   course.js用到
	function getnowtime() {  
		var nowtime = new Date();  
		var year = nowtime.getFullYear();  
		var month = padleft0(nowtime.getMonth() + 1);  
    	var day = padleft0(nowtime.getDate());  
   		var hour = padleft0(nowtime.getHours());  
    	var minute = padleft0(nowtime.getMinutes());  
    	var second = padleft0(nowtime.getSeconds());  
    	var millisecond = nowtime.getMilliseconds(); millisecond = millisecond.toString().length == 1 ? "00" + millisecond : millisecond.toString().length == 2 ? "0" + millisecond : millisecond;  
    	return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;  
	}  
	//补齐两位数  
	function padleft0(obj) {  
		return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
	}  

	function search_file() {
				var searchtj = $('#search-tj').val();
				var searchtj = "kc";
				var pageNo = 1;
				var keyword = $('#searchkeyword').val();
				keyword=$.trim(keyword);
				if (keyword == '') {
					return;
				}
				keyword = encodeURIComponent(keyword,"utf-8");
				//alert(keyword);
				if (searchtj == 'kc') {
					location.href = "/course/searchCourseBykeyword/"+ pageNo + "/" + keyword;
				}
				if (searchtj == 'sc') {
					location.href = "/course/searchstorepaginationBykeyword/"+ pageNo + "/" + keyword;
				}
				if (searchtj == 'jy') {
					location.href = "/course/employlist/searchEmployBykeyword/"+ pageNo + "/" + keyword;
				}

			}

/*
 * 用途：检查输入字符串是否为空或者全部都是空格 输入：str 返回： 如果全是空返回true,否则返回false
 */
function isNull(str) {
	if (str == "")
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
/**
 * 检查两个字符是否相等
 * 
 * @return
 */
function isEquals(str1, str2) {
	if (str1 != str2) {
		return false;
	} else
		return true;
}
/*
 * 用途：检查输入手机号码是否正确 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
function checkMobile(s) {
	/*
	 * var regu =/^[1][3][0-9]{9}$/; var re = new RegExp(regu); if (re.test(s)) {
	 * return true; }else{ return false; }
	 */
	return /^(13|15|17|18)\d{9}$/i.test(s);
}
/*
 * 用途：检查输入对象的值是否符合E-Mail格式 输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
 */
function isEmail(str) {
	var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if (myReg.test(str))
		return true;
	return false;
}
/*
 * 用途：检查输入对象的值是否符合整数格式 输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
 */
function isInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}

/*
 * 用途：检查输入对象的值是否为数字  输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
 */
function isNumber(str) {
	var regu = /^\d+$/;
	return regu.test(str);
}


function isDateYYMMDD(dateString) {
	if (dateString.trim() == "")
		return true;
	// 年月日时分秒正则表达式
	var r = dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null) {
		return false;
	}
	return true;

}

/**
 * 判断输入框中输入的日期格式是否为年月日时分秒 即 yyyy-mm-dd hh:mi:ss
 */
function isDate(dateString) {
	if (dateString.trim() == "")
		return true;
	// 年月日时分秒正则表达式
	var r = dateString
			.match(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
	if (r == null) {
		// alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例 如：2008-08-08\n\r");
		return false;
	}
	var d = new Date(r[1], r[2] - 1, r[3], r[4], r[5], r[6]);
	var num = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[2]
			&& d.getDate() == r[3] && d.getHours() == r[4]
			&& d.getMinutes() == r[5] && d.getSeconds() == r[6]);
	if (num == 0) {
		// alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例 如：2008-08-08\n\r");
	}
	return (num != 0);

}

function accAdd(arg1,arg2){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
	m=Math.pow(10,Math.max(r1,r2)) 
	return (arg1*m+arg2*m)/m 
} 

function replaceTextarea1(str) {
	var reg = new RegExp("\r\n", "g");
	var reg1 = new RegExp("\n", "g");
	str = str.replace(reg, "</iebr>");
	str = str.replace(reg1, "</br>");
	return str;
}
function replaceTextarea2(str) {
	var reg = new RegExp("</iebr>", "g");
	var reg1 = new RegExp("</br>", "g");
	str = str.replace(reg, "\r\n");
	str = str.replace(reg1, "\n");
	return str;
}

//去html标签
function delHtmlTag(str){
	return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}

/**   
替换双引号
*/ 
function valueReplace(v){ 
	v=v.toString().replace(new RegExp('(["\"])', 'g'),"\\\""); 
	return v; 
} 