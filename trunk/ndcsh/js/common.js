// JavaScript Document
var T1=null;
var T2=null;
var T3=null;
//var DELAY_SLIDE = 1;
//var SPLIT_W = 40;

//var DELAY_SLIDE2 = 5;
//var SPLIT_W2 = 5;

var DELAY_SLIDE = 16;
var SPLIT_W = 50;

var DELAY_SLIDE2 = 10;
var SPLIT_W2 = 10;

var OBJ1_NAME = "";
var OBJ2_NAME = "";
var IMG1="";
var MYIMG1="";
var IMG2="";
var MYIMG2="";
var MAX_W1 = 676;
var MAX_W2 = 100;
var H_W = 0;
var D_W = 0;
var PV;
var PV_H=0;
var PV_RH = 0;
var SPLIT_PV = 10;
var DELAY_SLIDE_PV = 10;

var ACT_OBJ_NAME = "content1";
var ACT_IMG_NAME = "";
var ACT_IMG = "";

function zoominpic(obj){
	obj.style.width=177;	
	obj.style.height=126;	
}

function zoomoutpic(obj){
	obj.style.width=147;	
	obj.style.height=105;	
}


function action(objh,objd,imgh,imgd,myimgh,myimgd,orimageh,orimaged) {
		if ((T1 != null) || ( T2 != null)) { return; }
		var tmpobj=document.getElementById(objd);
//		var tmpobj2=document.getElementById(objd);
		//if ((tmpobj.style.display == 'none') && (tmpobj2.style.display == 'none')) {
		//	return;	
		//}
		if (tmpobj.style.display == 'none') { // objd ready to display
			OBJ1_NAME = ACT_OBJ_NAME;
			OBJ2_NAME = objd;	
			IMG1 = ACT_IMG_NAME;
			MYIMG1 = ACT_IMG;
			IMG2 = imgd;
			MYIMG2 = myimgd;		
			H_W = MAX_W1+MAX_W2;	
			D_W = 0;
			ACT_OBJ_NAME = objd;
			ACT_IMG_NAME = imgd;
			ACT_IMG = orimaged;
			

			T1 = setInterval('RunSlideHide()', DELAY_SLIDE);	
			T2 = setInterval('RunSlideDisplay()', DELAY_SLIDE);			
		} else { //  objh ready to display
			OBJ1_NAME = ACT_OBJ_NAME;
			OBJ2_NAME = objh;		
			IMG1 = ACT_IMG_NAME;
			MYIMG1 = ACT_IMG;
			IMG2 = imgh;
			MYIMG2 = myimgh;		
			H_W = MAX_W1+MAX_W2;	
			D_W = 0;
			ACT_OBJ_NAME = objh;	
			ACT_IMG_NAME = imgh;
			ACT_IMG = orimageh;			
			T1 = setInterval('RunSlideHide()', DELAY_SLIDE);	
			T2 = setInterval('RunSlideDisplay()', DELAY_SLIDE);			
			

		}
			if (OBJ1_NAME == "content6") {
				var aobj=document.getElementById("p6");				
				if (aobj){
					aobj.style.display='none';
				}
			}
			if (OBJ1_NAME == "content7") {
				var aobj=document.getElementById("p7");				
				if (aobj){				
					aobj.style.display='none';
				}
			}					
}

function RunSlideHide(){
		var obj1=document.getElementById(OBJ1_NAME);
		obj1.style.zIndex=0;
		H_W = H_W - SPLIT_W;
		if (H_W <= MAX_W2) {
			H_W = MAX_W2;
			obj1.style.width=H_W+'px';
            clearInterval(T1);
            T1 = null;			
        	T1 = setInterval('RunSlideHide2()', DELAY_SLIDE2);	

		} else {
				obj1.style.width=H_W+'px';	
		}
}

function RunSlideHide2(){
		var obj1=document.getElementById(OBJ1_NAME);
		if (IMG1 != "") {
			var imgs=document.getElementById(IMG1);					
		} 
		obj1.style.zIndex=0;
		H_W = H_W - SPLIT_W2;
		if (H_W <= 0) {
			H_W = 0;
			obj1.style.width=H_W+'px';
			obj1.style.display='none';
			if (IMG1 != "") {
				imgs.src=MYIMG1;			
			} 
            clearInterval(T1);
            T1 = null;			
		} else {
				obj1.style.width=H_W+'px';	
		}
}

function RunSlideDisplay(){
		var obj2=document.getElementById(OBJ2_NAME);	
		obj2.style.zIndex=9999;		
		D_W = D_W + SPLIT_W;
		if (D_W >= MAX_W1) {
			D_W = MAX_W1;
			obj2.style.width=D_W+'px';
			obj2.style.display='block';
            clearInterval(T2);
            T2 = null;				
        	T2 = setInterval('RunSlideDisplay2()', DELAY_SLIDE2);							
		} else {
			obj2.style.width=D_W+'px';
			obj2.style.display='block';			
		}		

}

function RunSlideDisplay2(){
		var obj2=document.getElementById(OBJ2_NAME);	
		if (IMG2 != "") {		
			var imgs=document.getElementById(IMG2);	
		} 
		obj2.style.zIndex=9999;		
		D_W = D_W + SPLIT_W2;
		if (D_W >= MAX_W2+MAX_W1) {
			
			if (OBJ2_NAME == "content6") {
				var aobj=document.getElementById("p6");				
				if (aobj){
					aobj.style.display='block';
				}
			}
			if (OBJ2_NAME == "content7") {
				var aobj=document.getElementById("p7");				
				if (aobj){				
					aobj.style.display='block';
				}
			}					
			
			
			D_W = MAX_W1+MAX_W2;
			obj2.style.width=D_W+'px';
			obj2.style.display='block';
			if (IMG2 != "") {					
				imgs.src=MYIMG2;
			} 
            clearInterval(T2);
            T2 = null;						
		} else {
			obj2.style.width=D_W+'px';
			obj2.style.display='block';			
		}		

}



function display_pic(p,pic,h){
			PV=document.getElementById(p+"_preview");	
			PV.style.display='block';
			var pc=document.getElementById(p+"_content");				
			pc.innerHTML="<img src='upload/"+pic+"' width='260' />";
			if (IE) {
				PV.style.left='315px';
				PV.style.top='43px';				
				
			} else {
				PV.style.left='524px';
				PV.style.top='169px';
			}
//			alert(h);
			//PV_H = h;
			//PV_RH=0;
        	//T3 = setInterval('run_display()', DELAY_SLIDE_PV);	
	
}



