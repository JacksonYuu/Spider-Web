function tag(id) {
	if (id == "tag-finish") {
		$("#having").hide();
		$("#finish").show();
	} else {
		$("#having").show();
		$("#finish").hide();
	}
	$(".active").removeClass("active");
	$("#" + id).addClass("active");
}

function initpage(totalpage, currentPage) {	    	
	if (totalpage != 0){
		$.jqPaginator('#pagination', {
			totalPages : totalpage,
			visiblePages : 5,
			currentPage : currentPage,
			wrapper : '<ul class="pagination lastspan"></ul>',
			prev : '<li class="prev"><a href="javascript:void(0);">&laquo;</a></li>',
			next : '<li class="next"><a href="javascript:void(0);">&raquo;</a></li>',
			page : '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>', 
			onPageChange : function(num) {
				if (currentPage != num) {
					location.href= contextPath + "/user/mycourse/having/"+num;
				}
			}
		});
	}
}