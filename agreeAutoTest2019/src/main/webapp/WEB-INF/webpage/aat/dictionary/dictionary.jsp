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
				<li class="active">字典管理</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="dictcname">名称</label> <input type="text" class="form-control" id="dictcname" placeholder="字典中文名">
				</div>
				<div class="form-group">
					<label class="sr-only" for="dictename">名称</label> <input type="text" class="form-control" id="dictename" placeholder="字典英文名">
				</div>
				<button type="button" class="btn btn-default" id="btn_query"><i class="fa fa-search fa-fw"></i>查询</button>
				<a class="btn btn-default" id="addDict" data-toggle="modal" data-target="#dict"><i class="fa fa-plus-square fa-fw"></i>添加</a>
			</form>
			<div class="table-bordered">
				<table class="table table-striped" id="dict-table">
					<thead>
						<tr>
							<th>编号</th>
							<th>中文名</th>
							<th>英文名</th>
							<th>数据类型</th>
							<th id="operate">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="dict" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">新增字典</h4>
				</div>
				<div>
					<form id="mainform" action="${basePath}/dictionaryManagement/dictadd" method=post>
						<input type="hidden" id="dicttypeid" name="dicttypeid" value=""/>
						<div class="form-group">
							<label for="dictdictcname">字典中文名</label><input id="dictdictcname" name="dictcname">
						</div>
						<div class="form-group">
							<label for="dictdictename">字典英文名</label><input id="dictdictename" name="dictename">
						</div>
						<div class="form-group">
							<label for="dictdatatype">数据类型</label><input id="dictdatatype" name="datatype">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btnYes" class="btn btn-default" data-dismiss="modal">确定</button>
					<button type="button" id="btnClose" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- 取值列表键值对模态框开始 -->
		<div class="modal fade" id="myModal_keyValue" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                   
                    <div class="modal-content">
                       <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel2">Modal title</h4>
                        </div>
              	<div class="modal-body modal-body-left">
               				
	                 <button type="button" class="btn btn-outline btn-info" id="btn_ins" style="margin-left: 0px;margin-top: 0px;"><i class="fa fa-plus fa-fw"></i>添加</button>
	                	|
	                 <button type="button" class="btn btn-outline btn-info" id="btn_del" style="margin-left: 0px;margin-top: 0px;"><i class="fa fa-trash fa-fw"></i>删除</button>
	                 
                    <form id="mainform2" action="${basePath}/dictionaryManagement/dictValupt" method=post>
                    	<input type="hidden" id="message" value="${reCode.msg}"/>
                    	
                    	  <table width="100%" class="table table-striped table-bordered table-hover" id="table-dictValue">
                                <thead>
                                	<tr>
                                	    <th class="center" id="num">
                                        	<input type="checkbox" name="cb-check-all">
        								</th>
                                        <th>名称</th>
                                        <th>取值</th>
                                        <th>说明</th>
                                    </tr>
                             	</thead>
                             </table>
                             <input type="hidden" name="table_data" id="hidtab"/>
                             <input type="hidden" id="dtid" name="dicttypeid"/>
                    </form>
                </div>
                   <div class="modal-footer">
                       <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                       <button type="button" class="btn btn-primary" id="confirm_info">确定</button>
                    </div>
               </div>
                <!-- /.modal-content -->
         </div>
         <!-- /.modal-dialog -->
      </div>
       <!-- 取值列表键值对模态框结束 -->
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
<script src="${basePath}/aat/js/dictManage/dictManage.js"></script>
<script src="${basePath}/aat/js/dictManage/dictValManage.js"></script>
<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- DataTables CSS -->
<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="${basePath}/common/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- DataTables Responsive CSS -->
<link href="${basePath}/common/css/datatables/dataTables.responsive.css" rel="stylesheet">
<!-- modal fade datatable CSS-->	
<link href="${basePath}/system/css/dictButton.css" rel="stylesheet">
<!--backstage body CSS-->
<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
<style type="text/css">
		#dict form {
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
		
		#addDict {
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

</style>



</html>