<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//返回发出请求的客户机的主机名
	String host=request.getServerName();
	//返回发出请求的客户机的端口号。
	int port =request.getServerPort();
	String ctx = request.getContextPath();
	String hurl = "http://"+host+":"+port+ctx;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${title}</title>
		<%@ include file="/commons/taglibs.jsp" %>
		<link rel="stylesheet" href="${ctx}/styles/lrtk.css" type="text/css"
			media="screen" />
		<script src="${ctx}/scripts/jquery/jquery.min.js"></script>
		<script src="${ctx}/scripts/jquery/jquery.flexslider-min.js"></script>
		<script src="${ctx}/scripts/galleria/galleria-1.2.5.gzjs"></script>
		<script type="text/javascript">
	</script>
	 <c:set var="fpath" scope="page" value="<%=hurl%>" />
	</head>
</html>
<body style="text-align:center">
	<div id="container">
		<h2>
			${row.title}
		</h2>
		<!--
		<div class="flexslider">
			<ul class="slides">
				<c:forEach items="${list}" var="row" varStatus="status">
					<li>
						<img src="${ctx}/images/loading32.gif" src2="${ctx}/admin/images!image.cgi?entity.id=${row.id}&entity.articleId=${row.articleId}" name="LazyloadImg"/>
						<p class="flex-caption">${row.intro}</p>
					</li>
			   	</c:forEach>
			</ul>
		</div>
		begin="0" end="10" step="1"
		-->
		<!-- 
		<ul class="slides">
			<c:forEach items="${list}" var="row" varStatus="status" >
				<li>
					<img src="${ctx}/images/loading32.gif" src2="${ctx}/admin/images!image.cgi?entity.id=${row.id}&entity.articleId=${row.articleId}" name="LazyloadImg"/>
					<p>${row.intro}</p>
					<span>&nbsp;&nbsp;</span>
					<br/>
					<br/>
				</li>
		   	</c:forEach>
		</ul>
		 -->
		 <div id="gallery">
			<c:forEach items="${list}" var="row" varStatus="status" >
				<!-- 
				<img src="${ctx}/images/loading32.gif" src2="${ctx}/admin/images!image.cgi?entity.id=${row.id}&entity.articleId=${row.articleId}" name="LazyloadImg"/>
				 -->
				<img src="${row.imgUrl}" src2="${row.httpUrl}" name="LazyloadImg"/>
		   	</c:forEach>
	     </div>	
		<script src="${ctx}/scripts/util/Lazyload.js"></script>
        <script type="text/javascript">
		<!--
			Galleria.loadTheme('${ctx}/scripts/galleria/themes/classic/galleria.classic.min.js');
			$("#gallery").galleria({
				width: 800,
				height: 600
			});
            var imgList = document.getElementsByName("LazyloadImg");
            lazyload = new Lazyload({ src2: "src2", ImgList: imgList, defaultimage: "${fpath}/images/loading32.gif" });
            lazyload.loaded();
         -->
        </script>
	</div>
	<div style="clear:both"></div>
</body>
</html>
