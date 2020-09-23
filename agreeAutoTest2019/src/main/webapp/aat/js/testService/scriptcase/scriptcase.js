$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	var $table = $('#scriptcase-table');
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = script.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/script/getCaseList",
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
				{
					 className : "ellipsis",
					// //文字过长时用省略号显示，CSS实现
					data : "caseidentifier",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "casename",
					orderable : false
				// render :
				// CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
				// 固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
				// 切记设置table样式为table-layout:fixed;
				// 否则列宽不会强制为指定宽度，也不会出现省略号。
				},
				{
					className : "ellipsis",
					data : "casebelong",
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
					width: "225px",
					orderable : false,
					render : function(data,
							type, row, meta) {
						return '<div class="dropdown">'
								+ '<a href="javascript:void(0)" style="padding-left: 0px;" onclick="executescript(\''
								+ row.caseid
								+ '\',\''
								+ row.userid
								+ '\')">执行脚本</a>'
								+ '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shujuwaigua(\''
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
			"targets" : [ 3 ],
			"visible" : false
		} ],
		"createdRow" : function(row, data,
				index) {
			// $(row).children('td').eq(0).attr('style','text-align:center;')
		},
		"drawCallback" : function(settings) {
			// 渲染完毕后的回调
			// 清空全选状态
			// 默认选中第一行
			// $("tbody
			// tr",$table).eq(0).click();
			// $("#btn_query").blur();
		}
	})).api();

	/**
	 * 查询
	 */
	$("#btn_query").click(function() {
		_table.draw();
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

});


function executescript(caseid,exeusername){
	if (!common.checkuseropt(exeusername,curuser)) {
		return;
	}
	message = "确定要执行脚本吗?";
	$.dialog.confirm(message, function() {
		common.ajax({
			url : $("#basePath").val() + "/script/executescript",
			type : "POST",
			data : {
				"caseid" : caseid
			},
			success : function(data) {
				$.dialog.tips("发送成功");
				$("#btn_query").click();
			}
		});
	});
	
}


function shujuwaigua(caseid) {
	$.dialog.tips("正在开发中...");
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

//暂时只删除NAT_EXECUTE_CASE表的数据
function shanchu(caseid,shanchu_username) {
	if (!common.checkuseropt(shanchu_username,curuser)) {
		return;
	}
	message = "确定要删除吗?";

	$.dialog.confirm(message, function() {
		common.ajax({
			url : $("#basePath").val() + "/script/removes",
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

var script = {
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