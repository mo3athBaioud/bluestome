<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/session.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>我的工作台2</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/style/index.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ext_icon.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/theme/default/resources/css/ext-all.css" />
		<style type="text/css">
		    html, body {
		        font:normal 12px verdana;
		        margin:0;
		        padding:0;
		        border:0 none;
		        overflow:hidden;
		        height:100%;
		    }
	    </style>
	</head>
	<body>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ext-all.js"></script>
		<script type="text/javascript" src="${ctx}/resource/commonjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/mydesk.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/validation.js"></script>	
		<script type="text/javascript">
		    	var project = '${ctx}';
		    	var uid = '<%=us.getStaff().getId()%>';
		    	var mobile = '<%=us.getStaff().getMobile()%>';
		    	var phone = '<%=us.getStaff().getOfficephone()%>';
		    	var username = '<%=loginName%>';
		</script>
		<div id="mydesk"></div>
		<div id="mydesk-grid"></div>
	</body>
</html>
