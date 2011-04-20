//const variable
var DKEY_DISABLE        = 0;
var DKEY_ENABLE_V2      = 1 << 0;
var DKEY_ENABLE_V3      = 1 << 1;
var DKEY_ENABLE_BOTH    = DKEY_ENABLE_V2 | DKEY_ENABLE_V3;
var INST_COMP           = 4;
//

var DKEY_HAVE_UNKNOWN   = -1;
var DKEY_HAVE_NONE      = DKEY_DISABLE;
var DKEY_HAVE_V2        = DKEY_ENABLE_V2;
var DKEY_HAVE_V3        = DKEY_ENABLE_V3;
var DKEY_HAVE_BOTH      = DKEY_ENABLE_BOTH; 

/*count string len*/
function mbStringLength(s) 
{
    var totalLength = 0;
    var i;
    var charCode;
    for (i = 0; i < s.length; i++)
	{
          charCode = s.charCodeAt(i);
          if (charCode < 0x007f)
		  {
            totalLength = totalLength + 1;
          } 
		  else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) 
		  {
            totalLength += 2;
          } 
		  else if ((0x0800 <= charCode) && (charCode <= 0xffff)) 
		  {
            totalLength += 3;
          }
    }
    return totalLength;
}

//encode string into a available component of URI
//added by lwq 2008.4.11
 function svpn_encodeURIComponent(str)
 {
    var s = new Array('%00', '%01', '%02', '%03', '%04', '%05', '%06', '%07', '%08', '%09', '%0A', '%0B', '%0C', '%0D', '%0E', '%0F', '%10', '%11', '%12', '%13', '%14', '%15', '%16', '%17', '%18', '%19', '%1A', '%1B', '%1C', '%1D', '%1E', '%1F', '%20', '!', '%22', '%23', '%24', '%25', '%26', "'", '(', ')', '*', '%2B', '%2C', '-', '.', '%2F', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '%3A', '%3B', '%3C', '%3D', '%3E', '%3F', '%40', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '%5B', '%5C', '%5D', '%5E', '_', '%60', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '%7B', '%7C', '%7D', '~', '%7F');
    var r = new Array();
    var l = str.length;
    var i;
    var j = 0;
    var c;
    for (i = 0; i < l; i++) {
      c = str.charCodeAt(i);
      if (c < 128) {
        r[j++] = s[c];
      } else if (c < 2048) {
        r[j++] = "%" + ((c >> 6) + 192).toString(16).toUpperCase();
        r[j++] = "%" + ((c & 63) + 128).toString(16).toUpperCase();
      } else if (c < 65536) {
        r[j++] = "%" + ((c >> 12) + 224).toString(16).toUpperCase();
        r[j++] = "%" + (((c >> 6) & 63) + 128).toString(16).toUpperCase();
        r[j++] = "%" + ((c & 63) + 128).toString(16).toUpperCase();
      } else if (c < 2097152) {
        r[j++] = "%" + ((c >> 18) + 240).toString(16).toUpperCase();
        r[j++] = "%" + (((c >> 12) & 63) + 128).toString(16).toUpperCase();
        r[j++] = "%" + (((c >> 6) & 63) + 128).toString(16).toUpperCase();
        r[j++] = "%" + ((c & 63) + 128).toString(16).toUpperCase();
      }
    }
    return r.join("");
 }
  
function IsIE()
{
  	var nav=navigator.appVersion;
	if( nav.indexOf('MSIE') == -1 )
	{
		return false;;
	}
	return true;
}
/*about cookie */
function SetCookie(name, value)
{
	document.cookie = name + "=" + escape(value) + ";path=/";
}
function GetCookie(name) 
{
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var ret = "SinforDefaultValue";
	var i = 0;
	while (i < clen) 
	{
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg) 
		{
			var endstr = document.cookie.indexOf(";", j);
			if (endstr == -1) 
			{
				endstr = document.cookie.length;
			}
			ret = unescape(document.cookie.substring(j, endstr));
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
		{
			break;
		}
	}
	return ret;
}
//这里定义GetCookie2是为了避免在显示资源表格时，component.js未下载完导致脚本错误
function GetCookie2(name) 
{
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var ret = "SinforDefaultValue";
	var i = 0;
	while (i < clen) 
	{
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg) 
		{
			var endstr = document.cookie.indexOf(";", j);
			if (endstr == -1) 
			{
				endstr = document.cookie.length;
			}
			ret = unescape(document.cookie.substring(j, endstr));
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
		{
			break;
		}
	}
	return ret;
}

function DelCookie(name)
{
	var expireTime = new Date();
	expireTime.setTime(expireTime.getTime() - (365*3600000));
	var c = name + "=0" +  ";expires=" + expireTime.toGMTString() + ";path=/";
	document.cookie = c;
}

function IsVista()
{
	var agent = navigator.userAgent;
	if(agent.indexOf("Windows NT 6") != -1)
	{
		return true;
	}
	return false;
}

function Trim(str)
{
	return str.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

//ajax http request
if (typeof XMLHttpRequest == 'undefined') 
{
	XMLHttpRequest = function () 
	{
		var msxmls = ['MSXML3', 'MSXML2', 'Microsoft'];
		if(navigator.userAgent.indexOf("MSIE 5") >=0 )
		{
			msxmls = ['Microsoft','MSXML3', 'MSXML2' ]
		}
		for (var i=0; i < msxmls.length; i++) 
		{
			try 
			{
				return new ActiveXObject(msxmls[i]+'.XMLHTTP')

			} catch (e) 
			{}
		}
		return null;
	}
}

function CreateXMLHttpRequest() 
{
	try 
	{
		// Attempt to create it "the Mozilla way" 
		if (window.XMLHttpRequest) 
		{
			return new XMLHttpRequest();
		}
		// Guess not - now the IE way
		if (window.ActiveXObject) {
			return new ActiveiveXObject(getXMLPrefix() + ".XmlHttp");
		}
	}catch(ex){}
	return false;
}
function post_http(url,sbody,method)
{
	var xmlhttp  = null;
	try
	{
		xmlhttp = new XMLHttpRequest();
		if(xmlhttp == null)
		{
			return "";
		}
	}
	catch(e)
	{
		return "";
	}
	
	if((method != null)&&(method.toUpperCase()=="GET" || method.toUpperCase()=="POST")){
		method = method.toUpperCase();	
	}else{
		method = "GET";
	}
	
	xmlhttp.open(method, url , false);	// false=synch
	xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	try
	{
		if (sbody)
		{
			xmlhttp.send(sbody);
		}else{
			xmlhttp.send(null);
		}
	}catch(e){
		return "";
	}

	if((xmlhttp.readyState == 4) && (xmlhttp.status == 200))
	{
		return Trim(xmlhttp.responseText ? xmlhttp.responseText : "");
	}
	else
	{
		return "0";
	}
}

function post_http_async(url, sbody, func)
{
	var xmlhttp = null;
	try
	{
		xmlhttp = new XMLHttpRequest();
		if(xmlhttp == null)
		{
			return null;
		}
	}
	catch(e)
	{
		return null;
	}
	xmlhttp.open("GET", url , true);	// true=asynch
	xmlhttp.onreadystatechange = func;
	xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	try
	{
		if (sbody)
		{
			xmlhttp.send(sbody);
		}
		else
		{
			xmlhttp.send(null);
		}
	}
	catch (e)
	{
		//alert('Problem in sending information to server.');
		return null;
	}
	return xmlhttp;
}
//end ajax http request

function CreateObject(progid)
{
	var obj = null;
	try
	{
		obj = new ActiveXObject(progid);
	}
	catch(e)
	{
		obj = null;
	}
	return obj;
}

//make pc user is Relogin
function checkReLoginEx()
{
	var  cscmObjId = "CSClientManagerPrj.CSClientManager.1";
	var cscmObj = null;
	cscmObj = CreateObject(cscmObjId);
	if(cscmObj == null)
		return false;
		
	var isReLogin = cscmObj.reloginEx;
	if(isReLogin == 1)
	{
		window.location = "/com/warning.html";
		return true;
	}
	return false;	
}
function ajaxDealError(e)
{
	alert(e.description);
	return;
}
//add by hjk for ajax request 2009.5.11 -->
function createRequest()
{
	if(typeof XMLHttpRequest != "undefined" )
	{
		return new XMLHttpRequest();
	}	
	else if(window.ActiveXObject)
	{
		var aVersion =["MSXML2.XMLHttp.5.0","MSXML2.XMLHttp.4.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp","Microsoft.XMLHttp"];
		for(var i=0 ;i< aVersion.length;i++)
		{
			try
			{
				var oXmlHttp = new ActiveXObject(aVersion[i]);
				return oXmlHttp;
			}
			catch(e)
			{
				if(i >= aVersion.length)
				{
					ajaxDealError(e);
					return null;
				}
			}
		}
	}
}
function sendRequest(url,postcontent,requestmethod,asynch,callBackProc)
{
		g_AjaxResponseText = null;
		var oXmlHttp = createRequest();
		if(!oXmlHttp)
			return false;
		if(!url)
			return false;
		else
		{
			if(url.indexOf("?")!=-1)
				url += "&rnd=" + Math.random();
			else
				url += "?rnd=" + Math.random();
		}
		
		if(!postcontent)
			postcontent = null;
		
		if(!requestmethod)
			requestmethod = "GET";
		else
			requestmethod = requestmethod.toUpperCase();
			
		if((typeof asynch) == "undifined")
			asynch = true;
		
		if(!callBackProc)
			callBackProc = null;
		try
		{
			oXmlHttp.open(requestmethod,url,asynch);
			oXmlHttp.onreadystatechange = function()
			{
				if(oXmlHttp.readyState == 4 && oXmlHttp.status == 200)
				{
					try
					{
						if(oXmlHttp.responseText)
						{
							g_AjaxResponseText = oXmlHttp.responseText;
							g_AjaxResponseText = g_AjaxResponseText.replace(/(^\s*)|(\s*$)/g, "");
						}
						if(typeof(callBackProc) == "function")
						{
								callBackProc(true,g_AjaxResponseText);
						}
					}
					catch(e)
					{
						ajaxDealError(e)
					}
				}
			}
			if(postcontent && requestmethod == "POST")
			{
				oXmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				oXmlHttp.send(postcontent);
			}else{
				oXmlHttp.send(null);
			}
		}
		catch(ex)
		{
			ajaxDealError(ex)
			return false;
		}
		if(asynch == false){
			if(g_AjaxResponseText != null)
			{
				return g_AjaxResponseText;
			}
			else if(oXmlHttp.readyState == 4 && oXmlHttp.status == 200)
			{
				return oXmlHttp.responseText;
			}
		}else{
			return true;
		}
}
//.for keep 24 chinese chars
function getStrLen(str)
{
	var tmpstr=str.replace(/[^\x00-\xff]/g,"cn");
	return tmpstr.length;
}
//end add by hjk <--

