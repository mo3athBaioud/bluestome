<? 
require_once "conn.php"; 

$mrs = $conn->Execute("select * from tbl_ads where d_status = 1 limit 0,1");
	
$order   = array("\r\n", "\n", "\r");
$replace = '<br />';	
?>  
<!--
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>NDC</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--
<link href="demo/style.css" rel="stylesheet" type="text/css">
-->
<style>
#fW_Content {
	MARGIN: 50px 25px; WIDTH: 1005px; DISPLAY: inline; FLOAT: left; HEIGHT: 440px
}
#fW_Content IMG {
	WIDTH: 1005px; HEIGHT: 460px
}
/**
**/
A:link {
	COLOR: #666666;
	TEXT-DECORATION: none;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
A:visited {
	COLOR: #666666;
	TEXT-DECORATION: none;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
A:active {
	COLOR: #666666;
	TEXT-DECORATION: none;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
A:hover {
	COLOR: #D20000;
	TEXT-DECORATION: underline;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif";
	font-size: 9pt;
}
</style>
<SCRIPT type=text/javascript src="js/jquery.js"></SCRIPT>

<SCRIPT type=text/javascript src="js/accordion.js"></SCRIPT>

<SCRIPT type=text/javascript src="js/cycle.js"></SCRIPT>
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
<body bgcolor="#FFFFFF">
<TABLE class=bg2 border=0 cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width=1005px align=center>
        <TBODY>
        <TR>
          <TD>

<table width="800px" cellspacing="0" cellpadding="0" border="0">
        <tbody><tr> 
          <td width="196">
		  <table width="196" cellspacing="0" cellpadding="0" border="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tbody><tr> 
                <td rowspan="2">
					<img width="30" height="104" border="0" alt="" src="images/01_r1_c1.jpg" name="n01_r1_c1">
				</td>
                <td colspan="2" rowspan="2"><img width="166" height="104" border="0" alt="" src="images/01_r1_c2.jpg" name="n01_r1_c2"></td>
              </tr>
              <tr> </tr>
            </tbody></table></td>
          <td width="756">
		  <table width="756" cellspacing="0" cellpadding="0" border="0">
              <tbody>
			  <tr> 
                <td colspan="8" width="456">
				<img width="456" height="84" border="0" alt="" src="images/01_r1_c4.jpg" name="n01_r1_c4">
				</td>
              </tr>
            </tbody></table>
            <table width="756" cellspacing="0" cellpadding="0" border="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tbody><tr> 
                <td width="508">
				<table width="100%" height="20" cellspacing="0" cellpadding="0" border="0">
                    <tbody><tr> 
                      <td width="95%"> <div align="right"> 
                          <p class="TopContext">
							  <img width="20" height="20" align="absmiddle" src="images/arrow.jpg">
							  <a href="index.php">中文</a>
							  <img width="20" height="20" align="absmiddle" src="images/arrow.jpg">
							  <a href="english/index.php">English</a>
						  </p>
                        </div></td>
                      <td width="5%">&nbsp;</td>
                      <td width="5%">&nbsp;</td>
                    </tr>
                  </tbody>
				  </table>
				  </td>
			    <!-- 顶部栏与中间FLASH的高度差 -->
				<td height="30">
					&nbsp;
				</td>
                <td width="80"><a href="about.php" target="_top" onClick="MM_nbGroup('down','group1','n01_r2_c5','',1)" onMouseOver="MM_nbGroup('over','n01_r2_c5','images/01_r2_c5_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img name="n01_r2_c5" src="images/01_r2_c5.jpg" border="0" alt=""></a></td>
                <td width="84"><a href="contact.php" target="_top" onClick="MM_nbGroup('down','group1','n01_r2_c8','',1)" onMouseOver="MM_nbGroup('over','n01_r2_c8','images/01_r2_c8_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img name="n01_r2_c8" src="images/01_r2_c8.jpg" width="84" height="20" border="0" alt=""></a></td>
                <td width="84"><a href="join.php" target="_top" onClick="MM_nbGroup('down','group1','_72','',1)" onMouseOver="MM_nbGroup('over','_72','images/72_1.jpg','',1)" onMouseOut="MM_nbGroup('out')"><img src="images/72.jpg" name="_72" width="84" height="20" border="0"></a></td>
              </tr>
            </tbody></table></td>
          <td width="53">
		  <table width="53" cellspacing="0" cellpadding="0" border="0">
              <!-- fwtable fwsrc="未命名" fwbase="01.jpg" fwstyle="Dreamweaver" fwdocid = "221452389" fwnested="0" -->
              <tbody><tr> 
                <td rowspan="2"><img width="53" height="104" border="0" alt="" src="images/01_r1_c12.jpg" name="n01_r1_c12"></td>
              </tr>
              <tr> </tr>
            </tbody></table></td>
        </tr>
      </tbody></table>		 
         </div>
			<div align="center">
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="1005" height="440">
			<?if($mrs && !$mrs->EOF){?>
              <param name="movie" value="<?echo "/upload/".$mrs->fields["d_ads_file_name"];?>">
			 <?}else{?>
              <param name="movie" value="index.swf">
			 <?}?>
              <param name="quality" value="high">
			  <PARAM NAME="wmode" VALUE="transparent">
			  </object>	
			  </div>
			  </TD>
			</TR>
			<tr>
                <td width="492">
					<table width="705" height="20" cellspacing="0" cellpadding="0" border="0" align="center">
						<tbody>
							<tr> 
							<td width="10">
								<img width="30" height="27" border="0" alt="" src="images/01_r4_c1_01.jpg" name="n01_r4_c1">
							</td>
							  <td width="33">
								  <a href="about.php">
								  <img width="115px" border="0" height="27" src="images/index_foot_left.jpg"/>
								  </a>
                               </td>
							   <td width="972">
								  <img width="807px" height="27" src="images/index_foot_right.jpg"/>
								</td>
							 </td>
							</tr>
						</tbody>
					  </table>
				  </td>
          </tr></TBODY></TABLE></TD>
		  </TR>
		</TBODY></TABLE>
</body>
</html>
