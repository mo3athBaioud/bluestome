<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="eRedG4：易道系统集成与应用开发平台 V1.03" showLoading="false">
<eRedG4:import src="/arm/js/login.js"/>
<style type="text/css">
.user {
	background: url(./resource/image/ext/user.gif) no-repeat 2px 2px;
}
.key {
	background: url(./resource/image/ext/key.gif) no-repeat 2px 2px;
}
.key,.user {
	background-color: #FFFFFF;
	padding-left: 20px;
	font-size: 12px;
}
</style>
<eRedG4:body>
<div id="hello-win" class="x-hidden">
<div id="hello-tabs" >
<img border="0" width="450" height="70"
	src="${pageContext.request.contextPath}/resource/image/login_banner.png" />
</div>
</div>
<div id="aboutDiv" class="x-hidden"
	style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
易道系统集成与应用开发平台 V1.03 (eRedG4&reg)<br>
<br>
版权所有 &copy 2010 易道软件实验室(eRedLab&reg) <br>
<br>
<b>技术支持</b><br>
eRedG4①号群(500人):92497522&nbsp;&nbsp;
Email:eredlab@vip.qq.com<br>
<br>
访问项目主页:<a href="http://code.google.com/p/g4-xiongchun/" target="_blank"
	title="点此访问eRedG4资源下载站">http://code.google.com/p/g4-xiongchun/</a></div>
<div id="infoDiv" class="x-hidden" 
	style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
开发帐户：developer&nbsp;&nbsp;密码：111111<br><br>
超级管理员帐户：super&nbsp;&nbsp;密码：111111</div>
</eRedG4:body>
</eRedG4:html>