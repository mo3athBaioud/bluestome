<? session_start();
	require_once("conn.php");
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['url2'])) { $url2=$_POST['url2']; }else{ if (isset($_GET['url2'])) {$url2=$_GET['url2'];}}						


$bsname = realpath("../") . "/upload/";	

	$rs=$conn->Execute("select * from wdata_pic_e where hotid='".$hotid."'");
$error="";	
$sql="delete from wdata_e where hotid='".$hotid."'";
$sql2="delete from 
_pic where hotid='".$hotid."'";	
	if ($conn->Execute($sql)) {	
		$conn->Execute($sql2);		
	} else {
		$error="移除失败";
	}
	
	if ($error=="") {
		while ($rs && !$rs->EOF) { 
			unlink($bsname.$rs->fields["picture"]);																													
			unlink($bsname."s_".$rs->fields["picture"]);																														
			$rs->moveNext();
		}		
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