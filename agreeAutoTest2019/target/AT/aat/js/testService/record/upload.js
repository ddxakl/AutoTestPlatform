$(function(){
	$("#next").attr("disabled","disabled");
});

var caseId ;
var basePath ;
var rporst;

function uploadscript() {
	basePath = $("#basePath").val();
	var scriptData = $(".uploadfile")[0].files;
	console.log($(".uploadfile")[0].value);
	console.log(scriptData[0]);
	var reader = new FileReader();//新建一个FileReader
    reader.readAsText(scriptData[0], "UTF-8");//读取文件 
    reader.onload = function(evt){ //读取完文件之后会回来这里
   	var fileString = evt.target.result.toString(); // 读取文件内容
    console.log(fileString);
	//filedata.append("fileList":scriptData[0]);
	
    $.ajax({
		type:"POST",
	 	url: basePath+"Summary/uploadFiles",
	   	//data:scriptData[0],
	    data:{"fileList":fileString,"casetype":$("#casetype").val()},
	    async:true,
	  	dataType:"text",
	  	//contentType:"application/x-www-form-urlencoded",
	   	//contentType:"multipart/form-data",
	   	//contentType:'application/json;charset=utf-8',
	   	//contentType:"application/x-msdownload",
	    success:function(data){	
	 		caseId=data;
			console.log("上传成功");
			$(".progress").after("<br><div style='margin-top: 45px;text-align: center;'>上传完成</div>");
			rporst = "rp";
			$("#next").removeAttr("disabled");
			$("#next").css("background","#1081de");
			$("#uploadfile").attr("disabled","disabled");
			$("#uploadscript").css("background","#8ea6bb");
		}, 
	 	error:function(data){
	 		console.log("上传失败");
	 		$(".progress").after("<br><div style='margin-top: 45px;text-align: center;'>上传失败</div>");
	 		$("#uploadfile").attr("disabled","disabled");
	 		$("#uploadscript").css("background","#8ea6bb");
	 	},
	 	xhr:function() {
            var xhr = $.ajaxSettings.xhr();
            if (xhr.upload) { //检查upload属性是否存在 
            	var i=1;
            	i++;
                //绑定progress事件的回调函数  
				xhr.upload.addEventListener('progress', function(e){
					var loaded = e.loaded;                  //已经上传大小情况 
                    var total = e.total;                      //附件总大小 
                    var percent = Math.floor(100*loaded/total)+"%";     //已经上传的百分比  
                    $("#progress-bar").css("width",percent);
					
				}, false)
            }
            return xhr; //xhr对象返回给jQuery使用
        } 
	});


    }	
}

function createXHR() {
    if (typeof XMLHttpRequest != "undefined") {
        return new XMLHttpRequest();
    } else if (typeof ActiveXObject != "undefined") {
        if (typeof arguments.callee.activeXString != "string") {
            var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"];
            for (var i = 0, len = versions.length; i < len; i++) {
                try {
                    var xhr = new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    return xhr;
                } catch (ex) {
                    //跳过
                }
            }
        }
        return new ActiveXObject(arguments.callee.activeXString);
    } else {
        throw new Error("NO XHR object available.")
    }
}

function nextPage(){
	window.location.href= basePath+"/testService/supplyInfos/"  +caseId + "/" + rporst;
}

function back(){
	window.history.go(-1);
}


