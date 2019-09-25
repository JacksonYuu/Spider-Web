function goCourse(categoryid, categoryname) {
		var data = {
			categoryid : categoryid,
			categoryname : categoryname
		}

		$.ajax({
			url : contextPath + "/course/courselist",
			type : "post",
			data : data,
			traditional : true,
			success : function(s) {

			}
		});
	}