/**
 * 这是一个使用JavaScript进行表单验证的伪装类。
 * 在使用此类时我们建议表单域的名称与其文本描述信息的id保持一种关系，即：fieldName + "_tip" = id
 * 举例：<span id="username_tip">用户名</span><input type="text" name="username">
 * 这样当数据不合法时便于弹出提示框。
 * 使用方法：
 * var validator = new Validator();
 * validator.set××××××();//调用一些set方法设置你要验证的信息
 * ------------------
 * validator.validate();//最后执行验证
 * @author tiannet
 */
function Validator() {

	this.method = new Array();//存储需要执行的验证方法

	// --------- 开始定义存储表单域的数组 -----------//
	this.notEmpty	= new Array();//不能为空的表单域
	this.integer	= new Array();//只能为整数的表单域
	this.doubles	= new Array();//只能为double的表单域
	this.number		= new Array();//只能为number的表单域
	this.date		= new Array();//合法的日期时间
	this.dateFormat	= new Array();//日期格式。如y-m-d,ymd,mdy,m-d-y
	this.email		= new Array();//Email格式
	this.phone		= new Array();//普通电话格式
	this.mobile		= new Array();//手机号格式
	this.postCode	= new Array();//邮政编码
	this.url		= new Array();//网页地址格式
	this.currency	= new Array();//货币格式
	this.english	= new Array();//只能为英文字母
	this.chinese	= new Array();//只能为中文
	this.userName	= new Array();//合法的用户名，以字母开头、可带数字、_、.的字串
	this.idCard		= new Array();//合法的身份证好
	this.ip			= new Array();//验证IP
	this.limit		= new Array();//限制表单域的值范围
	this.limitLen	= new Array();//限制表单域值长度范围
	this.compare	= new Array();//比较表单域的值
	this.compareLen	= new Array();//比较表单域值长度
	this.allowedExt	= new Array();//允许的后缀名，针对file表单。
	this.deniedExt	= new Array();//不允许的后缀名，针对file表单。

	//定义限制表单域值所需数据的存储
	this.limit["ele"]	= new Array();//限制值大小范围的表单域
	this.limit["min"]	= new Array();//限制值大小范围的最小值，也可是一个表单域名称
	this.limit["max"]	= new Array();//限制值大小范围的最大值，也可是一个表单域名称
	//定义限制表单域值的长度所需数据的存储
	this.limitLen["ele"]= new Array();
	this.limitLen["min"]= new Array();
	this.limitLen["max"]= new Array();

	//定义比较表单域值所需数据的存储
	this.compare["ele1"]= new Array();//比较的表单域
	this.compare["ope"]	= new Array();//比较操作符。包括==,!=,>,>=,<,<=。
	this.compare["ele2"]= new Array();//被比较的表单域或一个指定值
	//定义比较表单域值长度所需数据的存储
	this.compareLen["ele1"]	= new Array();//比较的表单域
	this.compareLen["ope"]	= new Array();//比较操作符。包括==,!=,>,>=,<,<=。
	this.compareLen["ele2"]	= new Array();//被比较的表单域或一个指定值

	this.allowedExt["ele"]	= new Array();
	this.allowedExt["ext"]	= new Array();

	this.deniedExt["ele"]	= new Array();
	this.deniedExt["ext"]	= new Array();


	// --------- 开始定义用户可调用的方法 -----------//
	//设置不能为空的表单域，如果是多个表单域，请以英文逗号分割。
	this.setNotEmpty = function(eles) {
		this.method["notEmpty"] = "hasNoEmpty(this.notEmpty)";
		this.notEmpty = addTwoArray(this.notEmpty,toArray(eles));
	}
	//设置只能为数字的表单域，如果是多个表单域，请以英文逗号分割。
	this.setInteger = function(eles) {
		this.method["integer"] = "isInteger(this.integer)";
		this.integer = addTwoArray(this.integer,toArray(eles));
	}
	//设置只能为double型的表单域，如果是多个表单域，请以英文逗号分割。
	this.setDouble = function(eles) {
		this.method["double"] = "isDouble(this.doubles)";
		this.doubles = addTwoArray(this.doubles,toArray(eles));
	}
	//设置只能为number型的表单域，如果是多个表单域，请以英文逗号分割。
	this.setNumber = function(eles) {
		this.method["number"] = "isNumber(this.number)";
		this.number = addTwoArray(this.number,toArray(eles));
	}
	//设置必须为合法日期时间的表单域。
	this.setDate = function(ele,formatStr) {
		this.method["date"] = "isDate(this.date,this.dateFormat)";
		this.date[this.date.length] = ele;
		if( typeof(formatStr) == "undefined" ) formatStr = "y-m-d";
		this.dateFormat[this.dateFormat.length] = formatStr;
	}
	//设置必须符合Email格式的表单域，如果是多个表单域，请以英文逗号分割。
	this.setEmail = function(eles) {
		this.method["email"] = "isEmail(this.email)";
		this.email = addTwoArray(this.email,toArray(eles));
	}
	//设置必须符合普通电话格式的表单域，如果是多个表单域，请以英文逗号分割。
	this.setPhone = function(eles) {
		this.method["phone"] = "isPhone(this.phone)";
		this.phone = addTwoArray(this.phone,toArray(eles));
	}
	//设置必须符合手机号格式的表单域，如果是多个表单域，请以英文逗号分割。
	this.setMobile = function(eles) {
		this.method["mobile"] = "isMobile(this.mobile)";
		this.mobile = addTwoArray(this.mobile,toArray(eles));
	}
	//设置必须符合邮政编码格式的表单域，如果是多个表单域，请以英文逗号分割。
	this.setPostCode = function(eles) {
		this.method["postCode"] = "isPostCode(this.postCode)";
		this.postCode = addTwoArray(this.postCode,toArray(eles));
	}
	//设置必须符合网页地址格式的表单域，如果是多个表单域，请以英文逗号分割。
	this.setUrl = function(eles) {
		this.method["url"] = "isUrl(this.url)";
		this.url = addTwoArray(this.url,toArray(eles));
	}
	//设置必须符合货币格式的表单域，如果是多个表单域，请以英文逗号分割。
	this.setCurrency = function(eles) {
		this.method["currency"] = "isCurrency(this.currency)";
		this.currency = addTwoArray(this.currency,toArray(eles));
	}
	//设置只能输入英文字母的表单域，如果是多个表单域，请以英文逗号分割。
	this.setEnglish = function(eles) {
		this.method["english"] = "isEnglish(this.english)";
		this.english = addTwoArray(this.english,toArray(eles));
	}
	//设置只能输入中文的表单域，如果是多个表单域，请以英文逗号分割。
	this.setChinese = function(eles) {
		this.method["chinese"] = "isChinese(this.chinese)";
		this.chinese = addTwoArray(this.chinese,toArray(eles));
	}
	//设置必须符合合法的用户名表单域，如果是多个表单域，请以英文逗号分割。
	this.setUserName = function(eles) {
		this.method["userName"] = "isUserName(this.userName)";
		this.userName = addTwoArray(this.userName,toArray(eles));
	}
	//设置必须符合合法的身份证号表单域，如果是多个表单域，请以英文逗号分割。
	this.setIdCard = function(eles) {
		this.method["idCard"] = "isIdCard(this.idCard)";
		this.idCard = addTwoArray(this.idCard,toArray(eles));
	}
	//设置符合IP地址的表单域，如果是多个表单域，请以英文逗号分割。
	this.setIP = function(eles) {
		this.method["ip"] = "isIP(this.ip)";
		this.ip = addTwoArray(this.ip,toArray(eles));
	}
	//设置表单域的值在一个范围之内
	//@param ele 元素的id或name
	//@param min 范围的最小值，可以是一个值或者另一个元素的id或name
	//@param max 范围的最大值，可以是一个值或者另一个元素的id或name
	this.setLimit = function(ele, min, max) {
		this.method["limit"] = "isLimit(this.limit)";
		this.limit["ele"][this.limit["ele"].length] = ele;
		this.limit["min"][this.limit["min"].length] = min;
		this.limit["max"][this.limit["max"].length] = max;
	}
	//设置表单域的值长度在一个范围之内
	//@param ele 元素的id或name
	//@param min 范围的最小值，可以是一个值或者另一个元素的id或name
	//@param max 范围的最大值，可以是一个值或者另一个元素的id或name
	this.setLimitLen = function(ele, min, max) {
		this.method["limitLen"] = "isLimit(this.limitLen,'len')";
		this.limitLen["ele"][this.limitLen["ele"].length] = ele;
		this.limitLen["min"][this.limitLen["min"].length] = min;
		this.limitLen["max"][this.limitLen["max"].length] = max;
	}
	//设置表单域的值与另一个表单域值或指定的值比较
	//@param ele1 比较的表单域
	//@param ope 比较操作符
	//@param ele2 被比较的表单域或指定的一个值
	this.setCompare = function(ele1, ope, ele2) {
		this.method["compare"] = "isCompare(this.compare)";
		this.compare["ele1"][this.compare["ele1"].length] = ele1;
		this.compare["ope"][this.compare["ope"].length] = ope;
		this.compare["ele2"][this.compare["ele2"].length] = ele2;
	}
	//设置表单域的值长度与另一个表单域值或指定的值比较
	//@param ele1 比较的表单域
	//@param ope 比较操作符
	//@param ele2 被比较的表单域或指定的一个值
	this.setCompareLen = function(ele1, ope, ele2) {
		this.method["compareLen"] = "isCompare(this.compareLen,'len')";
		this.compareLen["ele1"][this.compareLen["ele1"].length] = ele1;
		this.compareLen["ope"][this.compareLen["ope"].length] = ope;
		this.compareLen["ele2"][this.compareLen["ele2"].length] = ele2;
	}
	//设置必须符合指定后缀名的文件表单域。
	this.setAllowedExt = function(ele,ext) {
		this.method["allowedExt"] = "isAllowedExt(this.allowedExt)";
		this.allowedExt["ele"][this.allowedExt["ele"].length] = ele;
		this.allowedExt["ext"][this.allowedExt["ext"].length] = ext;
	}
	//设置不允许指定后缀名的文件表单域。
	this.setDeniedExt = function(ele,ext) {
		this.method["deniedExt"] = "isDeniedExt(this.deniedExt)";
		this.deniedExt["ele"][this.deniedExt["ele"].length] = ele;
		this.deniedExt["ext"][this.deniedExt["ext"].length] = ext;
	}

	//执行验证
	this.validate = function() {

		//调用设置的方法
		for( var key in this.method ) {
			if( !eval(this.method[key]) ) {
				return false;
			}
		}
		return true;
	}

}

function toArray(str) {
	if(typeof(str) == "object")
		return new Array(str);
	return str.split(",");
}

//将两个数组合并为一个数组
function addTwoArray( arr1, arr2 ) {
	for( var i = 0;i < arr2.length;i ++ ) {
		arr1[arr1.length] = arr2[i];
	}
	return arr1;
}

// ---------------------------- 开始使用正则表达式验证 ------------------------------------//

//执行正则表达式模式在字符串中查找
//@param s 要查找的字符串
//@param reg 正则表达式
//@return 如果找到匹配返回true，否则返回false。
function execRegExp(s, reg) {
	if( !reg.exec(s) ) return false;
	return true;
}

//判断一组表单域的值是否符合某种规则。值为空的域将被忽略
//@param arrEle 数组，一组表单域
//@param reg 正则表达式
//@param tip 不符合格式的提示文本
function execRegExpForEles( arrEle, reg, tip ) {
	for( var i = 0;i < arrEle.length;i ++ ) {
		var aInfo = getObjInfo(arrEle[i]);
		if( aInfo.value.length == 0 || aInfo.value[0].length == 0 ) continue;
		if( !execRegExp( aInfo.value[0], reg ) ) {
			alert(aInfo.desc + tip + "！");
			if( aInfo.object.focus() ) aInfo.object.focus();
			return false;
		}
	}
	return true;
}

//判断一组表单域是否只有数字组成
function isInteger( arrEle ) {
	return execRegExpForEles( arrEle,/^[-\+]?\d+$/,"只能为整数" );
}
//判断一组表单域的值是否为double型。
function isDouble( arrEle ) {
	return execRegExpForEles( arrEle,/^[-\+]?\d+(\.\d+)?$/,"只能为整数或小数" );
}
//判断一组表单域的值是否为number型。
function isNumber( arrEle ) {
	return execRegExpForEles( arrEle,/^\d+$/,"只能为数字" );
}
//判断一组表单域的值是否符合Email格式。
function isEmail( arrEle ) {
	return execRegExpForEles( arrEle,/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,"格式不正确。\n举例：username@htomail.com" );
}
//判断一组表单域的值是否符合普通电话格式。
function isPhone( arrEle ) {
	return execRegExpForEles( arrEle,/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,"格式不正确。\n举例：010-86523725" );
}
//判断一组表单域的值是否符合手机号格式。
function isMobile( arrEle ) {
	return execRegExpForEles( arrEle,/^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,"格式不正确。\n举例：13975986315" );
}
//判断一组表单域的值是否为邮政编码。
function isPostCode( arrEle ) {
	return execRegExpForEles( arrEle,/^[1-9]\d{5}$/,"格式不正确。\n举例：100083" );
}
//判断一组表单域的值是否符合网页地址格式。
function isUrl( arrEle ) {
	return execRegExpForEles( arrEle,/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,"格式不正确。\n举例：http://www.google.com" );
}
//判断一组表单域的值是否符合货币格式。
function isCurrency( arrEle ) {
	return execRegExpForEles( arrEle,/^\d+(\.\d+)?$/,"格式不正确。\n举例：22.2" );
}
//判断一组表单域的值是否只由英文字母组成。
function isEnglish( arrEle ) {
	return execRegExpForEles( arrEle,/^[A-Za-z]+$/,"只能由英文字母组成。\n举例：abcDefG" );
}
//判断一组表单域的值是否只由中文组成。
function isChinese( arrEle ) {
	return execRegExpForEles( arrEle,/^[\u0391-\uFFE5]+$/,"只能由中文组成。\n举例：你好" );
}
//判断一组表单域的值是否为合法的用户名。
function isUserName( arrEle ) {
	return execRegExpForEles( arrEle,/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){2,19}$/,"必须以字母开头、可带数字、下划线及小数点，大小为3~20个字符。\n举例：abc_1,abc,abc1" );
}
//判断一组表单域的值是否为合法的IP地址。
function isIP( arrEle ) {
	return execRegExpForEles( arrEle,/^[0-9.]{1,20}$/,"格式不正确。\n举例：10.81.32.1" );
}
// ---------------------------- 结束使用正则表达式验证 ------------------------------------//

/**
 * 判断表单域是否无空项。对于radio，checkbox则判断是否有被选择项，对于select，则判断是否选中的是第一项。
 * @param arrEle 数组，表示一组表单域名称或id。
 * @return 如果有空项，则返回true，否则返回false。
 */
function hasNoEmpty( arrEle ) {
	if( !arrEle || arrEle.length == 0 ) return true;
	
	for( var i = 0;i < arrEle.length;i ++ ) {
		
		var aInfo = getObjInfo(arrEle[i]);
		
		if( aInfo.tagName == "select" ) {
			if( aInfo.object.selectedIndex == 0 ) {
				alert(aInfo.desc + "必须选择！");
				aInfo.object.focus();
				return false;
			}
		} else {

			if( aInfo.type == "file" ) {
				if( aInfo.value.length ==0 || aInfo.value[0].length == 0 ) {
					alert(aInfo.desc + "不能为空！\n请点击[浏览...]按钮从电脑上选择一个文件！");
					return false;
				}
			} else if( aInfo.type == "radio" || aInfo.type == "checkbox" ) {
				
				if( aInfo.value.length ==0 || aInfo.value[0].length == 0 ) {
					alert(aInfo.desc + "必须选择！");
					return false;
				}
			} else {
				if( aInfo.value.length ==0 || aInfo.value[0].length == 0 ) {
					alert(aInfo.desc + "不能为空！");
					if( aInfo.object.focus() ) aInfo.object.focus();
					return false;
				}
			}

		}// end if tagName
		
	}//end for
	return true;
}
//检验一组表单域的值是否为合法的身份证号码
function isIdCard( arrEle ) {
	for( var i = 0;i < arrEle.length;i ++ ) {
		var aInfo = getObjInfo(arrEle[i]);
		if( aInfo.value.length == 0 || aInfo.value[0].length == 0 ) continue;
		if( !isValidateIdCard( aInfo.value[0] ) ) {
			alert(aInfo.desc + "不是合法的！");
			if( aInfo.object.focus() ) aInfo.object.focus();
			return false;
		}
	}
	return true;
}

//判断某年是否为闰年
function isPinYear(year){
	var bolRet = false;
	if (0==year%4&&((year%100!=0)||(year%400==0))) {
		bolRet = true;
	}
	return bolRet;
}

//验证日期是否合法
//@dateStr 表示日期时间的字符串
//@formatStr 格式化字符串，主要是年月日的顺序。如ymd,dmy
//对于日期分割字符串，只考虑-,/或者空
function isValidateDateTime(dateStr,formatStr) {
	dateStr = trim(dateStr);
	var arrDate = new Array(3);
	var aTemp = new Array();
	var strDate = "";
	formatStr = trim(formatStr.toLowerCase());
	if( formatStr.length != 3 ) {
		alert("调用isValidateDateTime函数格式化字符串只能为三位！如ymd,dmy！");
		return false;
	}
	if( dateStr.indexOf("-") != -1 ) {
		if( dateStr.length < 10 ) return false;
		strDate = dateStr.substring(0,10);
		aTemp = strDate.split("-");
		arrDate[formatStr.substring(0,1)] = aTemp[0];
		arrDate[formatStr.substring(1,2)] = aTemp[1];
		arrDate[formatStr.substring(2,3)] = aTemp[2];
	} else if( dateStr.indexOf("/") != -1 ) {
		if( dateStr.length < 10 ) return false;
		strDate = dateStr.substring(0,10);
		aTemp = strDate.split("/");
		arrDate[formatStr.substring(0,1)] = aTemp[0];
		arrDate[formatStr.substring(1,2)] = aTemp[1];
		arrDate[formatStr.substring(2,3)] = aTemp[2];
	} else {
		//日期没有分隔符
		if( dateStr.length < 8 ) return false;
		arrDate[formatStr.substring(0,1)] = dateStr.substring(0,4);
		arrDate[formatStr.substring(1,2)] = dateStr.substring(4,6);
		arrDate[formatStr.substring(2,3)] = dateStr.substring(6,8);
		//用分隔符重构日期字符串
		dateStr = dateStr.substring(0,4) + "/" + dateStr.substring(4,6) + "/" + dateStr.substring(6,8) + dateStr.substring(8);
	}
	
	var year = parseInt(arrDate["y"],10),month = parseInt(arrDate["m"],10);
	var day = parseInt(arrDate["d"],10);
	if( year == "NaN" || month == "NaN" || day == "Nan" ) return false;
	if( year < 1000 || year > 9999 ) return false;
	if( month < 1 || month >12 ) return false;
	if( day < 1 || day > 31 ) return false;
	if( ( month == 2 || month == 4 || month == 6 || month == 9 || month == 11 ) && day >30 ) return false;
	if( isPinYear(year) && month == 2 && day > 29 ) return false;
	if( !isPinYear(year) && month == 2 && day > 28 ) return false;
	var strDateTime = arrDate["m"] + "/" + arrDate["d"] + "/" + arrDate["y"];
	//加上时间
	if( dateStr.length > 10 ) strDateTime += " " + dateStr.substring(11);
	strDateTime = trim(strDateTime);
	if( new Date(strDateTime) == "NaN" ) {
		return false;
	}
	return true;
}

//检验是否为合法的身份政号
function isValidateIdCard(number) {
	var date, Ai;
	var verify = "10x98765432";
	var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
	var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];
	var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
	if(re == null) return false;
	if(re[1] >= area.length || area[re[1]] == "") return false;
	if(re[2].length == 12){
		Ai = number.substr(0, 17);
		date = [re[9], re[10], re[11]].join("-");
	}
	else{
		Ai = number.substr(0, 6) + "19" + number.substr(6);
		date = ["19" + re[4], re[5], re[6]].join("-");
	}
	if(!isValidateDateTime(date, "ymd")) return false;
	var sum = 0;
	for(var i = 0;i<=16;i++){
		sum += Ai.charAt(i) * Wi[i];
	}
	Ai +=  verify.charAt(sum%11);
	return (number.length ==15 || number.length == 18 && number == Ai);
}//end function isValidateIdCard(number) {

//验证日期时间是否合法
//@param aDate 日期时间字符串数组或表单域名称
//@param aFormatStr 格式化字符串
function isDate(aDate, aFormatStr) {
	
	for( var i = 0;i < aDate.length;i ++ ) {

		var strDate = aDate[i];
		var desc = aDate[i];
		var objInfo = null;
		//如果是表单域名称
		if( isObject(strDate)) {
			objInfo = getObjInfo(strDate);
			strDate = objInfo.value.length > 0 ? objInfo.value[0] : "";
			desc = objInfo.desc;
		}
		if( strDate.length == 0 ) continue;
		var result = true;
		var msg = "";
		switch( aFormatStr[i].toLowerCase() ) {
			
			case "y-m-d":
				result = isValidateDateTime(strDate,"ymd");
				msg = "2008-02-03(或2008-02-03 01:01:01)";
				break;
			case "y/m/d":
				result = isValidateDateTime(strDate,"ymd");
				msg = "2008/02/03(或2008/02/03 01:01:01)";
				break;
			case "ymd":
				result = isValidateDateTime(strDate,"ymd");
				msg = "20080203";
				break;
			
			case "m-d-y":
				result = isValidateDateTime(strDate,"mdy");
				msg = "02-20-2008(或02-20-2008 01:01:01)";
				break;
			case "m/d/y":
				result = isValidateDateTime(strDate,"mdy");
				msg = "02/20/2008(或02/20/2008 01:01:01)";
				break;
			case "mdy":
				result = isValidateDateTime(strDate,"mdy");
				msg = "02202008";
				break;
			default:
				alert("无效的日期格式化字符串：" + aFormatStr[i] + "。\n有效的包括：y-m-d,y/m/d,ymd,m-d-y,m/d/y,mdy");
				result = false;
				break;
		}//end switch

		if( !result ) {
			alert(desc + "不是有效的日期(时间格式)！\n举例：" + msg);
			if( objInfo && objInfo.object && objInfo.object.focus() ) {
				objInfo.focus();
			}
			return false;
		}

	}//end for

	return true;

}

//验证表单域的值或值的长度是否在指定范围
//@limit 限制的数据
//@mode 模式。用于区分限制值还是值的长度，如果传入len，则视为长度比较，否则视为值。
function isLimit(limit,mode) {
	var eles = limit["ele"];//元素id或name
	var mins = limit["min"];//最小值
	var maxs = limit["max"];//最大值
	var ope1 = new Array(eles.length);
	var ope2 = new Array(eles.length);
	var modeDesc = "值";
	if( mode == "len" ) modeDesc = "长度";
	for( var i = 0;i < eles.length;i ++ ) {
		var eleInfo = getObjInfo(eles[i]);
		var eleValue = "";
		var eleDesc = eleInfo.desc;
		if( eleInfo.value.length != 0 ) eleValue = eleInfo.value[0];
		//比较长度
		if( mode == "len" ) eleValue = eleValue.length;
		//得到较小值信息
		var minValue = mins[i];
		var minDesc = mins[i];
		if( document.getElementById(mins[i]) && (typeof( document.getElementById(mins[i]) )).toLowerCase() == "object" ) {
			var minInfo = getObjInfo(mins[i]);
			minValue = minInfo.value.length > 0 ? minInfo.value[0] : "";
			minDesc = minInfo.desc;
		}
		//得到较大值信息
		var maxValue = maxs[i];
		var maxDesc = maxs[i];
		if( document.getElementById(maxs[i]) && (typeof( document.getElementById(maxs[i]) )).toLowerCase() == "object" ) {
			var maxInfo = getObjInfo(maxs[i]);
			maxValue = maxInfo.value.length > 0 ? maxInfo.value[0] : "";
			maxDesc = maxInfo.desc;
		}
		//alert( "eleValue=" + eleValue + ":minValue=" + minValue + ":maxValue=" + maxValue +
		//	"\neleValue < minValue：" + (eleValue < minValue) + "\neleValue > maxValue：" + (eleValue > maxValue));
		if( eleValue < minValue || eleValue > maxValue ) {
			alert(eleDesc + "(" + modeDesc + ")必须在" + minValue + "~" + maxValue + "之间！");
			if( eleInfo.object.focus() ) eleInfo.object.focus();
			return false;
		}
	}
	return true;
}
//比较两个表单域的值大小或比较一个表单域的值与指定值的大小
//如果被比较的表单域为一个对象，则取表单域的值比较，否则取传递的原值比较
//@param compare 用于比较得数据信息
//@mode 比较模式，如果为len，则视为值长度的比较，否则视为值的比较。
function isCompare(compare,mode) {
	var ele1s = compare["ele1"];//比较元素
	var ope	  = compare["ope"];//操作符
	var ele2s = compare["ele2"];//被比较元素或值
	var modeDesc = "值";
	if( mode == "len" ) modeDesc = "长度";
	for( var i = 0; i < ele1s.length;i ++ ) {
		var ele1Info = getObjInfo(ele1s[i]);
		var ele1Value = "";
		var desc1 = ele1Info.desc;
		if( ele1Info.value.length != 0 ) ele1Value = ele1Info.value[0];
		//比较长度
		if( mode == "len" ) ele1Value = ele1Value.length;
		var ele2Value = ele2s[i];
		var desc2 = ele2s[i];
		if( document.getElementById(ele2s[i]) && (typeof( document.getElementById(ele2s[i]) )).toLowerCase() == "object" ) {
			var ele2Info = getObjInfo(ele2s[i]);
			ele2Value = ele2Info.value.length > 0 ? ele2Info.value[0] : "";
			desc2 = ele2Info.desc;
		}
		switch( trim(ope[i]) ) {
			case "==":
				if( ele1Value != ele2Value ) {
					alert(desc1 + "(" + modeDesc + ")与" + desc2 + "必须一致(相等)！");
					if( ele1Info.object.focus() ) ele1Info.object.focus();
					return false;
				}
				break;
			case "!=":
				if( ele1Value == ele2Value ) {
					alert(desc1 + "(" + modeDesc + ")不能等于" + desc2 + "！");
					if( ele1Info.object.focus() ) ele1Info.object.focus();
					return false;
				}
				break;
			case ">":
				if( ele1Value <= ele2Value ) {
					alert(desc1 + "(" + modeDesc + ")必须大于" + desc2 + "！");
					if( ele1Info.object.focus() ) ele1Info.object.focus();
					return false;
				}
				break;
			case ">=":
				if( ele1Value < ele2Value ) {
					alert(desc1 + "(" + modeDesc + ")必须大于或等于" + desc2 + "！");
					if( ele1Info.object.focus() ) ele1Info.object.focus();
					return false;
				}
				break;
			case "<":
				if( ele1Value >= ele2Value ) {
					alert(desc1 + "(" + modeDesc + ")必须小于" + desc2 + "！");
					if( ele1Info.object.focus() ) ele1Info.object.focus();
					return false;
				}
				break;
			case "<=":
				if( ele1Value > ele2Value ) {
					alert(desc1 + "(" + modeDesc + ")必须小于或等于" + desc2 + "！");
					if( ele1Info.object.focus() ) ele1Info.object.focus();
					return false;
				}
				break;
			default:
				alert("无效的比较符" + ope[i] + "。\n比较符只能为==,!=,>,>=,<,<=之一！");
				break;
		}//end switch ope
	}
	return true;
}

//判断一组文件表单域的值是否符合指定的文件后缀名
//@param arrEleExt 数组，arrEleExt["ele"]为表单域名，arrEleExt["ext"]为允许的扩展名

function isAllowedExt( arrEleExt ) {
	
	return estimateExt( arrEleExt, false );

}
//判断一组文件表单域的值是否为不允许的文件后缀名
//@param arrEleExt 数组，arrEleExt["ele"]为表单域名，arrEleExt["ext"]为允许的扩展名

function isDeniedExt( arrEleExt ) {
	
	return estimateExt( arrEleExt, true );

}
//判断一组文件表单域选择的文件的后缀名
//@param arrEleExt 数组，arrEleExt["ele"]为表单域名，arrEleExt["ext"]为扩展名集合
//@isDenied 是否为不允许的扩展名。如果表单域的文件名不在指定的扩展名之中，则设为true，否则设为false。

function estimateExt( arrEleExt, isDenied) {
	var arrEle = arrEleExt["ele"];
	var arrExt = arrEleExt["ext"];
	var msg = "";
	if( isDenied ) {
		msg = "选择了不允许的文件格式！\n不允许的文件后缀名包括:";
	} else {
		msg = "选择了不允许的文件格式！\n有效的文件后缀名包括:";
	}
	for( var i = 0;i < arrEle.length;i ++ ) {
		var aInfo = getObjInfo(arrEle[i]);
		if( aInfo.value.length == 0 || aInfo.value[0].length == 0 ) continue;
		if( doFilter( aInfo.value[0].toLowerCase(), arrExt[i].toLowerCase() ) == isDenied ) {
			alert(aInfo.desc + msg + arrExt[i]);
			if( aInfo.object.focus() ) aInfo.object.focus();
			return false;
		}
	}
	return true;
}
//过滤
function doFilter(input, filter){
	return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
}

// ---------------------- 得到某个表单域的相关信息函数 -------------------------//
/**
 * 得到对象的标签名、类型、值、描述以及对象本身。
 * @return 返回一个数组，共无个元素：a.tagName,a.type,a.value,a.desc,a.object。
 * 其中a.tagName和a.type的值为小写，a.value为一个数组，如果对象值为空，则该数组大小为0,
 */
function getObjInfo(ele) {
	
	if( typeof(ele) == "string" )
		ele = trim(ele);
	var aInfo = new Array(5);
	aInfo.tagName = "";
	aInfo.type = "";
	aInfo.value = new Array();
	aInfo.desc = "";
	aInfo.object = null;

	var obj = getObject(ele);
	
	if( !obj ) return false;

	aInfo.object = obj;
	
	//得到描述文本
	var objDesc = document.getElementById(ele + "_tip");
	if( objDesc && objDesc.innerText ) {
		aInfo.desc = objDesc.innerText;
	}

	aInfo.tagName = (obj.tagName).toLowerCase();
	if( obj.type ) aInfo.type = (obj.type).toLowerCase();

	var objs = getObjects(ele);

	//多个对象，只考虑为radio，checkbox。
	if( objs.length > 1 ) {
		
		if( aInfo.type == "radio" || aInfo.type == "checkbox" ) {
			aInfo.value = getRadioBoxValue(objs);
		}

	} else if( obj.value ) {
		aInfo.value = new Array( trim(obj.value) );
	}

	return aInfo;

}//end function getObjInfo(ele)

//判断指定的字符串是否为一个表单域对象
function isObject(s) {
	if(typeof(s) == "object") return true;
	s = trim(s);
	if( document.getElementById(s) && (typeof( document.getElementById(s) )).toLowerCase() == "object" ) {
		return true;
	}
	return false;
}
//由表单域id/name/object得到object
//如果无法获取对象，则返回false。
function getObject(ele) {
	if( ele && (typeof(ele)).toLowerCase() == "object" ) {
		return ele;
	}
	var obj = document.getElementById(ele);
	//alert(obj);
	if( !obj || (typeof(obj)).toLowerCase() != "object" ) {
		alert("在使用验证器时指定了不存在的对象：" + ele + "！");
		throw new Error("在使用验证器时指定了不存在的对象：" + ele + "！[in function getObject(ele)]");
		return false;
	}
	return obj;
}
//由表单域name/objects得到一组对象
//如果无法获取对象，则返回false。
function getObjects(ele) {
	if( ele && (typeof(ele)).toLowerCase() == "object" && ele.length) {
		return ele;
	}
	var objs = document.getElementsByName(ele);
	//alert(objs);
	if( !objs || (typeof(objs)).toLowerCase() != "object" ) {
		alert("在使用验证器时指定了不存在的对象：" + ele + "！");
		throw new Error("在使用验证器时指定了不存在的对象：" + ele + "！[in function getObjects(ele)]");
		return false;
	}
	return objs;
}

/**
 * 得到一组radio或checkbox被选中项的值
 * @param arrObj 一组radio或checkbox对象。
 * @return 一个数组。如果没有被选中项则返回空数组。
 */
function getRadioBoxValue( arrObj ) {
	var len = arrObj.length;
	var aValue = new Array();
	if(typeof(len) == "undefined" && arrObj.checked) {
		//考虑只有一个radio或checkbox的情况
	    return new Array(arrObj.value);
	}
	for(var i = 0 ; i < len ; i ++) {
		if(arrObj[i].checked) {
			aValue[aValue.length] = arrObj[i].value;
		}
	}
	return aValue;
}//end function getRadioBoxValue(arrObj)

//去除空格
function trim(str) {
	str = str.toString();
	return str.replace(/(^\s*)|(\s*$)/g,"");
}