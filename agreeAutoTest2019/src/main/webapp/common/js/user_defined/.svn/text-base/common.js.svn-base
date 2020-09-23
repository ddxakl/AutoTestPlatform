var common = window.common || {};

/**
 * 打印指定消息。
 */
common.showMessage = function(msg) {
	if(msg) {
		$.dialog.tips(msg);
		//alert(msg);
	}
}

const constSettings = {
		P1:"CheckDialogResult",
		P2:"CheckDialogRegexResult",
		P3:"CheckCompoentResult",
		P4:"CheckTableResult"
	};


/** 
 * @see   获取数据字典值
 * @param   json字符串 
 * @return 返回object,array,string等对象 
 */    
common.getDictJson = function (itemid){
		var dictjson;
   		var submitdata ={};
   		
   		$.ajax({
				type:"get",
                url:$('#basePath').val()+"/common/getSysDictionaryItem/"+itemid,
                async :false,//async 异步 
                dataType:'json',    //接受数据格式
		  	 	data: submitdata, 
                success:function(data){
                	/*if(data.retCode==1){
                		//dictjson=data.data;
                	}else{
                		dictjson=data.msg;
                	}*/
                	dictjson=data.data;
               }
         });
   		
   		return  dictjson  	
   } 



/**
 *  增加 endwith 函数
 */
	if (typeof String.prototype.endsWith != 'function') {
	       String.prototype.endsWith = function (str){
	          return this.slice(-str.length) == str;
	       };
	}


/**
 * 对jQuery的ajax方法的二次封装
 */
common.ajax = function(param) {
	var mgeParam = $.extend({
		timeout : 10000
	} , param , {
		complete : function(response) {
			var url = response.getResponseHeader("url");
			if(url) {
				location.href = url;
			} else {
				if(param.complete && typeof param.complete == "function") {
					param.complete();
				}
			}
		}
	});
	$.ajax(mgeParam);
}

/**
 * 加载下拉框的值
 * id    select组件对象
 * param 后台返回参数
 */
common.boption = function(sel,param){
	
	if(param.msg){
		$.dialog.tips(param.msg);
		return ;
	}
	for(var j = 0,len = param.length; j < len; j++){		
		sel.append("<option value="+param[j].dictValue+">"+param[j].dictName+"</option>"); 				
	}	
}

/**
 * 加载品牌勾选框的值
 * id    组件对象
 * param 后台返回参数
 */
common.bcheck = function(sel,param){
	if(param.msg){
		$.dialog.tips(param.msg);
		return ;
	}
	var ul = sel.find("ul");
	for(var j = 0,len = param.length; j < len; j++){
		var li = $("<li>");							
		li.attr("data_id", param[j].dictValue);
		li.html('<input type="checkbox" class="cbx-brand"/><span>'+param[j].dictName+'</span>');
		ul.append(li);				
	}	
}

/**
 * 加载Android勾选框的值
 * id    组件对象
 * param 后台返回参数
 */
common.acheck = function(sel,param){
	if(param.msg){
		$.dialog.tips(param.msg);
		return ;
	}
	var ul = sel.find("ul");
	for(var j = 0,len = param.length; j < len; j++){
		var li = $("<li>");							
		li.attr("data_id", param[j].dictValue);
		li.html('<input type="checkbox" class="cbx-andSys"/><span>'+param[j].dictName+'</span>');
		ul.append(li);				
	}	
}

/**
 * 加载Ios勾选框的值
 * id    组件对象
 * param 后台返回参数
 */
common.icheck = function(sel,param){
	if(param.msg){
		$.dialog.tips(param.msg);
		return ;
	}
	var ul = sel.find("ul");
	for(var j = 0,len = param.length; j < len; j++){
		var li = $("<li>");							
		li.attr("data_id", param[j].dictValue);
		li.html('<input type="checkbox" class="cbx-iosSys"/><span>'+param[j].dictName+'</span>');
		ul.append(li);				
	}	
}

/**
 * 加载分辨率勾选框的值
 * id    组件对象
 * param 后台返回参数
 */
common.rcheck = function(sel,param){
	if(param.msg){
		$.dialog.tips(param.msg);
		return ;
	}
	var ul = sel.find("ul");
	for(var j = 0,len = param.length; j < len; j++){
		var li = $("<li>");							
		li.attr("data_id", param[j].dictValue);
		li.html('<input type="checkbox" class="cbx-resolution"/><span>'+param[j].dictName+'</span>');
		ul.append(li);				
	}	
}


/**
 * 检查是否是默认用户
 * defuser是创建人 curuser是当前用户(字段是username)
 */
common.checkuseropt = function(defuser,curuser){
	if (curuser.trim() != "系统管理员") {
		var message="没有权限";
		if(defuser.trim() !== curuser.trim()){
			$.dialog.tips(message);
			return false;
		}
	}
	return true;
}


/**
 * 页面返回码定义，与后台PageCode定义对应
 */
common.pageCode = {

}
