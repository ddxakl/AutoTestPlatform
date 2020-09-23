var node1;
var userId0;
$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	
	var $table = $('#user-table');
	
	//加载下拉参数 
	var item = common.getDictJson(25);		
	common.boption($("#usersex"),item);
	
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = userManage.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/systemManagement/userList",
	            cache : false,	//禁用缓存
	            data: param,	//传入已封装的参数
	            dataType: "json",
	            success: function(result) {
	            	
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
		columns: [
	            {
	            	data: "userid",
	            	width : "0px"
	            },
	            {
	            	className : "ellipsis",	
	            	data: "account",
	            	orderable:false
	            },
	            {
	            	className : "ellipsis",	
	            	data: "username",
	            	orderable:false
	            },
				{
					className : "ellipsis",
					data: "belongto",
					orderable : false
				},
	            {
	            	className : "ellipsis",
					data : "sex",
					orderable:false,
					render: function (data, type, row, meta) {
                		if (data==1) {
							return "<i class='fa fa-female fa-fw'></i>";
						}else {
							return "<i class='fa fa-male fa-fw'></i>";
						}
	                }
				},
				 {
					className : "ellipsis",
					data: "email",
					orderable : false
				},		
				 {
					className : "ellipsis",
					data: "phonenumber",
					orderable : false
				},
				{
	                className: "ellipsis",
	                data: "caseCao",
	                orderable: false,
	                render: function (data, type, row, meta) {
                		return '<div class="dropdown">' + 
	                    '<a href="javascript:void(0)" onclick="fenpei(\'' + row.userid + '\')">分配</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" id="xg'+row.userid+'" data-toggle="modal" data-target="#user" onclick="xiugai(\'' + row.userid + '\')">修改</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.userid + '\')">删除</a>' + 
	                    '</div>';
	                }
	            }
	        ],
	        "columnDefs":[{
	        	"targets": [ 0 ],
	            "visible": false  
	        }],
	        "createdRow": function ( row, data, index ) {
	        },
		"drawCallback": function( settings ) {
        }
	})).api();
	
    /**
     * 查询
     */
    $("#btn_query").click(function () {
        _table.draw();
    });
    
    /**
     * 添加按钮
     */
    $("#addUser").click(function(){
		clearDev();
		$('#useraccount').removeAttr("disabled");
		$('#userusername').removeAttr("disabled");
		$("#accountAlone").html("");
		$("#userpassword").val("123");
		$("#btnYes").attr("disabled",true);
		$("#myModalLabel").text("新增用户");
	});
    
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		if($("#myModalLabel").text()=="修改用户"){
			$("#mainform").attr("action",$("#basePath").val() + "systemManagement/userupt");
		}else{
			$("#mainform").attr("action",$("#basePath").val() + "systemManagement/useradd");
		}
		$("#mainform").form('submit', {
	        success:function(data){
	    		$("#user").modal('hide');
	    		_table.draw();
	    		if($("#myModalLabel").text()=="修改用户") {
	    			$.dialog.tips("修改成功");
	    		}else if($("#myModalLabel").text()=="新增用户"){
	    			$.dialog.tips("添加成功");
				}
	        },
	        error: function () {
	        	if($("#myModalLabel").text()=="修改用户") {
	    			$.dialog.tips("修改失败");
	    		}else if($("#myModalLabel").text()=="新增用户"){
	    			$.dialog.tips("添加失败");
				}
		    }
		});
	});
	
	/**
	 * 登录账号离焦事件:确保唯一
	 */
	$("#useraccount").blur(function() {
		if($("#myModalLabel").text()=="新增用户"){
			if ($("#useraccount").val()==null || $("#useraccount").val().trim()=="") {
				$("#accountAlone").html("登录名不能为空！");
				$("#btnYes").attr("disabled",true);
			}else {
				$("#accountAlone").html("");
				$("#btnYes").attr("disabled",false);
				common.ajax({
					  url: $('#basePath').val() + "/systemManagement/sltUserByName",
					  type: "GET",
					  data: {
						  "account": $("#useraccount").val()
					  },
					  success: function (data) {
						  if (data) {
							$("#accountAlone").html("该账号已经存在！");
							$("#btnYes").attr("disabled",true);
						  }
					  },
					  error: function () {
						  $.dialog.tips("出现错误！");
					  }
				});
			}
		}
	});
	
	/**
	 * 设置权限
	 */
	$("#save_role").click(function(){
		var roleNodes = $.fn.zTree.getZTreeObj("role").getCheckedNodes();
		var param = {};
		for (var i = 0; i < roleNodes.length; i++) {
			param["roleIdList[" + i + "]"] = roleNodes[i].roleid;
		}
		param["userid"] = userId0;
		common.ajax({
			url : $("#basePath").val() + "/roles/" + userId0 + "/allot",
			type: "POST",
			data : param,
			success : function(data) {
				common.showMessage(data.msg);
			},
		});
		closeUser();
		
	});
	
	/**
	 * 关闭用户维护界面
	 */
	function closeUser() {
		$("#myModal_urole").modal('hide');
	}
		
});

/**
 * 分配
 * @param userId
 * @returns
 */
function fenpei(userId) {
	if (userId!=1) {
		userId0 = userId;
		$("#myModalLabel1").text("分配权限");
		$("#myModal_urole").modal('show');
		initRoleTree(userId); 
	}else {
		$.dialog.tips("系统管理员不能分配");
	}
	
}

/**
 * 初始化角色树
 */
function initRoleTree(userId) {
	common.ajax({
		url : $("#basePath").val() + "/roles",
		success : function(data) {
			var setting = {
				edit : {
					enable : true,
					showRemoveBtn : false,
					showRenameBtn : false,
					drag : {
						isCopy : false
					}
				},
				check : {
					enable : true
				},
				view : {
					dblClickExpand : true,// 定义双击展开
					showLine : true,
					selectedMulti : false
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "roleid"
					},
					key:{
						name:"rolename"
					}
				},
				callback : {
					//onRightClick : roleRightClick
				}
			};
//			data.unshift({roleid:0,roleName:"角色列表",open:true,nocheck:true,icon:""});
			data[0].nocheck=false;
			$.fn.zTree.init($("#role"), setting, data);
			selectRole(userId);
		}
	});
}


/**
 * 选中角色菜单
 */
function selectRole(userId) {
	common.ajax({
		url : $("#basePath").val() + "/roles/" + userId + "/roles",
		success : function(data) {
			if(data=="" || data.rolist.length==0 || data.rolist[0].roleid==undefined){
				return ;
			}
			var roleTree = $.fn.zTree.getZTreeObj("role");
			roleTree.checkAllNodes(false);
			roleTree.expandAll(true);
			
			// 将角色树上,用户对应的角色节点勾选上
			for (var i = 0; i < data.rolist.length; i++) {
				roleTree.checkNode(roleTree.getNodeByParam("roleid", data.rolist[i].roleid), true);
			}
		},
	    error : function(XMLHttpRequest, textStatus, errorThrown){
	    	console.log(XMLHttpRequest.status); 
	    	console.log(XMLHttpRequest.readyState); 
	    }
	});
}

function xiugai(userid) {
	if (userid==1) {
		$('#xg'+userid).removeAttr("data-toggle");
		$('#xg'+userid).removeAttr("data-target");
		$.dialog.tips("系统管理员不能修改");
	}else {
		$("#useraccount").attr('disabled','disabled');
		$("#userusername").attr('disabled','disabled');
		$("#accountAlone").html("");
		$("#btnYes").attr("disabled",false);
		$("#myModalLabel").text("修改用户");	
		common.ajax({
			  url: $('#basePath').val() + "/systemManagement/getList",
			  type: "GET",
			  data: {
			      "userid": userid
			  },
			  success: function (data) {
				  var dataList = data["dataList"][0];
			      $("#useraccount").val(dataList.account);
			      $("#userusername").val(dataList.username);
			      $("#userpassword").val(dataList.password);
				  $("#userbelongto").val(dataList.belongto);
				  $("#usersex").val(dataList.sex);			
			  	  $("#useremail").val(dataList.email);
			  	  $("#userphonenumber").val(dataList.phonenumber);
			  	  $("#userid").val(dataList.userid);
		      }
		});
	}

}

function shanchu(userid) {
	if (userid==1) {
		$.dialog.tips("系统管理员不能删除");
	}else{
		message = "确定要删除吗?";

	    $.dialog.confirm(message, function () {
	        common.ajax({
	            url: $("#basePath").val() + "/systemManagement/userdlt",
	            type: "GET",
	            data: {
	                "userid": userid
	            },
	            success: function (data) {
	                $.dialog.tips("删除成功");
	                $("#btn_query").click();
	            },
	            error: function () {
	            	$.dialog.tips("删除失败");
	            }
	        });
	    });
	}
}

/**
 * 清空增加模态框值
 */
function clearDev() {
	$("#useraccount").val("");
	$("#userusername").val("");
	$("#userpassword").val("");
	$("#userbelongto").val("");
	$("#usersex").val("");			
	$("#useremail").val("");
	$("#userphonenumber").val("");
}

var userManage = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		param.username = $("#username").val();
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.page = data.start/10 + 1;
		return param;
	}
};