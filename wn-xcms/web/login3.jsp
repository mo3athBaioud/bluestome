<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>渭南移动终端营销系统--登录</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/login/login.css" />
		<link rel=stylesheet type=text/css href="${ctx}/login/tips.css">
		<style type="text/css">
			.user {
				background: url(${ctx}/images/user.gif) no-repeat 2px 2px;
			}
			.key {
				background: url(${ctx}/images/key.gif) no-repeat 2px 2px;
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
		<link rel="stylesheet" type="text/css" href="${ctx}/style/index.css" />
		<link rel="stylesheet" type="text/css"
			href="${ctx}/resource/css/ext_icon.css" />
		<link rel="stylesheet" type="text/css"
			href="${ctx}/resource/theme/default/resources/css/ext-all.css" />
		<script type="text/javascript"
			src="${ctx}/resource/extjs3.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ext-all.js"></script>
		<script type="text/javascript"
			src="${ctx}/resource/commonjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/login.js"></script>
		<script type="text/javascript">
			var project = '${ctx}';
			function formSubmit(){
			    if(typeof(g_documentComplete) == "undefined" || g_documentComplete == 0)
			    {
			        return false;
			    }
			    if(checkReLoginEx()) return  false;
			    var usrname = document.getElementById("form1").username.value;
			    var usrpsw = document.getElementById("form1").password.value;
			    var isValidSign = true;
			    
			    
			    
			    if(usrname == "")
			    {
			        showTips("请输入用户名,否则无法登录",0);
			        document.getElementById("form1").svpn_name.focus();
			        isValidSign = false;
			    }
			    if(mbStringLength(usrname) > 72)
			    {
			        showTips(" 不合法的用户名,用户名不能超过72个字节 ",0);
			        document.getElementById("form1").svpn_name.focus();
			        isValidSign = false;
			    }
			    if(isValidSign==true){
			
			    }
			    return isValidSign;
			}
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
											<img border="0" width="450" height="70"
												src="${ctx}/resource/image/weinan_login_log.png" />
										</div>
									</div>
									<div id="aboutDiv" class="x-hidden"
										style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
										渭南移动终端营销系统
										<br>
										<br>
										版权所有 &copy 2011 西安泰讯信息技术有限公司
										<br>
										<br>
										<!-- 
										客服联系方式：
										029-84336335
										<br>
										<br>
										 -->
									</div>
									<!-- 
									<ul class="logon-intcont-user" style="top:140px;left:390px">
										<li>
											用户名：
											<input class="intext1" id="username" name="username"
												type="text">
										<li>
											密&nbsp;&nbsp;&nbsp;码：
											<input id="password" name="password" type="password"
												class="intext1" style="width:130px">
										</li>
										<li class="btn-li">
											<input name="submit1" type="submit" value="登陆"
												class="btn-b-log">
											&nbsp;

											<input type="reset" value="重置" class="btn-b-log" tabindex="5">
										</li>
										<li class="text-red">
											<div id="TipsBox" class="tips">
												<div class="tips-head" style="display:none">
													<input id="btnCloseTips" name="close" type="button"
														value="&nbsp;" class="btnClose"
														onmouseover="this.className='btnCloseHover'"
														onMouseOut="this.className='btnClose'"
														onMouseDown="this.className='btnCloseActive'"
														onClick="hideTips();" />
													<img id="loader" src="../com/images/ajax_loader.gif"
														align="absmiddle" />
												</div>
												<div class="tips-body">
												</div>
											</div>
										</li>
									</ul>
									 -->
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


