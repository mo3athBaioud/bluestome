<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/commons/head.jsp"%>
		<title>首页</title>
		<script type="text/javascript">
			var project = '${ctx}';
			var mainTabs;
		</script>
		<link href="${pageContext.request.contextPath}/styles/eredg4.css" rel="stylesheet" type="text/css">		
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/other/extTabCloseMenu.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/other/eredg4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/module/admin.js"></script>
	</head>
	<body>
		<!-- 皮肤更换区域 -->
		<div id="themeTreeDiv" class="x-hidden"></div>
		<div id="previewDiv" class="x-hidden">
			<!-- 
				<img src="${ctx}/resource/image/theme/default.jpg" />
			 -->
 		</div>
		<!-- overflow: auto;  -->
		<div id="div.card.0101" style="height: 100%; width: 100%;"></div>

		<style type="text/css">
			#loading-mask {
				Z-INDEX: 20000;
				LEFT: 0px;
				WIDTH: 100%;
				POSITION: absolute;
				TOP: 0px;
				HEIGHT: 100%;
				BACKGROUND-COLOR: white
			}
			
			#loading {
				PADDING-RIGHT: 2px;
				PADDING-LEFT: 2px;
				Z-INDEX: 20001;
				LEFT: 45%;
				PADDING-BOTTOM: 2px;
				PADDING-TOP: 2px;
				POSITION: absolute;
				TOP: 40%;
				HEIGHT: auto
			}
			
			#loading IMG {
				MARGIN-BOTTOM: 5px
			}
			
			#loading .loading-indicator {
				PADDING-RIGHT: 10px;
				PADDING-LEFT: 10px;
				BACKGROUND: white;
				PADDING-BOTTOM: 10px;
				MARGIN: 0px;
				FONT: 12px 宋体, arial, helvetica;
				COLOR: #555;
				PADDING-TOP: 10px;
				HEIGHT: auto;
				TEXT-ALIGN: center
			}
			
			.banner {
				font-family: "宋体";
				font-size: 12px;
				color: 4798D7;
			}
		</style>

		<!--显示loding区域-->

		<DIV id=loading-mask></DIV>

		<DIV id=loading>
			<DIV class=loading-indicator>
				<IMG style="MARGIN-RIGHT: 8px" height=32
					src="${ctx}/images/loading32.gif" width=36 align=absMiddle>
				正在初始化,请稍等...
			</DIV>

		</DIV>

		<div id="north">
			<!-- background="${ctx}/resource/image/banner_background/default.png"  -->
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				height="60"
				>
				<tr>
					<td style="padding-left: 15px">
						<img class="IEPNG" src="${ctx}/resources/images/default/s.gif" />
					</td>
					<td style="padding-right: 5px">
						<table width="100%" border="0" cellpadding="0" cellspacing="3"
							class="banner">
							<tr align="right">
								<td>
									<!-- 
									今天是:2011-04-06 星期三
									 -->
									您好,admin!
									<span id="rTime"><span>
								</td>

							</tr>

							<tr align="right">

								<td>

									<table border="0" cellpadding="0" cellspacing="1">

										<tr>

											<td>
												<div id="themeDiv">
											</td>

											<td>
												&nbsp;
											</td>
											<td>
												<div id="configDiv">
											</td>
											<td>
												&nbsp;
											</td>
											<td>
												<div id="closeDiv">
											</td>

										</tr>

									</table>

								</td>

							</tr>

						</table>

					</td>

				</tr>

			</table>

		</div>

		<div id="south" align="left">
			<table class="banner" width="100%">
				<tr>
					<td width="65%">
						<nobr>
							&nbsp;
							<img class="IEPNG" src="${ctx}/images/comments.png" />
							&nbsp;
							<script type="text/javascript">
								document.write(welcomeword);
							</script>
							admin
						</nobr>
					</td>

					<td width="35%">
						<div align="right">
							<nobr>
								<script type="text/javascript">
									document.write(copyright);
								</script>
							</nobr>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<a href="#" onclick="javascript:endIeStatus();" id="endIeStatus"
			style="display: none;" />
	</body>

</html>
