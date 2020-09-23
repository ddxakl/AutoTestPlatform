$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	
	var $table = $('#dict-table');
	
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = dictManage.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/dictionaryManagement/dicttypeList",
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
	            	data: "dicttypeid",
	            	width : "0px"
	            },
	            {
	            	className : "ellipsis",	
	            	data: "dictcname",
					width: "180px",
	            	orderable:false
	            },
				 {
					className : "ellipsis",
					data: "dictename",
					width: "180px",
					orderable : false
				},
	            {
	            	className : "ellipsis",
					data : "datatype",
					width: "180px",
					orderable:false
				},
				{
	                className: "ellipsis",
	                data: "caseCao",
	                orderable: false,
	                render: function (data, type, row, meta) {
                		return '<div class="dropdown">' + 
                		'<button type="button" style="margin-left: 0px; margin-top: 0px; background-color: #00B2EE; color: #ffffff;" class="btn btn-down dropdown-toggle" data-toggle="modal" data-target="#myModal_keyValue" onclick="getDictValues(\'' + row.dicttypeid + '\')">'+"取值列表 "+'</button>' +
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" data-toggle="modal" data-target="#dict" onclick="xiugai(\'' + row.dicttypeid + '\')">修改</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.dicttypeid + '\')">删除</a>' + 
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
    $("#addDict").click(function(){
		clearDev();
		$("#myModalLabel").text("新增字典");
	});
    
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		if($("#myModalLabel").text()=="修改字典"){
			$("#mainform").attr("action",$("#basePath").val() + "dictionaryManagement/dictupt");
		}else{
			$("#mainform").attr("action",$("#basePath").val() + "dictionaryManagement/dictadd");
		}
		$("#mainform").form('submit', {
	        success:function(data){
	    		$("#dict").modal('hide');
	    		_table.draw();
	    		if($("#myModalLabel").text()=="修改字典") {
	    			$.dialog.tips("修改成功");
	    		}else if($("#myModalLabel").text()=="新增字典"){
	    			$.dialog.tips("添加成功");
				}
	        },
	        error: function () {
	        	if($("#myModalLabel").text()=="修改字典") {
	    			$.dialog.tips("修改失败");
	    		}else if($("#myModalLabel").text()=="新增字典"){
	    			$.dialog.tips("添加失败");
				}
		    }
		});
	});
	
});

function getDictValues(dicttypeid) {
	$("#myModalLabel2").text("管理取值列表");
	showDiv(dicttypeid);
}

function xiugai(dicttypeid) {
	$("#myModalLabel").text("修改字典");	
	common.ajax({
		  url: $('#basePath').val() + "/dictionaryManagement/getListById",
		  type: "POST",
		  data: {
		      "dicttypeid": dicttypeid
		  },
		  success: function (data) {
			  var dataList = data["dataList"][0];
		      $("#dictdictcname").val(dataList.dictcname);
			  $("#dictdictename").val(dataList.dictename);
			  $("#dictdatatype").val(dataList.datatype);			
			  $("#dicttypeid").val(dataList.dicttypeid);			
	      }
	});
}

function shanchu(dicttypeid) {
    message = "确定要删除吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/dictionaryManagement/dictdlt",
            type: "GET",
            data: {
                "dicttypeid": dicttypeid
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
	$("#dictdictcname").val("");
	$("#dictdictename").val("");
	$("#dictdatatype").val("");			
}

var dictManage = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		param.dictcname = $("#dictcname").val();
		param.dictename = $("#dictename").val();
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.page = data.start/10 + 1;
		return param;
	}
};