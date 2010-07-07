<? session_start();
	require_once "conn.php";
	require_once "fun.php";	
	$mydata=$LANG."data";	
	$msg="";
	$hotid="";
	if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if (isset($_GET['msg'])) {$msg=$_GET['msg'];}}					
	if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}
	if(isset($_POST['udate'])) { $uudate=$_POST['udate']; }else{ if (isset($_GET['udate'])) {$uudate=$_GET['udate'];}}
	if(isset($_POST['ordernum'])) { $ordernum=$_POST['ordernum']; }else{ if (isset($_GET['ordernum'])) {$ordernum=$_GET['ordernum'];}}
	$rs1 = $conn->Execute("select * from wdata_e where hotid='".$hotid."'");
	$rs2 = $conn->Execute("select count(*) as mcount from wdata_e where hotid='".$hotid."'");
	$rs3 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and udate='".$uudate."' and ordernum = '".$ordernum."'");
	$mcount = intval($rs2->fields["mcount"]);
	$intro2 = $conn->Execute("select * from wdata_e  where mtype = 'b' and stype = 'b' order by ordernum,hotid");
	$udate = date("YmdHis");
	if ($hotid == "") { $hotid=$udate; }
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>会所介绍设定</title>
</head>

<body>																									
<script type="text/javascript" src="js/tinyxmlsax.js"></script>
<script type="text/javascript" src="js/tinyxmlw3cdom.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<? if ($msg != "") { ?>
<script type="text/javascript">
	alert('<? echo $msg; ?>');
</script>
<? } ?>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666"><span class="text16" style="color:#FFFFCC; padding-left:15px;">会所介绍 - <? if ($mcount>0) { echo "修改"; } else { echo "新增"; } ?>項目</span></span></td>
  </tr>
  <tr>
    <td height="230" colspan="2">

	 
	<table width="100%" height="343" border="0"  cellpadding="0" cellspacing="0" style="border:#999999 1px solid">
      <tr>
        <td height="18" colspan="4" align="right" valign="top"><label></label></td>
        </tr>

      <tr>
        <td height="31" colspan="4" align="center" valign="top">
		<table width="100%" height="300" border="0" align="center">
	<form name="mainform" method="post" action="club_edit1.php" enctype="multipart/form-data">		
			  <tr>
				<td width="180" height="34" align="right" valign="top"><span class="text14">項目編號:&nbsp;</span></td>
				<td colspan="3" valign="top"><input type="text" name="hotid" value="<? echo $hotid; ?>" class="input_t_550"  readonly="true"/></td>
			  </tr>		
			  <tr>
				<td width="180" height="26" align="right" valign="top"><span class="text14">主题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="f1" type="text" size="80" value="<? echo $rs1->fields["hotcaption"]; ?>"  class="input_t_550"/></td>
			  </tr>
			  <tr>
				<td width="180" height="26" align="right" valign="top"><span class="text14">副標题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="f3" type="text" size="80" value="<? echo $rs1->fields["subcaption"]; ?>"  class="input_t_550"/></td>
			  </tr>			  
			  <tr>
				<td width="180" height="26" align="right" valign="top"><span class="text14">排序顺序:&nbsp;</span></td>
				<td colspan="3" valign="top">
					<input name="ordernum" type="text" size="80" value="<? echo $rs1->fields["ordernum"]; ?>"  class="input_t_550"/>
				</td>
			  </tr>
			  <tr>
				<td height="83" align="right" valign="top"><span class="text14">說明:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<textarea id="f2" name="f2" cols="70" rows="6" class="input_t_550"  style="display:none"><? echo html_entity_decode($rs1->fields[$mydata], ENT_QUOTES); ?>
				</textarea>
				<iframe id="myEditor1" src="sinaedit/editor.htm?id=f2" frameborder="0" scrolling="no"></iframe>
				</td>
			  </tr>
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">主題圖:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="picture" type="file" class="input_t_550" style="width:540px;" size="80"/> &nbsp;&nbsp;&nbsp;&nbsp;(242px X 182px)
				<BR />
				<? if ($rs1->fields["picture"] != "") {
					echo "<img src=\"../upload/". $rs1->fields["picture"] . "\" width=\"242\" />";
				 } ?>
				</td>
			  </tr>			  
			  
			  <tr>
				<td height="40" align="right" valign="top"><span class="text14">背景圖:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="background" type="file" class="input_t_550" style="width:540px;" size="80"/>&nbsp;&nbsp;&nbsp;&nbsp;(776px X 495px)
				<BR />
				<? if ($rs1->fields["background"] != "") {
					echo "<img src=\"../upload/". $rs1->fields["background"] . "\" width=\"400\" />";
				 } ?>
				</td>
			  </tr>			  
			  </form>
			  <tr>
				<td height="40">&nbsp;</td>
				<td width="395">&nbsp;</td>
				<td width="185"><label>
				  <input type="button" name="Submit" value="<? if ($mcount>0) { echo "修改"; } else { echo "新增"; } ?>" class="button_120" onclick="document.mainform.submit();" />
				</label></td>
				<td width="80">&nbsp;</td>
			  </tr>
			  <tr>
				<td height="40" colspan="4" valign="middle">
			      <hr width="80%" noshade="noshade"  style="color: #CCCCCC" /><br /></td>
			  </tr>						  
			  <? if ($mcount > 0 ) { ?>		  			  
			  <tr>
				<td height="54" align="right" valign="top"><span class="text14">上傳的圖檔:&nbsp;</span></td>
				<td colspan="3" valign="top"><div id="pic_area" style="background: #eeeeee; float:left; width:540px; border-bottom:#666666 dashed 1px; border-top:#666666 dashed 1px; padding-top:15px; padding-bottom:15px; padding-left:15px;">
				<?
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
				</div></td>
			  </tr>	

			  <tr>
				<td height="102" align="right" valign="top"><a name="new-pic-choose"><span class="text14">選擇檔案:&nbsp;</span></a></td>
				<td colspan="3" valign="top" align="left">
				<form name="pic_upload" method="post" action="upload_pic.php" enctype="multipart/form-data">
				<input type="hidden" name="w" value="147"/>								
				<input type="hidden" name="h" value="105"/>															
				<input type="hidden" name="url2" value="club_new.php"/>						
				<input type="hidden" name="hotid" value="<? echo $hotid; ?>"/>	
				<input type="hidden" name="uudate" value="<? echo $uudate; ?>"/>	
				<? if($rs3 && !$rs3->EOF){?>
				<table width="540" height="81" border="0" style="border:#999999 dotted 1px">
				  <tr>
					<td >排序顺序:</td>
					<td align="left">
						<input name="ordernum" type="text" style="width:300px;" size="70" value="<?echo $rs3->fields["ordernum"];?>"/>
					</td>
					<!--
					<td width="1"></td>
					-->
				  </tr>
				  <input name="ptitle"  type="hidden" value="club_title" />
				  <input name="pdetail" type="hidden" value="club_detail" />
				  <tr>
					<td >图片:</td>
					<td align="left"><input name="pic" type="file" style="width:300px;"size="70"/></td>
				  </tr>
				  <tr>
					<td >缩略图:</td>
					<td>
						<img src="../upload/s_<? echo $rs3->fields["picture"]; ?>" width="147" height="105" />
					</td>
				  </tr>
				  <tr>
					<td></td>
					<td align="right">
						<input type="button" class="button_90"  value="上傳" onclick="javascript:document.pic_upload.submit();" />			&nbsp;
						<input type="button" class="button_90"  value="取消" onclick="javascript:location='club_new.php?hotid=<?echo $hotid?>'" />
						&nbsp;
					</td>
					<td>&nbsp;</td>
				  </tr>
				</table>
				<?}else{?>
				<table width="540" height="81" border="0" style="border:#999999 dotted 1px">
				  <tr>
					<td >排序顺序:</td>
					<td align="left"><input name="ordernum" type="text" style="width:300px;" size="70"/></td>
					<!--
					<td width="1"></td>
					-->
				  </tr>
				  <input name="ptitle"  type="hidden" value="club_title" />
				  <input name="pdetail" type="hidden" value="club_detail" />
				  <tr>
					<td >图片:</td>
					<td align="left"><input name="pic" type="file" style="width:300px;"size="70"/></td>
					<!--
					<td width="1"></td>
					-->
				  </tr>
				  <tr>
					<td></td>
					<td align="right"><input type="button" class="button_90"  value="上傳" onclick="javascript:document.pic_upload.submit();" />					  &nbsp;</td>
					<td>&nbsp;</td>
				  </tr>
				</table>
				<?}?>
    			</form>
				</td>
			  </tr>			  			
			  <? } ?>  

        </table>
		<label></label></td>
        </tr>
      <tr>
        <td height="161" colspan="4" valign="top"><table width="100%" height="139" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1" height="33"></td>
            <td width="857">&nbsp;
			
			
			</td>
          </tr>
          <tr>
            <td height="44"></td>
            <td align="center"><a href="club.php?id=new" target="_self"><span class="text16">取消<? if ($mcount>0) { echo "修改"; } else { echo "新增"; } ?></span></a></td>
            </tr>
          <tr>
            <td></td>
            <td valign="top">
			<table width="90%" border="0" cellpadding="0" cellspacing="0" style="font-size:15px; color:#333333; border:#999999 solid 1px;" align="center">
              <tr>
                <td class="td_a" width="16%" height="29" align="center" bgcolor="#CCCCCC">編號</td>
                <td class="td_a" width="29%" align="center" bgcolor="#CCCCCC">名稱</td>
                <td class="td_a" width="19%" align="center" bgcolor="#CCCCCC">修改日期</td>
                <td class="td_a" width="12%" align="center" bgcolor="#CCCCCC">修改人</td>
                <td class="td_a" width="11%" align="center" bgcolor="#CCCCCC">排序</td>
                <td class="td_a" width="13%" align="center" bgcolor="#CCCCCC">&nbsp;</td>
              </tr>
<?	while ($intro2 && !$intro2->EOF) { 	?>
              <tr>
                <td height="31" class="td_a"><? echo $intro2->fields["hotid"]; ?></td>
                <td class="td_a"><? echo $intro2->fields["hotcaption"]; ?></td>
                <td class="td_a"><? echo formatdatetime($intro2->fields["udate"]); ?></td>
                <td class="td_a"><? echo $intro2->fields["optid"]; ?></td>
                <td class="td_a"><? echo $intro2->fields["ordernum"]; ?></td>
                <td class="td_a" align="center">
				<a href="club_new.php?hotid=<? echo $intro2->fields["hotid"]; ?>">
				<img src="images/ic_edit.png" width="13" border="0" />
				<span class="text12">Edit</span>
				</a>
				<a href="javascript:main_delete('<? echo $intro2->fields["hotid"]; ?>','club.php');">
				<img src="images/cut16.png" width="13" border="0" />
				<span class="text12">Delete</span>
				</a>
				</td>
              </tr>
<? 		$intro2->moveNext();
	}	
?>		  
            </table></td>
          </tr>
        </table></td>
        </tr>
      <tr>
        <td width="136">&nbsp;</td>
        <td colspan="3">&nbsp;</td>
      </tr>
    </table>
	
	</td>
  </tr>
</table>

</body>
</html>
