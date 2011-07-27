<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.*" %>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<%
	File file = null;
	File root = null;
	String fileName = request.getParameter("fileName") == null ? "":request.getParameter("fileName");
	String filePath = request.getParameter("filePath") == null ? "":request.getParameter("filePath");
	String del = request.getParameter("del") == null ? "":request.getParameter("del");
	try{

		 if(null != del && !"".equals(del)){
			File tmp = new File(del);
			if(tmp.isFile()){
				tmp.delete();
				out.println(" >> 删除文件["+del+"]成功!<br/>");
			}
		 }	
  	     filePath = request.getRealPath(request.getContextPath());
		 out.println(" >> ["+filePath+"] <br/>");
		 if(null != fileName && "".equals(fileName)){
			 root = new File(filePath);
		 }else{
			 root = new File(filePath+File.separator+fileName);
		 }
		 out.println(" >> 路径:"+root.getAbsolutePath()+"<br/>");
		if(root.isDirectory()){
			File[] files = root.listFiles();
			out.println("--| ..</br>");
			for(File sub:files){
				if(sub.isDirectory()){
					out.println("--| <a href=\"index.jsp?fileName="+sub.getName()+"\">"+sub.getName()+"</a>\t<a href=\"index.jsp?del="+sub.getAbsolutePath()+"\">删除</a></br> ");
				}else{
					out.println("--| "+sub.getName()+"\t<a href=\"index.jsp?del="+sub.getAbsolutePath()+"\">删除</a></br>");
				}
			}
		}else{
			out.println("--|"+root.list().length);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<html>
	<head>
		<title>SSI首页</title>
	</head>
	<body>
		<table class="list" cellspacing="1" cellpadding="0" width="98%">
			<tr>
				<th width="5%">
					报表名
					<BR>
				</th>
				<th width="5%">
					地址
					<BR>
				</th>
				<th width="5%">
					备注
					<BR>
				</th>
			</tr>
			<tr>
				<td align="center">
				文章报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/article.jsp">地址</a>
				</td>
				<td align="center">
				统计文章的获取总数
				</td>
			</tr>
			<tr>
				<td align="center">
				IT文章报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/articledoc.jsp">地址</a>
				</td>
				<td align="center">
				统计IT文章的获取总数
				</td>
			</tr>
			<tr>
				<td align="center">
				图片报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/image.jsp">地址</a>
				</td>
				<td align="center">
				统计图片的获取总数
				</td>
			</tr>
			<tr>
				<td align="center">
				图片文件报表
				</td>
				<td align="center">
					<a href="${ctx}/pages/charts/picturefile.jsp">地址</a>
				</td>
				<td align="center">
				统计图片文件的获取总数
				</td>
			</tr>
			<!-- 
			<tr>
				<td class="foot" align="left">
					<input type="button" name="button" value=" 返回 " class="button"
						onclick="return history.back();">
				</td>
				<td colspan="6" class="foot" align="right">
					${pageHtml}
				</td>
			</tr>
			 -->
		</table>
	</body>
</html>
