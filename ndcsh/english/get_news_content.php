<?PHP	include("conn.php");	
	
	if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if  (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}			
$page2 = 1;
if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
$start = ($page2-1)*10;
	$sql2 =  "select * from wdata_e where hotid = '" . $hotid . "'";
	$rs2 = $conn->Execute(trim($sql2));  
	$sql =  "select * from wdata_pic_e where hotid = '" . $hotid . "' order by udate desc LIMIT ".$start." ,10";
	

	$rs = $conn->Execute(trim($sql)); 
	


	/* Initial DomDocument */
	$dom = new DOMDocument('1.0');
	$content = $dom->createElement("content");
	$content = $dom->appendChild($content);
	$content->setAttribute("transparency", "false");
	/* -------------------  */

	
    while ($rs && !$rs->EOF) { 
			$page = $dom->createElement("pic");	
			$page = $content->appendChild($page);
			$page->setAttribute("src", $SHOME . "upload/".$rs->fields["picture"] );
			$page->setAttribute("ori_src", $SHOME . "upload/ori_".$rs->fields["picture"] );			
			$page->setAttribute("txt", $rs2->fields["piccaption"] );			
		$rs->moveNext();
	}

			
	echo $dom->saveHTML();
	
	
?>
