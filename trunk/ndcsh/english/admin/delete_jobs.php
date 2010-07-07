<? session_start();
	require_once("conn.php");
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['url2'])) { $url2=$_POST['url2']; }else{ if (isset($_GET['url2'])) {$url2=$_GET['url2'];}}						


$bsname = realpath("../") . "/upload/";	

$error="";	
$sql="delete from jobs_e where hotid='".$hotid."'";
	if ($conn->Execute($sql)) {	
	} else {
		$error="移除失败";
	}
	

//	echo $url2;
	if ($error=="") {
		$location = "Location:".$url2."?msg=移除完成";
		header($location);	
		exit;	
	} else {
		$location = "Location:".$url2."?msg=".$error;
		header($location);	
		exit;		
	}	
?>