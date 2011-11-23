<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/commons/head.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/module/login.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>斯凯2.0群客后台管理系统--登录</title>
		<style type="text/css">
			.user {
				background: url(${ctx}/images/user.png) no-repeat 2px 2px;
			}
			.key {
				background: url(${ctx}/images/key.png) no-repeat 2px 2px;
			}
			.key,.user {
				background-color: #FFFFFF;
				padding-left: 20px;
				font-size: 12px;
			}
			.acceptIcon {
			    background-image:url(${ctx}/images/accept.png) !important;
			 }
	 		.deleteIcon {
			    background-image:url(${ctx}/images/delete.png) !important;
			 }
			.status_awayIcon {
			    background-image:url(${ctx}/images/status_away.png) !important;
			 }
			.status_busyIcon {
			    background-image:url(${ctx}/images/status_busy.png) !important;
			 }
			.arrow_refreshIcon {
			    background-image:url(${ctx}/images/arrow_refresh.png) !important;
			 }
			 .imageIcon {
			    background-image:url(${ctx}/images/image.png) !important;
			 }
			.tbar_synchronizeIcon {
			    background-image:url(${ctx}/images/tbar_synchronize.png) !important;
			 }
			#header{ background-color:;}
			#p_rand_code,#cert_auth,#TipsBox,#DkeyTips,#ComLayer,#loading{ display:none;}
		</style>
		<script type="text/javascript">
		    	var project = '${ctx}';
				var webId = '${param.id}';
				var colName = '${colName}';
				var values = '${value}';
		</script>
	</head>
	<body class="body-bg-user">
		<form id="form1" name="form1" method="post" action="login_psw.csp"
			onsubmit="return formSubmit();" />
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0" class="bgimg-user">
				<tr>
					<td align="center" valign="middle">
						<table width="812" height="421" border="0" cellpadding="0"
							cellspacing="0"
							style="position:absolute;top:50%;left:50%;margin-top:-250px;margin-left:-406px"
							class="bg-mainimg">
							<tr>
								<td class="login-fix-1" style="position:relative;">
									<div id="hello-win" class="x-hidden">
										<div id="hello-tabs">
											<!-- 
											<img border="0" width="450" height="70"
												src="${ctx}/resource/image/weinan_login_log.png" />
											 -->
											 
										</div>
									</div>
									<div id="aboutDiv" class="x-hidden"
										style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
										斯凯2.0群客后台管理系统
										<br>
										<br>
										版权所有 &copy 2011 杭州斯凯网络科技有限公司
										<br>
										<br>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top" class="loginbg1-user">
									<table border="0" cellpadding="0" cellspacing="0"
										class="login-info">
										<tr>
											<td valign="top" class="text-log">
												<br>
											</td>
											<td valign="top" class="text-log">
												<br>
											</td>
										</tr>
										<tr>
										</tr>
										<tr>
										</tr>
										<tr>
											<td valign="top" class="text-log">
												<font color="#333">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
											</td>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


