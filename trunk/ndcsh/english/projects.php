<?
require_once "conn.php"; 

$hotid="";
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$page2 = 1;
if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
$start = ($page2-1)*10;
if ($hotid == "") { 
	$tmprs=$conn->Execute("select * from wdata_e where mtype='c' and stype='b' limit 0,1");
	$hotid = $tmprs->fields["hotid"];
}

	$mrs = $conn->Execute("select * from wdata_e where hotid='".$hotid."'");
	$dmrs = $conn->Execute("select * from wdata_e where mtype = 'c' and stype = 'b' order by ordernum,hotid");		
	$mydata = $LANG."data";
	$plmrs = $conn->Execute("select count(*) as mcount from wdata_pic_e where hotid='".$hotid."'");
	$mcount2 = intval($plmrs->fields["mcount"]);	
	$order   = array("\r\n", "\n", "\r");
	$replace = '<br />';
?>   
<table width="776" border="0" cellpadding="0" cellspacing="0">
  <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
  <tr> 
    <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top" background="images/64.jpg"><table width="100%" height="343" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="337" valign="top"><table width="776" height="8" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="776" height="8"><img src="images/spacer.gif" width="8" height="8"></td>
                    </tr>
                  </table>
                  <table width="776" height="298" border="0" cellpadding="0" cellspacing="0">
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
                      <td width="232" class="leftmenu"><a href="javascript:openprogram('projects.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content6','p6');"><? echo $dmrs->fields["hotcaption"]; ?></a></td>
                    </tr>
                  </table> 
<? 		$dmrs->moveNext();
	}	
?>		  				  							

							</td>
                          </tr>
                        </table></td>
                      <td width="472" valign="top">
					  <table width="445" height="310" border="0" cellpadding="0" cellspacing="0">
                          <tr> 
                            <td width="472" valign="top">
<span id="p6" style="position:absolute; display:none;font-family:Arial, Helvetica, sans-serif;font-weight:200;font-size:22px; color:#666666; padding-left:130px"><? echo $mrs->fields["piccaption"]; ?></span>																
							<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="450" height="310">
                                <param name="movie" value="projects.swf?hotid=<? echo $hotid; ?>&shome=<? echo $SHOME."get_content.php"; ?>&page2=<? echo $page2; ?>">
                                <param name="quality" value="high">
								<PARAM NAME=wmode VALUE=transparent>			  								
                                <embed src="projects.swf?hotid=<? echo $hotid; ?>&shome=<? echo $SHOME."get_content.php"; ?>&page2=<? echo $page2; ?>" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="450" height="310" wmode="transparent"></embed></object>					
								</td>
                          </tr>
						 <tr>
						 <td valign="top" height="20">
								<div align="left" class="mainContext11" style="padding-left:20px; padding-bottom:5px; font-size:13px"> 
<? if ($mcount2 > 10) { ?>				
							<? if ((intval($page2)-1) > 0 ) { ?>												
								<a href="javascript:openprogram('projects.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2-1; ?>','content6');">
								<font color="#333333">Previous</font>
								</a> 
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																	
                                 <font color="#FFFFFF">│</font> 
							<? if ((intval($page2)+1) <= ceil($mcount2/10) ) { ?>								 								
								<a href="javascript:openprogram('projects.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2+1; ?>','content6');">
								 <font color="#333333">Next</font>
								 </a>
							<?  } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																									 
<? } ?>								 
								 
                            </div>						 
						 </td>
						 </tr> 
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <table width="761" height="33" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="43" height="29">&nbsp;</td>
                <td width="585" valign="top" class="mainContext12"><span class="mainContext20"><? echo $mrs->fields["hotcaption"]; ?></span><span class="mainContext12"><br>
                  <font color="#000000"><? echo $mrs->fields["subcaption"]; ?></font></span></td>
                <td width="125" valign="top" class="mainContext12"><div align="right"><a href="javascript:openprogram('testimonials.php','a=1','content6');"><img src="images/65.jpg" width="124" height="18" border="0"></a></div></td>
              </tr>
            </table>
            <table width="760" height="33" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="43" height="29">&nbsp;</td>
                <td width="711" valign="top" class="mainContext12">
				  <div style="overflow: auto; height:100px;position:relative;">				
				  <? echo str_replace($order, $replace, html_entity_decode($mrs->fields[$mydata], ENT_QUOTES)); ?>
				</div>
				</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>