<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="${basePath}/aat/css/testService/record/upload.css"
	rel="stylesheet">
<!-- <script type="text/javascript" src="js/bootstrap.file-input.js"></script> -->
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
</head>
<body>
	<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
	<script src="${basePath}/aat/js/testService/record/upload.js"></script>
	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="casetype" value="${casetype}" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li><a href="${basePath}/testService/record">录制回放</a></li>
				<li class="active">上传脚本</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainBody">
			<div class="center">
				<img src="${basePath}system/images/download.png" class="upload-logo">
				<div id="uploadscript" class="uploadscript" style="margin-top: 45px;">
					<div>点击选择脚本</div>
					<input id="uploadfile" type="file" class="uploadfile" onchange="uploadscript();" />
				</div>
				<div>
					<div class="progress"
						style="margin-top: 45px; width: 300px; float: left;">
						<div class="progress-bar" id="progress-bar"></div>
					</div>
				</div>

				<!-- <div class="uploadscript">
					<div>点击选择视频</div>
					<input type="file" class="uploadfile "></input>
				</div> -->

			</div>
		</div>
		<div class="footer">
			<a><input id="back" type="button" value="返回" onclick="back()" style="background-color: #1081de;"></a>
			<a><input id="next" type="button" value="下一步" onclick="nextPage()" style="background-color: #8ea6bb;"></a>
		</div>
	</div>
</body>
	<!-- jQuery -->
    <script src="${basePath}/common/js/jquery/jquery.min.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.easyui.min.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.form.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
	<script src="${basePath}/common/js/datatables/jquery.dataTables.min.js"></script>
    <script src="${basePath}/common/js/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${basePath}/common/js/datatables/dataTables.responsive.js"></script>
    <script src="${basePath}/common/js/jquery/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${basePath}/common/js/jquery/messages_zh.js" type="text/javascript"></script>
	<script src="${basePath}/common/js/lhgdialog/lhgdialog.js?skin=bootstrap2"></script>
	<script src="${basePath}/common/js/user_defined/constant.js"></script>
    <script src="${basePath}/common/js/user_defined/common.js"></script>
	<script src="${basePath}/aat/js/testService/record/record.js"></script>
<style type="text/css">
</style>

<script>
	
</script>
</html>