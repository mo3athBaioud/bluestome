
<!--
	* 主页面的模板
	* 作者：曾次清


-->
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
	<title><template:get name="title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@ include file="/commons/style.jsp" %>
</head>


<body leftmargin="5" topmargin="5">
<table border="0" cellspacing="0" cellpadding="0" class="outer">


  <tr valign="top"> 
    <td   class="content"> 
      <template:get name="header" />
      <template:get name="content"/>
      <template:get name="footer" />
    </td>
  </tr>
  <!-- 
  <tr align="center"> 
    <td  height="7" ><font class="fontLight">&copy;2007 江西水利厅 | 中兴软件技术(南昌)有限公司
		</font>
	</td>
  </tr>
   -->
</table>
</body>
</html>