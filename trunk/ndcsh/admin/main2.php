<? 
require_once "conn.php";
if(isset($_POST['pwd'])) { $pwd=$_POST['pwd']; }else{ if  (isset($_GET['pwd'])) {$pwd=$_GET['pwd'];}}	
if(isset($_POST['id'])) { $id=$_POST['id']; }else{ if  (isset($_GET['id'])) {$id=$_GET['id'];}}	


if(!$_SESSION['manager']) {
		$urs = $conn->Execute("select * from usrdat where optid='".$id."'");
		if ($urs && !$urs->EOF) {
			if ($pwd == $urs->fields["pwd"]) {	
				$_SESSION['manager'] = $id; 
			} else {
				$location = "Location:index.php?msg=登入失敗!!";
					header($location);	
					exit;	
			}
		} else {
				$location = "Location:index.php?msg=登入失敗!!";
					header($location);	
					exit;	
			
		}	
}

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bookroll.com Web Management System</title>
<link href="common.css" rel="stylesheet" type="text/css">
</head>

<body>																									
<table width="1051" height="633" border="0" cellpadding="0" cellspacing="0" bordercolor="#003366">
  <tr>
    <td height="5"><div style="border:#003366 1px solid;width:175px"><img src="images/s_logo.gif" width="160" height="36" align="absbottom" /></div></td>
    <td width="874" rowspan="2" valign="top">
	  <div id="content">
<? include "member.php"; ?>
	  </div>
    </td>
  </tr>
  <tr>
    <td width="177" height="581" valign="top" bgcolor="#003366"><table width="160" height="607" border="0">
      <tr>
        <td width="1" height="26">&nbsp;</td>
        <td width="144">
			<div class="link">Member Management</div>		</td>
        <td width="10">&nbsp;</td>
      </tr>
      <tr>
        <td height="23">&nbsp;</td>
        <td><div class="link">Books Management</div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="23">&nbsp;</td>
        <td><div class="link">Flag Management</div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="23">&nbsp;</td>
        <td><div class="link">Feature Books</div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="23">&nbsp;</td>
        <td><div class="link">Sponser Management</div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="47">&nbsp;</td>
        <td><div class="link" onclick="javascript:doFuncReturnXmlv2('logout.php','a=1');	">Logout</div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="363">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="19">&nbsp;</td>
        <td><span class="text12_w">Login: admin</span></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
</body>
</html>
