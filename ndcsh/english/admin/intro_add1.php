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

$sql="insert into wdata_e (hotid,hotcaption,".$LANG."data,optid,mtype,stype,udate,background) values ('$hotid','$f1','$f2','".$_SESSION['manager']."','a','b','$udate','$jp')";

	if ($conn->Execute($sql)) {
	


		$location = "Location:introduction.php?msg=新增完成";
		header($location);	
		exit;	
	} else {
		$location = "Location:introduction.php?msg=新增失败";
		header($location);	
		exit;		
	}	
?>