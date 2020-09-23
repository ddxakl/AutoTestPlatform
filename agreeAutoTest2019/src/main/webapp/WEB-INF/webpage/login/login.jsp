<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   

<!DOCTYPE html>
<html>
<head>
	<meta charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link  type="image/x-icon" href="<%=basePath%>/system/images/fav.ico"  rel="shortcut icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<title>login</title>
	<link rel="stylesheet"  href="${basePath}/login/css/normalize.css"/>
	<link rel="stylesheet"  href="${basePath}/login/css/demo.css"/>
	<link rel="stylesheet"  href="${basePath}/login/css/jquery.jgrowl.min.css">
	<link rel="stylesheet" type="text/css" href="${basePath}/login/css/style.css" />
	<link rel="stylesheet" href="${basePath}/login/css/fontawesome-all.css" />
	<!--必要样式-->
	<link rel="stylesheet" href="${basePath}/login/css/component.css"/>
	<!-- google fonts-->
	<link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	<!--[if IE]>
	<script src="js/html5.js"></script>
	<![endif]-->
	<style>
		input::-webkit-input-placeholder{
			color:rgba(0, 0, 0, 0.726);
		}
		input::-moz-placeholder{   /* Mozilla Firefox 19+ */
			color:rgba(0, 0, 0, 0.726);
		}
		input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
			color:rgba(0, 0, 0, 0.726);
		}
		input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
			color:rgba(0, 0, 0, 0.726);
		}
	</style>
</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="message" value="${reCode.msg}"/>
		<h1></h1>
		<div class=" w3l-login-form">
			<h2>赞同自动化测试平台</h2>
			<form id="mainForm" action="${basePath}/login/validate" name="f" method="post">
				<div class=" w3l-form-group">
	                <div class="group">
	                    <i class="fas fa-user"></i>
	                    <input id="ID" name="account" type="text" class="form-control" placeholder="请输入用户名" required="required" />
	                </div>
	            </div>
	            <div class=" w3l-form-group">
	                <div class="group">
	                    <i class="fas fa-unlock"></i>
	                    <input id="PASSWORD" name="password" type="password" class="form-control" placeholder="请输入密码" required="required" />
	                </div>
	            </div>
	            <br>
	            <button id="LOGIN" type="submit">登 录</button>
			</form>
		</div>
		<script src="${basePath}/login/js/TweenLite.min.js" type="text/javascript"></script>
		<script src="${basePath}/login/js/EasePack.min.js" type="text/javascript"></script>
		<script src="${basePath}/common/js/jquery/jquery.min.js" type="text/javascript"></script>
		<script src="${basePath}/login/js/rAF.js" type="text/javascript"></script>
		<script src="${basePath}/login/js/jquery.jgrowl.min.js" type="text/javascript"></script>
		<script src="${basePath}/login/js/Longin.js" type="text/javascript"></script>
		<script src="${basePath}/common/js/jquery/jQuery.md5.js" type="text/javascript"></script>
		<script src="${basePath}/common/js/jquery/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${basePath}/common/js/jquery/messages_zh.js" type="text/javascript"></script>
		<script src="${basePath}/common/js/user_defined/common.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
	    		setTimeout(function(){			        
	                	common.showMessage($("#message").val()); 
	             	},1000);  
	    	    
	    		// click
	    		$("#LOGIN").click(function () {
	    			if(login()){
	//     				if($("#PASSWORD").val()) {
	//         				$("#PASSWORD").val($.md5($("#PASSWORD").val()));
	//         			} 
	        			$("#mainForm").submit();
	        			//titlpe(); 
	    			}
	    	    });
	    	});
    	</script>
		<div style="text-align:center;"></div>
	</body>
</html>