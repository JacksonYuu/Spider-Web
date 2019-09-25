
		var pattern = /^[^('"\\?,，)]+$/;
		$(function() {
			$("#share").hide();
			$("#course").addClass('active');
			$("#datepicker3").datetimepicker({
				minView: "month", //选择日期后，不会再跳转去选择时分秒 
				language : "zh-CN",
				autoclose : true,//选中之后自动隐藏日期选择框
				format : "yyyy-mm-dd"
			}).on('hide', function(ev){
			    if ($("#datepicker3").val() == ""){
					$("#datepicker3Msg").show();
			    }else{
			    	$("#datepicker3Msg").hide();
			    }
			})	;

			$("#datepicker4").datetimepicker({
				minView: "month", //选择日期后，不会再跳转去选择时分秒 
				language : "zh-CN",
				autoclose : true,//选中之后自动隐藏日期选择框
				format : "yyyy-mm-dd"
			}).on('hide', function(ev){
			    if ($("#datepicker4").val() != ""&&$("#datepicker3").val() != ""&& new Date($("#datepicker3").val()).getTime()>new Date($("#datepicker4").val()).getTime()){
			    	$("#datepicker4Msg").show();
			    }else{
			    	$("#datepicker4Msg").hide();
			    }
			})	;
			
			
			$("#datepicker5").datetimepicker({
				minView: "month", //选择日期后，不会再跳转去选择时分秒 
				language : "zh-CN",
				autoclose : true,//选中之后自动隐藏日期选择框
				format : "yyyy-mm-dd"
			}).on('hide', function(ev){
			    if ($("#datepicker5").val() == ""){
					$("#datepicker5Msg").show();
			    }else{
			    	$("#datepicker5Msg").hide();
			    }
			})	;

			$("#datepicker6").datetimepicker({
				minView: "month", //选择日期后，不会再跳转去选择时分秒 
				language : "zh-CN",
				autoclose : true,//选中之后自动隐藏日期选择框
				format : "yyyy-mm-dd"
			}).on('hide', function(ev){
			    if ($("#datepicker6").val() != ""&&$("#datepicker3").val() != ""&& new Date($("#datepicker3").val()).getTime()>new Date($("#datepicker4").val()).getTime()){
			    	$("#datepicker6Msg").show();
			    }else{
			    	$("#datepicker6Msg").hide();
			    }
			})	;
			
			$("#datepicker8").datetimepicker({
				minView: "month", //选择日期后，不会再跳转去选择时分秒 
				language : "zh-CN",
				autoclose : true,//选中之后自动隐藏日期选择框
				format : "yyyy-mm-dd"
			}).on('hide', function(ev){
			    if ($("#datepicker8").val() == ""){
					$("#datepicker8Msg").show();
			    }else{
			    	$("#datepicker8Msg").hide();
			    }
			})	;

			$("#datepicker9").datetimepicker({
				minView: "month", //选择日期后，不会再跳转去选择时分秒 
				language : "zh-CN",
				autoclose : true,//选中之后自动隐藏日期选择框
				format : "yyyy-mm-dd"
			}).on('hide', function(ev){
			    if ($("#datepicker9").val() != ""&&$("#datepicker8").val() != ""&& new Date($("#datepicker8").val()).getTime()>new Date($("#datepicker9").val()).getTime()){
					$("#datepicker9Msg").show();
			    }else{
			    	$("#datepicker9Msg").hide();
			    }
			})	;
			
			$("#yearly").datetimepicker({
				 	format: "yyyy",
			        language:"zh-CN",
					startView:"decade",
			        minView:"decade",
			        autoclose: true,
			}).on('hide', function(ev){
			    if ($("#yearly").val() == ""){
					$("#yearlyMsg").show();
			    }else{
			    	$("#yearlyMsg").hide();
			    	initTeachingTaskBaseCourse();
			    }
			});	
				
		});
		
		 function appendPlan(){
			 var temp = '';
			 temp += '<input type="hidden" id="planid"><input class="form-control" id="chooseplan" type="text"'
               + ' onclick="showMenu(); return false;" readonly="true" placeholder="请选择培养方案">'
			 	+'<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 500;">'
			  +'<div id="tree2" class="ztree" style="margin-top:0; width:301px;border: 1px solid #617775;background: #f0f6e4;position:absolute; height:102px; overflow:auto"></div>'   
			 +'</div><span id="tree2Msg" class="help-block" style="display:none">请选择培养方案</span>'; 
			 $("#appendplan").html(temp);	
		 }
		
		function createCourse(){
			appendPlan();
			initPlan();
			$("input[type=radio][name=nature][value=1]").prop("checked",true);
			$("#addorder").modal('show');
			showpart(0);
			$("input[type=radio][name=ispublic][value=0]").prop("checked",true);
		}
		
		function cCourse(bcid){
			//appendPlan();
			//initPlan();
			$("input[type=radio][name=nature][value=1]").prop("checked",true);
			$("#addorder").modal('show');
			$("#bcourse").val(bcid);
			//showpart(1);
			$("input[type=radio][name=ispublic][value=0]").prop("checked",true);
		}
		
		function ordername2MsgHide(){
			var ordername2 = $.trim($("#ordername2").val());
			if(!pattern.test(ordername2)){
				$("#ordername2Msg").show();
			}else{
				$("#ordername2Msg").hide();
			}
		}
		
		function showpart(value){
			if(value==1){
				$("#part0").show();
				$("#part1").hide();
				getCourse4Teaching();
			}else{
				$("#part1").show();
				$("#part0").hide();
				initbasecourse();
			}
		}

		


		function hidemessage() {
			$("#addorder").modal('hide');
			$("input[type=radio][name=nature][value=1]").prop("checked",true);
			$("#ordername2").val("");
			$("#teachingtaskcourse").val("");
			 $("#datepicker3").val("");
			 $("#datepicker4").val("");
			 $("#datepicker8").val("");
			 $("#datepicker9").val("");
			 //$("#tree2Msg").hide();
			 $("#yearly").val("");
			 $("#yearlyMsg").hide();
			 $("#teachingtaskbasecourseMsg").hide();
			 $("#teachingtaskcourseMsg").hide();
			 $("#ordername2Msg").hide();
			 $("#bcourseinputMsg").hide();
			 $("#bcourseinputMsg1").hide();
			 $("#teachingtaskbasecourse").html("");
			 $("#datepicker4Msg").hide();
			 $("#datepicker9Msg").hide();
			 showminus();	 
		}
		
		function initbasecourse(){
			 $.ajax({
					url : contextPath+"/teacher/initbasecourse",
					type : "post",
					data : {},
					success : function(s) {
						if(s.success){
							var other = s.other;
							var temp = "";
							for(var i=0;i<other.length;i++){
								temp += "<option value="+other[i].id+">"+other[i].name+"</option>";
							}
							$("#bcourse").html(temp);
							getCourse();// 加载可复制班次
						}else{
							temp += "<option value='-1'></option>";
							$("#bcourse").html(temp);
							$("#bcourse").attr("disabled",true); 
				
						}			 
					}
				});
		}
		
		//教学任务
		function getCourse4Teaching(){
			var nature = 0;

			var basecoursename =  $.trim($("#teachingtaskbasecourse  option:selected").text());
			var data = {bcname:basecoursename,nature:nature};
			$.ajax({
				url : contextPath+"/teacher/getByBaseCourseNew4Teaching",
				type : "post",
				data : data,
				success : function(s) {
					if (s.success) {
						var os=s.other;
						var temp="<option value='-1'>--</option>"
						for(var i in os){
							temp+="<option value='"+os[i].id+"'>"+os[i].name+"</option>";
						}
						$("#orderSel").html(temp);
	 				}else{
	 					$("#orderSel").html("<option value='-1'>--</option>");
	 				}
				}
			});
		}

		
		function getCourse(){
			//var nature = $("input[name='nature']:checked").val();
			$("#orderSel").html("<option value='-1'>--</option>");
			var nature = 0;
			var bcid=$("#bcourse").val();
			
			var text=$("#bcourse").find("option:selected").text(); 
			$("#bcourseinput").val(text);
			if(!bcid>0){
				return;
			}
			var data = {bcid:bcid,nature:nature};
			$.ajax({
				url : contextPath+"/teacher/getByBaseCourseNew",
				type : "post",
				data : data,
				success : function(s) {
					if (s.success) {
						var os=s.other;
						var temp="<option value='-1'>--</option>"
						for(var i in os){
							temp+="<option value='"+os[i].id+"'>"+os[i].name+"</option>";
						}
						$("#orderSel").html(temp);
	 				}
				}
			});
		}

		function teachingtaskcourseMsgHide(){
			var coursename = $.trim($("#teachingtaskcourse").val());
			if (!pattern.test(coursename) ) {
				$("#teachingtaskcourseMsg").show();
			}else{
				$("#teachingtaskcourseMsg").hide();
			}
		}

		function saveorder() {
			var copyid = $("#orderSel").val();
			if($("input[name=nature][value=1]").prop("checked") == true){
				if($("#planid").val()==""||$("#planid").val()==null){
					$("#tree2Msg").show();
					return;
				}
				var yearly = $("#yearly").val();
				if (yearly == ""){
					$("#yearlyMsg").show();
					return;
				}
				
				var basecoursename =  $.trim($("#teachingtaskbasecourse  option:selected").text());
				var plandetailid =  $("#teachingtaskbasecourse").val();
				var coursename = $.trim($("#teachingtaskcourse").val());
				if(basecoursename==""){
					$("#teachingtaskbasecourseMsg").show();
					return;
				}
				
				if (!pattern.test(coursename) ) {
					$("#teachingtaskcourseMsg").show();
					return;
				}
				var starttime = $("#datepicker8").val();
				if(starttime == ""){
					$("#datepicker8Msg").show();
					return;
				}			
				var endtime = $("#datepicker9").val();
				
				if(endtime != "" && new Date(starttime).getTime()>new Date(endtime).getTime()){   
	                return ;     
	            } 
				
				
				$.ajax({
					url : contextPath+"/teacher/getplandetail",
					type : "post",
					data : {id:plandetailid},
					success : function(s) {
						if(s.success){
							var json = eval("("+s.other+")");
							if(json.length==0){
								alert("获取不到培养方案相关信息");
								return false;
							}else{			
								var allhours = json.alllessons;
								var practicehours = json.practices;
								var xf = json.credit;							
								var data = {
										basecoursename : basecoursename,
										plandetailid:plandetailid,
										coursename:coursename,
										starttime : starttime,
										endtime : endtime,
										teaid : teaid,
										orgid : orgid,
										yearly:yearly,
										allhours:allhours,
										practicehours:practicehours,
										xf:xf,
										copyid:copyid
									};
									$.ajax({
										url : contextPath+"/teacher/saveteachingtask",
										type : "post",
										data : data,
										success : function(s) {
											if (s.success){
												location.href = contextPath+"/mycourse/"+ s.other +"/info";
											}else{
												alert(s.msg);
											}
										}
									});
							}		
						
						}			
					}
				});	
				
				
			}else{
				var copyid = -1;
				var len = studentidAll.length;
				if (len > 1) {
					studentidAll = studentidAll.substr(0, len - 1);
				} else {
					alert("请至少选择一名学生！")
					return;
				}
				studentidAll += "]";
				studentidAll = eval(studentidAll);
				
				var isaddbc=$("#isaddbc").val();
				var bcname=$.trim($("#bcourseinput").val());
				var bcid = $("#bcourse").val();
				if(isaddbc==1&&(!pattern.test(bcname))){
					$("#bcourseinputMsg1").show()
					return;	
				}
				
				if(isaddbc!=1&&bcid==-1){
					$("#bcourseinputMsg").show()
					return;	
				}
				var ordername = $.trim($("#ordername2").val());
				if (!pattern.test(ordername) ) {
					$("#ordername2Msg").show();
					return;
				}
				var starttime = $("#datepicker3").val();
				if(starttime == ""){
					$("#datepicker3Msg").show();
					return;
				}
				
				var endtime = $("#datepicker4").val();
				
				if(endtime != "" && new Date(starttime).getTime()>new Date(endtime).getTime()){   
	                return ;     
	            } 
				
				var ispublic = $("input[name='ispublic']:checked").val();
				var isshare=$('input[name="isshare"]:checked').val();
				var data = {
					bcid : bcid,
					ordername : ordername,
					teaid : teaid,
					etime : endtime,
					stime : starttime,
					isaddbc:isaddbc,
					bcname:bcname,
					orgid : orgid,
					ispublic : ispublic,
					copyid:copyid,
					isshare:isshare,
					studentid:studentidAll
				};
				$.ajax({
					url : contextPath+"/teacher/savecowithstudent",
					type : "post",
					data : data,
					success : function(s) {
						if (s.success){
							location.href = contextPath+"/teacher/myselfcourse/1";
						}else{
							alert(s.msg);
						}
					}
				});
			}			
		}
		// 显示创建BaseCourse按钮
		function showplus(){
			$("#share").show();
			$("#minus").show();
			$("#plus").hide();
			$("#bcourseinput").show();
			$("#bcourse").hide();
			$("#bcourseinput").focus();
			$("#isaddbc").val(1);
			$("#bcourseinput").val("");
			$("#bcourseinputMsg1").hide();
			$("#bcourseinputMsg").hide();
			
			$("#hideOrderSel").hide();
			$("#orderSel").val("-1");
		}
		// 隐藏
		function showminus(){
			$("#share").hide();
			$("#plus").show();
			$("#minus").hide();
			$("#bcourseinput").hide();
			$("#bcourse").show();
			$("#bcourseinput").blur();
			$("#isaddbc").val(-1);
			$("#bcourseinputMsg1").hide();
			$("#bcourseinputMsg").hide();
			$("#hideOrderSel").show();
		}
		
		
		function initPlan() {
			$.ajax({
						url : contextPath+"/teacher/getplantree",
						data:{orgid:orgid},
						type : "post",
						success : function(s) {
							if (s.success) {
								$("#tree2").html(s.other);
								$("#tree2")
										.dynatree(
												{
													checkbox : false,//单选框
													selectMode : 1,//单选
													onSelect : function(select,
															node) {
														var selKeys, selNodes;
														selNodes = node.tree
																.getSelectedNodes();
														selKeys = $
																.map(
																		selNodes,
																		function(
																				node) {
																			return "["
																					+ node.data.key
																					+ "]";
																		});
														return $(
																"#echoSelection2")
																.text(
																		selKeys
																				.join(", "));
													},
													onClick : function(node,
															event) {
														if (node.getEventTargetType(event) === "title") {
															if(node.data.isFolder){
																node.toggleSelect();
																$("#tree2Msg").show();
																return false;
															}else{
																saveplan(node);
															}
														}
													},
													onKeydown : function(node,
															event) {
														if (event.which === 32) {
															node.toggleSelect();
															$("#tree2Msg").show();
															return false;
														}
													},
													idPrefix : "dynatree-Cb2-"
												});
							}
						}
					});
		}
		
		function showMenu() {
			$("#menuContent").css({left:"15px", top:"30px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "chooseplan" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}else{
				$("#tree2Msg").hide();
			}
		}
		
		function saveplan(node) {
			var key = node.data.key;
			var name = node.data.title;
			if (key > 0) {
				hideMenu();
				$("#chooseplan").val(name);
				$("#planid").val(key);
				initTeachingTaskBaseCourse();
				
			} 
		}
		
		function initTeachingTaskBaseCourse(){
			var planid = $("#planid").val();
			var yearly =	$("#yearly").val(); 
			
			var data = {planid:planid,yearly:yearly};
			$.ajax({
				url : contextPath+"/teacher/plandetailnames",
				type : "post",
				data : data,
				success : function(s) {
					if(s.success){
						var temp = "";
						var json = eval("("+s.other+")");
						if(json == null){
							
						}else{
							for(var i = 0,len = json.length ;i<len;i++){
								temp += "<option value= '"+json[i].id+"' >"+json[i].coursename+"</option>";
							}
						}
						
						$("#teachingtaskbasecourse").html(temp);
						getCourse4Teaching();
						$("#teachingtaskbasecourseMsg").hide();
					}				
				}
			});
		}
