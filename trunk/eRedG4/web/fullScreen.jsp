<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
  <title>eRedCIP-东方红软件实验室构件化集成开发平台 V0.1</title>
  <style type="text/css">
  .waitMsg{
    font-family: "宋体";
    font-size: 13px;
  }
  </style>
</head>
<body onload="init();">
<span class="waitMsg">正在跳转,请等待...</span>
</body>
<script language="javascript">
   function init(){
     window.opener = null;
     //window.close();
     //self.close();
     var win = window.open('login.jsp', '_blank',
       'top=0,left=0,location=no,status=yes,menubar=no,toolbar=no,scrollbars=no,resizable=yes,' 
       + 'height=' + (screen.availHeight-30) + ', width=' + screen.availWidth);
     self.close();
	 }
</script>
</html>