<? session_start();
	require_once "conn.php";
		require_once "fun.php";

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>服务项目设定</title>
</head>
<link href="Style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
a:link {
	text-decoration: none;
	color: #666666;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
	color: #0033FF;
}
a:active {
	text-decoration: none;
}
a {
	font-family: Arial, Helvetica, sans-serif;
}
.function {width:80px;}
-->
</style>

<link href="style.css" rel="stylesheet"  type="text/css">
<script src="default.js"></script>

</head>
<?
function getvalue($sql,$str)
{
$row1=mysql_query($sql);
if ($rs1=mysql_fetch_object($row1)){
return $rs1->$str;
}
}
if($_POST["nid"]<>"" ){ 
	$sql="update csimg_e set fdate='20".date("ymd")."'";
	$sql=$sql." where csid='".$_POST["nid"]."' ";
	mysql_query($sql);
	if($_FILES["file"]["name"]<>""){
	$picname=getvalue("select * from csimg_e where csid='".$_POST["nid"]."'","csimg");
	if($picname=="") {
	$picname=$_SESSION["chungcsid"].date("yms")."news".substr($_FILES["file"]["name"],-4);
	mysql_query("update csimg_e set csimg='$picname' where csid='".$_POST["nid"]."'");
	}
	copy($_FILES["file"]["tmp_name"],"../upload/".$picname);
	}	
}
if($_FILES["file"]["name"]<>"" && $_POST["nid"]==""){
	$subname=substr($_FILES["file"]["name"],-4);
	$picname="csimg".rand(10000).date("ysd").$subname;
	copy($_FILES["file"]["tmp_name"],"../upload/".$picname);
	$csid=getvalue("select max(csid) as c from csimg_e","c")+1;
$sql="insert into csimg_e(csid,csimg,fdate) values('$csid','".$picname."','20".date("ymd")."')";
mysql_query($sql);
}	


if(isset($_GET["del"])){
	$sql="delete from  csimg_e  where csid='".$_GET["del"]."' ";
	mysql_query($sql);
}
?>
<?
if($_GET["msg"]==1) alert("新增成功!!");
if($_GET["msg"]==2) alert("修改成功!!");
?>

<body>																									
<div  class="CobaltFooterTD">
  <div align="center" class="text14">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="2" valign="middle" bgcolor="#666666"><span class="text16" style="color:#FFFFCC; padding-left:15px;">客戶名錄</span></td>
      </tr>
    </table>
  </div>
</div>

<div align="center"></div>
<div align="center">
  <?
if($_GET["update"]<>""){ 
$sql="select * from csimg_e  where csid='".$_GET["update"]."' ";
$rs2=mysql_query($sql);
if($r2=mysql_fetch_object($rs2)){
	$nid=$r2->csid;
	$csimg=$r2->csimg;
}else{
	$nid="";
	$csimg="";
}
}
?>
  <?
 if($_GET["add"]=="1" || $_GET["update"]<>""){
 ?>
   <form action="cslist.php" method="post" enctype="multipart/form-data" name="form1" id="form1">

  <table width="100%">
    <tr>
      <td width="22%" align="left" class="text16">圖片上傳</td>
      <td width="78%" align="left"><input type="file" name="file" />
        <input name="nid" type="hidden" id="nid" value="<? echo $nid; ?>" />
          <br />
          <img src="../upload/<? echo $csimg; ?>" height="50" /></td>
    </tr>
  </table>
  <p>
    <input type="hidden" name="csid" value="<? echo $nid; ?>" />
  </p>
  <p>
    <input type="submit" name="Submit" value="<?
	  if(isset($_GET["update"])<>"") echo "確定修改";
	  if(isset($_GET["update"])=="") echo "確定新增";
	  ?>" />
    / <a href='cslist.php?del=<? echo $nid; ?>' class="function">刪除此筆</a> </p>
</form>  <?
}
?>
  <p><a href="cslist.php?add=1" class="CobaltTextarea">新增一筆客戶名錄</a></p>
  <table width="60%">
    <tr>
      <td width="8%" bgcolor="#CCCCCC" class="CobaltFieldCaptionTD"><div align="center">編號</div></td>
      <td width="63%" bgcolor="#CCCCCC" class="CobaltFieldCaptionTD"><div align="center">縮圖<span class="CobaltDraftDataTD"></span></div></td>
      <td width="29%" bgcolor="#CCCCCC" class="CobaltFieldCaptionTD"><div align="center">管理</div></td>
    </tr>
    <?
if($_GET["querygid"]<>"") $query=$_GET["querygid"];
$sql="select * from csimg_e  order by csid desc";
$r1=mysql_query($sql);
$i=0;
$j=0;
while($rs=mysql_fetch_object($r1)){
	$i++;
	$j++;
	if($j>1){
		$bgcolor="bgcolor='#cccccc'";
		$j=0;
		}else{
		$bgcolor="";
		
	}
	?>
    <tr <? echo $bgcolor; ?>>
      <td><div align="center"><? echo $i; ?></div></td>
      <td align="left"><a href="../upload/<? echo $rs->csimg; ?>" target="_blank"><img src="../upload/<? echo $rs->csimg; ?>" height="50" border="0" /></a></td>
      <td><div align="center"><a href='cslist.php?update=<? echo $rs->csid; ?>' class="CobaltTextarea">修改</a></div></td>
    </tr>
    <?
	}
	?>
  </table>
</div>
</body>
</html>
