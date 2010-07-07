<? session_start();
require_once("conn.php");
$id = 0;
if(isset($_POST['id'])) { $id=$_POST['id']; }else{ if (isset($_GET['id'])) {$id=$_GET['id'];}}						
if(isset($_POST['action'])) { $action=$_POST['action']; }else{ if (isset($_GET['action'])) {$action=$_GET['action'];}}
if(isset($_POST['title'])) { $title=$_POST['title']; }else{ if (isset($_GET['title'])) {$title=$_GET['title'];}}						
if(isset($_POST['ordernum'])) { $ordernum=$_POST['ordernum']; }else{ if (isset($_GET['ordernum'])) {$ordernum=$_GET['ordernum'];}}
if(isset($_POST['status'])) { $status=$_POST['status']; }else{ if (isset($_GET['status'])) {$status=$_GET['status'];}}


#$location = "Location:ads.php?msg=".$action."|".$id;
#header($location);
#exit;

$isupdate = false;
if($action == "delete"){
	$sql = "delete from tbl_ads_e where d_id=".$id;
	$sg = "删除";
	if ($conn->Execute($sql)) {
		$location = "Location:ads.php?msg=".$sg."完成";
		header($location);	
		exit;	
	} else {
		$location = "Location:ads.php?msg=".$sg."失败";
		header($location);	
		exit;		
	}
	
}else{

$udate=date("YmdHis");
$bsname = realpath("../") . "/upload/";
$swf = session_id()."_".$udate."_index.swf";
if ($_FILES['background']['tmp_name'] != "") {						
	if (copy($_FILES['background']['tmp_name'],$bsname.$swf)) {	
		
	} else {
		$swf = "";
	}
} else {
		$swf = "";
}

	$rs1 = $conn->Execute("select count(*) as mcount from tbl_ads_e where d_id=".$id);

	if (intval($rs1->fields["mcount"]) > 0) {

	$sql="update tbl_ads_e set d_ads_title = '$title',".
			"d_ordernum='$ordernum',".
			"d_status='$status',".
			"d_createtime=current_timestamp ";
	if ($swf != "") { 
		$sql = $sql . ",d_ads_file_name='$swf' ";
	}	
	$sql = $sql . "where d_id='".$id."'";
	$url2 = "ads.php";	

	$sg = "modify";
	$isupdate = true;
	} else {

	$rs2 = $conn->Execute("select count(*) as mcount from tbl_ads_e where d_status = 1");
	$ordernum = $rs2->fields["mcount"];
	#首先将原来的记录置为不可用
	$sql1 = "delete from tbl_ads_e";
	$sql="insert into tbl_ads_e (d_ads_title,d_ads_file_name,d_ordernum,d_status,d_createtime) values".
			" ('$title','$swf','$ordernum','$status',current_timestamp)";
	$url2 = "ads.php";		
	$sg = "add";
	}
	if(!$isupdate){
		if($conn->Execute($sql1)){
			if ($conn->Execute($sql)) {
				$location = "Location:ads.php?msg=".$sg." completed!!";
				header($location);	
				exit;	
			} else {
				$location = "Location:ads.php?msg=".$sg." failure!!";
				header($location);	
				exit;		
			}
		}
	}else{
			if ($conn->Execute($sql)) {
				$location = "Location:ads.php?msg=".$sg." completed!!";
				header($location);	
				exit;	
			} else {
				$location = "Location:ads.php?msg=".$sg." failure!!";
				header($location);	
				exit;		
			}
	}
}
?>