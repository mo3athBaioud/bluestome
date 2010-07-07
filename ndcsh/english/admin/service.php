<? session_start();
	require_once "conn.php";
		require_once "fun.php";
$msg="";
if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if (isset($_GET['msg'])) {$msg=$_GET['msg'];}}					
$intro_rs = $conn->Execute("select * from wdata_e  where mtype = 'e' and stype = 'a'");

$intro2 = $conn->Execute("select * from wdata_e  where mtype = 'e' and stype = 'b'");

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>服务项目设定</title>
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
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666"><span class="text16" style="color:#FFFFCC; padding-left:15px;">服务项目</span></td>
  </tr>
  <tr>
    <td height="230" colspan="2">
	<form name="mainform" action="service_update1.php" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="k1" value="<? echo $intro_rs->fields["hotid"]; ?>" />
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="18" colspan="4" align="right" valign="top"><label></label></td>
        </tr>
      <tr>
        <td height="31" colspan="4" align="center" valign="top">
		<table width="100%" border="0" align="center">
			  <tr>
				<td width="180" height="34" align="right" valign="top"><span class="text14">主题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="f1" type="text" size="80" value="<? echo $intro_rs->fields["hotcaption"]; ?>"  class="input_t_550"/></td>
			  </tr>
			  <tr>
				<td height="54" align="right" valign="top"><span class="text14">介绍:&nbsp;</span></td>
				<td colspan="3" valign="top"><textarea id="f2" name="f2" cols="70" rows="6" class="input_t_550" style="display:none"><? echo  html_entity_decode($intro_rs->fields["cdata"], ENT_QUOTES); ?></textarea>
				<iframe id="myEditor1" src="sinaedit/editor.htm?id=f2" frameborder="0" scrolling="no"></iframe></td>
			  </tr>
			  <tr>
				<td height="31">&nbsp;</td>
				<td width="342">&nbsp;</td>
				<td width="146"><label>
				  <input type="submit" name="Submit" value="修改" class="button_120" />
				</label></td>
				<td width="172">&nbsp;</td>
			  </tr>
        </table>
		<label></label></td>
        </tr>
      <tr>
        <td height="161" colspan="4" valign="top"><table width="100%" height="139" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="44"></td>
            <td align="center"><a href="service_new.php?id=new" target="_self"><span class="text16">新增一筆</span></a></td>
            </tr>
          <tr>
            <td></td>
            <td valign="top">
			<table width="90%" border="0" cellpadding="0" cellspacing="0" style="font-size:15px; color:#333333; border:#999999 solid 1px;" align="center">
              <tr>
                <td class="td_a" width="16%" height="29" align="center" bgcolor="#CCCCCC">編號</td>
                <td class="td_a" width="29%" align="center" bgcolor="#CCCCCC">名稱</td>
                <td class="td_a" width="19%" align="center" bgcolor="#CCCCCC">修改日期</td>
                <td class="td_a" width="12%" align="center" bgcolor="#CCCCCC">修改人</td>
                <td class="td_a" width="11%" align="center" bgcolor="#CCCCCC">&nbsp;</td>
                <td class="td_a" width="13%" align="center" bgcolor="#CCCCCC">&nbsp;</td>
              </tr>
<?	while ($intro2 && !$intro2->EOF) { 	?>
              <tr>
                <td height="31" class="td_a"><? echo $intro2->fields["hotid"]; ?></td>
                <td class="td_a"><? echo $intro2->fields["hotcaption"]; ?></td>
                <td class="td_a"><? echo formatdatetime($intro2->fields["udate"]); ?></td>
                <td class="td_a"><? echo $intro2->fields["optid"]; ?></td>
                <td class="td_a" align="center"><a href="service_new.php?hotid=<? echo $intro2->fields["hotid"]; ?>"><img src="images/ic_edit.png" width="13" border="0"/><span class="text12">Edit</span></a></td>
                <td class="td_a" align="center"><a href="javascript:main_delete('<? echo $intro2->fields["hotid"]; ?>','service.php');"><img src="images/cut16.png" width="13" border="0" /><span class="text12">Delete</span></a></td>
              </tr>
<? 		$intro2->moveNext();
	}	
?>		  
            </table></td>
          </tr>
        </table></td>
        </tr>
      <tr>
        <td width="136">&nbsp;</td>
        <td colspan="3">&nbsp;</td>
      </tr>
    </table>
	</form>
	</td>
  </tr>
</table>

</body>
</html>
