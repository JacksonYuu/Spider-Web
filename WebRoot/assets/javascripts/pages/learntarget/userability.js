	$(function() {
		$(".list-group-item").removeClass("active");
		$("#skillmap").addClass("active");
		$("#img_myskills").attr("src",contextPath+"/assets/images/xxmb2.png");
		getzygjinfo();
	});
	
	function getzygjinfo(){
		var zysy = 0;
		var llzs = 0;
		$.ajax({
			url : contextPath+"/user/getzygjinfo",
			data : {
				userid :userid
			},
			type : "post",
			success : function(s) {
				if (s.success) {
					var jsonstr = s.other;
					var list = eval('(' + jsonstr + ')'); 
					if(list==null){
						var temp ="<div>暂无数据</div>";
						$("#radar").html(temp);
					}else{
						getdxwinfo(list);
					}
					
				}else{
					
				}
			}
		});
	}
	
	function getdxwinfo(zyjslist) {
		$.ajax({
			url : contextPath+"/user/getinfo",
			data : {
				userid : userid
			},
			type : "post",
			success : function(s) {
				if (s.success) {
					var list = s.other;
					var zysy=zyjslist.zysy;//职业素养
					var llzs=zyjslist.llzs;//理论知识
					var xxnl=zyjslist.pjdf*0.5+zyjslist.lesonc*0.3+list.submitscore*0.2;//学习能力
					var sjnl=zyjslist.lesonc*0.4+list.submitscore*0.25+list.reportava*0.35;//实践能力
					var xmjy=zyjslist.xmjy;//项目经验
					mychart1(zysy,llzs,xxnl,sjnl,xmjy);
				}
			}
		});
	}
	
	function mychart1(zysy,llzs,xxnl,sjnl,xmjy) {
		var myChart1 = echarts.init(document.getElementById('radar'));
		var option1 = {
			title : {
				text : '',
				subtext : '',
				x : 'right',
				y : 'bottom'
			},
			tooltip : {
				trigger : 'item',
				backgroundColor : 'rgba(0,0,250,0.2)'
			},
			legend : {
				data : (function() {
					var list = [];
					list.push('大一');
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            					list.push('大三');
					return list;
				})()
			},
			visualMap : {
				color : [ 'red', 'yellow' ]
			},
			radar : {
				indicator : [ {
					text : '学习能力',
					max : 100
				}, {
					text : '项目经验',
					max : 100
				}, {
					text : '实践能力',
					max : 100
				}, {
					text : '理论知识',
					max : 100
				}, {
					text : '职业素养',
					max : 100
				} ]
			},
			series : (function() {
				var series = [];
				series.push({
					name : '浏览器（数据纯属虚构）',
					type : 'radar',
					symbol : 'none',
					itemStyle : {
						normal : {
							lineStyle : {
								width : 1
							}
						},
						emphasis : {
							areaStyle : {
								color : 'rgba(0,250,0,0.3)'
							}
						}
					},
					data : [ {
						value : [ xxnl, xmjy,sjnl, llzs, zysy],
						name : '大一',
					} ]
				});
				return series;
			})()
		};
		myChart1.setOption(option1);
	}