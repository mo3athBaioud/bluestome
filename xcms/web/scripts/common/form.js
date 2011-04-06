
/**
 * 得到form表单中所有域的名称和值对(对于select-one,radio,checkbox取选中项的值，而select-multiple取所有option的值)。
 * @param objForm 表单对象
 * @return 一维数组，每个数组元素的格式为：表单域名称=表单域值(fieldName=fieldValue)。
 * 其中值中包含的特殊符号会被格式化。
 * 例如：一个form中只有一个输入域：<input type="text" name="userId" value="admin">
 * 那么返回的结果数组中只有一个元素，其值为：userId=admin
 * @author bluestome(张晓)
 */
function getFormFieldNameValuePairs(objForm) {
	if( !objForm ) return "";
	var first = true;
	var aStr = new Array();//包含表单域名称域值对的数组
	for( var i = 0;i < objForm.length;i ++ ) {
		var value = getFormObjectValue(objForm[i]);
		//跳过值为空的表单域
		if( !value || value == null || value.length == 0 ) {
			continue;
		}
		//跳过名称为空的表单域
		if( !objForm[i].name && objForm[i].name == "" ) {
			continue;
		}
		
		for( var j = 0;j < value.length;j ++ ) {
			aStr.push( objForm[i].name + "=" + parseValueForAjax(value[j]) );
		}
	}
	return aStr;
}


/**
 * 得到一个表单中某个域名称的值，如果传递的对象是按钮或者无法得到对象，则返回null。
 * @param obj 一个表单（form）域
 * @return 一个表单域的值，它是一个数组。对于select multiple，将提取所有option的值，其它表单域通常只有一个值。
 * radio,checkbox如果没有被选中，则返回null。
 * @author bluestome(张晓)
 */
function getFormObjectValue(obj) {
	if( (typeof(obj)).toLowerCase() != "object" ) {
		return null;
	}
	var strType = null;
	strType = obj.type;
	strType = strType.toLowerCase();
	if( strType == "button" || strType == "submit" || strType == "reset" || strType == "image" ) {
		return null;
	}
	
	if( strType == "select-one" ) {
		var aRet = new Array(1);
		aRet[0] = obj.options[obj.selectedIndex].value;
		return aRet;

	} if( strType == "select-multiple" ) {
		var aRet = new Array(obj.length);
		for( var i = 0;i < aRet.length;i ++ ) {
			aRet[i] = obj.options[i].value;
		}
		return aRet;

	} else if( strType == "checkbox" || strType == "radio" ) {
		var aRet = new Array(1);
		if( obj.checked ) {
			aRet[0] = obj.value;
			return aRet;
		} else {
			return null;
		}
	} else {
		var aRet = new Array(1);
		aRet[0] = obj.value;
		return aRet;
	}
	return null;

}//end function getFormObjectValue(obj)

/**
 * disable一组标签，参数为任意个，每个参数为一个标签名。
 * @author tiannet
 */
function disable() {
	for (var i = 0; i < arguments.length; i++) {
		var obj = $(arguments[i]);
		if( obj ) {
			obj.blur();
			obj.disabled = true;
		}
	}	
}//end function disable

/**
 * enable一组标签，参数为任意个，每个参数为一个标签名。
 * @author tiannet
 */
function enable() {
	for (var i = 0; i < arguments.length; i++) {
		var obj = $(arguments[i]);
		if( obj ) {
			obj.disabled = "";
		}
	}	
}//end function disable

/**
 * 得到一个或一组对象，可以传递若干个html标签名，如果有多个参数则返回一组对象。
 * @author prototype
 */
function $() {
  
	var elements = new Array();

  
	for (var i = 0; i < arguments.length; i++) {
    
		var element = arguments[i];
 
		if (typeof element == 'string')
      
			element = document.getElementById(element);

    
		if (arguments.length == 1) 
      
			return element;

    
		elements.push(element);
  
	}
	
 
	return elements;

}

//去除空格
function trim(str) {
	str = str.toString();
	return str.replace(/(^\s*)|(\s*$)/g,"");
}
//格式化使用ajax传递的值
function parseValueForAjax(value) {
	return trim(value.replace(/\%/g,"%25").replace(/\&/g,"%26"));
}