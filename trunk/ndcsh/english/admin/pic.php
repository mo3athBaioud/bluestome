<? session_start();
	require_once "conn.php";
$hotid="";
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}
$picrs = $conn->Execute("select * from wdata_pic_e where hotid = '" .$hotid."' order by ordernum");
					while ($picrs && !$picrs->EOF) { 
					?>
					<span style="float:left; padding-right:3px;">
					<table width="auto" height="auto" border="0" cellpadding="0" cellspacing="0">
					  <tr>
						<td><img src="../upload/s_<? echo $picrs->fields["picture"]; ?>" width="147" height="105" /></td>
					  </tr>
					  <tr>
						<td align="center">
						<a href="club_new.php?hotid=<? echo $hotid; ?>&udate=<? echo $picrs->fields["udate"];?>&ordernum=<?echo $picrs->fields["ordernum"];?>#new-pic-choose">修改</a>
						<a href="javascript:if(confirm('确认删除吗?')){doFuncReturnXmlv2('delete_pic.php','hotid=<? echo $hotid; ?>&udate=<? echo $picrs->fields["udate"]; ?>');}">刪除</a></td>
					  </tr>
					</table>
					</span>
					<?      $picrs->moveNext();
						}
					?>	
