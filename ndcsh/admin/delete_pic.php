<? session_start();
	require_once("conn.php");
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['udate'])) { $udate=$_POST['udate']; }else{ if (isset($_GET['udate'])) {$udate=$_GET['udate'];}}						
$bsname = realpath("../") . "/upload/";	
	$rs=$conn->Execute("select * from wdata_pic where hotid='".$hotid."' and udate = '".$udate."'");
	$conn->Execute("delete from wdata_pic where hotid='".$hotid."' and udate = '".$udate."'");
	unlink($bsname.$rs->fields["picture"]);																													
	unlink($bsname."s_".$rs->fields["picture"]);																														
		echo "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" .
			"<response callloop='1' msgloop='1'>\n" .
		"  <call area='pic_area' url2='pic.php' argu2='hotid=".$hotid."'>3</call>\n" ;									
		echo "<message method='2' area=''>刪除圖檔成功</message>\n";											
		echo "</response>";	
?>