﻿<? header('Content-type: text/html; charset=utf-8');
require_once "conn.php"; 
$hotid="";
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$page2 = 1;
if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
$start = ($page2-1)*6;
if ($hotid == "") { 
	$tmprs=$conn->Execute("select * from wdata where mtype='d' and stype='b' limit 0,1");
	$hotid = $tmprs->fields["hotid"];
}

	$mrs = $conn->Execute("select * from wdata where hotid='".$hotid."'");
//$pmrs = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' order by udate desc LIMIT ".$start." ,6");
	$dmrs = $conn->Execute("select * from wdata where mtype = 'd' and stype = 'b'");		
	$mydata = $LANG."data";
	$plmrs = $conn->Execute("select count(*) as mcount from wdata_pic where hotid='".$hotid."'");
	$mcount2 = intval($plmrs->fields["mcount"]);	
	
	
?>    
<table width="776" border="0" cellpadding="0" cellspacing="0">
  <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
  <tr> 
    <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top" background="images/82.jpg"> 
            <table width="100%" height="445" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="445" valign="top">
<table width="776" height="8" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="776" height="8"><img src="images/spacer.gif" width="8" height="8"></td>
                    </tr>
                  </table>
                  <table width="776" height="429" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="304" height="298" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td height="57">&nbsp;</td>
                          </tr>
                        </table>
                        <table width="292" height="159" border="0" cellpadding="0" cellspacing="2">
                          <tr> 
                            <td width="25" height="155">&nbsp;</td>
                            <td width="261" valign="top">
<?	while ($dmrs && !$dmrs->EOF) { 	?>				
					<table width="258" border="0" cellspacing="1" cellpadding="0">
                    <tr> 
                      <td width="23"><img src="images/arrow.gif" width="19" height="5"></td>
                      <td width="232" class="leftmenu"><a href="javascript:openprogram('news_test.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content7','p7');"><font color="FBD100"><? echo $dmrs->fields["hotcaption"]; ?></font></a></td>
                    </tr>
                  </table> 
<? 		$dmrs->moveNext();
	}	
?>		  				  							
							</td>
                          </tr>
                        </table></td>
                      <td width="472" valign="top">
							<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="470" height="430">
                                <param name="movie" value="news.swf?hotid=<? echo $hotid; ?>&shome=<? echo $SHOME."get_news_content.php"; ?>&page2=<? echo $page2; ?>">
                                <param name="quality" value="high">
								<PARAM NAME=wmode VALUE=transparent>			  								
                                <embed src="news.swf?hotid=<? echo $hotid; ?>&shome=<? echo $SHOME."get_news_content.php"; ?>&page2=<? echo $page2; ?>" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="470" height="430" wmode="transparent"></embed></object>					  
					  </td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <table width="760" height="33" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="43" height="29">&nbsp;</td>
                <td width="711" valign="top" class="mainContext12">
				 <div style="height:45px;overflow:auto;position:relative">								
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
