<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String loginName = "weinan";
	Object obj  = request.getSession().getAttribute("LOGIN_SESSION_NAME");
	if(null != obj){
		loginName = (String)obj;
	}
 %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>GPRS流量包月推荐清单</title>
		<link rel="stylesheet" type="text/css"
			href="/style/index.css" />
		<link href="/style/default/common.css" rel="stylesheet" type="text/css">
		<link href="/style/default/form.css" rel="stylesheet" type="text/css">
		<link href="/style/default/list.css" rel="stylesheet" type="text/css">
		<style type="text/css">
	    html, body {
	        font:normal 12px verdana;
	        margin:0;
	        padding:0;
	        border:0 none;
	        overflow:hidden;
	        height:100%;
	    }
	    p {
	        margin:5px;
	    }
	    td{
			font-family:宋体;
			line-height:17pt;
			font-size:12pt;
	    }
	    </style>
		<link rel="stylesheet" type="text/css"
			href="/resource/theme/default/resources/css/ext-all.css" />
		<script type="text/javascript"
			src="/resource/extjs3.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="/resource/extjs3.1/ext-all.js"></script>
		<script type="text/javascript"
			src="/resource/commonjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="/resource/commonjs/eredg4.js"></script>
		<link rel="stylesheet" type="text/css" href="/resource/css/eredg4.css" />
		<script type="text/javascript" src="/resource/extjs3.1/ux/ux-all.js"></script>
		<link rel="stylesheet" type="text/css"
			href="/resource/extjs3.1/ux/css/ux-all.css" />
		<script type="text/javascript" src="/scripts/common/swfupload.js"></script>
		<script type="text/javascript" src="/scripts/common/UploadDialog.js"></script>
		<script type="text/javascript" src="/scripts/common/RowExpander.js"></script>
		<script type="text/javascript"
			src="/scripts/common/validation.js"></script>
		<script type="text/javascript" src="/scripts/common/examples.js"></script>
		<script type="text/javascript" src="/scripts/report/list.js"></script>
		<script type="text/javascript">
			var name = '全网手机报-彩信推荐清单';
			var loginname = '<%=loginName%>';
			alert(' >> '+loginname);
		</script>
	</head>
	<body>
			<div id="utp"></div>
	</body>
</html>