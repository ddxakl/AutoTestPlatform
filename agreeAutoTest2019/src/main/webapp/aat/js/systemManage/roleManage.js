var roleId0;
$(function(){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	
	var $table = $('#role-table');

	//加载下拉参数 
	var item = common.getDictJson(24);		
	common.boption($("#roleisenable"),item);
	
	var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {
			//封装请求参数
			var param = roleManage.getQueryCondition(data);
			$.ajax({
	            type: "GET",
	            url: $('#basePath').val() + "/systemManagement/roleList",
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
	            	data: "roleid",
	            	width : "0px"
	            },
	            {
	            	className : "ellipsis",	
	            	data: "rolename",
					width: "180px",
	            	orderable:false
	            },
				 {
					className : "ellipsis",
					data: "roledesc",
					width: "180px",
					orderable : false
				},
	            {
	            	className : "ellipsis",
					data : "isenable",
					width: "180px",
					orderable:false,
					render: function (data, type, row, meta) {
						if (data==1) {
							return "启用";
						}else {
							return "停用";
						}
	                }
					
				},
				{
	                className: "ellipsis",
	                data: "caseCao",
	                orderable: false,
	                render: function (data, type, row, meta) {
                		return '<div class="dropdown">' + 
	                    '<a href="javascript:void(0)" onclick="fenpei(\'' + row.roleid + '\')">分配</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" id="xg'+row.roleid+'" data-toggle="modal" data-target="#role" onclick="xiugai(\'' + row.roleid + '\')">修改</a>' + 
	                    '<a href="javascript:void(0)" style="padding-left: 20px;" onclick="shanchu(\'' + row.roleid + '\')">删除</a>' + 
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
    $("#addRole").click(function(){
		clearDev();
		$("#roleisenableMust").html("");
		$("#rolerolenameMust").html("");
		$("#myModalLabel").text("新增角色");
	});
    
	/**
	 * 模态框页面-确定按钮事件
	 */
	$("#btnYes").click(function(){
		$("#roleisenableMust").html("");
		$("#rolerolenameMust").html("");
		if ($("#rolerolename").val() != null && $("#rolerolename").val() != ""){
			if ($("#roleisenable").val() != null && $("#roleisenable").val() != ""){
				if($("#myModalLabel").text()=="修改角色"){
					$("#mainform").attr("action",$("#basePath").val() + "systemManagement/roleupt");
				}else{
					$("#mainform").attr("action",$("#basePath").val() + "systemManagement/roleadd");
				}
				$("#mainform").form('submit', {
			        success:function(data){
			    		$("#role").modal('hide');
			    		_table.draw();
			    		if($("#myModalLabel").text()=="修改角色") {
			    			$.dialog.tips("修改成功");
			    		}else if($("#myModalLabel").text()=="新增角色"){
			    			$.dialog.tips("添加成功");
						}
			        },
			        error: function () {
			        	if($("#myModalLabel").text()=="修改角色") {
			    			$.dialog.tips("修改失败");
			    		}else if($("#myModalLabel").text()=="新增角色"){
			    			$.dialog.tips("添加失败");
						}
				    }
				});
			}else {
				$("#roleisenableMust").html("状态是必选项！");
			}
		}else {
			$("#rolerolenameMust").html("角色名是必填项！");
		}
	});
	
	
	/**
	 * 设置权限
	 */
	$("#save_role").click(function(){
		var menuNodes = $.fn.zTree.getZTreeObj("menu").getCheckedNodes();
		var param = {};
		for (var i = 0; i < menuNodes.length; i++) {
			param["moduleIdList[" + i + "]"] = menuNodes[i].moduleId;
		}
		param["roleid"] = roleId0;
		common.ajax({
			url : $("#basePath").val() + "/menus/" + roleId0 + "/allot",
			type: "POST",
			data : param,
			success : function(data) {
				common.showMessage(data.msg);
			},
		});
		closeRole();
	});
	
	/**
	 * 关闭角色维护界面
	 */
	function closeRole() {
		$("#myModal_rmodule").modal('hide');
	}
	
});

/**
 * 分配
 * @param roleid
 * @returns
 */
function fenpei(roleId) {
	roleId0 = roleId;
	$("#myModalLabel1").text("分配权限");
	$("#myModal_rmodule").modal('show');
	initMenuTree(roleId); 
}

/**
 * 初始化菜单树
 */
function initMenuTree(roleId) {
	if (roleId==1) {
		// 初始化菜单树
		common.ajax({
			url : $("#basePath").val() + "/menus/admin",
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
							pIdKey : "parentModuleId",
							idKey : "moduleId"
						},
						key:{
							name:"moduleName"
						}
					},
					callback : {
					//	onRightClick : menuRightClick
					}
				};
				data[0].nocheck=false;
				//data[0].open=true;
				$.fn.zTree.init($("#menu"), setting, data);
				selectRole(roleId);
			}
		});
	}else {
		// 初始化菜单树
		common.ajax({
			url : $("#basePath").val() + "/menus",
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
							pIdKey : "parentModuleId",
							idKey : "moduleId"
						},
						key:{
							name:"moduleName"
						}
					},
					callback : {
					//	onRightClick : menuRightClick
					}
				};
				data[0].nocheck=false;
				//data[0].open=true;
				$.fn.zTree.init($("#menu"), setting, data);
				selectRole(roleId);
			}
		});
	}
	
}

/**
 * 选中角色菜单
 */
function selectRole(roleId) {
	common.ajax({
		url : $("#basePath").val() + "/menus/" + roleId + "/menus",
		success : function(data) {
			if(data=="" || data.mdlist.length==0 || data.mdlist[0].moduleId==undefined){
				return ;
			}
			var menuTree = $.fn.zTree.getZTreeObj("menu");
			menuTree.checkAllNodes(false);
			menuTree.expandAll(true);
			
			// 将菜单树上,角色对应的菜单节点勾选上
			for (var i = 0; i < data.mdlist.length; i++) {
				if(roleId==1 && data.mdlist[i].belong==0){
					continue;
				}
				menuTree.checkNode(menuTree.getNodeByParam("moduleId", data.mdlist[i].moduleId), true);
			}
		},
	    error : function(XMLHttpRequest, textStatus, errorThrown){
	    	console.log(XMLHttpRequest.status); 
	    	console.log(XMLHttpRequest.readyState); 
	    }
	});
}

function xiugai(roleid) {
	if (roleid==1) {
		$('#xg'+roleid).removeAttr("data-toggle");
		$('#xg'+roleid).removeAttr("data-target");
		$.dialog.tips("系统管理员不能修改");
	}else {
		$("#myModalLabel").text("修改角色");	
		common.ajax({
			  url: $('#basePath').val() + "/systemManagement/getRoleListById",
			  type: "POST",
			  data: {
			      "roleid": roleid
			  },
			  success: function (data) {
				  var dataList = data["dataList"][0];
			      $("#rolerolename").val(dataList.rolename);
				  $("#roleroledesc").val(dataList.roledesc);
				  $("#roleisenable").val(dataList.isenable);			
				  $("#roleid").val(dataList.roleid);			
		      }
		});
	}
	
}

function shanchu(roleid) {
	if (roleid==1) {
		$.dialog.tips("系统管理员不能删除");
	}else{
		message = "确定要删除吗?";

	    $.dialog.confirm(message, function () {
	        common.ajax({
	            url: $("#basePath").val() + "/systemManagement/roledlt",
	            type: "GET",
	            data: {
	                "roleid": roleid
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
	$("#rolerolename").val("");
	$("#roleroledesc").val("");
	$("#roleisenable").val("");			
}

var roleManage = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		param.rolename = $("#rolename").val();
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.page = data.start/10 + 1;
		return param;
	}
};