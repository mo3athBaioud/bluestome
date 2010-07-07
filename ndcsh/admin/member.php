<? require_once "conn.php";
	$rs = $conn->Execute("select * from member order by ldate");
	
?>

	    																									 <table width="100%" border="0">
          <tr>
            <td height="34" valign="top">Member Management </td>
            <td width="16%" align="right">2,000</td>
          </tr>
        </table>
		
		<table width="1000" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="5%" height="27" align="center" bgcolor="#666666" class="text12_w">Item</td>
            <td width="23%" align="center" bgcolor="#666666" class="text12_w">Email(ID)</td>
            <td width="13%" align="center" bgcolor="#666666" class="text12_w">Nickname</td>
            <td width="15%" align="center" bgcolor="#666666" class="text12_w">Country</td>
            <td width="7%" align="center" bgcolor="#666666" class="text12_w">Gender</td>
            <td width="13%" align="center" bgcolor="#666666" class="text12_w">Book Count </td>
            <td width="12%" align="center" bgcolor="#666666" class="text12_w">Sign up </td>
            <td width="12%" align="center" bgcolor="#666666" class="text12_w">Last Login </td>
          </tr>
<? 	$item = 1; 
	while ($rs && !$rs->EOF) { 		  

?>
          <tr class="record_tr">
            <td class="record" height="23"><? echo $item; ?></td>
            <td class="record"><? echo $rs->fields["email"]; ?>&nbsp;</td>
            <td class="record"><? echo $rs->fields["nickname"]; ?>&nbsp;</td>
            <td class="record"><? echo $rs->fields["country"]; ?>&nbsp;</td>
            <td class="record"><? echo $rs->fields["gender"]; ?>&nbsp;</td>
            <td class="record"><? 
				$prs=$conn->Execute("select count(*) as mcount from PUB_M where memid = '".$rs->fields["email"]."'");
					echo $prs->fields["mcount"]; 
				?>&nbsp;</td>
            <td class="record"><? echo $rs->fields["cdate"]; ?>&nbsp;</td>
            <td class="record"><? echo $rs->fields["ldate"]; ?>&nbsp;</td>
          </tr>
		  
<? 		$item++;
		$rs->moveNext();
	}
?>			  
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table> 
