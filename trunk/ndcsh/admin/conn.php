<?php
session_start();
$LANG="c";

$SHOME = "http://www.ndcsh.com/admin/";
include("adodb/adodb.inc.php");
$conn = NewADOConnection('mysql');		

# load code common to ADOdb
# create a connection

if (!$conn->Connect('unixe2-g1.xinnetdns.com','web2598337','i3b2z8h3','db_ndcsh_com')) {
	echo "connect error";
}


$conn->Execute("SET NAMES 'utf8'");
//$conn = NewADOConnection("mysql://bradmin:bradmin@localhost/br?persist");
//$conn = &ADONewConnection('mysql'); 
//$conn->Connect('localhost', 'bradmin', 'bradmin', 'br'); 

// MSSQL Connect method

//$conn    = new COM("ADODB.Connection", NULL, CP_UTF8) or die("Cannot start ADO"); 
//$conn->Open("Driver={SQL Server};Server={camc-2};Database=br;UID=civildba;PWD=civildba;"); 
//$conn = odbc_connect( 'bookroll' , 'misdba', 'misdba' );

$HOMEPAGE = $SHOME . "index.php";

?>