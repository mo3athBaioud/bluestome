<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>多图类型</title>
	</head>
	<body>
		<table width="520" border="0" , cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<!-- saved from url=(0013)about:internet -->
					<!-- amline script-->
					<script type="text/javascript" src="${ctx}/amline/swfobject.js"></script>
					<!-- this id must be unique! -->
					<div id="flashcontent1">
						<strong>You need to upgrade your Flash Player</strong>
					</div>

					<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${ctx}/amline/amline.swf", "amline1", "360", "300", "8", "#FFFFFF");
		so.addVariable("path", "${ctx}/amline/");
		so.addVariable("settings_file", encodeURIComponent("${ctx}/config/amline_settings1.xml"));
		so.addVariable("chart_data", encodeURIComponent("${data}"));		
//		so.addVariable("data_file", encodeURIComponent("${ctx}/data/data.xml"));		
		so.write("flashcontent1");  // this id must match the div id above
		// ]]>
					</script>
					<!-- end of amline script -->

				</td>
				<td>
					<!-- saved from url=(0013)about:internet -->
					<!-- amline script-->
					<script type="text/javascript" src="${ctx}/amline/swfobject.js"></script>
					<!-- this id must be unique! -->
					<div id="flashcontent2">
						<strong>You need to upgrade your Flash Player</strong>
					</div>

					<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${ctx}/amline/amline.swf", "amline2", "360", "300", "8", "#FFFFFF");
		so.addVariable("path", "${ctx}/amline/");
		so.addVariable("settings_file", encodeURIComponent("${ctx}/config/amline_settings2.xml"));
		so.addVariable("chart_data", encodeURIComponent("${data2}"));		
		so.write("flashcontent2");   // this id must match the div id above
		// ]]>
	</script>
					<!-- end of amline script -->
				</td>
			</tr>
			<tr>
				<td>
					<!-- saved from url=(0013)about:internet -->
					<!-- amline script-->
					<script type="text/javascript" src="${ctx}/amline/swfobject.js"></script>
					<!-- this id must be unique! -->
					<div id="flashcontent3">
						<strong>You need to upgrade your Flash Player</strong>
					</div>

					<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${ctx}/amline/amline.swf", "amline3", "360", "300", "8", "#FFFFFF");
		so.addVariable("path", "${ctx}/amline/");
		so.addVariable("settings_file", encodeURIComponent("${ctx}/config/amline_settings3.xml"));
		so.addVariable("chart_data", encodeURIComponent("${data3}"));		
		so.write("flashcontent3");   // this id must match the div id above
		// ]]>
	</script>
					<!-- end of amline script -->
				</td>
				<td>
					<!-- saved from url=(0013)about:internet -->
					<!-- amline script-->
					<script type="text/javascript" src="${ctx}/amline/swfobject.js"></script>
					<!-- this id must be unique! -->
					<div id="flashcontent4">
						<strong>You need to upgrade your Flash Player</strong>
					</div>

					<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${ctx}/amline/amline.swf", "amline4", "360", "300", "8", "#FFFFFF");
		so.addVariable("path", "${ctx}/amline/");
		so.addVariable("settings_file", encodeURIComponent("${ctx}/config/amline_settings4.xml"));
		so.addVariable("chart_data", encodeURIComponent("${data4}"));		
		so.write("flashcontent4");   // this id must match the div id above
		// ]]>
	</script>
					<!-- end of amline script -->
				</td>
			</tr>
		</table>
	</body>
</html>
