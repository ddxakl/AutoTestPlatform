<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="${basePath}/aat/css/testService/record/upload.css" rel="stylesheet">
<!-- <script type="text/javascript" src="js/bootstrap.file-input.js"></script> -->
<!-- Bootstrap Core CSS -->
<link href="${basePath}/common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- DataTables CSS -->
<link href="${basePath}/common/css/datatables/dataTables.bootstrap.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="${basePath}/common/css/datatables/dataTables.responsive.css" rel="stylesheet">
<!-- webuploader CSS-->
<link href="${basePath}/common/css/webuploader/webuploader.css" rel="stylesheet">
<!--backstage body CSS-->
<link href="${basePath}/system/css/backstage.css" rel="stylesheet">

</head>
<body>
	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="casetype" value="${casetype}" />
	<div class="page-main">
		<div class="title">
			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/index">Home</a></li>
				<li><a href="${basePath}/testService/scriptCase">脚本案例</a></li>
				<li class="active">上传脚本</li>
			</ul>
		</div>
		<div id="breadLine"></div>
		<div class="mainBody">
			<div class="center">
				<img src="${basePath}system/images/download.png" class="upload-logo">
				<div class="btns" style="margin-top: 45px;">
					<div id="jb_pick" text-align: center;">点击选择脚本</div>
				</div>
				<div id="thelist" class="uploader-list"></div>
			</div>
		</div>
		<div class="footer">
			<a><input id="back" type="button" value="返回" onclick="back()"></a>
			<a><input id="next" type="button" value="下一步" onclick="nextPage()" style="background-color: #8ea6bb;"></a>
		</div>
	</div>
</body>

<script src="${basePath}/common/js/jquery/jquery.min.js"></script>
<script src="${basePath}/common/js/bootstrap/bootstrap.min.js"></script>
<script src="${basePath}/common/js/webuploader/webuploader.js"></script>
<script src="${basePath}/common/js/user_defined/constant.js"></script>
<script src="${basePath}/common/js/user_defined/common.js"></script>
<script>
var caseId ;
var rporst ;
$(function(){
	$("#next").attr("disabled","disabled");
	
	var uploader = WebUploader.create({					    
		 // 自动上传。
		auto: true,
	    // swf文件路径
	    swf: '<%=basePath%>/common/js/webuploader/Uploader.swf',
	    
	    // 文件接收服务端。
	    server: "${basePath}/script/upscript",
		
		formData:{},
		
		fileVal:'upscript',
		
		method: 'post',

	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#jb_pick',				
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: true,
	    
	     // 只允许选择文件，可选。
	    accept: {
	        title: 'Class',
	        extensions: 'class',
	        mimeTypes: 'class/*'
	    }
	});
	// 文件上传过程中创建进度条实时显示。	
		uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress span');

	    // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }
	 
        var percentageNew = Math.round(percentage * 100);
//         $li.find('p.state').text('上传中... ' + percentageNew + '%');
        $percent.css( 'width', percentage * 100 + '%' );

	});
		
	// 脚本上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file, response ) { 
		caseId = response.json.caseId;
		scriptfile = response.json.scriptfile;
		scriptname = response.json.scriptname;
		scriptstatus = 1;
// 		$( '#'+file.id ).find('p.state').text('上传成功');
		//$("#jbimg").attr("src", "${basePath}/test/img/jb.png");
		$("#thelist").after("<div style='text-align: center;'>上传完成</div>");
		rporst = "st";
		$("#next").removeAttr("disabled");
		$("#next").css("background","#1081de");
	});
		
	// 当有文件被添加进队列的时候
	uploader.on( 'fileQueued', function( file ) {
		if($("#thelist").children().length <= 0){
	    	$("#thelist").append( '<div id="' + file.id + '" class="file-item">' +
	                '<p class="state" style="font-weight: bold;color: #808080;"></p><br>' +
	            '</div>' );
		}else{
			$("#thelist").children().hide(); <!--隐藏上传过的div-->
			$("#thelist").append( '<div id="' + file.id + '" class="file-item">' +
	                '<p class="state" style="font-weight: bold;color: #808080;"></p><br>' +
	            '</div>' );    		
		}
	});
		
	uploader.on( 'uploadFinished', function( block, data ) {
	});
	
    uploader.on( 'uploadError', function( file ) {
    });
	
})

function nextPage(){
	window.location.href= $("#basePath").val() + "/testService/scriptcaseSupplyInfos/" + caseId + "/" + rporst;
}

function back(){
	window.history.go(-1);
}
	
</script>
</html>