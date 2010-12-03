<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<html>
	<head>
		<title>SSI首页</title>
	</head>
	<body>
		<table class="list" cellspacing="1" cellpadding="0" width="98%">
			<tr>
				<th width="5%">
					报表名
					<BR>
				</th>
				<th width="5%">
					地址
					<BR>
				</th>
				<th width="5%">
					备注
					<BR>
				</th>
			</tr>
			<tr>
				<td align="center">
				文章报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/article.jsp">地址</a>
				</td>
				<td align="center">
				统计文章的获取总数
				</td>
			</tr>
			<tr>
				<td align="center">
				IT文章报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/articledoc.jsp">地址</a>
				</td>
				<td align="center">
				统计IT文章的获取总数
				</td>
			</tr>
			<tr>
				<td align="center">
				图片报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/image.jsp">地址</a>
				</td>
				<td align="center">
				统计图片的获取总数
				</td>
			</tr>
			<tr>
				<td align="center">
				图片文件报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/picturefile.jsp">地址</a>
				</td>
				<td align="center">
				统计图片文件的获取总数
				</td>
			</tr>
			<!-- 
			<tr>
				<td class="foot" align="left">
					<input type="button" name="button" value=" 返回 " class="button"
						onclick="return history.back();">
				</td>
				<td colspan="6" class="foot" align="right">
					${pageHtml}
				</td>
			</tr>
			 -->
		</table>
	</body>
</html>
