$(function() {
	$("#myfavorite").addClass('active');
	$("#img_mycourse").attr("src", contextPath + "/assets/images/wdkc2.png");
});

function unfavoritecourse(courseid) {
	var data ={courseid:courseid};
	$.ajax({
		url : contextPath + "/user/cancelfav",
		type : "post",
		data : data,
		success : function(s) {
			if (s.success) {
				location.reload();
			}
		}
	});
} 