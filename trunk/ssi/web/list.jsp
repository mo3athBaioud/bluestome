<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<html>
	<head>
		<title>SSI首页</title>
		<script type="text/javascript">
			//返回到数据查询主页
			function back2index(){
				location.href="${ctx}/index.cgi?id=${parentId}";
			}
			
			//返回到数据查询主页
			function index(){
				location.href="${ctx}/index.jsp";
			}
		</script>
	</head>
	<body>
		<table class="list" cellspacing="1" cellpadding="0" width="98%">
			<tr>
				<td>
					<display:table name="websiteList" id="row" requestURI="/index.cgi"
						class="list" style="width:100%"	pagesize='10' partialList="true" size="20">
						<display:column title="网站名称">
							${row.name}
						</display:column>
						<display:column title="是否包含子站" >
							<c:forEach items="${row.children}" var="sub">
								<c:choose>
									<c:when test="${fn:length(sub.children) > 0 }">
										<a href="${ctx}/index.cgi?id=${sub.id}"> ${sub.id}[<c:out
												value="${sub.name}" />] </a>
									</c:when>
									<c:otherwise>
									[<c:out value="${sub.name}" />
										<c:if test="${sub.count > 0}">_<font color="blue">${sub.count}</font>
										</c:if>]
								</c:otherwise>
								</c:choose>
								<br />
							</c:forEach>
						</display:column>
						<display:column title="网站地址">
							${row.url}
						</display:column>
						<display:column title="创建时间">
							${row.createtime}
						</display:column>
					</display:table>
				</td>
			</tr>
		</table>
	</body>
</html>
