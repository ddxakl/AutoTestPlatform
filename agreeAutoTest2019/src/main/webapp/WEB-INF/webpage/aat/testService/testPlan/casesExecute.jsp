<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">提测计划</a></li>
				<li class="active">添加终端</li>
			</ul>
		</div>
		<div class="breadLine"></div>
		<div class="page-step-bar">
			标准兼容测试
			<ul>
				<li class="active"><span>1</span>分配执行终端</li>
				<li><span>2</span>补充测试选项</li>
				<li><span>3</span>提交测试</li>


			</ul>
		</div>

		<div id="switchPages">
			<div id="pickTerminal"><%@include file="./pickTerminal.jsp"%></div>
			<div id="addinfos"><%@include file="./addInfos.jsp"%></div>
			<div id="submit"><%@include file="./submit.jsp"%></div>
		</div>


	</div>
	<div class="modal fade" id="terminal-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择执行终端</h4>
				</div>
				<div style="margin: 30px 25px 30px 25px;">
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
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<style type="text/css">


#addinfos, #submit {
	display: none;
}

#terminal-modal .modal-dialog {
	width: 800px !important;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	background-color: #1081de;
	border-color: #1081de;
}

.pagination {
	margin-right: 15px;
}

.footer>div {
	text-align: right;
}

.page-step-bar ul li+li:before {
	display: block;
	content: "\20";
	width: 50px;
	height: 0;
	border-top: 1px solid #ccc;
	top: 50%;
	margin-left: -65px;
	position: absolute;
}

.page-step-bar ul li+li {
	margin-left: 80px;
	position: relative;
}

.page-step-bar ul {
	text-align: center
}

.page-step-bar>ul * {
	display: inline-block;
}

.page-step-bar  li span {
	width: 22px;
	height: 22px;
	border-radius: 50%;
	background: #ccc;
	color: #fff;
	text-align: center;
	line-height: 22px;
}

.page-step-bar ul li.active {
	color: #1081de;
}

.page-step-bar ul li.active span {
	background-color: #1081de;
}

.breadLine {
	border: 0.1px solid #999;
	background: #999;
	margin-bottom: 15px;
	margin-top: -15px;
	/* margin-left: 15px;
	margin-right: 15px; */
}

.breadcrumb {
	display: inline-block;
}

td>a {
	margin-right: 20px;
	color: #1081de;
	text-decoration: none;
}

#operate, #terminal {
	width: 200px;
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
<link href="${basePath}/aat/css/scriptInfos/scriptInfos.css" rel="stylesheet">
<script type="text/javascript">
	function switchPages(pageId, index) {
		$("#switchPages").children().css("display", "none");
		$("#" + pageId).css("display", "block");
		$(".page-step-bar li").removeClass("active");
		$(".page-step-bar li:lt(" + index + ")").addClass("active");
	}
</script>

</html>