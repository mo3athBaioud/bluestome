<? session_start();
	require_once("conn.php");
if(isset($_POST['f1'])) { $f1=$_POST['f1']; }else{ if (isset($_GET['f1'])) {$f1=$_GET['f1'];}}						
if(isset($_POST['f2'])) { $f2=$_POST['f2']; }else{ if (isset($_GET['f2'])) {$f2=$_GET['f2'];}}						
if(isset($_POST['f3'])) { $f3=$_POST['f3']; }else{ if (isset($_GET['f3'])) {$f3=$_GET['f3'];}}						
if(isset($_POST['f4'])) { $f4=$_POST['f4']; }else{ if (isset($_GET['f4'])) {$f4=$_GET['f4'];}}						
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['ordernum'])) { $ordernum=$_POST['ordernum']; }else{ if (isset($_GET['ordernum'])) {$ordernum=$_GET['ordernum'];}}
$f2=htmlentities($f2, ENT_QUOTES);
$udate=date("YmdHis");
$bsname = realpath("../") . "/upload/";
$jp = session_id()."_".$udate."_1.jpg";
$jp2 = session_id()."_".$udate."_2.jpg";

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
		"mtype='d',".
		"stype='b',".
		"udate='$udate',".
		"ordernum='$ordernum',";
if ($jp != "") { 
	$sql = $sql . "background='$jp',";
}	
if ($jp2 != "") { 
	$sql = $sql . "picture='$jp2',";
}	
		$sql = $sql . "subcaption='$f3',piccaption='$f4' where hotid='".$hotid."'";
$url2 = "new_new.php?hotid=".$hotid;	

$sg = "modify";
} else {

$sql="insert into wdata (hotid,hotcaption,".$LANG.
		"data,optid,mtype,stype,udate,background,picture,subcaption,piccaption,ordernum) values".
		" ('$hotid','$f1','$f2','".$_SESSION['manager']."','d','b','$udate','$jp','$jp2','$f3','$f4','$ordernum')";
$url2 = "new_new.php?hotid=".$hotid;		
$sg = "add";
}
	if ($conn->Execute($sql)) {
		$location = "Location:".$url2."&msg=".$sg." success";
		header($location);	
		exit;	
	} else {
		$location = "Location:".$url2."&msg=".$sg." failure";
		header($location);	
		exit;		
	}	
?>