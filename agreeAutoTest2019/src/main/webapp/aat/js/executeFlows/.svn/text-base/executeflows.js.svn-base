$(function() {
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	
	var $table2 = $('#table-flows');
	// 加载新数据之前先清空原数据
	if ($table2.html() != "") {
		$table2.dataTable().fnDestroy();
	}

	var _table2 = $table2.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION,
	{
		ajax : function(data, callback,
				settings) {// ajax配置为function,手动调用异步查询

			// 封装请求参数
			var param = executeflowmanage.getQueryCondition(data);

			$.ajax({
					type : "GET",
					url : $("#basePath").val()+"/flows/getResultFlowList",//+ caseid
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
	            	data: "executeflowid",
	            	width : "0px"
	            },
	            {
	            	className : "ellipsis",
	            	data: "caseid",
	            	orderable : false
	            },

				{
					className : "ellipsis",
					data : "casename",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "execlienttype",
					orderable : false,
					render: function (data, type, row, meta) {
	                	if (data == 'RP') {
	                		return "录制";
						}else if (data == 'ST') {
							return "脚本";
						}else {
							return "#";
						}
	                }
				},
				{
					className : "ellipsis",
					data : "startdate",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "enddate",
					orderable : false
				},
				{
					className : "ellipsis",
					data : "executer",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "execlientname",
					orderable : false
				},
				{
					className : "ellipsis", // 文字过长时用省略号显示，CSS实现
					data : "executeresult",
					orderable : false,
					render: function (data, type, row, meta) {
	                	if (data == 'Y'||data == '1') {
	                		return "成功";
						}else if (data == 'N'||data == '2') {
							return "失败";
						}else {
							return "#";
						}
	                }
				},
				{
					className : "ellipsis",
					data : "caseCao",
					orderable : false,
					render : function(data,type, row, meta) {
						return '<div class="dropdown">'	
						 + '<a  href="javascript:void(0)" style="padding-left: 0px;" data-toggle="modal" data-target="#resultDetails" onclick="details(\'' + row.executeflowid + '\',\'' + row.status + '\')">结果详情</a>'
						 + '<a href="javascript:void(0)" style="padding-left: 20px;"  onclick="shanchu(\''+row.executeflowid + '\',\'' + row.executer + '\')">删除</a>'
								+ '</div>';
					}
				} ],
		"columnDefs" : [ {
			"targets": [ 0,1 ],
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
    	_table2.draw();
    });
});


function details(executeflowid){
	$('#myTab a:first').tab('show');
	common.ajax({
		url : $("#basePath").val() + "/flows/getflowLogs/"+executeflowid,
		type : "GET",
		/*data : {
			"caseid" : executeflowid
		},*/
		success : function(flow) {
			$("#loginfo").html('<div style="font-family: Courier New;">' + flow.log + '</div>');
		}
	});
	
	 $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
	        // e.target // 新选项卡对象
	        // e.relatedTarget // 老选项卡对象
	    	common.ajax({
	    		url : $("#basePath").val() + "/flows/getflowPics/"+executeflowid,
	    		type : "GET",
	    		success : function(flowes) {
	    			var reslist = flowes.data;
	    			if (reslist.length>0) {
	                    $("#lunbwqewqotuewqeqw").empty();
	                    $("#olnum").empty();
	                    //填充轮播图数
	                    for(var i=0;i<reslist.length; i++){
	                        if(i == 0){
	                            $("#olnum").append("<li data-target='#carousel-example-generic' data-slide-to='0'  class='active'></li>")
	                        }else{
	                            $("#olnum").append("<li data-target='#carousel-example-generic' data-slide-to='"+i+"'></li>")
	                        }
	                    }
	                    //填充每幅图像的具体信息
	                    for(var i = 0; i < reslist.length; i++) {
	                        if(i == 0) {
	                            $("#lunbwqewqotuewqeqw").append("<div class='item active'>" +
	                                    "<img src='" + reslist[i].resfilename + "' alt='' width='450' height='320'>" +
	                                    "</div>");
	                          /*  +
	                            "<div class='carousel-caption'>" +
	                            "<p> " + "第" + i + "</p>" +
	                            "</div>" +*/
	                        } else {
	                            $("#lunbwqewqotuewqeqw").append("<div class='item'>" +
	                                    "<img src='" + reslist[i].resfilename + "' alt='' width='450' height='320'>" +
	                                    "</div>");
	                        }
	                    }
		    		}
	    		}
	    	});
      });
	 
	/**
     * resultAnaDiv 结果分析
	 */ 
	common.ajax({
        url: $("#basePath").val()+"/flows/getComAndPerByExecuteflowid/"+executeflowid,
        type: "POST",
        success: function (result) {
        	$("#span71").html("");
        	$.each(result,function(key,value){
        		if (key=="comList") {
        			if (JSON.stringify(value[0])=="{}" || value=="") {
        				$("#p0").text("无数据");
        				$(".pRes").hide();
        				$(".pSpan").html("");
        				return;
					}else {
						$("#p0").text("本案例分析如下：");
						$(".pRes").show();
						var pageCodeSet = new Set();
            			var itemNum = 0;
            			var arrDirection = [];
            			var arrItemName = [];
            			$.each(value[0], function (i, n) {
            				arrDirection.push(n.direction);
            				arrItemName.push(n.itemName);
            	        });
            			$("#span41").html("");
            			$.each(value[0], function (i, n) {
            				pageCodeSet.add(n.pageCode);
            				$("#span13").text(pageCodeSet.size);
            				itemNum++;
            				$("#span14").text(itemNum);
            				
            				var itemNameIndexMax = search(arrDirection,Math.max.apply(Math,arrDirection));
            				$("#span21").text(arrItemName[itemNameIndexMax]);
            				$("#span22").text(Math.max.apply(Math,arrDirection));
            				
            				var itemNameIndexMin = search(arrDirection,Math.min.apply(Math,arrDirection));
            				$("#span31").text(arrItemName[itemNameIndexMin]);
            				$("#span32").text(Math.min.apply(Math,arrDirection));
            				
        					if (parseInt(arrDirection[itemNum-1])>2000) {
            					$("#span41").append("<span>【"+arrItemName[itemNum-1]+"】</span>");
            					$("#span71").append("<span>【"+arrItemName[itemNum-1]+"】</span>");
    						}
            	        });
            			if ($("#span41").find("span").length==0) {
            				$("#span41").text("无");
            				$("#p7").hide();
    					}
					}
				}else if (key=="perList") {
					if (JSON.stringify(value[0])=="{}" || value=="") {
        				$("#main").hide();
        				$("#perSpan").text("无数据");
        				$("#perSpan").show()
        				return;
					}else {
						$("#perSpan").hide();
						$("#perSpan").html("")
						$("#main").show();
						var arrCpu = [];
						var arrMemory = [];
	        			var arrItem = [];
	        			var arrCpuNum = 0;
	        			var arrMemoryNum = 0;
	        			$.each(value[0], function (i, n) {
	        				arrCpu.push(n.cpu);
	        				arrMemory.push(n.memory);
	        				arrItem.push(n.item);
	        	        });
	        			for (var i = 0; i < arrCpu.length; i++) {
							arrCpuNum+=arrCpu[i]*1;
						}
	        			for (var i = 0; i < arrMemory.length; i++) {
	        				arrMemoryNum+=arrMemory[i]*1;
						}
	        			var arrCpuAvg = arrCpuNum/(arrCpu.length);
	        			var arrMemoryAvg = arrMemoryNum/(arrMemory.length);
	        			var itemIndexMax1;
	        			var itemIndexMin1;
	        			var itemIndexMax2;
	        			var itemIndexMin2;
						$.each(value[0], function (i, n) {
							//CPU
							itemIndexMax1 = search(arrCpu,Math.max.apply(Math,arrCpu));
							$("#span51").text(Math.max.apply(Math,arrCpu));
	        				$("#span52").text(arrItem[itemIndexMax1]);
	        				
	        				itemIndexMin1 = search(arrCpu,Math.min.apply(Math,arrCpu));
	        				$("#span53").text(Math.min.apply(Math,arrCpu));
	        				$("#span54").text(arrItem[itemIndexMin1]);
	        				$("#span55").text(arrCpuAvg.toFixed(2));
	        				
	        				//Memory
	        				itemIndexMax2 = search(arrMemory,Math.max.apply(Math,arrMemory));
							$("#span61").text(Math.max.apply(Math,arrMemory));
	        				$("#span62").text(arrItem[itemIndexMax2]);
	        				
	        				itemIndexMin2 = search(arrMemory,Math.min.apply(Math,arrMemory));
	        				$("#span63").text(Math.min.apply(Math,arrMemory));
	        				$("#span64").text(arrItem[itemIndexMin2]);
	        				$("#span65").text(arrMemoryAvg.toFixed(2));
	        	        });
//						$("#span71").append("<span>【"+arrItem[itemIndexMax1]+"】</span>");
//						$("#span71").append("<span>【"+arrItem[itemIndexMax2]+"】</span>");
					}
				}else if (key=="resList") {
					if (value[0]=="Y"||value[0] == '1') {
						$("#span11").text("成功");
					}else {
						$("#span11").text("失败");
					}
				}else if (key=="timeList") {
					$("#span12").text(value[0]);
				}
        	});
        }
    });
	
	function search(arr,dst){
	    var i = arr.length;
	    while(i-=1){
	        if (arr[i] == dst){
	           return i;
	        }
	    }
	    return 0;
	}
		
    /**
	 * resultDetails-table 组件指标
	 */ 
	common.ajax({
        url: $("#basePath").val()+"/flows/getComponentindexByExecuteflowid/"+executeflowid,
        type: "POST",
        success: function (result) {
        	var $table5 = $('#resultDetails-table>tbody');
        	if ($table5.html() != "") {
        		$table5.html("");
        	}
        	var data = result[0];
        	var order = 0;
        	$.each(data, function (i, n) {
        		order++;
        		var pageCode = n.pageCode;
        		var itemName = n.itemName;
        		var direction = n.direction;
        		$("#resultDetails-table>tbody").append(
        				'<tr>'+
        					'<td>'+order+'</td>'+
        					'<td>'+pageCode+'</td>'+
        					'<td>'+itemName+'</td>'+
        					'<td>'+direction+'</td>'+
        				'</tr>'	
				 );
	        });
        }
    });
	
	/**
	 * 性能指标
	 */ 
	var ycpu=[];
    var ymemory=[];
    var xtime=[];
    var xcount=0;
	common.ajax({
        url: $("#basePath").val()+"/flows/getPerformanceByExecuteflowid/"+executeflowid,
        type: "POST",
        success: function (result) {
        	var data = result[0];
        	$.each(data, function (i, n) {
        		ycpu.push(n.cpu);
        		ymemory.push(n.memory);
        		xtime.push(xcount++);
        		
                myChart.setOption({ //加载数据图表
                	tooltip: {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['CPU','内存']
            	    },
            	    toolbox: {
            	        show: true,
            	        feature: {
            	            dataZoom: {
            	                yAxisIndex: 'none'
            	            },
            	            dataView: {readOnly: false},
            	            magicType: {type: ['line', 'bar']},
            	            restore: {},
            	            saveAsImage: {}
            	        }
            	    },
            	    xAxis:  {
            	        type: 'category',
            	        name: '单位/s',
            	        boundaryGap: false,
            	        data: xtime
            	    },
            	    yAxis: {
            	        type: 'value',
            	        name: 'CPU/内存使用率',
            	        axisLabel: {
            	            formatter: '{value} %'
            	        }
            	    },
            	    series: [
            	        {
            	            name:'CPU',
            	            type:'line',
            	            data:ycpu,
            	            markPoint: {
            	                data: [
            	                    {type: 'max', name: '最大值'},
            	                    {type: 'min', name: '最小值'}
            	                ]
            	            },
            	            markLine: {
            	                data: [
            	                    {type: 'average', name: '平均值'}
            	                ]
            	            }
            	        },
            	        {
            	            name:'内存',
            	            type:'line',
            	            data:ymemory,
            	            markPoint: {
            	                data: [
            	                    {type: 'max', name: '最大值'},
            	                    {type: 'min', name: '最小值'}
            	                ]
            	            },
            	            markLine: {
            	                data: [
            	                    {type: 'average', name: '平均值'}
            	                ]
            	            }
            	        }
            	    ]
                });
	        });
        }
    });
}


function shanchu(executeflowid, shanchu_username) {
	if (!common.checkuseropt(shanchu_username,curuser)) {
		return;
	}
	
    message = "确定要删除吗?";

    $.dialog.confirm(message, function () {
        common.ajax({
            url: $("#basePath").val() + "/flows/delflows",
            type: "GET",
            data: {
                "executeflowid": executeflowid
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

var executeflowmanage = {
	currentItem : null,
	fuzzySearch : true,//开启模糊查询
	getQueryCondition : function(data) {
		var param = {};
		param.casename=$('#casename').val();
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
