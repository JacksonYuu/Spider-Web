function ski(name,max){
			this.name=name;
			this.max=max;
		}
		
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
		
        function achange(){
        	var id = $("#jobid").val();
    		var score = [];
    		var namemax = [];
    		$.ajax({
    			url :contextPath + "/user/getjskill",
    			type : "post",
    			data : {
    				id:id
    			},
    			success : function(s) {
    				for(var i=0;i<s.other.length;i++){
    					var na = s.other[i].name;
    					var name = na.substring(0,na.indexOf(":"));
    					var point = parseInt(na.substring(na.indexOf(":")+1)*100);
    					mski=new ski(name,100);
    					namemax.push(mski);
    					score.push(point);
    				}
    				// 指定图表的配置项和数据
    		       	option = {
    		       		backgroundColor: '#f3f3f3',
    				    title: {},
    				    tooltip: {},
    				    legend: {
    				    	left: 'right',
    				        data: ['实际技能', '目标技能']
    				    },
    				    radar: {
    				        // shape: 'circle',
    				        indicator: namemax
    				    },
    				    series: [{
    				        name: '预算 vs 开销（Budget vs spending）',
    				        type: 'radar',
    				        // areaStyle: {normal: {}},
    				        data : [
    				            {
    				                value : achangenum,
    				                name : '实际技能'
    				            },
    				             {
    				                value : score,
    				                name : '目标技能'
    				            }
    				        ]
    				    }]
    				};
    		        // 为echarts对象加载数据 
    		        myChart.setOption(option); 
    			}
    		});
        }
        