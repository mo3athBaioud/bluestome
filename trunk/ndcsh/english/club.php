<? 
    require_once "conn.php"; 
	$page2 = 1;
	if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
	$start = ($page2-1)*6;

	$mrs = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'a' order by ordernum,hotid");
	$mydata = $LANG."data";
	$plmrs = $conn->Execute("select count(*) as mcount from wdata_e where mtype = 'b' and stype = 'b' ");
	$mcount2 = intval($plmrs->fields["mcount"]);
?>  
	<table width="776" border="0" cellpadding="0" cellspacing="0">
  <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
  <tr> 
    <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top" background="images/54.jpg"><table width="100%" height="320" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="320" valign="top"><table width="776" height="39" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="776" height="39">&nbsp;</td>
                    </tr>
                  </table>
                  <table width="776" height="407" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="267" height="407" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td height="51">&nbsp;</td>
                          </tr>
                        </table>
                        <table width="253" height="159" border="0" cellpadding="0" cellspacing="2">
                          <tr> 
                            <td width="25" height="155">&nbsp;</td>
                            <td width="222" valign="top">
							<?	
								$dmrs = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid");	
								while ($dmrs && !$dmrs->EOF) { 	?>				

												<table width="216" border="0" cellspacing="1" cellpadding="0">
												<tr> 
												  <td width="24"><img src="images/arrow.gif" width="19" height="5"></td>
												  <td width="189" class="leftmenu">
												  <a href="javascript:openprogram('club_d.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content4');">
												  <? echo $dmrs->fields["hotcaption"]; ?></a></td>
												</tr>
											  </table> 
							<? 		$dmrs->moveNext();
								}
							?>		  				  
							</td>
                          </tr>
                        </table></td>
                      <td width="509" valign="top">
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><img src="images/spacer.gif" width="8" height="8"></td>
                          </tr>
                        </table>
                        <table width="100%" border="0" cellspacing="7" cellpadding="0" >
                          <tr>
							<?
							$rs1 = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid LIMIT ".$start.",".($start+1));
							if($rs1 && !$rs1->EOF){
							?>
                            <td width="55%" align="right" height="118">
							<a href="javascript:openprogram('club_d.php','hotid=<? echo $rs1->fields["hotid"]; ?>','content4');">
								<img src="upload/<? echo $rs1->fields["picture"]; ?>" width="169" height="118" border="0">
							</a>
							</td>
							<?}?>
							<?
							$rs2 = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid LIMIT ".($start+1).",".($start+2));
							if($rs2 && !$rs2->EOF){
							?>
                            <td width="45%" align="left" >
								<a href="javascript:openprogram('club_d.php','hotid=<? echo $rs2->fields["hotid"]; ?>','content4');">
									<img src="upload/<? echo $rs2->fields["picture"]; ?>" width="169" height="118" border="0">
								</a>
							</td>
							<? 
								}else{
							?>
                            <td width="45%" align="right" height="118">
								&nbsp;
							</td>
							<?
								}
							?>
                          </tr>
                        </table>
                        <table width="100%" border="0" cellspacing="7" cellpadding="0" >
                          <tr> 
							<?
							$rs3 = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid LIMIT ".($start+2).",".($start+3));
							if($rs3 && !$rs3->EOF){
							?>
                            <td width="55%" align="right" >
							<a href="javascript:openprogram('club_d.php','hotid=<? echo $rs3->fields["hotid"]; ?>','content4');">
								<img src="upload/<? echo $rs3->fields["picture"]; ?>" width="169" height="118" border="0">
							</a>
							</td>
							<?}else{?>
                            <td width="55%" align="right" height="118">
								&nbsp;<img src="upload/<? echo $rs3->fields["picture"]; ?>" width="169" height="118" border="0">
							</td>

							<?}?>
							<?
							$rs4 = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid LIMIT ".($start+3).",".($start+4));
							if($rs4 && !$rs4->EOF){
							?>
                            <td width="45%" align="left" >
								<a href="javascript:openprogram('club_d.php','hotid=<? echo $rs4->fields["hotid"]; ?>','content4');">
									<img src="upload/<? echo $rs4->fields["picture"]; ?>" width="169" height="118" border="0">
								</a>
							</td>
							<? 
								}else{
							?>
                            <td width="45%" align="left" height="118">
								&nbsp;<img src="upload/<? echo $rs4->fields["picture"]; ?>" width="169" height="118" border="0">
							</td>
							<?}?>
                          </tr>
                        </table>
                        <table width="100%" border="0" cellspacing="7" cellpadding="0" >
                          <tr> 
							<?
							$rs5 = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid LIMIT ".($start+4).",".($start+5));
							if($rs5 && !$rs5->EOF){
							?>
                            <td width="55%" align="right" >
							<a href="javascript:openprogram('club_d.php','hotid=<? echo $rs5->fields["hotid"]; ?>','content4');">
								<img src="upload/<? echo $rs5->fields["picture"]; ?>" width="169" height="118" border="0">
							</a>
							</td>
							<?
							}else{
							?>
                            <td width="55%" align="right" height="118">
								&nbsp;
							</td>
							<?}?>
							<?
							$rs6 = $conn->Execute("select * from wdata_e where mtype = 'b' and stype = 'b' order by ordernum,hotid LIMIT ".($start+5).",".($start+6));
							if($rs6 && !$rs6->EOF){
							?>
                            <td width="45%" align="left" >
								<a href="javascript:openprogram('club_d.php','hotid=<? echo $rs6->fields["hotid"]; ?>','content4');">
									<img src="upload/<? echo $rs6->fields["picture"]; ?>" width="169" height="118" border="0">
								</a>
							</td>
							<? 
								}else{
							?>
                            <td width="45%" align="left" >
								&nbsp;
							</td>
							<?}?>
                          </tr>
                        </table>
						</td>
						<td>
						</td>
                    </tr>
					</table>
						<table width="100%" border="0" cellspacing="7" cellpadding="0" >
                          <tr> 
                            <td width="100%"  height="9">
							<div width="100%" align="right"> 
                                <p class="mainContext11">
									<? if ($mcount2 > 6) { ?>			
										<? if ((intval($page2)-1) > 0 ) { ?>
											<a href="javascript:openprogram('club.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2-1; ?>','content4');">

											<font color="#FFFFFF">Previous</font>
											</a> 
										<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>									
											 <font color="#FFFFFF">│</font> 							
										<? if ((intval($page2)+1) <= ceil($mcount2/6) ) { ?>								 

											<a href="javascript:openprogram('club.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2+1; ?>','content4');">
											 <font color="#FFFFFF">Next</font>
											 </a>
										<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>	 
									<? } ?>								 
								  </p>
                              </div>
							  </td>
                          </tr>
                        </table>
					</td>
              </tr>
            </table>
            
          </td>
        </tr>
      </table></td>
  </tr>
</table>
