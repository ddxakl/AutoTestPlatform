<%@page import="com.agree.system.entity.SystemUser"%>
<%@page import="com.agree.framework.constant.ApplicationKeyConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	SystemUser user = (SystemUser)session.getAttribute(ApplicationKeyConst.USER_INFO);
	String username = user.getUsername();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link type="image/x-icon" href="<%=basePath%>/system/images/fav.ico"
	rel="shortcut icon" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>赞同自动化测试平台</title>

<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="${basePath}/common/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link href="${basePath}/system/css/admin.css" rel="stylesheet">
<style type="text/css">
	.left-nav #nav li a:hover {
		color: #45beed;
	}
	li {
		list-style: none;	
	}
	.glyphicon {
		top: 1.9px;
	}
	.welquit {
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-content: center;
	}
	.welcome {
	    color: rgb(255, 255, 255);
	    font-size: 12px;
	    line-height: 50px;
	    transition: background-color 0.3s ease 0s;
	}
	.quit {
		line-height: 50px;
		color: #56b1ff;
	}
	.quit:hover {
		color: #6acfff;
	}
</style>
</head>

<body style="background: #ECF0F5;">
	<input type="hidden" id="basePath" value="${basePath}" />
	<!-- 顶部开始 -->
	<div class="container">
		<div class="logo">
			<a style="padding-left: 7px;" href="#"><img src="<%=basePath%>/system/images/fav.ico" style="width: 24px; height:22px;">&nbsp;赞同自动化测试平台</a>
		</div>
		<div class="welquit">
			<div class="welcome">欢迎您，<%=username%></div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="quit" href="${basePath}/login"><i class="fa fa-sign-out fa-fw"></i>退出</a>
		</div>
	</div>
	<!-- 顶部结束 -->

	<!-- 中部开始 -->
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
				<c:forEach items="${MENU_INFO}" var="item" varStatus="s">
                   	<c:if test="${item.moduleType == 2}">
                   		<a href="javascript:void(0);" onclick="clickSubMenu(this,'${basePath}${item.moduleLocation}')"><i class="${item.moduleImg}"></i>${item.moduleName}</a>
                   	</c:if> 
                   	<c:if test="${item.moduleType == 1}">
                   		<c:if test="${item.moduleId == 3}">
                   			<li class="toggle"><a href="javascript:void(0);" onclick="clickSubMenu(this,'${basePath}${item.moduleLocation}')" style="font-size: 16px;"><i class="${item.moduleImg}"></i>${item.moduleName}</a></li>
                   		</c:if>
                   		<c:if test="${item.moduleId != 3}">
							<li class="toggle"><a style="font-size: 16px;"><i class="${item.moduleImg}"></i>${item.moduleName}</a></li>
	                   		<ul class="nav-second-level" style="display:none;padding-left: 6%;">
		                        <c:forEach items="${SUB_MENU_INFO}" var="sitem" varStatus="sub">
		                        	<c:if test="${item.moduleId == sitem.parentModuleId}">
				                         <li>
				                             <a href="javascript:void(0);" onclick="clickSubMenu(this,'${basePath}${sitem.moduleLocation}')"><i class="${sitem.moduleImg}"></i>${sitem.moduleName}</a>
				                         </li>
				                     </c:if>    
		                         </c:forEach>
	                     	</ul>
                   		</c:if>
                   	</c:if>                          
	          	</c:forEach>
			</ul>
		</div>
	</div>
	<!-- <div class="x-slide_left"></div> -->
	<!-- 左侧菜单结束 -->

	<!-- 右侧主体开始 -->
	<div class="page-content" style="overflow-y: no;">
		<div class="page-tab">
			<iframe class="container-fluid" id="mainPage" scrolling="yes"
				src="${basePath}/admin/index" frameborder="0" height="100%"
				width="100%" style="padding-left: 0px; padding-right: 0px;"></iframe>
		</div>
	</div>
	<!-- jQuery -->
	<script src="${basePath}/common/js/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>

	<!-- admin JavaScript -->
	<script src="${basePath}/system/js/admin.js"></script>

	<script>
		$(function() {
			$("#nav .toggle").click(function() {
				$(this).next("ul").slideToggle(300);
			});
			$("#nav ul > li").click(function() {
				$("#nav ul > li").removeClass("active");
				$(this).addClass("active");
			});
			$("#dictMan").click(function () {
				$("#nav ul > li").removeClass("active");
			});
		})
		function clickMenu(pagePath) {
			$("#mainPage").attr("src", pagePath);
		}
	</script>
</body>

</html>