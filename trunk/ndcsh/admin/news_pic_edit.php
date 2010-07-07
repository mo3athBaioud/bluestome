<? session_start();
require_once "conn.php";
require_once "fun.php";
$num=4;
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['udate'])) { $udate=$_POST['udate']; }else{ if (isset($_GET['udate'])) {$udate=$_GET['udate'];}}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<LINK href="css.css" type="text/css" rel="stylesheet">
<title>内容</title>
</head>

<body>																									
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666">
	<span class="text16" style="color:#FFFFCC; padding-left:15px;">编辑内容</span></td>
  </tr>
  <tr>
    <td height="230" colspan="2">
	<!-- enctype="application/x-www-form-urlencoded" -->
	<?
	$intro2 = $conn->Execute("select * from wdata_pic where hotid = '".$hotid."' and udate = '".$udate."'");
	?>
	<form name="mainform" action="news_pic_update.php" method="POST"  enctype="multipart/form-data" >
	<input type="hidden" name="hotid" value="<?echo $hotid;?>"/>
	<input type="hidden" name="udate" value="<?echo $udate;?>"/>
	<input type="hidden" name="url2" value="new_pic_edit.php"/>
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="18" colspan="4" align="right" valign="top"><label></label></td>
        </tr>
      <tr>
        <td height="31" colspan="4" align="center" valign="top">
		<table width="100%" border="0" align="center">
			  <tr>
				<td width="180" height="34" align="right" valign="top"><span class="text14">标题:&nbsp;</span></td>
				<td colspan="3" valign="top">
					<input name="title" type="text" size="80" class="input_t_550" value="<? echo  $intro2->fields["title"]; ?>" />
				</td>
			  </tr>
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">内容:&nbsp;</span></td>
				<td colspan="3" valign="top">
					<textarea id="detail" name="detail" class="input_t_550" cols="40" rows="10"  style="display:none"><? echo trim($intro2->fields["detail"]);?></textarea>
					<iframe id="myEditor" src="sinaedit/editor.htm?id=detail" class="input_t_550"  frameborder="0" scrolling="no"></iframe>
					<!--
					<iframe ID="Editor" name="Editor" src="HtmlEditor/index.html?ID=detail" frameBorder="0" marginHeight="0" marginWidth="0" scrolling="No" class="input_t_550"></iframe>
					-->
				</td>
			  </tr>
			  <!--
			  -->
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">栏目:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<select class="input_t_550" disabled="true">
					<?
					$ordernm = $intro2->fields["ordernum"];
					if(intval($ordernm) == 1){
					?>
						<option value="1" selected>Fashion|时尚公告</option>
					<?
					}
					if(intval($ordernm) == 2){
					?>
						<option value="2" selected>People|走近人物</option>
					<?
					}
					if(intval($ordernm) == 3){
					?>
						<option value="3" selected>Features|专题策划</option>
					<?
					}
					if(intval($ordernm) == 4){
					?>
						<option value="4" selected>Life|品位生活</option>
					<?
					}
					if(intval($ordernm) == 5){
					?>
						<option value="5" selected>Connoisseur|鉴赏九龙山</option>
					<?
					}
					if(intval($ordernm) == 6){
					?>
						<option value="6" selected>Cover Story|封面故事</option>
					<?
					}
					?>
				</select>
				<input type="hidden" name="ordernum" value="<?echo trim($ordernm);?>"/>
				</td>
			  </tr>
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">缩略图:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="background" type="file" class="input_t_550" style="width:540px;" size="80"/>
			  </tr>
			  <tr>
				<td height="31">&nbsp;</td>
				<td width="342">&nbsp;</td>
				<td width="146">
					<label>
					  <input type="submit" name="Submit" value="修改" />
					  <input type="button" name="button" value="返回" onclick="javascript:location='new_new.php?hotid=<?echo $hotid; ?>#news-pic-upload';"/>
					</label>
				</td>
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
