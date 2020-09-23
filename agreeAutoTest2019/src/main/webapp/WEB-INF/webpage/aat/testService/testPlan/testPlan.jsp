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
	<link href="${basePath}/aat/css/scriptInfos/scriptInfos.css" rel="stylesheet">

<style type="text/css">
.table tbody>tr>td {
			vertical-align: middle;
    		padding: 5px 0px 5px 18px;
    	}
/* .form-inline {
	float: left
} */

#execute {
	margin-right: 15px;
	float: right;
	background: #1081de;
	color: #fff;
}

.control-label {
	text-align: right;
	font-size: 14px;
	color: #333;
	font-weight: 400;
	padding: 0;
	height: 32px;
	line-height: 32px;
	width: 52px;
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
	width: 100px;
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
    #btn_testplan {
    margin-left: 10px;
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	.but{
    margin-left: 10px;
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	#btn_testplan:hover {
    		color: #1081de;
    		background-color: #fff;
    		border-color: #1081de;
    	}	
    	
    	#btn_terminal {
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	#btn_terminal:hover {
    		color: #1081de;
    		background-color: #fff;
    		border-color: #1081de;
    	}
    	
    		#btn_caseManage {
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	#btn_caseManage:hover {
    		color: #1081de;
    		background-color: #fff;
    		border-color: #1081de;
    	}
    	label {
	width: 114px;
	text-align: left;
	font-size: 14px;
	color: #333;
	font-weight: 400;
	padding: 0;
	height: 32px;
	line-height: 32px;
}
.form-group {
     margin-bottom: 2px;
    	}
.table-bordered {
	margin-top: 20px;
}

.RadioStyle input {
	display: none
}
.RadioStyle label {
	border: 1px solid #CCC;
	color: #666;
	cursor: pointer;
	padding: 2px 10px 2px 5px;
	line-height: 28px;
	min-width: 80px;
	text-align: center;
	float: left;
	margin: 2px;
	border-radius: 4px
}
.RadioStyle input:checked+label {
	background: url(${basePath}/aat/image/ico_checkon.svg) no-repeat right bottom;
	border: 1px solid #00a4ff;
	cursor: pointer;
	background-size: 21px 21px;
	color: #00a4ff
}
.RadioStyle input:disabled+label {
	opacity: 0.7;
}
</style>

</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="planid" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li class="active">提测计划</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline">
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="planname" placeholder="计划名称">
				</div>
				<!-- <div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="案例名称">
				</div>
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="AB版本">
				</div>
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="案例类型">
				</div> -->
				<button type="button" class="btn btn-default" id="btn_query">查询</button>
				
				<button  type="button" class="btn btn-default" id="btn_testplan">添加测试计划</button>
				<button  type="button" class="btn btn-default" id="btn_terminal">添加执行机</button>
				<button  type="button" class="btn btn-default" id="btn_caseManage">案例维护</button>

			</form>
			<div class="table-bordered">
				<table class="table table-striped" id="table-testplan">
					<thead>
						<tr>
							<th></th>
							<th>提测计划编号</th>
							<th>提测名称</th>
							<th>提测描述</th>
							<th>任务状态</th>
							<th>案例总数</th>
							<th>执行机</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
		<!-- 查看模态框开始 -->
      <div class="modal fade" id="chakanmyModal"  role="dialog"  tabindex="-1" aria-labelledby="myModalLabel3" aria-hidden="true">
             <div class="modal-dialog modal-lg">                   
                   <div class="modal-content" >
                      <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                           <h4 class="modal-title" id="myModalLabel3">案例列表</h4>
                       </div>
		               <div class="modal-body" style="width: 100%; padding-top: 0;">
							<div class="table-bordered">               	
			                    <table class="table table-striped table-hover file-table clearfix" id="caseInfo">
			                    	<thead>
			                           	<tr>
			                        		<th>案例编号</th>
			                        		<th>案例名称</th>
			                        		<th>案例类型</th>
			                        		<th>案例描述</th>
			                        		<th>创建人</th>
			                        		<th>创建时间</th>
			                        		<th>操作</th> 
			                              </tr>
			                         </thead>
			                    </table>        
			                </div>
		                </div>
               		</div>
                <!-- /.modal-content -->
        	</div>
     </div>
     <!-- 查看模态框结束 -->
     
     <!-- 添加 -->
      <div class="modal fade" id="addtestplan"  role="dialog"  tabindex="-1" aria-labelledby="myModalLabel4" data-backdrop="static" data-keyboard="false" aria-hidden="true">
             <div class="modal-dialog modal-lg">                   
                 			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加计划任务</h4>
				</div>
<!-- 				<div class="modal-body"> -->
				<div>
					<form id="addInfosForm" action="${basePath}/testplan/addplan" method=post>
						<input type="hidden" id="planid2" name="planid" value=""/>
						<div>
							<label>任务名称 <span class="text-fail">＊</span></label> <input type="text" id="planname2" name="planname"/>
							<span class="spanMust" id="plannameMust"></span>
						</div>
						<div>
							<label>任务描述 <span class="text-fail">＊</span></label> <input type="text" id="descinfos" name="descinfos"/>
							<span class="spanMust" id="belongMust"></span>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnClose" class="btn btn-default btn-primary" data-dismiss="modal">取消</button>
					<button type="submit" id="btnYes" class="btn btn-default btn-primary">确定</button>
				</div>
			</div>
                <!-- /.modal-content -->
        	</div>
     </div>
     <!-- 添加结束 -->
     <!-- 选择执行机 -->
  <div class="modal fade" id="termanler" role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true">
             <div class="modal-dialog" >                   
                   <div class="modal-content" >
	                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding:6px;">
	                         &times;
	                   </button>
	                   <div class="modal-header">
	                        <h4 class="modal-title" id="myModalLabel4">选择执行机</h4>
	                   </div>
		               <div class="modal-body" style="height:160px;width: 520px;">
			               <div class="RadioStyle">
								<div class="Block PaddingL" id="execlient-checkboxes">
									
								</div>
						   </div>
<!-- 			                <div class="form-group"> -->
<!-- 							    <label for="execlient" class="col-sm-4 control-label">执行机</label> -->
<!-- 						    	<div class="col-sm-8"> -->
<!-- 							   	 	<select id="execlient" class="form-control"  style="width:240px;overflow:auto;" name="execlient">      -->
<!-- 									<option data-tokens="ketchup mustard">请选择</option></select> -->
<!-- 						    	</div> -->
<!-- 							</div> -->
		                </div>
	       		      	<div class="modal-footer">
	                       <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                       <button type="button" id="addexecute" class="btn btn-primary">确定  </button>
	                    </div>
	                </div>
               </div>
     </div>

	<!-- 选择执行机 -->
</body>

<!-- jQuery -->
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
	<script src="${basePath}/aat/js/testService/testplan/testplan.js"></script>


<script type="text/javascript">
	function execute() {
		alert();
		document.form.action = "${basePath}/testService/casesExecute";
		document.submit();
	}
	var curuser = '<%=username%>';
</script>
</html>