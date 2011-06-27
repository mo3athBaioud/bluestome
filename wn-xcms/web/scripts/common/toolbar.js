/**
 * 工具栏脚本
 * 使用方法(请注意查看Toolbar和Button类的属性)：
 * var toolbar = new Toolbar();
 * //设置工具栏按钮一些通用属性
 * toolbar.target = "_self";
 * //构造一个按钮
 * var btn1 = new Button();
 * btn1.text = "按钮1";
 * //将按钮加入工具栏中
 * toolbar.addButton( btn1 );
 *
 * 作者：tiannet（曾次清）
 * 日期：2007年3月15日
 */


function Toolbar() {
	//链接打开的目标窗口，如果链接为一个脚本函数，请指定为javascript
	this.target = "_self";
	//文字相对图片的横向排列
	this.textAlign = "left";
	//文字相对图片的纵向排列
	this.textValign = "middle";
	//文字相对图片的方向，包括east,west,north,south
	this.textPosition = "east";
	//工具栏中按钮的宽度
	this.buttonWidth;
	//工具栏中按钮的高度
	this.buttonHeight;
	//按钮排列方向,hor为横排，ver为纵排
	this.buttonAlign = "hor";
	//按钮之间的间距，单位为象素
	this.buttonSpace = 5;
	//工具栏中的按钮集合
	this.buttons = new Array();
	//图片所在目录
	this.imgFolder = "";

	//添加按钮
	this.addButton = function addButton(button) {
		this.buttons[this.buttons.length] = button;
	}
}//end Toolbar

Toolbar.prototype.write = writeBoolbar;

/**
 *按钮类
 */
function Button() {
	this.text = "";
	this.img;//图片
	this.action;//按钮的动作，它可以是一个地址，也可以是一个JavaScript函数，如果是JavaScript函数，请将target指定为js或javascript
	this.title;//tooltip，如果不指定则为text
	this.target;
	this.textAlign;//文字相对图片的方向
	//文字相对图片的方向，包括east,west,north,south
	this.textPosition;
	this.width;//按钮宽度
	this.height;//按钮高度
	
}//end Button

/**
 * 输出工具栏。属于Toolbar的方法 
 */
function writeBoolbar() {
	var out = new Array();
	//div 显示方式
	var display = ( this.buttonAlign == "hor" ) ? "inline" : "";
	var style = "display:" + display + ";";
	for( var i = 0;i < this.buttons.length;i ++ ) {
		var title = this.buttons[i].title ? this.buttons[i].title : this.buttons[i].text;
		var width = this.buttons[i].width ? this.buttons[i].width : this.buttonWidth;
		if( width ) {
			style += "width:" + width + ";";
		}
		var height = this.buttons[i].height ? this.buttons[i].height : this.buttonHeight;
		if( height ) {
			style += "height:" + height + ";";
		}
		var target = this.buttons[i].target ? this.buttons[i].target : this.target;
		out.push("<table style='" + style + "' class='toolbarButtonDefault' cellspacing='0' cellpadding='0' ")
		out.push(" title = \"" + title + "\" ");
		out.push(" unselectable='on' ");
		out.push(" onmouseover=\"this.className='toolbarButtonMover'\" ");
		out.push(" onmouseout=\"this.className='toolbarButtonDefault'\" ");
		out.push(" onmousedown=\"this.className='toolbarButtonMdown'\" ");
		out.push(" onclick=execClickEvent(\"" + this.buttons[i].action + "\",\"" + target + "\"); ");
		out.push(" > ");//结束table起始标签

		var imgHtml;//显示图片的代码
		//是否有图片
		if( this.buttons[i].img ) {
			var textAlign = this.buttons[i].textAlign? this.buttons[i].textAlign : this.textAlign;
			imgHtml = "<img src=\"" + this.imgFolder + this.buttons[i].img + "\"  />";
		}
		//文字相对图片的位置
		var textPosition = this.buttons[i].textPosition ? this.buttons[i].textPosition : this.textPosition;
		//文本
		var text = this.buttons[i].text;
		switch( textPosition ) {
			case "east":
				//文字在图片的东边
				out.push("<tr>");
				if( imgHtml ) {
					out.push("<td>" + imgHtml + "</td>");
				}
				out.push("<td align='" + this.textAlign + "' valign='" + this.textValign + "' unselectable='on'>" + text + "</td>");
				out.push("</tr>");
				break;
			case "west":
				//文字在图片的西边
				out.push("<tr>");
				out.push("<td align='" + this.textAlign + "' valign='" + this.textValign + "' unselectable='on'>" + text + "</td>");
				if( imgHtml ) {
					out.push("<td>" + imgHtml + "</td>");
				}
				out.push("</tr>");
				break;
			case "south":
				//文字在图片的南边
				if( imgHtml ) {
					out.push("<tr><td align='center'>" + imgHtml + "</td></tr>");
				}
				out.push("<tr><td align='" + this.textAlign + "' valign='" + this.textValign + "' unselectable='on'>" + text + "</td></tr>");
				break;
			case "north":
				//文字在图片的北边
				out.push("<tr><td align='" + this.textAlign + "' valign='" + this.textValign + "' unselectable='on'>" + text + "</td></tr>");
				if( imgHtml ) {
					out.push("<tr><td align='center'>" + imgHtml + "</td></tr>");
				}
				break;
		}//end switch
		
		out.push("</table>");
		//按钮之间的间距
		if( this.buttonSpace > 0 && this.buttonAlign == "hor" ) {
			out.push("<div  style='width:" + this.buttonSpace + "px;display:" + display + "'></div>")
		}
	}//end for
	//alert(out.join(""));
	document.write(out.join(""));
}//end writeBoolbar



/**
 *执行点击事件
 *@param action 链接的动作，也可以是一段javascript代码或函数
 *@param target 页面打开的目标窗口，如果执行的是javascript代码或函数，请将该参数设为javascript或js
 */
function execClickEvent(action,target) {
	if( !action || action == "undefined" || action == "#"  || action == "" )
		return;
	if( target.toLowerCase() == "javascript" || target.toLowerCase() == "js" ) {
		eval(action);
		return;
	}
	window.open(action,target);
	return;
}
