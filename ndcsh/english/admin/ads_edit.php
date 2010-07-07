<? session_start();
	require_once "conn.php";
	require_once "fun.php";
$msg="";
$id=0;
if(isset($_POST['id'])) { $id=$_POST['id']; }else{ if (isset($_GET['id'])) {$id=$_GET['id'];}}						
$intro2 = $conn->Execute("select * from tbl_ads_e where d_id = ".$id." limit 0,1");
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>首页活动广告设定</title>
</head>

<body>																									
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666">
		<span class="text16" style="color:#FFFFCC; padding-left:15px;">
		首页FLASH
		</span>
	</td>
  </tr>
  <tr>
    <td height="230" colspan="2">
	<form name="mainform" action="ads_update.php?action=edit" method="POST"  enctype="multipart/form-data">
	<input type="hidden" name="id" value="<? echo $intro2->fields["d_id"]; ?>" />
	<input type="hidden" name="ordernum" value="1" />
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="18" colspan="4" align="right" valign="top"><label></label></td>
        </tr>
      <tr>
        <td height="31" colspan="4" align="center" valign="top">
		<table width="100%" border="0" align="center">
			  <tr>
				<td height="34" align="right" valign="top"><span class="text14">活动主题:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<input name="title" type="text" size="80" value="<? echo $intro2->fields["d_ads_title"] ?>"  class="input_t_550"/>
				</td>
			  </tr>
			  <tr>
				<td height="40" align="right"><span class="text14">活动状态:&nbsp;</span></td>
				<td colspan="3" align="left">
				<select name="status" style="margin:0 0 0 130px;">
					<? if($intro2->fields["d_status"] == 1){ ?>
					<option value="1" selected>正在进行</option>
					<option value="0">活动停止</option>
					<?} else {
					?>
					<option value="1">正在进行</option>
					<option value="0" selected>活动停止</option>
					<?
					}?>
				</select>
				</td>
			  </tr>
			  <tr>
				<td align="right" valign="top"><span class="text14">活动FLUSH:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<input name="background" type="file" class="input_t_550" style="width:540px;" size="80" /><BR />
				</td>
			  </tr>
			  <tr>
				<td>&nbsp;</td>
				<td colspan="3" align="center">
					<div>
				  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="800" height="440">
				  <?if($intro2 && !$intro2->EOF){?>
				  <param name="movie" value="<?echo "/english/upload/".$intro2->fields["d_ads_file_name"];?>">
				  <?}else{?>
				  <param name="movie" value="/index.swf">
				  <?}?>
				  <param name="quality" value="high">
				  <PARAM NAME="wmode" VALUE="transparent">			  								
				  </object>
					<?$intro2->fields["d_ads_file_name"];?>
				  </div>
				</td>
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
        <td height="161" colspan="4" valign="top"></td>
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