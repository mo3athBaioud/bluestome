
/**
 * 获取panel下XType={textfield, numberfield, textarea, combo, checkbox}的所有属性
 * @param Ext.Panel
 * @return Ext.Component[]{}
 */
function getComps(panel) {
	var components = new Array();
	var hiddenArray = panel.findByType('hidden', true);
	for (var i = 0; i < hiddenArray.length; i++) {
		components.push(hiddenArray[i]);
	}
	var textfieldArray = panel.findByType('textfield', true);
	for (var i = 0; i < textfieldArray.length; i++) {
		components.push(textfieldArray[i]);
	}
	var numberfieldArray = panel.findByType('numberfield', true);
	for (var i = 0; i < numberfieldArray.length; i++) {
		components.push(numberfieldArray[i]);
	}
	var textareaArray = panel.findByType('textarea', true);
	for (var i = 0; i < textareaArray.length; i++) {
		components.push(textareaArray[i]);
	}
	var comboboxArray = panel.findByType('combo', true);
	for (var i = 0; i < comboboxArray.length; i++) {
		components.push(comboboxArray[i]);
	}
	var checkboxArray = panel.findByType('checkbox', true);
	for (var i = 0; i < checkboxArray.length; i++) {
		components.push(checkboxArray[i]);
	}
	var triggerArray = panel.findByType('trigger', true);
	for (var i = 0; i < triggerArray.length; i++) {
		components.push(triggerArray[i]);
	}
	var dateArray = panel.findByType('datefield',true);
	for (var i = 0; i < dateArray.length; i++) {
		components.push(dateArray[i]);
	}
	return components;
}

/**
 * 获取panel下XType={textfield, numberfield, textarea, combo, checkbox}的名称为name的属性
 * @param Ext.Panel
 * @return Ext.Component
 */
function getComp(panel, name) {
	var components = getComps(panel);
	for (var i = 0; i < components.length; i++) {
		if (components[i].getName() == name) {
			return components[i];
		}
	}
	return null;
}
/**
 * 设置panel下XType={textfield, numberfield, textarea, combo, checkbox}的所有属性的值, 根据属性名称从Record对象中取值
 * @param Ext.Panel
 * @param Ext.data.Record
 */
function setCompsValue(panel, record) {
	var components = getComps(panel);
	for (var i = 0; i < components.length; i++) {
		components[i].setValue(record.get(components[i].getName()));
	}
}

/**
 * 清空panel下XType={textfield, numberfield, textarea, combo, checkbox}的所有属性的值
 * @param Ext.Panel
 */
function clearCompsValue(panel) {
	var components = getComps(panel);
	for (var i = 0; i < components.length; i++) {
		if (components[i].getXType() == "checkbox") {
			components[i].setValue("0");
		} else {
			components[i].setRawValue("");
		}
	}
}

/**
 * 获取panel下XType={textfield, numberfield, textarea, combo, checkbox}的所有属性的名称与值，封装在一个对象内
 * @param Ext.Panel
 * @return Object
 */
function getComps2Object(panel) {
	var obj = new Object();
	var components = getComps(panel);
	for (var i = 0; i < components.length; i++) {
		obj[components[i].getName()] = components[i].getValue();
	}
	return obj;
}
/**
 * 获取panel下XType={textfield, numberfield, textarea, combo, checkbox}的所有属性的名称与值，组装成URL参数格式
 * @param Ext.Panel
 * @return Object
 */
function getComps2URLQuery(panel) {
	var obj = "";
	var components = getComps(panel);
	for (var i = 0; i < components.length; i++) {
		obj[components[i].getName()] = components[i].getValue();
		//escape();
		obj += (components[i].getName()+"="+encodeURIComponent(components[i].getValue()));
		if(i<components.length-1){
			obj += "&";
		}
	}
	return obj;
}

/**
 * 校验panel下XType={textfield, numberfield, textarea, combo, checkbox}的所有属性, 调用其validate()方法
 * @param Ext.Panel
 * @return Boolean
 */
function validateComps(panel) {
	var components = getComps(panel);
	var index = 0;
	for (var i = 0; i < components.length; i++) {
		if (!components[i].validate()) {
			++index;
		}
	}
	return index == 0;
}
