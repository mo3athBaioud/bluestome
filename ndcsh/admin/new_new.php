<? session_start();
	require_once "conn.php";
	require_once "fun.php";	
$mydata=$LANG."data";	
$msg="";
$hotid="";
if(isset($_POST['msg'])) { $msg=$_POST['msg']; }else{ if (isset($_GET['msg'])) {$msg=$_GET['msg'];}}					
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$rs1 = $conn->Execute("select * from wdata where hotid='".$hotid."'");
$rs2 = $conn->Execute("select count(*) as mcount from wdata where hotid='".$hotid."'");
$mcount = intval($rs2->fields["mcount"]);
$intro2 = $conn->Execute("select * from wdata  where mtype = 'd' and stype = 'b' order by ordernum,hotid");
$udate = date("YmdHis");
if ($hotid == "") { $hotid=$udate; }
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="common.css" rel="stylesheet" type="text/css">
<title>品鉴LUXE设定</title>
<SCRIPT type="text/javascript" src="js/jquery.js"></SCRIPT>
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
<script type="text/javascript">
	<!--
	function showpicform(){
		$('#upload-pic').show();
	}

	function hidenpicform(){
		$('#upload-pic').hiden();
	}
	-->
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" height="30" valign="middle" bgcolor="#666666"><span class="text16" style="color:#FFFFCC; padding-left:15px;">媒體報導 - <? if ($mcount>0) { echo "修改"; } else { echo "新增"; } ?>項目</span></span></td>
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
		<form name="mainform" method="post" action="new_edit1.php" enctype="multipart/form-data">		
			  <tr>
				<td height="34" align="right" valign="top"><span class="text14">項目編號:&nbsp;</span></td>
				<td colspan="3" valign="top"><input type="text" name="hotid" value="<? echo $hotid; ?>" class="input_t_550"  readonly="true"/></td>
			  </tr>		
			  <tr>
				<td width="180" height="26" align="right" valign="top"><span class="text14">主题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="f1" type="text" size="80" value="<? echo $rs1->fields["hotcaption"]; ?>"  class="input_t_550"/></td>
			  </tr>
			  <tr>
				<td width="180" height="26" align="right" valign="top"><span class="text14">副標题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="f3" type="text" size="80" value="<? echo $rs1->fields["subcaption"]; ?>"  class="input_t_550"/></td>
			  <tr>
				<td width="180" height="26" align="right" valign="top"><span class="text14">排序号:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="ordernum" type="text" size="80" value="<? echo $rs1->fields["ordernum"]; ?>"  class="input_t_550"/></td>
			  </tr>
			  </tr>		
				<td width="180" height="26" align="right" valign="top"><span class="text14">圖片副標题:&nbsp;</span></td>
				<td colspan="3" valign="top"><input name="f4" type="text" size="80" value="<? echo $rs1->fields["piccaption"]; ?>"  class="input_t_550"/></td>
			  </tr>			  			  			  	  
			  <tr>
				<td height="83" align="right" valign="top"><span class="text14">說明:&nbsp;</span></td>
				<td colspan="3" valign="top">
				<textarea id="f2" name="f2" cols="70" rows="6" class="input_t_550" style="display:none"><? echo html_entity_decode($rs1->fields[$mydata], ENT_QUOTES); ?></textarea>
				<iframe id="f2editor" src="sinaedit/editor.htm?id=f2" frameborder="0" scrolling="no" ></iframe>
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
			    
				<td height="54" align="right" valign="top">
				<span class="text14">上傳的圖檔:&nbsp;</span>
				</td>
				<td colspan="3" valign="top"><div id="pic_area" style="background: #eeeeee; float:left; width:650px; border-bottom:#666666 dashed 1px; border-top:#666666 dashed 1px; padding-top:15px; padding-bottom:15px; padding-left:15px;">
				<?
						$picrs = $conn->Execute("select * from wdata_pic where hotid = '" .$hotid."'");
						while ($picrs && !$picrs->EOF) { 
					?>
					<span style="float:left; padding-right:3px;">
					<table width="auto" height="auto" border="0" cellpadding="0" cellspacing="5px;">
					  <tr align="left">
						<td rowspan="3"><img src="../upload/s_<? echo $picrs->fields["picture"]; ?>" width="156" height="100"/></td>
						<td>
							<!--
							<a href="hotid=<? echo $hotid; ?>&udate=<? echo $picrs->fields["udate"]; ?>">
							修改
							</a>
							-->
							<a href="news_pic_edit.php?hotid=<? echo $hotid; ?>&udate=<? echo $picrs->fields["udate"];?>">
							修改
							</a>			
						</td>
						<td>
							<a href="javascript:if(confirm('确定要删除吗?')){doFuncReturnXmlv2('delete_pic.php','hotid=<? echo $hotid; ?>&udate=<? echo $picrs->fields["udate"]; ?>');}">
							刪除
							</a>
						</td>
					  </tr>
					  <!--
					  <tr>
					  </tr>
					  -->
					</table>
					</span>
							

					<?      $picrs->moveNext();
						}
					?>	
				</div></td>
			  </tr>	
			  <tr>
				<td height="102" align="right" valign="top">
				<a name="news-pic-upload">
				<span class="text14">選擇檔案:&nbsp;</span>
				</a>
				</td>
				<td colspan="3" valign="top" align="left">
				<div id="upload-pic" style="display:''" >
				<form name="pic_upload" method="post" action="upload_news_pic.php" enctype="multipart/form-data">
				<input type="hidden" name="w" value="147"/>								
				<input type="hidden" name="h" value="170"/>																
				<input type="hidden" name="url2" value="new_new.php"/>								
				<input type="hidden" name="hotid" value="<? echo $hotid; ?>"/>				
				<table width="669" height="81" border="0" style="border:#999999 dotted 1px">
				  <tr>
					<td width="20%">标题:</td>
					<td><input type="text" name="ptitle"/></td>
				  </tr>
				  <tr>
					<td>详情:</td>
					<td>
					<textarea id="detail" name="pdetail" cols="35" rows="6" style="display:none"></textarea>
					<iframe id="detaileditor" src="sinaedit/editor.htm?id=detail" frameborder="0" scrolling="no"></iframe>
					</td>
				  </tr>
				  <tr>
					<td>位置:</td>
					<td>
						<select name="ordernum">
							<option value="1">Fashion|时尚公告</option>
							<option value="2">People|走近人物</option>
							<option value="3">Features|专题策划</option>
							<option value="4">Life|品位生活</option>
							<option value="5">Connoisseur|鉴赏九龙山</option>
							<option value="6">Cover Story|封面故事</option>
						</select>
					</td>
				  </tr>
				  <tr>
					<td>选择图片</td>
					<td>
					<input name="pic" type="file" class="input_t_550" style="width:540px;" size="70"/></td>
				  </tr>
				  <tr>
					<td>&nbsp;</td>
					<td width="113">( 740 x 860px 或等比例倍數大的圖)</td>
				  </tr>
				  <tr>
					<td>&nbsp;</td>
					<td align="right"><input type="button" class="button_90"  value="上傳" onclick="javascript:document.pic_upload.submit();" />					  &nbsp;</td>
					<td>&nbsp;</td>
				  </tr>
				</table>
    			</form>
				</div>
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
            <td align="center"><a href="new.php?id=new" target="_self"><span class="text16">取消<? if ($mcount>0) { echo "修改"; } else { echo "新增"; } ?></span></a></td>
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
                <td class="td_a" width="11%" align="center" bgcolor="#CCCCCC">排序号</td>
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
				<a href="new_new.php?hotid=<? echo $intro2->fields["hotid"]; ?>">
				<img src="images/ic_edit.png" width="13" border="0" /><span class="text12">Edit</span>
				</a>
				<a href="javascript:main_delete('<? echo $intro2->fields["hotid"]; ?>','new.php');">
				<img src="images/cut16.png" width="13" border="0" /><span class="text12">Delete</span>
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
