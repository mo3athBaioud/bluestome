<script type="text/javascript">
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
</script>