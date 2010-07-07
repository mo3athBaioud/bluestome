<? session_start();
	require_once("conn.php");
if(isset($_POST['f1'])) { $f1=$_POST['f1']; }else{ if (isset($_GET['f1'])) {$f1=$_GET['f1'];}}						
if(isset($_POST['f2'])) { $f2=$_POST['f2']; }else{ if (isset($_GET['f2'])) {$f2=$_GET['f2'];}}						
if(isset($_POST['k1'])) { $k1=$_POST['k1']; }else{ if (isset($_GET['k1'])) {$k1=$_GET['k1'];}}						
$f2=htmlentities($f2, ENT_QUOTES);
$udate = date("YmdHis");
$rs=$conn->Execute("select count(*) as mcount from wdata_e where mtype = 'f' and stype = 'a'");
if (intval($rs->fields["mcount"]) > 0) {
	$sql="update wdata_e set hotcaption='".$f1."',".$LANG."data='".$f2."' where hotid='".$k1."'";
} else {
$sql="insert into wdata_e (hotid,hotcaption,".$LANG.
		"data,optid,mtype,stype,udate) values".
		" ('$udate','$f1','$f2','".$_SESSION['manager']."','f','a','$udate')";
}

	if ($conn->Execute($sql)) {
		$location = "Location:contact.php?msg=更新完成";
		header($location);	
		exit;	
	} else {
		$location = "Location:contact.php?msg=更新失败";
		header($location);	
		exit;		
	}	
?>