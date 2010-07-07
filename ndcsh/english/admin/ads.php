<? session_start();
	require_once "conn.php";
	require_once "fun.php";
$msg="";
if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if (isset($_GET['msg'])) {$msg=$_GET['msg'];}}						
$num=10;
$id=0;
if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if (isset($_GET['msg'])) {$msg=$_GET['msg'];}}					
#$intro_rs = $conn->Execute("select * from tbl_ads_e where d_ads_title = ".$id);
$intro2 = $conn->Execute("select * from tbl_ads_e order by d_id desc limit 0,".$num);

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>首页活动广告设定</title>
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>

<body>																									
<? if ($msg != "") { ?>
<script type="text/javascript">
	alert('<? echo $msg; ?>');
</script>
<? } ?>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666">
		<span class="text16" style="color:#FFFFCC; padding-left:15px;">首页FLASH</span>
	</td>
  </tr>
  <tr>
    <td height="230" colspan="2">
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="161" colspan="4" valign="top"><table width="100%" height="139" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="44"></td>
            <td align="center">
				<a href="ads_new.php?id=new" target="_self">
				<span class="text16">新增一筆</span>
				</a>
			</td>
            </tr>
			<tr>
            <td></td>
            <td valign="top">
			<table width="90%" border="0" cellpadding="0" cellspacing="0" style="font-size:15px; color:#333333; border:#999999 solid 1px;" align="center">
              <tr>
                <td class="td_a" width="11%" height="29" align="center" bgcolor="#CCCCCC">編號</td>
                <td class="td_a" width="19%" align="center" bgcolor="#CCCCCC">活动主题</td>
                <td class="td_a" width="19%" align="center" bgcolor="#CCCCCC">修改时间</td>
                <td class="td_a" width="12%" align="center" bgcolor="#CCCCCC">活动状态</td>
                <td class="td_a" width="18%" align="center" bgcolor="#CCCCCC">&nbsp;</td>
              </tr>
<?	while ($intro2 && !$intro2->EOF) { 	?>
              <tr>
                <td height="31" class="td_a"><? echo $intro2->fields["d_id"]; ?></td>
                <td class="td_a"><? echo $intro2->fields["d_ads_title"]; ?></td>
                <td class="td_a"><? echo $intro2->fields["d_createtime"]; ?></td>
				 <td class="td_a">
				<?
				if($intro2->fields["d_status"] == 1){
				?>
				<font color="green">正在进行......</font>
				<?
				}else if($intro2->fields["d_status"] == 0) {
				?>
				<font color="red">活动已停止.....</font>
				<?
				}else{
				?>
				<font color="green">未知状态......</font>
				<?
				}
				?>
               </td>
                <td class="td_a" align="center"><a href="ads_edit.php?id=<? echo $intro2->fields["d_id"]; ?>"><img src="images/ic_edit.png" width="13" border="0"/><span class="text12">Edit</span></a>
				<!--
				</td>
                <td class="td_a" align="center">
				-->
                <!--
                <a href="ads_update.php?action=delete&id=<? echo $intro2->fields["d_id"]; ?>"/>
                -->
				<a href="ads_update.php?id=<? echo $intro2->fields["d_id"]; ?>&action=delete">
				<img src="images/cut16.png" width="13" border="0" /><span class="text12">Delete</span></a></td>
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
	</td>
  </tr>
</table>

</body>
</html>
