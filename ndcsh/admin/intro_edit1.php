<? session_start();
	require_once("conn.php");
if(isset($_POST['f1'])) { $f1=$_POST['f1']; }else{ if (isset($_GET['f1'])) {$f1=$_GET['f1'];}}						
if(isset($_POST['f2'])) { $f2=$_POST['f2']; }else{ if (isset($_GET['f2'])) {$f2=$_GET['f2'];}}						
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$f2=htmlentities($f2, ENT_QUOTES);
$udate=date("YmdHis");
$bsname = realpath("../") . "/upload/";
$jp = session_id()."_".$udate.".jpg";

if ($_FILES['background']['tmp_name'] != "") {						
	if (copy($_FILES['background']['tmp_name'],$bsname.$jp)) {	
		
	} else {
		$jp = "";
	}
} else {
		$jp = "";
}
$rs1 = $conn->Execute("select count(*) as mcount from wdata where hotid='".$hotid."'");

if (intval($rs1->fields["mcount"]) > 0) {
$sql="update wdata set hotcaption = '$f1',".
		$LANG."data='$f2',".
		"optid='".$_SESSION['manager']."',".
		"mtype='a',".
		"stype='b',".
		"udate='$udate',".
		"background='$jp' where hotid='".$hotid."'";
$url2 = "introduction_new.php?hotid=".$hotid;	

$sg = "修改";
} else {

$sql="insert into wdata (hotid,hotcaption,".$LANG."data,optid,mtype,stype,udate,background) values".
		" ('$hotid','$f1','$f2','".$_SESSION['manager']."','a','b','$udate','$jp')";
$url2 = "introduction_new.php?hotid=".$hotid;		
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