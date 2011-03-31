
/**
 *本日历选择控件由tiannet根据前人经验完善而得。很多代码来自meizz的日历控件。
 *tiannet添加了时间选择功能、select,object标签隐藏功能，还有其它小功能。
 *使用方法：
 *	(1)只选择日期				<input type="text" name="date"		readOnly onClick="setDay(this);">
 *	(2)选择日期和小时			<input type="text" name="dateh"		readOnly onClick="setDayH(this);">
 *	(3)选择日期和小时及分钟		<input type="text" name="datehm"	readOnly onClick="setDayHM(this);">
 *	(4)选择日期和小时及分钟和秒	<input type="text" name="datehms"	readOnly onClick="setDayHMS(this);">
 *设置参数的方法
 *	(1)设置日期分隔符				setDateSplit(strSplit);默认为"-"
 *	(2)设置日期与时间之间的分隔符		setDateTimeSplit(strSplit);默认为" "
 *	(3)设置时间分隔符				setTimeSplit(strSplit);默认为":"
 *	(4)设置(1),(2),(3)中的分隔符		setSplit(strDateSplit,strDateTimeSplit,strTimeSplit);
 *	(5)设置开始和结束年份				setYearPeriod(intDateBeg,intDateEnd)
 *说明：
 *	默认返回的日期时间格式如同：2005-02-02 08:08:08
 * 修改说明：
   20070227由tiannet添加秒的选择功能
 */

//------------------ 样式定义 ---------------------------//
//功能按钮样式
var s_tiannet_turn_base = "height:16px;font-size:9pt;color:white;border:0 solid #CCCCCC;cursor:hand;background-color:#2650A6;";
//翻年、月等的按钮
var s_tiannet_turn = "width:28px;" + s_tiannet_turn_base;
//关闭、清空等按钮样式
var s_tiannet_turn2 = "width:22px;" + s_tiannet_turn_base;
//年选择下拉框
var s_tiannet_select = "width:64px;display:none;";
//月、时、分选择下拉框
var s_tiannet_select2 = "width:46px;display:none;";
//日期选择控件体的样式
var s_tiannet_body = "width:150;background-color:#2650A6;display:none;z-index:9998;position:absolute;" +
		"border-left:1 solid #CCCCCC;border-top:1 solid #CCCCCC;border-right:1 solid #999999;border-bottom:1 solid #999999;";
//显示日的td的样式
var s_tiannet_day = "width:21px;height:20px;background-color:#D8F0FC;font-size:10pt;color:black;font-family:宋体";
//字体样式
var s_tiannet_font = "color:#FFCC00;font-size:9pt;cursor:hand;";
//链接的样式
var s_tiannet_link = "text-decoration:none;font-size:9pt;color:#2650A6;cursor:hand;";
//横线
var s_tiannet_line = "border-bottom:1 solid #6699CC";

//------------------ 变量定义 ---------------------------//

//默认开始年和结束年
var tiannetDefaultYearSt = 1960; //(20060412-tiannet)
var tiannetDefaultYearEnd = 2020; //(20060412-tiannet)

var tiannetIsAutoExpandYearPeriod = true;//当开始年份小于设定的最小年份或者结束年份大于设定的最大年份时，是否允许自动扩展年份选项。(20060412-tiannet)
var tiannetYearSt = tiannetDefaultYearSt;//可选择的开始年份
var tiannetYearEnd = tiannetDefaultYearEnd;//可选择的结束年份
var tiannetDateNow = new Date();
var tiannetYear = tiannetDateNow.getFullYear(); //定义年的变量的初始值
var tiannetMonth = tiannetDateNow.getMonth()+1; //定义月的变量的初始值
var tiannetDay = tiannetDateNow.getDate();
var tiannetHour = 8;//tiannetDateNow.getHours();
var tiannetMinute = 0;//tiannetDateNow.getMinutes();
var tiannetSecond = 0;//秒
var tiannetArrDay=new Array(42);          //定义写日期的数组
var tiannetDateSplit = "-";					//日期的分隔符号
var tiannetDateTimeSplit = " ";				//日期与时间之间的分隔符
var tiannetTimeSplit = ":";					//时间的分隔符号
var tiannetOutObject;						//接收日期时间的对象
var arrTiannetHide = new Array();//被强制隐藏的标签

var m_bolShowHour = false;//是否显示小时
var m_bolShowMinute = false;//是否显示分钟
var m_bolShowSecond = false;//是否显示秒


var m_aMonHead = new Array(12);    		   //定义阳历中每个月的最大天数
    m_aMonHead[0] = 31; m_aMonHead[1] = 28; m_aMonHead[2] = 31; m_aMonHead[3] = 30; m_aMonHead[4]  = 31; m_aMonHead[5]  = 30;
    m_aMonHead[6] = 31; m_aMonHead[7] = 31; m_aMonHead[8] = 30; m_aMonHead[9] = 31; m_aMonHead[10] = 30; m_aMonHead[11] = 31;

// ---------------------- 用户可调用的函数 -----------------------------//

//用户主调函数－只选择日期
function setDay(obj){
	tiannetOutObject = obj;
	//如果标签中有值，则将日期初始化为当前值
	var strValue = tiannetTrim(tiannetOutObject.value);
	if( tiannetIsValidateDT(strValue) ){
		tiannetInitDate(strValue);
	} else {
		tiannetInitDateNow();
	}
	m_bolShowHour = false;
	m_bolShowMinute = false;
	tiannetPopCalendar();
}
//用户主调函数－选择日期和小时
function setDayH(obj){
	tiannetOutObject = obj;
	m_bolShowHour = true;
	//如果标签中有值，则将日期和小时初始化为当前值
	var strValue = tiannetTrim(tiannetOutObject.value);
	if( tiannetIsValidateDT(strValue) ){
		tiannetInitDate(strValue.substring(0,10));
		tiannetHour = strValue.substring(11,13);
		if( tiannetHour < 10 ) tiannetHour = tiannetHour.substring(1,2);
		//alert(tiannetHour);
	} else {
		tiannetInitDateNow();
	}
	m_bolShowMinute = false;
	tiannetPopCalendar();
}
//用户主调函数－选择日期和小时及分钟
function setDayHM(obj){
	tiannetOutObject = obj;
	m_bolShowHour = true;
	m_bolShowMinute = true;
	//如果标签中有值，则将日期和小时及分钟初始化为当前值
	var strValue = tiannetTrim(tiannetOutObject.value);
	if( tiannetIsValidateDT(strValue) ){
		tiannetInitDate(strValue.substring(0,10));
		var time = strValue.substring(11,16);
		var arr = time.split(tiannetTimeSplit);
		tiannetHour = arr[0];
		tiannetMinute = arr[1];
		if( tiannetHour < 10 ) tiannetHour = tiannetHour.substring(1,2);
		if( tiannetMinute < 10 ) tiannetMinute = tiannetMinute.substring(1,2);
	} else {
		tiannetInitDateNow();
	}
	tiannetPopCalendar();
}
//用户主调函数－选择日期和小时及分钟和秒
function setDayHMS(obj){
	tiannetOutObject = obj;
	m_bolShowHour = true;
	m_bolShowMinute = true;
	m_bolShowSecond = true;
	//如果标签中有值，则将日期和小时及分钟初始化为当前值
	var strValue = tiannetTrim(tiannetOutObject.value);
	if( tiannetIsValidateDT(strValue) ){
		tiannetInitDate(strValue.substring(0,10));
		var time = strValue.substring(11,19);
		var arr = time.split(tiannetTimeSplit);
		tiannetHour = arr[0];
		tiannetMinute = arr[1];
		tiannetSecond = arr[2];
		if( tiannetHour < 10 ) tiannetHour = tiannetHour.substring(1,2);
		if( tiannetMinute < 10 ) tiannetMinute = tiannetMinute.substring(1,2);
		if( tiannetSecond < 10 ) tiannetSecond = tiannetSecond.substring(1,2);
	} else {
		tiannetInitDateNow();
	}
	tiannetPopCalendar();
}
//设置开始日期和结束日期
function  setYearPeriod(intDateBeg,intDateEnd){
	tiannetYearSt = intDateBeg;
	tiannetYearEnd = intDateEnd;
	//考虑开始结束年份的大小颠倒
	var sec = tiannetYearSt;
	if( sec > tiannetYearEnd ) {
		sec = tiannetYearEnd;
		tiannetYearEnd = tiannetYearSt;
		tiannetYearSt = sec;
	}
}
//设置日期分隔符。默认为"-"。只能设置为"-"或"/"
function setDateSplit(strDateSplit){
	if( strDateSplit != "-"  && strDateSplit != "/") {
		alert("日期分隔符只能为-或/");
		return;
	}
	tiannetDateSplit = strDateSplit;
}
//设置日期与时间之间的分隔符。默认为" "
function setDateTimeSplit(strDateTimeSplit){
	tiannetDateTimeSplit = strDateTimeSplit;
}
//设置时间分隔符。默认为":"
function setTimeSplit(strTimeSplit){
	tiannetTimeSplit = strTimeSplit;
}
//设置分隔符
function setSplit(strDateSplit,strDateTimeSplit,strTimeSplit){
	tiannetDateSplit(strDateSplit);
	tiannetDateTimeSplit(strDateTimeSplit);
	tiannetTimeSplit(strTimeSplit);
}
//设置默认的日期。格式为：YYYY-MM-DD
function setDefaultDate(strDate){
	tiannetYear = strDate.substring(0,4);
	tiannetMonth = strDate.substring(5,7);
	tiannetDay = strDate.substring(8,10);
}
//设置默认的时间。格式为：HH24:MI
function setDefaultTime(strTime){
	tiannetHour = strTime.substring(0,2);
	tiannetMinute = strTime.substring(3,5);
}

//设置是否允许自动扩展年份下拉框选项
function setAutoExpandYearPeriod(isExpand) {
	tiannetIsAutoExpandYearPeriod = isExpand;
}

// ---------------------- end 用户可调用的函数 -----------------------------//

//------------------ begin 页面显示部分 ---------------------------//
var weekName = new Array("日","一","二","三","四","五","六");
document.write('<div id="divTiannetDate" style="'+s_tiannet_body+'" style="本日历选择控件由tiannet根据前人经验完善而成！">');

document.write('<div align="center" id="divTiannetDateText" Author="tiannet" style="padding-top:2px;">');
document.write('<span id="tiannetYearHead" Author="tiannet" style="'+s_tiannet_font+'" '+
				'onclick="spanYearCEvent();">&nbsp;年</span>');
document.write('<select id="selTianYear" style="'+s_tiannet_select+'" Author="tiannet"  '+
				' onChange="selYearChgEvent(this.value);">');
for(var i=tiannetYearSt;i <= tiannetYearEnd;i ++){
	document.writeln('<option value="' + i + '">' + i + '年</option>');
}
document.write('</select>');
document.write('<span id="tiannetMonthHead" Author="tiannet" style="'+s_tiannet_font+'" '+
				'onclick="spanMonthCEvent();">&nbsp;&nbsp;月</span>');
document.write('<select id="selTianMonth" style="'+s_tiannet_select2+'" Author="tiannet" '+
				'onChange="selMonthChgEvent(this.value);">');
for(var i=1;i <= 12;i ++){
	document.writeln('<option value="' + i + '">' + i + '月</option>');
}
document.write('</select>');
//document.write('</div>');

//document.write('<div align="center" id="divTiannetTimeText" Author="tiannet">');
//----------------- 小时的选择 --------------------//
document.write('<span id="tiannetHourHead" Author="tiannet" style="'+s_tiannet_font+'display:none;" '+
				'onclick="spanHourCEvent();">&nbsp;时</span>');
document.write('<select id="selTianHour" style="'+s_tiannet_select2+'display:none;" Author="tiannet" '+
				' onChange="selHourChgEvent(this.value);">');
for(var i=0;i <= 23;i ++){
	document.writeln('<option value="' + i + '">' + i + '时</option>');
}
document.write('</select>');
//----------------- 分的选择 --------------------//
document.write('<span id="tiannetMinuteHead" Author="tiannet" style="'+s_tiannet_font+'display:none;" '+
				'onclick="spanMinuteCEvent();">&nbsp;&nbsp;分</span>');
document.write('<select id="selTianMinute" style="'+s_tiannet_select2+'display:none;" Author="tiannet" '+
				'  onChange="selMinuteChgEvent(this.value);">');
for(var i=0;i <= 59;i ++){
	document.writeln('<option value="' + i + '">' + i + '分</option>');
}

document.write('</select>');
//----------------- 秒的选择 --------------------//
document.write('<span id="tiannetSecondHead" Author="tiannet" style="'+s_tiannet_font+'display:none;" '+
				'onclick="spanSecondCEvent();">&nbsp;&nbsp;秒</span>');
document.write('<select id="selTianSecond" style="'+s_tiannet_select2+'display:none;" Author="tiannet" '+
				'  onChange="selSecondChgEvent(this.value);">');
for(var i=0;i <= 59;i ++){
	document.writeln('<option value="' + i + '">' + i + '秒</option>');
}

document.write('</select>');

document.write('</div>');

//输出一条横线
document.write('<div style="'+s_tiannet_line+'"></div>');

document.write('<div align="center" id="divTiannetTurn" style="border:0;" Author="tiannet">');
document.write('<span style="'+s_tiannet_turn+'"  title="上一年" Author="tiannet" onClick="tiannetPrevYear();">年↑</span>');
document.write('<span style="'+s_tiannet_turn+'"  title="下一年" Author="tiannet" onClick="tiannetNextYear();">年↓</span>&nbsp;');
document.write('<span style="'+s_tiannet_turn+'"  title="上一月" Author="tiannet" onClick="tiannetPrevMonth();">月↑</span>');
document.write('<span style="'+s_tiannet_turn+'"  title="下一月" Author="tiannet" onClick="tiannetNextMonth();">月↓</span>');
document.write('</div>');
//输出一条横线
document.write('<div style="'+s_tiannet_line+'"></div>');

document.write('<table border=0 cellspacing=0 cellpadding=0  bgcolor=white onselectstart="return false">');
document.write('	<tr style="background-color:#2650A6;font-size:10pt;color:white;height:22px;" Author="tiannet">');
for(var i =0;i < weekName.length;i ++){
	//输出星期
	document.write('<td width="21" align="center" Author="tiannet">' + weekName[i] + '</td>');
}
document.write('	</tr>');
document.write('</table>');
//输出天的选择
document.write('<table border=0 cellspacing=1 cellpadding=0  bgcolor=white onselectstart="return false">');
var n = 0;
for (var i=0;i<5;i++) { 
	document.write (' <tr align=center id="trTiannetDay' + i + '" >');
	for (var j=0;j<7;j++){
		document.write('<td align="center" id="tdTiannetDay' + n + '" '+
				'onClick="tiannetDay=this.innerText;tiannetSetValue(true);" ' 
			+' style="' + s_tiannet_day + '">&nbsp;</td>');
		n ++;
	}
	document.write (' </tr>');
}
document.write (' <tr align=center id="trTiannetDay5" >');
document.write('<td align="center" id="tdTiannetDay35" onClick="tiannetDay=this.innerText;tiannetSetValue(true);" ' 
				+' style="' + s_tiannet_day + '">&nbsp;</td>');
document.write('<td align="center" id="tdTiannetDay36" onClick="tiannetDay=this.innerText;tiannetSetValue(true);" ' 
				+' style="' + s_tiannet_day + '">&nbsp;</td>');
document.write('<td align="right" colspan="5"><span onclick="javascript:tiannetClear();" style="' + s_tiannet_link + '">清空</span>'+
				'&nbsp;<span onclick="javascript:tiannetHideControl();" style="' + s_tiannet_link + '">关闭</span>' +
				'&nbsp;<span onclick="javascript:tiannetSetValue(true);" style="' + s_tiannet_link + '">确定</span>&nbsp;' +
				'</td>');
document.write (' </tr>');
document.write('</table>');
document.write('</div>');
//------------------ end 页面显示部分 ---------------------------//

//------------------ 显示日期时间的span标签响应事件 ---------------------------//
//单击年份span标签响应
function spanYearCEvent(){
	hideElementsById(new Array("selTianYear","tiannetMonthHead"),false);
	if(m_bolShowHour)	hideElementsById(new Array("tiannetHourHead"),false);
	if(m_bolShowMinute)	hideElementsById(new Array("tiannetMinuteHead"),false);
	hideElementsById(new Array("tiannetYearHead","selTianMonth","selTianHour","selTianMinute"),true);
}
//单击月份span标签响应
function spanMonthCEvent(){
	hideElementsById(new Array("selTianMonth","tiannetYearHead"),false);
	if(m_bolShowHour)	hideElementsById(new Array("tiannetHourHead"),false);
	if(m_bolShowMinute)	hideElementsById(new Array("tiannetMinuteHead"),false);
	hideElementsById(new Array("tiannetMonthHead","selTianYear","selTianHour","selTianMinute"),true);
}
//单击小时span标签响应
function spanHourCEvent(){
	hideElementsById(new Array("tiannetYearHead","tiannetMonthHead"),false);
	if(m_bolShowHour)	hideElementsById(new Array("selTianHour"),false);
	if(m_bolShowMinute)	hideElementsById(new Array("tiannetMinuteHead"),false);
	hideElementsById(new Array("tiannetHourHead","selTianYear","selTianMonth","selTianMinute"),true);
}
//单击分钟span标签响应
function spanMinuteCEvent(){
	hideElementsById(new Array("tiannetYearHead","tiannetMonthHead"),false);
	if(m_bolShowHour)	hideElementsById(new Array("tiannetHourHead"),false);
	if(m_bolShowMinute)	hideElementsById(new Array("selTianMinute"),false);
	hideElementsById(new Array("tiannetMinuteHead","selTianYear","selTianMonth","selTianHour"),true);
}
//单击秒span标签响应
function spanSecondCEvent(){
	hideElementsById(new Array("tiannetYearHead","tiannetMonthHead"),false);
	if(m_bolShowHour)	hideElementsById(new Array("tiannetHourHead"),false);
	if(m_bolShowMinute)	hideElementsById(new Array("tiannetMinuteHead"),false);
	if(m_bolShowSecond)	hideElementsById(new Array("selTianSecond"),false);
	hideElementsById(new Array("tiannetSecondHead","selTianYear","selTianMonth","selTianHour","selTianMinute"),true);
}

//年份下拉框的change事件
function selYearChgEvent(value) {
	tiannetYear = value;
	tiannetSetDay(tiannetYear,tiannetMonth);
	hideElement("tiannetYearHead",false);
	hideElement("selTianYear",true);
}

//月份下拉框的change事件
function selMonthChgEvent(value) {
	tiannetMonth = value;
	tiannetSetDay(tiannetYear,tiannetMonth);
	hideElement("tiannetMonthHead",false);
	hideElement("selTianMonth",true);
}

//小时下拉框的change事件
function selHourChgEvent(value) {
	tiannetHour = value;
	tiannetWriteHead();
	hideElement("tiannetHourHead",false);
	hideElement("selTianHour",true);
}

//分钟下拉框的change事件
function selMinuteChgEvent(value) {
	tiannetMinute = value;
	tiannetWriteHead();
	hideElement("tiannetMinuteHead",false);
	hideElement("selTianMinute",true);
}
//秒下拉框的change事件
function selSecondChgEvent(value) {
	tiannetSecond = value;
	tiannetWriteHead();
	hideElement("tiannetSecondHead",false);
	hideElement("selTianSecond",true);
}

//根据标签id隐藏或显示标签
function hideElementsById(arrId,bolHide){
	var strDisplay = "";
	if(bolHide)	strDisplay = "none";
	for(var i = 0;i < arrId.length;i ++){
		var obj = document.getElementById(arrId[i]);
		obj.style.display = strDisplay;
	}
}
//隐藏或显示标签
function hideElement(obj, bolHide) {
	var strDisplay = "";
	if(bolHide)	strDisplay = "none";
	if( (typeof(obj)).toLowerCase() != "object") {
		obj = tGetElementById(obj);
	}
	obj.style.display = strDisplay;
}
//------------------ end 显示日期时间的span标签响应事件 ---------------------------//

//判断某年是否为闰年
function isPinYear(year){
	var bolRet = false;
	if (0==year%4&&((year%100!=0)||(year%400==0))) {
		bolRet = true;
	}
	return bolRet;
}

//得到一个月的天数，闰年为29天
function getMonthCount(year,month){
	var c=m_aMonHead[month-1];
	if((month==2)&&isPinYear(year)) c++;
	return c;
}
//重新设置当前的日。主要是防止在翻年、翻月时，当前日大于当月的最大日
function setRealDayCount() {
	if( tiannetDay > getMonthCount(tiannetYear,tiannetMonth) ) {
		//如果当前的日大于当月的最大日，则取当月最大日
		tiannetDay = getMonthCount(tiannetYear,tiannetMonth);
	}
}

//在个位数前加零
function addZero(value){
	if(value < 10 ){
		value = "0" + value;
	}
	return value;
}
//取出空格
function tiannetTrim(str) {
	return str.replace(/(^\s*)|(\s*$)/g,"");
}
//判断字符串是否为合法的日期形式
function tiannetIsValidateDT(strDTStr) {
	strDTStr = tiannetTrim(strDTStr);
	if( strDTStr.length < 10 ) return false;
	var strDate = strDTStr.substring(0,10);
	var arrDate = strDate.split(tiannetDateSplit);
	if( arrDate.length != 3 ) return false;
	var year = parseInt(arrDate[0],10),month = parseInt(arrDate[1],10);
	var day = parseInt(arrDate[2],10);
	if( year == "NaN" || month == "NaN" || day == "Nan" ) return false;
	if( year < 1000 || year > 9999 ) return false;
	if( month < 1 || month >12 ) return false;
	if( day < 1 || day > 31 ) return false;
	var strDateTime = arrDate[1] + "/" + arrDate[2] + "/" + arrDate[0]; //+ tiannetDateTimeSplit +strDTStr.substring(11);
	strDateTime = tiannetTrim(strDateTime);
	//alert(new Date(strDateTime));
	if( new Date(strDateTime) == "NaN" ) {
		return false;
	}
	return true;
}

//为select创建一个option
function createOption(objSelect,value,text){
	var option = document.createElement("OPTION");
	option.value = value;
	option.text = text;
	objSelect.options.add(option);
}


//重新构建年份选择项
function rebuildYearOptions() {

	//去除年份下拉框中的项
	var iSelYearLen = tGetElementById("selTianYear").length;
	for( var i = 0;i < iSelYearLen;i ++ ) {
		tGetElementById("selTianYear").remove(0);
	}
	//考虑开始结束年份的大小颠倒
	var sec = tiannetYearSt;
	if( sec > tiannetYearEnd ) {
		sec = tiannetYearEnd;
		tiannetYearEnd = tiannetYearSt;
		tiannetYearSt = sec;
	}
	
	for( var i = tiannetYearSt;i <= tiannetYearEnd;i ++ ) {
		createOption(tGetElementById("selTianYear"),i, i + "年");
	}

}

//得到年份选择框中的所有年份(20060412-tiannet)
function getYearsFromSel() {
	var iSelYearLen = tGetElementById("selTianYear").length;
	var arrYear = new Array(iSelYearLen);
	for( var i = 0;i < iSelYearLen;i ++ ) {
		arrYear[i] = tGetElementById("selTianYear").options[i].value;
	}
	return arrYear;
}

//往前翻 Year
function tiannetPrevYear()	{

	//(20060412-tiannet)
	if( !tiannetIsAutoExpandYearPeriod && tiannetYear <= tiannetYearSt ) {
		alert("您要选择的年份" + (tiannetYear - 1) + "已经小于最小允许的年份" + tiannetYearSt + "！");
		return;
	}
	if(tiannetYear > 999 && tiannetYear <10000){tiannetYear--;}
	else{alert("年份超出范围（1000-9999）！");}
	tiannetSetDay(tiannetYear,tiannetMonth);
	//如果年份小于允许的最小年份，则创建对应的option
	if( tiannetYear < tiannetYearSt ) {
		
		tiannetYearSt = tiannetYear;
		rebuildYearOptions();//重新构建年份下拉框选项(tiannet-20050415)

	}
	checkSelect(tGetElementById("selTianYear"),tiannetYear);
	tiannetWriteHead();
}
//往后翻 Year
function tiannetNextYear() {

	//(20060412-tiannet)
	if( !tiannetIsAutoExpandYearPeriod && tiannetYear >= tiannetYearEnd ) {
		alert("您要选择的年份" + (parseInt(tiannetYear,10) + 1) + "已经超出最大允许的年份" + tiannetYearEnd + "！");
		return;
	}

	if(tiannetYear > 999 && tiannetYear <10000){tiannetYear++;}
	else{alert("年份超出范围（1000-9999）！");return;}
	tiannetSetDay(tiannetYear,tiannetMonth);
	//如果年份超过允许的最大年份，则创建对应的option
	if( tiannetYear > tiannetYearEnd ) {

		tiannetYearEnd = tiannetYear;
		rebuildYearOptions();//重新构建年份下拉框选项(tiannet-20050415)

	}
	checkSelect(tGetElementById("selTianYear"),tiannetYear);
	tiannetWriteHead();
}
//选择今天
function tiannetToday()	{
	tiannetYear = tiannetDateNow.getFullYear();
	tiannetMonth = tiannetDateNow.getMonth()+1;
	tiannetDay = tiannetDateNow.getDate();
	tiannetSetValue(true);
	//tiannetSetDay(tiannetYear,tiannetMonth);
	//selectObject();
}
//往前翻月份
function tiannetPrevMonth() {
	if(tiannetMonth>1){tiannetMonth--}else{tiannetYear--;tiannetMonth=12;}
	tiannetSetDay(tiannetYear,tiannetMonth);
	checkSelect(tGetElementById("selTianMonth"),tiannetMonth);
	tiannetWriteHead();
}
//往后翻月份
function tiannetNextMonth() {
	if(tiannetMonth==12){tiannetYear++;tiannetMonth=1}else{tiannetMonth++}
	tiannetSetDay(tiannetYear,tiannetMonth);
	checkSelect(tGetElementById("selTianMonth"),tiannetMonth);
	tiannetWriteHead();
}

//向span标签中写入年、月、时、分等数据
function tiannetWriteHead(){
	tGetElementById("tiannetYearHead").innerText = tiannetYear + "年";
	tGetElementById("tiannetMonthHead").innerText = tiannetMonth + "月";
	if( m_bolShowHour )		tGetElementById("tiannetHourHead").innerText = " "+tiannetHour + "时";
	if( m_bolShowMinute )	tGetElementById("tiannetMinuteHead").innerText = tiannetMinute + "分";
	if( m_bolShowSecond )	tGetElementById("tiannetSecondHead").innerText = tiannetSecond + "秒";
	tiannetSetValue(false);//给文本框赋值，但不隐藏本控件
}

//设置显示天
function tiannetSetDay(yy,mm) {
  
	setRealDayCount();//设置当月真实的日
	tiannetWriteHead();
	var strDateFont1 = "", strDateFont2 = "" //处理日期显示的风格

	for (var i = 0; i < 37; i++){tiannetArrDay[i]=""};  //将显示框的内容全部清空
	var day1 = 1;
	var firstday = new Date(yy,mm-1,1).getDay();  //某月第一天的星期几
	for (var i = firstday; day1 < getMonthCount(yy,mm)+1; i++){
		tiannetArrDay[i]=day1;day1++;
	}
	//如果用于显示日的最后一行的第一个单元格的值为空，则隐藏整行。
	//if(tiannetArrDay[35] == ""){
	//	tGetElementById("trTiannetDay5").style.display = "none";
	//} else {
	//	tGetElementById("trTiannetDay5").style.display = "";
	//}
	for (var i = 0; i < 37; i++){ 
		var da = tGetElementById("tdTiannetDay"+i)     //书写新的一个月的日期星期排列
		if (tiannetArrDay[i]!="") { 
			//判断是否为周末，如果是周末，则改为红色字体
			if(i % 7 == 0 || (i+1) % 7 == 0){
			strDateFont1 = "<font color=#f0000>"
			strDateFont2 = "</font>"
			} else {
				strDateFont1 = "";
				strDateFont2 = ""
			}
			da.innerHTML = strDateFont1 + tiannetArrDay[i] + strDateFont2;

			//如果是当前选择的天，则改变颜色
			if(tiannetArrDay[i] == tiannetDay ) {
				da.style.backgroundColor = "#CCCCCC";
			} else {
				da.style.backgroundColor = "#EFEFEF";
			}
			da.style.cursor="hand"
		} else {
			da.innerHTML="";da.style.backgroundColor="";da.style.cursor="default"
		}
	}//end for
	tiannetSetValue(false);//给文本框赋值，但不隐藏本控件
}//end function tiannetSetDay

//根据option的值选中option
function checkSelect(objSelect,selectValue) {
	var count = parseInt(objSelect.length);
	if( selectValue < 10 && selectValue.toString().length == 2) {
		selectValue = selectValue.substring(1,2);
	}
	for(var i = 0;i < count;i ++){
		if(objSelect.options[i].value == selectValue){
			objSelect.selectedIndex = i;
			break;
		}
	}//for
}
//选中年、月、时、分、秒等下拉框
function selectObject(){
	//如果年份小于允许的最小年份，则创建对应的option
	if( tiannetYear < tiannetYearSt ) {
		
		tiannetYearSt = tiannetYear;
		rebuildYearOptions();//重新构建年份下拉框选项(tiannet-20050415)
	}
	//如果年份超过允许的最大年份，则创建对应的option
	if( tiannetYear > tiannetYearEnd ) {
		
		tiannetYearEnd = tiannetYear;
		rebuildYearOptions();//重新构建年份下拉框选项(tiannet-20050415)

	}
	checkSelect(tGetElementById("selTianYear"),tiannetYear);
	checkSelect(tGetElementById("selTianMonth"),tiannetMonth);
	
	if( m_bolShowHour )		checkSelect(tGetElementById("selTianHour"),tiannetHour);
	if( m_bolShowMinute )	checkSelect(tGetElementById("selTianMinute"),tiannetMinute);
	if( m_bolShowSecond )	checkSelect(tGetElementById("selTianSecond"),tiannetSecond);
}

//给接受日期时间的控件赋值
//参数bolHideControl - 是否隐藏控件
function tiannetSetValue(bolHideControl){
	var value = "";
	if( !tiannetDay || tiannetDay == "" ){
		tiannetOutObject.value = value;
		return;
	}
	var mm = tiannetMonth;
	var day = tiannetDay;
	if( mm < 10 && mm.toString().length == 1) mm = "0" + mm;
	if( day < 10 && day.toString().length == 1) day = "0" + day;
	value = tiannetYear + tiannetDateSplit + mm + tiannetDateSplit + day;
	if( m_bolShowHour ){
		var hour = tiannetHour;
		if( hour < 10 && hour.toString().length == 1 ) hour = "0" + hour;
		value += tiannetDateTimeSplit + hour;
	}
	if( m_bolShowMinute ){
		var minute = tiannetMinute;
		if( minute < 10 && minute.toString().length == 1 ) minute = "0" + minute;
		value += tiannetTimeSplit + minute;
	}
	if( m_bolShowSecond ){
		var second = tiannetSecond;
		if( second < 10 && second.toString().length == 1 ) second = "0" + second;
		value += tiannetTimeSplit + second;
	}
	tiannetOutObject.value = value;
	//tGetElementById("divTiannetDate").style.display = "none";
	if( bolHideControl ) {
		tiannetHideControl();
	}
}

//是否显示时间
function showTime(){
	if( !m_bolShowHour && m_bolShowMinute){
		alert("如果要选择分钟，则必须可以选择小时！");
		return;
	}
	if( !m_bolShowMinute && m_bolShowSecond){
		alert("如果要选择秒，则必须可以选择分钟！");
		return;
	}
	hideElementsById(new Array("tiannetHourHead","selTianHour","tiannetMinuteHead","selTianMinute","tiannetSecondHead","selTianSecond"),true);
	if( m_bolShowHour ){
		//显示小时
		hideElementsById(new Array("tiannetHourHead"),false);
	}
	if( m_bolShowMinute ){
		//显示分钟
		hideElementsById(new Array("tiannetMinuteHead"),false);
	}
	if( m_bolShowSecond ){
		//显示秒
		hideElementsById(new Array("tiannetSecondHead"),false);
	}
}

//弹出显示日历选择控件，以让用户选择
function tiannetPopCalendar(){
	//隐藏下拉框，显示相对应的head
	hideElementsById(new Array("selTianYear","selTianMonth","selTianHour","selTianMinute"),true);
	hideElementsById(new Array("tiannetYearHead","tiannetMonthHead","tiannetHourHead","tiannetMinuteHead"),false);


	//如果开始和结束年份之一与默认值不同，则重新创建年份选择器
	if( tiannetYearSt != tiannetDefaultYearSt  || tiannetYearEnd != tiannetDefaultYearEnd ) {
		
		rebuildYearOptions();//重新构建年份下拉框选项(tiannet-20050415)

	}

	//如果开始年份大于tiannetYear而又不允许自动扩展年份，则将tiannetYear设为开始年份(tiannet-20050415)
	if( tiannetYear <= tiannetYearSt && !tiannetIsAutoExpandYearPeriod ) {
		tiannetYear = tiannetYearSt;
	}

	//如果结束年份小于tiannetYear而又不允许自动扩展年份，则将tiannetYear设为结束年份(tiannet-20050415)
	if( tiannetYear >= tiannetYearEnd && !tiannetIsAutoExpandYearPeriod ) {
		tiannetYear = tiannetYearEnd;
	}

	tiannetSetDay(tiannetYear,tiannetMonth);
	tiannetWriteHead();
	showTime();
	var dads  = tGetElementById("divTiannetDate").style;
	var iX, iY;
  
	var h = tGetElementById("divTiannetDate").offsetHeight;
	var w = tGetElementById("divTiannetDate").offsetWidth;
	//计算left
	if (window.event.x + h > document.body.offsetWidth - 10    )
		iX = window.event.x - h - 5 ;
	else
		iX = window.event.x + 5;  
	if (iX <0)		
		iX=0;

	//计算top
	iY = window.event.y;
	if (window.event.y + w > document.body.offsetHeight - 10   )
		iY = document.body.scrollTop + document.body.offsetHeight - w - 5 ;
	else
		iY = document.body.scrollTop +window.event.y + 5;  
	if (iY <0)		
		iY=0;

	dads.left = iX;
	dads.top = iY;
	tiannetShowControl();
	selectObject();
}
//隐藏日历控件(同时显示被强制隐藏的标签)
function tiannetHideControl(){
	tGetElementById("divTiannetDate").style.display = "none";
	tiannetShowObject();
	arrTiannetHide = new Array();//将被隐藏的标签对象清空
}
//显示日历控件(同时隐藏会遮挡的标签)
function tiannetShowControl(){
	tGetElementById("divTiannetDate").style.display = "";
	tiannetHideObject("SELECT");
	tiannetHideObject("OBJECT");
}

//根据标签名称隐藏标签。如会遮住控件的select，object
function tiannetHideObject(strTagName) {
	
	x = tGetElementById("divTiannetDate").offsetLeft;
	y = tGetElementById("divTiannetDate").offsetTop;
	h = tGetElementById("divTiannetDate").offsetHeight;
	w = tGetElementById("divTiannetDate").offsetWidth;

	var arrObjs = document.getElementsByTagName(strTagName);
	
	for (var i = 0; i < arrObjs.length; i++)
	{
		
		var obj = arrObjs[i];
		if (! obj || ! obj.offsetParent)
			continue;

		// 获取元素对于BODY标记的相对坐标
		var objLeft   = obj.offsetLeft;
		var objTop    = obj.offsetTop;
		var objHeight = obj.offsetHeight;
		var objWidth = obj.offsetWidth;
		var objParent = obj.offsetParent;
		
		while (objParent.tagName.toUpperCase() != "BODY"){
			objLeft  += objParent.offsetLeft;
			objTop   += objParent.offsetTop;
			objParent = objParent.offsetParent;
		}

		//alert("控件左端:" + x + "select左端" + (objLeft + objWidth) + "控件底部:" + (y+h) + "select高:" + objTop);
		
		var bolHide = true;
		if( obj.style.display == "none" || obj.style.visibility == "hidden" || obj.getAttribute("Author") == "tiannet" ){
			//如果标签本身就是隐藏的，则不需要再隐藏。如果是控件中的下拉框，也不用隐藏。
			bolHide = false;
		}
		if(  ( (objLeft + objWidth) > x && (y + h + 20) > objTop && (objTop+objHeight) >  y && objLeft < (x+w) ) && bolHide ){
			//arrTiannetHide.push(obj);//记录被隐藏的标签对象
			arrTiannetHide[arrTiannetHide.length] = obj;
			obj.style.visibility = "hidden";
		}
		
		// 判断元素相对于控件的坐标
		//objTop = objTop - y;
		//if (x > (objLeft + obj.offsetWidth) || objLeft > (x + w))
		//	;//在控件的左边或者右边，不用隐含
		//else if (objTop > h || objHeight < y)
		//	;//在控件的下面或者上面，不用隐含
		//else
		//	obj.style.visibility = "hidden";
		
	}
}
//显示被隐藏的标签
function tiannetShowObject(){
	for(var i = 0;i < arrTiannetHide.length;i ++){
		//alert(arrTiannetHide[i]);
		arrTiannetHide[i].style.visibility = "";
	}
}

//初始化日期。
function tiannetInitDate(strDate){
	var arr = strDate.split(tiannetDateSplit);
	tiannetYear = arr[0];
	tiannetMonth = arr[1];
	tiannetDay = arr[2];

	//if( tiannetMonth < 10 && tiannetMonth.toString().length == 2) tiannetMonth = tiannetMonth.substring(1,2);
	//if( tiannetDay < 10 && tiannetDay.toString().length == 2) tiannetDay = tiannetDay.substring(1,2);
}

//初始化日期为当前日期
function tiannetInitDateNow() {
	tiannetYear = tiannetDateNow.getFullYear(); 
	tiannetMonth = tiannetDateNow.getMonth()+1; 
	tiannetDay = tiannetDateNow.getDate();
}

//清空
function tiannetClear(){
	tiannetOutObject.value = "";
	tiannetHideControl();
}

 //任意点击时关闭该控件
document.onclick = function (){ 
  with(window.event.srcElement){ 
	if (tagName != "INPUT" && getAttribute("Author") != "tiannet")
    tiannetHideControl();
  }
}
//按ESC键关闭该控件
document.onkeypress = function (){
	if( event.keyCode == 27 ){
		tiannetHideControl();
	}
}

//由id得到一个对象
function tGetElementById(id) {
	
	return document.getElementById(id);
	
}