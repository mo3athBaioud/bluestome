<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>插入图片</title>
		<style type="text/css">
			body, td, span, div, input {
				font-size: 12px;
				font-family: "宋体", "Courier New", Courier, monospace;
				margin: 0px;
			}
		</style>
		<script language="JavaScript">
		<!--
		window.isIE = (navigator.appName == "Microsoft Internet Explorer");
		if(window.isIE) {
			if(navigator.userAgent.indexOf("Opera")>-1) window.isIE = null;
		}
		else {
			if(navigator.userAgent.indexOf("Gecko")==-1) window.isIE = null;
		}
		function $(sID) {
			return document.getElementById(sID);
		}
		function adjustDialog(){
			var w = $("tabDialogSize").offsetWidth + 6;
			var h = $("tabDialogSize").offsetHeight + 25;
			window.dialogLeft = (screen.availWidth - w) / 2;
			window.dialogTop = (screen.availHeight - h) / 2;
		}
		window.onload = init;
		function init () {
			adjustDialog();
		}
		function LoadIMG(imgpath){
		    var editor = window.dialogArguments.EDiaryEditor;
		    var oRTE = editor.iframe.contentWindow;
		    var html = "<img src='" + imgpath + "'>";
			if(window.isIE) {
				try{
					oRTE.focus();
					var oRng = oRTE.document.selection.createRange();
					oRng.pasteHTML(html);
					oRng.collapse(false);
					oRng.select();
				}
				catch(e){}
			}
			else {
				editor.runCMD('insertHTML', html);
			}
			window.close();
		}
		function chk_imgpath () {
		  if($('radio1').checked==true){
			if($("imgpath").value == "http://" || $("imgpath").value == "") {
				window.close();
				return;
			}
			LoadIMG($("imgpath").value);
		  }else{
		    if($("file1").value == "") {
			   window.close();
			   return;
		    }
		    $('form1').submit();
			$('divProcessing').style.display='';
		  }
		}
		document.onkeydown = function (el) {
			var event = window.event || el;
			if(event.keyCode == 13) {
			    return chk_imgpath();
			}  
		}
		//-->
		</script>
	</head>
	<body>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" id="tabDialogSize">
    <form name="form1" id="form1" method="post" action="upload.asp?action=upload&uploadtype=img" enctype="multipart/form-data" target="myiframe">
    <tr>
      <td height="24" bgcolor="#DDE7EE" style="padding-left: 10px;">插入图片</td>
    </tr>
    <tr>
      <td height="50"><input type="radio" name="picurl" id="radio1" hidefocus="true" onClick="if(this.checked==true){$('imgpath').disabled='';$('file1').disabled='disabled';}">&nbsp;图片地址:
      <input type="text" value="http://" style="border: 1px solid #7E9DB9; width: 235px;" id="imgpath" name="imgpath" disabled="disabled"></td>
    </tr>
    <tr height="30"> 
      <td align="left" id="upid" width="400"><input type="radio" id="radio2" name="picurl" hidefocus="true" onClick="if(this.checked==true){$('imgpath').disabled='disabled';$('file1').disabled='';}" checked>&nbsp;上传图片: 
        <input type="file" name="file1" id="file1" style="width:235px; border:#7E9DB9 1px solid">
      </td>
    </tr>
    <tr>
      <td height="40" align="center" style="padding-bottom: 10px;"><a href="#" id="mysubmit" onClick="return chk_imgpath()"><img border="0" src="../images/dilog_bt_ok.gif" alt="确定" style="margin-right: 10px;"/></a><a href="#" onClick="window.close();"><img border="0" src="../images/dilog_bt_cancel.gif" alt="取消"/></a></td>
    </tr><tr><td bgcolor="#DDE7EE" height="5"></td>
    </tr></form>
	</table>
<div id=divProcessing style="width:200px;height:30px;position:absolute;left:85px;top:75px;display:none">
<table border="0" cellpadding="0" cellspacing="1" bgcolor="#333333" width="100%" height="100%">
  <tr>
    <td bgcolor="#3A6EA5" align="center"><font color=#FFFFFF>图片上传中,请等待...</font></td>
  </tr>
</table>
</div>
<iframe src="upload.asp" name="myiframe" id="myiframe" style="display:none"></iframe>
	</body>
</html>