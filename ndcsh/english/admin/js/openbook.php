<style type="text/css">
<!--
#mm {
	position:absolute;
	left:32px;
	top:37px;
	width:691px;
	height:340px;
	z-index:1;
}
-->
</style>
<script type="text/javascript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
																									 <div id="mm"></div>
<script language="JavaScript">
<!--

function InitAjax()
{
¡@var ajax=false; 
¡@try { 
¡@¡@ajax = new ActiveXObject("Msxml2.XMLHTTP"); 
¡@} catch (e) { 
¡@¡@try { 
¡@¡@¡@ajax = new ActiveXObject("Microsoft.XMLHTTP"); 
¡@¡@} catch (E) { 
¡@¡@¡@ajax = false; 
¡@¡@} 
¡@}
¡@if (!ajax && typeof XMLHttpRequest!='undefined') { 
¡@¡@ajax = new XMLHttpRequest(); 
¡@} 
¡@return ajax;
} 

function openbook(bookno)
{
¡@var msg = document.getElementById("mm");
¡@var url = "../";
¡@var postStr = "bg="+bg +"&pic=" +pic +"&note="+note+"&descript="+descript;
¡@var ajax = InitAjax();
¡@
¡@ajax.open("POST", url, true); 

¡@ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 

¡@ajax.send(postStr);

¡@ajax.onreadystatechange = function() { 
¡@¡@if (ajax.readyState == 4 && ajax.status == 200) { 
¡@¡@¡@mm.innerHTML = ajax.responseText; 
¡@¡@} 

¡@} 
}
//-->
</script>
