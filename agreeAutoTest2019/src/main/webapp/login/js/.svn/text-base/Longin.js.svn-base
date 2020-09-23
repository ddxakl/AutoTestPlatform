function login(){
	var username = document.getElementById("ID").value;
    var password = document.getElementById("PASSWORD").value;
    if(username==""){
        $.jGrowl("用户名不能为空！", { header: '提醒' });
    }else if(password==""){
        $.jGrowl("密码不能为空！", { header: '提醒' });
    }else{
    	return true;
        //AjaxFunc();
    }
}
function AjaxFunc(){
	var username = document.getElementById("ID").value;
    var password = document.getElementById("PASSWORD").value;
    $.ajax({
        type: 'get',
        url: "",
        dataType: "json",
        data: {"username": username,"password": password},
        success: function (data) {
            
        },
        error: function (xhr, type) {
            console.log(xhr);
        }
    });
//    var account = document.getElementById("ID").value;
//    var password = document.getElementById("PASSWORD").value;
//    $.ajax({
//        type: 'get',
//        url: $('#basePath').val() + "/login/checking",
//        dataType: "json",
//        data: {"account": account,"password": password},
//        success: function (data) {
//        	if (data) {
//        		$("#mainForm").submit();
//			}else {
//				$.jGrowl("用户名或密码错误！", { header: '提醒' });
//			}
//        },
//        error: function (xhr, type) {
//            console.log(xhr);
//        }
//    });
}

function titlpe(){
	//alert($("#message").val()); 
	//common.showMessage($("#message").val());  
	var aaa=$("#message").val();
	/* setTimeout(function(){			        
	//titlpe(); 
 	},1000); */ 
	if(aaa!==""){
		setTimeout(function(){	
			alert($("#message").val()); 
			common.showMessage($("#message").val()); 
			$.jGrowl(aaa, { header: '提醒' }); 
		 },500); 
	}
}