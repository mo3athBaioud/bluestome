<? session_start();
	require_once("conn.php");
	
	
if(isset($_POST['f1'])) { $f1=$_POST['f1']; }else{ if (isset($_GET['f1'])) {$f1=$_GET['f1'];}}						
if(isset($_POST['f2'])) { $f2=$_POST['f2']; }else{ if (isset($_GET['f2'])) {$f2=$_GET['f2'];}}						
if(isset($_POST['f3'])) { $f3=$_POST['f3']; }else{ if (isset($_GET['f3'])) {$f3=$_GET['f3'];}}						
if(isset($_POST['f4'])) { $f4=$_POST['f4']; }else{ if (isset($_GET['f4'])) {$f4=$_GET['f4'];}}						
if(isset($_POST['f5'])) { $f5=$_POST['f5']; }else{ if (isset($_GET['f5'])) {$f5=$_GET['f5'];}}						
if(isset($_POST['f6'])) { $f6=$_POST['f6']; }else{ if (isset($_GET['f6'])) {$f6=$_GET['f6'];}}						
if(isset($_POST['f7'])) { $f7=$_POST['f7']; }else{ if (isset($_GET['f7'])) {$f7=$_GET['f7'];}}						

if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
	$f2=htmlentities(stripslashes($f2), ENT_QUOTES);
	$f3=htmlentities(stripslashes($f3), ENT_QUOTES);
	$f4=htmlentities(stripslashes($f4), ENT_QUOTES);		

$udate=date("YmdHis");
//$bsname = realpath("../") . "/upload/";
//$jp = session_id()."_".$udate."_1.jpg";
//$jp2 = session_id()."_".$udate."_2.jpg";
/*
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
*/
$rs1 = $conn->Execute("select count(*) as mcount from jobs where hotid='".$hotid."'");

if (intval($rs1->fields["mcount"]) > 0) {
$sql="update jobs set f1 = '$f1',".
		"f2='$f2',".
		"f3='$f3',".
		"f4='$f4',".
		"f5='$f5',".
		"f6='$f6',".
		"f7='$f7',".										
		"optid='".$_SESSION['manager']."',".
		"udate='$udate' ";
/*		
if ($jp != "") { 
	$sql = $sql . "background='$jp',";
}	
if ($jp2 != "") { 
	$sql = $sql . "picture='$jp2',";
}
*/	
		$sql = $sql . " where hotid='".$hotid."'";
$url2 = "jobs_new.php?hotid=".$hotid;	

$sg = "修改";
} else {

$sql="insert into jobs (hotid,f1,f2,f3,f4,f5,f6,f7,".
		"optid,udate) values".
		" ('$hotid','$f1','$f2','$f3','$f4','$f5','$f6','$f7','".$_SESSION['manager'].	
			"','$udate')";
$url2 = "jobs_new.php?hotid=".$hotid;		
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