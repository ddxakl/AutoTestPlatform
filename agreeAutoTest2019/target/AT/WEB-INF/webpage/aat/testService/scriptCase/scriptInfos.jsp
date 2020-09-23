<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="${basePath}/common/css/datatables/dataTables.responsive.css"
	rel="stylesheet">
<!--backstage body CSS-->
<link href="${basePath}/system/css/backstage.css" rel="stylesheet">
<link href="${basePath}/common/css/compoent/button.css" rel="stylesheet">
<link href="${basePath}/aat/css/scriptInfos/scriptInfos.css"
	rel="stylesheet">

</head>
<body>
	<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
	<script src="${basePath}/aat/js/testService/scriptcase/scriptInfos.js"></script>
	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="caseId" value="${caseId}" />
	<input type="hidden" id="rporst" value="${rporst}" />
	
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li><a href="${basePath}/testService/scriptCase">脚本案例</a></li>
				<li><a href="${basePath}/testService/upload/ST">上传脚本</a></li>
				<li class="active">完善信息</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainBody">
			<form id="addInfosForm" >
				<div>
					<label>案例名称 <span class="text-fail">＊</span></label> <input type="text" id="caseName"/>
					<span class="spanMust" id="caseNameMust"></span>
				</div>
				<div>
					<label>案例编号 <span class="text-fail">＊</span></label> <input type="text" id="caseIdentifier"/>
					<span class="spanMust" id="caseIdentifierMust"></span>
				</div>
				<div>
					<label>所属项目 <span class="text-fail">＊</span></label> <input type="text" id="belong"/>
					<span class="spanMust" id="belongMust"></span>
				</div>
				<div>
					<label>关联交易码 </label> <input type="text" id="tradecodes"/>
				</div>
				<div>
					<label>脚本类型 </label> <input type="text" id="scriptType" disabled="disabled" value="" style="background-color: #d1d2d359"/>
				</div>
				<div>
					<label style="margin-top: -38px;">描述信息</label>
					<textarea class="form-control product-name" style="height: 70px;" id="caseDesc" maxlength="199" name="caseDesc" placeholder="请输入描述信息"></textarea>
				</div>
<!-- 				<div class="form-group"> -->
<!-- 	               	<label>AB版本</label> -->
<!--                    	<select id="abversion" class="form-control" name="abversion"></select> -->
<!--                 </div> -->
			</form>
		</div>

		<div class="footer">
			<div class="breadLine"></div>
			<a onclick="createCase()"><input class="next" type="button" value="完成"></a>
		</div>
	</div>
</body>

</html>