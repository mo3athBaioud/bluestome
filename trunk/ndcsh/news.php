<? 
header('Content-type: text/html; charset=utf-8');
require_once "conn.php"; 
$hotid="";
if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
$page2 = 1;
if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
$start = ($page2-1)*6;
if ($hotid == "") { 
	$tmprs=$conn->Execute("select * from wdata where mtype='d' and stype='b' order by hotid desc limit 0,1");
	$hotid = $tmprs->fields["hotid"];
}

#$mrs = $conn->Execute("select * from wdata where hotid='".$hotid."' order by ordernum");
#$pmrs = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' order by udate desc LIMIT ".$start." ,6");
	$dmrs = $conn->Execute("select * from wdata where mtype = 'd' and stype = 'b' order by ordernum");		
	$mydata = $LANG."data";
	$plmrs = $conn->Execute("select count(*) as mcount from wdata_pic where hotid='".$hotid."'");
	$mcount2 = intval($plmrs->fields["mcount"]);	
	$order   = array("\r\n", "\n", "\r");
	$replace = '<br />';
	
?>    
<SCRIPT type=text/javascript src="js/jquery.js"></SCRIPT>
<script type="text/javascript">
	<!--

	function hiddenPicDesc(){

		$('#titleshow1').hide();
		$('#titleshow2').hide();
		$('#titleshow3').hide();
		$('#titleshow4').hide();
		$('#titleshow5').hide();
		$('#titleshow6').hide();

		$('#contentshow1').hide();
		$('#contentshow2').hide();
		$('#contentshow3').hide();
		$('#contentshow4').hide();
		$('#contentshow5').hide();
		$('#contentshow6').hide();

		$('#content9').show();
		$('#maintitle').show();

	}

	function showCata1(){

		$('#content9').hide();
		$('#maintitle').hide();

		$('#titleshow1').show();
		$('#titleshow2').hide();
		$('#titleshow3').hide();
		$('#titleshow4').hide();
		$('#titleshow5').hide();
		$('#titleshow6').hide();

		$('#contentshow1').show();
		$('#contentshow2').hide();
		$('#contentshow3').hide();
		$('#contentshow4').hide();
		$('#contentshow5').hide();
		$('#contentshow6').hide();
}

	function showCata2(){
		$('#content9').hide();
		$('#maintitle').hide();

		$('#titleshow2').show();
		$('#titleshow1').hide();
		$('#titleshow3').hide();
		$('#titleshow4').hide();
		$('#titleshow5').hide();
		$('#titleshow6').hide();

		$('#contentshow2').show();
		$('#contentshow1').hide();
		$('#contentshow3').hide();
		$('#contentshow4').hide();
		$('#contentshow5').hide();
		$('#contentshow6').hide();
	}
	function showCata3(){
		$('#content9').hide();
		$('#maintitle').hide();

		$('#titleshow3').show();
		$('#titleshow2').hide();
		$('#titleshow1').hide();
		$('#titleshow4').hide();
		$('#titleshow5').hide();
		$('#titleshow6').hide();

		$('#contentshow3').show();
		$('#contentshow2').hide();
		$('#contentshow1').hide();
		$('#contentshow4').hide();
		$('#contentshow5').hide();
		$('#contentshow6').hide();
	}

	function showCata4(){
		$('#content9').hide();
		$('#maintitle').hide();

		$('#titleshow4').show();
		$('#titleshow2').hide();
		$('#titleshow3').hide();
		$('#titleshow1').hide();
		$('#titleshow5').hide();
		$('#titleshow6').hide();

		$('#contentshow4').show();
		$('#contentshow2').hide();
		$('#contentshow3').hide();
		$('#contentshow1').hide();
		$('#contentshow5').hide();
		$('#contentshow6').hide();
	}
	function showCata5(){
		$('#content9').hide();
		$('#maintitle').hide();

		$('#titleshow5').show();
		$('#titleshow2').hide();
		$('#titleshow3').hide();
		$('#titleshow4').hide();
		$('#titleshow1').hide();
		$('#titleshow6').hide();

		$('#contentshow5').show();
		$('#contentshow2').hide();
		$('#contentshow3').hide();
		$('#contentshow4').hide();
		$('#contentshow1').hide();
		$('#contentshow6').hide();
	}
	function showCata6(){
		$('#content9').hide();
		$('#maintitle').hide();

		$('#titleshow6').show();
		$('#titleshow2').hide();
		$('#titleshow3').hide();
		$('#titleshow4').hide();
		$('#titleshow5').hide();
		$('#titleshow1').hide();

		$('#contentshow6').show();
		$('#contentshow2').hide();
		$('#contentshow3').hide();
		$('#contentshow4').hide();
		$('#contentshow5').hide();
		$('#contentshow1').hide();
	}
	-->
</script>
<style>
.title_channel1{
font-size:12px;
/*color:#FFFFFF;*/
background-color:#RGB(197,167,116);
}

.p{
	line-height:16px;
	font-size:11px;
}
</style>
<div id="intro_preview" style=" display:none;position:absolute; background:#eeeeee;border:#666666 1px solid; width:270; height: auto;" align="center">
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
          <td width="776" valign="top" background="images/66.jpg">
		  <table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- height="300"  -->
              <tr> 
                <td valign="top" height="290"><!-- height="337"  -->
				<table width="776" height="8" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="776" height="8"><img src="images/spacer.gif" width="8" height="8"></td>
                    </tr>
                  </table>
                  <table width="776" border="0" cellpadding="0" cellspacing="0"><!--height="200" -->
                    <tr> 
                      <td width="304" valign="top"><!--  height="200" -->
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td height="48">&nbsp;</td>
                          </tr>
                        </table>
                        <table width="292" height="100" border="0" cellpadding="0" cellspacing="2">
                          <tr> 
                            <td width="25" height="155">&nbsp;</td>
                            <td width="261" valign="top">
							<?	while ($dmrs && !$dmrs->EOF) { 	?>				
												<table width="258" border="0" cellspacing="1" cellpadding="0">
												<tr> 
												  <td width="23"><img src="images/arrow.gif" width="19" height="5"></td>
												  <td width="232" class="leftmenu">
												  <!-- 
													onmouseover="javascript:openprogram('news.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content7','p8');" 
												  -->
												  <a href="javascript:openprogram('news.php','hotid=<? echo $dmrs->fields["hotid"]; ?>','content7','p8');"><font color="FBD100"><? echo $dmrs->fields["hotcaption"]; ?></font></a></td>
												</tr>
											  </table> 
							<? 
								$dmrs->moveNext();
								}	
							?>		  				  														
							</td>
                          </tr>
                        </table>
					  </td>
                      <td width="445" valign="top">
					  <table width="445" border="0" cellpadding="0" cellspacing="0" id="p8" style="display:'';">
                          <tr> 
                            <td width="445" valign="top">
							<table width="445" border="0" cellpadding="2px" cellspacing="0" >
								<tr>
									<td>
									<span style="font-size:13px;background-color:RGB(197,167,116);color:#FFFFFF;width:156px;">
									Fashion|时尚公告
									</span>
									<a onmouseover="javascript:showCata1();"
									onmouseout="javascript:hiddenPicDesc();"
									>
									<?
										$tmpprs1 = $conn->Execute("select distinct * from wdata_pic where hotid='".$hotid."' 
										and ordernum = 1 limit 0,1");
										if($tmpprs1 && !$tmpprs1->EOF){
									?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','<?echo $tmpprs1->fields['picture'];?>','<?  
									list($width, $height, $type, $attr) = getimagesize($bsname.'upload/'.$tmpprs1->fields['picture']); 
									if ($width>440) {
										echo intval(($height*($width/440))+23); 					
									} else {
										echo intval(($height*(440/$width))+23); 
									}	
									?>');">			  								  
									<img src="upload/<? echo $tmpprs1->fields['picture'];?>" alt="<? echo $tmpprs1->fields['title'];?>" width="156" height="100"/>
									<?}else{?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','bm1.jpg');">
										<img src="upload/m1.jpg" alt="Fashion|时尚公告" width="156" height="100"/>
									</div>
									<?}?>
									</div>
									</a>
									</td>
									<td>
									<span style="font-size:13px;background-color:RGB(232,82,50);color:#FFFFFF;width:156px;">
									People|走近人物
									</span>
									<br/>
									<a onmouseover="javascript:showCata2();"
									onmouseout="javascript:hiddenPicDesc();"
									>
									<?
										$tmpprs2 = $conn->Execute("select distinct * from wdata_pic where hotid='".$hotid."' 
										and ordernum = 2 limit 0,1");
										if($tmpprs2 && !$tmpprs2->EOF){
									?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','<? echo $tmpprs2->fields['picture'];?>','<?  
									list($width, $height, $type, $attr) = getimagesize($bsname.'upload/'.$tmpprs2->fields['picture']); 
									if ($width>440) {
										echo intval(($height*($width/440))+23); 					
									} else {
										echo intval(($height*(440/$width))+23); 
									}	
									?>');">			  								  
									<img src="upload/<?echo $tmpprs2->fields['picture'];?>" alt="<?echo $tmpprs2->fields['title'];?>" width="156" height="100"/>
									<?}else{?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','bm2.jpg');">
										<img src="upload/m2.jpg" alt="People|走近人物" width="156" height="100"/>
									</div>
									<?}?>
									</div>
									</a></td>
									<td>
									<span style="font-size:13px;background-color:RGB(19,112,180);color:#FFFFFF;width:156px;">
									Features|专题策划
									</span>
									<br/>
									<a onmouseover="javascript:showCata3();"
									onmouseout="javascript:hiddenPicDesc();"
									>
									<?
										$tmpprs3 = $conn->Execute("select distinct * from wdata_pic where hotid='".$hotid."' 
										and ordernum = 3 limit 0,1");
										if($tmpprs3 && !$tmpprs3->EOF){
									?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','<? echo $tmpprs3->fields['picture'];?>','<?  
									list($width, $height, $type, $attr) = getimagesize($bsname.'upload/'.$tmpprs3->fields['picture']); 
									if ($width>440) {
										echo intval(($height*($width/440))+23); 					
									} else {
										echo intval(($height*(440/$width))+23); 
									}	
									?>');">			  								  
									<img src="upload/<?echo $tmpprs3->fields['picture'];?>" alt="<?echo $tmpprs3->fields['title'];?>" width="156" height="100"/></a></td>
									<?}else{?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','bm3.jpg');">
										<img src="upload/m3.jpg" alt="Features|专题策划" width="156" height="100"/>
									</div>
									<?}?>
									</div>
									</a>
									</td>
								</tr>
							</table>
							</td>
							</tr>
							<tr>
							<td  width="472" valign="top">
							<table width="445" border="0" cellpadding="2px" cellspacing="0" >
								<tr>
									<td>
									<span style="font-size:13px;background-color:RGB(0,166,69);color:#FFFFFF;width:156px;">
									Life|品位生活
									</span>
									<a onmouseover="javascript:showCata4();"
									onmouseout="javascript:hiddenPicDesc();"
									>
									<?
										$tmpprs4 = $conn->Execute("select distinct * from wdata_pic where hotid='".$hotid."' 
										and ordernum  = 4 limit 0,1");
										if($tmpprs4 && !$tmpprs4->EOF){
									?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','<? echo $tmpprs4->fields['picture'];?>','<?  
									list($width, $height, $type, $attr) = getimagesize($bsname.'upload/'.$tmpprs4->fields['picture']); 
									if ($width>440) {
										echo intval(($height*($width/440))+23); 					
									} else {
										echo intval(($height*(440/$width))+23); 
									}	
									?>');">			  								  
									<img src="upload/<?echo $tmpprs4->fields['picture'];?>" alt="<?echo $tmpprs4->fields['title'];?>" width="156" height="100"/>
									<?}else{?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','bm4.jpg');">
										<img src="upload/m4.jpg" alt="Life|品位生活" width="156" height="100"/>
									</div>
									<?}?>
									</div>
									</a></td>
									<td>
									<span style="font-size:13px;background-color:RGB(90,25,124);color:#FFFFFF;width:156px;">
									Connoisseur|鉴赏九龙山
									</span>
									<a onmouseover="javascript:showCata5();"
										onmouseout="javascript:hiddenPicDesc();"
									>
									<?
										$tmpprs5 = $conn->Execute("select distinct * from wdata_pic where hotid='".$hotid."' 
										and ordernum = 5 limit 0,1");
										if($tmpprs5 && !$tmpprs5->EOF){
									?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','<? echo $tmpprs5->fields['picture'];?>','<?  
									list($width, $height, $type, $attr) = getimagesize($bsname.'upload/'.$tmpprs5->fields['picture']); 
									if ($width>440) {
										echo intval(($height*($width/440))+23); 					
									} else {
										echo intval(($height*(440/$width))+23); 
									}	
									?>');">			  								  
									<img src="upload/<?echo $tmpprs5->fields['picture'];?>" alt="<?echo $tmpprs5->fields['title'];?>" width="156" height="100"/>
									<?}else{?>
										<div style="cursor:pointer" onclick="javascript:display_pic('intro','bm5.jpg');">
											<img src="upload/m5.jpg" alt="Connoisseur|鉴赏九龙山" width="156" height="100"/>
										</div>
									<?}?>
									</div>
									</a></td>
									<td>
									<span style="font-size:13px;background-color:RGB(100,100,100);color:#FFFFFF;width:156px;">
									Cover Story|封面故事
									</span>
									<a onmouseover="javascript:showCata6();"
									onmouseout="javascript:hiddenPicDesc();"
									>
									<?
										$tmpprs6 = $conn->Execute("select distinct * from wdata_pic where hotid='".$hotid."' 
										and ordernum = 6 limit 0,1");
										if($tmpprs6 && !$tmpprs6->EOF){
									?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','<? echo $tmpprs6->fields['picture'];?>','<?  
									list($width, $height, $type, $attr) = getimagesize($bsname.'upload/'.$tmpprs6->fields['picture']); 
									if ($width>440) {
										echo intval(($height*($width/440))+23); 					
									} else {
										echo intval(($height*(440/$width))+23); 
									}	
									?>');">			  								  
									<img src="upload/<?echo $tmpprs6->fields['picture'];?>" alt="<?echo $tmpprs6->fields['title'];?>" width="156" height="100"/>
									<?}else{?>
									<div style="cursor:pointer" onclick="javascript:display_pic('intro','bm6.jpg');">
										<img src="upload/m6.jpg" alt="Cover Story|封面故事" width="156" height="100"/>
									</div>
									<?}?>
									</div>
									</a>
									</td>
								</tr>
							</table>
                            </td>
                          </tr>
						 <tr>
						 <td valign="top">
								<div align="left" class="mainContext11" style="padding-left:20px; padding-bottom:50px; font-size:13px"> 
							<? if ($mcount2 > 10) { ?>				
							<? if ((intval($page2)-1) > 0 ) { ?>												
								<a href="javascript:openprogram('news.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2-1; ?>','content7');">
								<font color="#333333">Previous</font>
								</a> 
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																	
                                 <font color="#FFFFFF">│</font> 
							<? if ((intval($page2)+1) <= ceil($mcount2/10) ) { ?>								 								
								<a href="javascript:openprogram('news.php','hotid=<? echo $hotid; ?>&page2=<? echo $page2+1; ?>','content7');">
								 <font color="#333333">Next</font>
								 </a>
							<? } else { echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; } ?>																									 
							<? } ?>								 
								 
                            </div>						 
						 </td>
						 </tr> 						  
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <table width="761" border="0" cellpadding="0" cellspacing="2"><!--height="33" -->
              <tr> 
                <td width="43" height="29">&nbsp;</td>
                <td width="585" valign="top" class="mainContext12">
					<span class="mainContext20" id="maintitle">
					<!--
					<? echo $mrs->fields["hotcaption"]; ?>
					-->
					关于-品鉴LUXE
					</span>
					<span class="mainContext20" id="titleshow">
					</span>
				<?
				$trs1 = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' and ordernum = 1 ");
				if($trs1 && !$trs1->EOF){
				?>
					<span class="mainContext20" id="titleshow1" style="display:none" >
					<?echo $trs1->fields["title"];?>
					</span>
				<?}else{?>
					<span class="mainContext20" id="titleshow1" style="display:none" >
					Fashion|时尚公告
					</span>
				<?}?>
				<?
				$trs2 = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' and ordernum = 2 ");
				if($trs2 && !$trs2->EOF){
				?>
					<span class="mainContext20" id="titleshow2" style="display:none">
					<?echo $trs2->fields["title"];?>
					</span>
				<?
					}else{
				?>
					<span class="mainContext20" id="titleshow2" style="display:none">
					People|走近人物
					</span>
				<?}?>

				<?
				$trs3 = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' and ordernum = 3 ");
				if($trs3 && !$trs3->EOF){
				?>
					<span class="mainContext20" id="titleshow3" style="display:none">
					<?echo $trs3->fields["title"];?>
					</span>
				<?
					}else{
				?>
					<span class="mainContext20" id="titleshow3" style="display:none">
					Features|专题策划
					</span>
				<?}?>
				<?
				$trs4 = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' and ordernum = 4 ");
				if($trs4 && !$trs4->EOF){
				?>
					<span class="mainContext20" id="titleshow4" style="display:none">
					<?echo $trs4->fields["title"];?>
					</span>
				<?
					}else{
				?>
					<span class="mainContext20" id="titleshow4" style="display:none">
					Life|品味生活
					</span>
				<?}?>

				<?
				$trs5 = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' and ordernum = 5 ");
				if($trs5 && !$trs5->EOF){
				?>
					<span class="mainContext20" id="titleshow5" style="display:none">
					<?echo $trs5->fields["title"];?>
					</span>
				<?
					}else{
				?>
					<span class="mainContext20" id="titleshow5" style="display:none">
					Connoisseur|鉴赏九龙山
					</span>
				<?}?>

				<?
				$trs6 = $conn->Execute("select * from wdata_pic where hotid='".$hotid."' and ordernum = 6 ");
				if($trs6 && !$trs6->EOF){
				?>
					<span class="mainContext20" id="titleshow6" style="display:none">
					<?echo $trs6->fields["title"];?>
					</span>
				<?
					}else{
				?>
					<span class="mainContext20" id="titleshow6" style="display:none">
					Cover Story|封面故事
					</span>
				<?}?>
					<!--
					<span class="mainContext12"><br>
                  <font color="#000000">
				  <? echo $mrs->fields["subcaption"]; ?>
				  </font></span>
				  -->
				  </td>
                <td width="125" valign="top" class="mainContext12"><div align="right"></div></td>
              </tr>
            </table>
            <table width="760" height="33" border="0" cellpadding="0" cellspacing="2">
              <tr>
			  
                <td width="43" height="29">&nbsp;</td>
                <td width="711" valign="top" class="mainContext12">
				  <div style="height:90px;overflow:auto;position:relative" id="content9">
					<p>
					《品鉴LUXE》是一本传达尊贵生活方式的会刊杂志，它的读者群不仅针对九龙山庄园的会员，还包括江浙一带的富有且对国际化高端生活品质有着极高追求的人群. 杂志同时在上海部分五星级酒店、高端商务中心以及高级座驾车主俱乐部中摆放。</br></br>   
					  《品鉴LUXE》的宗旨是向读者传达国际化高端品质生活所相关的最新信息。我们追求全方位的品质生活，从方式到理念，它不仅会带来奢侈品牌的最新产品,更包括贵族教育、医疗、艺术和文化等等。
					</p>
				  </div>
				  <div id="contentshow" style="display:''">
				  </div>
				  <div id="contentshow1" style="display:none;">
			<?
			$drs1 = $conn->Execute("select detail from wdata_pic where hotid='".$hotid."' and ordernum = 1 ");
			if($drs1 && !$drs1->EOF){
			?>
				  <p>
				  <? echo str_replace($order, $replace, html_entity_decode($drs1->fields["detail"], ENT_QUOTES)); ?>
				   </p>
			<?
			}else{
			?>
				<p>
				   <b>Fashion News&nbsp;时尚资讯</b>&nbsp;&nbsp;&nbsp;给您介绍时尚品牌的最新咨询和活动派对<br>
				   <b>Hot Item&nbsp;新元素</b>&nbsp;&nbsp;&nbsp;每期将推荐顶级时尚潮流新品<br>
				   <b>Watch&nbsp;手表</b>&nbsp;&nbsp;&nbsp;本期带您走进一年一度的“奢侈品中的奥斯卡”——巴塞尔国际钟表珠宝展<br>
				   <b>Jewelry&nbsp;珠宝</b>&nbsp;&nbsp;&nbsp;本期为您揭秘当今世上最令人惊叹的宝石之一Wittelsbach-Graff钻石的由来和故事<br>
				   <b>Style&nbsp;格调“浪漫逃逸”</b>&nbsp;&nbsp;&nbsp;本期的时装大片灵感来源于九龙山的游艇湾大海和自然的拥抱，我们希望让您有逃离喧闹的都市的冲动，躲进世外桃源的“天堂岛”，在这个享受阳光绚烂的季节里带着爱人放飞梦想。<br>
				   <b>Brand Story&nbsp;品牌故事</b>&nbsp;&nbsp;&nbsp;介绍源于意大利的奢华休闲品牌“Paul&Shark 保罗&鲨鱼”<br>
				</p>
			<?}?>
				  </div>

				  <div id="contentshow2" style="display:none">
			<?
			$drs2 = $conn->Execute("select detail from wdata_pic where hotid='".$hotid."' and ordernum = 2 ");
			if($drs2 && !$drs2->EOF){
			?>
				  <p>
				  <? echo str_replace($order, $replace, html_entity_decode($drs2->fields["detail"], ENT_QUOTES)); ?>
				  </p>
			<?}else{?>
				<p>
				  <b>Architect&nbsp;对话</b>&nbsp;&nbsp;&nbsp;</br>对话专栏本期独家采访了来自西班牙的世界级生态景观建筑师——缪文•比亚罗埃尔（Melvin Villarroel），他的建筑作品遍及世界个顶级度假胜地，是当代欧洲名流贵族御用建筑大师。Melvin最新力作——西班牙海角城堡也即将在九龙山庄园华丽呈现。</br></br>
				   <b>Celebrity&nbsp;名人</b>&nbsp;&nbsp;&nbsp;</br>从《建国大业》中的“宋美龄”到热门电视剧《蜗居》里的“宋太太”，邬君梅——这位曾被美国《人物》杂志誉为50位世界最美女人，她与九龙山的渊源，与慈善事业的渊源，让她的戏外人生更精彩！</br></br>
				  <b>Member Highligh&nbsp;会员介绍</b>&nbsp;&nbsp;&nbsp;</br>本期会员介绍带您认识上海商城总经理Byron Kan</br></br>
				</p>
			<?}?>
				  </div>

				  <div id="contentshow3" style="display:none">
			<?
			$drs3 = $conn->Execute("select detail from wdata_pic where hotid='".$hotid."' and ordernum = 3 ");
			if($drs3 && !$drs3->EOF){
			?>

				  <p>
				  <? echo str_replace($order, $replace, html_entity_decode($drs3->fields["detail"], ENT_QUOTES)); ?>
				 </p>
			<?}else{?>
				<p>
				  <b>Focus&nbsp;热点“勇敢者的运动”</b>&nbsp;&nbsp;&nbsp;</br>帆船被誉为“勇敢者的运动”，这些年在中国帆船运动越来越盛行，本期特别介绍了目前中国最大的几项帆船赛事和成长历程。</br></br>
				 <b>Experience&nbsp;体验“关注赛马”</b>&nbsp;&nbsp;&nbsp;</br>国内赛马业在低调中快速发展，本期带您体验和关注陌生的“赛马世界”，和了解正在举办中的速度赛马联赛信息。</br></br>
 				 <b>Topic&nbsp;话题“自由翱翔的私飞时代还远吗？</b>&nbsp;&nbsp;&nbsp;</br>轿车、游艇热潮渐退，人们开始把目光投向另一个热点——私人飞机。我们邀请到了庞巴迪、波音等亚太区最大的飞机经销商负责人来谈谈中国私飞时代是否已准备好起飞？</br></br>	
				</p>
			<?}?>
				  </div>

				  <div id="contentshow4" style="display:none">
			<?
			$drs4 = $conn->Execute("select detail from wdata_pic where hotid='".$hotid."' and ordernum = 4 ");
			if($drs4 && !$drs4->EOF){
			?>

				  <p>
				  <? echo str_replace($order, $replace, html_entity_decode($drs4->fields["detail"], ENT_QUOTES)); ?>
				   </p>
			<?}else{?>
				<p>
				   <b>Hot Item&nbsp;新元素</b>&nbsp;&nbsp;&nbsp;为您推荐顶级品牌生活新品</br>
				   <b>Automobile&nbsp;座驾</b>&nbsp;&nbsp;&nbsp;直击一年一度日内瓦车展盛宴</br>
				   <b>Health&nbsp;养身 “贴身”的健康管理</b>&nbsp;&nbsp;&nbsp;&nbsp;能拥有一位私人医生来贴身管理自己的健康逐渐成为一种潮流。本期为您解读这种全新的健康理念正悄悄走进我们的生活方式。</br>
                   <b>Art&nbsp;艺苑 用青铜雕琢一个神话世界</b>&nbsp;&nbsp;&nbsp;带您鉴赏法国艺术家理查德•塔克西埃用青铜雕琢的神话世界。这位法国国宝级的艺术家在上海的神兽主题艺术作品展让我们大开眼界，他的“神兽”作品不仅仅是雕塑品，更是人类走向心灵进程的一种演绎。</br>
                   <b>Hotel&nbsp;酒店  耀目明珠落外滩</b>&nbsp;&nbsp;&nbsp;3月28日上海半岛酒店盛大开幕</br>
                   <b>Travel&nbsp;旅行</b>&nbsp;&nbsp;&nbsp;为您和暑期中的孩子订制了可共同出游的体验路线——瑞法两周亲子夏令营</br>
				</p>
			<?}?>
				  </div>
				  <div id="contentshow5" style="display:none">

			<?
			$drs5 = $conn->Execute("select detail from wdata_pic where hotid='".$hotid."' and ordernum = 5 ");
			if($drs5 && !$drs5->EOF){
			?>
				  <p>
				  <? echo str_replace($order, $replace, html_entity_decode($drs5->fields["detail"], ENT_QUOTES)); ?>
				  </p>
			<?}else{?>
				<p>
				  2010年九龙山高尔夫会所迎来了他的第四个夏天，面海坐山风景如画的九龙山高尔夫俱乐部日渐成熟也越发迷人。今年夏天，这里无疑是你亲近自然、尽享绿地与氧气的不二之选，以及12栋珍藏版的九龙山凯撒岛独栋别墅的功能介绍。</br>
				</p>
			<?}?>
				  </div>
				  <div id="contentshow6" style="display:none">
				<?
				$drs6 = $conn->Execute("select detail from wdata_pic where hotid='".$hotid."' and ordernum = 6 ");
				if($drs6 && !$drs6->EOF){
				?>

				  <p>
				  <? echo str_replace($order, $replace, html_entity_decode($drs6->fields["detail"], ENT_QUOTES)); ?>
				  </p>
				<?}else{?>
				<p>
					  作为顶级奢侈品的代表，目前亚洲最大的私人游艇161英尺的Aurora即将亮相九龙山庄园。本期专题LUXE为您深入解读这艘可以停载直升机的超级海上行宫。</br>
				</p>
				<?}?>
				  </div>
				</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
