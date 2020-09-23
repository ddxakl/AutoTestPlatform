<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="mainbody">

	<div style="text-align: center; margin: 100px 0px;">
		<div>一共有100条案例，已发送40条。</div>

		<div class="progress" style="width: 600px; margin: 30px 337px">
			<div class="progress-bar" role="progressbar" aria-valuenow="60"
				aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
				<span class="sr-only">40% 完成</span>
			</div>
		</div>
	</div>
</div>
<div class="footer">
	<div>

		<div class="breadLine"
			style="margin-top: 5px; margin-bottom: 25px; border: 0.5px solid #999;"></div>
		<a href="${basePath}/testService/testPlan"><button
				class="btn btn-primary nextStep" style="background: #1081de">完成</button></a>
	</div>
</div>
