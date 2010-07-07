<? 
require_once "conn.php"; 
//if(isset($_POST['csid'])) { $csid=$_POST['csid']; }else{ if (isset($_GET['csid'])) {$csid=$_GET['csid'];}}						
$page2 = 1;
if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
$start = ($page2-1)*1;

	$mrs = $conn->Execute("select * from csimg_e order by csid limit ".$start.",1");
	$plmrs = $conn->Execute("select count(*) as mcount from csimg_e");
	$mcount2 = intval($plmrs->fields["mcount"]);	
?>	

																									 <table width="776" border="0" cellpadding="0" cellspacing="0">
  <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
  <tr> 
    <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top" background="upload/<? echo $mrs->fields["csimg"]; ?>">
		  <table width="100%" height="482" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="78%" rowspan="2">&nbsp;</td>
                <td width="20%" height="442" valign="bottom">&nbsp;</td>
                <td width="2%" rowspan="2">&nbsp;</td>
              </tr>
              <tr>
                <td height="2" align="center" valign="bottom">
					<div id="p5">
							<? if ((intval($page2)-1) > 0 ) { ?>				
								<a href="javascript:openprogram('customer.php','page2=<? echo $page2-1; ?>','content5');">
								<font color="#FFFFFF">Previous</font>								</a> 
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>	
							<? if ($mcount2 > 1) { ?>																
                                 <font color="#FFFFFF">│</font> 
							<? } ?>	 
							<? if ((intval($page2)+1) <= $mcount2 ) { ?>								 								 
								<a href="javascript:openprogram('customer.php','page2=<? echo $page2+1; ?>','content5');">
								 <font color="#FFFFFF">Next</font>					  </a>
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																									 
</div>				
				
				</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
