<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<sql:setDataSource url="jdbc:mysql://localhost:3306/filecollection"
	driver="com.mysql.jdbc.Driver"
	user="root" 
	password="123456"
	var="dbs"
	scope="page" />
