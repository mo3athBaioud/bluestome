<?PHP	
	header('Content-Type: text/xml;charset=utf-8');
	include("conn.php");

	if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if  (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}
	if(isset($_POST['udate'])) { $udate=$_POST['udate']; }else{ if (isset($_GET['udate'])) {$udate=$_GET['udate'];}}

#	echo "hotid:".$hotid;
#	echo "udate:".$udate;

	$sql =  "select * from wdata_pic where hotid = '" . $hotid . "' and udate  = '".$udate."'";

	$rs = $conn->Execute(trim($sql)); 

	#
	$doc=new DOMDocument("1.0","gb2312");  #声明文档类型
#	$doc->formatOutput=true;              #设置可以输出操作

	$root = $doc->createElement("picinfo");
	$root = $doc->appendChild($root);

#	while ($rs && !$rs->EOF){

#	echo $rs->fields["title"];
#	echo html_entity_decode($rs->fields["detail"], ENT_QUOTES);

	$title = $doc->createElement("ptitle");
	$title = $root->appendChild($title);

	$detail = $doc->createElement("pdetail");
	$detail - $root->appendChild($detail);

#	$title->appendChild($doc->createTextNode(iconv("GB2312","UTF-8",html_entity_decode($rs->fields["title"], ENT_QUOTES))));
#	$detail->appendChild($doc->createTextNode(iconv("GB2312","UTF-8",html_entity_decode($rs->fields["detail"], ENT_QUOTES))));

	$title->appendChild($doc->createTextNode($rs->fields["title"]));
#	echo "before replace:".$rs->fields["detail"]."\r\n";
	$table_change = array('&ldquo;'=>'her sister');
	#,'&rdquo;'=>'her sister','&mdash;'=>'her sister'
	$table_change += array('&rdquo;'=>'her sister');
	$table_change += array('&mdash;'=>'her sister');

	$detail_str = strtr($rs->fields["detail"],$table_change);

	echo $detail_str_tmp2;
	$detail->appendChild($doc->createTextNode(html_entity_decode($detail_str, ENT_QUOTES)));

# iconv("GB2312","UTF-8",html_entity_decode($rs->fields["detail"], ENT_QUOTES)); 

#	}

	echo "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n".$doc->saveHTML();

	
?>
