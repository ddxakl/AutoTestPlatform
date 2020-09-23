<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">报表详情</a></li>
				<li class="active">测试报告</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">1、执行概况</h3>
				</div>
				<div class="panel-body">
					<div>
						<div class="profiles"
							style="width: 50%; height: 200px; float: left;">

							<p class="summary-des"
								style="margin: 10% 50px; text-indent: 2em;">本次测试一共向10台终端发送了100次案例执行操作，发送成功的有90次，在其中执行成功的有70次，失败的20次。</p>
						</div>


						<div class="profiles" id="profilePieChart"
							style="width: 600px; height: 400px;">ssssssssssssssssssssssssss</div>

					</div>
				</div>
			</div>

			<div class="panel panel-default" style="height: 600px">
				<div class="panel-heading">
					<h3 class="panel-title">2、详细信息</h3>
				</div>
				<div class="panel-body">
					<form class="form-inline" role="form">
						<div class="form-group">
							<label class="sr-only" for="name">名称</label> <input type="text"
								class="form-control" id="planNmae" placeholder="所属项目">
						</div>
						<div class="form-group">
							<label class="sr-only" for="name">名称</label> <input type="text"
								class="form-control" id="planNmae" placeholder="案例名称">
						</div>
						<div class="form-group">
							<label class="sr-only" for="name">名称</label> <input type="text"
								class="form-control" id="ABversion" placeholder="AB版本">
						</div>
						<!-- 	<div class="form-group">
							<label class="sr-only" for="name">名称</label> <input type="text"
								autocomplete="off" class="form-control" id="startDate"
								placeholder="开始时间">
						</div>
						<div class="form-group">
							<label class="sr-only" for="name">名称</label> <input type="text"
								autocomplete="off" class="form-control" id="endDate"
								placeholder="结束时间">
						</div> -->
						<button type="submit" class="btn btn-default">查询</button>
					</form>
					<table class="table table-striped">
						<caption>案例执行结果</caption>
						<thead>
							<tr>
								<th>所属项目</th>
								<th>案例名称</th>
								<th>执行时间</th>
								<th>AB版本</th>
								<th>执行终端</th>
								<th>执行结果</th>
								<th id="operate">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>Bangalore</td>
								<td>560001</td>
								<td>Bangalore</td>
								<td>成功</td>
								<td><a data-toggle="modal" data-target="#resultDetails">结果详情</a>
									<a onclick="deleteCase()">删除</a></td>

							</tr>
							<tr>
								<td>Sachin</td>
								<td>Mumbai</td>
								<td>Bangalore</td>
								<td>400003</td>
								<td>Bangalore</td>
								<td>成功</td>
								<td>560001</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>


	<!-- 模态框（Modal） -->
	<div class="modal fade" id="resultDetails" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">案例结果</h4>
				</div>
				<div class="modal-body" style="width: 100%; padding-top: 0;">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#loginfo"
							role="tab" data-toggle="tab">日志</a></li>
						<li role="presentation"><a href="#screenShot" role="tab"
							data-toggle="tab">截图</a></li>
					</ul>

					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="loginfo">
							<p style="width: 75%">日志内容</p>
						</div>
						<div role="tabpanel" class="tab-pane" id="screenShot"
							style="text-align: center">
							<img alt="" src="${basePath}/aat/image/lw.jpg" style="width: 75%">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<!-- <button type="button" class="btn btn-primary">提交更改</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>

<style>
#resultDetails>.modal-dialog>.modal-content>.modal-body  .active>a {
	color: #337ab7;
}

#resultDetails>.modal-dialog>.modal-content>.modal-body  li>a {
	color: #333;
}

#resultDetails>.modal-dialog {
	width: 800px !important;
}

#resultDetails>.modal-dialog>.modal-content {
	height: 600px !important;
}

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
</style>
<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
<script
	src="${basePath}/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>
<script
	src="${basePath}/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="${basePath}/common/js/echart/echarts.min.js"></script>

<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<!-- DataTables CSS -->
<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css"
	rel="stylesheet">
<link
	href="${basePath}/common/css/bootstrapdatepicker/bootstrap-datetimepicker.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="${basePath}/common/css/datatables/dataTables.responsive.css"
	rel="stylesheet">
<!--backstage body CSS-->
<link href="${basePath}/system/css/backstage.css" rel="stylesheet">

<script type="text/javascript">
	var date = new Date();
	$("#startDate").datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		endDate : date,
		autoclose : true,
		minView : 2,
		todayHighlight : true,
		keyboardNavigation : true,
		language : 'zh-CN',
		bootcssVer : 3
	}).on('changeDate', function(e) {
		//获取选取的开始时间
		var endTimeStart = $("#startDate").val();
		console.log(endTimeStart);
		console.log(e.date);
		//设置结束时间
		$('#endDate').datetimepicker('setStartDate', e.date);
	});
	$("#endDate").datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		endDate : date,
		autoclose : true,
		minView : 2,
		todayHighlight : true,
		keyboardNavigation : true,
		language : 'zh-CN'
	}).on('changeDate', function(e) {
		//获取选取的开始时间
		var endTimeStart = $("#startDate").val();
		console.log(date);
		console.log(endTimeStart);
		console.log(e.date);
		//设置结束时间
		$('#endDate').datetimepicker('setStartDate', e.date);
	});

	var mychart = echarts.init(document.getElementById("profilePieChart"));
	mychart.setOption({
		title : {
			text : '测试计划执行概况'
		},
		tooltip : {},
		legend : {
			data : [ '案例数 ' ]
		},
		xAxis : {
			data : [ "案例总数", "发送成功", "执行成功", "执行失败" ]
		},
		yAxis : {},
		series : [ {
			name : '案例数',
			type : 'bar',
			data : [ 5, 20, 36, 10 ],
			itemStyle : {
				normal : {
					color : ' #1081de',
				}
			}
		} ]
	});
</script>
</html>