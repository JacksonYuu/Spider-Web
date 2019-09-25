
function forgetPwd(){
	location.href=contextPath+"/course/repassword";
}

$(function() {
	 if ($.cookie("rmbUser") == "true") {
		    $("#remember").attr("checked", true);
		    $("#username").val($.cookie("username"));
		    $("#password").val($.cookie("password"));
		    }
	
	$('input').keypress(function(e) {
		var key = e.which;
		if (key == 13) {
			login();
		}
	});
	
	$("#qqhover").hover(function(){
	    $("#qqimage1").show();
	    $("#qqimage").hide();
	},function(){
		 $("#qqimage1").hide();
		    $("#qqimage").show();
	});
	
	$("#wxhover").hover(function(){
	    $("#wximage1").show();
	    $("#wximage").hide();
	},function(){
		 $("#wximage1").hide();
		    $("#wximage").show();
	});
});



function login() {
	if($('#remember').is(':checked')) {
	      var str_username = $("#username").val();
	      var str_password = $("#password").val();
	      $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
	      $.cookie("username", str_username, { expires: 7 });
	      $.cookie("password", str_password, { expires: 7 });
	 }else{
	      $.cookie("rmbUser", "false", { expire: -1 });
	      $.cookie("username", "", { expires: -1 });
	      $.cookie("password", "", { expires: -1 });
	 }
	var username = $("#username").val();
	var password = $("#password").val();
	if ((username != "") || (password != "")) {
		if (username != "") {
			$(".yz1").addClass('hide');
		}
		if (password != "") {
			$(".yz2").addClass('hide');
		}
	}
	if ((username == "") || (password == "")) {
		if (username == "") {
			$(".yz1").removeClass('hide');
		}
		if (password == "") {
			$(".yz2").removeClass('hide');
		}
		return;
	}

	var data = {
		username : username,
		password : password
	};
	$.ajax({
		url : contextPath+"/course/login",
		type : "post",
		data : data,
		success : function(s) {
			if (s.success) {
			/* 	if(s.msg == 3){		
					//首页的跳转
					location.href="${request.contextPath}/";
				}else if(s.msg == 10){
					//完善专业班级的跳转
					location.href = "${request.contextPath}/user/majorselect";
				}else{
					//完善个人资料跳转
					location.href = "${request.contextPath}/user/improveinfo";
				} */		
				var rurl = s.other;
				if (rurl)
				{
					location.href = rurl;
				}
				else
				{
					location.href= contextPath+"/";											
				}
			}else {
				alert(s.msg);
			}
		}
	});
}