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
					图片总数：${fn:length(imageList)}
				</td>
				<td>
					<a onclick="javascript:history.go(-1)">返回</a>
				</td>
			</tr>
		</table>
		<c:if test="${not empty imageList}">
			<table align="left" class="list" cellspacing="1" cellpadding="0"
				width="98%">
				<tr>
					<th width="2%">
						ID
					</th>
					<th width="10%">
						图片标题
					</th>
					<th width="20%">
						简介
					</th>
					<th width="5%">
						状态
					</th>
					<th width="5%">
						查看图片
					</th>
					<th width="15%">
						创建时间
					</th>
				</tr>
				<c:forEach items="${imageList}" var="row">
					<tr>
						<td>
							<c:out value="${row.id}" />
						</td>
						<td>
							<c:out value="${row.title}" />
						</td>
						<td>
							<c:out value="${row.intro}" />
						</td>
						<td>
							<c:choose>
								<c:when test="${row.link == 'NED'}">
									<font color="red">需要下载图片地址</font>
								</c:when>
								<c:when test="${row.link == 'FD'}">
									<font color="blue">已获取图片地址</font>
								</c:when>
								<c:otherwise>
									<font color="yellow">状态出错</font>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="${row.httpUrl}">观看</a>
							<c:if test="${row.link == 'FD' }">
								<a href="#">下载图片到本地</a>
							</c:if>
						</td>
						</td>
						<td>
							<c:out value="${row.createtime}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>
