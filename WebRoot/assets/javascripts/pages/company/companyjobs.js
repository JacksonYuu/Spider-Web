		$(function() {
			$('#li1').hover(function() {
				$(".li1").css('display', 'block');
			}, function() {
				$(".li1").css('display', 'none');
			});});
			
		var div=document.getElementsByName("echarts");


		$(function(){
		
		for(var i=0; i<div.length;i++){
			console.log(i);
			console.log(div.length);
			var myChart;
			myChart=  echarts.init(div[i]);
			
		 	var hasskills = skill[i];
		 	
			var option = {
					radar : [ {
						indicator : [],
						center : [ '50%', '50%' ],
						startAngle : 90,
						splitNumber : 4,
						shape : 'circle'
					}

					],
					series : [ {
						name : '雷达图',
						type : 'radar',
						itemStyle : {
							emphasis : {
								// color: 各异,
								lineStyle : {
									width : 3
								}
							}
						},
						data : [ {
							value : [],
							name : '图一',
							symbol : 'rect',
							symbolSize : 5,
							lineStyle : {
								normal : {
									type : 'dashed'
								}
							}
						} ]
					},

					]
				};
			
			var skills = hasskills.split(",");
		
			for (var j=0;j<skills.length;j++){
			
				var label={
					text:''
				}
			
				label.text = skills[j];
				option.radar[0].indicator[j]=label;
			
				option.series[0].data[0].value.push(1);
		}
			
			
			myChart.setOption(option);
		
		}
		});
		
		
		function apply(orgid,jmid){
			$.ajax({			
				url:contextPath+"/course/apply",
			    type:"post",
			    data:{orgid,jmid},
			    success:function(s){
			    	if(s.success){
			    		alert("申请成功!");
			    		location.reload();
			    	}else {

			    		location.href=contextPath+"/course/tologin";
			    	}
			    	
			    							    	
			    }
			})
		}