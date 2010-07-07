<? session_start();
	require_once "conn.php";
$hotid="";
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}									$picrs = $conn->Execute("select * from wdata_pic where hotid = '" .$hotid."' order by ordernum");
			while ($picrs && !$picrs->EOF) { 
					?>
					<span style="float:left; padding-right:3px;">
					<table width="auto" height="auto" border="0" cellpadding="0" cellspacing="0">
					  <tr align="left">
						<td>标题</td>
						<td><input type="text" name="title" value="<? echo $picrs->fields["title"]; ?>"/></td>
					  </tr>
					  <tr align="left">
						<td>详情</td>
						<td><input type="text" name="detail" value="<? echo $picrs->fields["detail"]; ?>"/></td>
					  </tr>
					  <tr align="left">
						<td>排序号</td>
						<td><input type="text" name="ordernum" value="<? echo $picrs->fields["ordernum"]; ?>"/></td>
					  </tr>
					  <tr align="left">
						<td rowspan="3"><img src="../upload/s_<? echo $picrs->fields["picture"]; ?>" width="147" /></td>
					  </tr>
					  <tr>
						<td align="center"><a href="javascript:doFuncReturnXmlv2('delete_pic.php','hotid=<? echo $hotid; ?>&udate=<? echo $picrs->fields["udate"]; ?>');">刪除</a></td>
					  </tr>
					</table>
					</span>
					<?      $picrs->moveNext();
						}
					?>	
