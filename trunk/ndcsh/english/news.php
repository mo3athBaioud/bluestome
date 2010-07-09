	<? 
		header('Content-type: text/html; charset=utf-8');
		require_once "conn.php"; 
		$hotid="";
		if(isset($_POST['hotid'])) { $hotid=$_POST['hotid']; }else{ if (isset($_GET['hotid'])) {$hotid=$_GET['hotid'];}}						
		$page2 = 1;
		if(isset($_POST['page2'])) { $page2=$_POST['page2']; }else{ if (isset($_GET['page2'])) {$page2=$_GET['page2'];}}				
		$start = ($page2-1)*6;
		if ($hotid == "") { 
			$tmprs=$conn->Execute("select * from wdata_e where mtype='d' and stype='b' order by hotid desc limit 0,1");
			$hotid = $tmprs->fields["hotid"];
		}
		$dmrs = $conn->Execute("select * from wdata_e where mtype = 'd' and stype = 'b' order by ordernum");		
		$mydata = $LANG."data";
		$plmrs = $conn->Execute("select count(*) as mcount from wdata_pic_e where hotid='".$hotid."'");
		$mcount2 = intval($plmrs->fields["mcount"]);	
		$order   = array("\r\n", "\n", "\r");
		$replace = '<br />';	
		
	?>    
	<SCRIPT type=text/javascript src="js/jquery.js"></SCRIPT>
	<script type="text/javascript">
		<!--
		function showPicDesc(title,content){
			var maintitle = document.getElementById("maintitle");
			maintitle.style.display='none';
			var content9 = document.getElementById("content9");
			content9.style.display = 'none';
			var titleshow = document.getElementById("titleshow");
			titleshow.style.display = '';
			titleshow.innerHTML = title;
			var contentshow = document.getElementById("contentshow");
			contentshow.style.display = '';
			contentshow.innerHTML = " <font color='#000000'>"+content+"</font>";
		}

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
	</style>
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
										Fashion
										</span>
										<a onmouseover="javascript:showCata1();"
										onmouseout="javascript:hiddenPicDesc();"
										>
										<?
											$tmpprs1 = $conn->Execute("select distinct * from wdata_pic_e where hotid='".$hotid."' 
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
										People
										</span>
										<br/>
										<a onmouseover="javascript:showCata2();"
										onmouseout="javascript:hiddenPicDesc();"
										>
										<?
											$tmpprs2 = $conn->Execute("select distinct * from wdata_pic_e where hotid='".$hotid."' 
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
										Features
										</span>
										<br/>
										<a onmouseover="javascript:showCata3();"
										onmouseout="javascript:hiddenPicDesc();"
										>
										<?
											$tmpprs3 = $conn->Execute("select distinct * from wdata_pic_e where hotid='".$hotid."' 
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
										Life
										</span>
										<a onmouseover="javascript:showCata4();"
										onmouseout="javascript:hiddenPicDesc();"
										>
										<?
											$tmpprs4 = $conn->Execute("select distinct * from wdata_pic_e where hotid='".$hotid."' 
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
										Connoisseur
										</span>
										<a onmouseover="javascript:showCata5();"
											onmouseout="javascript:hiddenPicDesc();"
										>
										<?
											$tmpprs5 = $conn->Execute("select distinct * from wdata_pic_e where hotid='".$hotid."' 
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
										Cover Story
										</span>
										<a onmouseover="javascript:showCata6();"
										onmouseout="javascript:hiddenPicDesc();"
										>
										<?
											$tmpprs6 = $conn->Execute("select distinct * from wdata_pic_e where hotid='".$hotid."' 
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
						About-品鉴LUXE
						</span>
						<span class="mainContext20" id="titleshow">
						</span>
					<?
					$trs1 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and ordernum = 1 ");
					if($trs1 && !$trs1->EOF){
					?>
						<span class="mainContext20" id="titleshow1" style="display:none" >
						<?echo $trs1->fields["title"];?>
						</span>
					<?}else{?>
						<span class="mainContext20" id="titleshow1" style="display:none" >
						Fashion
						</span>
					<?}?>
					<?
					$trs2 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and ordernum = 2 ");
					if($trs2 && !$trs2->EOF){
					?>
						<span class="mainContext20" id="titleshow2" style="display:none">
						<?echo $trs2->fields["title"];?>
						</span>
					<?
						}else{
					?>
						<span class="mainContext20" id="titleshow2" style="display:none" >
						People
						</span>
					<?}?>

					<?
					$trs3 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and ordernum = 3 ");
					if($trs3 && !$trs3->EOF){
					?>
						<span class="mainContext20" id="titleshow3" style="display:none">
						<?echo $trs3->fields["title"];?>
						</span>
					<?
						}else{
					?>
						<span class="mainContext20" id="titleshow3" style="display:none" >
						Features
						</span>
					<?}?>

					<?
					$trs4 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and ordernum = 4 ");
					if($trs4 && !$trs4->EOF){
					?>
						<span class="mainContext20" id="titleshow4" style="display:none">
						<?echo $trs4->fields["title"];?>
						</span>
					<?
						}else{
					?>
						<span class="mainContext20" id="titleshow4" style="display:none" >
						Life
						</span>
					<?}?>

					<?
					$trs5 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and ordernum = 5 ");
					if($trs5 && !$trs5->EOF){
					?>
						<span class="mainContext20" id="titleshow5" style="display:none">
						<?echo $trs5->fields["title"];?>
						</span>
					<?
						}else{
					?>
						<span class="mainContext20" id="titleshow5" style="display:none" >
						Connoisseur
						</span>
					<?}?>

					<?
					$trs6 = $conn->Execute("select * from wdata_pic_e where hotid='".$hotid."' and ordernum = 6 ");
					if($trs6 && !$trs6->EOF){
					?>
						<span class="mainContext20" id="titleshow6" style="display:none">
						<?echo $trs6->fields["title"];?>
						</span>
					<?
						}else{
					?>
						<span class="mainContext20" id="titleshow6" style="display:none" >
						Cover Story
						</span>
					<?}?>
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
					《品鉴 LUXE》 is a magazine about the luxury way of life.  Its readers are not only Nine Dragon’s club members but also the wealthy that’s after high quality lifestyle.  The magazine will also be distributed to Shanghai’s 5star hotels, high end office centers and  top drivers’ clubs.</br></br>
					《品鉴 LUXE》’ s mission is to bring  the latest international luxury lifestyle related information to its readers.  We are after quality life, we provide not only luxury brands’ latest information but also prestigious education, health, art, literature and more. 
				  </p>
					  </div>
					  <div id="contentshow" style="display:''">
					  </div>
					  <div id="contentshow1" style="display:none">
				<?
				$drs1 = $conn->Execute("select detail from wdata_pic_e where hotid='".$hotid."' and ordernum = 1 ");
				if($drs1 && !$drs1->EOF){
				?>
					  <p>
					  <? echo str_replace($order, $replace, html_entity_decode($drs1->fields["detail"], ENT_QUOTES)); ?>
					   </p>
				<?
				}else{
				?>
					<p>
						<!-- 注视中文
					   <b>Fashion News 时尚资讯</b>  给您介绍时尚品牌的最新咨询和活动派对</br>
					   <b>Hot Item 新元素</b>  每期将推荐顶级时尚潮流新品</br>
					   <b>Watch 手表</b>  本期带您走进一年一度的“奢侈品中的奥斯卡”——巴塞尔国际钟表珠宝展</br>
					   <b>Jewelry 珠宝</b> 本期为您揭秘当今世上最令人惊叹的宝石之一Wittelsbach-Graff钻石的由来和故事</br>
					   <b>Style 格调  “浪漫逃逸”</b>
					   本期的时装大片灵感来源于九龙山的游艇湾大海和自然的拥抱，我们希望让您有逃离喧闹的都市的冲动，躲进世外桃源的“天堂岛”，在这个享受阳光绚烂的季节里带着爱人放飞梦想。</br>
					   <b>Brand Story 品牌故事</b> 介绍源于意大利的奢华休闲品牌“Paul&Shark 保罗&鲨鱼”</br>
					   -->
						<b>Fashion News</b> – the latest info and events of luxury brands</br>
						<b>Hot Item</b> – latest fashion trends and products</br>
						<b>Watch</b> – in this issue, we give you a glimpse into the annual Baselworld Watch and Jewellery Show</br>
						<b>Jewelry</b> – unveiling one of the world’s most exquisite jewel - the story of the Wittelsbach-Graff diamond</br>
						<b>Style</b> -“A Romantic Escapade”
						Inspiration for this issue’s fashion spread originated from our yearning for nature, the kind of tranquility that one can only find at a paradise island called Nine Dragons Marina Bay</br>
						<b>Brand Story</b> – the story of the Italian luxury leisurewear brand“Paul & Shark”</br>
				  </p>
				<?}?>
					  </div>

					  <div id="contentshow2" style="display:none">
				<?
				$drs2 = $conn->Execute("select detail from wdata_pic_e where hotid='".$hotid."' and ordernum = 2 ");
				if($drs2 && !$drs2->EOF){
				?>
					  <p>
					  <? echo str_replace($order, $replace, html_entity_decode($drs2->fields["detail"], ENT_QUOTES)); ?>
					  </p>
				<?}else{?>
					<p>
						<b>Architect</b>-
						In current issue we present you with an exclusive interview with internationally renowned Spanish architect and landscape designer Melvin Villarroe.  His works are all over the world’s best resort destinations and is the exclusive architect to the most famous celebrities in Europe.  Melvin’s latest masterpiece – Spanish Ocean Villa will soon be revealed at Nine Dragons Estate</br>

						<b>Celebrity </b>-
						From the movie《建国大业The Foundation of a Republic》in the role of“Song Mei Ling”to the popular TV series《蜗居》as“Mrs. Song”，Vivian Wu -- American《People》magazine once hailed her as the world’s 50 most beautiful woman.</br>

						<b>Member Highlight </b>-
						Exclusive interview with the general manager of Shanghai Centre -- Mr. Byron Kan</br>
					<!--
					  <b>Architect 对话</b>
					  对话专栏本期独家采访了来自西班牙的世界级生态景观建筑师——缪文•比亚罗埃尔（Melvin Villarroel），他的建筑作品遍及世界个顶级度假胜地，是当代欧洲名流贵族御用建筑大师。Melvin最新力作——西班牙海角城堡也即将在九龙山庄园华丽呈现。</br>

					   <b>Celebrity 名人</b>
					  从《建国大业》中的“宋美龄”到热门电视剧《蜗居》里的“宋太太”，邬君梅——这位曾被美国《人物》杂志誉为50位世界最美女人，她与九龙山的渊源，与慈善事业的渊源，让她的戏外人生更精彩！</br>

					  <b>Member Highlight 会员介绍</b>
					  本期会员介绍带您认识上海商城总经理Byron Kan</br>
					  -->
				  </p>
				<?}?>
					  </div>

					  <div id="contentshow3" style="display:none">
				<?
				$drs3 = $conn->Execute("select detail from wdata_pic_e where hotid='".$hotid."' and ordernum = 3 ");
				if($drs3 && !$drs3->EOF){
				?>

					  <p>
					  <? echo str_replace($order, $replace, html_entity_decode($drs3->fields["detail"], ENT_QUOTES)); ?>
					 </p>
				<?}else{?>
					<p>
					  <b>Focus 热点 “勇敢者的运动”</b>
					  帆船被誉为“勇敢者的运动”，这些年在中国帆船运动越来越盛行，本期特别介绍了目前中国最大的几项帆船赛事和成长历程。</br>

					 <b>Experience 体验  “关注赛马”</b>
					 国内赛马业在低调中快速发展，本期带您体验和关注陌生的“赛马世界”，和了解正在举办中的速度赛马联赛信息。</br>
	 
					 <b>Topic 话题  “自由翱翔的私飞时代还远吗？</b>
					 轿车、游艇热潮渐退，人们开始把目光投向另一个热点——私人飞机。我们邀请到了庞巴迪、波音等亚太区最大的飞机经销商负责人来谈谈中国私飞时代是否已准备好起飞？</br>
				  </p>
				<?}?>
					  </div>

					  <div id="contentshow4" style="display:none">
				<?
				$drs4 = $conn->Execute("select detail from wdata_pic_e where hotid='".$hotid."' and ordernum = 4 ");
				if($drs4 && !$drs4->EOF){
				?>

					  <p>
					  <? echo str_replace($order, $replace, html_entity_decode($drs4->fields["detail"], ENT_QUOTES)); ?>
					   </p>
				<?}else{?>
					<p>
					   <b>Hot Item 新元素</b>
					   为您推荐顶级品牌生活新品</br>

					   <b>Automobile 座驾</b>
					   直击一年一度日内瓦车展盛宴</br>

					   <b>Health 养身  “贴身”的健康管理</b>
					   能拥有一位私人医生来贴身管理自己的健康逐渐成为一种潮流。本期为您解读这种全新的健康理念正悄悄走进我们的生活方式。</br>

					   <b>Art 艺苑   用青铜雕琢一个神话世界</b>
					   带您鉴赏法国艺术家理查德•塔克西埃用青铜雕琢的神话世界。这位法国国宝级的艺术家在上海的神兽主题艺术作品展让我们大开眼界，他的“神兽”作品不仅仅是雕塑品，更是人类走向心灵进程的一种演绎。</br>

					   <b>Hotel 酒店  耀目明珠落外滩</b>
					   3月28日上海半岛酒店盛大开幕</br>

					   <b>Travel 旅行</b>
					   为您和暑期中的孩子订制了可共同出游的体验路线——瑞法两周亲子夏令营</br>
				  </p>
				<?}?>
					  </div>
					  <div id="contentshow5" style="display:none">

				<?
				$drs5 = $conn->Execute("select detail from wdata_pic_e where hotid='".$hotid."' and ordernum = 5 ");
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
					$drs6 = $conn->Execute("select detail from wdata_pic_e where hotid='".$hotid."' and ordernum = 6 ");
					if($drs6 && !$drs6->EOF){
					?>

					  <p>
					  <? echo str_replace($order, $replace, html_entity_decode($drs6->fields["detail"], ENT_QUOTES)); ?>
					  </p>
					<?}else{?>
					<p>
						  作为顶级奢侈品的代表，目前亚洲最大的私人游艇161英尺的Aurora即将亮相九龙山庄园。本期专题LUXE为您深入解读这艘可以停载直升机的超级海上行宫。
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
