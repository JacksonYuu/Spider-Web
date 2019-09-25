
		
		$('#myTab li').bind("click", function() {
			$(this).addClass("tabactive").siblings().removeClass("tabactive");
		});
		
		function orderChange(orderid){
			location.href=contextPath+"/course/courseinfo/"+orderid;
		}	
		
