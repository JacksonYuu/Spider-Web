		var u = navigator.userAgent, app = navigator.appVersion;
		var a = u.indexOf('Trident');
		if (u.indexOf('Trident') > -1) {
			if (!+'\v1' && !'1'[0]) {
				alert("系统检测到您浏览器版本过低，可能会影响到您浏览页面建议使用极速模式浏览")
			}
			if (navigator.appVersion.match(/8./i) == "8.") {
				alert("系统检测到您浏览器版本过低可能会影响到您浏览页面效果，建议使用'极速模式'浏览")
			}
		}
		
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			nextButton : '.swiper-button-next',
			prevButton : '.swiper-button-prev',
			slidesPerView : 1,
			paginationClickable : true,
			spaceBetween : 30,
			autoplay : 3500,
			loop : true
		});

		$(function() {
			$("#hindex").addClass("acitve");
		});
		
		function onResizeRbox(){
			if(window.innerHeight < 800){
				$(".rBox").css({bottom:'60px'});
			}else{
				$(".rBox").css({bottom:'260px'});
			}
		}
		onResizeRbox();
		window.onresize=function(){
			onResizeRbox();
		}
