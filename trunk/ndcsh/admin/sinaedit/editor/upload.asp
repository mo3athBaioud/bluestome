<%@LANGUAGE="VBSCRIPT" CODEPAGE="936"%>
<%
on error resume next
Server.ScriptTimeOut=5000
%>
<!--#include file="UpLoadClass.asp"-->
<!--#include file="../../../../Conn.asp"-->
<!--#include file="../../../../HB_Cls/hnhuibo.commonCls.asp"-->
<!--#include file="../../../../Plus/Session.asp"-->
<%
if Request.QueryString("action")="upload" then
dim MyRequest,lngUpSize,SavePath
SavePath="/uploadfiles/"&year(now)&"-"&month(now())&"/" '设置上传目录（可以是相对路径）
SavePath=replace(SavePath,"\","/")
Set MyRequest=new UpLoadClass
MyRequest.SavePath=SavePath

'设置允许上传的文件类型
UploadType=trim(Request.QueryString("uploadtype"))
if UploadType="img" then
	MyRequest.FileType="jpg/gif/bmp/png"
elseif UploadType="flash" then
    MyRequest.fileType="swf"
elseif UploadType="attach" then
	MyRequest.FileType="rar/zip/doc/xls"
end if

'判断上传目录是否存在，不存在则自动创建
FolderPath=server.MapPath(SavePath)
Set FSO=Server.CreateObject("Scripting.FileSystemObject")
if FSO.FolderExists(FolderPath)=false then
FSO.CreateFolder(FolderPath)
end if
Set FSO=nothing

'取得当前文件所在目录
FileName=Right(Request.Servervariables("Script_Name"),len(Request.Servervariables("Script_Name"))-InstrRev(Request.Servervariables("Script_Name"),"/"))
FileFolder=replace(Request.Servervariables("Script_Name"),FileName,"")

'获取文件地址的根绝对路径
if left(SavePath,1)="/" then
uploadPath=SavePath
else
uploadPath=FileFolder&SavePath
end if

lngUpSize = MyRequest.Open()
  select case MyRequest.error
         case 0
			 if UploadType="img" then
				response.Write("<script>window.parent.LoadIMG('"&uploadPath&trim(MyRequest.form("file1"))&"');</script>")
			 elseif UploadType="flash" then
				response.Write("<script>window.parent.LoadFlash('"&uploadPath&trim(MyRequest.form("file1"))&"','"&trim(MyRequest.form("flashwidth"))&"','"&trim(MyRequest.form("flashheight"))&"');</script>")
			 elseif UploadType="attach" then
				response.Write("<script>window.parent.LoadAttach('"&uploadPath&trim(MyRequest.form("file1"))&"');</script>")
			 end if
         case 1
		 response.Write("<script>alert('文件过大！');window.parent.$('divProcessing').style.display='none';history.back();</script>")
		 case 2
		 response.Write("<script>alert('不允许上传该类型的文件！');window.parent.$('divProcessing').style.display='none';history.back();</script>")
         case 3
		 response.Write("<script>alert('不允许上传该类型的文件！');window.parent.$('divProcessing').style.display='none';history.back();</script>")
		 case else
		 response.Write("<script>alert('文件上传出错！');window.parent.$('divProcessing').style.display='none';history.back();</script>")
  end select
end if

%>