<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- ZTREE -->
<link href="${basePath}/common/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li class="active">角色管理</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="rolename">名称</label> <input type="text" class="form-control" id="rolename" placeholder="角色名">
				</div>
				<button type="button" class="btn btn-default" id="btn_query"><i class="fa fa-search fa-fw"></i>查询</button>
				<a class="btn btn-default" id="addRole" data-toggle="modal" data-target="#role"><i class="fa fa-plus-square fa-fw"></i>添加</a>
			</form>
			<div class="table-bordered">
				<table class="table table-striped" id="role-table">
					<thead>
						<tr>
							<th>编号</th>
                            <th>角色名称</th>
							<th>角色描述</th>
                            <th>启用状态</th>
							<th id="operate">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="role" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">新增角色</h4>
				</div>
				<div>
					<form id="mainform" action="${basePath}/systemManagement/roleadd" method=post>
						<input type="hidden" id="roleid" name="roleid" value=""/>
						<div class="form-group">
							<label for="rolerolename">角色名<span class="text-fail">＊</span></label><input id="rolerolename" name="rolename">
							<br><span class="rolerolenameMust" id="rolerolenameMust"></span>
						</div>
						<div class="form-group">
							<label for="roleroledesc">角色描述</label><input id="roleroledesc" name="roledesc">
						</div>
						<div class="form-group">
							<label for="roleisenable">状&nbsp;&nbsp;&nbsp;&nbsp;态<span class="text-fail">＊</span></label>
							<select id="roleisenable" name="isenable"></select>
							<br><span class="roleisenableMust" id="roleisenableMust"></span>
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
	
     <!-- 分配模态框-->
	 <div class="modal fade" id="myModal_rmodule"  role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
              <div class="modal-dialog">
                    <div class="modal-content" style="width:310px;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel1">Modal title</h4>
                        </div>
	          			<div class="modal-body">
	                   			    
					     <div class="row" id="modal-row">
						    <div class="col-lg-12">
					                 <div class="panel panel-info" style="border:1px;font-size:16px;">
				                   		<div class="panel-body"> 
				                   			<input type="hidden" id="message" value="${reCode.msg}"/>
				                   			<div class="row">
				                   				<div class="col-xs-12" style="margin-top: 5px;line-height: 1.8;">                  		
					                   				<ul id=menu class="ztree" style="width:260px; overflow:auto;">
					                   				</ul>
				                   				</div>			
				                   			</div>
				                   		</div>
					               </div>
						    </div>
					     </div>						    
                		</div>
                		<div class="modal-footer">
	                       <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                       <button type="submit" class="btn btn-primary" id="save_role">确定</button>
	                    </div>
               	</div>
                <!-- /.modal-content -->
         </div>
         <!-- /.modal-dialog -->
      </div>
       <!-- 分配结束 -->   
	
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
<script src="${basePath}/common/js/ztree/jquery.ztree.all.min.js" type="text/javascript"></script>
<script src="${basePath}/common/js/user_defined/constant.js"></script>
<script src="${basePath}/common/js/user_defined/common.js"></script>
<script src="${basePath}/aat/js/systemManage/roleManage.js"></script>
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
		#role form {
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
		
		#addRole {
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
    	
    	#num {
			width: 30px !important;
		}
		.diaClass {
			margin-bottom: -2px;
			margin-right: 5px;
		}
		.modal-body {
			width: 320px;
		}
		
		.text-fail {
			vertical-align: middle;
			color: #eb4e3d
		}
		.rolerolenameMust {
			color: red;
			margin-left: -90px;
		}
		.roleisenableMust {
			color: red;
			margin-left: -102px;
		}
		.form-group {
			margin-bottom: 20px;
		}
		#roleisenable {
			height: 26px;
			width: 300px;
			margin-left: -4px;
		}

</style>



</html>