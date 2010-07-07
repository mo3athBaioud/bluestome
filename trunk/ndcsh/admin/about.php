<? session_start();
	require_once "conn.php";
		require_once "fun.php";
$msg="";
if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if (isset($_GET['msg'])) {$msg=$_GET['msg'];}}					
$intro_rs = $conn->Execute("select * from wdata  where mtype = 'g' and stype = 'a'");


?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>關於NDC</title>
</head>

<body>																									
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<? if ($msg != "") { ?>
<script type="text/javascript">
	alert('<? echo $msg; ?>');
</script>
<? } ?>
<form name="mainform1" method="post" action="<? echo $SHOME; ?>about_update1.php" enctype="application/x-www-form-urlencoded">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666"><span class="text16" style="color:#FFFFCC; padding-left:15px;">關於NDC</span></td>
  </tr>
  <tr>
    <td height="230" colspan="2">

	<input type="hidden" name="k1" value="<? echo $intro_rs->fields["hotid"]; ?>" />
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="18" colspan="4" align="right" valign="top"><label></label></td>
        </tr>

      <tr>
        <td height="31" colspan="4" align="center" valign="top">
		<table width="100%" border="0" align="center">
			  
			  <tr>
				<td width="172" height="121" align="right" valign="top"><span class="text14">关于 NDC:&nbsp;</span></td>
				<td colspan="3" valign="top"><textarea id="f1" name="f1" cols="70" rows="6" class="input_t_550" style="display:none"><? echo  html_entity_decode($intro_rs->fields["f1"], ENT_QUOTES); ?></textarea>
				<iframe id="myEditor1" src="sinaedit/editor.htm?id=f1" frameborder="0" scrolling="no"></iframe></td>
				<!--
				-->
			  </tr>
			  <tr>
				<td width="172" height="23" align="right" valign="top"><span class="text14">优势1:&nbsp;</span></td>
				<td colspan="3" valign="top"><input type="text" name="f2" class="input_t_550" value="<? echo  html_entity_decode($intro_rs->fields["f2"], ENT_QUOTES); ?>" /></td>
			  </tr>			  			  
			  <tr>
				<td width="172" height="122" align="right" valign="top"><span class="text14">优势1-說明:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<textarea id="f3" name="f3" cols="70" rows="6" class="input_t_550" style="display:none"><? echo  html_entity_decode($intro_rs->fields["f3"], ENT_QUOTES); ?></textarea>
				<iframe id="myEditor2" src="sinaedit/editor.htm?id=f3" frameborder="0" scrolling="no"></iframe></td>
			  </tr>			  
			  <tr>
				<td width="172" height="20" align="right" valign="top"><span class="text14">优势2:&nbsp;</span></td>
				<td colspan="3" valign="top"><input type="text" name="f4" class="input_t_550" value="<? echo  html_entity_decode($intro_rs->fields["f4"], ENT_QUOTES); ?>" /></td>
			  </tr>			  			  
			  <tr>
				<td width="172" height="54" align="right" valign="top"><span class="text14">优势2-說明:&nbsp;</span></td>
				<td colspan="3" valign="top"><textarea id="f5" name="f5" cols="70" rows="6" class="input_t_550" style="display:none"><? echo  html_entity_decode($intro_rs->fields["f5"], ENT_QUOTES); ?></textarea>
				<iframe id="myEditor3" src="sinaedit/editor.htm?id=f5" frameborder="0" scrolling="no"></iframe></td>
				<!--
				-->
			  </tr>			  			  
			  <tr>
				<td height="31">&nbsp;</td>
				<td width="354">&nbsp;</td>
				<td width="146"><label>
				  <input type="submit" name="Submit" value="更新" class="button_120" />
				</label></td>
				<td width="172">&nbsp;</td>
			  </tr>
        </table>
		<label></label></td>
        </tr>
      <tr>
        <td height="161" colspan="4" valign="top"></td>
        </tr>
      <tr>
        <td width="136">&nbsp;</td>
        <td colspan="3">&nbsp;</td>
      </tr>
    </table>
	
	</td>
  </tr>
</table>
</form>
</body>
</html>
