<? session_start();
	require_once("conn.php");
if(isset($_POST['f1'])) { $f1=$_POST['f1']; }else{ if (isset($_GET['f1'])) {$f1=$_GET['f1'];}}						
if(isset($_POST['f2'])) { $f2=$_POST['f2']; }else{ if (isset($_GET['f2'])) {$f2=$_GET['f2'];}}						
if(isset($_POST['f3'])) { $f3=$_POST['f3']; }else{ if (isset($_GET['f3'])) {$f3=$_GET['f3'];}}						
if(isset($_POST['iorder'])) { $iorder=$_POST['iorder']; }else{ if (isset($_GET['iorder'])) {$iorder=$_GET['iorder'];}}						
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$order   = array("\r\n", "\n", "\r");
$replace = '<br />';
//$f2 = str_replace($order, $replace, $f2);
$f2=htmlentities($f2, ENT_QUOTES);
$udate=date("YmdHis");
$bsname = realpath("../") . "/upload/";
$jp = session_id()."_".$udate."_1.jpg";
$jp2 = session_id()."_".$udate."_2.jpg";
//echo "aaa".$_FILES['background']['tmp_name'];
if ($_FILES['background']['tmp_name'] != "") {						
	if (copy($_FILES['background']['tmp_name'],$bsname.$jp)) {	
		
	} else {
		$jp = "";
	}
} else {
		$jp = "";
}
if ($_FILES['picture']['tmp_name'] != "") {						
	if (copy($_FILES['picture']['tmp_name'],$bsname.$jp2)) {	
		
	} else {
		$jp2 = "";
	}
} else {
		$jp2 = "";
}

$rs1 = $conn->Execute("select count(*) as mcount from wdata where hotid='".$hotid."'");

if (intval($rs1->fields["mcount"]) > 0) {
$sql="update wdata set hotcaption = '$f1',".
		$LANG."data='$f2',".
		"optid='".$_SESSION['manager']."',".
		"mtype='e',".
		"stype='b',".
		"iorder='$iorder',".		
		"udate='$udate',";
if ($jp != "") { 
	$sql = $sql . "background='$jp',";
}	
if ($jp2 != "") { 
	$sql = $sql . "picture='$jp2',";
}	
		$sql = $sql . "subcaption='$f3' where hotid='".$hotid."'";
$url2 = "service.php?hotid=".$hotid;	

$sg = "修改";
} else {

$sql="insert into wdata (hotid,hotcaption,".$LANG.
		"data,optid,mtype,stype,udate,background,picture,subcaption,iorder) values".
		" ('$hotid','$f1','$f2','".$_SESSION['manager']."','e','b','$udate','$jp','$jp2','$f3','$iorder')";
$url2 = "service.php?hotid=".$hotid;		
$sg = "新增";
}

	if ($conn->Execute($sql)) {
		$location = "Location:".$url2."&msg=".$sg."完成";
		header($location);	
		exit;	
	} else {
		$location = "Location:".$url2."&msg=".$sg."失败";
		header($location);	
		exit;		
	}
?>