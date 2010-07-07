<script type='text/javascript'>
var TIMER_SLIDE = null;
var OBJ_SLIDE;
var OBJ_VIEW;
var PIX_SLIDE = 5; //this is the amount of slide/DELAY_SLIDE
var NEW_PIX_VAL;
var DELAY_SLIDE = 10; //this is the time between each call to slide
var DIV_HEIGHT = 22; //value irrelevant
var SUB_MENU_NUM =0;
var START_DIV;
var RE_INIT_OBJ = null;
var MainDiv,SubDiv
var a_div_name = new Array();
var detail_swap_flag = new Array();
var comments_swap_flag = new Array();
var doFunc = null;
var doFunc_argu = "";
var Glb_commObj = "";
var GLb_pagew = 0;


var A_TIMER_SLIDE = null;
var A_OBJ_SLIDE;
var A_OBJ_VIEW;
var PIX_SLIDE = 5; //this is the amount of slide/DELAY_SLIDE
var A_NEW_PIX_VAL;
var DELAY_SLIDE = 30; //this is the time between each call to slide
var A_DIV_HEIGHT = 22; //value irrelevant
var SUB_MENU_NUM =0;
var A_START_DIV;
var A_RE_INIT_OBJ = null;
var A_MainDiv;
var A_SubDiv;
var a_tag_name = new Array();
var A_doFunc = new Array();
var A_doFunc_argu = "";




	function setStyleWidth(divname,value){
		var bdiv=document.getElementById(divname);
		bdiv.style.width=value;		
	
	}
	
	function setTopPosition(divname,reldivname,value) {
		alert('1111');
		var bdiv=document.getElementById(divname);
		var rdiv=document.getElementById(reldivname);
		
		bdiv.style.top = parseInt(getposOffset(rdiv, "top")) + parseInt(value) + "px"; 
	
	}
	
	


	function endMoveDiv(){ // v1.0.0 Copyright Kaosweaver
		clearInterval(document.KW_timer);
		document.KW_timer = null;
	}	
	
	function initMoveDiv(divname,reldivname,value,dir) {
	    if (!document.KW_timer) {
			document.KW_timer =window.setInterval('moveDiv(\''+divname+'\',\'' + reldivname + '\',\'' + value + '\',\'' + dir + '\');',30);
		}	
	}	
	
	function moveDiv(divname,reldivname,value,dir) {
	//dir = 1 means left
	// dir = 2 means right
		var bdiv=document.getElementById(divname);	
		var rdiv=document.getElementById(reldivname);
		var newLeft = parseInt(bdiv.style.left);
		if (parseInt(dir) == 1) {
    		newLeft = 	parseInt(bdiv.style.left) - parseInt(value);		
			if	(parseInt(newLeft) >= parseInt(bdiv.style.width)){
				return;
			}			
		} else if (parseInt(dir) == 2) {
			newLeft = 	parseInt(bdiv.style.left) + parseInt(value);						
			if	(parseFloat(bdiv.style.left) >= 0){
				return;
			}			


		}	 
				
		bdiv.style.left = newLeft;
	}

	function backChange(divname,backg){
		var bdiv=document.getElementById(divname);
		var arg="url(" + backg + ") no-repeat 1px 1px";		
		bdiv.style.background=arg;
	
	}
	
	function backChangebySwapFlag(divname,backg){
	
		for (i=0; i<a_div_name.length; i++){	
			if (("detail_cavan_" + a_div_name[i]) == divname) {		
				if (detail_swap_flag[i] != 0) { return; }
			}	
		}	
		var bdiv=document.getElementById(divname);
		var arg="url(" + backg + ") no-repeat 1px 1px";		
		bdiv.style.background=arg;
	
	}
	
	function CommentsBackChangebySwapFlag(divname,backg){
		alert(divname);
		for (i=0; i<a_div_name.length; i++){	
			if (("comments_cavan_" + a_div_name[i]) == divname) {		
				if (comments_swap_flag[i] != 0) { return; }
			}	
		}
		var bdiv=document.getElementById(divname);
		var arg="url(" + backg + ") no-repeat 1px 1px";		
		bdiv.style.background=arg;
	
	}		

function detail_swap(div_name,image_name_org,image_to_swap){
		var bdiv=document.getElementById(div_name);
		var arg = "";
		var d_flag;
		for (i=0; i<a_div_name.length; i++){
				if (("detail_cavan_" + a_div_name[i]) == div_name) {
					if (detail_swap_flag[i] == 0) {
						arg="url(" + image_to_swap + ") no-repeat 1px 1px";
						detail_swap_flag[i] = 1;
					} else {
						arg="url(" + image_name_org + ") no-repeat 1px 1px";				
						detail_swap_flag[i] = 0;		
					}

					bdiv.style.background=arg;				
				} else {
				
				}
		
		}


}

function comments_swap(div_name,image_name_org,image_to_swap){
		var bdiv=document.getElementById(div_name);
		var arg = "";
		var d_flag;
		for (i=0; i<a_div_name.length; i++){
				if (("comments_cavan_" + a_div_name[i]) == div_name) {
					if (comments_swap_flag[i] == 0) {
						arg="url(" + image_to_swap + ") no-repeat 1px 1px";
						comments_swap_flag[i] = 1;
					} else {
						arg="url(" + image_name_org + ") no-repeat 1px 1px";				
						comments_swap_flag[i] = 0;		
					}

					bdiv.style.background=arg;				
				} else {
				
				
				}
		
		}


}


function view_comments_logo_swap(div_name,image_name_org,image_to_swap){
		var bdiv=document.getElementById(div_name);
		var arg = "";
		var d_flag;
//		for (i=0; i<a_div_name.length; i++){
//				if (("detail_cavan_" + a_div_name[i]) == div_name) {
//					if (detail_swap_flag[i] == 0) {
//						arg="url(" + image_to_swap + ") no-repeat 1px 1px";
//						detail_swap_flag[i] = 1;
//					} else {
//						arg="url(" + image_name_org + ") no-repeat 1px 1px";				
//						detail_swap_flag[i] = 0;		
//					}

//					bdiv.style.background=arg;				
//				} else {
//				
//				
//				}
		
//		}


}


function Init_M(start_div,func_to_do,argu)
{
    if (TIMER_SLIDE == null)
    {
	alert('aa1');
		objDiv = document.getElementById(start_div);
        SUB_MENU_NUM = 0;
        MainDiv = objDiv.parentNode;
	alert('2');		
        SubDiv =  MainDiv.getElementsByTagName("DIV").item(0);  //topItem
        action_D = document.getElementById(MainDiv.getAttribute("actionObj"));
		START_DIV = start_div;
	alert('3');
        OBJ_SLIDE = MainDiv.getElementsByTagName("DIV").item(1);  //dropMenu
        OBJ_VIEW = OBJ_SLIDE.getElementsByTagName("DIV").item(0);  //subMenu
		doFunc = func_to_do;
		doFunc_argu = argu;
        action_D.onclick = SetSlide_M;		 		
        NEW_PIX_VAL = parseInt(MainDiv.getAttribute("state")); 
	alert('4');		
    }

}

function Init_Mail(start_div)
{
    if (TIMER_SLIDE == null)
    {
		objDiv = document.getElementById(start_div);
        SUB_MENU_NUM = 0;
        MainDiv = objDiv.parentNode;

        SubDiv =  MainDiv.getElementsByTagName("DIV").item(0);  //topItem
        action_D = document.getElementById(MainDiv.getAttribute("actionObj"));
		START_DIV = start_div;

        OBJ_SLIDE = MainDiv.getElementsByTagName("DIV").item(1);  //dropMenu
        OBJ_VIEW = OBJ_SLIDE.getElementsByTagName("DIV").item(0);  //subMenu
		doFunc = null;
		doFunc_argu = null;
        action_D.onclick = SetSlide_M;		 		
        NEW_PIX_VAL = parseInt(MainDiv.getAttribute("state")); 
    }

}



function Init_Input_actionObj(start_div,func_to_do,argu,actionObj)
{
    if (TIMER_SLIDE == null)
    {
		objDiv = document.getElementById(start_div);
        SUB_MENU_NUM = 0;
        MainDiv = objDiv.parentNode;
		
        SubDiv =  MainDiv.getElementsByTagName("DIV").item(0);  //topItem
        action_D = document.getElementById(actionObj);
		START_DIV = start_div;

        OBJ_SLIDE = MainDiv.getElementsByTagName("DIV").item(1);  //dropMenu
        OBJ_VIEW = OBJ_SLIDE.getElementsByTagName("DIV").item(0);  //subMenu
		doFunc = func_to_do;
		doFunc_argu = argu;
        action_D.onclick = SetSlide_M;		 		
        NEW_PIX_VAL = parseInt(MainDiv.getAttribute("state")); 
    }

}

function Init_Comment(start_div,func_to_do,argu,actionObj,commObj,pagew)
{
    if (TIMER_SLIDE == null)
    {
		objDiv = document.getElementById(start_div);
        SUB_MENU_NUM = 0;
        MainDiv = objDiv.parentNode;
		
        SubDiv =  MainDiv.getElementsByTagName("DIV").item(0);  //topItem
        action_D = document.getElementById(actionObj);
		START_DIV = start_div;

        OBJ_SLIDE = MainDiv.getElementsByTagName("DIV").item(1);  //dropMenu
        OBJ_VIEW = OBJ_SLIDE.getElementsByTagName("DIV").item(0);  //subMenu
		doFunc = func_to_do;
		doFunc_argu = argu;
		Glb_commObj = commObj;
		Glb_pagew = pagew;
        action_D.onclick = SetSlide_Comm;		 		
        NEW_PIX_VAL = parseInt(MainDiv.getAttribute("state")); 

		
    }

}

function SetSlide_Comm()
{   
 	var url = "";
	var postStr= "page=1";
	if (OBJ_VIEW.getAttribute("argu")) {
		postStr = postStr + "&" + OBJ_VIEW.getAttribute("argu");
	}
	if (OBJ_VIEW.getAttribute("callprocess")) {
		url = OBJ_VIEW.getAttribute("callprocess");
	}
	OBJ_VIEW.style.display = 'none';	
 	var ajax = InitAjax();
	  if (window.TIMER_SLIDE) clearInterval(TIMER_SLIDE) //DD added code
      if (TIMER_SLIDE == null ) {
	    if (OBJ_VIEW.getAttribute("state") == 0){
			 url = OBJ_VIEW.getAttribute("callprocess");
			 ajax.open("POST", url, true); 
			 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=UTF-8"); 
 			 //ajax.setRequestHeader("Content-Type","text/html; charset=big5"); 
			 ajax.send(postStr);
	 		 ajax.onreadystatechange = function() { 		 

		 		  if (ajax.readyState == 4 && ajax.status == 200) { 
					OBJ_VIEW.innerHTML= ajax.responseText; 
					DIV_HEIGHT=48; //DD added code	  
					DIV_HEIGHT = OBJ_VIEW.getElementsByTagName("SPAN").item(0).getAttribute("proheight");//get innerHtml's height
//					DIV_HEIGHT = document.getElementById("pro").getAttribute("proheight");//get innerHtml's height

		            TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);
			        var bdiv = document.getElementById(Glb_commObj);
					bdiv.style.left = 0;					
					bdiv.style.width = 5000;					
				  }	
	
			} 	        
		} else {
            TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);		
		}
		


	  }					
      else
      {

		  //TIMER_SLIDE = null;
          setTimeout('ReInit_M()', 200);
      }
}



function SetSlide_M()
{   
 	var url = "";
	var postStr= "page=1";
	if (OBJ_VIEW.getAttribute("argu")) {
		postStr = postStr + "&" + OBJ_VIEW.getAttribute("argu");
	}
	if (OBJ_VIEW.getAttribute("callprocess")) {
		url = OBJ_VIEW.getAttribute("callprocess");
	}
	OBJ_VIEW.style.display = 'none';	
 	var ajax = InitAjax();
	  if (window.TIMER_SLIDE) clearInterval(TIMER_SLIDE) //DD added code
      if (TIMER_SLIDE == null ) {
	    if (OBJ_VIEW.getAttribute("state") == 0){
			 url = OBJ_VIEW.getAttribute("callprocess");
			 ajax.open("POST", url, true); 
			 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=UTF-8"); 
 			 //ajax.setRequestHeader("Content-Type","text/html; charset=big5"); 
			 ajax.send(postStr);
	 		 ajax.onreadystatechange = function() { 		 

		 		  if (ajax.readyState == 4 && ajax.status == 200) { 
					OBJ_VIEW.innerHTML= ajax.responseText; 
					DIV_HEIGHT=48; //DD added code	  
					DIV_HEIGHT = OBJ_VIEW.getElementsByTagName("SPAN").item(0).getAttribute("proheight");//get innerHtml's height
//					DIV_HEIGHT = document.getElementById("pro").getAttribute("proheight");//get innerHtml's height

		            TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);
				  }	
	
			} 	        
		} else {
            TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);		
		}
		


	  }					
      else
      {

		  //TIMER_SLIDE = null;
          setTimeout('ReInit_M()', 200);
      }
}

function ReInit_M()
{
    Init_M(START_DIV);
    TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);
}

function Init(objDiv)
{
    if (TIMER_SLIDE == null)
    {

        SUB_MENU_NUM = 0;
        MainDiv = objDiv.parentNode;
        SubDiv =  MainDiv.getElementsByTagName("DIV").item(0);  //topItem
        SubDiv.onclick = SetSlide;
        
        OBJ_SLIDE = MainDiv.getElementsByTagName("DIV").item(1);  //dropMenu
        OBJ_VIEW = OBJ_SLIDE.getElementsByTagName("DIV").item(0);  //subMenu
//				document.getElementById("tempcontainer").innerHTML=MainDiv.getElementsByTagName("DIV").item(2).innerHTML //DD added code		

//		DIV_HEIGHT=document.getElementById("tempcontainer").offsetHeight //DD added code
/*		
        for (i=0;i<OBJ_VIEW.childNodes.length;i++)
        {
            if (OBJ_VIEW.childNodes.item(i).tagName == "SPAN")
            {
                SUB_MENU_NUM ++;
                OBJ_VIEW.childNodes.item(i).onmouseover= ChangeStyle;
                OBJ_VIEW.childNodes.item(i).onmouseout= ChangeStyle;
            }
        }   
 */       
              NEW_PIX_VAL = parseInt(MainDiv.getAttribute("state")); 
    }

}

function SetSlideforSub()
{   

 	var url = "";
	var postStr= "page=1";
	
	if (OBJ_VIEW.getAttribute("argu")) {
		postStr = postStr + "&" + OBJ_VIEW.getAttribute("argu");
	}	
 	var ajax = InitAjax();
	  if (window.TIMER_SLIDE) clearInterval(TIMER_SLIDE) //DD added code

      if (TIMER_SLIDE == null && this == MainDiv){
			 url = OBJ_VIEW.getAttribute("callprocess");
			 ajax.open("POST", url, true); 
			 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=UTF-8"); 
 			 //ajax.setRequestHeader("Content-Type","text/html; charset=big5"); 
			 ajax.send(postStr);
	 		 ajax.onreadystatechange = function() { 		 

		 		  if (ajax.readyState == 4 && ajax.status == 200) { 
					//document.getElementById("tempcontainer").innerHTML= ajax.responseText; 
					OBJ_VIEW.innerHTML= ajax.responseText; 
//					alert(ajax.responseText);
					DIV_HEIGHT=48; //DD added code	  
					DIV_HEIGHT = OBJ_VIEW.getElementsByTagName("SPAN").item(0).getAttribute("proheight");//get innerHtml's height

				  }	
	
			} 	        

            TIMER_SLIDE = setInterval('RunSlideforSub()', DELAY_SLIDE);
	  }					
      else
      {
          RE_INIT_OBJ = this;
          setTimeout('ReInitforSub()', 200);
      }
}

function RunSlideforSub()
{
	  var mysheet=document.styleSheets[3]
	  var myrules=mysheet.cssRules? mysheet.cssRules: mysheet.rules

    if (OBJ_VIEW.getAttribute("state") == 0)
    {

        NEW_PIX_VAL += PIX_SLIDE;

        OBJ_SLIDE.style.width = NEW_PIX_VAL;

        if (NEW_PIX_VAL >= DIV_HEIGHT) 
        {

            clearInterval(TIMER_SLIDE);
            TIMER_SLIDE = null;
			
            OBJ_VIEW.style.display = 'inline';
            OBJ_VIEW.setAttribute("state","1")
            MainDiv.setAttribute("state",NEW_PIX_VAL);
			SubDiv.style.background = "url(" + shome + "images/context_open.gif) #eeeeee no-repeat 1px 1px";
        }
    } else
    {
        OBJ_VIEW.style.display = 'none';
        NEW_PIX_VAL -= PIX_SLIDE;
        if(NEW_PIX_VAL > 0)OBJ_SLIDE.style.height = NEW_PIX_VAL;
        if (NEW_PIX_VAL <= 0)
        {
            NEW_PIX_VAL = 0;
            OBJ_SLIDE.style.height = NEW_PIX_VAL
            clearInterval(TIMER_SLIDE);
            TIMER_SLIDE = null;
            OBJ_VIEW.setAttribute("state","0")
            MainDiv.setAttribute("state",NEW_PIX_VAL);
			SubDiv.style.background = "url(" + shome + "images/context_closed.gif) #eeeeee no-repeat 1px 1px";			
        }
    }
}


function SetSlide()
{   
 	var url = "";
	var postStr= "page=1";
	
	if (OBJ_VIEW.getAttribute("argu")) {
		postStr = postStr + "&" + OBJ_VIEW.getAttribute("argu");
	}
	if (OBJ_VIEW.getAttribute("callprocess")) {
		url = OBJ_VIEW.getAttribute("callprocess");
	}
    if (OBJ_VIEW.getAttribute("state") == 0) {	
		OBJ_VIEW.style.display = 'none';	
	} else {
		OBJ_VIEW.style.display = 'inline';			
	}	
 	var ajax = InitAjax();
	  if (window.TIMER_SLIDE) clearInterval(TIMER_SLIDE) //DD added code
      if (TIMER_SLIDE == null ){
			 url = OBJ_VIEW.getAttribute("callprocess");
			 ajax.open("POST", url, true); 
			 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=UTF-8"); 
 			 //ajax.setRequestHeader("Content-Type","text/html; charset=big5"); 
			 ajax.send(postStr);
	 		 ajax.onreadystatechange = function() { 		 

		 		  if (ajax.readyState == 4 && ajax.status == 200) { 
					//document.getElementById("tempcontainer").innerHTML= ajax.responseText; 
					OBJ_VIEW.innerHTML= ajax.responseText; 
					DIV_HEIGHT=48; //DD added code	  
					DIV_HEIGHT = OBJ_VIEW.getElementsByTagName("SPAN").item(0).getAttribute("proheight");//get innerHtml's height
		            TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);

				  }	
	
			} 	        



	  }					
      else
      {
          RE_INIT_OBJ = this;
          setTimeout('ReInit()', 200);
      }
}

function ReInit(obj)
{
    Init(RE_INIT_OBJ);
    TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);
    RE_INIT_OBJ = null;
}

function ReInitforSub(obj)
{
    Init(RE_INIT_OBJ);
    TIMER_SLIDE = setInterval('RunSlideforSub()', DELAY_SLIDE);
    RE_INIT_OBJ = null;
}



function RunSlide()
{
	  var mysheet=document.styleSheets[3]
	  var myrules=mysheet.cssRules? mysheet.cssRules: mysheet.rules
    if (OBJ_VIEW.getAttribute("state") == 0)
    {
        NEW_PIX_VAL += PIX_SLIDE;
        OBJ_SLIDE.style.height = NEW_PIX_VAL;
		OBJ_SLIDE.style.width = parseInt(OBJ_VIEW.getAttribute("pagew"));
        if (NEW_PIX_VAL >= DIV_HEIGHT) 
        {
            clearInterval(TIMER_SLIDE);
            TIMER_SLIDE = null;
            OBJ_VIEW.style.display = 'inline';
            OBJ_VIEW.setAttribute("state","1")
            MainDiv.setAttribute("state",NEW_PIX_VAL);
			if (doFunc) {
//				alert(doFunc + "(" + doFunc_argu + ")" +";");			
				eval(doFunc + "(" + doFunc_argu + ")" +";");
			}	 
			//SubDiv.style.background = "url(" + shome + "images/context_open.gif) #eeeeee no-repeat 1px 1px";
        }
    } else
    {
	
        NEW_PIX_VAL -= PIX_SLIDE;
        if(NEW_PIX_VAL > 0)OBJ_SLIDE.style.height = NEW_PIX_VAL;
        if (NEW_PIX_VAL <= 0)
        {
            NEW_PIX_VAL = 0;
            OBJ_SLIDE.style.height = NEW_PIX_VAL
            clearInterval(TIMER_SLIDE);
            TIMER_SLIDE = null;
            OBJ_VIEW.setAttribute("state","0")
            MainDiv.setAttribute("state",NEW_PIX_VAL);
	        OBJ_VIEW.style.display = 'none';
			if (doFunc) {
//				alert(doFunc + "(" + doFunc_argu + ")" +";");
				eval(doFunc + "(" + doFunc_argu + ")" +";");
			}	 								
		//SubDiv.style.background = "url(" + shome + "images/context_closed.gif) #eeeeee no-repeat 1px 1px";			
        }
    }
}




function ChangeStyle()
{
    if (this.className == this.getAttribute("classOut"))
        this.className = this.getAttribute("classOver");
    else
        this.className = this.getAttribute("classOut");
}
/*---------------------------------------------------------------------------*/



</script>