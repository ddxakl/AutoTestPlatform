function showDiv(dicttypeid){
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	var $tab = $('#table-dictValue');
	/**
	 * form 表单验证
	 */
	$.fn.dataTable.ext.errMode = 'none'; //【屏蔽警告错误提示】防止添加一行时弹无主键警告框
	var _tab = $tab.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		"paging": false,
		"info": false, 
		serverSide: false,
		ajax : function(data, callback, settings) {

			$.ajax({
	            type: "GET",
	            url: $('#basePath').val()+"/dictionaryManagement/dictVals",
	            cache : false,	//禁用缓存
	            data: "dicttypeid="+dicttypeid,	//传入已封装的参数
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
	columns: [//配置每一列的数据源
	            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
	            {
	            	className : "ellipsis",
					data : "dictname",
					width : "180px",
					orderable:false
				},{
	            	className : "ellipsis",	
	            	data: "dictvalue",
	            	width : "180px",
	            	orderable:false
	            	//render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
	            	//固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
					//切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
	            },{
	            	className : "ellipsis",
					data : "dictdesc",
					width : "180px",
					orderable:false
				}				
	        ],
	columnDefs:[{//把dataTable变成可编辑的行
                "targets": [1,2,3],//第3列和第4列
                createdCell:function(cell, cellData, rowData, rowIndex, colIndex){
                    $(cell).click(function(){
                    	let dd =$(cell).text();
                        $(this).html('<input type="text" size="16"/>');
                        var aInput = $(this).find(":input");
                        //aInput.focus().val(cellData);//获得焦点（原来）
                        aInput.focus().val(dd);//获得焦点
                    });
                    $(cell).on("blur",":input",function(){                 	
                        var text = $(this).val();
                        $(cell).html(text);
//                        editTableObj.table.cell( cell ).data(text)
                    })
                }
            },{
            	"targets": [ ],
                "visible": false  
            }],
		"drawCallback": function( settings ) {
        	//渲染完毕后的回调
        	//清空全选状态
        	//默认选中第一行
//        	$("tbody tr",$tab).eq(0).click();
        }
	})).api();	
	
	$('#table-dictValue').on( 'error.dt', function ( e, settings, techNote, message ){
	    //这里可以接管错误处理，也可以不做任何处理
	    console.log( 'An error has been reported by DataTables: ', message );//由于屏蔽了错误弹框，用log显示
	}).DataTable();
	
	/** 添加行*/	
	$('#btn_ins').on( 'click', function () {
		_tab.row.add( [
	    ] ).draw(false);
	} );
	/** 删除行*/
	$("#btn_del").off('click');//由于每次调用showDiv方法会绑定一次，所以每次首先得清空绑定
	$('#btn_del').on( 'click', function(){
		var arrItemId = [];
        $("tbody :checkbox:checked",$tab).each(function(i) {
        	var item = _tab.row($(this).closest('tr')).data();
        	arrItemId.push(item);
        });       
		deleteItem(arrItemId);
	});
	
	$tab.on("change",":checkbox",function(e) {		
		if ($(this).is("[name='cb-check-all']")) {
			//全选
			$(":checkbox",$tab).prop("checked",$(this).prop("checked"));
		}else{
			//一般复选
			var checkbox = $("tbody :checkbox",$tab);
			$(":checkbox[name='cb-check-all']",$tab).prop('checked', checkbox.length == checkbox.filter(':checked').length);
		}
    });
		
	function deleteItem(selectedItems){	
		var message;
		if (selectedItems&&selectedItems.length) {
			if (selectedItems.length == 1) {
				//alert(JSON.stringify(selectedItems[0]))
				//dictvalueid dicttypeid dictValue dictName dictDesc
				message = "确定要删除 '"+selectedItems[0].dictname+"' 吗?";
				
			}else{
				message = "确定要删除选中的"+selectedItems.length+"项记录吗?";
			}
			
			$.dialog.confirmDanger(message, function(){
					if(selectedItems[0].dictname==undefined){//当新增行未保存时
						//$("#myModal_keyValue").modal('hide');						
						return;						
					}
					var jsonData = JSON.stringify(selectedItems);// 转成JSON格式
					console.log(jsonData);
					//var result = $.parseJSON(jsonData);// 转成JSON对象
					common.ajax({
						url: $("#basePath").val() + "/dictionaryManagement/deletes",
						type : "POST",
						data : {
							"result" : jsonData
						},
						success : function(data) {
							$.dialog.tips("删除成功");
							$("tbody :checkbox:checked",$tab).each(function(i) {
								var tr = $(this).parents("tr");
								var index = tr.prevAll().length;
					        	//var item = _tab.row($(this).closest('tr')).data();
								//alert(_tab.row($(this)))
								_tab.row(index).remove().draw(false);
								
					        });
						//	_tab.draw(false);
						//	$("#myModal_keyValue").modal('hide');							
						}
					});
			});
		}else{
			$.dialog.tips('请先选中要操作的行');
		}
	}
	
	
/**点击确定按钮*/	
	$("#confirm_info").off('click');//由于每次调用showDiv方法会绑定一次，所以每次首先得清空绑定
	$("#confirm_info").on('click',function(){
		var result=get_table_data(dicttypeid);
		var jsonData = JSON.stringify(result);
		var dival= result[0]["dictitem"];//字典值的类型ID
		common.ajax({
			url: $("#basePath").val() + "/dictionaryManagement/dictValsupt",
			type : "POST",
			data : {
				"dicttypeid" : dival,
				"dictData" : jsonData
			},
			success : function(data) {
				$.dialog.tips('字典值修改成功！');
				$("#myModal_keyValue").modal('hide');
			}
		});	
	});
	
	if (_tab) {	
		_tab.destroy(); //销毁datatable
    } 
};
/**获取table中的数据*/
function get_table_data(dicttypeid){
	var tr =$("#table-dictValue tr");
	var result =[];
	for(var i=0;i<tr.length;i++){
		var tds =$(tr[i]).find("td");
		if(tds.length>0){
			result.push({'dictitem':dicttypeid,'dictname':$(tds[1]).html(),'dictvalue':$(tds[2]).html(),'dictdesc':$(tds[3]).html(),});
		}
	}
	return result;
}
