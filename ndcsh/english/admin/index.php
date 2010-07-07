<? 

session_start();
if($_SESSION['manager']) {
	if($_SESSION['manager'] != "") {
				$location = "Location:main.php";
					header($location);	
					exit;	
	}
}					
if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if  (isset($_GET['msg'])) {$msg=$_GET['msg'];}}	

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ndc web management</title>
<link href="common.css" rel="stylesheet" type="text/css">
</head>

<body>																									
<table width="844" height="480" border="0" align="center">
  <tr>
    <td width="158" height="110">&nbsp;</td>
    <td width="489">&nbsp;</td>
    <td width="183">&nbsp;</td>
  </tr>
  <tr>
    <td height="242">&nbsp;</td>
    <td align="center" valign="middle">
	<form name="login" action="main.php" method="post">
	<table width="100%" height="188" border="0">
      <tr>
        <td height="76" colspan="3" valign="middle"><img src="images/01_r1_c2.jpg" width="166" height="104" align="absbottom" />&nbsp;&nbsp;<span class="text16">Web Management System</span></td>
        </tr>
      <tr>
        <td width="233" height="22" align="right">ID:</td>
        <td width="154"><label>
          <input type="text" name="id" class="input"/>
        </label></td>
        <td width="88">&nbsp;</td>
      </tr>
      <tr>
        <td height="23" align="right">Password:</td>
        <td><label>
          <input type="password" name="pwd" class="input" />
        </label></td>
        <td>&nbsp;</td>
      </tr>
	  
      <tr>
        <td height="55" align="center" valign="middle">
		<div style="color:#FF3300"><? echo $msg; ?></div>
		</td>
        <td align="right"><div class="button" onclick="document.login.submit();"><img src="images/login.gif" /></div></td>
        <td>&nbsp;</td>
      </tr>	  
    </table>
	</form>
	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
