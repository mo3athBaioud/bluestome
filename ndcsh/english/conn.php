<?
session_start();
$LANG="c";
$SHOME = "http://www.ndcsh.com/english/";
//$SHOME = "http://localhost:8888/NDC/Source/english/";
include("adodb/adodb.inc.php");
$conn = NewADOConnection('mysql');		

# load code common to ADOdb
# create a connection
$conn->Connect('unixe2-g1.xinnetdns.com','web2598337','i3b2z8h3','db_ndcsh_com'); 

$conn->Execute("SET NAMES 'utf8'");

$HOMEPAGE = $SHOME . "index.php";
$order   = array("\r\n", "\n", "\r");
$replace = '<br />';				

?>
	