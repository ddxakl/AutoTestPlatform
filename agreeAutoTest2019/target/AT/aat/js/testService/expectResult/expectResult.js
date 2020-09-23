$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	
	var $table = $('#expect-table');
	
	// 加载新数据之前先清空原数据
//	if ($table2.html() != "") {
//		$table2.dataTable().fnDestroy();
//	}
	
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = expect.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/expect/expectList",
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
	            	data: "expectresultid",
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
					className : "ellipsis",
					data: "creater",
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
		                    '<a href="javascript:void(0)" style="padding-left: 0px;" data-toggle="modal" data-target="#expect" onclick="xiugai(\'' + row.expectresultid + '\')">修改</a>' + 
		                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.expectresultid + '\')">删除</a>' + 
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
     * 查询
     */
    $("#btn_query").click(function () {
        _table.draw();
    });
    
    /**
     * 添加按钮
     */
    $("#addexpect").click(function(){
		clearDev();
		$("#nameAlone").html("");
		//$("#btnYes").attr("disabled",true);
		$("#myModalLabel").text("新增组件");
	});
    
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		if($("#myModalLabel").text()=="修改组件"){
			$("#mainform").attr("action",$("#basePath").val() + "expect/uptExpect");
		}else{
			$("#mainform").attr("action",$("#basePath").val() + "expect/addExpect");
		}
		$("#mainform").form('submit', {
	        success:function(data){
	    		$("#expect").modal('hide');
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
	 * 离焦事件:确保唯一
	 */
	$("#exp").blur(function() {
		if($("#myModalLabel").text()=="新增组件"){
			if ($("#expectresultname").val()==null || $("#expectresultname").val().trim()=="") {
				$("#nameAlone").html("名称不能为空！");
				$("#btnYes").attr("disabled",true);
			}else {
				$("#nameAlone").html("");
				$("#btnYes").attr("disabled",false);
				common.ajax({
					  url: $('#basePath').val() + "/expect/uptExpect",
					  type: "GET",
					  data: {
					      "name": $("#expectresultname").val()
					  },
					  success: function (data) {
					  	  if (data) {
					  		$("#nameAlone").html("名称已经存在！");
					  		$("#btnYes").attr("disabled",true);
					  	  }
				      },
			          error: function () {
			        	  $.dialog.tips("出现错误！");
				      }
				});
			}
		}else if($("#myModalLabel").text()=="修改组件"){
			if ($("#expectresultname").val()==null || $("#expectresultname").val().trim()=="") {
				$("#nameAlone").html("名称不能为空！");
				$("#btnYes").attr("disabled",true);
			}else {
				$("#nameAlone").html("");
				$("#btnYes").attr("disabled",false);
				common.ajax({
					  url: $('#basePath').val() + "/expect/uptExpect",
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


function xiugai(expectresultid) {
	$("#nameAlone").html("");
	$("#btnYes").attr("disabled",false);
	$("#myModalLabel").text("修改组件");	
	common.ajax({
		  url: $('#basePath').val() + "/expect/getList",
		  type: "POST",
		  data: {
		      "expectresultid": expectresultid
		  },
		  success: function (data) {
			  var dataList = data.data;
		      $("#expectresultname").val(dataList.expectresultname);
			  $("#expectresultcode").val(dataList.expectresultcode);
			  $("#expectresultdesc").val(dataList.expectresultdesc);			
		  	  $("#expression").val(dataList.expression);
		  	  $("#expectresultid").val(dataList.expectresultid);
		  	  $("#creater").val(dataList.creater);
		  	  $("#createdate").val(dataList.createdate);
		  	
		  	
	      }
	});

}

function shanchu(expectresultid) {
    message = "确定要删除吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/expect/delExpect",
            type: "GET",
            data: {
                "expectresultid": expectresultid
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
	$("#expectresultname").val("");
	$("#expectresultcode").val("");
	$("#expectresultdesc").val("");			
	$("#expression").val("");
}

var expect = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		param.name = $("#ername").val();
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.page = data.start/10 + 1;
		return param;
	}
};