<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
		rel="stylesheet">
	<link href="${basePath}/aat/css/testService/record/record.css"
		rel="stylesheet">
	<!-- Bootstrap Core CSS -->
	<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
		rel="stylesheet">
	
	<!-- DataTables CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${basePath}/common/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- DataTables Responsive CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.responsive.css" rel="stylesheet">
	<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
	<link href="${basePath}/common/css/bootstrapdatepicker/bootstrap-datetimepicker.css" rel="stylesheet">
	<style>
		.table tbody>tr>td {
			vertical-align: middle;
    		padding: 5px 0px 5px 18px;
    	}
    	#breadLine {
    		margin-right: 0px;
    		margin-left: 0px;
    	}
		#operate {
			width: 150px;
		}
    	.table-bordered {
    		margin-top: 20px;
    	}
		#btn_query {
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	#btn_query:hover {
    		color: #1081de;
    		background-color: #fff;
    		border-color: #1081de;
    	}
	</style>
</head>
<body>
<input type="hidden" id="basePath" value="${basePath}" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li class="active">报表详情</li>
			</ul>
		</div>
		<div id="breadLine"></div>

		<div class="mainbody">
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="planName">名称</label> <input type="text"
						class="form-control" id="planName" placeholder="计划名称">
				</div>
				<div class="form-group">
					<label class="sr-only" for="startDate">名称</label> <input type="text"
						autocomplete="off" class="form-control" id="startDate"
						placeholder="开始时间">
				</div>
				<div class="form-group">
					<label class="sr-only" for="endDate">名称</label> <input type="text"
						autocomplete="off" class="form-control" id="endDate"
						placeholder="结束时间">
				</div>
				<button type="button" class="btn btn-default" id="btn_query"><i class="fa fa-search fa-fw"></i>查询</button>
			</form>
			<div class="table-bordered">
				<table class="table table-striped" id="table-report">
					<thead>
						<tr>
							<th>计划名称</th>
							<th>执行人</th>
							<th>执行结果</th>
							<th>执行机</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>总数</th>
							<th>成功数</th>
							<th>失败数</th>
							<th>执行状态</th>
							<th id="operate">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
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
	<script src="${basePath}/common/js/user_defined/constant.js"></script>
    <script src="${basePath}/common/js/user_defined/common.js"></script>
	<script src="${basePath}/aat/js/statementDetails/statementDetails.js"></script>

<script type="text/javascript">
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
	/* var mychart = echarts.init(document.getElementById("profilePieChart")); */
</script>
</html>