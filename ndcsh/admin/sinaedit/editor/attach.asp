<%@LANGUAGE="VBSCRIPT" CODEPAGE="936"%>
<%option explicit%>
<!--#include file="../../../../Conn.asp"-->
<!--#include file="../../../../HB_Cls/hnhuibo.commonCls.asp"-->
<!--#include file="../../../../Plus/Session.asp"-->
<%
'****************************************************
' Software name:株洲教育信息网
' Email: 86317079@qq.com . QQ:86317079
' Web: http://www.zzedu.gov.cn
' Copyright (C) Hnhuibo Network All Rights Reserved.
'****************************************************Response.Buffer = True 
Response.Expires = -1
Response.ExpiresAbsolute = Now() - 1 
Response.Expires = 0 
Response.CacheControl = "no-cache" 
Dim KSCls
Set KSCls = New InsertAttach
KSCls.Kesion()
Set KSCls = Nothing

Class InsertAttach
        Private KS
		Private AdminDir
		Private ChannelID
		Private FromUrl
        Private CurrPath,InstallDir
		Private Sub Class_Initialize()
		  Set KS=New PublicCls
		End Sub
        Private Sub Class_Terminate()
		 Set KS=Nothing
		End Sub
        Public Sub Kesion()
			FromUrl=KS.ChkClng(KS.S("FromUrl"))
	       IF FromUrl=1 Then  '后台调用，检查是否登录
				Dim KSLoginCls
				Set KSLoginCls = New LoginCheckCls1
				KSLoginCls.Run()
				Set KSLoginCls= Nothing
			End IF

			ChannelID = KS.S("ChannelID")
			CurrPath=KS.GetUpFilesDir()
			AdminDir=KS.GetDomain & KS.Setting(89)
			%>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>添加附件</title>
		<style type="text/css">
			body, td, span, div, input, select {
				font-size: 12px;
				font-family: "宋体", "Courier New", Courier, monospace;
				margin: 0px;
			}
			.input {
				border: 1px solid #7E9DB9;
				width: 250px;
				margin-left: 4px;
				height: 18px;
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
		function resteText() {
			var editor = window.dialogArguments.EDiaryEditor;
			var frameWindow = editor.iframe.contentWindow;
			var selection = frameWindow.document.selection; 
			if (selection != null) {
				rng = selection.createRange();
			}
			$("linktext").value = rng.text ? rng.text : "";
		}
		function loadFileName(){
			var start=$("linkpath").value.lastIndexOf("\\")+1;
			var end=$("linkpath").value.length;
			$("linktext").value=$("linkpath").value.substr(start,end);
		}
		window.onload = init;
		function init() {
			adjustDialog();
			resteText();
			$("linkpath").select();
		}
		function LoadAttach (path) {
			var editor = window.dialogArguments.EDiaryEditor;
			var oRTE = editor.iframe.contentWindow;
			var imgpath=location.href.substr(0,location.href.lastIndexOf("\/")).replace("\/editor","\/images\/common.gif");
			var html = "<img src='"+imgpath+"' align='absmiddle'><a href='" + path + "' target='_blank'>" + $("linktext").value + "</a>";
			if(window.isIE) {
				try{
					oRTE.focus();
					var oRng = oRTE.document.selection.createRange();
					oRng.pasteHTML(html);
					oRng.collapse(false);
					oRng.select();
				}catch(e){}
			}
			else {
				editor.runCMD('insertHTML', html);
			}
			window.close();
		}
		function chk_link(){
			if($("linkpath").value==""){
				alert("请选择要上传的附件!");
				return false;
			}
			if($("linktext").value==""){
				alert("请选择输入附件名称!");
				$("linktext").focus();
				return false;
			}
			$("form1").submit();
			$('divProcessing').style.display='';
		}
		document.onkeydown = function (el) {
			var event = window.event || el;
			if(event.keyCode == 13) {
			    return chk_link();
			}  
		}
		//-->
		</script>
	</head>
	<body>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" id="tabDialogSize">
    <tr>
      <td height="24" bgcolor="#DDE7EE" style="padding-left: 10px;">添加附件<font color="#666666">(允许上传rar、zip、doc、xls格式的附件)</font></td>
    </tr>
    <tr>
      <td align="center"><table border="0" cellpadding="0" cellspacing="0" width="100%">
      <form name="form1" id="form1" method="post" action="upload.asp?action=upload&uploadtype=attach" enctype="multipart/form-data" target="myiframe">
        <tr>
          <td width="70" height="28" align="right" valign="bottom">上传附件:</td>
          <td align="left" valign="bottom"><input type="file" class="input" name="file1" id="linkpath" onChange="loadFileName()"></td>
        </tr>
        <tr>
          <td height="28" align="right" valign="bottom">附件名称:</td>
          <td align="left" valign="bottom"><input type="text" class="input" id="linktext"></td>
        </tr>
      </form>  
      </table></td>
    </tr>
    
    <tr>
      <td height="40" align="center" style="padding-bottom: 10px;"><a href="#" onClick="chk_link()"><img border="0" src="../images/dilog_bt_ok.gif" alt="确定" style="margin-right: 10px;"/></a><a href="#" onClick="window.close();"><img border="0" src="../images/dilog_bt_cancel.gif" alt="取消"/></a></td>
    </tr><tr><td bgcolor="#DDE7EE" height="5"></td>
    </tr>
	</table>
<div id=divProcessing style="width:200px;height:30px;position:absolute;left:85px;top:75px;display:none">
<table border="0" cellpadding="0" cellspacing="1" bgcolor="#333333" width="100%" height="100%">
  <tr>
    <td bgcolor="#3A6EA5" align="center"><font color=#FFFFFF>附件上传中,请等待...</font></td>
  </tr>
</table>
</div>
<iframe src="upload.asp" name="myiframe" id="myiframe" style="display:none"></iframe>
</body>
</html>
<%
  End Sub
End Class
%> 
