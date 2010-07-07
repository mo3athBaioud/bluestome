<? require_once "conn.php"; 

	$mrs = $conn->Execute("select * from jobs_e");
$order   = array("\r\n", "\n", "\r");
$replace = '<br />';		
?>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>NDC</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_nbGroup(event, grpName) { //v6.0
  var i,img,nbArr,args=MM_nbGroup.arguments;
  if (event == "init" && args.length > 2) {
    if ((img = MM_findObj(args[2])) != null && !img.MM_init) {
      img.MM_init = true; img.MM_up = args[3]; img.MM_dn = img.src;
      if ((nbArr = document[grpName]) == null) nbArr = document[grpName] = new Array();
      nbArr[nbArr.length] = img;
      for (i=4; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {
        if (!img.MM_up) img.MM_up = img.src;
        img.src = img.MM_dn = args[i+1];
        nbArr[nbArr.length] = img;
    } }
  } else if (event == "over") {
    document.MM_nbOver = nbArr = new Array();
    for (i=1; i < args.length-1; i+=3) if ((img = MM_findObj(args[i])) != null) {
      if (!img.MM_up) img.MM_up = img.src;
      img.src = (img.MM_dn && args[i+2]) ? args[i+2] : ((args[i+1])? args[i+1] : img.MM_up);
      nbArr[nbArr.length] = img;
    }
  } else if (event == "out" ) {
    for (i=0; i < document.MM_nbOver.length; i++) {
      img = document.MM_nbOver[i]; img.src = (img.MM_dn) ? img.MM_dn : img.MM_up; }
  } else if (event == "down") {
    nbArr = document[grpName];
    if (nbArr)
      for (i=0; i < nbArr.length; i++) { img=nbArr[i]; img.src = img.MM_up; img.MM_dn = 0; }
    document[grpName] = nbArr = new Array();
    for (i=2; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {
      if (!img.MM_up) img.MM_up = img.src;
      img.src = img.MM_dn = (args[i+1])? args[i+1] : img.MM_up;
      nbArr[nbArr.length] = img;
  } }
}
//-->
</script>
</head>
<body>																									
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<table width="1005" height="660" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1005" height="660"> <table width="1005" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td width="196"><table width="196" border="0" cellpadding="0" cellspacing="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tr> 
                <td rowspan="2"> <img name="n01_r1_c1" src="images/01_r1_c1.jpg" width="30" height="104" border="0" alt=""> 
                </td>
                <td rowspan="2" colspan="2"><img name="n01_r1_c2" src="images/01_r1_c2.jpg" width="166" height="104" border="0" alt=""></td>
              </tr>
              <tr> </tr>
            </table></td>
          <td width="756"><table width="756" border="0" cellpadding="0" cellspacing="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tr> 
                <td colspan="8"><img name="n01_r1_c4" src="images/01_r1_c4.jpg" width="756" height="84" border="0" alt=""></td>
              </tr>
            </table>
            <table width="756" border="0" cellpadding="0" cellspacing="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tr> 
                <td width="508"><table width="100%" height="20" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="95%"> <div align="right"> 
                          <p class="TopContext"><img src="images/arrow.jpg" width="20" height="20" align="absmiddle"><a href="#">English</a></p>
                        </div></td>
                      <td width="5%">&nbsp;</td>
                    </tr>
                  </table></td>
                <td width="80"><a href="about.php" target="_self" onClick="MM_nbGroup('down','group1','n01_r2_c5','',1)" onMouseOver="MM_nbGroup('over','n01_r2_c5','images/01_r2_c5_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img name="n01_r2_c5" src="images/01_r2_c5.jpg" border="0" alt=""></a></td>
                <td width="84"><a href="contact.php" target="_self" onClick="MM_nbGroup('down','group1','n01_r2_c8','',1)" onMouseOver="MM_nbGroup('over','n01_r2_c8','images/01_r2_c8_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img name="n01_r2_c8" src="images/01_r2_c8.jpg" width="84" height="20" border="0" alt=""></a></td>
                <td width="84"><a href="join.php" target="_self" onClick="MM_nbGroup('down','group1','_72','',1)" onMouseOver="MM_nbGroup('over','_72','images/72_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img src="images/72.jpg" name="_72" width="84" height="20" border="0"></a></td>
              </tr>
            </table></td>
          <td width="53"><table width="53" border="0" cellpadding="0" cellspacing="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tr> 
                <td rowspan="2"><img name="n01_r1_c12" src="images/01_r1_c12.jpg" width="53" height="104" border="0" alt=""></td>
              </tr>
              <tr> </tr>
            </table></td>
        </tr>
      </table>
      <table width="1005" height="30" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="30">
		  <table width="30" border="0" cellpadding="0" cellspacing="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tr> 
                <td><img name="n01_r3_c1" src="images/01_r3_c1.jpg" width="30" height="495" border="0" alt=""></td>
              </tr>
            </table>
		  </td>
          <td width="922">
		  <table width="922" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="799">
				<div id="content1" style=" overflow:hidden"> 
                    <table width="776" border="0" cellpadding="0" cellspacing="0">
                      <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                      <tr> 
                        <td><table width="776" height="495" border="0" cellpadding="0" cellspacing="0">
                            <tr> 
                              <td width="776" valign="top" background="images/73.jpg"><table width="100%" height="290" border="0" cellpadding="0" cellspacing="0" style="z-index:0">
                                  <tr> 
                                    <td height="290">&nbsp;</td>
                                  </tr>
                                </table>
                                <table width="760" height="34" border="0" cellpadding="0" cellspacing="2" style="z-index:0">
                                  <tr> 
                                    <td width="43" height="30">&nbsp;</td>
                                    <td width="194" valign="top" class="mainContext12"><span class="mainContext20" style="z-index:1">JOIN US</span></td>
                                    <td width="515" valign="top" class="mainContext12"><span class="mainContext20" style="z-index:1"><font color="720019"></font></span></td>
                                  </tr>
                                </table>
                                <table width="760" height="23" border="0" cellpadding="0" cellspacing="2">
                                  <tr> 
                                    <td width="44" height="19">&nbsp;</td>
                                    <td width="308" class="mainContext12"><strong><font color="#000000">Vacancies：</font></strong></td>
                                    <td width="416" valign="top" class="mainContext12">&nbsp; 
                                    </td>
                                  </tr>
                                </table> 
                                <table width="760" height="86" border="0" cellpadding="0" cellspacing="2">
                                  <tr> 
                                    <td width="43" height="19">&nbsp;</td>
                                    <td rowspan="4" valign="top" class="mainContext12"><strong></strong>
									
                                      <table width="300" border="0" cellpadding="0" cellspacing="0">
									  <tr>
<?	$cc=1;
	while ($mrs && !$mrs->EOF) { 	
			if (($cc%4) == 1) {
	
	?>		
	 <td valign="top">
					<table border="0" cellspacing="0" cellpadding="0">												  
                                        
	<?		}  ?>						
										<tr>			
                                          <td valign="top">
										<table width="111" border="0" cellspacing="2" cellpadding="0">
                                        <tr> 
                                          <td width="13"><img src="images/arrow3.gif" width="5" height="5"></td>
                                          <td width="92" class="mainContext12"><a href="join_1.php?hotid=<? echo $mrs->fields["hotid"]; ?>"><? echo $mrs->fields["f1"]; ?></a></td>
                                        </tr>
                                      </table>										  
										  </td>
										</tr>										  
<? 			
			if (($cc%4) == 0) {				?>						  
                      </table>   
					</td>					  
<? 			}
		$cc++;	
		$mrs->moveNext();
	}
	if ($cc > 1) {	
		echo "</table></td>";
	}
?>						
	
									</tr>
                                      </table>
									  
								    </td>
                                    <td rowspan="4" valign="top" class="mainContext12">&nbsp;</td>
                                  </tr>
                                  <tr> 
                                    <td height="19">&nbsp;</td>
                                  </tr>
                                  <tr> 
                                    <td height="19">&nbsp;</td>
                                  </tr>
                                  <tr> 
                                    <td height="19">&nbsp;</td>
                                  </tr>
                                </table>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td><img src="images/spacer.gif" width="8" height="8"></td>
                                  </tr>
                                </table>
                                <table width="760" height="23" border="0" cellpadding="0" cellspacing="2">
                                  <tr> 
                                    <td width="43" height="19">&nbsp;</td>
                                    <td width="42" valign="top" class="mainContext12"><strong><font color="#000000">Contact：</font></strong></td>
                                    <td width="667" valign="top" class="mainContext12">Please send your resume to <a href="mailto:contact@ndcsh.com"><font color="720019">contact@ndcsh.com</font></a>, email subject please state Application for Vacancies.<br>
                                      We will arrange interview for you at our earliest</td>
                                  </tr>
                                </table></td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                  </div>  
				</td>
                <td width="10">
				<div id="btn1" style="cursor:pointer" onClick="javascript:action('content1','content2','','n01_r3_c2','','images/01_r3_c2_1.jpg','','images/01_r3_c2.jpg');">
					<table width="24" border="0" cellpadding="0" cellspacing="0">
                    <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                    <tr> 
                      <td><span style="cursor:pointer"><img name="n01_r3_c2" id="n01_r3_c2" src="images/01_r3_c2.jpg" width="24" height="495" border="0" alt=""></span></td>
                    </tr>
                  </table>
				</div>  
				</td>
				<td>
				<div id="content2" style="width:0px; display:none; overflow:hidden; z-index:20;">
				<? include "service.php"; ?>
				</div>					
			    </td>
                <td width="25">
<div id="btn2" style="cursor:pointer" onClick="javascript:action('content2','content3','n01_r3_c2','n01_r3_c6','images/01_r3_c2_1.jpg','images/01_r3_c6_1.jpg','images/01_r3_c2.jpg','images/01_r3_c6.jpg');">								
				<table width="25" border="0" cellpadding="0" cellspacing="0">
                    <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                    <tr> 
                      <td><span style="cursor:pointer" ><img name="n01_r3_c6" id="n01_r3_c6" src="images/01_r3_c6.jpg" width="25" height="495" border="0" alt=""></span></td>
                    </tr>
              </table>
				  </div>				  
			    </td>
				  <td>
				<div id="content3" style="width:0px; display:none; overflow:hidden; z-index:30;">
				<? include "introduction.php"; ?>
				</div>									  				  
				  </td>
                <td width="25">
<div id="btn3" style="cursor:pointer" onClick="javascript:action('content3','content4','n01_r3_c6','n01_r3_c7','images/01_r3_c6_1.jpg','images/01_r3_c7_1.jpg','images/01_r3_c6.jpg','images/01_r3_c7.jpg');">								
				<table width="25" border="0" cellpadding="0" cellspacing="0">
                    <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                    <tr> 
                      <td><span ><img name="n01_r3_c7" id="n01_r3_c7" src="images/01_r3_c7.jpg" width="25" height="495" border="0" alt=""></span></td>
                    </tr>
              </table>
</div>				
			    </td>
				  <td>
				<div id="content4" style="width:0px; display:none; overflow:hidden; z-index:30;">
				<? include "club.php"; ?>
				</div>									  				  
				  </td>
                <td width="24">
<div id="btn4" style="cursor:pointer" onClick="javascript:action('content4','content5','n01_r3_c7','n01_r3_c9','images/01_r3_c7_1.jpg','images/01_r3_c9_1.jpg','images/01_r3_c7.jpg','images/01_r3_c9.jpg');">												
				<table width="24" border="0" cellpadding="0" cellspacing="0">
                    <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                    <tr> 
                      <td><span ><img name="n01_r3_c9" id="n01_r3_c9" src="images/01_r3_c9.jpg" width="24" height="495" border="0" alt=""></span></td>
                    </tr>
              </table>
				  </div>
			    </td>
				  <td>
				<div id="content5" style="width:0px; display:none; overflow:hidden; z-index:30;">
				<? include "customer.php"; ?>
				</div>									  				  
				  </td>				  
                <td width="24">
<div id="btn5" style="cursor:pointer" onClick="javascript:action('content5','content6','n01_r3_c9','n01_r3_c10','images/01_r3_c9_1.jpg','images/01_r3_c10_1.jpg','images/01_r3_c9.jpg','images/01_r3_c10.jpg');">																
				<table width="24" border="0" cellpadding="0" cellspacing="0">
                    <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                    <tr> 
                      <td><span ><img name="n01_r3_c10" id="n01_r3_c10" src="images/01_r3_c10.jpg" width="24" height="495" border="0" alt=""></span></td>
                    </tr>
              </table>
</div>
			    </td>
				  <td>
				<div id="content6" style="width:0px; display:none; overflow:hidden; z-index:30;">
				<? include "projects.php"; ?>
				</div>
				</td>									  				  				  
                <td width="24">
<div id="btn6" style="cursor:pointer" onClick="javascript:action('content6','content7','n01_r3_c10','n01_r3_c11','images/01_r3_c10_1.jpg','images/01_r3_c11_1.jpg','images/01_r3_c10.jpg','images/01_r3_c11.jpg');">																				
				<table border="0" cellpadding="0" cellspacing="0" width="24">
                    <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
                    <tr> 
                      <td><span ><img name="n01_r3_c11" id="n01_r3_c11" src="images/01_r3_c11.jpg" width="24" height="495" border="0" alt=""></span></td>
                    </tr>
              </table>
</div>				  
			    </td>
				  <td>
				<div id="content7" style="width:0px; display:none; overflow:hidden; z-index:30;">
				<? include "news.php"; ?>
				</div>
				</td>				  
              </tr>
            </table>
		  </td>
          <td width="53"><table width="53" border="0" cellpadding="0" cellspacing="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tr> 
                <td><img name="n01_r3_c12" src="images/01_r3_c12.jpg" width="53" height="495" border="0" alt=""></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="1005" height="23" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="10"><img name="n01_r4_c1" src="images/01_r4_c1.jpg" width="30" height="61" border="0" alt=""></td>
          <td width="492"><table width="705" height="23" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="33"><a href="http://www.vocuis.com/" target="_blank" onClick="MM_nbGroup('down','group1','_01_r4_c2','',1)" onMouseOver="MM_nbGroup('over','_01_r4_c2','images/01_r4_c2_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img src="images/01_r4_c2.jpg" name="_01_r4_c2" width="115" height="27" border="0"></a></td>
                <td width="972"><img src="images/01_r4_c4.jpg" width="807" height="27"></td>
              </tr>
              <tr> 
                <td><img src="images/01_r5_c2.jpg" width="115" height="34"></td>
                <td><img src="images/01_r5_c4.jpg" width="807" height="34"></td>
              </tr>
            </table></td>
          <td width="503"><img name="n01_r4_c12" src="images/01_r4_c12.jpg" width="53" height="61" border="0" alt=""></td>
        </tr>
      </table>
      
    </td>
  </tr>
</table>
</body>
</html>
