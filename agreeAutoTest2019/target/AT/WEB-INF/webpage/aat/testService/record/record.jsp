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
	
	<style type="text/css">
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
    	#btn_export {
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	#btn_export:hover {
    		color: #1081de;
    		background-color: #fff;
    		border-color: #1081de;
    	}
    	#upload {
    		color: #fff;
    		background-color: #1081de;
    		border-color: #fff;
    	}
    	.modal-scdialog3 {
		    width: 95%;
		    height: 300px;
		    margin: 30px auto;
		}
		.modal-content {
			height: auto;
		}
		.modal-body2 {
			padding: 0px 15px 15px 15px; 
		}
		.modal-body3 {
			padding: 0px 15px 30px 15px; 
		}
		.modal-footer {
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
		.popover {
			font-family: Song;
			line-height: 1.5;
			font-weight: 0;
			letter-spacing: 1px;
			font-family: NSimSun;
			font-size: 11px;
		}
		.popover-title {
			width: 180px;	
			height: 29px;
			font-size: 13px;
/* 			font-family: Microsoft Yahei; */
		}
		.popover-content {
			white-space: pre;
			width: 180px;
			height: 180px;
		}
		#edit_input::-webkit-input-placeholder {
			font-size: 12px;
		}
		#edit_input::-moz-placeholder {
            font-size: 12px;
        }
　　　　#edit_input::-ms-input-placeholder {
            font-size: 12px;
        }
        #row-form-group>.panel {
        	margin-bottom: 0px;
        }
        #uploadfiles>.modal-dialog>.modal-content>button.close {
        	padding: 7px;
        }
        #play-back>.modal-dialog>.modal-content>button.close {
        	padding: 7px;
        }
		
		.contain {
		      background-color: #D1CEBC;
		      height: 100px;
		      width: 300px;
		  }
		  
		  .menu {
/* 		      width: 150px; */
		      background-color: white;
 		      visibility: hidden;
/* 		      display: none; */
		      position: absolute;
		      box-shadow: 0px 0px 10px #D1CEBC;
		  }
		  
		  .menu-item {
		      background-color: #fff;
		      margin: 0;
		  }
		  
		  .menu-item-btn {
		      width: 60px;
		      margin: 2px;
		      border: 0;
		      text-align: left;
		      padding-left: 15px;
		      padding-top: 5px;
		      padding-bottom: 5px;
		      background-color: #fff;
		      color: #000;
		      font-size: 13px;
		  }
		  
		  .menu-item-btn:hover {
		      background-color: #D1CEBC;
		  }
		  
	        #charu_modal form {
				padding: 30px;
				text-align: center;
			}
			.spanMust2 {
				color: red;
				margin-left: -50px;
			}
			.fa-plus-circle:before {
				font-size: 21px;
			}
/* 		.table-bordered>tbody>tr>td, .table-bordered>tbody>tr>th, .table-bordered>tfoot>tr>td, .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>thead>tr>th{ */
/* 			border: 1px solid #ddd; */
/* 		} */
/* .breadcrumb {

    float: left;
    height: 10px;

} */
	</style>
</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="executeCaseList" value='${executeCaseList}' />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li class="active">录制回放</li>
			</ul>
			<a href="${basePath}/testService/upload/RP"><button id="upload" style="padding: 4px 16px;"><i></i> 上传脚本</button></a>
		</div>
		<div id="breadLine"></div>
		<div class="mainbody">
			<form class="form-inline" role="form" id="mainForm" method=post>
				<div class="form-group">
					<label class="sr-only" for="casebelong">所属项目</label> <input type="text"
						class="form-control" id="casebelong" placeholder="所属项目">
				</div>
				<div class="form-group">
					<label class="sr-only" for="casename">案例名称</label> <input type="text"
						class="form-control" id="casename" placeholder="案例名称">
				</div>
				<div class="form-group">
					<label class="sr-only" for="tradecodes">关联交易码</label> <input type="text"
						class="form-control" id="tradecodes" placeholder="关联交易码">
				</div>
				<input type="hidden" id="exportHidden" name="exportHidden">
				<button type="button" class="btn btn-default" id="btn_query"><i class="fa fa-search fa-fw"></i>查询</button>
				<button type="button" class="btn btn-default" id="btn_export"><i class="fa fa-sign-out fa-fw"></i>案例导出</button>
			</form>
			<div class="table-bordered">
				<table class="table table-striped recordCaseTable" id="record-py">
					<thead>
						<tr>
							<th class="center" width="1%">
                               <input type="checkbox" name="cb-check-all">
							</th>
						 	<th>案例编号</th> 
							<th>案例名称</th>
							<th>所属项目</th>
							<th>AB版本</th>
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
	
	<!-- 查看模态框开始 -->
      <div class="modal fade" id="myModal_sub3"  role="dialog" aria-labelledby="myModalLabel3" data-backdrop="static" data-keyboard="false" aria-hidden="true">
             <div class="modal-scdialog3">                   
                   <div class="modal-content" >
                      <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                           <h4 class="modal-title" id="myModalLabel3">案例信息</h4>
                       </div>
	               <div class="modal-body2">
						<input type="hidden" id="jnamea3" name="jname3" value=""/>
						<div class="table-bordered">               	
		                    <table class="table table-striped table-hover file-table clearfix" id="table-caseInfo">
		                    	<thead>
		                           	<tr>
		                        		<th>编号</th>
		                        		<th>组件代码</th>
		                        		<th>别名</th>
		                        		<th>组件值</th>
<!-- 		                        		<th>组件类型</th> -->
<!-- 		                        		<th>事件名</th> -->
		                        		<th>是否截图</th>
		                        		<th>取值赋值</th>
		                        		<th>操作</th> 
		                              </tr>
		                         </thead>
		                    </table>        
		                </div>
	                </div>
               </div>
                <!-- /.modal-content -->
        		</div>
        <!-- /.modal-dialog -->
        
		<!-- 右键菜单 -->
		<input type="hidden" id="itemidhover" name="itemidhover" value=""/>
	     <div id="menu-right" class="menu">
	         <div class="menu-item">
	             <button class="menu-item-btn" id="btnUp">上移</button>
	         </div>
	         <div class="menu-item">
	             <button class="menu-item-btn" id="btnDown">下移</button>
	         </div>
	         <div class="menu-item">
	             <button class="menu-item-btn" id="btnInsert">插入</button>
	         </div>
	     </div>
     </div>
     <!-- 查看模态框结束 -->
     
     <!-- 插入模态框（Modal） -->
	<div class="modal fade" id="charu_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel6" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel6">新增组件</h4>
				</div>
				<div>
					<form id="mainform_ins" action="${basePath}/playback/insertItem" method=post>
						<input type="hidden" id="caseid_ins" name="caseid" value=""/>
						<input type="hidden" id="itemindex_ins" name="itemindex" value=""/>
						<input type="hidden" id="itemid" name="itemid" value=""/>
						<input type="hidden" id="caseid_upt" value=""/>
						<div class="form-group2">
							<label>数据项代码 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="itemcode" name="itemcode"/>
							<br><span class="spanMust2" id="itemcodeMust"></span>
						</div>
						<div class="form-group2">
							<label>数据项值 </label> <input type="text" class="xiugai_modal_input" id="itemvalue" name="itemvalue"/>
						</div>
						<div class="form-group2">
							<label>交易码 </label> <input type="text" class="xiugai_modal_input" id="tradecode" name="tradecode"/>
						</div>
						<div class="form-group2">
							<label>页面代码 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="pagecode" name="pagecode"/>
							<br><span class="spanMust2" id="pagecodeMust"></span>
						</div>
						<div class="form-group2">
							<label>数据项类型 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="itemtype" name="itemtype"/>
							<br><span class="spanMust2" id="itemtypeMust"></span>
						</div>
						<div class="form-group2">
							<label>数据项名称 <span class="text-fail">＊</span></label> <input type="text" class="xiugai_modal_input" id="itemname" name="itemname"/>
							<br><span class="spanMust2" id="itemnameMust"></span>
						</div>
						<div class="form-group2">
							<label>事件名 </label> <input type="text" class="xiugai_modal_input" id="itemevent" name="itemevent"/>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btnYes_ins" class="btn btn-default" onclick="btnYes_ins()">确定</button>
					<button type="button" id="btnClose_ins" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 插入模态框结束 -->
     
     	<!-- 回放模态框开始   style="height:400px;width:600px;"-->
      <div class="modal fade" id="play-back"   role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true">
             <div class="modal-dialog" >                   
                   <div class="modal-content"" >
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                      <div class="modal-header">
                           <h4 class="modal-title" id="myModalLabel4">回放执行</h4>
                       </div>
	               <div class="modal-body">
	               
	               <div class="form-group">
					    <label for="execlient" class="col-sm-4 control-label">执行机</label>
					    	<div class="col-sm-8">
						   	 <select id="execlient" class="form-control"  style="width:240px;overflow:auto;" name="execlient">     
								<option data-tokens="ketchup mustard">请选择</option></select>
					    	</div>
					</div>

	              <!--  <label for="execlient" class="col-sm-2 control-label">执行机</label>
						<select id="execlient" class="form-control" name="execlient">     
						<option data-tokens="ketchup mustard">请选择</option></select> -->
						<!-- <div class="table-bordered">               	
		                   	<label class="sr-only" for="execlient">执行机：</label> 
		                   	<select id="execlient" class="form-control" style="width:200px;overflow:auto;" name="execlient"></select>     
		                </div> -->
		                 </div>
        		      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" id="executecase" class="btn btn-primary">执行  </button>
                      
                    </div>
	                </div>
	               
               </div>
          
     </div>
     <!-- 回放模态框结束 -->
	
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
	
	
	<!-- 上传模态框开始   style="height:400px;width:600px;"-->
      <div class="modal fade" id="uploadfiles"  role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true">
             <div class="modal-dialog" >                   
                   <div class="modal-content"" >
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                      <div class="modal-header">
                           <h4 class="modal-title" id="myModalLabel4">数据外挂</h4>
                      </div>
	               <div class="modal-body">
					  	<div class="container-fluid" style="padding-right: 0px;">
							<form id="uploadform" action="${basePath}/testService/uploadDataFile" method="post" enctype="multipart/form-data">
								<input type="hidden" id="dataPlugin-caseid" name="dataPlugin-caseid" value=""/>
								<div id="row-form-group" class="row form-group" style="margin-bottom: 0px;">
									<div class="panel panel-primary">
										<div class="panel-heading" align="center">
											<label style="text-align: center; font-size: 18px;">文 件 上 传（xlsx）</label>
										</div>
										<div class="panel-body">
											<div class="col-sm-12">
												<input name="sealPfxFile" id="sealPfxFile" multiple type="file" accept=".xlsx" data-show-caption="true" />
											</div>
										</div>
									</div>
								</div>
								
								<br>
								<div id="fileDown" style="text-align: center;margin-bottom:5px;">
									<a style="font-size: 20px;" href="javascript:void(0);" onclick="downloadFile()">
										<i class="fa fa-download" aria-hidden="true"></i>下载文件
									</a>
								</div>
								
								<span id="batchCaseMust" style="color: red;"></span>

							    <div class="modal-footer" style="text-align: center;margin-top: 7px;">
			                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                        <button type="submit" id="uploadfile" class="btn btn-primary">开始上传 </button>
		                        </div>
							</form>
						</div>
		            </div>
	               </div>
	               
               </div>
          
     </div>
     <!-- 上传模态框结束 -->
     <!-- 预期模态框开始 -->
      <div class="modal fade" id="itemexpect"  role="dialog" aria-labelledby="myModalLabel3" data-backdrop="static" data-keyboard="false" aria-hidden="true">
             <div class="modal-dialog modal-lg" style="width: 95%;">                   
                   <div class="modal-content" >
                      <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                           <h4 class="modal-title" id="expectmyModalLabel3">预期结果信息</h4>
                       </div>
	               <div class="modal-body3">
						<div style="margin-left: 98%;margin-top: 10px;">
							<a href="javascript:void(0)" id="ins"><i class="fa fa-plus-circle fa-fw"></i></a>
						</div>
						<div class="table-bordered" style="margin-top: 10px;">               	
		                    <table class="table table-striped table-hover file-table clearfix" id="expectInfo">
		                    	<thead>
		                           	<tr>
		                        		<th>编号</th>
		                        		<th>预期结果名称</th>
		                        		<th>预期结果函数</th>
		                        		<th>预期结果描述</th>
		                        		<th>表达式</th>
		                        		<th>操作</th>
		                              </tr>
		                         </thead>
		                    </table>        
		                </div>
	                </div>
               </div>
                <!-- /.modal-content -->
        		</div>
        <!-- /.modal-dialog -->
     </div>
     <!-- 预期模态框结束 -->
     
</body>
	<!-- jQuery -->
    <script src="${basePath}/common/js/jquery/jquery.min.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.easyui.min.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.form.js"></script>
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
	<script src="${basePath}/aat/js/testService/record/record.js"></script>
	
	<script type="text/javascript">
		var curuser = '<%=username%>';

		//下载文件
		function downloadFile() {
			window.location.href = $('#basePath').val() + "playback/downloadFile/" + $("#dataPlugin-caseid").val();
		}
	</script>
</html>