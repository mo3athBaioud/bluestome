<?PHP
 require_once "conn.php";
if(isset($_POST['pwd'])) { $pwd=$_POST['pwd']; }else{ if  (isset($_GET['pwd'])) {$pwd=$_GET['pwd'];}}	
if(isset($_POST['id'])) { $id=$_POST['id']; }else{ if  (isset($_GET['id'])) {$id=$_GET['id'];}}	

$sql="CREATE TABLE csimg ('id' INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,'img' VARCHAR( 100 ) NOT NULL ,'fdate' VARCHAR( 8 ) NOT NULL )";
mysql_query($sql);
if(!$_SESSION['manager']) {
		$urs = $conn->Execute("select * from usrdat_e where optid='".$id."'");
		if ($urs && !$urs->EOF) {
			if ($pwd == $urs->fields["pwd"]) {	
				$_SESSION['manager'] = $id; 
			} else {
				$location = "Location:index.php?msg=登入失敗!!";
					header($location);	
					exit;	
			}
		} else {
				$location = "Location:index.php?msg=登入失敗!!";
					header($location);	
					exit;	
			
		}	
}
?>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>ndc web management</title>

</head>
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<frameset rows="*" cols="185,*" framespacing="0" frameborder="no" border="0">
  <frame src="menu.php" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="about.php" name="mainFrame" id="mainFrame" title="mainFrame" />
</frameset>
<noframes><body>																									
</body>
</noframes></html>
