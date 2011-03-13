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
					<td>文章总数：${fn:length(articleList)}</td>
					<td><a onclick="javascript:history.go(-1)">返回</a></td>
				</tr>
			</table>
			<c:if test="${not empty articleList}">
			<table align="left" class="list" cellspacing="1" cellpadding="0" width="98%">
				<tr>
					<th width="2%">
						ID
					</th>
					<th width="15%">
						标题
					</th>
					<th width="10%">
						简介
					</th>
					<th width="10%">
						状态
					</th>
					<th width="5%">
						文章创建时间
					</th>
				</tr>
				<c:forEach items="${articleList}" var="row">
					<tr>
						<td>
							${row.id}
						</td>
						<td>
							<c:choose>
								<c:when test="${row.text == 'NED' || row.text == 'NED_WALLCOO' }">
									<font color="red">${row.title}</font>
								</c:when>
								<c:when test="${row.text == 'FD'}">
									<a href="${ctx}/showImage.cgi?id=${row.id}" >${row.title}</a>
								</c:when>
								<c:otherwise>
									<font color="yellow">
										<a href="${ctx}/showImage.cgi?id=${row.id}" >${row.title}</a>
									</font>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${not empty row.intro}">
									${row.intro}
								</c:when>
								<c:otherwise>
									暂无描述
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${row.text == 'NED' || row.text == 'NED_WALLCOO' }">
									<font color="red">需要下载图片地址</font>
								</c:when>
								<c:when test="${row.text == 'FD'}">
									<font color="blue">已获取图片地址</font>
								</c:when>
								<c:otherwise>
									<font color="yellow">状态出错</font>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							${row.createTime}
						</td>
						<!-- 
						<td>
							<c:if test="${not empty row.json}">
								${row.json}
							</c:if>
						</td>
						 -->
					</tr>
				</c:forEach>			
			</c:if>
		</table>
	</body>
</html>
