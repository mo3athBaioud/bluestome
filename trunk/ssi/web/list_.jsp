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
						class="list" cellpadding="2" style="width:100%" cellspacing="1"
						pagesize='10' sort="list" partialList="true" size="resultSize">
						<display:column title="网站名称" class="green">
							${row.name}
						</display:column>
						<display:column title="是否包含子站" class="green" >
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
						<display:column title="网站地址" class="green">
							${row.url}
						</display:column>
						<display:column title="创建时间" class="green">
							${row.createtime}
						</display:column>
					</display:table>
				</td>
			</tr>
		</table>
		<table class="list" cellspacing="1" cellpadding="0" width="98%">
			<tr>
				<td colspan="4">
					<p align="center">
						<input type="button" value="返回" onclick="back2index();"
							class="button">
						<input type="button" value="报表" onclick="index();" class="button">
					</p>
				</td>
			</tr>
			<tr>
				<th width="5%">
					网站名称
					<BR>
				</th>
				<th width="5%">
					是否包含子站
					<BR>
				</th>
				<th width="5%">
					网站地址
					<BR>
				</th>
				<th width="5%">
					创建时间
					<BR>
				</th>
			</tr>
			<c:forEach items="${websiteList}" var="row">
				<tr>
					<c:choose>
						<c:when test="${fn:length(row.children) > 0}">
							<td align="center">
								${row.id}_${row.name}
								<br />
							</td>
							<td width="5%">
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
									<!--
								<c:choose>
									<c:when test="${fn:length(sub.children) > 0}">
										<c:forEach items="${sub.children}" var="sub2">
											&nbsp;&nbsp;&nbsp;&nbsp;|--三级菜单:<a href="${sub2.url}"><c:out value="${sub2.name}" /></a>
											<c:if test="${sub2.count > 0}">
												|文章数量:${sub2.count}<br/>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										&nbsp;&nbsp;&nbsp;&nbsp;|--暂无三级菜单<br/>
									</c:otherwise>
								</c:choose>
								 -->
								</c:forEach>
							</td>
							<td align="center">
								${row.url}
							</td>
							<td align="center">
								${row.createtime}
							</td>
						</c:when>
						<c:otherwise>
							<td align="center">
								${row.name}
							</td>
							<td width="5%">
								<font color=red>未包含子站_${row.count}</font>
								<BR>
							</td>
							<td align="center">
								${row.url}
							</td>
							<td align="center">
								${row.createtime}
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">
					<p align="center">
						<input type="button" value="返回" onclick="back2index();"
							class="button">
						<input type="button" value="报表" onclick="index();" class="button">
					</p>
				</td>
			</tr>
		</table>
	</body>
</html>
