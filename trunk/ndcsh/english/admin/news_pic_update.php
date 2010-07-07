<?php 
	require_once("html.php");
    session_start();
	require_once("conn.php");
	
	if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}
	if(isset($_POST['udate'])) { $udate=$_POST['udate']; }else{ if (isset($_GET['udate'])) {$udate=$_GET['udate'];}}
	if(isset($_POST['title'])) { $title=$_POST['title']; }else{ if (isset($_GET['title'])) {$title=$_GET['title'];}}
	if(isset($_POST['detail'])) { $detail=$_POST['detail']; }else{ if (isset($_GET['detail'])) {$detail=$_GET['detail'];}}
	if(isset($_POST['ordernum'])) { $ordernum=$_POST['ordernum']; }else{ if (isset($_GET['ordernum'])) {$ordernum=$_GET['ordernum'];}}
	$udate1=date("YmdHis");
	$bsname = realpath("../") . "/upload/";
	$jp = session_id()."_".$udate1."_1.jpg";
	if ($_FILES['background']['tmp_name'] != "") {						
		if (copy($_FILES['background']['tmp_name'],$bsname.$jp)) {	
			
		} else {
			$jp = "";
		}
	} else {
			$jp = "";
	}
	$tmp2 = trans_string($detail);
	$sql="update wdata_pic_e set title = '$title',ordernum='$ordernum',detail='$tmp2' ";
	if ($jp != "") {
		$sql = $sql . ",picture='$jp' ";
	}
	$sql = $sql . "where hotid='".$hotid."' and udate = '".$udate."'";
	$url2 = "new_new.php?hotid=".$hotid;	

	$sg = "修改";
	if ($conn->Execute($sql)) {
		$location = "Location:".$url2."&msg=".$sg."完成#news-pic-upload";
		header($location);	
		exit;	
	} else {
		$location = "Location:".$url2."&msg=".$sg."失败#news-pic-upload";
		header($location);	
		exit;		
	}
?>