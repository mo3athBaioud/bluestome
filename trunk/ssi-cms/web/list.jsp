<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="conn.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网站列表</title>
  </head>
  
  <body>
  <center>
  <h1>网站列表</h1><hr>
					   
	<table border="0" align="left" width="100%">
	<!-- 
		<tr>
			<td>网站ID</td>
			<td>网站名称</td>
			<td>网站地址</td>
			<td>网站创建时间</td>
		</tr>
	 -->	
		<%-- 设置数据源，并查询表中内容，全部输出--%>
		<sql:query var="result" dataSource="${dbs}" maxRows="10">
			SELECT * FROM tbl_web_site
		</sql:query>
		<c:forEach items="${result.rows}" var="row">
			<tr>
				<!-- 
				<td>${row.d_id }</td>
				 -->
				<%-- 查看留言链接,传递id值--%>
				<td>${row.d_web_name }</td>
				<td><a href="showMsg.jsp?id=${row.d_id }">${row.d_web_url }</a></td>
				<td>${row.d_createtime }</td>
			</tr>
		</c:forEach>
	</table>
	<%-- 添加留言链接--%>
	<!-- 
	<a href="addMsg.jsp">添加留言</a>
	 -->	
	</center>			   
  </body>
</html>
