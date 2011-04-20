/////////////////////////////////////////
// GOBAL var
/////////////////////////////////////////
var E_OK         = 1;                 //OK
var E_FAIL       = E_OK << 1;         //Fail
var E_UNKNOWN    = E_FAIL << 1;       //Unknown
var E_PENDING    = E_UNKNOWN << 1;    //Running

var E_NO_RIGHT   = E_PENDING << 1;    //No permiss
var E_REPAIR     = E_NO_RIGHT << 1;   //Repaire
var E_INSTALL    = E_REPAIR << 1;     //Install
var E_UNINSTALL  = E_INSTALL << 1;    //UnInstall
var E_UPDATE     = E_UNINSTALL << 1;  //UPdate
var E_RESTART_IE = E_UPDATE << 1;     //Restart IEcon
var E_RELOGIN    = E_RESTART_IE << 1; //

//
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
//
var ESA_NEVER_INSTALL       = 0;
var ESA_DEST_DVERSION_OLD   = 1;
var ESA_DEST_DVERSION_EQUAL = 2;
var ESA_DEST_DVERSION_NEW   = 3;
//repair epnd.dl

var ESA_SUCCESS	            = 0x0000;
var ESA_ERR_FILE_NOT_FOUND  = 0x0001;
var ESA_ERR_COPY_FILES      = 0x0002;
var ESA_ERR_REG             = 0x0003;
var ESA_ERR_REG_CSP         = 0x0004;
var ESA_ERR_UNREG           = 0x0005;
var ESA_ERR_DEL_FILES       = 0x0006;
var ESA_ERR_DEL_DIR         = 0x0007;
var ESA_ERR_EXCTOR_FILES    = 0x0008;
var ESA_ERR_HOST_MEMORY     = 0x0009;
var ESA_ERR_REG_CARD_REG    = 0x000A;
var ESA_SUCCESS_REBOOT      = 0x1000;


//DLL索引
 
var ID_CSCM      = 0;
var ID_EPND      = ID_CSCM + 1;
var ID_IEHELPER  = ID_EPND + 1;
var ID_CLIENTNSP = ID_IEHELPER + 1;
var ID_PROXYIE   = ID_CLIENTNSP + 1;
var ID_SSOCLIENT = ID_PROXYIE + 1;
var ID_COMPINSP  = ID_SSOCLIENT + 1;
var ID_HARDID    = ID_COMPINSP + 1;
var ID_SDDN      = ID_HARDID + 1;
var ID_UCP		 = ID_SDDN + 1; 
var ID_SUPERSERVICE = ID_UCP + 1;
var ID_SUPEREXE = ID_SUPERSERVICE + 1;
 
 
var ID_NDDKEYV3 =  ID_SUPEREXE + 1;
var ID_NDDKEYV2 = ID_NDDKEYV3  + 1;
 
var ID_SINFORUI =  ID_NDDKEYV2 +1;
var ID_DLL_MAX  = ID_SINFORUI + 1;
//DLL index

var ID_NAME     = 0;
var ID_VER      = ID_NAME + 1;
var ID_FILE     = ID_VER + 1;
var ID_PROGID   = ID_FILE + 1;
var ID_CODEBASE = ID_PROGID + 1;



var G_DLLS = new Array(ID_DLL_MAX+1);
G_DLLS[ID_CSCM]      = [" 客户端管理控件 ", "4,2,6,1", "CSClientManagerPrj.dll", "CSClientManagerPrj.CSClientManager.1", "/com/CSClientManagerPrj.CAB"];
G_DLLS[ID_EPND]      = [" DKEY检查控件 ",   "4,2,1,1",  "epnd.dll", "Epnd.Epassnd.1", "/com/epnd.CAB"];
G_DLLS[ID_CLIENTNSP] = [" 域名解析服务 ",   "4,2,6,1",  "ClientNSPPrj.dll", "ClientNSPPrj.ClientNSP.1", "/com/ClientNSPPrj.CAB"];
G_DLLS[ID_IEHELPER]  = [" 浏览器辅助控件 ", "4,2,5,1",  "SinforBHO.dll", "SinforBHO.SinforHelper.1", "/com/SinforBHO.CAB"];
G_DLLS[ID_PROXYIE]   = [" ProxyIE控件 ",    "4,2,5,2", "ProxyIE.dll", "ProxyIE.CSProxy.1", "/com/ProxyIE.CAB"];
G_DLLS[ID_SSOCLIENT] = [" 单点登录控件 ",   "4,2,6,1",  "SSOClientPrj.dll", "SSOClientPrj.Web2Client.1", "/com/SSOClientPrj.CAB"];
G_DLLS[ID_HARDID]    = [" 硬件特征码控件 ", "4,2,1,1", "Hardid.dll","Hardid.GetID.1", "/com/Hardid.CAB"];
G_DLLS[ID_SDDN]      = [" SSL专线 ",  "4,2,1,1"," "," ", "/com/SVPNMonitorInstaller.exe"];
G_DLLS[ID_UCP]  = ["UDPProxyClient",  "1.4.2.3"," "," ", "/com/UCPInstaller.exe"];
G_DLLS[ID_SUPERSERVICE]  = ["SuperService",   "4,2,6,1"," "," ", "/com/SuperServiceInstaller.exe"];
G_DLLS[ID_SUPEREXE] = ["SuperExe",   "4,2,6,1"," "," ", "/com/SuperExeInstaller.exe"];
 
G_DLLS[ID_NDDKEYV3]  = ["Nddkey_v3","1,0,0,1","HTHMAC.dll","HTHMAC.HTHMACCtrl.1","/com/nd_dkey_v3.CAB"]; 
G_DLLS[ID_NDDKEYV2]  = ["Nddkey_v2","1,1,7,1105","ft_nd_sc.dll","FT_ND_SC.ePsM8SC.1","/com/nd_dkey_v2.cab"];
 
G_DLLS[ID_SINFORUI] = ["SinforUI", "4,2,5,1", " ", " ", "/com/SinforUIInstaller.exe"];

var g_ProxyieObj      = null;
var g_NspObj          = null;
var g_SsoObj          = null;
var g_CscmObj         = null;
var g_IEHelperObj     = null;
var g_DKeyObj         = null;
var g_EPassObj        = null;
var g_BhoObj		  = null;
var g_SsoBhoObj		  = null;
var g_IsBhoUpdate	  = false;
var g_IsSsoBhoUpdate	  = false;
var g_RestartIE       = false;
var g_HardidNeedRestart	=false;
var g_NeedComponent   = false;
var g_RestartDisabled = false; 
var g_NoPrompt        = false;
var g_IptunRequireVer = "4,2,5,2";
var g_VnicRequireVer = "1,0,1,0";
var g_FirstTimeVisit = true;
var g_bNeedIstallCSCM = false;
var g_Nddkeyv2Obj = null;
var g_Nddkeyv3Obj = null;

var g_IpSupportState = "0";
var g_AppSupportState = "0";
var g_enableSSLTray = 0;
var g_canUseIpService = false;
var g_defaultRcId = 0;

//panwc added for auto relogin -->
var g_enableAutoRelogin = 0;
//panwc added end <--
var g_defaultParserMd5 = "bbc4a68787165fa5d0156b21a57c8c5c";
//

//hjm add for IE8 support
var g_bInstallIEHook = false;
//end by hjm

/* linyan add for WebCaahe at 2009.4.23 */
function EnableWebCache(mode,count,url)
{
	if (g_ProxyieObj == null)
	{
		g_ProxyieObj = CreateObject(G_DLLS[ID_PROXYIE][ID_PROGID]);
	}
	if(g_ProxyieObj != null)
	{
		g_ProxyieObj.enableWebCache();
		g_ProxyieObj.setWebCacheFilt(mode, url);
		return true;
	}
	return false;
}
/* end linyan add */

/* lpt add for WebCache */
function DisableWebCache()
{
	if (g_ProxyieObj == null)
	{
		g_ProxyieObj = CreateObject(G_DLLS[ID_PROXYIE][ID_PROGID]);
	}
	if(g_ProxyieObj != null)
	{
		g_ProxyieObj.disableWebCache();
		return true;
	}
	return false;
}
/* end lpt add */

function DisableRestart()
{
	g_RestartDisabled = true;
}

function EnableRestart()
{
	g_RestartDisabled = false;
}

function IsNeedRestart()
{
	return g_RestartIE;
}

function SetNeedRestart(isNeedRestart)
{
	g_RestartIE = isNeedRestart;
}


function IsNeedComponent()
{
	return g_NeedComponent;
}

function SetNeedComponent()
{
	g_NeedComponent = true;
}

function IsBrowseNewProcess(cscmObj)
{
	try 
	{
		var result = cscmObj.RegValueOf("HKEY_CURRENT_USER", "Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\BrowseNewProcess", "BrowseNewProcess");
		result = result.toLowerCase();
		if (result == "yes")
		{
			return true;
		}
		else
		{ 
			return false;
		}
	}
	catch (e)
	{ 
		return false;
	}
}

function SetBrowseNewProcess(cscmObj)
{
	try
	{
		cscmObj.RegValueOf("HKEY_CURRENT_USER", "Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\BrowseNewProcess", "BrowseNewProcess") = "Yes";
	}
	catch(e)

	{
		return false;
	}
	return IsBrowseNewProcess(cscmObj);
}

function NewProcessLogin(cscmObj, url)
{
	try
	{
		if ( !IsBrowseNewProcess(cscmObj) && !SetBrowseNewProcess(cscmObj))
		{
			return false;
		}
		var result = cscmObj.Explore(GetHostPath(url));
		if (result == 1)
		{
			return true;
		}	
	}
	catch(e)
	{
	}
	return false;
}

function RestartExplorer(cscmObj, url)
{
	return;
}

function Close() 
{ 
	window.open('','_parent','');
	window.opener = null;
	window.close(); 
}

function ConfigSsoUserBind(proxyie, configData)
{
	for(var i = 0 ; i < configData.row.length; ++i)
	{
		var rc = configData.row[i];
		var use_ub = rc["use_ub"];
		if(rc != null && (use_ub&2) != 0)
		{
			var bSsoConfigStr = SinforSSOData.row[0][rc["id"]];
			if(bSsoConfigStr == null)		//Can not find the sso config info
			{
				//configData.row[i] = null;
				continue;
			}
			var destIp = GetRcIpAddr(rc);
			var bOk = proxyie.setSSOUserBindResource(destIp,rc["port_from"],rc["port_to"],rc["proto"]);
		}	
	}
}

function ConfigProxyieUserBind()
{
	if(SinforSSOData.row.length <= 0)
	{
		return;
	}
	if(HasSsoUserBind())
	{
		if(g_ProxyieObj == null)
		{
			g_ProxyieObj = CreateObject(G_DLLS[ID_PROXYIE][ID_PROGID]);
			if(g_ProxyieObj == null)
			{
				return;
			}
		}
		ConfigSsoUserBind(g_ProxyieObj, SinforAppRcData);
		ConfigSsoUserBind(g_ProxyieObj, SinforIpRcData);		
	}
}

function InstallUBDlls()
{
	var nUBDllCount = sinforUBDlls.length;
	//deal with null ubdata
	if(sinforUBData.row.length <= 0)
	{
		return;
	}
	//
	for(var i = 0; i< nUBDllCount; ++i)
	{
		var temp = sinforUBDlls[i].split(':');
		//handle default parser's md5
		if(temp[0] == "DefaultParser.dll")
		{
			temp[1] = g_defaultParserMd5;
		}
		var ret = g_CscmObj.CheckMD5UbDll(temp[0], temp[1]);
		if(ret == 0)
		{
			continue;
		}
		ret = g_CscmObj.InstallUbDll(window.location, temp[0], "");
		if(ret !=0 )
		{
			for(key in sinforUBData.row[0])
			{
				if(sinforUBData.row[0][key] == null)
				{
					continue;
				}
				var index= sinforUBData.row[0][key].indexOf(":");
				var ubdllId = sinforUBData.row[0][key].substring(0,index);
				if(ubdllId == i)
				{
					sinforUBData.row[0][key] = null;
				}
			}
		}
	}
}
function HasSsoUserBind()
{
	for(var i = 0;i< SinforRcData.row.length;++i)
	{
		if(SinforRcData.row[i]!=null)
		{
			var use_ub = SinforRcData.row[i]["use_ub"];
			if((use_ub&2) != 0)
			{
				return true;
			}
		}
	}
	return false;
}

/////////////////////////////////////////
// 
/////////////////////////////////////////
/*
 * Judge Administator

 * @param cscmObj ClientManager object
 * @return Administator return true，otherwise return false
 */
function IsAdminAccount(cscmObj)
{
	var admin = 0x220;
	return cscmObj.loginAccountType == admin;
}

function IsUserAccount(cscmObj)
{
	var user = 0x221;
	return cscmObj.loginAccountType == user;
}

function IsGuestAccount(cscmObj)
{
	var guest = 0x222;
	return cscmObj.loginAccountType == guest;
}

/*
 * Judge super administrtor

 * @param cscmObj ClientManager object
 * @return super administrator retur true，otherwise return false
 */
function IsPowerUserAccount(cscmObj)
{
	var powerUser = 0x223;
	return cscmObj.loginAccountType == powerUser;
}

function IsUnknownAccount(cscmObj)
{
	return cscmObj.loginAccountType == 0;
}

/////////////////////////////////////////
// component install function
/////////////////////////////////////////
/*
 * 

 * @param cscmObj   ClientManager object
 *        codebase  CAB path
 *        file     DLL file
 * @return success reutr true,otherwise return false
 */
function InstallDllByCAB(cscmObj, codebase, file)
{
	var url = GetHostPath(codebase);
	try
	{		
		return 1 == cscmObj.InstallClientInetComCAB(url, file);
	}
	catch(e)
	{
		return false;
	}
}

/*
 * Uninstall component
 * @param cscmObj   ClientManager object
 * @param progid    ID,as IEHelper.ProxyHelper.1
 * @return success return true, otherwise return false
 */
function UninstallDll(cscmObj, progid)
{
	try
	{
		var guid = cscmObj.CLSIDFromProgID(progid);
		var path = cscmObj.DllPathByGUID(guid);
		//linyan add for M4.1 控件升级不重启



		return 1 == cscmObj.UnregisterDll(path);
	}
	catch(e)
	{
		return false;
	}
}

/////////////////////////////////////////
// 
/////////////////////////////////////////
/*
 * progid create ActiveXobject
 * @param progid    ID,as IEHelper.ProxyHelper.1
 * @return success return object，otherwise return null
 */
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

/////////////////////////////////////////
// Component version function
/////////////////////////////////////////
/*
 * get object version with progid
 * @param cscmObj   ClientManagerobject
 *        progid    ID,as IEHelper.ProxyHelper.1
 * @return success return version，otherwise return null
 */
function VersionOfDll(cscmObj, progid)
{
	try
	{
		var guid = cscmObj.CLSIDFromProgID(progid);
		var path = cscmObj.DllPathByGUID(guid);
		return cscmObj.VersionOfFile(path);
	}
	catch(e)
	{
		return "";
	}
}
 
/*
 * check component version
 * @param cscmObj    ClientManagerobject
 *        progid     ID,as IEHelper.ProxyHelper.1
 *        requireVer 

 * @return match version retur true, otherwise retur false
 */
function IsDllVersionOK(cscmObj, progid, requireVer)
{
	try
	{
		var ver = "";
		ver = VersionOfDll(cscmObj, progid);
		return 0 == cscmObj.CompareVersion(ver, requireVer);
		
	}
	catch(e)
	{
		return false;
	}
}

function GetHostPath(path)
{
	var loc = window.location;
	return loc.protocol + "//" + loc.host + path;
}
/////////////////////////////////////////
// client function

/////////////////////////////////////////
/*
 * Judge Client Manager version

 * @return no install return E_INSTALL
 *         no match version retur E_UPDATE
 *         match version return E_OK
 */
function CheckCSCM()
{
	var progid     = G_DLLS[ID_CSCM][ID_PROGID];
	var requireVer = G_DLLS[ID_CSCM][ID_VER];
	if(g_CscmObj == null)
	{
		g_CscmObj = CreateObject(progid);
		if( null == g_CscmObj )//
		{
			return E_INSTALL;
		}
	}

	return IsDllVersionOK(g_CscmObj, progid, requireVer) ? E_OK : E_UPDATE;
}

/*
 * Install Client Manager
 * return

 */
function InstallCSCM()
{
	g_NoPrompt = true;
	g_bNeedIstallCSCM = true;
	window.location.href = "/com/installCSCM.html";
}
/*
 * Update Client Manager
 * @return CSCM No install return E_INSTALL
 *         No permiss return E_NO_RIGHT
 *         Uninstall Fail retur E_UNINSTALL
 *         Install Fail retur E_FAIL
 *         Success return E_OK
 */
function UpdateCSCM()
{
	var codeBase   = "/com/CSClientManagerPrj.CAB";
	var progid     = G_DLLS[ID_CSCM][ID_PROGID];
	if(g_CscmObj == null)
	{
		g_CscmObj = CreateObject(progid);
	}
	if( g_CscmObj == null )
	{
		return E_INSTALL;
	}
	
	var isHaveNoRight = (!IsAdminAccount(g_CscmObj) && !IsPowerUserAccount(g_CscmObj));
	//1，0，0，19之前的版本是不支持提升权限的，所以都需要管理员权限安装
	var compareVersion = g_CscmObj.CompareVersion(g_CscmObj.SelfVersion,"1,0,0,19");	
	if ( isHaveNoRight  && (compareVersion < 0 || compareVersion >= 0 && !g_CscmObj.IsServiceOK))
	{
		return E_NO_RIGHT;
	}
	
	if ( isHaveNoRight )
	{
		var ret = g_CscmObj.BeforeLogin;
		var downloadUrl = GetHostPath(G_DLLS[ID_SUPEREXE][ID_CODEBASE]);
	  ret = g_CscmObj.SetDownloadExeURL(downloadUrl);
	  MakeSuperExeOKWhenUpdateCscm(g_CscmObj);
	}

	//卸载原来的cscm，但会将注册表信息一起删除掉，那么新注册的也没有了 
	//获取原来的CSCM
	var oldguid = g_CscmObj.CLSIDFromProgID(progid);
	var oldpath = g_CscmObj.DllPathByGUID(oldguid);
	var ret = g_CscmObj.UnregisterDll(oldpath);
	g_CscmObj.BeforeLogin;
	
	if( !InstallDllByCAB(g_CscmObj, codeBase, "CSClientManagerPrj.dll") )
	{
		return E_FAIL;
	}
	
/*	if ( !IsAdminAccount(g_CscmObj) && !IsPowerUserAccount(g_CscmObj))
	{
	  g_CscmObj.LogOut;
	}
	*/
	g_CscmObj = null;
	return E_OK;
}

function HandleVista()
{
	try
	{
		SetCookie("StartIEAsAdminFail", "0");//

		var progid = G_DLLS[ID_CSCM][ID_PROGID];
		g_CscmObj = CreateObject(progid);
		if (g_CscmObj == null)
		{
			InstallCSCM();
			return E_INSTALL;
		}
		// linyan modify for M4.1 at 2008-11-4
		//1，0，0，19之前的版本是不支持提升权限的，所以都需要管理员权限安装
		var compareVersion = g_CscmObj.CompareVersion(g_CscmObj.SelfVersion,"1,0,0,19");
		if( !IsAdminAccount(g_CscmObj) && (compareVersion >= 0  && !g_CscmObj.IsServiceOK || compareVersion < 0))
		{
			var openUrl = GetHostPath("/por/shortcut.csp");
			if(typeof(g_UseDkeyVer) != "undefined" && (g_UseDkeyVer & DKEY_ENABLE_V3) && IsUsingDkey() &&(Usingnddkey()=="0v3"))//

			{
				openUrl = GetHostPath("/por/cert_shortcut.csp");
			}
			//ccm add for expand Cookie "UsingDkey"
			var Using_Dkey = GetCookie("UsingDkey");
			if(Using_Dkey != "0")
			{
				Using_Dkey = "1";
			}
			//ccm add end
			openUrl += "?id=" + GetCookie("TWFID") + "&app=" + GetCookie("AppEnabled") + "&ip=" + GetCookie("IpEnabled") + "&webonly=" + GetCookie("webonly") + "&UsingDkey=" + Using_Dkey;
			openUrl += "&G_TransMethod=" + GetCookie("G_TransMethod") + "&TransMethod=" + GetCookie("TransMethod");
			openUrl += "&destUrl=" + window.location;
			g_NoPrompt = true;
			var downloadUrl = GetHostPath("/com/StartIEAsAdmin.exe");
			var ret = g_CscmObj.StartIEAsAdminV2(downloadUrl, openUrl);
			if(0 != ret)
			{
				SetCookie("StartIEAsAdminFail", "1");
				return E_FAIL;
			}
			else
			{
				SetCookie("StartIEAsAdminFail", "0");
				g_CscmObj.CloseIE();
				return E_INSTALL;
			}
		}
		return E_OK;
	}
	catch(e)
	{
		InstallCSCM();
		return E_INSTALL;
	}
}

function MakeCscmOK()
{
	if( IsVista() )
	{
		var ret = HandleVista();
		if(E_OK != ret)
		{
			return E_INSTALL;
		}
	}

	var IsUpdateCscm = false;
	var ret = CheckCSCM();
	switch(ret)
	{
		case E_INSTALL:
			InstallCSCM();
			return E_INSTALL;
			//linyan delete for M4.1 控件更新不重启 at 2008-10-21
		case E_UPDATE:
			var updateResult = UpdateCSCM();
			IsUpdateCscm = true;
			//linyan delete for M4.1 控件更新不重启 at 2008-10-21
			break;
		case E_OK:
			break;
		default:
			return false;
	}

	g_CscmObj = null;
	
	g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	
	if(g_CscmObj != null)
	{
		var downloadUrl = GetHostPath(G_DLLS[ID_SUPEREXE][ID_CODEBASE]);
	  var ret = g_CscmObj.SetDownloadExeURL(downloadUrl);
	  if(IsUpdateCscm || GetCookie("haveLogin") != "1")
	  {
			ret = g_CscmObj.BeforeLogin;//更新cscm的时候都先调用这个
	  }
	
		ret = MakeServiceOK(g_CscmObj);

		if(!IsAdminAccount(g_CscmObj) && !ret)
		{
			return false;
		}
		MakeSuperExeOK(g_CscmObj);

		var r = g_CscmObj.IsUserSuperExeOK;	/*add by cml 2008 8 16*/
		
		//add by hjm for IE8 support
		try
		{
		
			if( g_bInstallIEHook == false )
			{
			
				if( IsPowerUserAccount(g_CscmObj)==false && g_CscmObj.GetIEMajorVersion>=8 )
				{
		
					if( GetCookie("haveLogin") == "1"  &&  g_CscmObj.reloginEx != 0 )
					{
			
						//启动外部挂钩
						if( g_CscmObj.StartSinforIEHook == "1" )
						{
							g_bInstallIEHook = true;
						}
						
					}
				}
			}
		}
		catch(e)
		{
			//end add by hjm
		}
	}
	return true;
}
//////////////////////////////////////////////////////////////////////////////
/*
 * judge component 
 * @param  cscmObj    ClientManagerobject
 *         progid     ID,IEHelper.ProxyHelper.1
 *         requireVer 
 * @return no install return E_INSTALL
 *         no match version return E_UPDATE
 *         match version return E_OK
 */
function CheckActiveXControlVersion(cscmObj, progid, requireVer)
{
	var vCLSID  = cscmObj.CLSIDFromProgID(progid);
	if( vCLSID == "" )
	{
		//没有安装
		return E_INSTALL;
	}
	else
	{
		//已经安装
		try
		{
			cscmObj.MakeUnblockInVistaIE8(vCLSID);//设置注册表
		}
		catch(e)
		{
		
		}
		return IsDllVersionOK(cscmObj, progid, requireVer) ? E_OK : E_UPDATE;
	}
}

/*
* ie7 will note when component nerver loaded

* this function  make all component has loaded.
*/
function MakeComponentUnblocked(cscmobj, progid)
{
	var nav=navigator.appVersion;
	if( nav.indexOf('MSIE') != -1 )
	{
  		var curIE = nav.substr(nav.indexOf('MSIE')+5,3);
		if(curIE < 7)
		{
			return true;
		}
	}
	else
	{
		return true;
	}
		
	if(!cscmobj)
	{
		return false;
	}
	try
	{
		var clsid = cscmobj.CLSIDFromProgID(progid);
		var vIsUnBlockOK = cscmobj.IsComponentUnblock(clsid);
		if( vIsUnBlockOK == 0 )
		{
			var key   = "Software\\Microsoft\\Windows\\CurrentVersion\\Ext\\PreApproved\\" + clsid ;
			cscmobj.TypeRegValueOf("HKEY_LOCAL_MACHINE", key, "Flags", 4,4) = "4";
		}
		cscmobj.MakeUnblockInVistaIE8(clsid);
		return true;
	}
	catch(e)
	{
		return false;
	}	
}
/*
 
* Install Component
 * @param  cscmObj    ClientManagerobject
 *         codeBase   path
 *         dllName    dll name

 * @return No permision return E_NO_RIGHT
 *         Success return E_OK
 *         Fail return E_FAIL
 */
function InstallActiveXControl(cscmObj, codeBase, dllName)
{
	return InstallDllByCAB(cscmObj, codeBase, dllName) ? E_OK : E_FAIL;
}

/*
 * Update Active Component
 * @param  cscmObj     ClientManagerobject
 *         progid     ID,如IEHelper.ProxyHelper.1
 *         codeBase Path
 *         dllName    Dll name

 * @return No permiss return E_NO_RIGHT
 *         Success return E_OK
 *         Fail return E_FAIL
 */

function UpdateActiveXControl(cscmObj, progid, codeBase, dllName)
{
	if( UninstallDll(cscmObj, progid) )
	{
		//return E_UNINSTALL;
	}
	//linyan add for M4.1 控件更新不重启



	/* add by cml 2008-11-17*/
	/*安装ProxyIe的时候。在Vista保护模式下，解决安装链式ISA后，更新链式ProxyIE->ProxyIE出现SinforPromote出现的问题*/
	if( ( progid == G_DLLS[ID_PROXYIE][ID_PROGID] ) && (IsVista() == true )  && (IsAdminAccount(cscmObj) == false ))
	{
		cscmObj.StopSuperExe;
		//var downloadUrl = GetHostPath("/com/SuperExeInstaller.exe");
		//cscmObj.StartSuperExe(downloadUrl,"");
	} 
	/* end add by cml*/

	var ret = InstallDllByCAB(cscmObj, codeBase, dllName);
	return ret?E_OK:E_FAIL;
	//end linyan add 
}
/*
 * MakeActiveXControlOK
 * @param  cscmObj         ClientManagerobject
 *         progid         
 *         requireVersion  
 *         dllName         

 *         codeBase        
 *         setRestart     
 * @return Uninstall return E_INSTALL
 *         No permison return E_NO_RIGHT
 *         Normal return E_OK
 */
function MakeActiveXControlOK(cscmObj, progid, requireVersion, dllName, codeBase, setRestart)
{
	var ret = CheckActiveXControlVersion(cscmObj, progid, requireVersion);
	switch(ret)
	{
		case E_INSTALL://64
			try
			{
				ret = InstallActiveXControl(cscmObj, codeBase, dllName);
				MakeComponentUnblocked(cscmObj, progid);
			}
			catch(e)
			{
			}
			if(progid == G_DLLS[ID_IEHELPER][ID_PROGID])
				g_IsBhoUpdate = true;
			if(progid == G_DLLS[ID_SSOCLIENT][ID_PROGID])
				g_IsSsoBhoUpdate = true;
			SetNeedRestart(true);
			return E_OK;
		case E_UPDATE://256
			ret = UpdateActiveXControl(cscmObj, progid, codeBase, dllName);
			MakeComponentUnblocked(cscmObj, progid);
			if(E_OK == ret && setRestart)
			{
				if(dllName == G_DLLS[ID_HARDID][ID_FILE]) 
				{ 
					g_HardidNeedRestart = true; 					
					MakeComponentUnblocked(cscmObj, progid);
					
					return ret;
				}
			}
			if(progid == G_DLLS[ID_HARDID][ID_PROGID])
			{
				g_NspObj = null;
				g_NspObj = CreateObject(progid);
			}
			if(progid == G_DLLS[ID_CLIENTNSP][ID_PROGID])
			{
				g_NspObj = null;
				g_NspObj = CreateObject(progid);
			}
			if(progid == G_DLLS[ID_SSOCLIENT][ID_PROGID])
			{
				g_SsoObj = null;
				g_SsoObj = CreateObject(progid);
			}
			if(progid == G_DLLS[ID_PROXYIE][ID_PROGID])
			{
				g_ProxyieObj = null;
				g_ProxyieObj = CreateObject(progid);
			}
			if(progid == G_DLLS[ID_EPND][ID_PROGID])
			{
				g_DKeyObj = null;
				g_DKeyObj = CreateObject(progid);
			}
			
	//		MakeComponentUnblocked(cscmObj, progid);
			if(progid == G_DLLS[ID_IEHELPER][ID_PROGID])
				g_IsBhoUpdate = true;
			if(progid == G_DLLS[ID_SSOCLIENT][ID_PROGID])
				g_IsSsoBhoUpdate = true;
			SetNeedRestart(true);
			return E_OK;
		case E_OK:
			MakeComponentUnblocked(cscmObj, progid);
			if(progid == G_DLLS[ID_IEHELPER][ID_PROGID])
				g_IsBhoUpdate = false;
			if(progid == G_DLLS[ID_SSOCLIENT][ID_PROGID])
				g_IsSsoBhoUpdate = false;
			return E_OK;
		default:
			return E_UNKNOWN;
	}
}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
/*
 * Make NSP hook OK
 * @param cscmObj ClientManagerobject
 * @return Uninstall return E_INSTALL
 *         No permison retur E_NO_RIGHT
 *         Repair return E_REPAIR
 *         Normal retur E_OK
 */
function MakeNSPHookOK(cscmObj)
{
	var result = 0;
	var progid = G_DLLS[ID_CLIENTNSP][ID_PROGID];
	var nsp    = CreateObject(progid);
	if( nsp == null )
	{
		return E_INSTALL;
	}
	try
	{
		result = nsp.CheckProvider;
	}
	catch(e)
	{
		result = 0;
	}
	if( result == 0 )
	{
		/*if ( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) )
		{
			return E_NO_RIGHT;
		}*/		
		try
		{ 
			if ( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) ) 
			{ 
					var dllName        = G_DLLS[ID_CLIENTNSP][ID_FILE];
					var codeBase       = G_DLLS[ID_CLIENTNSP][ID_CODEBASE];
					result = InstallActiveXControl(cscmObj, codeBase, dllName);
				  SetNeedRestart(true);
			} 
			else
			{ 
					result = nsp.InstallProvider;
			}
			
		}
		catch(e)
		{
			result = 0;
		}
		if( result == 0 )
		{
			return E_REPAIR;
		}
	}
	return E_OK;
}

/*
 * Make IEHelper hook OK
 * @param cscmObj ClientManager object
 * @return Uninstall return E_INSTALL
 *         No permison retur E_NO_RIGHT
 *         Repair return E_REPAIR
 *         Normal retur E_OK
 */
function MakeIEHelperHookOK(cscmObj)
{
	var result   = 0;
	var progid   = G_DLLS[ID_IEHELPER][ID_PROGID];
	var g_IEHelperObj = CreateObject(progid);
	if( g_IEHelperObj == null )
	{
		return E_INSTALL;
	}
	try
	{
		result = g_IEHelperObj.CheckProvider;
	}
	catch(e)
	{
		result = 0;
	}
	if( result == 0 )
	{
		/*if ( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) )
		{
			return E_NO_RIGHT;
		}*/		
		try
		{
			if ( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) ) 
			{ 
					var dllName        = G_DLLS[ID_IEHELPER][ID_FILE];
					var codeBase       = G_DLLS[ID_IEHELPER][ID_CODEBASE];
					result = InstallActiveXControl(cscmObj, codeBase, dllName);
				  SetNeedRestart(true);
			} 
			else
			{ 
					result = g_IEHelperObj.InstallProvider;
			}
		}
		catch(e)
		{
			result = 0;
		}
		if( result == 0 )
		{
			return E_REPAIR;
		}
		//SetNeedRestart(true);
	}
	KeepIEHelperHook();
	return E_OK;	
}

function KeepIEHelperHook()
{
	if(g_IEHelperObj == null)
	{
		g_IEHelperObj = CreateObject(G_DLLS[ID_IEHELPER][ID_PROGID]);
	}
	try
	{
		result = g_IEHelperObj.CheckProvider;
	}
	catch(e)
	{
		result = 0;
	}
	if( result == 0 )
	{
		try
		{
			
			if ( !IsAdminAccount(g_CscmObj) && !IsPowerUserAccount(g_CscmObj) ) 
			{ 
					var dllName        = G_DLLS[ID_IEHELPER][ID_FILE];
					var codeBase       = G_DLLS[ID_IEHELPER][ID_CODEBASE];
					result = InstallActiveXControl(g_CscmObj, codeBase, dllName);
				  SetNeedRestart(true);
			} 
			else
			{ 
					result = g_IEHelperObj.InstallProvider;
			}
			
		}
		catch(e)
		{
			result = 0;
		}
		if( result == 0 )
		{
			return;
		}
	}
	window.setTimeout("KeepIEHelperHook()", 1000);
}

/*
 * Make ProxyIE hook OK
 * @param cscmObj ClientManager object
 * @return Uninstall return E_INSTALL
 *         No permison retur E_NO_RIGHT
 *         Repair return E_REPAIR
 *         Normal retur E_OK
 */
function MakeProxyIEHookOK(cscmObj)
{
	var result   = 0;
	var progid   = G_DLLS[ID_PROXYIE][ID_PROGID];
	if(g_ProxyieObj == null)
	{
		g_ProxyieObj = CreateObject(progid);
	}
	if( g_ProxyieObj == null )
	{
		return E_INSTALL;
	}
	try
	{
		g_ProxyieObj.test();
	}
	catch(e)
	{
		/*if( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) )
		{
			return E_NO_RIGHT;
		} */
		try
		{
			if ( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) ) 
			{ 
					var dllName        = G_DLLS[ID_PROXYIE][ID_FILE];
					var codeBase       = G_DLLS[ID_PROXYIE][ID_CODEBASE];
					result = InstallActiveXControl(cscmObj, codeBase, dllName);
				 // SetNeedRestart(true);
			} 
			else
			{ 
					g_ProxyieObj.installProvider();
				//	SetNeedRestart(true);
			}	
		}
		catch(e) 
		{
			return E_REPAIR;
		}
		try
		{
			if( 0 == g_ProxyieObj.browseNewProcess )
			{
				g_ProxyieObj.browseNewProcess = 1;
				if( 0 == g_ProxyieObj.browseNewProcess )
				{
					return E_FAIL;
				}
			}
		}
		catch(e)
		{
			return E_FAIL;
		}
	}
	KeepProxyIEHook();
	return E_OK;	
}

function KeepProxyIEHook()
{
	try
	{
		if(g_ProxyieObj == null)
		{
			return;
		}
		g_ProxyieObj.test();
	}
	catch(e)
	{
		try
		{
			if ( !IsAdminAccount(g_CscmObj) && !IsPowerUserAccount(g_CscmObj) ) 
			{ 
					var dllName        = G_DLLS[ID_PROXYIE][ID_FILE];
					var codeBase       = G_DLLS[ID_PROXYIE][ID_CODEBASE];
					result = InstallActiveXControl(g_CscmObj, codeBase, dllName);
				  SetNeedRestart(true);
			} 
			else
			{ 
					g_ProxyieObj.installProvider();
			}

		}
		catch(e) 
		{
		}
		try
		{
			if( 0 == g_ProxyieObj.browseNewProcess )
			{
				g_ProxyieObj.browseNewProcess = 1;
				if( 0 == g_ProxyieObj.browseNewProcess )
				{
				//do nothing
				}
			}
		}
		catch(e)
		{
		}
	}
	window.setTimeout("KeepProxyIEHook()", 1000);
}

function StopIEHelper()
{
	try 
	{
		var progid = G_DLLS[ID_IEHELPER][ID_PROGID];
		if(g_IEHelperObj == null)
		{
			g_IEHelperObj = CreateObject(progid);
		}
		g_IEHelperObj.setExitURL("");
		g_IEHelperObj.clear();
	} 
	catch (e) 
	{
	}
}

///////////////////////////////////////////////
// extern interface
///////////////////////////////////////////////
/*
 * Make NSP OK
 * @param cscmObj ClientManagerobject
 * @return success return true，otherwise return false
 */
function MakeNspOK(cscmObj)
{
	var nspProgid         = G_DLLS[ID_CLIENTNSP][ID_PROGID];
	var nspRequireVersion = G_DLLS[ID_CLIENTNSP][ID_VER];
	var nspDllName        = G_DLLS[ID_CLIENTNSP][ID_FILE];
	var nspCodeBase       = G_DLLS[ID_CLIENTNSP][ID_CODEBASE];
	var nspCommonName     = G_DLLS[ID_CLIENTNSP][ID_NAME];
	var ret = MakeActiveXControlOK(cscmObj, nspProgid, nspRequireVersion, nspDllName, nspCodeBase, true);
	if(ret != E_OK)
	{
		return false;
	}

	ret = MakeNSPHookOK(cscmObj);
	switch(ret)
	{
		case E_INSTALL:
			return false;
		case E_NO_RIGHT:
			return false;
		case E_REPAIR:
			return false;
		case E_OK:
			return true;
		default:
			return false;
	}
	return true;
}

/*
 * Make IEHelper Ok
 * @param cscmObj ClientManagerobject
 * @return success return true，otherwise return false
 */
function MakeIEHelperOK(cscmObj)
{
	var progid         = G_DLLS[ID_IEHELPER][ID_PROGID];
	var requireVersion = G_DLLS[ID_IEHELPER][ID_VER];
	var dllName        = G_DLLS[ID_IEHELPER][ID_FILE];
	var codeBase       = G_DLLS[ID_IEHELPER][ID_CODEBASE];
	var commonName     = G_DLLS[ID_IEHELPER][ID_NAME];
	var ret = MakeActiveXControlOK(cscmObj, progid, requireVersion, dllName, codeBase, true);
	if(ret != E_OK)
	{
		return false;
	}
	/* linyan add for M4.1 控件更新不重启 at 2008-10-24*/
	if(g_IsBhoUpdate)//更新或安装时才调用



	{
		g_IEHelperObj = null;
		g_IEHelperObj = CreateObject(progid);
		if(g_IEHelperObj != null)
		{
			try
			{
				if(g_BhoObj == null)
				{
					MakeComponentUnblocked(cscmObj, "SinforBHO.SinforIEBHO.1");
					g_BhoObj = CreateObject("SinforBHO.SinforIEBHO.1");
				}
				if(g_BhoObj != null)
				{
					g_IEHelperObj.LoadBHO(document,g_BhoObj);
					g_IsBhoUpdate = false;
				}
			}
			catch(e)
			{
			}
		}
		else
		{
		}
	}
	/* end  linyan add */
	ret = MakeIEHelperHookOK(cscmObj);
	if(ret != E_OK)
	{
		UninstallDll(cscmObj, progid);
		var iRet = MakeActiveXControlOK(cscmObj, progid, requireVersion, dllName, codeBase, true);
		if(iRet != E_OK)
		{
			ret = E_UNKNOWN;
		}
		ret = MakeIEHelperHookOK(cscmObj);
	}
	switch(ret)
	{
		case E_INSTALL:
			return false;
		case E_NO_RIGHT:
			return false;
		case E_REPAIR:
			return false;
		case E_OK:
			return true;
		default:
			return false;
	}
	//zhk add for locale
	return true;
}

/*
 * Make ProxyIE OK
 * @param cscmObj ClientManager object
 * @return success return true，otherwise return false
 */
function MakeProxyIEOK(cscmObj)
{
	var progid         = G_DLLS[ID_PROXYIE][ID_PROGID];
	var requireVersion = G_DLLS[ID_PROXYIE][ID_VER];
	var dllName        = G_DLLS[ID_PROXYIE][ID_FILE];
	var codeBase       = G_DLLS[ID_PROXYIE][ID_CODEBASE];
	var commonName     = G_DLLS[ID_PROXYIE][ID_NAME];

	var ret = MakeActiveXControlOK(cscmObj, progid, requireVersion, dllName, codeBase, true);
	if(ret != E_OK)
	{
		return false;
	}
		
	ret = MakeProxyIEHookOK(cscmObj);
	switch(ret)
	{
		case E_INSTALL:
			return false;
		case E_NO_RIGHT:
			return false;
		case E_REPAIR:
			return false;
		case E_OK:
			/* linyan add for M4.2 WebCache and web optimize at 2009.4.23 */
			// modify by lpt
			if (IsWebCacheEnabled())
			{
				EnableWebCache(g_wc_mode,g_wc_count,g_wc_url);
			}
			else
			{
				DisableWebCache();
			}
			/*lixing modefy */
			/*if(g_wopt_enable & 5)
			{
				g_ProxyieObj.setWebOpt(g_wopt_support_device); 
				g_ProxyieObj.setWebOptimizeBlackAndWhiteList(g_wopt_mode, g_wope_filter, g_wopt_url);		
			}
			else
			{
				g_ProxyieObj.setWebOpt(0);
			}*/
			/* end linayan add */
			return true;
		default:
			return false;
	}
	return true;
}

/*
 * config NSP 
 * @param none

 * @return success return true，otherwise return false
 */
function ConfigNsp()
{
	var configData = SinforDnsData.row;
	var configDnsRules = SinforDnsFilterRules.row;
	if(g_NspObj == null)
	{
		g_NspObj = CreateObject(G_DLLS[ID_CLIENTNSP][ID_PROGID]);
	}
	var tmp;
	if(g_NspObj == null)
	{
		return false;	
	}
	for(var i = 0; i < configData.length; i++)
	{
		tmp = g_NspObj.addHostMapping(configData[i]["name"], configData[i]["addr"]);
	}
	for(var j = 0; j < configDnsRules.length; j++)
	{
		tmp = g_NspObj.addDnsFilter(configDnsRules[j]["rules_name"]);	
	}   
	tmp = g_NspObj.addSSLHost(document.location.hostname, g_serverPort,"01234567890123456789012345678900",32);
	
	//add by hjk 2009.4.24
	var domain_array = g_autorule_domain.split(',', g_autorule_count);
	for(var i = 0; i < g_autorule_count; i++)
	{
		g_NspObj.SetBlcakOrWhiterFilter(domain_array[i], g_autorule_mode);
	}
	//end add by hjk
	return true;
}

function StartNsp()
{
	if(g_NspObj != null)
	{ 
		if( IsVista() )
		{ 
			MakeSuperExeOK(g_CscmObj);
		}
		g_NspObj.SetDnsIP(g_PrimayDnsServer, g_SecondDnsServer);
		g_NspObj.startService();
		return true;
	}
	return false;
}

function StopNsp()
{
	var nspObj = CreateObject(G_DLLS[ID_CLIENTNSP][ID_PROGID]);
	if(nspObj == null)
	{
		return false;	
	}
	nspObj.stopService();
	return true;
}
function GetExitUrl(path)
{
    var loc = window.location;
    return loc.protocol + "//" + loc.host + path;
}
/*
 * return ProxyIe type ,for setFilter
 * @param serviceType rc type,for example ..http,ftp
 * @return proxyie type

 */
function GetProxyType(serviceType)
{
	if(serviceType.toLowerCase() == "http")
	{
		return 3;
	}
	else if(serviceType.toLowerCase().indexOf("ftp") != -1)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}
/*
 * return host's ip
 * @param rc array

 * @remarks look for ip
 */
function GetRcIpAddr(rc)
{
	var hostMapData = SinforDnsData.row;
	for(var i=0; i<hostMapData.length; i++)
	{
		if(hostMapData[i]["rcid"] == rc["id"])
		{
			return hostMapData[i]["addr"];
		}
	}
	return rc["host_from"];
}
/*
 * config proxyie
 * @return success return true，otherwise return false
 */
function ConfigProxyIE()
{
	var configData = SinforAppRcData.row;
	var userName   = g_loginName;
	var userMode   = g_userMode;
	var compress   = g_compress;
	var serverPort = g_serverPort;
	var sslctx     = g_sslctx;
	//add by yuanxiang  for M4.2 at 2009-4-19
	var LockFileData = SinforLockFileData.row; 
	var LockExeCounts = SinforExeCounts ;
	var LockFileCounts = SinforLockFileCounts ;
	//end yuanxiang
	if(g_ProxyieObj == null)
	{
		g_ProxyieObj = CreateObject(G_DLLS[ID_PROXYIE][ID_PROGID]);
	}
	if(g_ProxyieObj == null)
	{
		return false;				
	}
	g_ProxyieObj.autoClearIECache();
	g_ProxyieObj.setCacheHost(document.location.host);
	//clean cache
	g_ProxyieObj.setCacheHost(GetExitUrl(""));
	//user mode
	g_ProxyieObj.usermode = userMode;
	g_ProxyieObj.userName = userName;
	g_ProxyieObj.disableAutoClose();
	var proxyType = 0;
	var hostip  = "0.0.0.0";
	
	for(var i=0; i < configData.length; i++)
	{
		//jzw add for M4.2 UserBinding
		if(configData[i] == null)
		{
			continue;
		}
		//end jzw add
		if(configData[i]["service"].toLowerCase() == "http" )
		{
			if( configData[i]["port_from"] == 80 )
			{
				g_ProxyieObj.setCacheHost(configData[i]["host_from"]);
			}
			else
			{
				g_ProxyieObj.setCacheHost(configData[i]["host_from"] + ":" + configData[i]["port_from"]);					
			}
		}
		else if( configData[i]["service"].toLowerCase() == "https" )
		{
			if( configData[i]["port_from"] == 443 )
			{
				g_ProxyieObj.setCacheHost(configData[i]["host_from"]);
			}
			else
			{
				g_ProxyieObj.setCacheHost(configData[i]["host_from"] + ":" + configData[i]["port_from"]);					
			}	
		}
		proxyType = GetProxyType(configData[i]["service"]);
		//jzw modify for M4.2 UserBinding
		if((configData[i]["use_ub"]&1) == 0)
		{
			if( configData[i]["host_to"].length > 0 )//ip range
			{
				var bIsEnableAppSupport = (g_autorule_enable  == 1) ? parseInt(configData[i]["is_app_recursion"]) : 0 ;
				g_ProxyieObj.setIpRangeFilter(configData[i]["host_from"], configData[i]["host_to"], parseInt(configData[i]["port_from"]), parseInt(configData[i]["port_to"]), parseInt(proxyType),parseInt(configData[i]["id"]),bIsEnableAppSupport,1);	
			}
			else
			{
				hostip = GetRcIpAddr(configData[i]);
				var bIsEnableAppSupport = (g_autorule_enable  == 1) ? parseInt(configData[i]["is_app_recursion"]) : 0 ;
				g_ProxyieObj.setFilter(hostip, parseInt(configData[i]["port_from"]), parseInt(configData[i]["port_to"]), hostip, parseInt(configData[i]["port_from"]), parseInt(proxyType),parseInt(configData[i]["id"]),bIsEnableAppSupport,1);
			}
		}
		else
		{
			hostip = GetRcIpAddr(configData[i]);
			if(sinforUBData.row.length > 0)
			{
				var ubinfo = sinforUBData.row[0][configData[i]["id"]];
				if(ubinfo == null)
				{
					var bIsEnableAppSupport = (g_autorule_enable  == 1) ? parseInt(configData[i]["is_app_recursion"]) : 0 ;
					g_ProxyieObj.setFilter(hostip, parseInt(configData[i]["port_from"]), parseInt(configData[i]["port_to"]), hostip, parseInt(configData[i]["port_from"]), parseInt(proxyType),parseInt(configData[i]["id"]),bIsEnableAppSupport,1);		
					continue;
				}
				var uboffset = ubinfo.indexOf(":");
				if(uboffset != -1)
				{
					ubinfo = ubinfo.substr(uboffset+1);
					var bIsEnableAppSupport = (g_autorule_enable  == 1) ? parseInt(configData[i]["is_app_recursion"]) : 0 ;
					g_ProxyieObj.setFilterWithUB(hostip, parseInt(configData[i]["port_from"]), parseInt(configData[i]["port_to"]), hostip, parseInt(configData[i]["port_from"]), parseInt(proxyType),parseInt(configData[i]["id"]),bIsEnableAppSupport,1, ubinfo);		
				}
			}
			else
			{
				var bIsEnableAppSupport = (g_autorule_enable  == 1) ? parseInt(configData[i]["is_app_recursion"]) : 0 ;
				g_ProxyieObj.setFilter(hostip, parseInt(configData[i]["port_from"]), parseInt(configData[i]["port_to"]), hostip, parseInt(configData[i]["port_from"]), parseInt(proxyType),parseInt(configData[i]["id"]),bIsEnableAppSupport,1);						
			}
		}
		//end jzw modify
	}
	//ssl csproxy
	
	/*lsc add 20090519*/
	if(g_svpnlanaddr!="" &&(g_sendBandwidth > 0 || g_recvBandwidth > 0)&& SinforGetWebRcCount()>0)
	{
		g_ProxyieObj.setFilter(g_svpnlanaddr, g_serverPort, g_serverPort, g_svpnlanaddr, g_serverPort, 3,0,0,1);
	}
	/*end of lsc add*/
	
	g_ProxyieObj.setSSLHost(document.location.hostname, serverPort, sslctx); 
	g_ProxyieObj.setSecurityParam(1, compress);
	//g_ProxyieObj.setMaxThread(g_ProxyIEMaxThread);
	
    //yuanxiang add for M4.2 at 4-19
	g_ProxyieObj.setMaxThread(g_appMaxSessions);
	//设置带宽和全局会话数



    //g_ProxyieObj.SetBandInfo(g_recvBandwidth,g_sendBandwidth);
	g_ProxyieObj.init_fluxcontrol();
	
   //设置关键文件保护
   if(LockExeCounts != 0 && LockFileCounts != 0)
   {
       g_ProxyieObj.SetLockFileInfo(LockExeCounts,LockFileCounts);
	   for(var i = 0;i < LockFileData.length;i++)
	   {
	          g_ProxyieObj.AddLockFileRule(LockFileData[i]["lockfile_struct"]);
	   }
          
   }
   //end yuan
    return true;
}

function StartProxyIE()
{
	if(g_ProxyieObj != null)
	{
		g_ProxyieObj.startProxy(g_autorule_enable_limit, g_autorule_gather_rule_limit);
		return true;
	}
	return false;
}

function StopProxyIE()
{
	if( g_ProxyieObj == null )
	{
		g_ProxyieObj = CreateObject(G_DLLS[ID_PROXYIE][ID_PROGID]);
	}
	if(g_ProxyieObj == null)
	{
		return false;				
	}
	g_ProxyieObj.clearFilter();
	g_ProxyieObj.stopProxy();
	g_ProxyieObj.close_fluxcontrol();
	g_ProxyieObj.CloseLockFile();
	
	return true;
}

function StopAll()
{
	try
	{
		SetCookie("haveLogin", "0");
		DisableSecurityCheck();
		DisableAppSupport();
		DisableIpSupport();
		DisableSddn();
		DisableSso();
		DisableUCP(); 
		//DisableSuperExe();
		ClientSSLUsrLogout();
	}
	catch(e)
	{
	}
}
function EnableAppSupport(configOnly)
{
	var ret = MakeCscmOK();
	if(ret == E_RELOGIN)
	{
		return false;
	}
	if( E_INSTALL == ret )
	{
		if(GetCookie("StartIEAsAdminFail") == "1")
		{
			SetCookie("AppEnabled", "0");
		}
		else
		{
			SetCookie("AppEnabled", "1");
		}
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	if(ret == false)
	{
		SetCookie("AppEnabled", "0");
		return false;
	}
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if( g_CscmObj == null )
	{
		return false;
	}
	//jzw add for M4.2 UserBinding
	var bProxyIeOk = MakeProxyIEOK(g_CscmObj);
	ConfigProxyieUserBind();
	//end jzw add	
	//make app support component ok
	if( !( bProxyIeOk && MakeNspOK(g_CscmObj)) )
	{
		return false;
	}
	SetCookie("AppEnabled", "1");
	
	//config app support component
	if( !( ConfigNsp() && StartNsp() && ConfigProxyIE() ) )
	{
		SetCookie("AppEnabled", "0");
		return false;
	}
	if(configOnly)
	{
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	
	 MakeIEHelperOK(g_CscmObj);

	if( !StartProxyIE() )
	{
		SetCookie("AppEnabled", "0");
		return false;
	}
	/*
	if( !StartNsp() )
	{
		SetCookie("AppEnabled", "0");
		return false;
	}*/
	var running = true;
	SetAppSupportState(running);
	/*add by lwq 2008-11-20*/
	if(g_CscmObj != null)
		checkReLogin();
	/*end add by lwq 2008-11-20*/
	return true;
}

function SetAppSupportState(running)
{
	g_AppSupportState = (running ? "1" : "0");
}

function GetAppSupportState()
{
	return g_AppSupportState == "1";
}

function DisableAppSupport()
{
	try
	{
		SetCookie("AppEnabled", "0");
		StopProxyIE();
		StopNsp();
		var running = false;
		SetAppSupportState(running);
	}
	catch (e)
	{
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////
//  ip service
////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * base64 encoding
 * @param str 

 * @return str

 */
function base64encode(str) {
	var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var out, i, len;
	var c1, c2, c3;
	len = str.length;
	i = 0;
	out = "";
	while(i < len) 
	{
		c1 = str.charCodeAt(i++) & 0xff;
		if(i == len)
		{
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt((c1 & 0x3) << 4);
			out += "==";
			break;
		}
		c2 = str.charCodeAt(i++);
		if(i == len)
		{
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
			out += base64EncodeChars.charAt((c2 & 0xF) << 2);
			out += "=";
			break;
		}
		c3 = str.charCodeAt(i++);
		out += base64EncodeChars.charAt(c1 >> 2);
		out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
		out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
		out += base64EncodeChars.charAt(c3 & 0x3F);
	}
	return out;
}

/*
 * IPTunnl can use?
 * @param cscmObj ClientManagerobject
 * @return can use return true，otherwise return false
 */
function IsCanUseIpTun(cscmObj)
{
    var win2000 = 5;
    var os = cscmObj.OsVersion;
    if (os < win2000)
    {
	    return false;
    }
    //return IsAdminAccount(cscmObj);
     return true;
}
/*
 * @param cscmObj ClientManagerobject
 *        requireVer:target version
 * @return match version retur E_OK，no match retur E_UPDATE，no install return E_INSTALL
 */
function CheckVnicVersion(cscmObj, requireVer)
{
	var ver = cscmObj.VNICVersion;
	if(ver == "")
	{
		return E_INSTALL;
	}
	if( cscmObj.CompareVersion(ver, requireVer) == 0 )
	{
		return E_OK;
	}
	else
	{
		return E_UPDATE;
	}
}
/*
 * Install VNIC
 * @param cscmObj ClientManagerobject
 * @return success return true，other return false
 */
function InstallVnic(cscmObj)
{
	var downloadUrl = GetHostPath("/com/VNICInstaller.exe");
	var result = cscmObj.UpdateVNIC(downloadUrl, "");
	return 0 != result;
}

/*
 * Uninstall VNIC
 * @param cscmObj ClientManager object
 * @return success return true，other return false
 */
function UninstallVnic(cscmObj)
{
	var dir    = cscmObj.VNICUninstDir;
	var param  = "/S _?=" + dir;
	return 0  != cscmObj.UninstallVNIC(param);
}

/*
 * MAKE VNIC OK
 * @param cscmObj ClientManager object
 * @return success return true，other return false
 */
function MakeVnicOK(cscmObj)
{
	//var requireVer = "1,0,0,8";
	var ret = CheckVnicVersion(cscmObj, g_VnicRequireVer);
	switch(ret)
	{
		case E_INSTALL:
			return InstallVnic(cscmObj);
		case E_UPDATE:
			UninstallVnic(cscmObj);
			return InstallVnic(cscmObj);
		case E_OK:
			return true;
		default: 
			return false;
	}
}

// ip tunel compoments
/*
 * @param cscmObj ClientManagerobject
 *        requireVer:target version
 * @return match version retur E_OK，no match retur E_UPDATE，no install return E_INSTALL
 */
function CheckIpTunVersion(cscmObj, requireVer)
{
	var ver = cscmObj.IPTunnelVersion;
	if(ver == "")
	{
		return E_INSTALL;	
	}
	if( cscmObj.CompareVersion(ver, requireVer) != 0 )
	{
		return E_UPDATE;
	}
	else
	{
		return E_OK;
	}
}

/*
 * Install iptun
 * @param cscmObj ClientManager object
 * @return success return true，other return false
 */
function InstallIpTun(cscmObj)
{
	var downloadUrl = GetHostPath("/com/CSAppSupportInstaller.exe");
	return 0 !=  cscmObj.UpdateIPTunnel(downloadUrl, "");
}

/*
 * Uninstall iptun
 * @param cscmObj ClientManagerobject
 * @return success return true，other return false
 */
function UninstallIpTun(cscmObj)
{
	var dir    = cscmObj.IPTunnelUninstDir;
	var param  = "/S _?=" + dir;
	return 0 != cscmObj.UninstallIPTunnel(param);
}

/*
 * Make iptun OK
 * @param cscmObj ClientManager ojbect
 * @return success return true，other return false
 */
function MakeIpTunOK(cscmObj)
{
	//zhk modify for locale
	var requireVer = g_IptunRequireVer;
	var ret = CheckIpTunVersion(cscmObj, requireVer);
	switch(ret)
	{
		case E_INSTALL:
			return InstallIpTun(cscmObj);
		case E_UPDATE:
			UninstallIpTun(cscmObj);
			return InstallIpTun(cscmObj);
		case E_OK:
			return true;
		default:
			return false;
	}
}
/*
 * Start IpTun
 * @param cscmObj ClientManager object
 * @return success return true，other return false
 */
function RunIpTun(cscmObj)
{
	var result = 0;
	var times  = 5;
	// loop to run iptun client
	while(1)
	{
		if( times-- < 0 )
		{
			window.status = " IP服务配置失败,IP服务将不可用,请尝试重新登录 ";
			return false;
		}
		result = cscmObj.InitIPTunnel;
		cscmObj.Sleep(500);
		if(result == 0)
		{
			cscmObj.Sleep(300);
			continue;	
		}
		result = cscmObj.IPTunnelRunningState;
		if(result != 1)
		{
			cscmObj.Sleep(500);
			continue;
		}
		return true;
	}
}
function Config_iprule_str(cscmObj,rcData)
{
	var rcConfig = "";
	var host_to = "";
	var x = "";
	var	host_from = GetRcIpAddr(rcData);
		if(rcData["host_to"] == "")
		{
			host_to = host_from;	
		}
		else
		{
			host_to = rcData["host_to"];
		}
		

		if(rcData["id"] > 0 || rcData["id"] < -1000)
		{
			if(2 == rcData["proto"])
			{
				rcConfig += host_from + " " + host_to;
			}
			else
			{
				rcConfig += host_from + " " + host_to + " " + rcData["port_from"] + " " + rcData["port_to"];
			}
			switch(rcData["proto"])
			{
				case "0":
					rcConfig = rcData["id"] + " tcp " + rcConfig;
					break;
				case "1":
					rcConfig = rcData["id"] + " udp " + rcConfig;
					break;
				case "2":
					rcConfig = rcData["id"] + " icmp " + rcConfig;
					break;
				default:
					return null;
			}
			var ubFlag = " 0";
			var ubInfo = "";
			if(sinforUBData.row.length > 0)
			{
				if((rcData["use_ub"]&1) != 0)
				{
					ubFlag = " 1 ";
					ubInfo = sinforUBData.row[0][rcData["id"]];
					if(ubInfo == null)
					{
						ubInfo = "";
					}
					else
					{
						var temp = ubInfo.split(":");
						ubInfo = temp[1];
					}
				}
			}
			x += rcConfig + ubFlag + ubInfo;
//			result = cscmObj.ConfigIPTunnel(base64encode(rcConfig), 0);
		}
		else if(rcData["id"] < 0)
		{
			var result = cscmObj.ConfigIPTunnel(base64encode( "rc rule " + rcData["id"] + " tcp " + host_from + " " + host_to + " " + rcData["port_from"] + " " + rcData["port_to"]), 0);
			result = cscmObj.ConfigIPTunnel(base64encode( "rc rule " + rcData["id"] + " udp " + host_from + " " + host_to + " " + rcData["port_from"] + " " + rcData["port_to"]), 0);
			result = cscmObj.ConfigIPTunnel(base64encode( "rc rule " + rcData["id"] + " icmp " +  host_from + " " + host_to), 0);
			return null;
		}
		return x;
}
/*
 * config IpTun
 * @param cscmObj ClientManager object
 * @return success return true，other return false
 */
function ConfigIpTun(cscmObj, configOnly)
{
	var result     = 0;
	var userName   = g_loginName;
	var serverPort = g_serverPort;
	var sslctx     = g_sslctx;
	var dns1       = g_iptunDns;
	var dns2       = g_iptunDnsBak;
	var rcData     = SinforIpRcData.row;
	var rcConfig   = "";
	result = cscmObj.CreateIPServiceEvent();
	if( result != 1 && g_defaultRcId>0)
	{
		result = cscmObj.CreateIPServiceEvent();
	}
	if(configOnly)
	{
		result = cscmObj.ConfigIPTunnel("reset", 1);
	}
	// linpeitian change for rule
	result = cscmObj.ConfigIPTunnel("rc errstr  未能连接SINFOR SSL VPN网关,请确保计算机已经正确接入网络或联系管理员.|未能正确打开SINFOR SSL Virtual网卡,暂时不能提供SSL CS服务,请联系管理员.|非常抱歉,CS应用支持出现异常,即将退出,请联系管理员.|与本机IP、代理IP或者SSL网关IP冲突.您将无法访问这些IP资源.|内网IP地址:|没有IP可以分配，请尝试注销后重新启动IP服务或者联系管理员.|系统忙，请尝试注销后重新启动IP服务或者联系管理员.|系统接入用户数已满，请尝试注销后重新启动IP服务或者联系管理员.|您所请求的IP已被他人使用，请尝试注销后重新启动IP服务或者联系管理员.|您所请求的IP为非法IP，请联系管理员.|您所在组id在IP服务侧非法，请联系管理员.|IP服务以路由模式运行且您没有绑定IP，在集群环境下不能使用IP服务，请联系管理员解决该问题.",1);
	result = cscmObj.ConfigIPTunnel("rc server " + document.location.hostname + " " + serverPort, 1);
	result = cscmObj.ConfigIPTunnel("rc username " + userName, 1);
	result = cscmObj.ConfigIPTunnel(base64encode("rc id " + sslctx), 0);
	var host_to = "";
	var tmp_rule_str = "";
	for(var i=0; i<rcData.length; i++)
	{
		var x = "rc rule ";
		//jzw modify for M4.2UserBinding
		if(rcData[i] == null)
		{
			continue;
		}
		//lsc modify 20080901
		tmp_rule_str = Config_iprule_str(cscmObj,rcData[i]);
		if(tmp_rule_str != null)
		{
			x+= tmp_rule_str;
			result = cscmObj.ConfigIPTunnel(base64encode(x), 0);
		}
	}

	if(configOnly)
	{
		result = cscmObj.ConfigIPTunnel("configend2", 1);	
	}
	else
	{
	result = cscmObj.ConfigIPTunnel("cmd startservice", 1);
	}
 	//=========Added by lwq 2008-9-4:ssltray===========>
	if(result == 1 && g_defaultRcId>0)
	{
		//阻塞等待IP服务配置好事件


		result = cscmObj.WaitIPServiceEvent();
		//返回值0,超时或其它原因，不可使用
		//返回值1,IP服务已经准备好，可以使用
		//返回值2,事件打开有问题。


		if( result == 1 )
			g_canUseIpService = true;
		else
			g_canUseIpService = false;
	}
	//=========Added by lwq 2008-9-4:ssltray===========<
	return true;
}



/*
 * STOP IpTun
 * @param cscmObj ClientManager Object
 * @return success return true，other return false
 */
function StopIpTun(cscmObj)
{
	var times  = 5;
	var result = 0;
	while(times--)
	{
		result = cscmObj.IPTunnelRunningState;
		if(result == 1)//iptun is running
		{
			result = cscmObj.ConfigIPTunnel("cmd stopservice", 1);
			cscmObj.Sleep(500);
			continue;
		}
		return true;
	}
	return false;
}

/*
 * Start IP Service
 * @return success return true，otherwise return false
 */
function EnableIpSupport(configOnly)
{
	var ret = MakeCscmOK();
	if(ret == E_RELOGIN)
	{
		return false;
	}
	if( E_INSTALL == ret )
	{
		if(GetCookie("StartIEAsAdminFail") == "1")
		{
			SetCookie("IpEnabled", "0");
		}
		else
		{
			SetCookie("IpEnabled", "1");
		}
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	if(ret == false)
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if( g_CscmObj == null )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	//is can use ip tunnel?
	if( !IsCanUseIpTun(g_CscmObj) )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	if( !MakeVnicOK(g_CscmObj) )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	if( !MakeIpTunOK(g_CscmObj) )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	if( !MakeNspOK(g_CscmObj))
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	
	MakeIEHelperOK(g_CscmObj);
	
	SetCookie("IpEnabled", "1");

	if( !ConfigNsp() )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	if( !RunIpTun(g_CscmObj) )
	{
		SetCookie("IpEnabled", "0");
		return false;	
	}
	//config ip support
	if(!ConfigIpTun(g_CscmObj, configOnly) )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	if( !StartNsp() )
	{
		SetCookie("IpEnabled", "0");
		return false;
	}
	var running = true;
	SetIpSupportState(running);
	/*add by lwq 2008-11-20*/
	if(g_CscmObj != null)
		checkReLogin();
	/*end add by lwq 2008-11-20*/
	return true;
}

function SetIpSupportState(running)
{
	g_IpSupportState = (running ? "1" : "0");
}

function GetIpSupportState()
{
	return g_IpSupportState == "1";
}
/*
 * STOP IP SERVICE
 * @return success return true，otherwise return false
 */
function DisableIpSupport()
{
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if( g_CscmObj == null )
	{
		return false;
	}
	try
	{
		SetCookie("IpEnabled", "0");
		StopIpTun(g_CscmObj);	
		var running = false;
		SetIpSupportState(running);
	}
	catch (e)
	{
	}
}

/////////////////////////////////////////////////////////////////////
//  SSL 
/////////////////////////////////////////////////////////////////////
function CheckSddnVersion(cscmObj, requireVersion)
{
	var ver = cscmObj.SVPNMonitorVersion;
	if(ver == "")
	{
		return E_INSTALL;
	}
	return ver == requireVersion ? E_OK : E_UPDATE;
}

function InstallSddn(cscmObj)
{
	var url = GetHostPath("/com/SVPNMonitorInstaller.exe");
	return 0 != cscmObj.UpdateSVPNMonitor(url,"");
}

function UninstallSddn(cscmObj)
{
	cscmObj.UnistallSVPNMonitor;
}

function StopSddn(cscmObj)
{
	var tryTimes = 10;
	while(cscmObj.SVPNMonitorRunningState == 1)
	{
		if( 0 > tryTimes-- )
		{
			return false;
		}
		ret = cscmObj.ConfigSVPNMonitor("stop");
		if(ret == 0)
		{
			return false;
		}
		cscmObj.Sleep(100);
	}
	return true;
}

function MakeSddnOK(cscmObj)
{
	//stop sddn first
	if( !StopSddn(cscmObj) )
	{
		return false;
	}
	var requireVer = G_DLLS[ID_SDDN][ID_VER];
	var ret = CheckSddnVersion(cscmObj, requireVer);
	switch(ret)
	{
		case E_INSTALL:
			return InstallSddn(cscmObj);
		case E_UPDATE:
			//UninstallSddn(cscmObj);
			return InstallSddn(cscmObj);
		case E_OK:
			return true;
		default: 
			return false;
	}
}

function ConfigSddn(cscmObj)
{
	//run monitor
	var ret = cscmObj.InitSVPNMonitor;
	if(ret != 1)
	{
		return false;
	}
	var waitTimes = 100;
	while(cscmObj.SVPNMonitorRunningState != 1)
	{
		if( 0 > waitTimes-- )
		{
			return false;
		}
		cscmObj.Sleep(50);
	}
	ret = cscmObj.ConfigSVPNMonitor("SDDNStart" + document.location.hostname);

	return true;
}

function KeepSddn()
{
	return;//

	if(g_CscmObj == null)
	{
		return;
	}
	var ret = g_CscmObj.SVPNMonitorRunningState;
	if(ret != 1)
	{
		if(!ConfigSddn(g_CscmObj))
		{
		}
	}
	window.setTimeout("KeepSddn()", 3000);
}
/////////////////////////////////////////////////////////////////////
//  SSO 
/////////////////////////////////////////////////////////////////////
function IsVista()
{
	var agent = navigator.userAgent;
	if(agent.indexOf("Windows NT 6") != -1)
	{
		return true;
	}
	return false;
}

function IsCanUseSso(cscmObj)
{
	var osVer = cscmObj.OsVersion;
	if( osVer < 4)//

	{
		return false;
	}
	if( IsVista() )//vista 
	{
		/*if( !IsAdminAccount(cscmObj) && !IsPowerUserAccount(cscmObj) )
		{
			return false;
		} */
	}
	return true;
}

function MakeSsoHookOK()
{
	var ssoClient = CreateObject(G_DLLS[ID_SSOCLIENT][ID_PROGID]);
	try
	{
		ssoClient.SSOTest();//

	}
	catch(e)
	{
		//
		var ssoProgid   = G_DLLS[ID_SSOCLIENT][ID_PROGID];
		var ssoDllName  = G_DLLS[ID_SSOCLIENT][ID_FILE];
		var ssoCodeBase = G_DLLS[ID_SSOCLIENT][ID_CODEBASE];
		if( E_OK == UpdateActiveXControl(g_CscmObj, ssoProgid, ssoCodeBase, ssoDllName) )
		{
			SetNeedRestart(true);
			return true;
		}
		return false;
	}	
	return true;
}

function MakeSsoOK(cscmObj)
{
	var ssoProgid         = G_DLLS[ID_SSOCLIENT][ID_PROGID];
	var ssoRequireVersion = G_DLLS[ID_SSOCLIENT][ID_VER];
	var ssoDllName        = G_DLLS[ID_SSOCLIENT][ID_FILE];
	var ssoCodeBase       = G_DLLS[ID_SSOCLIENT][ID_CODEBASE];
	var ssoCommonName     = G_DLLS[ID_SSOCLIENT][ID_NAME];
	var ret = MakeActiveXControlOK(cscmObj, ssoProgid, ssoRequireVersion, ssoDllName, ssoCodeBase, true);
	if(ret != E_OK)
	{
		return false;
	}
	/* linyan add for M4.1 控件更新不重启 */
	if(g_IsSsoBhoUpdate)
	{
		g_SsoObj = CreateObject(ssoProgid);
		if(g_SsoObj != null)
		{
			try
			{
				MakeComponentUnblocked(cscmObj, "SSOClientPrj.SSOClientBHO.1");		
				g_SsoBhoObj = null;
				g_SsoBhoObj = CreateObject("SSOClientPrj.SSOClientBHO.1");
				if(g_SsoBhoObj != null)
				{
					g_SsoObj.LoadBHO(document,g_SsoBhoObj);
				}
			}
			catch(e)
			{
				return false;
			}
		}
	}
	/* end linyan add */
	if( !MakeSsoHookOK(cscmObj) )
	{
		return false;
	}
	return true;	
}

function ConfigSso()
{
	//jzw add for M4.2 UserBinding
		if(g_SsoObj == null)
		{
			g_SsoObj = CreateObject(G_DLLS[ID_SSOCLIENT][ID_PROGID]);	
		}
		if(g_SsoObj == null)
		{
			return false;
		}	
		//zhk add for locale
		g_SsoObj.SetLocaleInfo(" 启动应用程序失败,请联系管理检查配置|SSL VPN 单点登录提示|无法连接主机,请联系管理员检查配置|启动应用程序失败,请联系管理员检查配置|Sinfors SSL VPN 单点登录--超时提示|浏览器设置无法使用单点登陆:\n请在 \'工具->Internet选项->高级->启用第三方浏览器扩展\' 前打勾并重新登陆!|单点登录处理程序或网页|超时 ");
		g_SsoObj.Login();
		g_SsoObj.setUserInfo(g_SsoUsername, g_SsoUserpwd);
		g_SsoObj.SetTimeout(g_SsoTimeout * 1000);
		//jzw add for M4.2 UserBinding
		for(key in SinforSSOData.row[0])
		{
			var rc = SinforGetRcById(key);
			if(rc!=null && (IsBS(rc["service"]) || rc["type"] == 0))
			{
				var flag = 0;
				var use_ub = rc["use_ub"];
				if((use_ub&2) != 0)
				{
					flag = 1;
				}
				g_SsoObj.SetBsSSOConfig(SinforSSOData.row[0][key], window.location , rc["type"], flag);
			}
		}
		g_SsoObj.SetBsConfigEnd();
		g_SsoObj.SetHandleInfo();
	//end jzw add
	return true;	
}

function EnableSso()
{
	var ret = MakeCscmOK();
	if( E_INSTALL == ret )
	{
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	if(ret == false)
	{
		return false;
	}
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj == null)
	{
		return false;
	}
	if( !IsCanUseSso(g_CscmObj) )
	{
		return false;
	}
	if( !MakeNspOK(g_CscmObj))
	{
		return false;
	}
	
	MakeIEHelperOK(g_CscmObj);
	
	if( !ConfigNsp() )
	{
		return false;
	}

	if( !StartNsp() )
	{
		return false;
	}
	
	if( !MakeSsoOK(g_CscmObj) )
	{
		return false;
	}
	
	if( !ConfigSso() )
	{
		return false;
	}
	/*add by lwq 2008-11-20*/
	if(g_CscmObj != null)
		checkReLogin();
	/*end add by lwq 2008-11-20*/
	return true;
}

function DisableSso()
{
	try
	{
		if(g_SsoObj != null)
		{
			g_SsoObj.StopSSO();
		}
		else
		{
			var progid = G_DLLS[ID_SSOCLIENT][ID_PROGID];
			var ssoClient = CreateObject(progid);
			ssoClient.StopSSO();
		}
		g_IsSsoEnabled = 0;
	}
	catch(e)
	{}
}

function InitUCPUntilOK(cscmObj)
{
	if(cscmObj.InitUCP != 1)
	{
		return false;
	}
	var wait_times = 0;
	while(cscmObj.UCPRunningState != 1)
	{
		wait_times = wait_times + 1;
		if(wait_times < 100)	// 10 sec timeout
		{
			cscmObj.sleep(100);
		}
		else
		{
			return false;
		}		
	}
	return true;
}

function ConfigUCP(cscmObj)
{
	if(InitUCPUntilOK(cscmObj) != true)
	{
		return false;
	}
	var ret = 1;
	ret &= cscmObj.ConfigUCP("Host:"+document.location.hostname);
	ret &= cscmObj.ConfigUCP("Port:"+g_serverPort);
	ret &= cscmObj.ConfigUCP("UcpPara:"+GetCookie("G_UCP_senv"));
	ret &= cscmObj.ConfigUCP("Test:"+(GetCookie("G_TransMethod") == 1 ? "1":"0"));
	ret &= cscmObj.ConfigUCP("StartUCP");
	if(ret != 1)
	{
		return false;
	}
	HTP_NET();
	return true;
}

function StopUCP(cscmObj)
{
	var tryTimes = 10;
	while(cscmObj.UCPRunningState == 1)
	{
		if( 0 > tryTimes-- )
		{
			return false;
		}
		ret = cscmObj.ConfigUCP("StopUCP");
		if(ret == 0)
		{
			return false;
		}
		cscmObj.Sleep(100);
	}
	return true;
}

function CheckUCPVersion(cscmObj, requireVersion)
{
	var ver = cscmObj.UCPVersion;
	if(ver == "")
	{
		return E_INSTALL;
	}
	return ver == requireVersion ? E_OK : E_UPDATE;
}

function InstallUCP(cscmObj)
{
	var url = GetHostPath("/com/UCPInstaller.exe");
	return 0 != cscmObj.UpdateUCP(url,"");
}

function UninstallUCP(cscmObj)
{
	cscmObj.UnistallUCP;
}


function MakeUCPOK(cscmObj)
{
	if( !StopUCP(cscmObj) )
	{
		return false;
	}
	var requireVer = G_DLLS[ID_UCP][ID_VER];
	var ret = CheckUCPVersion(cscmObj, requireVer);
	switch(ret)
	{
		case E_INSTALL:
			return InstallUCP(cscmObj);
		case E_UPDATE:
			UninstallUCP(cscmObj);
			return InstallUCP(cscmObj);
		case E_OK:
			return true;
		default: 
			return false;
	}
}

function DisableUCP()
{
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj != null)
	{
		try
		{
			StopUCP(g_CscmObj);
		}
		catch (e)
		{
		}
	}
}

function EnableUCP()
{
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if( g_CscmObj == null )
	{
		return false;
	}
	//make proxyIEHookOK
	if(!MakeProxyIEOK(g_CscmObj))
	{
		return false;
	}


	g_ProxyieObj.setSSLHost(document.location.hostname, g_serverPort, g_sslctx); 
	if(MakeUCPOK(g_CscmObj) == false)
	{
			return false;
	}
	else
	{
		return ConfigUCP(g_CscmObj);
	}
}

function EnableSddn()
{
	var ret = MakeCscmOK();
	if( E_INSTALL == ret )
	{
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	if(ret == false)
	{
		return false;
	}
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj == null)
	{
		return false;
	}
	if( !MakeSddnOK(g_CscmObj) )
	{
		return false;
	}
	//

	MakeIEHelperOK(g_CscmObj);

	if( !ConfigSddn(g_CscmObj) )
	{
		return false;
	}
	KeepSddn();
	/*add by lwq 2008-11-20*/
	if(g_CscmObj != null)
		checkReLogin();
	/*end add by lwq 2008-11-20*/
	return true;
}

var g_SDDNStopTimes = 0;
function DisableSddn()
{
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj != null)
	{
		try
		{
			var tmp = g_CscmObj.SVPNMonitorRunningState;
			if(tmp == 1)//running
			{
				tmp = g_CscmObj.ConfigSVPNMonitor("stop");
				g_SDDNStopTimes = g_SDDNStopTimes + 1;
				if (g_SDDNStopTimes > 10) 
				{
					return;
				}
				window.setTimeout("DisableSddn()", 300);
				return;
			}
		}
		catch (e)
		{
		}
	}
}

function IsSddnEnabled()
{
	if(g_CscmObj == null)
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj == null )
	{
		return false;
	}
	return g_CscmObj.SVPNMonitorRunningState == 1;
}

function updateResource()
{
	if(g_IEHelperObj == null)
	{
		g_IEHelperObj = CreateObject(G_DLLS[ID_IEHELPER][ID_PROGID]);
	}
	if(g_IEHelperObj == null)
	{
		//判断cscm是否有安装
		if( CheckCSCM() == E_INSTALL )
		{
			//cscm 没有安装,直接返回
			return false;
		}
		alert("无法加载IEHelper对象，请检查IEHelper是否正确安装");
		return false;
	}

	var r_str = g_IEHelperObj.RcAuthorization;
	var r_str_copy = new String(r_str);
	var r_str_a = r_str_copy.split(";");
				
	var r_str_a_a = new Array(r_str_a.length - 1);
		
	for (var i = 0; i < r_str_a.length - 1; ++i)
	{
		r_str_a_a[i] = r_str_a[i].split(",");
	}
		
	var rcArray = SinforRcData.row;
	for (var i = 0; i < r_str_a_a.length; ++i)
	{
		//alert("id:"+r_str_a_a[i][0]+"\nauth:"+r_str_a_a[i][1]+"\nstra id:"+r_str_a_a[i][2]);
		rcArray[r_str_a_a[i][0]].authorization = r_str_a_a[i][1];
		rcArray[r_str_a_a[i][0]].auth_sp_id = r_str_a_a[i][2];
	}
	return true;
}
function ConfigSecurityCheck(needSetTime)
{
	if(g_IEHelperObj == null)
	{
		g_IEHelperObj = CreateObject(G_DLLS[ID_IEHELPER][ID_PROGID]);
	}
	if(null != g_IEHelperObj)
	{
		g_IEHelperObj.clear();
		
		g_IEHelperObj.SetLocaleInfo("本机不符合管理员指定的安全条件|因为以下安全检查策略不能满足:\n策略名:|\n描述:|\n\n您将只能使用web服务|\n\n点击\"确定\"关闭本IE窗口|\n\n点击\"确定\"用户登出");
		
		obj = SinforRcData.row;
		for (var i =0; i < obj.length; ++i)
		{
			g_IEHelperObj.addResource(obj[i].id, obj[i].type);
		}
		
		obj = SinforSecurityData.role;
		for (var i = 0; i < obj.length; ++i)
		{
			g_IEHelperObj.addRole(obj[i].roledata);
		}

		var obj = SinforSecurityData.stra;
		for (var i = 0; i < obj.length; ++i)
		{
			g_IEHelperObj.addStrategy(obj[i]["configdata"]);
		}
	    
		obj = SinforSecurityData.baserule;
		for (var i = 0; i < obj.length; ++i)
		{
			g_IEHelperObj.addBaseRule(obj[i]["baseruledata"]);
		}
		g_IEHelperObj.setExitURL(GetExitUrl("/por/logout.csp"));
		g_IEHelperObj.setAction(0);
		g_IEHelperObj.checkInterval = g_securityCheckInterval;
		if (needSetTime)
		{
			g_IEHelperObj.setLoginTime();
		}
		g_IEHelperObj.addHostUrl(document.location.protocol+"//"+document.location.host);
		g_IEHelperObj.SetDefaultRcID(g_defaultRcId);
		g_IEHelperObj.check();
		//setTimeout("updateResource()",3000);
		updateResource();
		return true;
	}
	return false;
}

function EnableSecurityCheck(needSetTime)
{
	var ret = MakeCscmOK();
	if( E_INSTALL == ret )
	{
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	if(ret == false)
	{
		return false;
	}
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj == null)
	{
		return false;
	}
	if( !MakeIEHelperOK(g_CscmObj) )
	{
		return false;
	}
	/*add by lwq 2008-11-20*/
	if(g_CscmObj != null)
		checkReLogin();
	/*end add by lwq 2008-11-20*/
	return ConfigSecurityCheck(needSetTime);
}

function DisableSecurityCheck()
{
	if(g_IEHelperObj == null)
	{
		g_IEHelperObj = CreateObject(G_DLLS[ID_IEHELPER][ID_PROGID]);
	}
	if(null != g_IEHelperObj)
	{
		try
		{
			g_IEHelperObj.clear();//	
		}
		catch (e)
		{
		}
	}
}


function CheckProcessv2v3()
{
		//hjk add for resolve bug 4661: the client have choosen to ignore the cscm control install
		//if(GetCookie("ignore_dkey_check") == 1)	
		//{
		//	return 0;
		//}
		//end add by hjk

		//lpt add for resolve bug 6046
		//var ret = MakeCscmOK();
		//if(E_INSTALL == ret)
		//{
		//	return 0;
		//}
		//if( g_CscmObj == null )
		//{
		//	g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
		//}
		//if(g_CscmObj == null)
		//{
		//	alert("Create Client Manager failed!");
		//	return 0;
		//}
		var ret = MakeCscmOK();
		if(ret == false)
		{
			return 0;
		}
		if(g_CscmObj == null )
		{
			g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
		}
		if( g_CscmObj == null )
		{
			return 0;
		}
		checkReLogin();
		var downloadUrl = GetHostPath(G_DLLS[ID_SUPEREXE][ID_CODEBASE]);
	  	ret = g_CscmObj.SetDownloadExeURL(downloadUrl);
		if( !MakeNddkeyV2OK(g_CscmObj) )
		{
//			alert("MakeNddkeyV2 failed.");
 
			return 0;
		}
 
		if( !MakeNddkeyV3OK(g_CscmObj) )
		{
//			alert("MakeNddkeyV3 failed.");
 
			return 0;
		}

		if(g_Nddkeyv2Obj == null)
		{
			g_Nddkeyv2Obj = CreateObject(G_DLLS[ID_NDDKEYV2][ID_PROGID]);
		}	
		if(g_Nddkeyv2Obj == null)
		{
//			alert("NddkeyV2 error!");
 
			return 0;
		} 
 	
		if(g_Nddkeyv3Obj == null)
		{
			g_Nddkeyv3Obj = CreateObject(G_DLLS[ID_NDDKEYV3][ID_PROGID]);
		}	
		if(g_Nddkeyv3Obj == null)
		{
//			alert("NddkeyV3 error!");
 	
			return 0;
		} 
	
		try
		{
			g_Nddkeyv2Obj.OpenDevice(1,"");
		}
		catch(error)
		{
			try
			{
				var hCard = g_Nddkeyv3Obj.OpenDevice(1);
			}
			catch(error)
			{
				return 0;
			}
 			g_Nddkeyv3Obj.CloseDevice(hCard);
 	
 			return 1;
 		
		} 		
		g_Nddkeyv2Obj.CloseDevice(); 
		return 1;			
}
function IsUsingDkey()
{
	if(typeof(g_UseDkeyVer) != "undefined" && ( (g_UseDkeyVer & DKEY_ENABLE_V3) || (g_UseDkeyVer & DKEY_ENABLE_V2) )) 
	{
		var UsingDkey_value = GetCookie("UsingDkey");
		if( UsingDkey_value == "1"|| UsingDkey_value == "2")
		{
			//alert("yes using dkey!");	
			return 1;
		}
	}	
//alert("not using dkey!");	
	return 0;	
	
}

function Usingnddkey()
{ 
	return GetCookie("Usingnddkey");
}


function MakeDkeyHookOK()
{
	var dkeyobj = CreateObject(G_DLLS[ID_EPND][ID_PROGID]);
	try
	{
		dkeyobj.test();

	}
	catch(e)
	{
		try
		{ 
			var dkeyProgid         = G_DLLS[ID_EPND][ID_PROGID];
			var dkeyDllName        = G_DLLS[ID_EPND][ID_FILE];
			var dkeyCodeBase       = G_DLLS[ID_EPND][ID_CODEBASE];
			var ret;
			
			if (IsAdminAccount(g_CscmObj) || IsPowerUserAccount(g_CscmObj))
			{
				ret = dkeyobj.install;
			}	
			else
			{
				ret = InstallActiveXControl(g_CscmObj, dkeyCodeBase, dkeyDllName);
				/* linyan delete for M4.1 控件更新不重启 at 2008-10-23 */
				//SetNeedRestart(true);
			} 
			
			/*if(ESA_SUCCESS_REBOOT == ret)
			{
				SetNeedRestart(true);
			}
			else*/ if(ESA_SUCCESS != ret)
			{
				return false;
			}
			/* end linyan delete */
		}
		catch(e)
		{
			return false;
		}
	}	
	return true;
}

function MakeDkeyOK(cscmObj)
{
	var dkeyProgid         = G_DLLS[ID_EPND][ID_PROGID];
	var dkeyRequireVersion = G_DLLS[ID_EPND][ID_VER];
	var dkeyDllName        = G_DLLS[ID_EPND][ID_FILE];
	var dkeyCodeBase       = G_DLLS[ID_EPND][ID_CODEBASE];
	var dkeyCommonName     = G_DLLS[ID_EPND][ID_NAME];
	var ret = MakeActiveXControlOK(cscmObj, dkeyProgid, dkeyRequireVersion, dkeyDllName, dkeyCodeBase, false);
	if(ret != E_OK)
	{
		return false;
	}
	var dkeyv2Progid = "FT_ND_SC.ePsM8SC.1";
	MakeComponentUnblocked(cscmObj, dkeyv2Progid);
	if( !MakeDkeyHookOK(cscmObj) )
	{
		return false;
	}
 
	return true;	
}


function MakeNddkeyV3OK(cscmObj)
{
	var dkeyProgid         = G_DLLS[ID_NDDKEYV3][ID_PROGID];
	var dkeyRequireVersion = G_DLLS[ID_NDDKEYV3][ID_VER];
	var dkeyDllName        = G_DLLS[ID_NDDKEYV3][ID_FILE];
	var dkeyCodeBase       = G_DLLS[ID_NDDKEYV3][ID_CODEBASE];
	var dkeyCommonName     = G_DLLS[ID_NDDKEYV3][ID_NAME];
	
	if(IsVista())
	{
		return true;
	}
	
	var ret = MakeActiveXControlOK(cscmObj, dkeyProgid, dkeyRequireVersion, dkeyDllName, dkeyCodeBase, false);
	if(ret != E_OK)
	{
		return false;
	}
	var dkeyv3Progid = "HTHMAC.HTHMACCtrl.1";
	MakeComponentUnblocked(cscmObj, dkeyv3Progid);
	return true;	
}
 


function MakeNddkeyV2OK(cscmObj)
{
	var dkeyProgid         = G_DLLS[ID_NDDKEYV2][ID_PROGID];
	var dkeyRequireVersion = G_DLLS[ID_NDDKEYV2][ID_VER];
	var dkeyDllName        = G_DLLS[ID_NDDKEYV2][ID_FILE];
	var dkeyCodeBase       = G_DLLS[ID_NDDKEYV2][ID_CODEBASE];
	var dkeyCommonName     = G_DLLS[ID_NDDKEYV2][ID_NAME];
	var ret = MakeActiveXControlOK(cscmObj, dkeyProgid, dkeyRequireVersion, dkeyDllName, dkeyCodeBase, false);
	if(ret != E_OK)
	{
		return false;
	}
 
	
	var dkeyv2Progid = "FT_ND_SC.ePsM8SC.1";
	MakeComponentUnblocked(cscmObj, dkeyv2Progid);
	return true;	
}
function DkeyV2Autocheck()
{
	if (g_DKeyObj == null)
	{
		window.setInterval("DkeyV2Autocheck()", 0);
		return;
	}
	if(g_DKeyObj.isFKeyRemoved())
	{
		SinforDisableTray();
		g_NoPrompt = true;
		window.location = "../por/logout.csp?toClose=1";
		return;
	}
}

function DkeyV2AutocheckV2()
{
	try
	{
		var ret = g_EPassObj.GetStrProperty(0x07, 0, 0);
		if("0000000000000000" == ret)
		{
			SinforDisableTray();
			g_NoPrompt = true;
			window.location = "../por/logout.csp?toClose=1";
		}
	}
	catch(e)
	{
		SinforDisableTray();
		g_NoPrompt = true;
		window.location = "../por/logout.csp?toClose=1";
	}
}

function DkeyV3Autocheck()
{
	if (g_DKeyObj == null)
	{
		window.setInterval("DkeyV3Autocheck()", 0);
		return;
	}
	if(g_DKeyObj.isHkeyRemoved())
	{
		SinforDisableTray();
		g_NoPrompt = true;
		window.location = "../por/logout.csp?toClose=1";
		return;
	}	
}

function ConfigDkey()
{
	if(g_UseDkeyVer & DKEY_ENABLE_V2)
	{
		try
		{
			g_EPassObj = CreateObject("FT_ND_SC.ePsM8SC.1");
			if(g_EPassObj != null)
			{
				g_EPassObj.OpenDevice(1, "");

				window.setInterval("DkeyV2AutocheckV2()", 2000);
				return true;
			}
		}
		catch(e)
		{
		}		
	}
	if(!IsVista() && (g_UseDkeyVer & DKEY_ENABLE_V3))
	{
		try
		{
			if(null == g_DKeyObj)
			{
				g_DKeyObj = CreateObject(G_DLLS[ID_EPND][ID_PROGID]);
			}
			if(null != g_DKeyObj)
			{
				if(GetCookie("DKeyV3Enabled") == "1")
				{
					window.setInterval("DkeyV3Autocheck()", 2000);
					return true;
				}
				else
				{
					if(g_DKeyObj.registerHKHandler())
					{
						SetCookie("DKeyV3Enabled", "1");
						window.setInterval("DkeyV3Autocheck()", 2000);
						return true;
					}
				}
			}
		}
		catch(e)
		{ //do nothing	
		}
	}
	//SinforDisableTray();
	g_NoPrompt = true;
	window.location = "../por/logout.csp?toClose=1";
	return false;
}

function EnableDkey()
{
	var ret = MakeCscmOK();
	if( E_INSTALL == ret )
	{
		/*add by lwq 2008-11-20*/
		if(g_CscmObj != null)
			checkReLogin();
		/*end add by lwq 2008-11-20*/
		return true;
	}
	if(ret == false)
	{
		return false;
	}
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if(g_CscmObj == null)
	{
		return false;
	}
	if( !MakeDkeyOK(g_CscmObj) )
	{
		return false;
	}
	/*add by lwq 2008-11-20*/
	if(g_CscmObj != null)
		checkReLogin();
	/*end add by lwq 2008-11-20*/
	return true;
}


function CheckDkeyDrv(dkeyVerEnable)
{
	if(DKEY_DISABLE == dkeyVerEnable)
	{
		return DKEY_HAVE_NONE;
	}
	var haveWhich = DKEY_HAVE_NONE;
	var ver    = G_DLLS[ID_EPND][ID_VER];
	var progid = G_DLLS[ID_EPND][ID_PROGID];
	if(null == g_DKeyObj)	
	{
		g_DKeyObj = CreateObject(progid);
	}
	if(null == g_DKeyObj)
	{
		return DKEY_HAVE_UNKNOWN;
	}
	if(DKEY_ENABLE_V2 == dkeyVerEnable || DKEY_ENABLE_BOTH == dkeyVerEnable)
	{
		try
		{
			if(ESA_DEST_DVERSION_EQUAL == g_DKeyObj.haveinstall)
			{
				haveWhich |= DKEY_HAVE_V2;
			}
		}
		catch(e)
		{ //do nothing
		}
	}
	if(DKEY_ENABLE_V3 == dkeyVerEnable || DKEY_ENABLE_BOTH == dkeyVerEnable)
	{
		try
		{
			if(ESA_DEST_DVERSION_EQUAL == g_DKeyObj.detectHKDrv())
			{
				haveWhich |= DKEY_HAVE_V3;
			}
		}
		catch(e)
		{ //do nothing
		}
	}

	return haveWhich;
}
function MakeServiceOK(cscmObj)
{
	var ret = CheckService(cscmObj);
	switch(ret)
	{
	case E_INSTALL:
		var installResult = InstallService(cscmObj);
		if(installResult == E_NO_RIGHT)
		{
			return false;
		}
		return installResult;
	case E_UPDATE:
		var updateResult = UpdateService(cscmObj);
		return updateResult;
	case E_OK:
		break;
	default:
		return false;
	}
	return true;
}

function CheckService(cscmObj)
{
	if(!g_CscmObj.IsServiceOK)
	{
		return E_INSTALL;
	}
	
	var requireVer = G_DLLS[ID_SUPERSERVICE][ID_VER];
	return CheckServiceVersion(cscmObj,requireVer);
}

function CheckServiceVersion(cscmObj, requireVersion)
{
	var ver = cscmObj.SuperServiceVersion;
	return ver == requireVersion ? E_OK : E_UPDATE;
}

function InstallService(cscmObj)
{
	if(!IsAdminAccount(cscmObj))
	{
		return E_NO_RIGHT;
	}
	
	var downloadUrl = GetHostPath("/com/SuperServiceInstaller.exe");
	return 0 !=  cscmObj.UpdateSuperServiceByNotCon(downloadUrl, "");
}

function UpdateService(cscmObj)
{
	
	var downloadUrl = GetHostPath("/com/SuperServiceInstaller.exe");
	
	var ret =  cscmObj.UpdateSuperServiceByVersion(downloadUrl, "");
	var r = cscmObj.StopSuperExe;
	return ret;
}

function MakeSuperExeOKWhenUpdateCscm(cscmObj)
{
	var ret = CheckSuperExe(cscmObj);
	switch(ret)
	{
		case E_INSTALL:
			InstallSuperExe(cscmObj);
			break ;
		case E_UPDATE:
			UpdateSuperExe(cscmObj);
			break ;
		default:
			break ;
	}
}

function MakeSuperExeOK(cscmObj)
{
	var ret = CheckSuperExe(cscmObj);
	switch(ret)
	{
	case E_INSTALL:
		if(g_CscmObj != null)
		{
			checkReLogin();
		}
		var installResult = InstallSuperExe(cscmObj);
		return installResult;
	case E_UPDATE:
		if(g_CscmObj != null)
		{
			checkReLogin();
		}
		var updateResult = UpdateSuperExe(cscmObj);
		return updateResult;
	case E_OK:
		break;
	default:
		return false;
	}
	return true;
}

function CheckSuperExe(cscmObj)
{
	var requireVer = G_DLLS[ID_SUPEREXE][ID_VER];
	return CheckSuperExeVersion(cscmObj,requireVer);
}

function CheckSuperExeVersion(cscmObj, requireVersion)
{
	var ver = cscmObj.SuperExeVersion;
	return ver == requireVersion ? E_OK : E_UPDATE;
}

function InstallSuperExe(cscmObj)
{
	var downloadUrl = GetHostPath("/com/SuperExeInstaller.exe");
	return 0 !=  cscmObj.UpdateSuperExe(downloadUrl, "");
}

function UpdateSuperExe(cscmObj)
{
	var downloadUrl = GetHostPath("/com/SuperExeInstaller.exe");
	return 0 !=  cscmObj.UpdateSuperExe(downloadUrl, "");
}

function DisableSuperExe()
{ 
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	} 
	
	if(g_CscmObj != null)
	{
		g_CscmObj.StopSuperExe;
	}
}
function ClientSSLUsrLogout()
{
	if( g_CscmObj != null )
	{
		try
		{
			g_CscmObj.Logout;
		}
		catch (e)
		{
		}
	}
}
function IsAppComponentOK()
{
	var progid         = G_DLLS[ID_PROXYIE][ID_PROGID];
	var requireVersion = G_DLLS[ID_PROXYIE][ID_VER];
	g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	//安装不成功,不能自动启用APP服务
	if(g_CscmObj == null)
	{
		return false;
	}
	ret = CheckActiveXControlVersion(g_CscmObj, progid, requireVersion);
	//客户端的APP组件已经正确安装过,可以自动启用
	if(ret == E_OK )
	{
		return true;
	}
	return false;
}

function IsIpCompoentOK()
{
	g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	//安装不成功,不能自动启用APP服务
	if(g_CscmObj == null)
	{
		return false;
	}
	var ret = CheckIpTunVersion(g_CscmObj, g_IptunRequireVer);
	var rt = CheckVnicVersion(g_CscmObj, g_VnicRequireVer)
	if(ret == E_OK && rt == E_OK)
	{
		return true;
	}
	return false;
}

//add by xiongtao 2008-08-19 检测并显示加速协议30秒自动取消	
function HTP_NET()
{
	if( g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if( g_CscmObj == null )
	{
		return false;
	}
	
	var UCPState = g_CscmObj.UCPAccState;
    var fRetry = null;
    if(UCPState == 1)
    {
			document.getElementById("htp_net").style.display="inline";
    }
    else
    {		
	    document.getElementById("htp_net").style.display="none";	
		//IE5.o has script error		
	    fRetry = window.setTimeout("HTP_NET()",10000);
		window.setTimeout("window.clearTimeout("+fRetry+")",30000);
    }	
}
//make IEhelper,Nsp,Proxy,SSO OK.
function MakeComponentsOK(cscmObj,appRcCount,ipRcCount)
{
	if(cscmObj != null)
	{
		if(ipRcCount > 0 || appRcCount > 0)
		{
			MakeIEHelperOK(cscmObj);
			MakeNspOK(cscmObj);
			MakeSsoOK(cscmObj);
		}
		//zhk add  for hzx advices
		if(ipRcCount > 0)
		{
			MakeVnicOK(cscmObj);
			MakeIpTunOK(cscmObj);
		}
		if(appRcCount > 0)
		{
			MakeProxyIEOK(cscmObj);
		}
	}
	return;
}
//make pc user is Relogin
function checkReLogin()
{
	if(GetCookie("haveLogin") == "1")//用户已经登录
	{
			var isLogin = g_CscmObj.reloginEx;
			if(isLogin == 0)//页面已经设置了用户登录标志，但没有调用控件接口设置登录标志

			{
				var loginMode = GetCookie("LoginMode");
				var isReLogin = g_CscmObj.ReLogin(document.location.protocol+"//"+document.location.host, document, parseInt(loginMode), g_enableAutoRelogin);
			}
			else//页面和调用控件接口都设置了用户登录标志，那么就没有必要调用了
			{
			}
	}
	else//页面没有得到用户登录的cookie
	{
		var isLogin = g_CscmObj.reloginEx;
		if(isLogin == 0)//没有调用控件接口设置登录标志，说明本机没有登录vpn
		{
		}
		else//其他调用控件接口设置登录标志，说明本机已经登录vpn
		{
			post_http("/por/logout.csp?twfid="+GetCookie("TWFID"),null);
			window.location = "/com/warning.html";
			return true;
		}
	}
	return false;
}


function SinforDisableTray()
{//像刷新服务页面这样的情况，会导致没有重新加载后的页面根本没有g_IEHelperObj对象
	var tmp_IEHelperObj = null;
	if(g_IEHelperObj == null)
	{
			try
			{
				tmp_IEHelperObj = CreateObject(G_DLLS[ID_IEHELPER][ID_PROGID]);	
			}
			catch(e)
			{}
	}
	else
	{
		tmp_IEHelperObj = 	g_IEHelperObj;
	}
	if(tmp_IEHelperObj!=null)
	{
		try
		{
			tmp_IEHelperObj.DisableTimeQuery();
			tmp_IEHelperObj.DisableSSLTray();
		}
		catch(e)
		{}
	}
}

//panwc added for AutoLogin 09.05.22 -->
function MakeSinforUIOK()
{
	var ret = CheckSinforUIVersion(g_CscmObj, G_DLLS[ID_SINFORUI][ID_VER]);
	switch(ret)
	{
		case E_INSTALL:
			return InstallSinforUI(g_CscmObj);
		case E_UPDATE:
			UninstallSinforUI(g_CscmObj);
			return InstallSinforUI(g_CscmObj);
		case E_OK:
			return true;
		default:
			return false;
	}
}

function CheckSinforUIVersion(cscmObj, requireVer)
{
	var ver = cscmObj.SinforUIVersion;
	if(ver == "")
	{
		return E_INSTALL;	
	}
	if( cscmObj.CompareVersion(ver, requireVer) != 0 )
	{
		return E_UPDATE;
	}
	else
	{
		return E_OK;
	}
}

function UninstallSinforUI(cscmObj)
{
	return 0 != cscmObj.UnistallSinforUI;
}

function InstallSinforUI(cscmObj)
{
	var downloadUrl = GetHostPath("/com/SinforUIInstaller.exe ");
	return 0 !=  cscmObj.UpdateSinforUI(downloadUrl, "");
}
 

function EnableSSLTraySupport()
{
	var ret = MakeCscmOK();
	if(ret == false)
	{
		return false;
	}
	if(g_CscmObj == null )
	{
		g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
	}
	if( g_CscmObj == null )
	{
		return false;
	}
 
	if(checkReLogin())
	{
		return false;
	}
	
	MakeIEHelperOK(g_CscmObj);

	if( !MakeSinforUIOK(g_CscmObj) )
	{
		return false;
	} 
	
	try
	{
 
		var  enbleWebOpt = 0;
	  if(g_wopt_support_device&2)
	   {
	   	 	 enbleWebOpt = 1;
	   }
		g_CscmObj.EnableSSLTray(enbleWebOpt);
	}
	catch(e)
	{ 
		return false;
	} 
	
	return true;
}
 
//panwc added end 09.05.22 <--

//panwc added 09.07.13 -->
function MakeHardIDOK(cscmObj)
{
	var Name     = G_DLLS[ID_HARDID][ID_NAME];
	var Progid   = G_DLLS[ID_HARDID][ID_PROGID];
	var Version  = G_DLLS[ID_HARDID][ID_VER];
	var DllName  = G_DLLS[ID_HARDID][ID_FILE];
	var CodeBase = G_DLLS[ID_HARDID][ID_CODEBASE];
	var ret = 0;

	ret = MakeActiveXControlOK(cscmObj, Progid, Version, DllName, CodeBase, false);
	
	if(ret != E_OK)
	{
		return false;
	}
	return true;
}
//panwc added end <--

//ccm add for clear ssl state
function ClearSslState()
{
		var ret = MakeCscmOK();
		if(E_INSTALL == ret)
		{
			return;
		}
		
		if( g_CscmObj == null )
		{
			g_CscmObj = CreateObject(G_DLLS[ID_CSCM][ID_PROGID]);
		}
		if(g_CscmObj == null)
		{
			alert("Create Client Manager failed!");
			return false;
		}
		
		if( !MakeIEHelperOK(g_CscmObj) )
		{
			alert("MakeIEHelperOK failed.");
			return false;
		}
		if(g_IEHelperObj == null)
		{
			g_IEHelperObj = CreateObject(G_DLLS[ID_IEHELPER][ID_PROGID]);

		}	
		if(g_IEHelperObj == null)
		{
			alert("IEHelper error!");
			return false;
		} 
		g_IEHelperObj.ClearSslState();
 
		return true;
	
}   
//ccm add end

