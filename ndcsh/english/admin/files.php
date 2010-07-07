<?
if($_FILES['file']['name']<>""){
copy($_FILES['file']['tmp_name'],"../images/".$_FILES['file']['name']);
echo "<script>alert('File updated!!');</script>";

}
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>無標題文件</title>
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {
	color: #990000;
	font-size: 14px;
}
.style3 {
	font-size: 13px;
	font-weight: bold;
}
-->
</style>
<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
</head>
<link href="style.css" type="text/css" rel="stylesheet" />
<body>																									
<div class="style2">檔案上傳修改</div>
<form action="files.php" method="post" enctype="multipart/form-data" name="form1" id="form1">
  <p class="style3">說明：只要是網站所有圖片或下載的檔案皆可在此上傳覆蓋。<br />
    選擇檔案：
    <input type="file" name="file" /> 

  <p>      
    <input type="submit" name="Submit" value="確認上傳" />
  </p>

</form>
</p>
</body>
</html>
