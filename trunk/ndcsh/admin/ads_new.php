<? session_start();
require_once "conn.php";
require_once "fun.php";
$num=4;
$intro2 = $conn->Execute("select * from tbl_ads order by d_id desc limit 0,".$num);
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
	<span class="text16" style="color:#FFFFCC; padding-left:15px;">添加活动广告</span></td>
  </tr>
  <tr>
    <td height="230" colspan="2">
	<!-- enctype="application/x-www-form-urlencoded" -->
	<form name="mainform" action="ads_update.php?action=add" method="POST"  enctype="multipart/form-data" >
	<input type="hidden" name="ordernum" value="1"/>
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="18" colspan="4" align="right" valign="top"><label></label></td>
        </tr>
      <tr>
        <td height="31" colspan="4" align="center" valign="top">
		<table width="100%" border="0" align="center">
			  <tr>
				<td width="180" height="34" align="right" valign="top"><span class="text14">活动主题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="title" type="text" size="80" class="input_t_550"/></td>
			  </tr>
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">活动状态:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<select name="status" class="input_t_550" width="10px">
					<option value="1" selected>正在进行</option>
					<option value="0">活动停止</option>
				</select>
				</td>
			  </tr>
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">活动圖:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="background" type="file" class="input_t_550" style="width:540px;" size="80"/>
			  </tr>
			  <tr>
				<td height="31">&nbsp;</td>
				<td width="342">&nbsp;</td>
				<td width="146"><label>
				  <input type="submit" name="Submit" value="添加" class="button_120" />
				</label></td>
				<td width="172">&nbsp;</td>
			  </tr>
        </table>
		<label></label></td>
        </tr>
    </table>
	</form>
	</td>
  </tr>
</table>

</body>
</html>
