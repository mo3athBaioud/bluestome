<? session_start();
	require_once("conn.php");
if(isset($_POST['f1'])) { $f1=$_POST['f1']; }else{ if (isset($_GET['f1'])) {$f1=$_GET['f1'];}}						
if(isset($_POST['f2'])) { $f2=$_POST['f2']; }else{ if (isset($_GET['f2'])) {$f2=$_GET['f2'];}}						
if(isset($_POST['f3'])) { $f3=$_POST['f3']; }else{ if (isset($_GET['f3'])) {$f3=$_GET['f3'];}}						
if(isset($_POST['f4'])) { $f4=$_POST['f4']; }else{ if (isset($_GET['f4'])) {$f4=$_GET['f4'];}}						
if(isset($_POST['f5'])) { $f5=$_POST['f5']; }else{ if (isset($_GET['f5'])) {$f5=$_GET['f5'];}}						
if(isset($_POST['k1'])) { $k1=$_POST['k1']; }else{ if (isset($_GET['k1'])) {$k1=$_GET['k1'];}}						
$f1=htmlentities($f1, ENT_QUOTES);
$f2=htmlentities($f2, ENT_QUOTES);
$f3=htmlentities($f3, ENT_QUOTES);
$f4=htmlentities($f4, ENT_QUOTES);
$f5=htmlentities($f5, ENT_QUOTES);

$udate = date("YmdHis");
$rs=$conn->Execute("select count(*) as mcount from wdata where mtype = 'g' and stype = 'a'");
if (intval($rs->fields["mcount"]) > 0) {
	$sql="update wdata set f1='$f1',f2='$f2',f3='$f3',f4='$f4',f5='$f5',mtype='g',stype='a' where hotid='".$k1."'";
} else {
$sql="insert into wdata (hotid,f1,f2,f3,f4,f5,mtype,stype) values".
		" ('$udate','$f1','$f2','$f3','$f4','$f5','g','a')";
}

	if ($conn->Execute($sql)) {
		$location = "Location:about.php?msg=更新完成";
		header($location);	
		exit;	
	} else {
		$location = "Location:about.php?msg=更新失败";
		header($location);	
		exit;		
	}	
?>