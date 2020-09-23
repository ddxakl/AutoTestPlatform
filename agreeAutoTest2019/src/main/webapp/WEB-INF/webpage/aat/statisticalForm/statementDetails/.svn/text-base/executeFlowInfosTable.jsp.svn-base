<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="forhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>测试报告</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="${basePath}/aat/css/testService/record/record.css" rel="stylesheet">
<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- DataTables CSS -->
<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="${basePath}/common/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- DataTables Responsive CSS -->
<link href="${basePath}/common/css/datatables/dataTables.responsive.css" rel="stylesheet">
<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
<link href="${basePath}/common/css/bootstrapdatepicker/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="${basePath}/aat/js/statementDetails/executeflowstable-style/css/table/styleTables.css" rel="stylesheet" type="text/css">
<style>
.summary-des {
	margin-top: 10px;
	font-size: 14px;
	color: #666;
	line-height: 30px;
}

.profiles {
	display: inline;
}

#operate {
	width: 150px;
}

#btn_query {
	color: #fff;
/* 	background-color: #1081de; */
	background-color: #52aefa;
	border-color: #fff;
}
#btn_query:hover {
	color: #1081de;
	background-color: #fff;
	border-color: #1081de;
}
#loginfo{padding: 10px; white-space: pre-line;}

#carousel-example-generic img{margin: auto;}

#b04 { width: 640px;}

#b04 .dots { position: absolute; left: 0; right: 0; bottom: 20px;}

#b04 .dots li 

{ 

	display: inline-block; 

	width: 10px; 

	height: 10px; 

	margin: 0 4px; 

	text-indent: -999em; 

	border: 2px solid #fff; 

	border-radius: 6px; 

	cursor: pointer; 

	opacity: .4; 

	-webkit-transition: background .5s, opacity .5s; 

	-moz-transition: background .5s, opacity .5s; 

	transition: background .5s, opacity .5s;

}

#b04 .dots li.active 

{

	background: #fff;

	opacity: 1;

}

.table tbody>tr>td {
	vertical-align: middle;
	padding: 5px 0px 5px 18px;
}

.breadLine {
	border:0.1px solid  #999;
	background:#999;
	margin-bottom: 20px;
	margin-top: -15px;
	margin-right: 0px;
	margin-left: 0px;
}
table.dataTable {
	margin-top: 0px !important;
	margin-bottom: 0px !important;
}
thead {
/* 	background-color: #d2dce740; */
	background-color: #ffffff;
}

.upLT {
	margin-top: 12px;
}
.upRT {
	margin-top: 32px;
}
.downT {
	margin-top: 32px;
}

#b04 .arrow { position: absolute; top: 200px;}

#b04 #al { left: 15px;}

#b04 #ar { right: 15px;}
.resultAnaDiv{
	background-color: #000;
	margin-top: 10px;
	padding: 5px;
}
.resultAnaDiv>p{
	color: #fff;
	margin: 15px 10px 10px 10px;
	font-family: initial;
	font-size: initial;
}
#resultDetails-table>thead>tr>th {
	padding-left: 18px;
	font-family: initial;
}
#resultDetails-table>tbody>tr>td {
	font-family: initial;
}
#resultDetails-table>thead>tr {
	background-color: lightgray;
}
#perSpan {
	margin-left: 15px;
	font-family: initial;
	font-size: initial;
}
.firstf0 {
	display: flex;
	flex-direction: row;
	align-content: center;
}
.firstf01 {
	display: flex;
	flex-direction: column;
	align-content: center;
/* 	margin-right: 5%; */
	margin-left: 2%;
	line-height: 25px;
}
.firstf01>div {
	font-size: 15px;
	color: #134f89;
	font-weight: bold;
}
.firstf01>div>span {
	font-size: 14px;
	font-weight: normal;
}
.firstf01>div>span>a {
	font-size: 14px;
	font-weight: normal;
	color: #134f89;
	cursor: pointer;
}
#third-table-bordered {
	margin-top: 15px;
}
.list-box{width:20%;overflow:hidden;}
#list-box-h3{font-weight:400;font-size:55px;color:#333;margin: 14px 0 7px 0;}
/*list-box*/
.list-box .color_2DBE70{display:block;height:4px;width:100px;background:#2DBE70;margin:0 0 0 30px}
.list-box h4{font-weight:400;margin:25px 0 0 30px;line-height:44px;font-size:25px;color:#333}
</style>
</head>
<body>
<input type="hidden" id="basePath" value="${basePath}" />
<input type="hidden" id="planflowid" value="${planflowid}" />
<input type="hidden" id="planid" value="${planid}" />
<input type="hidden" id="planname" value="${planname}" />
<div class="page-main">
	<div class="title">
		<ul class="breadcrumb">
			<li><a href="${basePath}/admin/index">Home</a></li>
			<li><a href="${basePath}/testService/statementDetails">报表详情</a></li>
			<li class="active">测试报告</li>
		</ul>
	</div>
	<div id="breadLine"></div>
</div>

<!-- pie first -->
<div modeltag="true" id="deviceListModel" canable="true" url="" param="" class="device-handle">
	<div class="device-list" id="_first">
		<div class="device-total" id="auto_first">
			<h3 class="title" id="font_first">测试报告概述</h3>
			<div name="deviceList" class="handle-top-box">
				<div class="device-list-tab" style="padding:25px 30px 10px 30px;">
					<div class="firstf0">
						<div class="firstf01">
							<div>提测计划名称：<span id="sp1">空</span></div>
							<div>开始时间：<span id="sp2">空</span></div>
							<div>结束时间：<span id="sp3">空</span></div>
							<div>总时长：<span id="sp4">空</span></div>
							<div>总数：<span id="sp5">空</span></div>
							<div>成功数：<span id="sp6">空</span></div>
							<div>失败数：<span id="sp7"><a id="a7">空</a></span></div>
						</div>
						<div id="container" style="height: 225px;margin-top: -20px;"></div>
						<div class="list-box">
							<h3 id="list-box-h3"></h3>
							<span class="color_2DBE70"></span>
							<h4>
								<img alt="" src="<%=basePath%>/aat/image/pass.png" style="width: 20%;height: 20%;"> 通过率
							</h4>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- pie first end -->

<!-- bar second -->
<div modeltag="true" id="deviceListModel" canable="true" url="" param="" class="device-handle">
	<div class="device-list" id="_second">
		<div class="device-total" id="auto_second">
			<h3 class="title" id="font_second">案例分析</h3>
			<div name="deviceList" class="handle-top-box">
				<div class="device-list-tab" style="padding:25px 30px 10px 30px;">
					<div id="container2" style="min-width:400px;height:400px"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- bar second end -->

<!-- table third-->
<div modeltag="true" id="deviceListModel2" canable="true" url="" param="" class="device-handle">
	<div class="device-list" id="_deviceListInfo2">
		<div class="device-total" id="auto_content2">
			<h3 class="title" id="detailli"></h3>
			<div name="deviceList" class="handle-top-box">
				<div class="device-list-tab">
					<div id="myTabContent2" name="myTabContent2" class="device-tab-content">
						<div class="tab-pane fade in active" id="allTab2">
							<div class="mainbody">
								<form class="form-inline" role="form">
									<div class="form-group">
										<label class="sr-only" for="name">名称</label> <input type="text"
											class="form-control" id="casename" placeholder="案例名称">
									</div>
									<div class="form-group">
										<label class="sr-only" for="name">名称</label> <input type="text"
											autocomplete="off" class="form-control" id="startDate"
											placeholder="开始时间">
									</div>
									<div class="form-group">
										<label class="sr-only" for="name">名称</label> <input type="text"
											autocomplete="off" class="form-control" id="endDate"
											placeholder="结束时间">
									</div>
									<button type="button" class="btn btn-default" id="btn_query"><i class="fa fa-search fa-fw"></i>查询</button>
								</form>
								<div class="table-bordered" id="third-table-bordered">
									<table class="table table-striped" id="table-flows">
										<thead>
											<tr>
												<th>流水编号</th>
												<th>案例编号</th>
												<th>案例名称</th>
												<th>案例类型</th>
												<th>执行时间</th>
												<th>执行人</th>
												<th>执行终端</th>
												<th>测试结果</th>
												<th id="operate">操作</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- table third end -->

	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="resultDetails" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">案例结果</h4>
				</div>
				<div class="modal-body" style="width: 100%; padding-top: 0;">
					<ul class="nav nav-pills" role="tablist" aria-expanded="true" id="myTab"><!-- nav-tabs. -->
						<li role="presentation" class="active"><a href="#resultAna" role="tab" data-toggle="tab">结果分析</a></li>
						<li role="presentation"><a href="#component" role="tab" data-toggle="tab">组件指标</a></li>
						<li role="presentation"><a href="#performance" role="tab" data-toggle="tab">性能指标</a></li>
						<li role="presentation"><a href="#loginfo" role="tab" data-toggle="tab">日志</a></li>
						<li role="presentation"><a href="#screenShot" role="tab" data-toggle="tab">截图</a></li>
					</ul>

					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="resultAna">
							<div class="resultAnaDiv" id="resultAnaDiv">
								<p id="p0">本案例分析如下：</p>
								<p class="pRes">1. 案例执行<span id="span11" class="pSpan"></span>，案例执行耗时<span id="span12" class="pSpan"></span>秒，操作了<span id="span13" class="pSpan"></span>个页面，共<span id="span14" class="pSpan"></span>个组件；</p>
								<p class="pRes">2. 组件【<span id="span21" class="pSpan"></span>】耗时<span id="span22" class="pSpan"></span>毫秒，执行时间最长；</p>
								<p class="pRes">3. 组件【<span id="span31" class="pSpan"></span>】耗时<span id="span32" class="pSpan"></span>毫秒，执行时间最短；</p>
								<p class="pRes">4. 执行组件耗时超过2秒：<span id="span41" class="pSpan"></span>；</p>
								<p class="pRes">5. CPU使用率最高：<span id="span51" class="pSpan"></span>%；执行组件是【<span id="span52" class="pSpan"></span>】；最低：<span id="span53" class="pSpan"></span>%；执行组件是【<span id="span54" class="pSpan"></span>】；平均值：<span id="span55" class="pSpan"></span>%；</p>
								<p class="pRes">6. 内存使用率最高：<span id="span61" class="pSpan"></span>%；执行组件是【<span id="span62" class="pSpan"></span>】；最低：<span id="span63" class="pSpan"></span>%；执行组件是【<span id="span64" class="pSpan"></span>】；平均值：<span id="span65" class="pSpan"></span>%；</p>
								<p class="pRes" style="color: #ff3400;font-size: larger;">组件<span id="span71" class="pSpan"></span>需要人工核查！</p>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="component">
							<div class="table-bordered" style="margin-top: 20px;">
								<table class="table table-striped" id="resultDetails-table">
									<thead>
										<tr>
											<th>序号</th>
											<th>页面名称</th>
											<th>组件名称</th>
											<th>执行时间(单位/毫秒)</th>
										</tr>
									</thead>
									<tbody>
									
									</tbody>
								</table>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="performance">
							<span id="perSpan"></span>
							<div id="main" style="width: 850px; height: 400px;margin-top: 10px;"></div>
						</div>
						<div role="tabpanel" class="tab-pane" id="loginfo">
						</div>
						 <div role="tabpanel" class="tab-pane" id="screenShot" style="text-align: center">
							<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">

								<!-- Indicators -->
								<ul class="carousel-indicators" id="olnum">
								</ul>
								<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox" id="lunbwqewqotuewqeqw">
									<div class="item active"><img class="img-responsive" src=""</div>
								</div>
								<!-- Controls -->
								<a class="left carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left"></span> <span
									class="sr-only">Previous</span>
								</a> <a class="right carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right"></span> <span
									class="sr-only">Next</span>
								</a>
							</div>

						</div> 
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<!-- jQuery -->
<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
<script src="${basePath}/common/js/datatables/jquery.dataTables.min.js"></script>
<script src="${basePath}/common/js/datatables/dataTables.bootstrap.min.js"></script>
<script src="${basePath}/common/js/datatables/dataTables.responsive.js"></script>
<script src="${basePath}/common/js/jquery/jquery.validate.min.js" type="text/javascript"></script>
<script src="${basePath}/common/js/jquery/messages_zh.js" type="text/javascript"></script>
<script src="${basePath}/common/js/lhgdialog/lhgdialog.js?skin=bootstrap2"></script>
	
<script src="${basePath}/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="${basePath}/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>	
<script src="${basePath}/common/js/echart/echarts.min.js"></script>	
<script src="${basePath}/common/js/highcharts/highcharts.js"></script>	
<script src="${basePath}/common/js/highcharts/highcharts-3d.js"></script>	
<script src="${basePath}/common/js/highcharts/exporting.js"></script>	
<script src="${basePath}/common/js/user_defined/constant.js"></script>
<script src="${basePath}/common/js/user_defined/common.js"></script>
<script src="${basePath}/aat/js/statementDetails/executeflowstable.js"></script>

<script type="text/javascript">
/**
 * 模糊查询-时间
 */
var date = new Date();
$("#startDate").datetimepicker({
	format : 'yyyy-mm-dd',// 'yyyy-mm-dd hh:ii'
	/* endDate : date, */
	autoclose : true,
	minView : 2,
	todayBtn: 1,// 显示‘今日’按钮
	todayHighlight : true,
	keyboardNavigation : true,
	language : 'zh-CN',
	bootcssVer : 3,
	initialDate:date
	/* startDate:date */
}).on('changeDate', function(e) { //获取选取的开始时间 var
	endTimeStart = $("#startDate").val();
	$('#endDate').datetimepicker('setStartDate', e.date);
});
$("#endDate").datetimepicker({
	format : 'yyyy-mm-dd',
	autoclose : true,
	minView : 2,
	todayBtn: 1,// 显示‘今日’按钮
	todayHighlight : true,
	keyboardNavigation : true,
	language : 'zh-CN',
	initialDate:date
}).on('changeDate', function(e) { //获取选取的开始时间 
	var endTimeStart = $("#startDate").val();
	//设置结束时间
	$('#endDate').datetimepicker('setStartDate', e.date);
});
$("#startDate").datetimepicker("setDate", new Date());
$("#endDate").datetimepicker("setDate", new Date());


/**
 * pie饼状图 first
 */
var piecount;
var piesuccess;
var piefail;
//$("#section-title").html("提测计划名称："+$("#planname").val());
var param1 = {};
param1.planflowid=$('#planflowid').val();
common.ajax({
	url : $("#basePath").val()+"/flows/getReportDetailsListByPlanidNoPage",
	type: "POST",
	data : param1,
	dataType : "json",
	success : function(data) {
		var res = data.dataList;
		piecount = res[0].count;
		piesuccess = res[0].succss;
		piefail = res[0].fail;
		var startdate = formatDate(new Date(res[0].startdate));
		var enddate = formatDate(new Date(res[0].enddate));
		var time = formatDuring(res[0].enddate-res[0].startdate)
		$("#sp1").html(res[0].planname);
		$("#sp2").html(startdate);
		$("#sp3").html(enddate);
		$("#sp4").html(time);
		$("#sp5").html(piecount);
		$("#sp6").html(piesuccess);
		$("#a7").html(piefail);
		$("#a7").click(function () {
	    	$.dialog.tips("失败数");
	    });
		$("#list-box-h3").html((piesuccess/(piesuccess+piefail)*100).toFixed(1)+"%");
		//pie
		var chart = Highcharts.chart('container', {
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 45,
					beta: 0
				}
			},
			title: {
				text: ''
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					depth: 35,
					colors : ['#58BA99', '#FF4040'], //饼图颜色
					dataLabels: {
						enabled: true,
						style: {
			                'fontSize' : '12px', //支出标签字体
			                'color' : '#624b24' //支出标签字体
			            },
						format: '{point.name}'
					}
				}
			},
			series: [{
				type: 'pie',
				name: '占比',
				size: '100%', //饼图整体大小
		 		//innerSize: '80%', //环状
				data: [
					['成功数', piesuccess],
					{
						name: '失败数',
						y: piefail,
						sliced: true, //突出显示某个扇区，表示强调
						selected: true
					}
				]
			}]
		});
	}
});


/**
 * bar柱状图 second
 */
var bartotal=[];
var barsuccess=[];
var barfail=[];
var barcasename=[];
var param1 = {};
param1.planflowid=$('#planflowid').val();
common.ajax({
	url : $("#basePath").val()+"/flows/getExecuteFlowsCountListNoPage",
	type: "POST",
	data : param1,
	dataType : "json",
	success : function(data) {
		var res = data.dataList;
		//bar
		$.each(res, function (i, n){
			bartotal.push(n.total);
			barsuccess.push(n.success);
			barfail.push(n.fail);
			barcasename.push(n.casename);
		});
		var chart2 = Highcharts.chart('container2',{
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: ''
		    },
// 		     subtitle: {
// 		         text: '数据来源: WorldClimate.com'
// 		     },
		    xAxis: {
// 		        categories: [
// 		            '案例1','案例2','案例3','案例4','案例5','案例6','案例7','案例8','案例9','案例10'
// 		        ],
				categories: barcasename,
		        crosshair: true
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: ''
		        }
		    },
		    colors: ['#624b24', '#58BA99', '#FF4040'], //柱体颜色
		    tooltip: {
		        // head + 每个 point + footer 拼接成完整的 table
		        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		        '<td style="padding:0"><b>&nbsp;&nbsp;{point.y} </b></td></tr>',
		        footerFormat: '</table>',
		        shared: true,
		        useHTML: true
		    },
		    plotOptions: {
		        column: {
		            borderWidth: 0
		        }
		    },
		    series: [{
		        name: '总数',
// 		        data: [6, 5, 6, 3, 4, 7, 4, 5, 6, 3]
		    	data: bartotal
		    }, {
		        name: '成功数',
		        data: barsuccess
		    }, {
		        name: '失败数',
		        data: barfail
		    }]
		});
	}
});

 

/**
 * 结果详情
 */
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
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
        data: []
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
            data:[],
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
            data:[],
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
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);


function formatDate(date) { 
    var year=date.getFullYear(); 
    var month=date.getMonth()+1; 
    var day=date.getDate(); 
    var hour=date.getHours(); 
    var minute=date.getMinutes(); 
    var second=date.getSeconds(); 
   //  return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second; 
    return year + '-' + (String(month).length > 1 ? month : '0' + month) + '-' + 
    (String(day).length > 1 ? day : '0' + day) + ' ' + (String(hour).length > 1 ? hour : '0' + hour) + ':' + (String(minute).length > 1 ? minute : '0' + minute)
     + ':' + (String(second).length > 1 ? second : '0' + second)
} 

function formatDuring(mss){
//     var days = parseInt(mss / (1000 * 60 * 60 * 24));
    var hours = parseInt((mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = parseInt((mss % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = (mss % (1000 * 60)) / 1000;
    return hours + " 小时 " + minutes + " 分 " + seconds + " 秒 ";
}
</script>
</html>