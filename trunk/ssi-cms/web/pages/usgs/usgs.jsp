<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>文章管理</title>
		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/styles/index.css" />
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-all.js"></script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/RowExpander.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/examples.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/models/usgs.js"></script>
		<script type="text/javascript">
		    	var project = '${ctx}';
		</script>
		<!-- 
		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true_or_false&amp;key=ABQIAAAAaDOzVTTlXR2YxtC8KS6VhhT2yXp_ZAY8_ufC3CFXhHIE1NvwkxSCvk-K1OWdOWGWgYXnuQJhZCM-Yg" type="text/javascript">
		</script>
	    <script type="text/javascript" src="${ctx}/scripts/common/Ext.ux.GMapPanel.js"></script>
	     -->
	    <style type="text/css">
	        body .x-panel {
	            margin-bottom:20px;
	        }
	        #button-grid .x-panel-body {
	            border:1px solid #99bbe8;
	            border-top:0 none;
	        }
	        p {
				margin-bottom:15px;
			}
	    </style>
	</head>

	<body>
		<div id="div-usgs"></div>
	</body>
</html>
