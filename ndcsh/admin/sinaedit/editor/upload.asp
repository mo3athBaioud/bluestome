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
SavePath="/uploadfiles/"&year(now)&"-"&month(now())&"/" '�����ϴ�Ŀ¼�����������·����
SavePath=replace(SavePath,"\","/")
Set MyRequest=new UpLoadClass
MyRequest.SavePath=SavePath

'���������ϴ����ļ�����
UploadType=trim(Request.QueryString("uploadtype"))
if UploadType="img" then
	MyRequest.FileType="jpg/gif/bmp/png"
elseif UploadType="flash" then
    MyRequest.fileType="swf"
elseif UploadType="attach" then
	MyRequest.FileType="rar/zip/doc/xls"
end if

'�ж��ϴ�Ŀ¼�Ƿ���ڣ����������Զ�����
FolderPath=server.MapPath(SavePath)
Set FSO=Server.CreateObject("Scripting.FileSystemObject")
if FSO.FolderExists(FolderPath)=false then
FSO.CreateFolder(FolderPath)
end if
Set FSO=nothing

'ȡ�õ�ǰ�ļ�����Ŀ¼
FileName=Right(Request.Servervariables("Script_Name"),len(Request.Servervariables("Script_Name"))-InstrRev(Request.Servervariables("Script_Name"),"/"))
FileFolder=replace(Request.Servervariables("Script_Name"),FileName,"")

'��ȡ�ļ���ַ�ĸ�����·��
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
		 response.Write("<script>alert('�ļ�����');window.parent.$('divProcessing').style.display='none';history.back();</script>")
		 case 2
		 response.Write("<script>alert('�������ϴ������͵��ļ���');window.parent.$('divProcessing').style.display='none';history.back();</script>")
         case 3
		 response.Write("<script>alert('�������ϴ������͵��ļ���');window.parent.$('divProcessing').style.display='none';history.back();</script>")
		 case else
		 response.Write("<script>alert('�ļ��ϴ�������');window.parent.$('divProcessing').style.display='none';history.back();</script>")
  end select
end if

%>