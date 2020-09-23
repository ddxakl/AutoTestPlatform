<%@page import="com.agree.system.entity.SystemUser"%>
<%@page import="com.agree.framework.constant.ApplicationKeyConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	SystemUser user = (SystemUser)session.getAttribute(ApplicationKeyConst.USER_INFO);
	String username = user.getUsername();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>脚本管理</title>
	
	<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
		rel="stylesheet">
	<link href="${basePath}/aat/css/testService/record/record.css"
		rel="stylesheet">
	<!-- Bootstrap Core CSS -->
	<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
		rel="stylesheet">
	
	<!-- DataTables CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css"
		rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${basePath}/common/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- DataTables Responsive CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.responsive.css"
		rel="stylesheet">
	<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
	<link href="${basePath}/common/css/bootstrapdatepicker/bootstrap-datetimepicker.css" rel="stylesheet">
<style>
.table tbody>tr>td {
			vertical-align: middle;
    		padding: 5px 0px 5px 18px;
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
	margin-left: 15px;
	margin-right: 15px;
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
.table-bordered {
    		margin-top: 20px;
    	}
#xiugai_modal form {
	padding: 30px;
	text-align: center;
}
.form-group2 {
	height: 32px;
	margin-bottom: 20px;
}

.form-group2>label {
	width: 100px;
	font-weight: normal;
}

.form-group2>input {
	width: 300px;
}
.form-group2>textarea {
	width: 300px;
}
.text-fail {
	margin-left: 5px;
	font-size: 12px;
	position: relative;
	top: -1px;
	color: #eb4e3d;
	vertical-align: middle;
}
.spanMust {
	color: red;
	margin-left: -70px;
}
#miaoshuxinxi {
	margin-left: 58px;
	margin-right: 12px;
	float: left;
}
#jiaoyima {
	margin-left: -4px;
}
#tradeCodes {
	margin-left: 4px;
}
.xiugai_modal_input {
	height: 30px;
	border-radius: 2px;
	background-color: #fff;
	border: solid 1px #d1d2d3;
	padding: 0 10px;
	font-size: 13px;
}
#caseIdentifier {
	background-color: #d1d2d359;
}
</style>
</head>
<body>
<input type="hidden" id="basePath" value="${basePath}" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li class="active">脚本案例</li>
			</ul>
			<a href="${basePath}/testService/upload/ST"><button id="upload"
					style="padding: 4px 16px;">
					<i></i> 上传脚本
				</button></a>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="casebelong">名称</label> <input type="text"
						class="form-control" id="casebelong" placeholder="所属项目">
				</div>
				<div class="form-group">
					<label class="sr-only" for="casename">名称</label> <input type="text"
						class="form-control" id="casename" placeholder="案例名称">
				</div>
				<div class="form-group">
					<label class="sr-only" for="tradecodes">关联交易码</label> <input type="text"
						class="form-control" id="tradecodes" placeholder="关联交易码">
				</div>
				<button type="button" class="btn btn-default" id="btn_query">查询</button>
			</form>
			<div class="table-bordered">
				<table class="table table-striped" id="scriptcase-table">
					<thead>
							<tr>
							 	<th>案例编号</th> 
								<th>案例名称</th>
								<th>所属项目</th>
								<th>案例类型</th>
								<th>关联交易码</th>
								<th>案例描述</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
				</table>
			</div>
		</div>
<!-- 		<div id="breadLine"></div> -->
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="caseItem" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">案例信息</h4>
				</div>
				<div>
					<table class="table">
						<thead>
							<tr>
								<th>组件代码</th>
								<th>组件名称</th>
								<th>组件类型</th>
								<th>组件值</th>
								<th>组件附加项</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>Tanmay</td>
							</tr>
							<tr>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>Tanmay</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div class="modal fade" id="dataPlugin" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">导入外挂数据</h4>
				</div>
				<div class="modal-body">
					<div style="display: block; width: 500px;">
						<p class="load-template">
							请上传一个 Excel/CSV 文件，文件格式符合模板格式，<a href="/template/import.xlsx">下载用例模版</a>
						</p>
						<form>
							<div id="test">
								<label class="control-label">选择文件</label> <input type="file"
									style="left: 85px; top: 50%; position: absolute; z-index: 0; opacity: 0; width: 375px; height: 32px;">
								<input style="width: 300px; height: 32px;"><button
									style="width: 75px; height: 32px; background: #1081de; border: none; color:#fff">浏览</button>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<!-- 修改模态框（Modal） -->
	<div class="modal fade" id="xiugai_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel5" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel5">修改案例</h4>
				</div>
				<div>
					<form id="mainform" action="${basePath}/Summary/updateCase" method=post>
						<input type="hidden" id="caseid" name="caseid" value=""/>
						<div class="form-group2">
							<label>案例名称 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="caseName" name="casename"/>
							<br><span class="spanMust" id="caseNameMust"></span>
						</div>
						<div class="form-group2">
							<label>案例编号 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="caseIdentifier" name="caseidentifier" disabled="disabled"/>
							<br><span class="spanMust" id="caseIdentifierMust"></span>
						</div>
						<div class="form-group2">
							<label>所属项目 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="belong" name="casebelong"/>
							<br><span class="spanMust" id="belongMust"></span>
						</div>
						<div class="form-group2">
							<label id="jiaoyima">关联交易码 </label> <input type="text" class="xiugai_modal_input" id="tradeCodes" name="tradecodes"/>
						</div>
						<div class="form-group2">
							<label id="miaoshuxinxi">描述信息</label>
							<textarea class="form-control product-name" style="height: 70px;" id="caseDesc" maxlength="199" name="casedesc" placeholder="请输入描述信息"></textarea>
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
	<!-- 修改模态框结束 -->
</body>



<!-- jQuery -->
<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
<%-- <script src="${basePath}/common/js/unslider-150203225543/jquery-1.11.1.min.js"></script> --%>
<!-- Bootstrap Core JavaScript -->
<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
<script src="${basePath}/common/js/datatables/jquery.dataTables.min.js"></script>
<script
	src="${basePath}/common/js/datatables/dataTables.bootstrap.min.js"></script>
<script src="${basePath}/common/js/datatables/dataTables.responsive.js"></script>
<script src="${basePath}/common/js/jquery/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${basePath}/common/js/jquery/messages_zh.js"
	type="text/javascript"></script>
<script
	src="${basePath}/common/js/lhgdialog/lhgdialog.js?skin=bootstrap2"></script>
	
<script src="${basePath}/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="${basePath}/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>	
<script src="${basePath}/common/js/jquery/jquery.easyui.min.js"></script>
<script src="${basePath}/common/js/user_defined/constant.js"></script>
<script src="${basePath}/common/js/user_defined/common.js"></script>
<script src="${basePath}/aat/js/testService/scriptcase/scriptcase.js"></script>
	<script type="text/javascript">
		var curuser = '<%=username%>';
	</script>
</html>