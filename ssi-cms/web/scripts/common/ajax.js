/**
 * 针对ajax提供的脚本，包含主要的将form表单提交转换为ajax提交的类。
 * 一般来说，使用该脚本的同时须应用同目录下的form.js
 * @author tiannet(曾次清)
 *
 */

//////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 针对表单或request使用ajax提交处理。如果要使用这个伪类，则必须引入同目录下的form.js
 *
 */

var AjaxSubmit = function() {
	this.form = null;
	this.method = "GET";
	this.action = null;
	this.addParameters = new Array();//添加的参数
	this.completeHandler = simpleHandlerForResponseXml;/** 服务端成功响应的处理对象（函数） */
	/** 设置表单对象 */
	this.setForm = function(form) {
		this.form = $(form);
		if( !this.form || this.form.tagName != "FORM" ) {
			alert("你通过setForm设定的" + form + "并不是一个表单对象！");
			return;
		}
		if( this.form.method != "" && typeof(this.form.method) == "string" ) {
			this.method = this.form.method;
		}
		if( this.form.action != "" && typeof(this.form.action) == "string") {
			this.action = this.form.action;
		}
	}//end setForm
	/**
	 * 添加一个传递的参数
	 * @param name 参数名
	 * @param value 参数值
	 */
	this.putParameter = function(name, value) {
		this.addParameters.push( name + "=" + parseValueForAjax(value) );
	}
	/**
	 * 
	 *
	 */
	this.setCompleteHandler = function(completeHandler) {
		this.completeHandler = completeHandler;
	}
	/**
	 *得到所有的参数对，包括form表单和设置的参数（如果它们都存在的话）。
	 */
	this.getAllParameters = function() {
		//如果没有表单，直接返回设置的参数对。
		if( this.form == null ) {
			return this.addParameters;
		}
		var a = getFormFieldNameValuePairs( this.form );
		for( var i = 0;i < this.addParameters.length;i ++ ) {
			a.push( this.addParameters[i] );
		}
		return a;
	}
	this.setMethod = function(method) {
		this.method = method;
	}
	this.setAction = function(action) {
		this.action = action;
	}
	/**
	 * 提交处理
	 */
	this.submit = function() {
		if( this.action == null || typeof(this.action) != "string" ) {
			alert("使用AjaxSubmit时未指定提交地址！");
			return false;
		}
		if( this.completeHandler == null ) {
			alert("使用AjaxSubmit时未指定服务器返回信息处理函数（请调用setCompleteHandler函数）！");
			return false;
		}
		//没有表单的提交意味着通过地址请求，此时method必须为POST
		if( this.form == null ) {
			this.method = "POST";
		}
		//alert(this.action);
		var req = newXMLHttpRequest();
		req.onreadystatechange = getReadyStateResponse(req, this.completeHandler);
		
		req.open(this.method, this.action, true);
		//req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//这一句解决传中文的问题
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		//req.setRequestHeader("Accept-language", "zh-cn");
		req.send( this.getAllParameters().join("&") );
	}


}//end AjaxSubmit


//////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////// 处理简单的xml（使用ajax提交返回的xml） ///////////////////////////////////////////////////

/**
 * 针对ajax提交表单返回xml的处理。
 * 如果操作成功并且设置了成功跳转的地址，则将使用window.location跳转到成功地址。
 * 如果操作失败，则弹出错误信息，特别的，如果指定了出错的表单域，则光标聚集到该表单域。
 * 如果设置了提示信息，则会弹出提示信息。
 *
 */
function simpleHandlerForResponseXml(req) {
	var xml = req.responseXml;
	var objResponse = new SimpleXml(xml);
	//alert(objResponse.success);

	//提示信息，包括成功或错误的提示
	var messages = "";
	for(var i = 0;i < objResponse.messageMsgs.length;i ++) {
		//alert(objResponse.messages[i]);
		messages += "(" + (i+1) + ")." + objResponse.messageMsgs[i] + "\n";
	}

	if( objResponse.success ) {
		if( messages != "" ) {
			alert("[成功提示]\n" + messages);
		}
		//操作成功
		if( objResponse.successUrl ) {
			window.location = objResponse.successUrl;
		}
		return;
	}
	
	//操作失败的处理
	if( messages != "" ) {
		alert("[错误提示]\n" + messages);
	} else {
		alert("发生意外错误！");
	}
	//聚焦
	for(var i = 0;i < objResponse.messageNames.length;i ++) {
		if( objResponse.messageNames[i] != "" && $(objResponse.messageNames[i]) ) {
			try { 
				$(objResponse.messageNames[i]).focus();
				break;
			}
			catch (e){}
		}
	}
	if( objResponse.failUrl ) {
		window.location = objResponse.failUrl;
	}
	return;
}

//alert("OK1");

//完整的简单xml格式例子如下：
/*
<?xml version=\"1.0\" encoding=\"utf-8\"?>
<response>
	<success>true</success>
	<success-url>/db.do?action=list</success-url>
	<fail-url>/error.jsp</fail-url>
	<messages>
		<message name=\"userId\">添加用户失败</message>
		<message name=\"userName\">请选择别的用户名</message>
	</messages>
</response>*/
/**
 * 简单的xml分析，分解成JavaScript对象。
 * @param xml xml对象
 */
var SimpleXml = function(xml) {
	if( !xml ) {
		alert("使用SimpleXml请传递xml对象");
	}
	this.success = false;//操作是否成功
	this.messageNames = new Array();//提示信息名称集，它与提示信息值一一对应。该对象通常指表单域。如果提示信息名称不存在则统一置为空。
	this.messageMsgs = new Array();//提示信息值集，它与提示信息名称一一对应
	//this.errors = new Array();//错误信息集
	this.successUrl = null;//操作成功跳转地址
	this.failUrl = null;//操作失败跳转地址
	//获取操作是否成功
	var nodeSuccess = xml.selectSingleNode("//response/success");
	if( nodeSuccess && nodeSuccess.text == "true" ) {
		this.success = true;
	}
	//alert(this.success);
	//获取操作成功的链接地址
	var nodesuccessUrl = xml.selectSingleNode("//response/success-url");
	if( nodesuccessUrl ) {
		this.successUrl = nodesuccessUrl.text;
	}
	//获取操作失败的链接地址
	var nodefailUrl = xml.selectSingleNode("//response/fail-url");
	if( nodefailUrl ) {
		this.failUrl = nodefailUrl.text;
	}
	//获取所有提示
	var msgNodes = xml.selectNodes("//response/messages/message");
	for( var i = 0; msgNodes && i < msgNodes.length;i ++ ) {
		this.messageMsgs.push( msgNodes[i].text );
		if( !msgNodes[i].attributes.getNamedItem("name") ) {
			this.messageNames.push("");
		} else {
			this.messageNames.push(msgNodes[i].attributes.getNamedItem("name").text);
		}
	}
	//获取所有错误信息
	//var errorNodes = xml.selectNodes("//response/errors/error");
	//for( var i = 0; errorNodes && i < errorNodes.length;i ++ ) {
	//	this.errors.push( errorNodes[i].text );
	//}
}

//////////////////////////////////////////////////////////////////////////////////////////////////


/*
 * Returns an new XMLHttpRequest object, or false if the browser
 * doesn't support it
 */
function newXMLHttpRequest() {

  var xmlreq = false;

  // Create XMLHttpRequest object in non-Microsoft browsers
  if (window.XMLHttpRequest) {
    xmlreq = new XMLHttpRequest();

  } else if (window.ActiveXObject) {

    try {
      // Try to create XMLHttpRequest in later versions
      // of Internet Explorer

      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
      
    } catch (e1) {

      // Failed to create required ActiveXObject
      
      try {
        // Try version supported by older versions
        // of Internet Explorer
      
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

      } catch (e2) {

        // Unable to create an XMLHttpRequest by any means
        xmlreq = false;
      }
    }
  }

	return xmlreq;
}//end function newXMLHttpRequest

   /*
	* Returns a function that waits for the specified XMLHttpRequest
	* to complete, then passes  response to the given handler function.
	* req - The XMLHttpRequest whose state is changing
	* responseHandler - Function to pass the response to
	*/
 function getReadyStateResponse(req, responseHandler) {

   // Return an anonymous function that listens to the XMLHttpRequest instance
   return function () {

     // If the request's status is "complete"
     if (req.readyState == 4) {
       
       // Check that we received a successful response from the server
       if (req.status == 200) {

         // Pass the XML payload of the response to the handler function.
         responseHandler(req);

       } else {
         // An HTTP problem has occurred
         alert("服务端处理出现一点问题，请稍后再试！" + "HTTP 错误 "+req.status+": "+req.statusText);
       }
     }
   }
 }//end function getReadyStateXmlHandler(req, responseXmlHandler) 