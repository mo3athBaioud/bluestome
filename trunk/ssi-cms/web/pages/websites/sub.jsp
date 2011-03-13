<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>

	<body>
		<table class="list" cellspacing="1" cellpadding="0" width="98%">
			<tr>
				<th width="9%">
					网站名称
				</th>
				<th width="9%">
					网站分类
				</th>
			</tr>
			<c:forEach items="${websiteList}" var="row">
				<tr>
					<td>
						<c:out value="${row.name}" />
					</td>
					<td>
						<c:choose>
							<c:when test="${not empty row.children}">
								<a href="${ctx}/website/sub.cgi?id=${row.id}" target="mainFrame"><c:out
										value="${row.url}" />
								</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/pages/articles/article.jsp?id=${row.id}" target="mainFrame"><c:out value="${row.url}" /></a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
