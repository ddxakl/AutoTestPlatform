var _table;
$(function(){
	document.body.style.zoom = window.screen.width / 1536; 
	var $table = $('#table-testplan');
	
	
	_table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = testplan.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val()+"/testplan/getPlanList",
	            cache : false,	//禁用缓存
	            data: param,	//传入已封装的参数
	            dataType: "json",
	            success: function(result) {
	            	
	            		//异常判断与处理
	            		if (result.errorCode) {
	            			return;
						}
	            		
	            		//封装返回数据，这里仅演示了修改属性名
	            		var returnData = {};
		            	returnData.draw = result.draw;//这里直接自行返回了draw计数器,应该由后台返回
		            	returnData.recordsTotal = result.total;
		            	returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
		            	returnData.data = result.pageData;
		            	
		            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
		            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
		            	callback(returnData);
	            	
	            },
	            error: function(XMLHttpRequest, textStatus, errorThrown) {
	               
	            }
	        });
		},
		columns: [//配置每一列的数据源
			 {
				 className: "ellipsis",
	                data: "fgmc",
	                orderable:false,
	                render: function (data, type, row, meta) {
	                    return '<input type="checkbox"  class="checkchild_sjyj"  value="' + data + '"  name="xmlxcheckBox"/>';
	                }
			 },
	            {
	            	className : "ellipsis",	//文字过长时用省略号显示，CSS实现
	            	data: "planid",
	            	orderable:false
	            },
	            {
	            	className : "ellipsis",	
	            	data: "planname",
	            	orderable:false
	            	//render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
	            	//固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
					//切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
	            },
	            {
	            	className : "ellipsis",
					data : "descinfos",
					orderable:false
				},
				 {
					className : "ellipsis",
					data: "executeresult",
					orderable : false,
					render: function (data, type, row, meta) {
	                	if (data == 0) {
	                		return "未执行";
						}else if (data == 1) {
							return "已执行";
						}else {
							return "#";
						}
	                }
				},		
				 {
					className : "ellipsis",
					data: "count",
					orderable : false
				},
				 {
					className : "ellipsis",
					data: "targetterminals",
					orderable : false
				},
				{
					className : "ellipsis",
					data: "executer",
					orderable : false
				},
				{
					className : "ellipsis",
					data: "createdate",
					orderable : false
				},
				{
	                className: "ellipsis",
	                data: "caseCao",
	                orderable: false,
	                render: function (data, type, row, meta) {
	                    return '<div class="dropdown">' + 
	                    '<a href="javascript:void(0)" id="allb' + row.planid + '\" onclick="chakan(\'' + row.planid + '\')">案例列表</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="executeplan(\'' + row.count +'\',\''+ row.targetterminals+'\',\''+row.planid+'\',\''+row.executer+'\')">执行</a>' +
						'<a href="javascript:void(0)" style="padding-left: 20px;" id="xiugaia' + row.planid + '\" onclick="xiugaiplan(\'' + row.planid + '\',\'' + row.executer + '\')">修改</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchuplan(\'' + row.planid + '\',\''+row.executer + '\')">删除</a>' + 
	                    '</div>';
	                }
	            }
	        ],
	        "columnDefs":[{
	        	"targets": [1],
	            "visible": false  
	        }],
	        "createdRow": function ( row, data, index ) {
//	        	$(row).children('td').eq(0).attr('style','text-align:center;')     
	        },
		"drawCallback": function( settings ) {
        	//渲染完毕后的回调
			//判断案例是否有数据
			common.ajax({
	            url: $("#basePath").val() + "/testplan/getPlanCaseListRtnPid",
	            type: "POST",
	            aysnc: false,
	            success: function (data) {
	            	for (var i = 0; i < data.length; i++) {
						  $("#allb"+data[i]).css("color","#5600ff"); 
					  }
	            }
	        });
			
        }
	})).api();
	
	
	/**
	 * 查询
	 */
	$("#btn_query").click(function() {
		_table.draw();
	});
	
	
	/**
	 * 添加计划
	 */
	$("#btn_testplan").click(function(){
		window.location.href= $("#basePath").val() + "/testService/addPlanTask";
//		clearDev();
//		$("#plannameMust").html("");
//		$("#belongMust").html("");
//		$("#addtestplan").modal('show');
//		$("#myModalLabel").text("新增计划任务");
	});
	
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		$("#plannameMust").html("");
		$("#belongMust").html("");
		if ($("#planname2").val() != null && $("#planname2").val() != ""){
			if ($("#descinfos").val() != null && $("#descinfos").val() != ""){
				if($("#myModalLabel").text()=="修改计划任务"){
					$("#addInfosForm").attr("action",$("#basePath").val() + "testplan/updateplan");
				}else{
					$("#addInfosForm").attr("action",$("#basePath").val() + "testplan/addplan");
				}
				$("#addInfosForm").form('submit', {
			        success:function(data){
			    		$("#addtestplan").modal('hide');
			    		_table.draw();
			    		if($("#myModalLabel").text()=="修改计划任务") {
			    			$.dialog.tips("修改成功");
			    		}else if($("#myModalLabel").text()=="新增计划任务"){
			    			$.dialog.tips("添加成功");
						}
			        },
			        error: function () {
			        	if($("#myModalLabel").text()=="修改计划任务") {
			    			$.dialog.tips("修改失败");
			    		}else if($("#myModalLabel").text()=="新增计划任务"){
			    			$.dialog.tips("添加失败");
						}
				    }
				});
			}else {
				$("#belongMust").html("任务描述是必填项！");
			}
		}else {
			$("#plannameMust").html("任务名称是必填项！");
		}
	});
	/**
	 * 提测
	 */
	
	$("#execute").click(function(){
		
		$.dialog.tips('开发中。');
		
	/*	var selectedItems = [];
        $("tbody :checkbox:checked",$table).each(function(i) {
        	var item = _table.row($(this).closest('tr')).data();
        	selectedItems.push(item);
        });
        
		var message;
		if (selectedItems&&selectedItems.length) {
//			$("#myModalLabel").text("提测任务");
//			$("#myModal_add").modal('show');
			common.ajax({
				url: $("#basePath").val() + "/testService/casesExecute",
				type : "POST",
//				data : {
//					"result" : jsonData
//				},
				success : function(data) {
//					$.dialog.tips("删除成功");
//					_table.draw(false);
				}
			});
			
		}else{
			$.dialog.tips('请先选中要提测的案例！');
		}
*/
	});
	
	
	/**
	 * 案例维护
	 */
	$("#btn_caseManage").click(function(){
		
		var selectedItems = [];
        $("tbody :checkbox:checked",$table).each(function(i) {
        	var item = _table.row($(this).closest('tr')).data();
        	selectedItems.push(item);
        });
        
		var message;
		if (selectedItems&&selectedItems.length) {
			if(selectedItems.length>1){
				$.dialog.tips('只可以选择一条');
				return ;
			}
		}else{
			$.dialog.tips('请先选中提测任务！');
			return;
		}
		
		document.body.style.zoom = window.screen.width / 1536; 
		var $table3 = $('#caseInfo');
		
		if ($table3.html() != "") {
			$table3.dataTable().fnDestroy();
		}
		var realplanid=selectedItems[0].planid;
		var planid='0';
		var type='RP';//不支持脚本案例
		var _table3 = $table3.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
			ajax : function(data, callback, settings) {
				// 封装请求参数
				var param = caseInfoManage.getQueryCondition(data);
				param.casetype=type;
				$.ajax({
		            type: "GET",
		            url: $('#basePath').val()+"/testplan/getCaseList/"+planid,
		            cache : false,	//禁用缓存
		            data: param,	//传入已封装的参数
		            dataType: "json",
		            success: function(result) {
		            	
		            	$("#chakanmyModal").modal('show');
		            	
		            		//异常判断与处理
		            		if (result.errorCode) {
		            			return;
							}
		            		
		            		//封装返回数据，这里仅演示了修改属性名
		            		var returnData = {};
			            	returnData.draw = result.draw;//这里直接自行返回了draw计数器,应该由后台返回
			            	returnData.recordsTotal = result.total;
			            	returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
			            	returnData.data = result.pageData;
			            	
			            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
			            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
			            	callback(returnData);
		            	
		            },
		            error: function(XMLHttpRequest, textStatus, errorThrown) {
		               
		            }
		        });
			},
			columns:[
				{
					className : "ellipsis",
					data : "caseid",
					orderable : false
					// render :
					// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
					// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
					// 切记设置table样式为table-layout:fixed;
					// 否则列宽不会强制为指定宽度，也不会出现省略号。
					
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "casename",
					orderable : false
					// render :
					// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
					
				},
				{
					className : "ellipsis",
					data : "casetype",
					orderable : false,
					render : function(data,type, row, meta) {
						if(data=='RP'){
							return '录制';
						}else if(data=='ST'){
							return '脚本';
						}
					}
					// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
					// 切记设置table样式为table-layout:fixed;
					// 否则列宽不会强制为指定宽度，也不会出现省略号。
					
				},
				{
					className : "ellipsis",
					data : "casedesc",
					orderable : false
				
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "userid",
					orderable : false
					
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "createdate",
					orderable : false
				
				},
				{
					className : "ellipsis",
					data : "caseCao",
					orderable : false,
					render : function(data,
							type, row, meta) {
						return '<div class="dropdown">'
								+'<a href="javascript:void(0)" onclick="addplancase(\''
								+ realplanid
								+ '\',\''
								+ row.caseid
								+ '\')">添加</a>'
								+ '</div>';
					}
				} ],
		        "columnDefs":[{
		        	"targets": [  ],
		            "visible": false  
		        }],
		        "createdRow": function ( row, data, index ) {
//		        	$(row).children('td').eq(0).attr('style','text-align:center;')     
		        },
			"drawCallback": function( settings ) {
	        	//渲染完毕后的回调
	        	//清空全选状态
	        	//默认选中第一行
//	        	$("tbody tr",$table).eq(0).click();
//	        	$("#btn_query").blur();
	        }
		})).api();

	});
	
	/**
	 * 添加执行机
	 */
	
	$("#btn_terminal").click(function(){
		
		var selectedItems = [];
        $("tbody :checkbox:checked",$table).each(function(i) {
        	var item = _table.row($(this).closest('tr')).data();
        	selectedItems.push(item);
        });
        
		var message;
		if (selectedItems&&selectedItems.length) {
			if(selectedItems.length>1){
				$.dialog.tips('只可以选择一条');
				return ;
			}
		}else{
			$.dialog.tips('请先选中提测！');
			return;
		}
		$("#planid").val(selectedItems[0].planid);
		common.ajax({
			url : $("#basePath").val() + "/executeclient/ecListstatus1",
			type : "GET",
			dataType : "json",
			success : function(redata) {
				//console.log(redata);
				if (redata.data.length == 0) {
					$.dialog.tips("请添加执行机！");
					return;
				}
				$("#execlient").empty();
				for (var i = 0; i < redata.data.length; i++) {
					var terminalid = redata.data[i].terminalid;
					var name = redata.data[i].name;
					$("#execlient").append(
							"<option value=" + terminalid + ">" + name + "</option>");
				}
			}
		});
		
		$('#termanler').modal('show');

	});
	
	$("#addexecute").click(function() {

		// 提交数据
		var basePath = $("#basePath").val();
		var formData = {};
		formData.executeclientid = $("#execlient").val();
		formData.planid =  $("#planid").val();

			common.ajax({
				url : $("#basePath").val() + "/testplan/updatePlanExecuteclient",
				type : "POST",
				data: formData,
				success : function(data) {
					$("#termanler").modal('hide');
					$.dialog.tips("添加成功！");
					$("#btn_query").click();
					
				}
			});
	});
	
	
	
});

var _table2 ;
//查看案例列表
function chakan(planid) {
	
/*	var selectedItems = [];
    $("tbody :checkbox:checked",$table).each(function(i) {
    	var item = _table.row($(this).closest('tr')).data();
    	selectedItems.push(item);
    });
*/  
	document.body.style.zoom = window.screen.width / 1536; 
	var $table2 = $('#caseInfo');

	// 加载新数据之前先清空原数据
	if ($table2.html() != "") {
		$table2.dataTable().fnDestroy();
	}

	_table2 = $table2.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION,
									{
										ajax : function(data, callback,
												settings) {// ajax配置为function,手动调用异步查询

											// 封装请求参数
											var param = caseInfoManage.getQueryCondition(data);

											$.ajax({
														type : "GET",
														url : $("#basePath")
																.val()
																+ "/testplan/getPlanCaseList/"
																+ planid,
														cache : false, // 禁用缓存
														data : param, // 传入已封装的参数
														dataType : "json",
														success : function(result) {
															$("#chakanmyModal").modal('show');
															
															// 异常判断与处理
															if (result.errorCode) {
																return;
															}

															// 封装返回数据，这里仅演示了修改属性名
															var returnData = {};
															returnData.draw = result.draw;// 这里直接自行返回了draw计数器,应该由后台返回
															returnData.recordsTotal = result.total;
															returnData.recordsFiltered = result.total;// 后台不实现过滤功能，每次查询均视作全部结果
															returnData.data = result.pageData;

															// 调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
															// 此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
															callback(returnData);
														},
														error : function(
																XMLHttpRequest,
																textStatus,
																errorThrown) {
														}
													});
										},
										columns : [
												{
													className : "ellipsis",
													data : "caseid",
													orderable : false
													// render :
													// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
													// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
													// 切记设置table样式为table-layout:fixed;
													// 否则列宽不会强制为指定宽度，也不会出现省略号。
													
												},
												{
													className : "ellipsis", // 文字过长时用省略号显示，CSS实现
													data : "casename",
													orderable : false
													// render :
													// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
													
												},
												{
													className : "ellipsis",
													data : "casetype",
													orderable : false,
													render : function(data,type, row, meta) {
														if(data=='RP'){
															return '录制';
														}else if(data=='ST'){
															return '脚本';
														}
													}
													// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
													// 切记设置table样式为table-layout:fixed;
													// 否则列宽不会强制为指定宽度，也不会出现省略号。
													
												},
												{
													className : "ellipsis",
													data : "casedesc",
													orderable : false
												
												},
												{
													className : "ellipsis", // 文字过长时用省略号显示，CSS实现
													data : "userid",
													orderable : false
													
												},
												{
													className : "ellipsis", // 文字过长时用省略号显示，CSS实现
													data : "createdate",
													orderable : false
												
												},
												{
													className : "ellipsis",
													data : "caseCao",
													orderable : false,
													render : function(data,
															type, row, meta) {
														
														if(planid=='0'){
															return '<div class="dropdown but">'
															+'<a href="javascript:void(0)" style="margin-left: 10px;color: #fff;background-color: #1081de;border-color: #fff;" onclick="addplancase(\''
															+ planid
															+ '\',\''
															+row.caseid
															+ '\')">添加</a>'
															+ '</div>';
														}else{
															
														return '<div class="dropdown">'
																+'<a href="javascript:void(0)" onclick="shanchucase(\''
																+row.id
																+'\',\''
																+ planid
																+ '\',\''
																+row.caseid
																+ '\')">删除</a>'
																+ '</div>';
														}
													}
												} ],
										"columnDefs" : [ {} ],
										"createdRow" : function(row, data,
												index) {
											// 行渲染回调,在这里可以对该行dom元素进行任何操作
										},
										"initComplete" : function(settings,json) {

										},
										"drawCallback" : function(settings) {

										}
									})).api();// 此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
}

function executeplan(count,target,planid,executer) {
	if (!common.checkuseropt(executer,curuser)) {
		return;
	}
    message = "确定要执行任务吗?";
    
    if(count==0){
    	 $.dialog.tips("请维护案例！");
    	 return;
    }
    
    if(target==''||target==null){
   	 $.dialog.tips("请维护一个执行机！");
   	 return;
   }

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/testplan/executeplan",
            type: "POST",
            data: {
                "planid":planid
            },
            success: function (data) {
                $.dialog.tips("发送批次成功，正在执行..");
                $("#btn_query").click();
            },
            error: function () {
            	$.dialog.tips("发送批次失败");
            }
        });
    });
}

function xiugaiplan(planid,executer) {
	if (!common.checkuseropt(executer,curuser)) {
		return;
	}else {
		$("#xiugaia"+planid).attr("data-toggle","modal");
		$("#xiugaia"+planid).attr("data-target","#addtestplan");
		$("#myModalLabel").text("修改计划任务");	
		
		$("#planid2").val(planid);
		$("#plannameMust").html("");
		$("#belongMust").html("");
		common.ajax({
			  url: $('#basePath').val() + "/testplan/getPlanListById",
			  type: "POST",
			  data: {
			      "planid": planid
			  },
			  success: function (data) {
				  var dataList = data["dataList"][0];
			      $("#planname2").val(dataList.planname);
			      $("#descinfos").val(dataList.descinfos);
		      }
		});
	}
}



function shanchuplan(planid,executer) {
	if (!common.checkuseropt(executer,curuser)) {
		return;
	}
    message = "确定要删除提测任务以及案例吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/testplan/delplan",
            type: "POST",
            data: {
                "planid": planid
            },
            success: function (data) {
                $.dialog.tips("删除成功");
                $("#btn_query").click();
            },
            error: function () {
            	$.dialog.tips("删除失败");
            }
        });
    });
}


function shanchucase(id,planid,caseid) {
    message = "确定要删除提测计划下案例吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/testplan/delplancase",
            type: "POST",
            data: {
                "caseid": caseid,
                "planid":planid,
                "id":id
            },
            success: function (data) {
                $.dialog.tips("删除成功");
//                _table.draw();
                chakan(planid);
            },
            error: function () {
            	$.dialog.tips("删除失败");
            }
        });
    });
}
function addplancase(planid,caseid) {
//    message = "确定要添加提测计划案例吗?";
//    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/testplan/addplancase",
            type: "POST",
            data: {
            	"planid":planid,
                "caseid": caseid
            },
            success: function (data) {
                $.dialog.tips("添加成功");
                _table.draw();
            },
            error: function () {
            	$.dialog.tips("添加失败");
            }
        });
//    });
}

/**
 * 清空增加模态框值
 */
function clearDev() {
	$("#planname2").val("");
	$("#descinfos").val("");
}



var testplan = {
		currentItem : null,
		fuzzySearch : true,
		getQueryCondition : function(data) {
			var param = {};
			/*param.casebelong = $("#casebelong").val();
			param.casename = $("#casename").val();
			param.abversion = $("#abversion").val();*/
			param.planname = $("#planname").val();
			//组装分页参数
			param.startIndex = data.start;
			param.pageSize = data.length;
			param.draw = data.draw;
			param.page = data.start / 10 + 1;
			return param;
		}
	};

var caseInfoManage = {
		currentItem : null,
		fuzzySearch : true,//开启模糊查询
		getQueryCondition : function(data) {
			var param = {};
			//组装分页参数
			param.startIndex = data.start;
			param.pageSize = data.length;
			param.draw = data.draw;
			param.page = data.start / 10 + 1;
			return param;
		}
	};