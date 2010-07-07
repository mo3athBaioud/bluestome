<? session_start();
	require_once("conn.php");
	require_once("resize_image.php");	
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['url2'])) { $url2=$_POST['url2']; }else{ if (isset($_GET['url2'])) {$url2=$_GET['url2'];}}						
if(isset($_POST['w'])) { $w=$_POST['w']; }else{ if (isset($_GET['w'])) {$w=$_GET['w'];}}						
if(isset($_POST['h'])) { $h=$_POST['h']; }else{ if (isset($_GET['h'])) {$h=$_GET['h'];}}						
if(isset($_POST['ptitle'])) { $ptitle=$_POST['ptitle']; }else{ if(isset($_GET['ptitle'])) {$ptitle=$_GET['ptitle'];}}
if(isset($_POST['pdetail'])) { $detail=$_POST['pdetail']; }else{ if(isset($_GET['pdetail'])) {$detail=$_GET['pdetail'];}}
if(isset($_POST['ordernum'])) { $ordernum=$_POST['ordernum']; }else{ if(isset($_GET['ordernum'])) {$ordernum=$_GET['ordernum'];}}
$tmprs=$conn->Execute("select count(*) as mcount from wdata_pic where hotid='".$hotid."'");
if (intval($tmprs->fields["mcount"]) >=6) {
	$location = "Location:".$url2."?hotid=".$hotid."&msg=Over Limit(6 Picture)";
	header($location);	
	exit;		
}
$bsname = realpath("../") . "/upload/";
$udate = date("YmdHis");
$jp = session_id()."_".$udate.".jpg";
$orijp = "ori_".session_id()."_".$udate.".jpg";
$sjp = "s_".session_id()."_".$udate.".jpg";
	if ($_FILES['pic']['tmp_name'] != "") {						
		if (copy($_FILES['pic']['tmp_name'],$bsname.$jp)) {	
				copy($bsname.$jp,$bsname.$orijp);
				list($width, $height, $type, $attr) = getimagesize($bsname.$jp);
//				if ($width>370) {
					$tmpjp = "tmp_".session_id()."_".$udate.".jpg";				
					$pageh = $height*(370/$width);		
					copy($bsname.$jp,$bsname.$tmpjp);								
					Resize2jpeg($bsname.$jp,$bsname.$tmpjp,370,$pageh,100);		
					copy($bsname.$tmpjp,$bsname.$jp);																																	
					unlink($bsname.$tmpjp);																																		
//				} else if ($height>430) {
//					$tmpjp = "tmp_".session_id()."_".$udate.".jpg";				
//					$pagew = $width*(430/$height);		
//					copy($bsname.$jp,$bsname.$tmpjp);								
//					Resize2jpeg($bsname.$jp,$bsname.$tmpjp,$pagew,430,100);		
//					copy($bsname.$tmpjp,$bsname.$jp);																																	
//					unlink($bsname.$tmpjp);										
//				}					
				$spagew = intval($w);
				$spageh = intval($h);
				if (($spagew>=$width) || ($spageh>=$height)) {
					copy($bsname.$jp,$bsname.$sjp);																		
				} else {
					Resize2jpeg($bsname.$jp,$bsname.$sjp,$spagew,$spageh,100);																						
				}	

		} else {
			$location = "Location:".$url2."?hotid=".$hotid."&msg=上傳失败1";
			header($location);	
			exit;		
		}	
	} else {
			$location = "Location:".$url2."?hotid=".$hotid."&msg=上傳失败2";
		header($location);	
		exit;		
	}	
#$tmprs2=$conn->Execute("select max(ordernum) as mcount from wdata_pic where hotid='".$hotid."'");
#$ordernum = $tmprs2->fields['mcount'];
#$ordernum = intval($ordernum) + 1;
$tmptitle = trim($ptitle);
$tmpdetail = trim($detail);
$sql="insert into wdata_pic (hotid,picture,udate,ordernum,title,detail) values ('$hotid','$jp','$udate','$ordernum','$tmptitle','$tmpdetail')";

	if ($conn->Execute($sql)) {
			$location = "Location:".$url2."?hotid=".$hotid."&msg=upload completed_$ordernum!";
			header($location);	
			exit;		
	} else {
			$location = "Location:".$url2."?hotid=".$hotid."&msg=上傳失败3";
			header($location);	
			exit;		
	}	
?>