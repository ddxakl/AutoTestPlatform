<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="mainBody">
	<form id="addInfosForm" action="">
		<div>
			<label>计划名称 <span class="text-fail">＊</span></label> <input>
		</div>
		<div>
			<label>执行人<span class="text-fail">＊</span></label> <input>
		</div>
		<div>
			<label>AB版本 <span class="text-fail">＊</span></label> <input>
		</div>
		<!-- <div>
			<label>脚本类型 <span class="text-fail">＊</span></label> <input>
		</div> -->
		<div>
			<label>描述信息<span class="text-fail">＊</span></label> <input>
		</div>
	</form>

</div>
<div class="footer">
	<div>
		<div class="breadLine"
			style="margin-top: 5px; margin-bottom: 25px; border: 0.5px solid #999;"></div>
		<button class="btn btn-primary nextStep" style="background: #1081de"
			onclick="switchPages('pickTerminal',1)">上一步</button>
		<button class="btn btn-primary nextStep" style="background: #1081de"
			onclick="switchPages('submit',3)">提交测试</button>
	</div>
</div>