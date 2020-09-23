$(function() {
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	var $table = $('#table-report');
	// 加载新数据之前先清空原数据
	if ($table.html() != "") {
		$table.dataTable().fnDestroy();
	}

	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION,
	{
		ajax : function(data, callback,
				settings) {// ajax配置为function,手动调用异步查询

			// 封装请求参数
			var param = reportdetailsmanage.getQueryCondition(data);

			$.ajax({
				type : "GET",
				url : $("#basePath").val()+"/report/getReportDetailsList",//+ caseid
				cache : false, // 禁用缓存
				data : param, // 传入已封装的参数
				dataType : "json",
				success : function(result) {
					
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
	            	data: "planname",
	            	orderable : false
	            },

				{
					className : "ellipsis",
					data : "executer",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "executeresult",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "targetterminals",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "startdate",
					orderable : false,
					width: "130px",
					render : function(data,type, row, meta) {
						return gmtToStr(data);
					}
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "enddate",
					orderable : false,
					width: "130px",
					render : function(data,type, row, meta) {
						return gmtToStr(data);
					}
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "count",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "succss",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "fail",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "status",
					orderable : false,
					render : function(data,type, row, meta) {
						if(data=='1'){
							return '执行中';
						}else if(data=='2'){
							return '已完成';
						}else{
							return "未知";
						}
					}
				},
				{
					className : "ellipsis",
					data : "caseCao",
					orderable : false,
					render : function(data,type, row, meta) {
						if (row.status=='2') {
							return '<div class="dropdown">'	
							 + '<a target="_self" href="'+$("#basePath").val()+'statisticalForm/statementDetailsFlowTable/'+row.planflowid+"/"+row.planid+"/"+row.planname+'" style="padding-left: 0px;">测试报告</a>'
							 + '<a href="javascript:void(0)" style="padding-left: 20px;"  onclick="shanchu(\''+row.planflowid+ '\')">删除</a>'
							 + '</div>';
						}else {
							return '<div class="dropdown">'	
							 +'<a href="javascript:void(0)" style="padding-left: 0px;color: #9ea1a3bd;">测试报告</a>'
							 + '<a href="javascript:void(0)" style="padding-left: 20px;color: #9ea1a3bd;">删除</a>'
							 + '</div>';
						}
					}
				} ],
		"columnDefs" : [ {
			"targets": [ 2 ],
            "visible": false
		} ],
		"createdRow" : function(row, data,
				index) {
			// 行渲染回调,在这里可以对该行dom元素进行任何操作
		},
		"initComplete" : function(settings,
				json) {

		},
		"drawCallback" : function(settings) {

		}
	})).api();// 此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象


	 /**
     * 查询
     */
    $("#btn_query").click(function () {
    	_table.draw();
    });
});

function shanchu(planflowid) {
    message = "确定要删除吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/report/delreport",
            type: "POST",
            data: {
                "planflowid": planflowid
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

//日期时间格式化
function gmtToStr(time){ 	
	let date = new Date(time) 
	let month = date.getMonth()+1
	let day = date.getDate()
	let hour = date.getHours()
	let minute = date.getMinutes()
	let second = date.getSeconds()
	if(date.getMonth()<=8){
		month='0'+(date.getMonth()+1)
	}
	if(date.getDate()<=9){
		day='0'+date.getDate()
	}
	if(date.getHours()<=9){
		hour='0'+date.getHours()
	}
	if(date.getMinutes()<=9){
		minute='0'+date.getMinutes()
	}
	if(date.getSeconds()<=9){
		second='0'+date.getSeconds()
	}
	let Str=date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second
	return Str;
}

var reportdetailsmanage = {
	currentItem : null,
	fuzzySearch : true,//开启模糊查询
	getQueryCondition : function(data) {
		var param = {};
		param.planname=$('#planName').val();
		param.startdate=$('#startDate').val();
		param.enddate=$('#endDate').val();
			
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.page = data.start / 10 + 1;
		return param;
	}
};


