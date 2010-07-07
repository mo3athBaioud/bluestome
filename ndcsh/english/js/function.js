	var IE = document.all?true:false
	var IE = false;
	var NS = false;
	var MZ = false;
	var OP = false;
      if (navigator.appName == "Microsoft Internet Explorer"){IE = true;}
      else if (navigator.appName == "Navigator" || navigator.appName == "Netscape"){NS=true;}
      else if (navigator.appName == "Mozilla"){MZ=true;}
	  else { OP = true; }
/* func list
	InitAjax()
	
	menuSwap(activeno,total_menu,iclass_active,iclass_deactive);
	contentSwap(obj,content)	
	getposOffset(what, offsettype)


	openprogram2(programid,poststring,loginarea,obj)
	openprogram(programid,poststring,loginarea)
	
	doFuncReturnXmlv2(programid,poststring,obj)	
	
*/	

var myscript="";




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




function openprogram_inline(programid,poststring,loginarea)
{
	
 var show = document.getElementById(loginarea);
 var ajax3 = InitAjax();
// poststring = poststring + "";
 url=programid;
 ajax3.open("POST", url, true); 
 ajax3.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax3.send(poststring);
 ajax3.onreadystatechange = function() { 
 	if (ajax3.readyState == 4 && ajax3.status == 200) {
  		show.innerHTML=ajax3.responseText;
		show.style.display='inline';
	} 

 } 
}



	function changeBorder(divname,value){
		var bdiv=document.getElementById(divname);
		bdiv.style.border=value;		
	
	}
	
	function setBackColor(divname,value){
		var bdiv=document.getElementById(divname);
		bdiv.style.background=value;		
	
	}	
	
	function changeHeight(divname,value,action){
		var bdiv=document.getElementById(divname);
		if (action == 'add') {
			bdiv.style.height=bdiv.clientHeight+parseInt(value)+"px";
		} else if (action = 'self') {
			bdiv.style.height=value+"px";
			
		}
	
	}	
	
	function showHideLayer(divname,value){
		var bdiv=document.getElementById(divname);
		bdiv.style.display=value;		
	
	}	
	
function runscript() {

	eval(myscript);
}
	

function remote_login(programid,loginarea,url)
{
 var show = document.getElementById(loginarea);
 var ajax = InitAjax();

 url = url + "loginform.php";
 poststring="programid=1";
  
 ajax.open("POST", url, true); 

// ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);

 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;
		show.style.display="inline";
	} 

 } 


}

function mygenerate_wysiwyg(programid,poststring,loginarea,genobj,width)
{
 var show2 = document.getElementById(loginarea);
 var ajax2 = InitAjax();
 poststring = poststring + "&t=1";
 url=programid;
 ajax2.open("POST", url, true); 
 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax2.send(poststring);
 ajax2.onreadystatechange = function() { 
 	if (ajax2.readyState == 4 && ajax2.status == 200) {
  		show2.innerHTML=ajax2.responseText;
		show2.style.display='inline';
		if (width){
			wysiwygWidth = parseInt(width);
		}
		generate_wysiwyg(genobj);
		document.getElementById('wysiwyg'+genobj).contentWindow.focus();
		wysiwyg
	} 

 } 
}

function openprogram2(programid,poststring,loginarea,obj)
{

var show = document.getElementById(loginarea);
	var fobj = eval("document.forms."+obj);
	var show = document.getElementById(loginarea);
	var ajax = InitAjax();
	for (i=0; i<fobj.length; i++){
		poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
	}	

	url=programid;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
	ajax.send(poststring);
	ajax.onreadystatechange = function() { 
 		if (ajax.readyState == 4 && ajax.status == 200) {
//			alert( ajax.responseText);
  			show.innerHTML=ajax.responseText;
		} 
	} 
 
}



function send_mail(programid,poststring,loginarea,obj)
{
	doFuncReturnXmlv2(programid,poststring,obj);
//	changeHeight(loginarea,'30','self');
	
	
}




function proxy_openprogram(programid,poststring,loginarea,obj,desthost,iwait,aaa)
{
  
  if (iwait) {
	  var waitObj = document.getElementById("iwait");	
	  waitObj.style.display = 'inline';	   	  
  }	  
    var show = document.getElementById(loginarea);
	var fobj = eval("document.forms."+obj);
	var show = document.getElementById(loginarea);
	var ajax = InitAjax();
	for (i=0; i<fobj.length; i++){
		poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
	}	
	url=programid+"?"+poststring;
	url = encodeURIComponent(url); 
	desthost = encodeURIComponent(desthost); 
	urlcall="proxy_curl.php?hostname=" + desthost + "&path=" + url;	
	ajax.open("GET", urlcall, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
	ajax.send(null);
	ajax.onreadystatechange = function() { 
 		if (ajax.readyState == 4 && ajax.status == 200) {
  			show.innerHTML=ajax.responseText;
			show.style.display = 'inline';
			  if (iwait) {			
				  waitObj.style.display = 'none';	     			
			  }	  
		} 
	} 
 
}


function callLogin(programid,argu,loginarea) {
		doFuncReturnXmlv2(programid,"a=1","form1");
	
}

function callLogin2(programid,argu,loginarea) {
		doFuncReturnXmlv2(programid,argu,"form1");
	
}

function callLogin3(programid,argu) {
		doFuncReturnXmlv2(programid,argu,"form1");
	
}




function procedure(programid,poststring)
{
 var ajax2 = InitAjax();
 poststring = poststring + "&t=1";
 url=programid;
 ajax2.open("POST", url, true); 
 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax2.send(poststring);
 ajax2.onreadystatechange = function() { 
 	if (ajax2.readyState == 4 && ajax2.status == 200) {
	} 

 } 
}




function dofunc(programid,poststring)
{
 var ajax2 = InitAjax();
 url=programid;
 ajax2.open("POST", url, true); 
 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax2.send(poststring);
 ajax2.onreadystatechange = function() { 
 	if (ajax2.readyState == 4 && ajax2.status == 200) {
//  		show.innerHTML=ajax2.responseText;
	} 

 } 
}


function dofunc_obj(programid,poststring,loginarea,obj)
{


	var fobj = eval("document.forms."+obj);
	var show = document.getElementById(loginarea);
	var ajax = InitAjax();
	for (i=0; i<fobj.length; i++){
		if( fobj.elements[i].type == 'radio') {
				if (fobj.elements[i].checked) {
					poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
				}
		} else {
				poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
		}
	}	
	url=programid;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
	ajax.send(poststring);
	ajax.onreadystatechange = function() { 
 		if (ajax.readyState == 4 && ajax.status == 200) {
//			alert( ajax.responseText);
  			show.innerHTML=ajax.responseText;
			show.style.display = 'inline';			
		} 
	} 
 
}

function dofunc_nortn(programid,poststring)
{
 	
 var ajax = InitAjax();
 url=programid;
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		//show.innerHTML=ajax.responseText;
		//show.style.display = 'inline';		
	} 

 } 
}

function dofunc2(loginarea,programid,poststring,tl)
{
  var t = document.getElementById("h_title");		
  t.innerHTML = tl; 	
  
 var show = document.getElementById(loginarea);
 var ajax = InitAjax();
 url=programid;
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;
		show.style.display = 'inline';		
	} 

 } 
}

function refreshprogram(programid,poststring,loginarea)
{
//	alert(programid);
	
 var show = document.getElementById(loginarea);
 var ajax2 = InitAjax();
 poststring = poststring + "&t=1";
// alert(poststring);
 url=programid;
 ajax2.open("POST", url, true); 
 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax2.send(poststring);
 ajax2.onreadystatechange = function() { 
 	if (ajax2.readyState == 4 && ajax2.status == 200) {
  		show.innerHTML=ajax2.responseText;
	} 

 } 
}


function openprogram(programid,poststring,loginarea,p)
{
//	alert(programid);
	
 var show = document.getElementById(loginarea);
 var ajax2 = InitAjax();
 poststring = poststring + "&t=1";
// alert(poststring);
 url=programid;
 ajax2.open("POST", url, true); 
 ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
 ajax2.send(poststring);
 ajax2.onreadystatechange = function() { 
 	if (ajax2.readyState == 4 && ajax2.status == 200) {
  		show.innerHTML=ajax2.responseText;
		show.style.display='block';
		if (p) {
			 var show2 = document.getElementById(p);			
			 show2.style.display='inline';
		}
	} 

 } 
}



function checkemail2(str){

var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
if (filter.test(str))
testresults=true
else{
//alert("Please input a valid email address!")
testresults=false
}
return (testresults)
}


function emailCheck (emailStr) {
/* The following pattern is used to check if the entered e-mail address
   fits the user@domain format.  It also is used to separate the username
   from the domain. */
var emailPat=/^(.+)@(.+)$/
/* The following string represents the pattern for matching all special
   characters.  We don't want to allow special characters in the address. 
   These characters include ( ) < > @ , ; : \ " . [ ]    */
var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]"
/* The following string represents the range of characters allowed in a 
   username or domainname.  It really states which chars aren't allowed. */
var validChars="\[^\\s" + specialChars + "\]"
/* The following pattern applies if the "user" is a quoted string (in
   which case, there are no rules about which characters are allowed
   and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
   is a legal e-mail address. */
var quotedUser="(\"[^\"]*\")"
/* The following pattern applies for domains that are IP addresses,
   rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
   e-mail address. NOTE: The square brackets are required. */
var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/
/* The following string represents an atom (basically a series of
   non-special characters.) */
var atom=validChars + '+'
/* The following string represents one word in the typical username.
   For example, in john.doe@somewhere.com, john and doe are words.
   Basically, a word is either an atom or quoted string. */
var word="(" + atom + "|" + quotedUser + ")"
// The following pattern describes the structure of the user
var userPat=new RegExp("^" + word + "(\\." + word + ")*$")
/* The following pattern describes the structure of a normal symbolic
   domain, as opposed to ipDomainPat, shown above. */
var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$")


/* Finally, let's start trying to figure out if the supplied address is
   valid. */

/* Begin with the coarse pattern to simply break up user@domain into
   different pieces that are easy to analyze. */
var matchArray=emailStr.match(emailPat)
if (matchArray==null) {
  /* Too many/few @'s or something; basically, this address doesn't
     even fit the general mould of a valid e-mail address. */
	//alert("Email address seems incorrect (check @ and .'s)")
	return false
}
var user=matchArray[1]
var domain=matchArray[2]

// See if "user" is valid 
if (user.match(userPat)==null) {
    // user is not valid
    //alert("The username doesn't seem to be valid.")
    return false
}

/* if the e-mail address is at an IP address (as opposed to a symbolic
   host name) make sure the IP address is valid. */
var IPArray=domain.match(ipDomainPat)
if (IPArray!=null) {
    // this is an IP address
	  for (var i=1;i<=4;i++) {
	    if (IPArray[i]>255) {
	        //alert("Destination IP address is invalid!")
		return false
	    }
    }
    return true
}

// Domain is symbolic name
var domainArray=domain.match(domainPat)
if (domainArray==null) {
	//alert("The domain name doesn't seem to be valid.")
    return false
}

/* domain name seems valid, but now make sure that it ends in a
   three-letter word (like com, edu, gov) or a two-letter word,
   representing country (uk, nl), and that there's a hostname preceding 
   the domain or country. */

/* Now we need to break up the domain to get a count of how many atoms
   it consists of. */
var atomPat=new RegExp(atom,"g")
var domArr=domain.match(atomPat)
var len=domArr.length
if (domArr[domArr.length-1].length<2 || 
    domArr[domArr.length-1].length>3) {
   // the address must end in a two letter or three letter word.
   //alert("The address must end in a three-letter domain, or two letter country.")
   return false
}

// Make sure there's a host name preceding the domain.
if (len<2) {
   var errStr="This address is missing a hostname!"
   //alert(errStr)
   return false
}

// If we've gotten this far, everything's valid!
return true;
}
//  End -->

function loadContentByXml(programid,poststring,obj)
{
//var show = document.getElementById(loginarea);
	if (obj) {
		var fobj = eval("document.forms."+obj);
		for (i=0; i<fobj.length; i++){
		//alert(fobj.elements[i].name);
			poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
		}			
	}	
//	var show = document.getElementById(loginarea);
	var ajax = InitAjax();

	url=programid;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
//	ajax.setRequestHeader("Content-Type","text/html; charset=UTF-8");  	
	ajax.send(poststring);
	ajax.onreadystatechange = function() { 
 		if (ajax.readyState == 4 && ajax.status == 200) {
			
			//instantiate the W3C DOM Parser
			var parser = new DOMImplementation();
			//load the XML into the parser and get the DOMDocument
			var domDoc = parser.loadXML(ajax.responseText);
			//get the root node (in this case, it is ROOTNODE)
			var docRoot = domDoc.getDocumentElement();
			//get the first "TAG1" element
			var firstTag1 = docRoot.getElementsByTagName("response").item(0);
			//display the data
			//it should be "Hello World"
			var area = "";
			var url2 = "";
			var argu2 = "";			
			area = docRoot.getElementsByTagName("area").item(0).getFirstChild().getNodeValue();
			call = docRoot.getElementsByTagName("call").item(0).getFirstChild().getNodeValue();
			if (call == 1) {
				url2 = docRoot.getElementsByTagName("url2").item(0).getFirstChild().getNodeValue();
//				argu2 = docRoot.getElementsByTagName("argu2").item(0).getFirstChild().getFirstChild().getData();
//				openprogram("http://192.168.16.81/br/mybookroll/mbroll.php","t=1","mainpage");
				openprogram(url2,"t=1",area);
				
			} else {
				message = docRoot.getElementsByTagName("message").item(0).getFirstChild().getNodeValue();
				var show = document.getElementById(area);
				show.innerHTML = message;
			}
//			} catch (e) {
//					alert("An exception occurred in the script. Error name: " + e.name 
//					+ ". Error message: " + e.message); 
//			}			

		} 
	} 
 
}

function getCheckedValueDefaultZero(radioObj) {
	if(!radioObj)
		return "0";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "0";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "0";
}


function doFuncReturnXmlv2(programid,poststring,obj)
{
	
  var waitObj = document.getElementById("iwait");
  if (waitObj) {
	  waitObj.style.display = 'inline';	   	
  }
//var show = document.getElementById(loginarea);
	if (obj) {
		var fobj = eval("document.forms."+obj);
		for (i=0; i<fobj.length; i++){
		//alert(fobj.elements[i].name);
			if (fobj.elements[i].type != "button") {
				poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
			}
		}			
	}	
//	var show = document.getElementById(loginarea);

	var ajax = InitAjax();
	url=programid;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
//	ajax.setRequestHeader("Content-Type","text/html; charset=UTF-8");  	
	ajax.send(poststring);
	ajax.onreadystatechange = function() {
 		if (ajax.readyState == 4 && ajax.status == 200) {
			//instantiate the W3C DOM Parser
			var parser = new DOMImplementation();
			//load the XML into the parser and get the DOMDocument
			var domDoc = parser.loadXML(ajax.responseText);
			//get the root node (in this case, it is ROOTNODE)
			var docRoot = domDoc.getDocumentElement();
			//get the first "TAG1" element
			var firstTag1 = docRoot.getElementsByTagName("response").item(0);
			//display the data
			var area = "";
			//url2 = "";
			var argu2 = "";			
			callloop = parseInt(docRoot.getElementsByTagName("response").item(0).getAttribute("callloop")); 
			msgloop = parseInt(docRoot.getElementsByTagName("response").item(0).getAttribute("msgloop"));
			
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
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						//area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")																
						doFuncReturnXmlv2(url2,argu2)					
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
					case 9: // for wizard
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
//						alert(argu2)
						doFuncReturnXml_wizard(url2,argu2)
						break
						
					case 10: // eval
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						//argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
//						alert(url2);
						eval("'"+url2+"'");
						break		
						
					case 11: // set focus
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")
						//url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						var myobj = document.getElementById(obj2)
						myobj.focus()
						break		
					case 12: // color picker
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						initpicker(argu2)
						break		
					case 13: // close
						area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")
						//argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						closeBox(area)
						break		
					case 14: // generate_wysiwyg
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")					
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")
						argu3 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu3")						
						//argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						mygenerate_wysiwyg(url2,argu2,area,obj2,argu3)
						break								
						
					case 15: // set wysiwyg editor focus
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")
						//url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						var myobj = document.getElementById(obj2).contentWindow;
						myobj.focus()
						break								
											
				}
			}	

		  if (waitObj) {
			waitObj.style.display = 'none';	   				
		  }
			
//			} catch (e) {
//					alert("An exception occurred in the script. Error name: " + e.name 
//					+ ". Error message: " + e.message); 
//			}			

		} 
	} 
 
}

function doFuncReturnXmlv3(programid,poststring,obj)
{
//	alert(programid);
  var waitObj = document.getElementById("iwait");
  if (waitObj) {
	  waitObj.style.display = 'inline';	   	
  }
//var show = document.getElementById(loginarea);
	if (obj) {
		var fobj = eval("document.forms."+obj);
		for (i=0; i<fobj.length; i++){
		//alert(fobj.elements[i].name);
			if (fobj.elements[i].type != "button") {
				poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
			}
		}			
	}	
//	var show = document.getElementById(loginarea);
	var ajax = InitAjax();
	url=programid;
//	alert(poststring);
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","multipart/form-data");
//	ajax.setRequestHeader("Content-Type","text/html; charset=UTF-8");  	
	ajax.send(poststring);
	ajax.onreadystatechange = function() {
 		if (ajax.readyState == 4 && ajax.status == 200) {
//			alert(ajax.responseText);
			//instantiate the W3C DOM Parser
			var parser = new DOMImplementation();
			//load the XML into the parser and get the DOMDocument
			var domDoc = parser.loadXML(ajax.responseText);
			//get the root node (in this case, it is ROOTNODE)
			var docRoot = domDoc.getDocumentElement();
			//get the first "TAG1" element
			var firstTag1 = docRoot.getElementsByTagName("response").item(0);
			//display the data
			var area = "";
			//url2 = "";
			var argu2 = "";			

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
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						//area = docRoot.getElementsByTagName("call").item(x).getAttribute("area")																
						doFuncReturnXmlv2(url2,argu2)					
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
					case 9: // for wizard
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						doFuncReturnXml_wizard(url2,argu2)
						break
						
					case 10: // eval
						url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						//argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						eval(url2)
						break		
						
					case 11: // set focus
						obj2 = docRoot.getElementsByTagName("call").item(x).getAttribute("obj")
						//url2 = docRoot.getElementsByTagName("call").item(x).getAttribute("url2")
						var myobj = document.getElementById(obj2)
						myobj.focus()
						break		
					case 12: // color picker
						argu2 = docRoot.getElementsByTagName("call").item(x).getAttribute("argu2")
						initpicker(argu2)
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
		  if (waitObj) {
			waitObj.style.display = 'none';	   				
		  }
			
//			} catch (e) {
//					alert("An exception occurred in the script. Error name: " + e.name 
//					+ ". Error message: " + e.message); 
//			}			

		} 
	} 
 
}



function doFuncReturnXml(programid,poststring,obj)
{
//var show = document.getElementById(loginarea);
	if (obj) {
		var fobj = eval("document.forms."+obj);
		for (i=0; i<fobj.length; i++){
		//alert(fobj.elements[i].name);
			poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
		}			
	}	
//	var show = document.getElementById(loginarea);
	var ajax = InitAjax();


	url=programid;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
//	ajax.setRequestHeader("Content-Type","text/html; charset=UTF-8");  	
	ajax.send(poststring);
	ajax.onreadystatechange = function() { 
 		if (ajax.readyState == 4 && ajax.status == 200) {
			
			//instantiate the W3C DOM Parser
			var parser = new DOMImplementation();
			//load the XML into the parser and get the DOMDocument
			var domDoc = parser.loadXML(ajax.responseText);
			//get the root node (in this case, it is ROOTNODE)
			var docRoot = domDoc.getDocumentElement();
			//get the first "TAG1" element
			var firstTag1 = docRoot.getElementsByTagName("response").item(0);
			//display the data
			//it should be "Hello World"
			var area = "";
			var url2 = "";
			var argu2 = "";			
			area = docRoot.getElementsByTagName("area").item(0).getFirstChild().getNodeValue();
			call = docRoot.getElementsByTagName("call").item(0).getFirstChild().getNodeValue();
			switch (call)
			{
				case 1:
					url2 = docRoot.getElementsByTagName("url2").item(0).getFirstChild().getNodeValue();
					openprogram(url2,"t=1",area);
				    break;
				case 0:
					message = docRoot.getElementsByTagName("message").item(0).getFirstChild().getNodeValue();
					var show = document.getElementById(area);
					show.innerHTML = message;
					break;
				case 2:
					message = docRoot.getElementsByTagName("message").item(0).getFirstChild().getNodeValue();
					alert(message);
					break;
			}
//			} catch (e) {
//					alert("An exception occurred in the script. Error name: " + e.name 
//					+ ". Error message: " + e.message); 
//			}			

		} 
	} 
 
}

function setclassName(mm,iclass){
	var objz = document.getElementById(mm);		
	objz.className=iclass;	
}


function menuSwap(activeno,total_menu,iclass_active,iclass_deactive){
	var objz;
	var menu_name="";
	for (var i=1;i<=parseInt(total_menu);i++){
		menu_name = "menu" + i;
		objz = document.getElementById(menu_name);
		if (i==parseInt(activeno)){
			objz.className=iclass_active;	
		} else {
			objz.className=iclass_deactive;				
		}
	}
}


function contentSwap(obj,content){
	var objz = document.getElementById(obj);
	objz.innerHTML = content;	

}



document.write('<div id="temp_do" class="mainDiv" style="visibility: hidden; position: absolute"></div>')
function doprogram(programid,poststring,obj)
{
var show = document.getElementById("temp_do");
var fobj = eval("document.forms."+obj);
for (i=0; i<fobj.length; i++){
		//alert(fobj.elements[i].name);
		poststring = poststring + "&" + fobj.elements[i].name + "=" + fobj.elements[i].value;
}
var ajax = InitAjax();
 url=programid;
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded charset=utf-8");
//ajax.setRequestHeader("Content-Type","text/html; charset=big5");  

 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;

	} 

 } 
}

function openprogram_for2(programid,poststring,loginarea,runfunc)
{

 var show = document.getElementById(loginarea);
 var ajax = InitAjax();
 url=programid;
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;
		if (runfunc) {
			eval(runfunc);
		}	
	} 

 } 
}

function openprogram_for_detail(programid,poststring,loginarea,runfunc)
{
 var show = document.getElementById(loginarea);
 
      for (i=0; i<a_div_name.length; i++){
				if (("details_" + a_div_name[i]) == loginarea) {
					if (detail_swap_flag[i] == 0) {
						return;
					} else {
					}
				} else {
				}
		}

 var ajax = InitAjax();
 url=programid;
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;
		if (runfunc) {
		//	eval(runfunc);
		}	
	} 

 } 
}


function openprogram_for_detail2(programid,poststring,loginarea)
{
 var show = document.getElementById(loginarea);
 
      for (i=0; i<a_div_name.length; i++){
				if (("details_" + a_div_name[i]) == loginarea) {
					if (detail_swap_flag[i] == 0) {
						return;
					} else {
					}
				} else {
				}
		}

 var ajax = InitAjax();
 url=programid;
 ajax.open("POST", url, true); 
 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
 	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;
	} 

 } 
}


function login(url,programid,loginarea)
{
 var show = document.getElementById(loginarea);
 var ajax = InitAjax();
// url=loginform.php";
 poststring="code=" + programid;
  
 ajax.open("POST", url, true); 

 ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 ajax.send(poststring);
 ajax.onreadystatechange = function() { 
	if (ajax.readyState == 4 && ajax.status == 200) { 
  		show.innerHTML=ajax.responseText;
//		alert(show.innerHTML);
	 } 

 } 
}





// for sign up -----------------------------------------------------------
function check(dofunc,doform){
var signup_title = document.getElementById("signup_title");
var pa1 = document.signup_form.passwd.value;
var pa2 = document.signup_form.passwd2.value;
	if (pa1!=pa2) { 
    	signup_title.innerHTML = "Please Comfirm Your passwd again!!";
	} else{
		loadContentByXml(dofunc,"t=1",doform);
//		signup_form.submit();
	}
}





function thisMovie(movieName) {
    if (navigator.appName.indexOf("Microsoft") != -1) {
        return window[movieName]
    }
    else {
        return document[movieName]
    }
}
