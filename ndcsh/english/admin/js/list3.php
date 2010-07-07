<? include("../conn.php");
		  			if	(session_is_registered ("bookroll_memid"))  	{
						$memid =  $_SESSION["bookroll_memid"] ;
					}


		$memid = "allen@cvl.com.tw";
?>

<style type="text/css">
.menutitle{
cursor:pointer;
margin-bottom: 0px;
/*background-color:#ECECFF;
color:#000000;
width:140px;
padding:2px;
text-align:center;
font-weight:bold;
*/
 padding: 0px 0 0px 2px;

 /* top right bottom left*/ 
 width:150px;
 height:25px;
/* padding: 5px 0 4px 3px; */
 border-bottom: 0px solid #ddd;
 border-top: 1px solid #ddd; 
 text-align:left;
 background-repeat: no-repeat;
 background-position: 6px 5px;
 display: block;
 list-style: none;
 text-decoration: none; 

 font: 15px Arial, Tahoma, sans-serif #FFFFFF;
/*border:1px solid #000000;/* */
}
h3	{
 margin: 7px 15px 3px;
 background-position: 0 0;
 background-repeat: no-repeat;
 text-indent: -9999em;
 font: 1px/1px sans-serif; /* stop IE from revealing unnecessary bg */
 width: 273px;
 height: 28px;
 }
 
 .mb_title  {
 	font-family: "Verdana";
	FONT-WEIGHT: bold; 
	FONT-SIZE: 17px; 
	/*COLOR: #ff0084*/
	COLOR: #666666;
 }
 
 .img_me {
	 border: 1px solid #cc6699;
	margin: 5px 0px 2px 6px;	
}


#lselect {
/*	 margin: 0px 0px 20px 0px;*/
/*	height : auto; */
 	border: 1px solid #ebebeb; 
 }


#linkList {
 position: static;
 left: 300px; 
 width: 150px;
 font: 13px Arial, Tahoma, sans-serif;
 top: 1px;

 }
#mprofile {
	 text-align: left;
 }




.menutitle A {

 color:#666666;
 font-weight:bold;

}

.menutitle A:hover {
 border-left: 4px solid #33cc99;
 color:#666666;
 background-color: #f3f3f3;
 text-decoration: none;  
 margin: 0px 0px 0px 0px;
 padding: 0px 0 0px 3px; 
 }
.menutitle A:Link {
 color:#666666;
 background-color: #f3f3f3;
 text-decoration: none;  
 } 
.menutitle A:Visited {
 color:#666666;
 background-color: #f3f3f3;
 text-decoration: none;  
 }  
 
.menutitle A:active {
 color:#666666;
 border-left: 4px solid #33cc99;
 background-color: #f3f3f3;
 text-decoration: none;  
 margin: 0px 0px 0px 0px; 
 }  


.submenu{
 margin-bottom: 5px;

 text-decoration: none; 
 font: 13px Arial, Tahoma, sans-serif ; 
 width:130;
}
.submenu A{
 margin-bottom: 15px;
 height:auto;

}

.submenu A:hover {
 border-left: 4px solid #ffcc33;
 color:#666666; 
 background-color: #ffffff;
 text-decoration: none;  
 margin: 0px 0px 15px 0px;
 padding: 0px 0 5px 3px; 
 }
.submenu A:Link {
 color:#666666;
 background-color: #ffffff;
 text-decoration: none;  
  margin: 0px 0px 15px 0px;
 padding: 0px 0 5px 0px;   
 } 
.submenu A:Visited {
 color:#666666;
 background-color: #ffffff;
 text-decoration: none;  
 margin: 0px 0px 15px 0px; 
  padding: 0px 0 5px 0px; 
 }  
 
.submenu A:active {
 color:#666666;
 border-left: 4px solid #ffcc33;
 background-color: #ffffff;
 text-decoration: none;  
 margin: 0px 0px 15px 0px;
 padding: 0px 0 5px 0px;  
  
 }  


</style>






/***********************************************
* Switch Menu script- by Martial B of http://getElementById.com/
* Modified by Dynamic Drive for format & NS4/IE4 compatibility
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/

var persistmenu="yes" //"yes" or "no". Make sure each SPAN content contains an incrementing ID starting at 1 (id="sub1", id="sub2", etc)
var persisttype="sitewide" //enter "sitewide" for menu to persist across site, "local" for this page only

if (document.getElementById){ //DynamicDrive.com change
document.write('<style type="text/css">\n')
document.write('.submenu{display: none;}\n')
document.write('</style>\n')
}

function SwitchMenu(obj){
	if(document.getElementById){
	var el = document.getElementById(obj);
	var ar = document.getElementById("masterdiv").getElementsByTagName("span"); //DynamicDrive.com change
		if(el.style.display != "block"){ //DynamicDrive.com change
			for (var i=0; i<ar.length; i++){
				if (ar[i].className=="submenu") //DynamicDrive.com change
				ar[i].style.display = "none";
			}
			el.style.display = "block";
		}else{
			el.style.display = "none";
		}
	}
}

function get_cookie(Name) { 
var search = Name + "="
var returnvalue = "";
if (document.cookie.length > 0) {
offset = document.cookie.indexOf(search)
if (offset != -1) { 
offset += search.length
end = document.cookie.indexOf(";", offset);
if (end == -1) end = document.cookie.length;
returnvalue=unescape(document.cookie.substring(offset, end))
}
}
return returnvalue;
}

function onloadfunction(){
if (persistmenu=="yes"){
var cookiename=(persisttype=="sitewide")? "switchmenu" : window.location.pathname
var cookievalue=get_cookie(cookiename)
if (cookievalue!="")
document.getElementById(cookievalue).style.display="block"
}
}

function savemenustate(){
var inc=1, blockid=""
while (document.getElementById("sub"+inc)){
if (document.getElementById("sub"+inc).style.display=="block"){
blockid="sub"+inc
break
}
inc++
}
var cookiename=(persisttype=="sitewide")? "switchmenu" : window.location.pathname
var cookievalue=(persisttype=="sitewide")? blockid+";path=/" : blockid
document.cookie=cookiename+"="+cookievalue
}

if (window.addEventListener)
window.addEventListener("load", onloadfunction, false)
else if (window.attachEvent)
window.attachEvent("onload", onloadfunction)
else if (document.getElementById)
window.onload=onloadfunction

if (persistmenu=="yes" && document.getElementById)
window.onunload=savemenustate

</script>

																									 <div id=linkList>
		<DIV id=linkList2>
		<DIV id=lselect>

		  <SPAN class="mprofile"><img class="img_me" src = '<?PHP 
		  		     $dSQL = "";
					$dSQL = "SELECT * " . 
					" FROM member m  WHERE m.email = '" . $memid . "'"; 

			

					$query_Com = odbc_exec($conn, $dSQL);
					$row_Com = odbc_fetch_row($query_Com);
					echo "../home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'>";
					echo "		&nbsp;	<SPAN class='me_nickname_normal'>" . odbc_result($query_Com,"nickname") . "</SPAN>";		
					?>
			</SPAN>		
			
			
<!-- Keep all menus within masterdiv-->
<div id="masterdiv">

	<div class="menutitle" valign=left >
		<a href="javascript:SwitchMenu('sub1')"> my group </a></div>
	<span class="submenu" id="sub1">
		 <a href="new.htm">What's New</a><br>
		 <a href="hot.htm">What's hot</a><br>
		 <a href="revised.htm">Revised Scripts</a><br>
		 <a href="morezone/">More Zone</a>
	</span>

	<div class="menutitle" >
			<a href="javascript:SwitchMenu('sub2')">my favorite</A></div>
	<span class="submenu" id="sub2">
		 <a href="notice.htm">Usage Terms</a><br>
		 <a href="faqs.htm">DHTML FAQs</a><br>
		 <a href="help.htm">Scripts FAQs</a>
	</span>


</div>








</div>
</div>
</div>
