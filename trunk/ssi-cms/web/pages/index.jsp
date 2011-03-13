<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>测试首页</title>
		<meta content="text/html; charset=UTF-8" http-equiv="content-type">
		<meta http-equiv="cache-control" content="no-cache">
		<style>
			#con { 
				background-color:#c1c1c1}
			#t{width:100px;float:left;clear:both;}
			#con li{display:inline;}
			#perDiv{position:absolute;width:210px;left:800px;height:210px;background-color:;align:center}
		</style>
	</head>

	<body>
		<table>
			<tr>
				<td>
					网站总数：${fn:length(websiteList)}
				</td>
				<td>
					<a onclick="javascript:history.go(-1)">返回</a>
				</td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${not empty websiteList}">
				<table class="list" cellspacing="1" cellpadding="0" width="98%">
					<tr>
						<th width="2%">
							ID
						</th>
						<th width="5%">
							网站名称
						</th>
						<th width="15%">
							操作
						</th>
						<!-- 
						<th width="15%">
							文章
						</th>
						 -->
						<th width="9%">
							创建时间
						</th>
					</tr>
					<c:forEach items="${websiteList}" var="row">
						<tr>
							<td>
								${row.id}
							</td>
							<td>
								${row.name}
							</td>
							<td>
								<!-- 
								<a href="${ctx}/showSubWeb.cgi?id=${row.id}">[有] 
								 -->
								编辑
							</td>
							<!-- 
							<td>
								<a href="${ctx}/showArticle.cgi?id=${row.id}">[有] 
							</td>
								 -->
							<td>
								${row.createtime}
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>
							提示：
						</td>
						<td>
							<c:choose>
								<c:when test="${not empty website}">
									<a href="${ctx}/showUpWeb.cgi?id=${id}">[上一级网站]</a>
									</br>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/showUpWeb.cgi?id=${id}">[上一级网站]</a>
									</br>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					</br>
				</table>
			</c:otherwise>
		</c:choose>
	</body>
</html>
