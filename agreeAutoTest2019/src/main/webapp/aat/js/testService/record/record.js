$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	var $table = $('#record-py');
	var executeCaseList =  $("#executeCaseList").val();
	
	$("#caseItem").validate();
	
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = record.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "playback/record",
	            cache : false,	//禁用缓存
	            data: param,	//传入已封装的参数
	            dataType: "json",
	            success: function(result) {

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
		columns : [// 配置每一列的数据源
				CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
				{
					 className : "ellipsis",
					// //文字过长时用省略号显示，CSS实现
					data : "caseidentifier",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "casename",
					orderable : false,
				// render :
				// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
				// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
				// 切记设置table样式为table-layout:fixed;
				// 否则列宽不会强制为指定宽度，也不会出现省略号。
					render : function(data,
							type, row, meta) {
						var desc =  row.casedesc;
						if(desc=='null'||desc==null){
							desc='无';
						}
						if (data != null) {
							var html = '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;" title=案例描述：'
									+ desc
									+ '>'
									+ data
									+ '</div>';
							return html;
						} else {
							return "";
						}
					}
				},
				{
					className : "ellipsis",
					data : "casebelong",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "abversion",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "casetype",
					orderable : false
				
				},
				{
					className : "ellipsis",
					data : "tradecodes",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "casedesc",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "userid",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "createdate",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "caseCao",
					width: "250px",
					orderable : false,
					render : function(data,
							type, row, meta) {
						return '<div class="dropdown">'
								+ '<a href="javascript:void(0)" onclick="chakan(\''
								+ row.caseid
								+ '\',\''
								+ row.casename
								+ '\')">查看</a>'
								+ '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="huifang(\''
								+ row.caseid
								+ '\',\''
								+ row.userid
								+ '\',\''
								+ row.batchcase
								+ '\')">回放</a>'
//																+ '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shipin(\''
//																+ row.caseid
//																+ '\')">视频</a>'
								+ '<a href="javascript:void(0)" style="padding-left: 20px;" id="sjwg' + row.caseid + '\" onclick="shujuwaigua(\''
								+ row.caseid
								+ '\')">数据外挂</a>'
//								+ '<a href="javascript:void(0)" style="padding-left: 20px;" data-toggle="modal" data-target="#xiugai_modal" onclick="xiugai(\''
								+ '<a href="javascript:void(0)" style="padding-left: 20px;" id="xiugaia' + row.caseid + '\" onclick="xiugai(\''
								+ row.caseid
								+ '\',\''
								+ row.userid
								+ '\')">修改</a>'
								+ '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\''
								+ row.caseid
								+ '\',\''
								+ row.userid
								+ '\')">删除</a>'
								+ '</div>';
					}
				} ],
		"columnDefs" : [ {
			"targets" : [ 4,5,7 ],
			"visible" : false
		} ],
		"createdRow" : function(row, data,
				index) {
			// $(row).children('td').eq(0).attr('style','text-align:center;')
		},
		"drawCallback" : function(settings) {
			// 渲染完毕后的回调
			/**
			 * 判断是否已经上传了外挂文件
			 */
			common.ajax({
				  url: $('#basePath').val() + "playback/selectCaseidsByBatchcase",
				  type: "POST",
				  aysnc: false,
				  success: function (data) {
					  for (var i = 0; i < data.length; i++) {
						  $("#sjwg"+data[i]).css("color","#5600ff"); 
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

	$("#executecase").click(function() {
		// 提交数据
		var basePath = $("#basePath").val();
		//var caseId = caseid;
		var formData = {};
		formData.executeclientid = $("#execlient").val();
		formData.caseId = caseid1;
		formData.username = username1;
		formData.type = "RP";
		formData.batchcase = batchcase1;

		message = "确认执行案例吗？";

		$.dialog.confirm(message, function() {
			common.ajax({
				url : $("#basePath").val() + "/testplan/pbexecute",
				type : "POST",
				data: formData,
				success : function(data) {
					$("#play-back").modal('hide');
					$.dialog.tips("发送成功！");
				}
			});
		});
	});
	
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		$("#belongMust").html("");
		$("#caseIdentifierMust").html("");
		$("#caseNameMust").html("");
		if ($("#caseName").val() != null && $("#caseName").val() != ""){
			if ($("#caseIdentifier").val() != null && $("#caseIdentifier").val() != ""){
				if ($("#belong").val() != null && $("#belong").val() != ""){
					$("#mainform").form('submit', {
				        success:function(data){
				    		$("#xiugai_modal").modal('hide');
			    			$.dialog.tips("修改成功");
			    			_table.draw();
				        },
				        error: function () {
			    			$.dialog.tips("修改失败");
					    }
					});
				}else {
					$("#belongMust").html("所属项目是必填项！");
				}
			}else {
				$("#caseIdentifierMust").html("案例编号是必填项！");
		    }	
		}else {
			$("#caseNameMust").html("案例名称是必填项！");
		}
	});
	
	/**
	 * 数据外挂上传模态框-开始上传按钮
	 */
	//验证文件是否上传成功
	$("#uploadfiles").ajaxForm(function(data){
		if(data){
			$.dialog.tips('上传成功');
		}else {
			$.dialog.tips('上传失败（只允许上传xlsx格式的文件）');
		}
		$("#uploadfiles").modal('hide');
		_table.draw();
	});     
	
	/**
	 * (批量)导出所选数据
	 */
	$("#btn_export").click(function(){
		var arrItemId = [];
        $("tbody :checkbox:checked",$table).each(function(i) {
        	var item = _table.row($(this).closest('tr')).data();
        	arrItemId.push(item);
        });
		exportItem(arrItemId);
	});
	
	function exportItem(selectedItems){
		if (selectedItems&&selectedItems.length) {
			var jsonData = JSON.stringify(selectedItems);// 转成JSON格式
			$("#exportHidden").val(jsonData);
			//var result = $.parseJSON(jsonData);// 转成JSON对象
			$("#mainForm").attr("action",$("#basePath").val() + "playback/exportData");
			$("#mainForm").submit();
		}else{
			$.dialog.tips('请选中要操作的行');
		}
	}
	
	/** checkbox勾选框的勾选 */
 	$("tbody").on("click",".iCheck",function(event){
 		if($(this).is(':checked')){
 			//$(this).parent().parent().addClass("active");
 			$(this).attr("checked", true);
 		}else{
 			//$(this).parent().parent().removeClass("active");
 			$(this).removeAttr("checked");
 			//$(this).attr("checked",false);
 		}		
 		event.stopPropagation();
 	});
	
	
	//行点击事件
//	$("tbody",$table).on("click","tr",function(event) {
//		//这里应该加个判断，如果勾选框勾选大于1，则不执行removeClass,防止勾选多项要去一项时，直接全部去掉
//		var a=$("input[type='checkbox']:checked").length;		
//		if(a<=1){				
//			//$(this).addClass("active");//.siblings().removeClass("active");
//		}
//		
//		//点击某行，勾选该行的复选框 
//		 var input = $(this).find("input");
//         if ($(input).prop("checked")) {
//          
//        	 $(input).prop("checked",false);
//            // $(this).removeClass("active");
//         }else{
//             $("tbody tr").find("input").each(function () {           	 
//                 //$(this).removeAttr("checked");             
//             });
//        
//             $(input).prop("checked",true);
//           //  $(this).addClass("active");
//         }           
//         //多选框 防止事件冒泡
//         	 event.stopImmediatePropagation();
// 		 //获取该行对应的数据
//		 var item = _table.row($(this).closest('tr')).data();
//		 record.currentItem = item;
//    });
	
	
	
	/**click 全选反选行加属性*/
 	$("input[type='checkbox']").click(function(e){
 		if($(this).is(':checked')){
 			//$("tr",$table).addClass("active");
 		}else{
 			//$("tr",$table).removeClass("active");
 		}		
         e.stopPropagation();
         //e.stopImmediatePropagation();
     });
 	
	
	$table.on("change",":checkbox",function(e) {
		
		if ($(this).is("[name='cb-check-all']")) {
			//全选
			$(":checkbox",$table).prop("checked",$(this).prop("checked"));
		}else{
			//一般复选
			var checkbox = $("tbody :checkbox",$table);
			$(":checkbox[name='cb-check-all']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
		}
		e.stopPropagation();
    }).on("click",".td-checkbox",function(event) {
    	//点击单元格即点击复选框
    	!$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    	event.stopPropagation();
    });		
	

});

function chakan(caseid, casename) {
	
	var $table2 = $('#table-caseInfo');

	// 加载新数据之前先清空原数据
	if ($table2.html() != "") {
		$table2.dataTable().fnDestroy();
	}
	
	document.getElementById("menu-right").style.visibility = "hidden";

	var _table2 = $table2
			.dataTable(
					$
.extend(
		true,
		{},
		CONSTANT.DATA_TABLES.DEFAULT_OPTION,
		{
			ajax : function(data, callback,
					settings) {// ajax配置为function,手动调用异步查询

			// 封装请求参数
			var param = caseInfoManage
					.getQueryCondition(data);

			$
					.ajax({
						type : "GET",
						url : $("#basePath")
								.val()
								+ "playback/selectcaseinfo/"
								+ caseid,
						cache : false, // 禁用缓存
						data : param, // 传入已封装的参数
						dataType : "json",
						success : function(result) {
							$("#jnamea3").val(caseid);
							$("#myModal_sub3").modal('show');
							if (casename == "null"|| casename == "undefined"|| casename == "") {
								$("#myModalLabel3").text("案例信息");
							} else {
								$("#myModalLabel3").text(casename);
							}
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
					data : "itemindex",
					orderable : false,
					render : function(data,
							type, row, meta) {
						return data;
//						if (data != null) {
//							var html = '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;" title='
//									+ data
//									+ '>'
//									+ data
//									+ '</div>';
//							return html;
//						} else {
//							return "";
//						}
					}
				},
				{
					className : "ellipsis",
					data : "itemcode",
					orderable : false,
					// render :
					// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
					// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
					// 切记设置table样式为table-layout:fixed;
					// 否则列宽不会强制为指定宽度，也不会出现省略号。
					render : function(data,
							type, row, meta) {
						if (data != null) {
							var html = '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;" title=交易码：'
									+ row.tradecode
									+ '&nbsp;&nbsp;组件类型：' + row.itemtype
									+ '&nbsp;&nbsp;事件名：' + row.itemevent
									+ '>'
									+ data
									+ '</div>';
							return html;
						} else {
							return "";
						}
					}
				},
				{
					className : "ellipsis",
					data : "itemalias",
					orderable : false,
					width: "130px",
					render : function(data,
							type, row, meta) {
//						return data;
						if (data != null){
							var html='<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:130px;cursor:pointer;" title='+data+'>'+data+'</div>';	                     		                    	
			            	return html;
						 }else {
							 return "";
						 }
					}
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "itemvalue",
					orderable : false,
					width: "130px",
					// render :
					// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
					render : function(data,
							type, row, meta) {
//						return data;
						if (data != null){
							var html='<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:130px;cursor:pointer;" title='+data+'>'+data+'</div>';	                     		                    	
			            	return html;
						 }else {
							 return "";
						 }
					}
				},
				{
					className : "ellipsis",
					data : "isscreen",
					orderable : false,
					render : function(data, type, row, meta) {
						if (data=="Y") {
							return '<div><input id="box_'+row.itemid+'" type="checkbox" checked="checked" style="width:18px;height:18px;"/></div>';
						}else {
							return '<div><input id="box_'+row.itemid+'" type="checkbox" style="width:18px;height:18px;"/></div>';
						}
					}
				},
				{
					className : "ellipsis",
					data : "relationvalue",
					orderable : false,
					width: "160px",
					render : function(data,
							type, row, meta) {
//						return data;
						if (data != null){
							var html='<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:160px;cursor:pointer;" title='+data+'>'+data+'</div>';	                     		                    	
			            	return html;
						 }else {
							 return "";
						 }
					}
				},
				{
					className : "ellipsis",
					data : "caseCao",
					orderable : false,
					render : function(data,
							type, row, meta) {
						return '<div class="dropdown">'
								+'<a href="javascript:void(0)" id="yqjg' + row.itemid + '\" onclick="addexpectresult(\''
								 + row.itemid
								 +'\')">预期结果</a>'
								 + '<a href="javascript:void(0)" style="padding-left: 20px;" id="xg' + row.itemid + '\" onclick="chakan_xiugai(\''
									+ row.itemid
									+ '\',\''
									+ caseid
									+ '\')">修改</a>'
								 +'<a href="javascript:void(0)" style="padding-left: 20px;" onclick="chakan_shanchu(\''
								+ row.itemid
								+ '\',\''
								+ caseid
								+ '\')">删除</a>'
								+ '</div>';
					}
				} ],
		"columnDefs" : [ {} ],
		"createdRow" : function(row, data,
				index) {
			// 行渲染回调,在这里可以对该行dom元素进行任何操作
		},
		"initComplete" : function(settings,
				json) {

		},
		"drawCallback" : function(settings) {
			//渲染完毕后的回调
			//判断案例是否有数据
			common.ajax({
	            url: $("#basePath").val() + "/expect/getExpResListRtnEid",
	            type: "POST",
	            aysnc: false,
	            success: function (data) {
	            	for (var i = 0; i < data.length; i++) {
						  $("#yqjg"+data[i]).css("color","#5600ff"); 
					  }
	            }
	        });
			$('#table-caseInfo>tbody').attr("id","caseInfoTbody");
		}
	})).api();// 此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

	/**
	 * doubleclick 编辑
	 */
	$('#table-caseInfo').on("dblclick", "td", function() {
		var $td = $(this); // 获取被双击的td单元格
		var cellindex = $td.parent().context._DT_CellIndex.column; // 列号
		if (cellindex == 2 || cellindex == 3 || cellindex == 5) {
			var item = _table2.row($td.closest('tr')).data(); // 获取双击的该行内容
			var itemid = item.itemid; // 获取该行的主键itemid
			var _t = $td.text(); // 被双击的td单元格里的文本内容
			$td.html(""); // 清空被双击的td单元格里的内容
//			var $input = $("<input type='text' placeholder='单击提示/取消' id='edit_input' data-toggle='popover' data-placement='bottom' data-trigger='click'>"); // 设置一个待添加的input表单
			var $input = $("<input type='text' id='edit_input' style='width:130px;height:20px;'>"); // 设置一个待添加的input表单
			$input.html(_t);// 保留td单元格原有内容
			if (cellindex == 5) {
				$input.attr("placeholder","单击提示/取消");
				$input.attr("data-toggle","popover");
				$input.attr("data-placement","right");
				$input.attr("data-trigger","click");
				$input.mouseover(function(){
					popover();
				});
			}
			// 下面这句是把input表单添加到被双击的单元格里，并让其获取焦点，并设置在失去焦点时的动作
			$input.appendTo($td).val(_t).focus().blur(function() {
				if (_t != $input.val()) {
					if (cellindex == 2) {
						var existFlag;
						common.ajax({
							url : $("#basePath").val() + "playback/getItemAliasByCaseid",
							type : "POST",
							async: false,
							data : {
								"caseid" : caseid,
								"input" : $input.val()
							},
							success : function(data) {
								if (data=="alias") {
									existFlag="alias";
								}else if (data=="index") {
									existFlag="index";
								}else if (data=="code") {
									existFlag="code";
								}
							}
						});
						if (existFlag=="alias") {
							$.dialog.tips("修改失败，别名已存在");
							chakan(caseid);
							return;
						}else if (existFlag=="index") {
							$.dialog.tips("修改失败，和编号冲突");
							chakan(caseid);
							return;
						}else if (existFlag=="code") {
							$.dialog.tips("修改失败，和组件代码冲突");
							chakan(caseid);
							return;
						}
					}
					// 失去焦点，保存值。与服务器ajax交互
//					$.dialog.confirm("是否确认保存修改?", function() {
						common.ajax({
							url : $("#basePath").val() + "playback/updateItem",
							type : "POST",
							data : {
								"itemid" : itemid,
								"column" : cellindex,
								"input" : $input.val()
							},
							success : function(data) {
								$.dialog.tips("修改成功");
								chakan(caseid);
							}
						});
//					});
				}
				chakan(caseid);
			});
			$('#table-caseInfo').off("dblclick");
		}
	});
	//加载弹出框
	function popover() {
		$("[data-toggle='popover']").popover({  
	        html : true,    
	        title: "表达式",    
	        delay: {show:0, hide:0},  
	        content: function() {  
	        	return content();    
	        }   
		});
	}
	function content() {
		return "取值 \n 1.GET{编号} \n 2.GET{组件代码} \n 3.GET{组件别名}  \n赋值 \n 1.SET{编号} \n 2.SET{组件代码}  \n 3.SET{组件别名} ";
	}
	
	$('#table-caseInfo').on("click", "td", function(event) {
		var $td = $(this); // 获取被单击的td单元格
		var cellindex = $td.parent().context._DT_CellIndex.column; // 列号
		if (cellindex == 4) {
			var item = _table2.row($td.closest('tr')).data(); // 获取单击的该行内容
			var itemid = item.itemid; // 获取该行的主键itemid
			var checkbox;
			var ischecked = $("#box_"+itemid).is(":checked");
			if (ischecked) {
				checkbox="Y";
			}else {
				checkbox="N";
			}
			// 点击, 修改f
			common.ajax({
				url : $("#basePath").val() + "playback/updateItem",
				type : "POST",
				data : {
					"itemid" : itemid,
					"column" : cellindex,
					"input" : checkbox
				},
				success : function(data) {
					if (data!="0") {
						$.dialog.tips("修改成功");
					}
				}
			});
		}
	});
	
	//行悬浮事件
	$('#table-caseInfo').on("mouseover", "td", function(event) {
		var $td = $(this); // 获取被单击的td单元格
		var item = _table2.row($td.closest('tr')).data(); // 获取单击的该行内容
		var itemid = item.itemid; // 获取该行的主键itemid
		$("#itemidhover").val(itemid);
//		console.log($("#itemidhover").val());
	});
	
	//以下为自定义右击菜单
	var menu1 = document.getElementById("menu-right");
    document.oncontextmenu = function(env) {
        //阻止执行浏览器默认右击事件
    	var eve = env || window.event;
    	eve.preventDefault();
        //菜单列表
        if (document.getElementById("menu-right")) {
      　　　　　 //菜单定位
            document.getElementById("caseInfoTbody").onmousedown = function(e) {
        		menu1.style.left=e.clientX+'px';
        		menu1.style.top=e.clientY+'px';
                if (document.getElementById("caseInfoTbody")) {
                    if (e.button == 2) {
                    	$('#table-caseInfo').off("mouseover"); //防止在选择菜单的时候hover其他行改变隐藏域itemid的值
                        menu1.style.visibility = "visible";
                    } else {
                        menu1.style.visibility = "hidden";
                    }
                }
            }
        }
//        return false;
    }
    if (document.getElementById("btnUp")) {
        document.getElementById("btnUp").onmousedown = function(e) {
        	menu1.style.visibility = "hidden";
        	//更新itemindex, 上移的减一, 被下移的加一
        	common.ajax({
				url : $("#basePath").val() + "playback/updateItemindexUp",
				type : "POST",
				async : false,
				data : {
					"itemid" : $("#itemidhover").val()
				},
				success : function(data) {
					chakan(caseid);
				}
			});
        }
    }
    if (document.getElementById("btnDown")) {
        document.getElementById("btnDown").onmousedown = function(e) {
        	menu1.style.visibility = "hidden";
        	//更新itemindex, 下移的加一, 被上移的减一
        	common.ajax({
				url : $("#basePath").val() + "playback/updateItemindexDown",
				type : "POST",
				async : false,
				data : {
					"itemid" : $("#itemidhover").val()
				},
				success : function(data) {
					chakan(caseid);
				}
			});
        }
    }
    if (document.getElementById("btnInsert")) {
        document.getElementById("btnInsert").onmousedown = function(e) {
        	menu1.style.visibility = "hidden";
        	//弹出框, 手动输入信息
        	$("#itemcode").val("");
    		$("#itemvalue").val("");
    		$("#tradecode").val("");
    		$("#pagecode").val("");
    		$("#itemtype").val("");
    		$("#itemname").val("");
    		$("#itemevent").val("");
    		$("#caseid_ins").val(caseid);
    		$("#itemcodeMust").html("");
    		$("#pagecodeMust").html("");
    		$("#itemtypeMust").html("");
    		$("#itemnameMust").html("");
    		$("#charu_modal").modal('show');
        	//统计有多少个item
        	common.ajax({
    			url : $("#basePath").val() + "playback/countItem",
    			type : "POST",
    			async : false,
    			data : {
    				"caseid" : caseid
    			},
    			success : function(data) {
    				$("#itemindex_ins").val(data);
    			}
    		});
        }
    }
    //return false;与e.preventDefault();功能相同，但是必须放在最后否则在return后面的内容均不执行

}

/**
 * 插入模态框页面-确定按钮事件
 */
function btnYes_ins() {
	if($("#myModalLabel6").text()=="修改组件"){
		$("#mainform_ins").attr("action",$("#basePath").val() + "/playback/uptItemByItemid");
	}else{
		$("#mainform_ins").attr("action",$("#basePath").val() + "/playback/insertItem");
	}
	if ($("#itemcode").val() != null && $("#itemcode").val() != ""){
		if ($("#pagecode").val() != null && $("#pagecode").val() != ""){
			if ($("#itemtype").val() != null && $("#itemtype").val() != ""){
				if ($("#itemname").val() != null && $("#itemname").val() != ""){
					$("#mainform_ins").form('submit', {
				        success:function(data){
				    		$("#charu_modal").modal('hide');
				    		if($("#myModalLabel6").text()=="修改组件") {
				    			$.dialog.tips("修改成功");
				    			chakan($("#caseid_upt").val());
				    		}else if($("#myModalLabel6").text()=="新增组件"){
				    			$.dialog.tips("添加成功");
				    			chakan($("#caseid_ins").val());
							}
//			    			window.location.href= $("#basePath").val() + "/testService/record";
				        },
				        error: function () {
				        	if($("#myModalLabel6").text()=="修改组件") {
				    			$.dialog.tips("修改失败");
				    		}else if($("#myModalLabel6").text()=="新增组件"){
				    			$.dialog.tips("添加失败");
							}
					    }
					});
				}else {
					$("#itemnameMust").html("数据项名称是必填项！");
				}
			}else {
				$("#itemtypeMust").html("数据项类型是必填项！");
		    }	
		}else {
			$("#pagecodeMust").html("页面代码是必填项！");
		}
	}else {
		$("#itemcodeMust").html("数据项代码是必填项！");
	}
}

var caseid1;
var username1;
var batchcase1;
// 回放执行
function huifang(caseid,username,batchcase) {
	caseid1=caseid;
	username1=username;
	batchcase1=batchcase;
	if (!common.checkuseropt(username,curuser)) {
		return;
	}
	// parent.location.href = $("#basePath").val() + "/testService/replay";
	// 加载下拉参数
	/*
	 * $("#execlient").append("<option value=''> --- </option>");
	 * $("#execlient").append("<option value='3.0'>3.0</option>");
	 * $("#execlient").append("<option value='4.0'>4.0</option>");
	 * $("#execlient").append("<option value='5.0'>5.0</option>");
	 */

	exelist=[];
	common.ajax({
		url : $("#basePath").val() + "/executeclient/ecListstatus1",
		type : "GET",
		/*
		 * data: { "caseid": caseid },
		 */
		dataType : "json",
		success : function(redata) {
			exelist.push(redata.data);
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

	

	$("#myModalLabel4").text("回放执行");
	$("#play-back").modal('show');
	// common.ajax({
	// url: $("#basePath").val() + "/executeclient/ecList",
	// type: "GET",
	// /*data: {
	// "caseid": caseid
	// },*/
	// dataType: "json",
	// success: function (data) {
	// console.log(data);
	// $.dialog.tips("回放成功");
	// }
	// });
	

}

function shipin(caseid) {
	// parent.location.href = $("#basePath").val() + "/testService/radioView";
	$.dialog.tips("正在开发中...");
}

function shujuwaigua(caseid) {
	$("#fileDown").hide();
	//window.location.href= $("#basePath").val() + "/testService/dataPlugin";
	$("#batchCaseMust").html("");
	$("#sealPfxFile").val("");
	$("#dataPlugin-caseid").val(caseid);
	$("#uploadfiles").modal('show');
	common.ajax({
		  url: $('#basePath').val() + "playback/selectBatchcaseByCaseid",
		  type: "POST",
		  data: {
		      "caseid": caseid
		  },
		  success: function (data) {
			  if (data) {
				  $("#batchCaseMust").html("已上传过文件，再次上传会覆盖已有文件");
				  $("#fileDown").show();
			  }
	      }
	});
}

function xiugai(caseid,xiugai_username) {
	if (!common.checkuseropt(xiugai_username,curuser)) {
		return;
	}else {
		$("#xiugaia"+caseid).attr("data-toggle","modal");
		$("#xiugaia"+caseid).attr("data-target","#xiugai_modal");
		
		$("#caseid").val(caseid);
		$("#belongMust").html("");
		$("#caseIdentifierMust").html("");
		$("#caseNameMust").html("");
		common.ajax({
			  url: $('#basePath').val() + "playback/getListByCaseid",
			  type: "POST",
			  data: {
			      "caseid": caseid
			  },
			  success: function (data) {
				  var dataList = data["dataList"][0];
			      $("#caseName").val(dataList.casename);
			      $("#caseIdentifier").val(dataList.caseidentifier);
				  $("#belong").val(dataList.casebelong);
				  $("#tradeCodes").val(dataList.tradecodes);			
				  $("#caseDesc").text(dataList.casedesc);			
		      }
		});
	}
}

// 暂时只删除NAT_EXECUTE_CASE表的数据
function shanchu(caseid,shanchu_username) {
	if (!common.checkuseropt(shanchu_username,curuser)) {
		return;
	}
	message = "确定要删除吗?";

	$.dialog.confirm(message, function() {
		common.ajax({
			url : $("#basePath").val() + "playback/removes",
			type : "POST",
			data : {
				"caseid" : caseid
			},
			success : function(data) {
				$.dialog.tips("删除成功");
				$("#btn_query").click();
			}
		});
	});
}

var _tableexpect;
//预期结果
function addexpectresult(itemid) {
	
document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	
	_tableexpect = $('#expectInfo');
	
	// 加载新数据之前先清空原数据
	if (_tableexpect.html() != "") {
		_tableexpect.dataTable().fnDestroy();
	}
	
	_tableexpect = _tableexpect.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = expect.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/expect/itemexpectList/"+itemid,
	            cache : false,	//禁用缓存
	            data: param,	//传入已封装的参数
	            dataType: "json",
	            success: function(result) {
					$("#itemexpect").modal('show');
//					if (casename == "null"|| casename == "undefined"|| casename == "") {
//						$("#myModalLabel3").text("案例信息");
//					} else {
						$("#expectmyModalLabel3").text("选择预期结果");
	            	
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
		columns: [
	            {
	            	data: "expectid",
	            	width : "0px"
	            },
	            {
	            	className : "ellipsis",	
	            	data: "expectresultname",
	            	orderable:false
	            },
	            {
	            	className : "ellipsis",	
	            	data: "expectresultcode",
	            	orderable:false
	            },
				 {
					className : "ellipsis",
					data: "expectresultdesc",
					orderable : false
				},
	            {
	            	className : "ellipsis",
					data : "expression",
					orderable:false
				},
				{
	                className: "ellipsis",
	                data: "caseCao",
	                orderable: false,
	                render: function (data, type, row, meta) {
							return '<div class="dropdown">' + 
		                    '<a href="javascript:void(0)" onclick="shanchuitemexpect(\'' + row.expectid + '\')">删除</a>' + 
		                    //'<a href="javascript:void(0)" style="padding-left: 10px;" id="qiehuanbut" onclick="bianjitemexpect(\'' + row.expectid + '\',\''+ row.expression+'\')">保存</a>' + 
		                    '</div>';
	                }
	            }
	        ],
	        "columnDefs":[{
	        	"targets": [ 0 ],
	            "visible": false  
	        }],
	        "createdRow": function ( row, data, index ) {
	        },
		"drawCallback": function( settings ) {
        }
	})).api();
	
	/**
	 * 预期检查
	 */
	$('#expectInfo').on("dblclick", "td", function() {
		
		//alert(constSettings.CheckDialogResult);
		var $td = $(this); // 获取被双击的td单元格
		var cellindex = $td.parent().context._DT_CellIndex.column; // 列号
		if (cellindex == 4) {
			var item = _tableexpect.row($td.closest('tr')).data(); // 获取双击的该行内容
			var expectid = item.expectid; 
			var _t = $td.text(); // 被双击的td单元格里的文本内容
			$td.html(""); // 清空被双击的td单元格里的内容
				var $input = $("<input type='text' id='edit_input1' style='width:160px;height:20px;'>"); // 设置一个待添加的input表单
			$input.html(_t);// 保留td单元格原有内容
			if (cellindex == 5) {
			}
			$input.attr("placeholder","单击提示/取消");
			$input.attr("data-toggle","popover1");
			$input.attr("data-placement","right");
			$input.attr("data-trigger","click");
			$input.mouseover(function(){
				popover(item);
			});
			// 下面这句是把input表单添加到被双击的单元格里，并让其获取焦点，并设置在失去焦点时的动作
			$input.appendTo($td).val(_t).focus().blur(function() {
				if (_t != $input.val()) {
					if (cellindex == 4) {
						
						common.ajax({
							url : $("#basePath").val() + "expect/updateItemExpect",
							type : "POST",
							async: false,
							data : {
								"expectid" : expectid,
								"input" : $input.val()
							},
							success : function(data) {
								if (data) {
									$.dialog.tips("修改成功");
//									_tableexpect.draw();
									addexpectresult(itemid);
								}
							},
							error: function(data){
								$.dialog.tips("修改失败");
							}
						});
					}
				}
//				_tableexpect.draw();
				addexpectresult(itemid);
			});
			$('#expectInfo').off("dblclick");
		}
	});
	
	//加载弹出框
	function popover(item) {
		$("[data-toggle='popover1']").popover({  
	        html : true,    
	        title: "表达式",    
	        delay: {show:0, hide:0},  
	        content: function() {  
	        	if(item.expectresultcode==constSettings.P1){
	        		return "弹窗结果检查\n（支持and、or）\n 例 文本检查：\n val=='交易成功'；\n 长度检查：val.len==18; \n 数字检查：val>10 ";
	        	}
	        	if(item.expectresultcode==constSettings.P2){
	        		return "（支持and、or,\n val为当前组件值）\n 例：结果是否为数字：\n {^[0-9]*$} ";
	        	}
	        	if(item.expectresultcode==constSettings.P3){
	        		return "{别名/编号/组件代码}\n 引用案例组件值\n（支持and、or,\n val为当前组件值）例：\n {别名}>10";
	        	}
	        	if(item.expectresultcode==constSettings.P4){
	        		return "x,y坐标定位表格元素";
	        	}
	        }   
		});
	}
	
	
	/**
	 * 添加一条数据
	 */
	$("#ins").click(function() {
		$(".dataTables_empty").remove();
		if ($("#expectInfo>tbody").find("#blankRow").length==0) {
			common.ajax({
				url : $("#basePath").val() + "playback/expectRes",
				async: false,
				type : "POST",
				success : function(data) {
					$("#expectInfo>tbody").append(
							'<tr role="row" class="odd" id="blankRow">' + 
								'<td class=" ellipsis"><select id="expectResName" style="width: 100px;"></select></td>' +
								'<td class=" ellipsis"></td>' + 
								'<td class=" ellipsis"></td>' + 
								'<td class=" ellipsis"></td>' + 
								'<td class=" ellipsis"></td>' + 
							'</tr>');
					$("#expectResName").append('<option></option>');
					for (var i = 0; i < data.length; i++) {
						$("#expectResName").append('<option id="'+data[i].expectresultid+'" value="'+data[i].expectresultid+'">'+data[i].expectresultname+'</option>');
					}
					//选中某一个名称后显示所有数据  并保存
					$("#expectResName").change(function() {
						if ($("#expectResName option:selected").val()!=null && $("#expectResName option:selected").val()!="") {
							var expectresultid = $("#expectResName option:selected").val();
							additemexpect(expectresultid,itemid);
						}
					});
				}
			});
		}else {
			
		}
	});
}

function chakan_xiugai(itemid, caseid) {
	$("#xg"+itemid).attr("data-toggle","modal");
	$("#xg"+itemid).attr("data-target","#charu_modal");
	
	$("#itemid").val(itemid);
	$("#caseid_upt").val(caseid);
	$("#itemcodeMust").html("");
	$("#pagecodeMust").html("");
	$("#itemtypeMust").html("");
	$("#itemnameMust").html("");
	$("#myModalLabel6").text("修改组件");	
	common.ajax({
		  url: $('#basePath').val() + "playback/getItemListByItemid",
		  type: "POST",
		  data: {
		      "itemid": itemid
		  },
		  success: function (data) {
			  var dataList = data["dataList"][0];
			  $("#itemcode").val(dataList.itemcode);
			  $("#itemvalue").val(dataList.itemvalue);
			  $("#tradecode").val(dataList.tradecode);
			  $("#pagecode").val(dataList.pagecode);
			  $("#itemtype").val(dataList.itemtype);
			  $("#itemname").val(dataList.itemname);
			  $("#itemevent").val(dataList.itemevent);
	      }
	});
}

function chakan_shanchu(itemid, caseid) {
	message = "确定要删除这条数据吗?";

	$.dialog.confirm(message, function() {
		common.ajax({
			url : $("#basePath").val() + "playback/removesItem",
			type : "POST",
			data : {
				"itemid" : itemid
			},
			success : function(data) {
				$.dialog.tips("删除成功");
				chakan(caseid);
			}
		});
	});
}

function shanchuitemexpect(expectid) {
	
	message = "确定要删除这条数据吗?";

	$.dialog.confirm(message, function() {
		common.ajax({
			url : $("#basePath").val() + "expect/removesItemExpect",
			type : "POST",
			data : {
				"expectid" : expectid
			},
			success : function(data) {
				$.dialog.tips("删除成功");
				_tableexpect.draw();
			}
		});
	});
}


function bianjitemexpect(expectid,expression,old) {
	common.ajax({
		url : $("#basePath").val() + "expect/updateItemExpect",
		type : "POST",
		data : {
			"expectid" : expectid,
			"expression" : ""
		},
		success : function(data) {
			$.dialog.tips("保存成功");
			_tableexpect.draw();
		}
	});
}

function additemexpect(expectresultid,itemid) {
	common.ajax({
		url : $("#basePath").val() + "expect/addItemExpect",
		type : "POST",
		data : {
			"expectresultid" : expectresultid,
			"itemid" : itemid
		},
		success : function(data) {
			$.dialog.tips("添加成功");
			_tableexpect.draw();
		}
	});
}

var record = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		param.casebelong = $("#casebelong").val();
		param.casename = $("#casename").val();
		param.tradecodes = $("#tradecodes").val();
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

/*常量*/
var CONSTANT = {
		DATA_TABLES : {
			DEFAULT_OPTION : { //DataTables初始化选项
				language: {
					"sProcessing":   "处理中...",
					"sLengthMenu":   "每页 _MENU_ 项",
					"sZeroRecords":  "没有匹配结果",
					"sInfo":         "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
					"sInfoEmpty":    "当前显示第 0 至 0 项，共 0 项",
					"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
					"sInfoPostFix":  "",
					"sSearch":       "搜索:",
					"sUrl":          "",
					"sEmptyTable":     "表中数据为空",
					"sLoadingRecords": "载入中...",
					"sInfoThousands":  ",",
					"oPaginate": {
						"sFirst":    "首页",
						"sPrevious": "上页",
						"sNext":     "下页",
						"sLast":     "末页",
						"sJump":     "跳转"
					},
					"oAria": {
						"sSortAscending":  ": 以升序排列此列",
						"sSortDescending": ": 以降序排列此列"
					}
				},
                autoWidth: false,//禁用自动调整列宽
                stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
                order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
   //             processing: false,	//隐藏加载提示,自行处理
                serverSide: true,	//启用服务器端分页
  //              bLengthChange:false,//取消显示每页多少条
 //               searching: false	//禁用原生搜索
               dom: '<"row"<"col-sm-12"t><"col-sm-6"i><"col-sm-6"p>>'//通过定制dom可以修改datatables的相关样式
			},
			COLUMN: {
                CHECKBOX: {	//复选框单元格
                    className: "td-checkbox",
                    orderable: false,
                    width: "10px",
                    data: null,
                    render: function (data, type, row, meta) {
                        return '<input type="checkbox" class="iCheck">';
                    }
                }
            },
            RENDER: {	//常用render可以抽取出来，如日期时间、头像等
                ELLIPSIS: function (data, type, row, meta) {
                	data = data||"";
                	return '<span title="' + data + '">' + data + '</span>';
                },
            	
            	DOUDATA: function(data, type, row, meta){
            		data = data || "";
            		if (data=="" || "null"==data){
            			return '<span>-</span>';
            		}else{
            			return '<span>'+parseFloat(data).toFixed(2)+'</span>';            			
            		}
            	},
                
                OPT:function(data, type,row,meta){
                	return '<a target="_blank" href=move_deviceFlow?dvid='+data+'>'+"查看"+'</a>';
                },
            	FLAG:function(data,type,row,meta){
            		if ("true"==data){            			
            			return '<i class="flag-success"></i>'
            		}else{
            			return '<i class="flag-fail"></i>'
            		}
            	}
            }
		}
};


var expect = {
		currentItem : null,
		fuzzySearch : true,
		getQueryCondition : function(data) {
			var param = {};
			param.name = "";//$("#ername").val();
			//组装分页参数
			param.startIndex = data.start;
			param.pageSize = data.length;
			param.draw = data.draw;
			param.page = data.start/10 + 1;
			return param;
		}
	};


var expectall = {
		currentItem : null,
		fuzzySearch : true,
		getQueryCondition : function(data) {
			var param = {};
			param.name = "";//$("#ername").val();
			//组装分页参数
			param.startIndex = data.start;
			param.pageSize = data.length;
			param.draw = data.draw;
			param.page = data.start/10 + 1;
			return param;
		}
	};
