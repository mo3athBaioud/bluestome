<? session_start();
	require_once("conn.php");
	require_once("resize_image.php");	
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if(isset($_POST['url2'])) { $url2=$_POST['url2']; }else{ if (isset($_GET['url2'])) {$url2=$_GET['url2'];}}						
if(isset($_POST['w'])) { $w=$_POST['w']; }else{ if (isset($_GET['w'])) {$w=$_GET['w'];}}						
if(isset($_POST['h'])) { $h=$_POST['h']; }else{ if (isset($_GET['h'])) {$h=$_GET['h'];}}						
if(isset($_POST['ptitle'])) { $ptitle=$_POST['ptitle']; }else{ if (isset($_GET['ptitle'])) {$ptitle=$_GET['ptitle'];}}
if(isset($_POST['pdetail'])) { $pdetail=$_POST['pdetail']; }else{ if (isset($_GET['pdetail'])) {$pdetail=$_GET['pdetail'];}}
if(isset($_POST['ordernum'])) { $ordernum=$_POST['ordernum']; }else{ if (isset($_GET['ordernum'])) {$ordernum=$_GET['ordernum'];}}
if(isset($_POST['uudate'])) { $uudate=$_POST['uudate']; }else{ if (isset($_GET['uudate'])) {$uudate=$_GET['uudate'];}}
$bsname = realpath("../") . "/upload/";
$udate = date("YmdHis");
$jp = session_id()."_".$udate.".jpg";
$sjp = "s_".session_id()."_".$udate.".jpg";
	if ($_FILES['pic']['tmp_name'] != "") {						
		if (copy($_FILES['pic']['tmp_name'],$bsname.$jp)) {	

				list($width, $height, $type, $attr) = getimagesize($bsname.$jp);
				if ($width>310) {
					$tmpjp = "tmp_".session_id()."_".$udate.".jpg";				
					$pageh = $height*(310/$width);		
					copy($bsname.$jp,$bsname.$tmpjp);								
					Resize2jpeg($bsname.$jp,$bsname.$tmpjp,310,$pageh,100);		
					copy($bsname.$tmpjp,$bsname.$jp);																																	
					unlink($bsname.$tmpjp);																																		
				} else if ($height>290) {
					$tmpjp = "tmp_".session_id()."_".$udate.".jpg";				
					$pagew = $width*(290/$height);		
					copy($bsname.$jp,$bsname.$tmpjp);								
					Resize2jpeg($bsname.$jp,$bsname.$tmpjp,$pagew,290,100);		
					copy($bsname.$tmpjp,$bsname.$jp);																																	
					unlink($bsname.$tmpjp);										
				}					
				$spagew = intval($w);
				$spageh = intval($h);
				if (($spagew>=$width) || ($spageh>=$height)) {
					copy($bsname.$jp,$bsname.$sjp);																		
				} else {
					Resize2jpeg($bsname.$jp,$bsname.$sjp,$spagew,$spageh,100);																						
				}	

		} else {
#			$location = "Location:".$url2."?hotid=".$hotid."&msg=上傳失败1";
#			header($location);	
#			exit;		
			$jp = "";
		}	
	} else {
#		$location = "Location:".$url2."?hotid=".$hotid."&msg=上傳失败2";
#		header($location);	
#		exit;		
		$jp = "";
	}	
	$csql = "select count(*) as mcount from wdata_pic_e where hotid = '".$hotid."' and udate = '".$uudate."'";
	#and ordernum ='".$ordernum."'
	$crs = $conn->Execute($csql);
	if($crs && !$crs -> EOF && intval($crs->fields["mcount"]) > 0){
		$sql = "update wdata_pic_e set title = 'club_title',detail='club_detail',";
		if($jp != ""){
			$sql = $sql."picture='$jp',";
		}
		$sql = $sql. "ordernum='$ordernum' where hotid = '$hotid' and udate = '$uudate' ";
#		echo "update.sql:".$sql;
		if ($conn->Execute($sql)) {
				$location = "Location:".$url2."?hotid=".$hotid."&msg=modify completed success!!#new-pic-choose";
				header($location);	
				exit;		
		} else {
				$location = "Location:".$url2."?hotid=".$hotid."&msg=modify failure#new-pic-choose";
				header($location);	
				exit;		
		}	

	}else{
		if($jp != ""){
			$sql="insert into wdata_pic_e (hotid,picture,udate,title,detail,ordernum) values ('$hotid','$jp','$udate','$ptitle','$pdetail','$ordernum')";
			if ($conn->Execute($sql)) {
					$location = "Location:".$url2."?hotid=".$hotid."&msg=upload completed success!!#new-pic-choose";
					header($location);	
					exit;		
			} else {
					$location = "Location:".$url2."?hotid=".$hotid."&msg=upload completed failure!!#new-pic-choose";
					header($location);	
					exit;		
			}
		}else{
			$location = "Location:".$url2."?hotid=".$hotid."&msg=upload completed failure [no pic choose ]!!#new-pic-choose";
			header($location);	
			exit;		
		}
	}
?>