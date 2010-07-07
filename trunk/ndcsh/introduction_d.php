<? header('Content-type: text/html; charset=utf-8');
require_once "conn.php"; 
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$page2 = 1;
if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
$start = ($page2-1)*6;

	$mrs = $conn->Execute("select * from wdata where hotid='".$hotid."'");
$pmrs = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' order by udate desc LIMIT ".$start." ,6");
	$dmrs = $conn->Execute("select * from wdata where mtype = 'a' and stype = 'b'");		
	$mydata = $LANG."data";
	$plmrs = $conn->Execute("select count(*) as mcount from wdata_pic where hotid='".$hotid."'");
	$mcount2 = intval($plmrs->fields["mcount"]);	
$bsname = realpath(".") . "/upload/";	
$order   = array("\r\n", "\n", "\r");
$replace = '<br />';		
?>  
<div id="intro_preview" style=" display:none;position:absolute; background:#eeeeee;border:#666666 1px solid; width:270; height: auto;  align="center">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
							  <tr>
								<td align="right" valign="top" height="20"><span style="cursor:pointer" onclick="javascript:showHideLayer('intro_preview','none');"><img src="images/close.gif"  width="15" height="15" /></span></td>
							  </tr>
							  <tr>
								<td align="center"><div id="intro_content" style="padding-bottom:15px;"></div></td>
							  </tr>
							</table>

							</div>		
<table width="776" border="0" cellpadding="0" cellspacing="0">
  <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
  <tr> 
    <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top" background="upload/<? echo $mrs->fields["background"]; ?>"><table width="100%" height="320" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="320" valign="top"><table width="776" height="39" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="776" height="39">&nbsp;</td>
                    </tr>
                  </table>
                  <table width="776" height="266" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="313" height="39" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="51">&nbsp;</td>
                          </tr>
                        </table>
                        <table width="290" height="159" border="0" cellpadding="0" cellspacing="2">
                          <tr> 
                            <td width="25" height="155">&nbsp;</td>
                            <td width="259" valign="top">
<?	while ($dmrs && !$dmrs->EOF) { 	?>				
					<table width="250" border="0" cellspacing="1" cellpadding="0">
                    <tr> 
                      <td width="24"><img src="images/arrow.gif" width="19" height="5"></td>
                      <td width="223" class="leftmenu"><a href="javascript:openprogram('introduction_d.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content3');"><? echo $dmrs->fields["hotcaption"]; ?></a></td>
                    </tr>
                  </table> 
<? 		$dmrs->moveNext();
	}	
?>		  				  
						</td>
                          </tr>
                        </table></td>
                      <td width="463" valign="top">
					  <table width="463" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					  <td valign="top" height="220">					  
                        <table width="456" border="0" cellspacing="3" cellpadding="0">
                          <tr> 
                            <td valign="top" align="center">
												
							<div style="float:left">
<?	while ($pmrs && !$pmrs->EOF) { 	?>											
						<span style="float:left">	
						<table width="97" height="57" border="0" cellpadding="0" cellspacing="1" bgcolor="FFD700">
                                <tr> 
                                  <td width="95">
					<div style="cursor:pointer" onclick="javascript:display_pic('intro','<? echo $pmrs->fields["picture"]; ?>','<?  
					list($width, $height, $type, $attr) = getimagesize($bsname.$pmrs->fields["picture"]); 
					if ($width>440) {
						echo intval(($height*($width/440))+23); 					
					} else {
						echo intval(($height*(440/$width))+23); 
					}	
					?>');">			  								  
					<img src="upload/<? echo $pmrs->fields["picture"]; ?>" border="0" width="147" height="105">
					</div>
								  </td>
                                </tr>
                              </table>
						</span>	  
<? 		$pmrs->moveNext();
	}	
?>		  				  		
						  </div>							
							</td>
                          </tr>
                        </table>
					</td>
					</tr>
					<tr>
					<td>							
                        <table width="459" border="0" cellspacing="4" cellpadding="0">
                          <tr> 
                            <td width="457" height="20" valign="top">
								<div align="right"> 
                                <p class="mainContext11">
<? if ($mcount2 > 6) { ?>				
							<? if ((intval($page2)-1) > 0 ) { ?>				
								<a href="javascript:openprogram('introduction_d.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2-1; ?>','content3');">
								<font color="#FFFFFF">Previous</font>
								</a> 
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																	
                                 <font color="#FFFFFF">│</font> 
							<? if ((intval($page2)+1) <= ceil($mcount2/6) ) { ?>								 								 
								<a href="javascript:openprogram('introduction_d.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2+1; ?>','content3');">
								 <font color="#FFFFFF">Next</font>
								 </a>
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																									 
<? } ?>								 
								 </p>
                            </div></td>
                          </tr>
                        </table>
						
					  </td>
                    </tr>
                  </table>
					 </td>
                    </tr>
                  </table>				  
				  </td>
              </tr>
            </table>
            <table width="745" height="84" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="43" height="80">&nbsp;</td>
                <td width="696" valign="top" class="mainContext12"><span class="mainContext20"> 
                  </span><span class="mainContext18"><? echo $mrs->fields["hotcaption"]; ?></span><span class="mainContext12"><br>
                  <br>
                  </span>
				  <div style=" height:120px;overflow:auto;position:relative">
				  <? echo str_replace($order, $replace, html_entity_decode($mrs->fields[$mydata], ENT_QUOTES)); ?>
				  </div>
				  </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
