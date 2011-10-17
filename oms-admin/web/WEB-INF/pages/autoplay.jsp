<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${title}</title>
		<%@ include file="/commons/taglibs.jsp" %>
		<link rel="stylesheet" href="${ctx}/styles/lrtk.css" type="text/css"
			media="screen" />
		<script src="${ctx}/scripts/jquery/jquery.min.js"></script>
		<script src="${ctx}/scripts/jquery/jquery.flexslider-min.js"></script>
		<script type="text/javascript">
		$(window).load(function() {
			$('.flexslider').flexslider();
		});
	</script>
	</head>
</html>
<body style="text-align:center">
	<div id="container">
		<h2>
			${row.title}
		</h2>
		<div class="flexslider">
			<ul class="slides">
				<c:forEach items="${list}" var="row" varStatus="status">
					<li>
						<img src="${ctx}/admin/images!image.cgi?entity.id=${row.id}&entity.articleId=${row.articleId}" />
						<p class="flex-caption">${row.intro}</p>
					</li>
			   	</c:forEach>
			</ul>
		</div>
	</div>
	<div style="clear:both"></div>
</body>
</html>
