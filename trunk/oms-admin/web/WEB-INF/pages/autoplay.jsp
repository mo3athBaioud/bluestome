<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${title}</title>
	<%@ include file="/commons/head.jsp" %>
  </head>
  
  <body>
  	<c:forEach items="${list}" var="row" varStatus="status">
 		$status.count：${row.httpUrl}
   	</c:forEach>
  </body>
</html>
