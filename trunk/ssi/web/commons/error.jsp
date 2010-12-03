<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"%>
<%@ include file="/commons/taglibs.jsp"%>
<% response.setStatus(HttpServletResponse.SC_OK); %>

发生意外错误。。。
该页面待美化。
<li>错误发生的页面：${javax.servlet.error.request_uri}</li>
<li>错误信息：${javax.servlet.error.message}</li>
<li>错误类型：${javax.servlet.error.exception_type}</li>
<li>错误代码：${javax.servlet.error.status_code}</li>