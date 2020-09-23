<%@page import="com.agree.system.entity.SystemUser"%>
<%@page import="com.agree.framework.constant.ApplicationKeyConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	SystemUser user = (SystemUser)session.getAttribute(ApplicationKeyConst.USER_INFO);
	String username = user.getUsername();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>新增计划任务</title>
	<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<!-- DataTables CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${basePath}/common/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- DataTables Responsive CSS -->
	<link href="${basePath}/common/css/datatables/dataTables.responsive.css" rel="stylesheet">
	<link href="${basePath}/system/css/backstage.css" rel="stylesheet">

	<style type="text/css">
	.page-step-bar {
		text-align: center;
	}
	.page-step-bar ul {
	    display: inline-block;
	}
	.page-step-bar ul li + li {
	    margin-left: 80px;
	    position: relative;
	}
	.page-step-bar ul li {
	    display: inline-block;
	}
	.page-step-bar ul li + li::before {
	    display: block;
	    content: "\20";
	    width: 50px;
	    height: 0;
	    border-top: 1px solid #ccc;
	    position: absolute;
	    top: 10px;
	    margin-left: -65px;
	}
	.page-step-bar ul li span {
	    display: inline-block;
	    width: 22px;
	    height: 22px;
	    line-height: 22px;
	    margin-right: 12px;
	    border-radius: 50%;
	    color: #fff;
	    background-color: #ccc;
	    text-align: center;
	}
	.page-step-bar ul li.active span {
	    background-color: #1081de;
	}
	.page-step-bar ul li.finish span {
	    color: #1081de;
	    background-color: #1081de;
	    position: relative;
	}
	.page-step-bar ul li.finish span::before {
	    width: 100%;
	    height: 100%;
	    text-align: center;
	    position: absolute;
	    left: 0;
	    font-family: iconfont !important;
	    content: '\e69a';
	    color: #fff;
	}
	.btn:hover {
		 color:#1081de;
		 background-color:#fff;
		 border-color: #1081de;
		 outline:0
	}
	.btn:focus {
		 color:#fff;
		 background-color:#1081de;
		 outline:0
	}
	.btn-after, .btn-before {
		 color:#fff;
		 background-color:#1081de;
		 border-color:#1081de
	}
	.webuploader-pick {
		background: #fff;
	}
	.modal-dialog {
	    width: 300px;
	    margin: 30px auto;
	}
	body {
		overflow-x: hidden;
	}
	.uploader-text {
		font-family: STXihei;
		font-size: 15px;
		color: red;
		letter-spacing: 1px;
	}
	.table tbody>tr>td {
		vertical-align: middle;
   		padding: 5px 0px 5px 18px;
  	}
  	
	#addInfosForm>div>label {
		width: 114px;
		font-size: 14px;
		color: #333;
		font-weight: 400;
		padding: 0;
		height: 32px;
		line-height: 32px;
	}
	#desclabeldiv>label {
		width: 122px;
		font-size: 14px;
		color: #333;
		font-weight: 400;
		padding: 0;
		height: 32px;
		line-height: 32px;
	}
	
	#addInfosForm>div>input {
		width: 298px;
		height: 32px;
		border-radius: 2px;
		background-color: #fff;
		border: solid 1px #d1d2d3;
		padding: 0 10px;
		font-size: 13px;
	}
	#desclabeldivtop {
		display: -moz-box; /*兼容Firefox*/
		display: -webkit-box; /*兼容FSafari、Chrome*/
		/*上下居中*/
		/*-moz-box-align: center;*/ /*兼容Firefox*/
		/*-webkit-box-align: center;*/ /*兼容FSafari、Chrome */
		/*左右居中*/
		-moz-box-pack: center; /*兼容Firefox*/
		-webkit-box-pack: center; /*兼容FSafari、Chrome */
		position: relative;
	}
	.spanMust {
		color: red;
		margin-left: -45px;
	}
	#addInfosForm .text-fail {
		font-size: 12px;
		position: relative;
		top: -1px;
		color: #eb4e3d;
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
	.form-control {
		width: 298px;
		height: 32px;
		border-radius: 2px;
		border: solid 1px #d1d2d3;
		padding: 0 10px;
		font-size: 14px;
		background-color: #fff;
		margin-left: 3px;
	}
	.descinfosTxtarea {
		width: 298px;
		height: 64px;
		border-radius: 2px;
		border: solid 1px #d1d2d3;
		padding: 0 10px;
		font-size: 13px;
		background-color: #fff;
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
		min-width: 112px;
		text-align: center;
		float: left;
		margin: 2px;
		font-weight: 500;
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
	<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.easyui.min.js"></script>
	<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
	<script src="${basePath}/common/js/datatables/jquery.dataTables.min.js"></script>
    <script src="${basePath}/common/js/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${basePath}/common/js/datatables/dataTables.responsive.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${basePath}/common/js/jquery/messages_zh.js" type="text/javascript"></script>
	<script src="${basePath}/common/js/lhgdialog/lhgdialog.js?skin=bootstrap2"></script>
	<script src="${basePath}/common/js/user_defined/constant.js"></script>
	<script src="${basePath}/common/js/user_defined/common.js"></script>
			<input type="hidden" id="basePath" value="${basePath}" />
			<input type="hidden" id="planid" />
			<div class="page-main ">
				<div class="page-step-bar row" style="border-bottom:1px solid #d1d2d3;margin: 20px;">
			        <ul>
			            <li class="onetep active"><span>1</span>添加测试计划</li>
			            <li class="twotep "><span>2</span>添加执行机</li>
			            <li class="threetep "><span>3</span>案例维护</li>
			        </ul>
	    		</div>
	    		
	    		<div id="testPlan" style="display: none;text-align:center; margin: 20px;">
					<form id="addInfosForm" action="${basePath}/testplan/addplan" method=post>
						<input type="hidden" id="planid2" name="planid" value=""/>
						<div style="margin-bottom: 10px;">
							<label>任务名称 <span class="text-fail">＊</span></label> <input type="text" id="planname2" name="planname"/>
							<br>
							<span class="spanMust" id="plannameMust"></span>
						</div>
						<div id="desclabeldivtop">
							<div id="desclabeldiv"><label>任务描述 <span class="text-fail">＊</span></label></div>
							<textarea class="descinfosTxtarea" id="descinfos" maxlength="199" name="descinfos" placeholder="请输入描述信息"></textarea>
							<br>
							<span class="spanMust" id="belongMust"></span>
							
						</div>
					</form>
				</div>
				
				<div id="executer" class="modal-body" style="display: none;height:155px;width:380px;">
						<div class="RadioStyle">
							<div class="Block PaddingL" id="execlient-checkboxes">
								
							</div>
					   </div>
<!-- 					<div class="form-group"> -->
<!-- 					    <label for="execlient" class="col-sm-4 control-label">执行机</label> -->
<!-- 					    	<div class="col-sm-8"> -->
<!-- 						   	 <select id="execlient" class="form-control" style="width:240px;overflow:auto;" name="execlient">      -->
<!-- 								<option data-tokens="ketchup mustard">请选择</option></select> -->
<!-- 					    	</div> -->
<!-- 					</div> -->
	    		</div>
	    		
	    		<!--案例维护表格开始 -->
		      	<div class="table-bordered" style="display: none;" id="caseTable">               	
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
		     	<!-- 案例维护表格结束 -->
    		</div>
			
			<div class="foot-button row" style="border-top:1px solid #d1d2d3;margin: 20px;">
        		<div class="col-lg-4 onebut" style="margin-top: 10px;margin-left:85%">
        			<button class="btn btn-before" id="bt0" onclick="javascript:history.go(-1)">返回</button>
        			<button class="btn btn-after" id="bt1" onclick="" type="submit">下一步</button>
		        </div>
		        <div class="col-lg-4 twobut" style="margin-top: 10px;display: none;margin-left:85%" >
        			<button class="btn btn-after" id="bt2" onclick="" type="button">下一步</button>
		        </div>
		        <div class="col-lg-4 threebut" style="margin-top: 10px;display: none;margin-left:85%" >
        			<button class="btn btn-after" id="bt3" onclick="" type="button" style="width:68px;">完成</button>
		        </div>
    		</div>   		
		</div>
	
	<script>
		var curuser = '<%=username%>';
		$(function(){
			document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
			$('#testPlan').css("display","block");
			$('#executer').css("display","none");
		});
		
		var terLength=0;
		$('#bt1').click(function(){
			$("#plannameMust").html("");
			$("#belongMust").html("");
			if ($("#planname2").val() != null && $("#planname2").val() != ""){
				if ($("#descinfos").val() != null && $("#descinfos").val() != ""){
					$("#addInfosForm").form('submit', {
				        success:function(data){
// 				    		$.dialog.tips("添加成功");
							$('.onebut').css("display","none");
					 		$('.twobut').css("display","block");
					 		$(".onetep").removeClass("active");
					 		$(".twotep").addClass("active");
					 		$('#testPlan').css("display","none");
							$('#executer').css("display","block");
							
							common.ajax({
								url : $("#basePath").val() + "/testplan/getLatestPlanid",
								type : "GET",
								data: {"curuser":curuser},
								async: false,
								success : function(data) {
									$("#planid").val(data);
								}
							});
							
							common.ajax({
								url : $("#basePath").val() + "/executeclient/ecListstatus1",
								type : "GET",
								async: false,
								dataType : "json",
								success : function(redata) {
									if (redata.data.length == 0) {
										$.dialog.tips("请添加执行机！");
										return;
									}
									$("#execlient-checkboxes").empty();
									for (var i = 0; i < redata.data.length; i++) {
										terLength = redata.data.length;
										var terminalid = redata.data[i].terminalid;
										var name = redata.data[i].name;
 										//$("#execlient").append("<option value=" + terminalid + ">" + name + "</option>");
										$("#execlient-checkboxes").append(
											"<input type='checkbox' name='" + terminalid + "' id='execli" + i + "' value='" + terminalid + "'/>" + 
											"<label for='execli" + i + "'>" + name + "</label>"
										);
									}
								}
							});
				        },
				        error: function () {
				        	$.dialog.tips("添加失败");
					    }
					});
				}else {
					$("#belongMust").html("任务描述是必填项！");
				}
			}else {
				$("#plannameMust").html("任务名称是必填项！");
			}
	 	});
		
		$('#bt2').click(function(){
			var arrTer = [];
			for (var i = 0; i < terLength; i++) {
				if ($("#execli"+i).is(":checked")) {
					arrTer.push($("#execli"+i).val());
				}
			}
			if (arrTer.length <= 0) {
				$.dialog.tips("未选择执行机");
				return;
			}
			// 提交数据
			var formData = {};
// 			formData.executeclientid = $("#execlient").val();
// 			formData.executeclientid = JSON.stringify(arrTer);
			formData.executeclientid = arrTer.toString();
			formData.planid = $("#planid").val();
			common.ajax({
				url : $("#basePath").val() + "/testplan/updatePlanExecuteclient",
				type : "POST",
				async: false,
				data: formData,
				success : function(data) {
// 					$.dialog.tips("添加成功");
					$('.twobut').css("display","none");
			 		$('.threebut').css("display","block");
			 		$(".twotep").removeClass("active");
			 		$(".threetep").addClass("active");
			 		$('#executer').css("display","none");
					$('#caseTable').css("display","block");
					
					document.body.style.zoom = window.screen.width / 1536; 
					var $table3 = $('#caseInfo');
					
					if ($table3.html() != "") {
						$table3.dataTable().fnDestroy();
					}
					var realplanid=$("#planid").val();
					var planid='0';
					var type='RP';//不支持脚本案例
					var _table3 = $table3.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
						ajax : function(data, callback, settings) {
							// 封装请求参数
							var param = caseInfoManage.getQueryCondition(data);
							param.casetype=type;
							$.ajax({
					            type: "GET",
					            url: $('#basePath').val()+"/testplan/getCaseList/"+planid,
					            cache : false,	//禁用缓存
					            data: param,	//传入已封装的参数
					            dataType: "json",
					            success: function(result) {
					            	$("#chakanmyModal").modal('show');
					            		//异常判断与处理
					            		if (result.errorCode) {
					            			return;
										}
					            		//封装返回数据，这里仅演示了修改属性名
					            		var returnData = {};
						            	returnData.draw = result.draw;//这里直接自行返回了draw计数器,应该由后台返回
						            	returnData.recordsTotal = result.total;
						            	returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
						            	returnData.data = result.pageData;
						            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
						            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
						            	callback(returnData);
					            },
					            error: function(XMLHttpRequest, textStatus, errorThrown) {
					               
					            }
					        });
						},
						columns:[
							{
								className : "ellipsis",
								data : "caseid",
								orderable : false
							},
							{
								className : "ellipsis", // 文字过长时用省略号显示，CSS实现
								data : "casename",
								orderable : false
							},
							{
								className : "ellipsis",
								data : "casetype",
								orderable : false,
								render : function(data,type, row, meta) {
									if(data=='RP'){
										return '录制';
									}else if(data=='ST'){
										return '脚本';
									}
								}
							},
							{
								className : "ellipsis",
								data : "casedesc",
								orderable : false
							
							},
							{
								className : "ellipsis", // 文字过长时用省略号显示，CSS实现
								data : "userid",
								orderable : false
								
							},
							{
								className : "ellipsis", // 文字过长时用省略号显示，CSS实现
								data : "createdate",
								orderable : false
							
							},
							{
								className : "ellipsis",
								data : "caseCao",
								orderable : false,
								render : function(data,
										type, row, meta) {
									return '<div class="dropdown">'
											+'<a href="javascript:void(0)" onclick="addplancase(\''
											+ realplanid
											+ '\',\''
											+ row.caseid
											+ '\')">添加</a>'
											+ '</div>';
								}
							} ],
					        "columnDefs":[{
					        	"targets": [  ],
					            "visible": false  
					        }],
					        "createdRow": function ( row, data, index ) {
					        	
					        },
						"drawCallback": function( settings ) {
				        	//渲染完毕后的回调
				        }
					})).api();
				}
			});
	 	});
		
		$('#bt3').click(function(){
			window.location.href = $("#basePath").val() + "/testService/testPlan";
	 	});
		
		function addplancase(planid,caseid) {
	        common.ajax({
	            url: $("#basePath").val() + "/testplan/addplancase",
	            type: "POST",
	            data: {
	            	"planid":planid,
	                "caseid": caseid
	            },
	            success: function (data) {
	                $.dialog.tips("添加成功");
	            },
	            error: function () {
	            	$.dialog.tips("添加失败");
	            }
	        });
		}
		
		var caseInfoManage = {
			currentItem : null,
			fuzzySearch : true,//开启模糊查询
			getQueryCondition : function(data) {
				var param = {};
				//组装分页参数
				param.startIndex = data.start;
				param.pageSize = data.length;
				param.draw = data.draw;
				param.page = data.start / 10 + 1;
				return param;
			}
		};
    </script>
</body>

</html>