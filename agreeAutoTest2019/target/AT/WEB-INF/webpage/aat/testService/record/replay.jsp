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
				<li><a href="#">录制回放</a></li>
				<li class="active">回放案例</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="终端名称">
				</div>
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="终端ip">
				</div>
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="创建人">
				</div>
				<button type="submit" class="btn btn-default">查询</button>

				<button class="btn btn-default" id="execute" onclick="execute()">执行</button>

			</form>
			<div class="terminal-table">
				<table class="table table-striped">
					<!-- <caption>选择需要执行的设备</caption> -->
					<thead>
						<tr>
							<th></th>
							<th>执行终端名称</th>
							<th>执行终端ip</th>
							<th>创建人</th>
							<th>是否可用</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox"></td>
							<td>Tanmay</td>
							<td>Bangalore</td>
							<td>560001</td>
							<td>Bangalore</td>

						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>Sachin</td>
							<td>Mumbai</td>
							<td>400003</td>
							<td>Bangalore</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="breadLine"></div>
		<div class="footer">
			<ul class="pagination">
				<li><a href="#">&laquo;</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">&raquo;</a></li>
			</ul>
		</div>
	</div>
</body>
<style>
#execute {
	margin-right: 15px;
	float: right;
	background: #1081de;
	color: #fff;
}

.terminal-table {
	margin-top: 30px;
}

.breadLine {
	border: 0.5px solid #999;
	background: #999;
	margin-bottom: 15px;
	margin-top: -15px;
	margin-left: 15px;
	margin-right: 15px;
	background: #999;
}
</style>
<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="${basePath}/common/css/datatables/dataTables.responsive.css"
	rel="stylesheet">
<!--backstage body CSS-->
<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
<script>
	
</script>
</html>