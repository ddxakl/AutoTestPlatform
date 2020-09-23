$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	//加载下拉参数 
	$("#status").append("<option value=''>终端状态</option>");
	$("#status").append("<option value='1'>启用</option>");
	$("#status").append("<option value='0'>禁用</option>");
	
	var $table = $('#exeTerminal-table');
	
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = exeTerminal.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/executeclient/ecList",
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
		columns: [
	            {
	            	data: "terminalid",
	            	width : "0px"
	            },
	            {
	            	className : "ellipsis",	
	            	data: "name",
	            	orderable:false
	            },
				 {
					className : "ellipsis",
					data: "descinfos",
					orderable : false
				},
	            {
	            	className : "ellipsis",
					data : "ip",
					orderable:false
				},
				 {
					className : "ellipsis",
					data: "port",
					orderable : false
				},		
				 {
					className : "ellipsis",
					data: "status",
					orderable : false,
					render: function (data, type, row, meta) {
	                	if (data == 0) {
	                		return "禁用";
						}else if (data == 1) {
							return "启用";
						}else {
							return "#";
						}
	                }
				},
				{
	                className: "ellipsis",
	                data: "caseCao",
	                orderable: false,
	                render: function (data, type, row, meta) {
	                	if (row.status == 0) {
	                		return '<div class="dropdown">' + 
		                    '<a id="isenablea" href="javascript:void(0)" onclick="isEnable(\'' + row.terminalid + '\',\'' + row.status + '\')">启用</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" data-toggle="modal" data-target="#terminal" onclick="xiugai(\'' + row.terminalid + '\')">修改</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.terminalid + '\')">删除</a>' + 
		                    '</div>';
						}else if (row.status == 1){
							return '<div class="dropdown">' + 
		                    '<a id="isenablea" href="javascript:void(0)" onclick="isEnable(\'' + row.terminalid + '\',\'' + row.status + '\')">禁用</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" data-toggle="modal" data-target="#terminal" onclick="xiugai(\'' + row.terminalid + '\')">修改</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.terminalid + '\')">删除</a>' + 
		                    '</div>';
						}else {
							return '<div class="dropdown">' + 
		                    '<a id="isenablea" href="javascript:void(0)">#</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" data-toggle="modal" data-target="#terminal" onclick="xiugai(\'' + row.terminalid + '\')">修改</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.terminalid + '\')">删除</a>' + 
		                    '</div>';
						}
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
     * 查询
     */
    $("#btn_query").click(function () {
        _table.draw();
    });
    
    /**
     * 添加按钮
     */
    $("#addTerminal").click(function(){
		clearDev();
		$("#nameAlone").html("");
		$("#btnYes").attr("disabled",true);
		$("#myModalLabel").text("新增组件");
	});
    
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		if($("#myModalLabel").text()=="修改组件"){
			$("#mainform").attr("action",$("#basePath").val() + "executeclient/terminalupt");
		}else{
			$("#mainform").attr("action",$("#basePath").val() + "executeclient/terminaladd");
		}
		$("#mainform").form('submit', {
	        success:function(data){
	    		$("#terminal").modal('hide');
	    		_table.draw();
	    		if($("#myModalLabel").text()=="修改组件") {
	    			$.dialog.tips("修改成功");
	    		}else if($("#myModalLabel").text()=="新增组件"){
	    			$.dialog.tips("添加成功");
				}
	        },
	        error: function () {
	        	if($("#myModalLabel").text()=="修改组件") {
	    			$.dialog.tips("修改失败");
	    		}else if($("#myModalLabel").text()=="新增组件"){
	    			$.dialog.tips("添加失败");
				}
		    }
		});
	});
	
	/**
	 * 终端名称离焦事件:确保唯一
	 */
	$("#terminalname").blur(function() {
		if($("#myModalLabel").text()=="新增组件"){
			if ($("#terminalname").val()==null || $("#terminalname").val().trim()=="") {
				$("#nameAlone").html("终端名称不能为空！");
				$("#btnYes").attr("disabled",true);
			}else {
				$("#nameAlone").html("");
				$("#btnYes").attr("disabled",false);
				common.ajax({
					  url: $('#basePath').val() + "/executeclient/sltTerminalByName",
					  type: "GET",
					  data: {
					      "name": $("#terminalname").val()
					  },
					  success: function (data) {
					  	  if (data) {
					  		$("#nameAlone").html("终端名称已经存在！");
					  		$("#btnYes").attr("disabled",true);
					  	  }
				      },
			          error: function () {
			        	  $.dialog.tips("出现错误！");
				      }
				});
			}
		}else if($("#myModalLabel").text()=="修改组件"){
			if ($("#terminalname").val()==null || $("#terminalname").val().trim()=="") {
				$("#nameAlone").html("终端名称不能为空！");
				$("#btnYes").attr("disabled",true);
			}else {
				$("#nameAlone").html("");
				$("#btnYes").attr("disabled",false);
				common.ajax({
					  url: $('#basePath').val() + "/executeclient/sltTerminalByName2",
					  type: "GET",
					  data: {
					      "name": $("#terminalname").val()
					  },
					  success: function (data) {
					  	  if (data) {
					  		$("#nameAlone").html("终端名称已经存在！");
					  		$("#btnYes").attr("disabled",true);
					  	  }
				      },
			          error: function () {
			        	  $.dialog.tips("出现错误！");
				      }
				});
			}
		}
	});
		
});

function isEnable(terminalid, status) {
	common.ajax({
		  url: $('#basePath').val() + "/executeclient/isEnable",
		  type: "GET",
		  data: {
		      "terminalid": terminalid,
		      "status": status
		  },
		  success: function (data) {
		  	  $.dialog.tips("已" + $("#isenablea").text());
		  	  $("#btn_query").click();
	      },
          error: function () {
        	  $.dialog.tips("出现错误");
	      }
	});
}

function xiugai(terminalid) {
	$("#nameAlone").html("");
	$("#btnYes").attr("disabled",false);
	$("#myModalLabel").text("修改组件");	
	common.ajax({
		  url: $('#basePath').val() + "/executeclient/getList",
		  type: "GET",
		  data: {
		      "terminalid": terminalid
		  },
		  success: function (data) {
			  var dataList = data["dataList"][0];
		      $("#terminalname").val(dataList.name);
			  $("#terminalip").val(dataList.ip);
			  $("#terminalport").val(dataList.port);			
		  	  $("#terminalinfos").val(dataList.descinfos);
		  	  $("#terminalid").val(dataList.terminalid);
	      }
	});

}

function shanchu(terminalid) {
    message = "确定要删除吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/executeclient/terminaldlt",
            type: "GET",
            data: {
                "terminalid": terminalid
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

/**
 * 清空增加模态框值
 */
function clearDev() {
	$("#terminalname").val("");
	$("#terminalip").val("");
	$("#terminalport").val("");			
	$("#terminalinfos").val("");
}

var exeTerminal = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		param.name = $("#name").val();
		param.ip = $("#ip").val();
		param.status = $("#status option:checked").text();
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.page = data.start/10 + 1;
		return param;
	}
};