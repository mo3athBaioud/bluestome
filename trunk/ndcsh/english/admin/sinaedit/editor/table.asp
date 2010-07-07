<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>插入表格</title>
		<style type="text/css">
			body, td, span, div, input, select {
				font-size: 12px;
				font-family: "宋体", "Courier New", Courier, monospace;
				margin: 0px;
			}
			.input {
				border: 1px solid #7E9DB9;
				width: 38px;
				margin-left: 4px;
				height: 18px;
			}
			.select {
				height: 18px;
				border: 1px solid #7E9DB9;
				margin-left: 4px;
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
		function init() {
			adjustDialog();
			$("tablerow").select();
		}
		function chk_table () {
			
			if($("tablerow").value > 50) {
				alert("为保证页面效果，您可以插入的最大表格为50行X20列！");
				$("tablerow").select();
				return;
			}
			if($("tablecol").value > 50) {
				alert("为保证页面效果，您可以插入的最大表格为50行X20列！");
				$("tablecol").select();
				return;
			}
			var widthType = $("tabletype").value == "pixels" ? "" : "%";
			var html = '<table border="' + $("tableborder").value + '" cellpadding="' + $("tablepadding").value + '" ';
			html += 'cellspacing="' + $("tablespacing").value + '" style=width:"' + $("tablewidth").value + widthType + '">\n';
			for (var rows = 0; rows < $("tablerow").value; rows ++) {
				html += "<tr>\n";
				for (var cols = 0; cols < $("tablecol").value; cols ++) {
					html += "<td>&nbsp;</td>\n";
				}
				html+= "</tr>\n";
			}
			html += "</table>\n";
			
			var editor = window.dialogArguments.EDiaryEditor;
			var oRTE = editor.iframe.contentWindow;
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
		//-->
		</script>
	</head>
	<body>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" id="tabDialogSize">
    <tr>
      <td height="24" bgcolor="#DDE7EE" style="padding-left: 10px;">插入表格</td>
    </tr>
    <tr>
      <td align="center"><table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="70" height="28" align="right" valign="bottom">行数:</td>
          <td width="120" valign="bottom"><input class="input" id="tablerow" value="2"></td>
          <td width="80" align="right" valign="bottom">单元格边距:</td>
          <td width="100" valign="bottom"><input class="input" id="tablepadding" value="1">
          像素</td>
        </tr>
        <tr>
          <td height="28" align="right" valign="bottom">列数:</td>
          <td valign="bottom"><input class="input" id="tablecol" value="2"></td>
          <td align="right" valign="bottom">单元格间距:</td>
          <td valign="bottom"><input class="input" id="tablespacing" value="1">
          像素</td>
        </tr>
        <tr>
          <td height="28" align="right" valign="bottom">表格宽度:</td>
          <td valign="bottom"><input class="input" id="tablewidth" value="200">
          <select class="select" id="tabletype">
          <option selected value="pixels">像素</option>
          <option>百分比</option>
          </select></td>
          <td align="right" valign="bottom">边框粗细:</td>
          <td valign="bottom"><input class="input" id="tableborder" value="1">
          像素</td>
        </tr>
      </table></td>
    </tr>
    
    <tr>
      <td height="40" align="center" style="padding-bottom: 10px;"><a href="#" onclick="chk_table()"><img border="0" src="../images/dilog_bt_ok.gif" alt="确定" style="margin-right: 10px;"/></a><a href="#" onclick="window.close();"><img border="0" src="../images/dilog_bt_cancel.gif" alt="取消"/></a></td>
    </tr><tr><td bgcolor="#DDE7EE" height="5"></td>
    </tr>
	</table>
	</body>
</html>