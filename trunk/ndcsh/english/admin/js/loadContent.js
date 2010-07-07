var Z_TIMER_SLIDE = null;
var Z_PIX_SLIDE = 10; //this is the amount of slide/DELAY_SLIDE
var Z_NEW_PIX_VAL;
var Z_DELAY_SLIDE = 2; //this is the time between each call to slide
var Z_DIV_HEIGHT = 22; //value irrelevant
var Z_SUB_MENU_NUM =0;
var Z_ILEFT;
var B_OBJ_VIEW=null;
var windows_status=0;
var sURL="";
var ARGU="";

function load_w_move_Content(func_name,argu,div_name,divH)
{
 var ldata = document.getElementById(div_name);
 var url = func_name;
 var postStr = "page=1";
	if (windows_status == 1) {
		return;
	}
  	B_OBJ_VIEW=document.createElement("div");
	ldata.appendChild(B_OBJ_VIEW);
	B_OBJ_VIEW.zorder	= 9000;
	B_OBJ_VIEW.style.position = 'absolute';
	B_OBJ_VIEW.style.width = '0px';
	B_OBJ_VIEW.style.height = '88px';
//	var iTop = getposOffset(ldata, "top") + ldata.clientHeight - 5;
//	var iLeft = getposOffset(ldata, "left") - 6 ;
	var iTop = ldata.offsetTop + ldata.clientHeight - 5;
	var iLeft = -6 ;
	B_OBJ_VIEW.style.top = iTop;
	Z_ILEFT = iLeft;
	B_OBJ_VIEW.style.left = Z_ILEFT+"px";
    B_OBJ_VIEW.setAttribute("state","0");
    B_OBJ_VIEW.setAttribute("pix","0");	
    B_OBJ_VIEW.setAttribute("id","subContent");	
	B_OBJ_VIEW.style.border = "1px solid #cccccc"; 
	B_OBJ_VIEW.style.backgroundColor = "#e2f4b7";				
	if (B_OBJ_VIEW.addEventListener)
		B_OBJ_VIEW.addEventListener("click", MM_dragLayer, false); //invoke function

 	sURL = func_name;
	var postStr= "page=1";
	
	postStr = postStr + "&" + argu;
	Z_NEW_PIX_VAL = 0;
	if (window.Z_TIMER_SLIDE) clearInterval(Z_TIMER_SLIDE) //DD added code
    if (Z_TIMER_SLIDE == null){
//		alert(divH);
					Z_DIV_HEIGHT = divH;
					ARGU = postStr;
					Z_TIMER_SLIDE = setInterval('RunSlideforSub_W()', Z_DELAY_SLIDE);					


	  }					
      else
      {
      }	

	
}

function loadContent(func_name,argu,div_name)
{
  var waitObj = document.getElementById("iwait");
  if (waitObj) {
	  waitObj.style.display = 'inline';	   	
  }	
 var ldata = document.getElementById(div_name);
 var url = func_name;
 var postStr = "page=1";
  if ((argu.length) != 0) {
  		postStr = postStr + "&" + argu;
  }		
 var ajax = InitAjax();
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
 ajax.send(postStr);
 ajax.onreadystatechange = function() { 
  if (ajax.readyState == 4 && ajax.status == 200) { 
   	ldata.innerHTML = ajax.responseText;
		  if (waitObj) {
	  		waitObj.style.display = 'none';	   	
  		  }		
  } 

 } 
}

function getposOffset(what, offsettype){
	var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
	var parentEl=what.offsetParent;
	while (parentEl!=null){
		totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
		parentEl=parentEl.offsetParent;
	}
	return totaloffset;
}



function ReInitforSub(obj)
{
    Init(RE_INIT_OBJ);
    TIMER_SLIDE = setInterval('RunSlideforSub()', DELAY_SLIDE);
    RE_INIT_OBJ = null;
}

function RunSlideforSub_W()
{
//    var mysheet=document.styleSheets[3]
//	var myrules=mysheet.cssRules? mysheet.cssRules: mysheet.rules
    if (B_OBJ_VIEW.getAttribute("state") == 0)
    {
        Z_NEW_PIX_VAL += Z_PIX_SLIDE;
		Z_ILEFT -= Z_PIX_SLIDE;

        B_OBJ_VIEW.style.width = Z_NEW_PIX_VAL+"px";
		B_OBJ_VIEW.style.left = Z_ILEFT+"px";
        if (Z_NEW_PIX_VAL >= Z_DIV_HEIGHT) 
        {
            clearInterval(Z_TIMER_SLIDE);
            Z_TIMER_SLIDE = null;

            B_OBJ_VIEW.style.display = 'inline';
            B_OBJ_VIEW.setAttribute("state","1")
            B_OBJ_VIEW.setAttribute("pix",NEW_PIX_VAL);
			windows_status = 1;
		 	var ajax2 = InitAjax();
			 ajax2.open("POST", sURL, true); 
			 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
 			 //ajax.setRequestHeader("Content-Type","text/html; charset=big5"); 
			 ajax2.send(ARGU);
	 		 ajax2.onreadystatechange = function() { 		 
		 		  if (ajax2.readyState == 4 && ajax2.status == 200) { 
					//document.getElementById("tempcontainer").innerHTML= ajax.responseText; 
					B_OBJ_VIEW.innerHTML= ajax2.responseText; 
//					alert(ajax2.responseText);
					
				  }	
	
			} 	        			
        }
    } else {
        B_OBJ_VIEW.style.display = 'none';
        Z_NEW_PIX_VAL -= Z_PIX_SLIDE;
		Z_ILEFT += Z_PIX_SLIDE;
        if(Z_NEW_PIX_VAL > 0){
			B_OBJ_VIEW.style.width = Z_NEW_PIX_VAL+"px";
			B_OBJ_VIEW.style.left = Z_ILEFT+"px";			
		}	
        if (Z_NEW_PIX_VAL <= 0)
        {
            Z_NEW_PIX_VAL = 0;
            B_OBJ_VIEW.style.width = Z_NEW_PIX_VAL+"px";
            clearInterval(Z_TIMER_SLIDE);
            Z_TIMER_SLIDE = null;
            B_OBJ_VIEW.setAttribute("state","0");
			B_OBJ_VIEW = null;		
		}

    }
}

function erase_w_move_Content(div_name)
{
	B_OBJ_VIEW.style.display = 'none';
	B_OBJ_VIEW = null;
	windows_status = 0;	


}




function load_h_move_Content(func_name,argu,div_name,divH)
{

 var ldata = document.getElementById(div_name);
 
 var url = func_name;
 var postStr = "page=1";
	if (windows_status == 1) {return;}
  	B_OBJ_VIEW=document.createElement("div");
	ldata.appendChild(B_OBJ_VIEW);
	B_OBJ_VIEW.zorder	= 300;
	B_OBJ_VIEW.style.position = 'absolute';
	B_OBJ_VIEW.style.width = '0px';
	B_OBJ_VIEW.style.height = '88px';
	var iTop = getposOffset(ldata, "top") + ldata.clientHeight ;
	var iLeft = getposOffset(ldata, "left") +3 ;
	B_OBJ_VIEW.style.top = iTop+"px";
	Z_ILEFT = iLeft;
	B_OBJ_VIEW.style.left = Z_ILEFT+"px";
    B_OBJ_VIEW.setAttribute("state","0");
    B_OBJ_VIEW.setAttribute("pix","0");	
    B_OBJ_VIEW.setAttribute("id","subContent");	
	B_OBJ_VIEW.style.border = "1px solid #cccccc"; 
	B_OBJ_VIEW.style.background = "#e2f4b7 no-repeat 1px 1px";				
 	sURL = func_name;
	var postStr= "page=1";
	
	postStr = postStr + "&" + argu;
	Z_NEW_PIX_VAL = 0;
	if (window.Z_TIMER_SLIDE) clearInterval(Z_TIMER_SLIDE) //DD added code
	
    if (Z_TIMER_SLIDE == null){
					Z_DIV_HEIGHT = divH;
					ARGU = postStr;
					Z_TIMER_SLIDE = setInterval('RunSlideforSub_W()', Z_DELAY_SLIDE);					


	  }					
      else
      {
      }	

	
}



