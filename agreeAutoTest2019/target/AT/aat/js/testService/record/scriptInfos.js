$(function() {
	document.body.style.zoom = window.screen.width / 1536; //1920分辨率的显示器在125%缩放的情况下是1536px, 此处是根据这个情况进行不同分辨率电脑的页面缩放
	//加载下拉参数 
//	$("#abversion").append("<option value=''></option>");
//	$("#abversion").append("<option value='3.0'>3.0</option>");
//	$("#abversion").append("<option value='4.0'>4.0</option>");
//	$("#abversion").append("<option value='5.0'>5.0</option>");
	if ($("#rporst").val()=="rp") {
		$("#scriptType").val("RP-录制");
	}else if ($("#rporst").val()=="st") {
		$("#scriptType").val("ST-脚本");
	}
});


function createCase() {
	var basePath = $("#basePath").val();
	var caseId = $("#caseId").val();
	var formData = {};
	formData.caseName=$("#addInfosForm #caseName").val();
	formData.caseIdentifier=$("#addInfosForm #caseIdentifier").val();
	formData.belong=$("#addInfosForm #belong").val();
	formData.tradecodes=$("#addInfosForm #tradecodes").val();
	if ($("#rporst").val()=="rp") {
		formData.scriptType="RP";
	}else if ($("#rporst").val()=="st"){
		formData.scriptType="ST";
	}
//	formData.scriptType=$("#addInfosForm #scriptType").val();
	formData.caseDesc=$("#addInfosForm #caseDesc").val();
	formData.abversion = $("#abversion option:checked").text();
	formData.caseId=caseId;
	
	var flag;
	if ($("#caseName").val().trim() != null && $("#caseName").val().trim() != ""){
		if ($("#caseIdentifier").val().trim() != null && $("#caseIdentifier").val().trim() != ""){
			if ($("#belong").val().trim() != null && $("#belong").val().trim() != ""){
				$.ajax({
					type:"post",
					url:basePath+"playback/existByCaseidentifier",
					cache : false,	// 禁用缓存 
			    	async: false, // 同步
			    	data: formData,	// 传入已封装的参数
			    	success:function(data){
			    	 	if (data) {
			    	 		flag = false;
						}else {
							flag = true;
						}
			    	 }
				});
				if (flag) {
					$.ajax({
						type:"post",
						url:basePath+"/Summary/createCase",
						cache : false,	// 禁用缓存 
				    	async: false, // 同步
				    	data: formData,	// 传入已封装的参数
				    	success:function(result){
				    	 	window.location.href = basePath + "testService/record";
				    	 }
					});
				}else {
					$("#caseNameMust").html("");
					$("#caseIdentifierMust").html("");
					$("#belongMust").html("");
					$("#caseIdentifierMust").html("此案例编号已存在！");
				}
			}else {
				$("#caseNameMust").html("");
				$("#caseIdentifierMust").html("");
				$("#belongMust").html("所属项目是必填项！");
			}
		}else {
			$("#caseNameMust").html("");
			$("#belongMust").html("");
			$("#caseIdentifierMust").html("案例编号是必填项！");
	    }	
	}else {
		$("#belongMust").html("");
		$("#caseIdentifierMust").html("");
		$("#caseNameMust").html("案例名称是必填项！");
	}
}