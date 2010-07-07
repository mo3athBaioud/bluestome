<?
require_once "conn.php"; 

$hotid="";
$bg = 0;
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
if ($hotid == "") { 
	
	$tmprs=$conn->Execute("select * from wdata_e where mtype='e' and stype='a' limit 0,1");
	$hotid = $tmprs->fields["hotid"];
} else {
	$bg = 1;
}

	$mrs = $conn->Execute("select * from wdata_e where hotid='".$hotid."'");
	$dmrs = $conn->Execute("select * from wdata_e where mtype = 'e' and stype = 'b' limit 0,4");		
	$mydata = $LANG."data";
	$order   = array("\r\n", "\n", "\r");
	$replace = '<br />';	
?>   
 <script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
<link href="style.css" rel="stylesheet" type="text/css">

<table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="776" valign="top" background="<? 
						if ($bg == 0) { 
							echo "images/74.jpg";
						} else {
							echo "upload/".$mrs->fields["background"]; 
						}	
							 ?>">
<table width="776" height="309" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="776" valign="top">
<table width="776" height="41" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="776" height="41">&nbsp;</td>
              </tr>
            </table>
            <table width="776" height="46" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="258">&nbsp;</td>
                <td width="40"><div align="center"><span class="mainContext12">
                          <? 	
			  	$tmprs=$conn->Execute("select * from wdata_e where iorder='5' and mtype='e' and stype='b' limit 0,1");
				?>
                          <a href="javascript:openprogram('service.php','hotid=<? echo $tmprs->fields["hotid"]; ?>','content2');"> 
                          <font color="#000000"> 
                          <? echo $tmprs->fields["subcaption"]; ?>
                          </font>				
				</a></span>
				</div></td>
                <td width="315">&nbsp;</td>
                <td width="48" class="mainContext12"> <div align="center"><span class="mainContext12"> 
                    <? 	
			  	$tmprs=$conn->Execute("select * from wdata_e where iorder='4' and mtype='e' and stype='b' limit 0,1");
				?>
                    <a href="javascript:openprogram('service.php','hotid=<? echo $tmprs->fields["hotid"]; ?>','content2');"> 
                    <font color="#000000"> 
                    <? echo $tmprs->fields["subcaption"]; ?>
                    </font></a></span></div></td>
                <td width="128">&nbsp;</td>
              </tr>
            </table>
            <table width="776" height="78" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="776" height="78">&nbsp;</td>
              </tr>
            </table>
            <table width="776" height="96" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="100" height="96">&nbsp;</td>
                <td width="42" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="19">&nbsp;</td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td height="31"><div align="center"><span class="mainContext12">
                          <? 	
			  	$tmprs=$conn->Execute("select * from wdata_e where iorder='6' and mtype='e' and stype='b' limit 0,1");
				?>
                          <a href="javascript:openprogram('service.php','hotid=<? echo $tmprs->fields["hotid"]; ?>','content2');"> 
                          <font color="#000000"> 
                          <? echo $tmprs->fields["subcaption"]; ?>
                          </font></a></span>					  
					  </div></td>
                    </tr>
                  </table></td>
                <td width="88">&nbsp;</td>
                <td width="53" valign="top"> <table width="100%" height="59" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td height="59" class="mainContext12">&nbsp;</td>
                    </tr>
                  </table>
                  <table width="100%" height="42" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td class="mainContext12"> <div align="center"><span class="mainContext12"> 
                          <? 	
			  	$tmprs=$conn->Execute("select * from wdata_e where iorder='1' and mtype='e' and stype='b' limit 0,1");
				?>
                          <a href="javascript:openprogram('service.php','hotid=<? echo $tmprs->fields["hotid"]; ?>','content2');"> 
                          <font color="#000000"> 
                          <? echo $tmprs->fields["subcaption"]; ?>
                          </font></a></span></div></td>
                    </tr>
                  </table></td>
                <td width="182">&nbsp;</td>
                <td width="51" valign="bottom"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td class="mainContext12"> <div align="center"><span class="mainContext12"> 
                          <? 	
			  	$tmprs=$conn->Execute("select * from wdata_e where iorder='2' and mtype='e' and stype='b' limit 0,1");
				?>
                          <a href="javascript:openprogram('service.php','hotid=<? echo $tmprs->fields["hotid"]; ?>','content2');"> 
                          <font color="#000000"> 
                          <? echo $tmprs->fields["subcaption"]; ?>
                          </font></a></span></div></td>
                    </tr>
                  </table></td>
                <td width="130">&nbsp;</td>
                <td width="61" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td class="mainContext12"> <div align="center"><span class="mainContext12"> 
                          <? 	
			  	$tmprs=$conn->Execute("select * from wdata_e where iorder='3' and mtype='e' and stype='b' limit 0,1");
				?>
                          <a href="javascript:openprogram('service.php','hotid=<? echo $tmprs->fields["hotid"]; ?>','content2');"> 
                          <font color="#000000"> 
                          <? echo $tmprs->fields["subcaption"]; ?>
                          </font></a></span></div></td>
                    </tr>
                  </table></td>
                <td width="69">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table> 
      <table width="776" height="186" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top">
<table width="100%" height="8" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td height="8"><img src="images/spacer.gif" width="8" height="8"></td>
              </tr>
            </table>
            <table width="760" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="43" height="73">&nbsp;</td>
                <td width="711" valign="top" class="mainContext12"><span class="mainContext20"><? echo $mrs->fields["hotcaption"]; ?> 
                  </span><span class="mainContext12"><br>
                  <br>
                  </span>
 <div style=" height:135px;overflow:auto;position:relative">				  
<? echo str_replace($order, $replace, html_entity_decode($mrs->fields[$mydata], ENT_QUOTES)); ?>
</div>
</td>
              </tr>
            </table>
          </td>
        </tr>
      </table></td>
  </tr>
</table>
