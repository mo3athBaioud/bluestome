// JavaScript Document
var shome="http://www.bookroll.com/";
var f = 'DXImageTransform.Microsoft.AlphaImageLoader';
var set_str="SET_DHTML(CURSOR_MOVE ,\"page_list\",\"property_area\"";
if (typeof blankImg == 'undefined') var blankImg = 'blank.gif';
function delpage(argu,page){
	var answer = confirm("Delete "+ page + " Are you sure ?");
	if (answer){
		doFuncReturnXmlv2('delpage.php',argu); 
	}	

}				 

function delobj(argu,obj){
	if (obj=="Cover") {
		alert('Cover can not be deleted !!');
		return;
	}
	var answer = confirm("Delete object ["+ obj + "], Are you sure ?");
	if (answer){
		doFuncReturnXmlv2('deleteobj.php',argu); 
	}	

}				 

function publishbook(program,argu){
	var answer = confirm("After publish, this book can not be edited again!! ,Publish this book?")
	if (answer){
		doFuncReturnXmlv2(program,argu);			
	}

}
function publishbook_batch(program,argu,obj){
	var answer = confirm("Publish this book?")
	if (answer){
		doFuncReturnXmlv2(program,argu,obj);			
	}

}

function setcolor() {
	

var oj = document.getElementById("hexBox");
var parentobj = document.getElementById("parentobj");

var po = parentobj.value;
var oo = document.getElementById(po);
var str = oj.value;
oo.value = str;
var cbox = document.getElementById("color_box");
cbox.style.background=str;
	
}

function initpicker(currcolor) {
var oo = document.getElementById(currcolor);	
currentColor = Colors.ColorFromHex(oo.value);

var parentobj = document.getElementById("parentobj");
parentobj.value=currcolor;
colorChanged('box');

}

function showpicker(programid,obj)
{
 var oo = document.getElementById(obj);
 
 poststring = "currcolor=" + oo.value;
 doFuncReturnXmlv2(programid,poststring);
}

function findPosTop(obj) {
	var curtop = 0;
	if (obj.offsetParent) {
		curtop = obj.offsetTop;
		while (obj = obj.offsetParent) {
			curtop += obj.offsetTop;
		}
	}
	return curtop;
}


function findPosLeft(obj) {
	var curleft = 0;
	if (obj.offsetParent) {
		curleft = obj.offsetLeft
		while (obj = obj.offsetParent) {
			curtop += obj.offsetLeft
		}
	}
	return curleft;
}
function setPos(parent,obj_name,myx,myy) {
		var obj = document.getElementById(obj_name);	
		y=getposOffset(parent,"top");
		x=getposOffset(parent,"left");
		var posX = "";
		var poxY = "";
		posX = x+myx;
		posY = y+myy;
		if (posX < 0) posX = 0;
		if (posY < 0) posY = 0;		
		obj.style.top=posY+'px';
		obj.style.left=posX+'px';
		obj.style.zIndex=99999;		
		
}
	

function get_obj_add_form(type,argu){
	if(type=="pic") {
		
		openprogram("add_obj_pic.php",argu,"add_obj");	
	} else if (type=="text"){
		
		
	}
	
}

function resetPage(bookid,page) {
		var temp = document.getElementById('page_content');
		if (temp != null) {

			while (temp.childNodes[0]) {
				//var sn = temp.childNodes[0].id;
				//if (sn.substr(0,5) == "child") {
				//	dd.elements.sn.del(); 
				//}// else {
				//alert('id:'+temp.childNodes[0].id);
					temp.removeChild(temp.childNodes[0]);
				//}
			}
		}
		var main = document.getElementById('main_board');
		while (main.childNodes[0]) {
			main.removeChild(main.childNodes[0]);
		}	
	
}

function objCreate(bmemid,bookid,page,pagew,pageh,oTop,oLeft,oWidth,oHeight,oPath,oType,oZindex,objid,main,content){
	  var obj = document.createElement('DIV');
	  obj.id = "child"+bookid+page+objid;	
      var newimg=document.createElement('img');
	  newimg.id="childimg"+bookid+page+objid;
/*
      if ((BrowserDetect.browser == "Explorer") && (BrowserDetect.version<7)) {
		  	if (oType == "png") {
		  	   	filt(shome+"home/"+bmemid+"/source/"+bookid+"/"+oPath, 'scale');  
				newimg.src = blankImg;
			}
	  } else {
		  	if (oType == "png") {
		  	   	filt(shome+"home/"+bmemid+"/source/"+bookid+"/"+oPath, 'scale');  
				newimg.src = blankImg;
			}		  
		  
	  }
*/	  

      if ((BrowserDetect.browser == "Explorer") && (BrowserDetect.version<7)) {
		  	if (oType == "png") {
				
				s=shome+"home/"+bmemid+"/source/"+bookid+"/"+oPath;
				
				m='scale';
				newimg.style.filter = 'progid:'+f+'(src="'+s+'",sizingMethod="'+m+'")';		
//				alert(newimg.style.filter);
 			    newimg.src=shome+'images/'+blankImg;				

			} else  newimg.src=shome+"home/"+bmemid+"/source/"+bookid+"/"+oPath;				
		  //alert("ie"+newimg.src);				
	  }	 else {
			  
		  newimg.src=shome+"home/"+bmemid+"/source/"+bookid+"/"+oPath;				
	  }
	  


      newimg.style.position = "absolute";	 
	  newimg.width=oWidth;
	  newimg.height=oHeight;
	  obj.appendChild(newimg);				
	  content.appendChild(obj);
	  
//	  main.appendChild(content);	
}

function objSetDetail(bmemid,bookid,page,pagew,pageh,oTop,oLeft,oWidth,oHeight,oPath,oType,objType,oZindex,objid,main,content){
	  var obj = document.getElementById("child"+bookid+page+objid);		
      obj.style.position = "absolute";
	  contentTop = getposOffset(content,"top");
	  contentLeft = getposOffset(content,"left");
	  

	  childTop = contentTop+oTop;
	  childLeft = contentLeft+oLeft;
	  obj.style.top = childTop+ "px";
	  obj.style.left = childLeft + "px";
	  obj.style.width=oWidth+"px";
	  obj.style.height=oHeight+"px";
	  //obj.style.background="#333333";


//	  obj.style.z-index = parseInt(oZindex) + 1000;	  
	  mytop = childTop - contentTop -1 ;
	  myleft = childLeft - contentLeft-1 ;
	  myright = (contentLeft + parseInt(pagew)) - (childLeft + oWidth) ;
	  mybottom = (contentTop + parseInt(pageh)) - (childTop + oHeight) ;
/*  
	  mytop = childTop;
	  myleft = childLeft;
	  myright = (parseInt(pagew)) - (childLeft + oWidth);
	  mybottom = (parseInt(pageh)) - (childTop + oHeight);
*/	  

//--------  set object can move in content --------------------------------------------------------
//if dd.elements.
if ((objType == "text") || (objType == "image")){
	ADD_DHTML("child"+bookid+page+objid+SCALABLE+MAXOFFTOP+mytop+MAXOFFLEFT+myleft);	
} else {
	ADD_DHTML("child"+bookid+page+objid+SCALABLE+MAXOFFTOP+mytop+MAXOFFBOTTOM+mybottom+MAXOFFLEFT+myleft+MAXOFFRIGHT+myright);
}
//dd.elements."child"+bookid+page+objid.setScalable(true);
//dd.elements["child"+bookid+page+objid].setScalable(true);

//alert(dd.elements.
		
//ADD_DHTML("child"+objid);
//-------------------------------------------------------------------------------------------------
// *************************************************************************************************				   
				   
}				   
function addsplit(bmemid,bookid,currpage,backcolor,pagew,pageh,content){
					contentTop = getposOffset(content,"top");
					contentLeft = getposOffset(content,"left");		
		var csplit = document.createElement('DIV');
		
  		csplit.id = 'csplit' + bookid + currpage;
		if (backcolor=="#999999") {
			csplit.style.border = '#333333 1px dashed'; 
		} else {
			csplit.style.border = '#999999 1px dashed'; 
		}
		var tmpH = parseInt(pageh) + 40;
		csplit.style.height = tmpH + "px";
		csplit.style.width = "0px";
	    content.appendChild(csplit);					
		var tmpTop = contentTop-20;
		var tmpLeft = contentLeft + (parseInt(pagew)/2);
	    csplit.style.position = "absolute";		
		csplit.style.top = tmpTop+ "px";
	  	csplit.style.left = tmpLeft +"px";	  	
	  	csplit.style.zIndex = 90999;	  			

//--------  set object can move in content --------------------------------------------------------
//ADD_DHTML("csplit"+VERTICAL+HORIZONTAL);
//-------------------------------------------------------------------------------------------------	
	
}

function objPut(programid,bmemid,bookid,page,pagew,pageh,main,content)
{

  var waitObj = document.getElementById("iwait");
  if (waitObj) {
	  waitObj.style.display = 'inline';	   	
  }
 var objrequest = InitAjax();
 poststring = "bmemid="+bmemid+"&bookid="+bookid+"&currpage="+page;

 url=programid;
 objrequest.open("POST", url, true); 
 objrequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 objrequest.send(poststring);
 objrequest.onreadystatechange = function() { 
 	if (objrequest.readyState == 4 && objrequest.status == 200) {
			if ((objrequest.responseText == "") || (objrequest.responseText == "error")){
					var show = document.getElementById('msg_board');
					show.innerHTML = "sorry !! load page error,please relogin then try again";
					show.style.display='inline';
			} else {
					//instantiate the W3C DOM Parser
					var parser = new DOMImplementation();

					//load the XML into the parser and get the DOMDocument
					var domDoc = parser.loadXML(objrequest.responseText);
					//get the root node (in this case, it is ROOTNODE)
					var docRoot = domDoc.getDocumentElement();
					//get the first "TAG1" element
					var firstTag1 = docRoot.getElementsByTagName("response").item(0);
					//display the data
					mypage=sprintf("%05d",page);		
					callloop = parseInt(docRoot.getElementsByTagName("response").item(0).getAttribute("objectnum")); 
					for (var x = 0; x < callloop; x++) {			
						objid = docRoot.getElementsByTagName("object").item(x).getFirstChild().getNodeValue();	
						//objid = docRoot.getElementsByTagName("object").item(x).getAttribute("objid");
						otop = docRoot.getElementsByTagName("object").item(x).getAttribute("otop");
						oleft = docRoot.getElementsByTagName("object").item(x).getAttribute("oleft");
						owidth = docRoot.getElementsByTagName("object").item(x).getAttribute("owidth");
						oheight = docRoot.getElementsByTagName("object").item(x).getAttribute("oheight");
						opath = docRoot.getElementsByTagName("object").item(x).getAttribute("opath");
						otype = docRoot.getElementsByTagName("object").item(x).getAttribute("otype");
						objtype = docRoot.getElementsByTagName("object").item(x).getAttribute("objtype");						
						ozindex = docRoot.getElementsByTagName("object").item(x).getAttribute("ozindex");
						objid=sprintf("%05d",objid);
						eval("objCreate('" + bmemid + "','" + bookid + "','" +mypage+"',"+pagew+","+pageh+","+otop+","+oleft+","+owidth+","+oheight+",'"+opath+"','"+otype+"',"+ozindex+",'"+objid+"',main,content);");
					} // for	
					main.appendChild(content);		
					addsplit(bmemid,bookid,page,backcolor,pagew,pageh,content);
					for (var x = 0; x < callloop; x++) {			
						objid = docRoot.getElementsByTagName("object").item(x).getFirstChild().getNodeValue();	
						//objid = docRoot.getElementsByTagName("object").item(x).getAttribute("objid");
						otop = docRoot.getElementsByTagName("object").item(x).getAttribute("otop");
						oleft = docRoot.getElementsByTagName("object").item(x).getAttribute("oleft");
						owidth = docRoot.getElementsByTagName("object").item(x).getAttribute("owidth");
						oheight = docRoot.getElementsByTagName("object").item(x).getAttribute("oheight");
						opath = docRoot.getElementsByTagName("object").item(x).getAttribute("opath");
						otype = docRoot.getElementsByTagName("object").item(x).getAttribute("otype");
						objtype = docRoot.getElementsByTagName("object").item(x).getAttribute("objtype");												
						ozindex = docRoot.getElementsByTagName("object").item(x).getAttribute("ozindex");				
						objid=sprintf("%05d",objid);
						//eval("var child" + objid + " = document.createElement('DIV');");
						eval("objSetDetail('" + bmemid + "','" + bookid + "','" + mypage+"',"+pagew+","+pageh+","+otop+","+oleft+","+owidth+","+oheight+",'"+opath+"','"+otype+"','"+objtype+"',"+ozindex+",'"+objid+"',main,content);");
					} // for	
			} //if
		} 
	} 
  if (waitObj) {
	  waitObj.style.display = 'none';	   	
  }	
 
}

function pageSet(setprogram,bookid,bmemid,currpage,pagew,pageh,backcolor,prvpage) {
		//for(i=0;i<dd.elements.length;i++) {
		//	if ((dd.elements[i].name != "page_list") && (dd.elements[i].name != "property_area")) {
		//		dd.elements[i].del();
		//	}
		//}
		resetPage(bookid,prvpage);

		
		var main = document.getElementById('main_board');		
		var title1 = document.createElement('DIV');
		var title1Text = document.createTextNode('Design Area');
		title1.appendChild(title1Text);
		title1.className = "text16";
		main.appendChild(title1);				
  		
		
		var content = document.createElement('DIV');



  		content.id = 'page_content';		   
		
		content.style.border = '#cccccc 1px solid'; 
		content.style.background = backcolor;
		content.style.width = pagew + "px"; 
		content.style.height = pageh + "px";
		content.style.zIndex = 90009;
		
		
// ************* handle object in page ****************************************************************** 
objPut(setprogram,bmemid,bookid,currpage,pagew,pageh,main,content);
/*
	  var child1 = document.createElement('DIV');
	  child1.id = "child1";	
      var newimg=document.createElement('img');
	  newimg.src="../images/18666967@N00.jpg";
	  newimg.alt="allen here";
	  child1.appendChild(newimg);				
	  content.appendChild(child1);
	  


	
	  main.appendChild(content);	
	
      child1.style.position = "absolute";

	  

	  childTop = 100+contentTop;
	  childLeft = 500+contentLeft;	  	  
	  child1.style.top = childTop+ "px";
	  child1.style.left = childLeft + "px";	  	  
	  mytop = childTop - contentTop;
	  myleft = childLeft - contentLeft;
	  myright = (contentLeft + parseInt(pagew)) - (childLeft + 48);
	  mybottom = (contentTop + parseInt(pageh)) - (childTop + 48);
*/
//--------  set object can move in content --------------------------------------------------------
//ADD_DHTML("child1"+MAXOFFTOP+mytop+MAXOFFBOTTOM+mybottom+MAXOFFLEFT+myleft+MAXOFFRIGHT+myright);
//-------------------------------------------------------------------------------------------------
// *************************************************************************************************




 
}



function doFuncReturnXml_wizard(programid,poststring)
{
  var waitObj = document.getElementById("iwait");
  if (waitObj) {
	  waitObj.style.display = 'inline';	   	
  }
/*  
	if (obj) {
		var fobj = eval("document.forms."+obj);
		for (i=0; i<fobj.length; i++){
		//alert(fobj.elements[i].name);
			if (fobj.elements[i].type != "button") {
				poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
			}
		}			
	}
*/	
//	var show = document.getElementById(loginarea);
 var ajax2 = InitAjax();
 poststring = poststring + "&t=1";
 url=programid;
 ajax2.open("POST", url, true); 
 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax2.send(poststring);
 ajax2.onreadystatechange = function() { 
 	if (ajax2.readyState == 4 && ajax2.status == 200) {
			//instantiate the W3C DOM Parser
			var parser = new DOMImplementation();
			//load the XML into the parser and get the DOMDocument
			var domDoc = parser.loadXML(ajax2.responseText);
			//get the root node (in this case, it is ROOTNODE)
			var docRoot = domDoc.getDocumentElement();
			//get the first "TAG1" element
			var firstTag1 = docRoot.getElementsByTagName("response").item(0);
			//display the data

			callloop = parseInt(docRoot.getElementsByTagName("response").item(0).getAttribute("callloop")); 
			msgloop = parseInt(docRoot.getElementsByTagName("response").item(0).getAttribute("msgloop"));
			for (var x = 0; x < callloop; x++) {			
				call = docRoot.getElementsByTagName("call").item(x).getFirstChild().getNodeValue();	
				switch (parseInt(call))
				{
					case 1:
						area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")										
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						openprogram(url2,"t=1",area)
						break
					case 0:
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")					
						bookid = docRoot.getElementsByTagName("call").item(x).getAttribute("bookid")
						bmemid = docRoot.getElementsByTagName("call").item(x).getAttribute("bmemid")
						pagew = docRoot.getElementsByTagName("call").item(x).getAttribute("pagew")
						pageh = docRoot.getElementsByTagName("call").item(x).getAttribute("pageh")
						backcolor = docRoot.getElementsByTagName("call").item(x).getAttribute("backcolor")
						currpage = docRoot.getElementsByTagName("call").item(x).getAttribute("currpage")
						prvpage = docRoot.getElementsByTagName("call").item(x).getAttribute("prvpage")	
						pageSet(url2,bookid,bmemid,currpage,pagew,pageh,backcolor,prvpage)
						break					

						
					case 2:
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						procedure(url2,argu2)
						break
						
					case 3:
						
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")																
						openprogram(url2,argu2,area)					
						break					
						
					case 4:
	//					message = docRoot.getElementsByTagName("message").item(0).getFirstChild().getNodeValue()
						area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")					
						showHideLayer(area,'none')					
	//					alert(message)
						break	
						
					case 5:
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")
						dofunc_obj(url2,argu2,area,obj2)
						break	
						
					case 6:  // relpace hole web page to url2
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						location.href=url2;
						break							
					case 7:  // change layer class
						myclassname = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")						
						setclassName(obj2,myclassname)					
						break												
					case 8: // change layer height
						value = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						action = docRoot.getElementsByTagName("call").item(x).getAttribute("action")
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")						
						changeHeight(obj2,value,action)					
						break						
					case 9: // eval
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						myscript=url2
						runscript()
						break												
												
				}
			}	
			for (var x = 0; x < msgloop; x++) {
					switch(parseInt(docRoot.getElementsByTagName("message").item(x).getAttribute("method"))) {
						case 1 : //show message to area
								message = docRoot.getElementsByTagName("message").item(x).getFirstChild().getNodeValue()
								msgarea = docRoot.getElementsByTagName("message").item(x).getAttribute("area")
								var show = document.getElementById(msgarea)
								show.innerHTML = message
								show.style.display='inline'
								break
						case 2 : //alert message
								message = docRoot.getElementsByTagName("message").item(x).getFirstChild().getNodeValue()
								alert(message)																								 
								break
																								 
					}
								
				
			}

		} 
	} 
 
}