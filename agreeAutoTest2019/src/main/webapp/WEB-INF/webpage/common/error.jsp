<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
        <title>系统错误页面</title>
       <script src="${basePath}/common/js/jquery/jquery.min.js"></script>
       <script src="${basePath}/common/js/lhgdialog/lhgdialog.js?skin=bootstrap2"></script>
        <script src="${basePath}/common/js/user_defined/common.js" type="text/javascript"></script>
        <script type="text/javascript">
        	$(function () {
        		common.showMessage('请重新登录');
        		setTimeout("openurl()","1200");
        	});
        	
        	
        	function openurl(){
        		var topWindow = window;
        		while(topWindow.parent != topWindow) {
        			topWindow = topWindow.parent;
        		}
        		topWindow.location.href = "${basePath}/login";
        	}
		</script>
    </head>
    <body>
    </body>
</html>