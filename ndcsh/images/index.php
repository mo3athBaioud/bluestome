<!--
<? 
require_once "conn.php"; 

$mrs = $conn->Execute("select * from tbl_ads where d_status = 1 limit 0,4");
	
$order   = array("\r\n", "\n", "\r");
$replace = '<br />';	
?>  
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>NDC</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--
<link href="demo/style.css" rel="stylesheet" type="text/css">
-->
<style>
#fW_Content {
	MARGIN: 50px 25px; WIDTH: 776px; DISPLAY: inline; FLOAT: left; HEIGHT: 440px
}
#fW_Content IMG {
	WIDTH: 776px; HEIGHT: 460px
}
A:link {
	COLOR: #666666;
	TEXT-DECORATION: none;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
A:visited {
	COLOR: #666666;
	TEXT-DECORATION: none;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
A:active {
	COLOR: #666666;
	TEXT-DECORATION: none;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
A:hover {
	COLOR: #D20000;
	TEXT-DECORATION: underline;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
</style>
<SCRIPT type=text/javascript 
src="js/jquery.js"></SCRIPT>

<SCRIPT type=text/javascript 
src="js/accordion.js"></SCRIPT>

<SCRIPT type=text/javascript 
src="js/cycle.js"></SCRIPT>
<!-- jQuery Scripts //-->
<SCRIPT type=text/javascript>
	<!--
	// Cycle
	$(function() {
		$('#fW_Content').cycle({
		timeout:4000,
		fx:    'fade',
		pager: '#fW_Controls'
		});
	});
	//-->
	</SCRIPT>
</head>
<body bgcolor="#FFFFFF">
<TABLE class=bg2 border=0 cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width=776px align=center>
        <TBODY>
        <TR>
          <TD>
		  <!--
            <DIV id=fW_Content>
			<?	while ($mrs && !$mrs->EOF) { 	?>
				<a href="#" target="_blank"><img 
				<IMG border=0 alt="<? echo $mrs->fields["d_ads_title"] ?>" 
            src="<? echo "/uploads/".$mrs->fields["d_ads_file_name"]; ?>">
			</a>
			<? 		$mrs->moveNext();
				}	
			?>
         </DIV>
		 -->
		 <div align="center" id="fW_Content"> 
			<a href="#" target="_blank"><img border="0" alt="活动一" src="/images/70.jpg" /></a>
			<a href="#" target="_blank"><img border="0" alt="活动二" src="/images/110.jpg" /></a>
         </div>

		 </TD>
		</TR><tr>
            <td align="center" ><font color="#000000"><a href="/about.php"><font color="#000000">中文</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/english/about.php"><font color="#000000">English</font></a></font></td>
          </tr></TBODY></TABLE></TD></TR>
		</TBODY></TABLE>
</body>
</html>
