<?		if (file_exists("../conn.php")) {
			include('../conn.php');
		}else{
			if (file_exists("conn.php")) {
				include('conn.php');		
			}
		}	
?>

<script type="text/javascript">
<!--


function Total_preloadImages(){
	MM_preloadImages("<? echo $SHOME ?>images/add_to_faves_color.gif");
	MM_preloadImages("<? echo $SHOME ?>images/a_fave_color.gif");

}

	function myDocument_DoFSCommand(command, args) {
		 if (command == "messagebox") {
			 alert(args);
		 }
	}






function InitAjax()
{
var ajax=false; 
try { 
ajax = new ActiveXObject("Msxml2.XMLHTTP"); 
} catch (e) { 
try { 
ajax = new ActiveXObject("Microsoft.XMLHTTP"); 
} catch (E) { 
ajax = false; 
} 
}
if (!ajax && typeof XMLHttpRequest!='undefined') { 
ajax = new XMLHttpRequest(); 
} 
return ajax;
} 


function opengroup(memid)
{
	alert(memid);
var ldata = document.getElementById("ldata");
  var url = "mgroup.php?memid=" + memid;
var ajax = InitAjax();
ajax.open("GET", url, true); 
ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
ajax.send(url);
//¡@ajax.onreadystatechange = function() { 
//¡@if (ajax.readyState == 4 && ajax.status == 200) { 
//	  alert('ok');
	ldata.innerHTML="<iframe src='mgroup.php?memid" + memid + "'  width=300 height=200 frameborder='0' scrolling=no >";
//	ldata.innerHTML= ajax.responseText;

//¡@} 
}

/* allen add for get object's pagetop and pageleft */
function getposOffset(what, offsettype){
	var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
	var parentEl=what.offsetParent;
	while (parentEl!=null){
		totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
		parentEl=parentEl.offsetParent;
	}
	return totaloffset;
}
/* ------------------------------------------------ */

//-->
</script>


