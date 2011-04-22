<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快速入门</title>
<script type="text/javascript" src="${base}/js/jquery-1.4.3.min.js"></script>
<script type="text/javascript">
$(function (){
	$('#loginForm').submit(function (){
		$.post("${base}/auth/login.nut",$(this).serialize(),function (result){
				if (result == true) {
					alert("${msg['auth.login.success']}");
					$('#loginForm').hide();
					$('#logout').show();
				} else {
					alert("${msg['auth.login.fail']}");
				}
			}, "json");
		return false;
	});
	$('#logout').click(function (){
		$.post("${base}/auth/logout.nut",function (){
			alert("${msg['auth.logout.success']}");
			$('#loginForm').show();
			$('#logout').hide();
		});
	});
});
</script>
</head>
<body>
	<form action="#" id="loginForm">
		<label for="auth.username">${msg['auth.username']}</label><input id="auth.username" type="text" name="username"></input>
		<label for="auth.password">${msg['auth.password']}</label><input id="auth.password" type="password" name="password"></input>
		<input type="submit" value="${msg['auth.login']}"></input>
	</form>
	
	<p/><p/><p/>
	<a href="#" id="logout" style="display:none">${msg['auth.logout']}</a>
</body>
</html>