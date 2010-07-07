<? require_once "conn.php"; 
	$mrs = $conn->Execute("select * from wdata where mtype = 'a' and stype = 'a'");
	$dmrs = $conn->Execute("select * from wdata where mtype = 'a' and stype = 'b'");	
	$mydata = $LANG."data";
?> 
<table width="776" border="0" cellpadding="0" cellspacing="0">
  <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
  <tr> 
    <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="776" valign="top" background="images/04.jpg">
		    <table width="745" height="61" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="27" height="46" valign="top">&nbsp;</td>
                <td width="712" valign="top">&nbsp; </td>
              </tr>
            </table>
            <table width="179" height="172" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="25" height="168">&nbsp;</td>
                <td width="148" valign="top">
<?	while ($dmrs && !$dmrs->EOF) { 	?>				
					<table width="142" border="0" cellspacing="1" cellpadding="0">
                    <tr> 
                      <td width="24" valign="top"><img src="images/arrow.gif" width="19" height="9"></td>
                      <td width="115" class="leftmenu"><a href="javascript:openprogram('introduction_d.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content3');"><? echo $dmrs->fields["hotcaption"]; ?></a></td>
                    </tr>
                  </table> 
<? 		$dmrs->moveNext();
	}	
?>		  				  
				</td>
              </tr>
            </table>
            <table width="167" height="84" border="0" cellpadding="0" cellspacing="2">
              <tr> 
                <td width="12" height="80">&nbsp;</td>
                <td width="149" valign="top" class="mainContext11"><span class="mainContext20"><? echo $mrs->fields["hotcaption"]; ?></span><span class="mainContext12"><br>
                  </span>
				  <div style="overflow:auto;height:200px;">
					<p style="font-size:11px;line-height:12px;"><? echo html_entity_decode($mrs->fields[$mydata], ENT_QUOTES); ?></p> 
				  </div>
                </td>
              </tr>
            </table> </td>
        </tr>
      </table></td>
  </tr>
</table>

