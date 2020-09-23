<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li class="active">预期结果管理</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="name">预期结果名称</label> <input type="text"  class="form-control" id="ername" placeholder="预期结果名称">
				</div>
				<button type="button" class="btn btn-default" id="btn_query"><i class="fa fa-search fa-fw"></i>查询</button>
				<a class="btn btn-default" id="addexpect" data-toggle="modal" data-target="#expect"><i class="fa fa-plus-square fa-fw"></i>添加</a>
			</form>
			<div class="table-bordered">
			<table class="table table-striped" id="expect-table">
					<thead>
						<tr>
							<th>预期结果编号</th>
							<th>预期结果名称</th>
							<th>函数</th>
							<th>描述信息</th>
							<th>表达式</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th id="operate">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="expect" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加预期结果</h4>
				</div>
				<div>
					<form id="mainform" action="${basePath}/executeclient/terminaladd" method=post>
						<input type="hidden" id="expectresultid" name="expectresultid" value=""/>
						<input type="hidden" id="creater" name="creater" value=""/>
						<input type="hidden" id="createdate" name="createdate" value=""/>
						<div class="form-group">
							<label for="terminalname">预期结果名称</label><input id="expectresultname" name="expectresultname">
							<br><span class="spanMust" id="nameAlone"></span>
						</div>
						<div class="form-group">
							<label for="terminalip">预期结果函数</label><input id="expectresultcode" name="expectresultcode">
						</div>
						<div class="form-group">
							<label for="terminalinfos">描述信息</label>
							<textarea id="expectresultdesc" name="expectresultdesc" rows="1" style="width:300px;"></textarea>
						</div>
						<div class="form-group">
							<label for="terminalinfos">预期结果表达式</label>
							<textarea id="expression" name="expression" rows="1" style="width:300px;"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btnYes" class="btn btn-default">确定</button>
					<button type="button" id="btnClose" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
	<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.easyui.min.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
	<script src="${basePath}/common/js/datatables/jquery.dataTables.min.js"></script>
	<script src="${basePath}/common/js/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${basePath}/common/js/datatables/dataTables.responsive.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${basePath}/common/js/jquery/messages_zh.js" type="text/javascript"></script>
	<script src="${basePath}/common/js/lhgdialog/lhgdialog.js?skin=bootstrap2"></script>
	<script src="${basePath}/common/js/user_defined/constant.js"></script>
    <script src="${basePath}/common/js/user_defined/common.js"></script>
	<script src="${basePath}/aat/js/testService/expectResult/expectResult.js"></script>
	<!-- Bootstrap Core CSS -->
	<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<!-- DataTables CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="${basePath}/common/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- DataTables Responsive CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.responsive.css" rel="stylesheet">
	<!--backstage body CSS-->
	<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
	<style type="text/css">
		#expect form {
			padding: 30px;
			text-align: center;
		}
		
		.form-group {
			/* dislay: inline-block; */
			height: 32px;
		}
		
		.form-group>label {
			width: 100px;
			font-weight: normal;
		}
		
		.form-group>input {
			width: 300px;
		}
		
		#addexpect {
			margin-right: 15px;
			float: right;
			background: #1081de;
			color: #fff;
		}
		
		.control-label {
			text-align: left;
			font-size: 14px;
			color: #333;
			font-weight: 400;
			padding: 0;
			height: 32px;
			line-height: 32px;
			width: 80px;
		}
		
		.fileinput {
			height: 32px;
			line-height: 32px;
			width: 94px;
		}
		
		#_fileDiv>* {
			display: inline;
		}
		
		#test>* {
			display: inline-block;
		}
		
		#test {
			/* font-size: 0px; */
			
		}
		
		.fileinput>* {
			display: inline-block !important;
		}
		
		#caseItem>.modal-dialog {
			width: 900px !important;
		}
		
		#dataPlugin .modal-body {
			padding: 0;
			margin: 12px 20px 20px 20px
		}
		
		#breadLine {
			border: 0.1px solid #999;
			background: #999;
			margin-bottom: 15px;
			margin-top: -15px;
		}
		
		.breadcrumb {
			display: inline-block;
		}
		
		#upload {
			display: inline-block;
			float: right;
			border: none;
		}
		
		td>a {
			margin-right: 20px;
			color: #1081de;
			text-decoration: none;
		}
		
		#operate {
			width: 150px;
		}
		
		#upload {
			color: #fff;
			background-color: #1081de;
			border-color: #1081de;
		}
		
		#upload>i {
			line-height: 1;
			margin-right: 4px;
		}
		
		.table tbody>tr>td {
			vertical-align: middle;
			padding: 5px 0px 5px 18px;
		}
		#breadLine {
			margin-right: 0px;
			margin-left: 0px;
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
    	.btn {
    		margin-left: 10px;
    		margin-top: 1px;
    	}
    	.spanMust {
			color: red;
			margin-left: -74px;
		}
	</style>

</html>