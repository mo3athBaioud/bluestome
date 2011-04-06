var BoxWidth = 900 ;   // 资料表显示宽度 ( 不含滚动条)
var ShowLine = 20 ;   // 资料表显示列数
var RsHeight = 20 ;   // 资料列高度
var LockCols = 3  ;  // 要锁定的栏位数 ( 由左至右 )
var RowColor = "white";
var RowAlternativeColor = "#DDE5FC";
var RowMouseOverColor = "LightSteelBlue";
var RowSelectedColor = "CornflowerBlue";
var contentWidth=62; /*一列的宽度*/
var flag=false;


//记录每次右击的列的索引。
var columnRC = 0

//记录被单击过的行的索引。
var rowC = -1;
var rowC2 = 1;

/* 判断表头是横列还是竖立*/
var rowTitle = true;

/*重新设置表格颜色*/
function SetRowColor(sRowIndex, sColor,type)
{
if(rowTitle){
	for(i=0;i<leftTable.rows[sRowIndex].cells.length;i++)
		leftTable.rows[sRowIndex].cells[i].style.backgroundColor = sColor;

	for(i=0;i<rightTable.rows[sRowIndex].cells.length;i++)
		rightTable.rows[sRowIndex].cells[i].style.backgroundColor = sColor;
}else{	
		for(i=0;i<topTable.rows[sRowIndex].cells.length;i++)
			topTable.rows[sRowIndex].cells[i].style.backgroundColor = sColor;
	

}	
		
}

function ResetRowColor(sRowIndex)
{
	var sColor = null;
	if (sRowIndex % 2 ==0)
		sColor = RowColor;
	else
		sColor = RowAlternativeColor;
		
	SetRowColor(sRowIndex, sColor);
}


function compareString(str1,str2){
	return str1.localeCompare(str2);
}

function SortTableAsc()
{
	if(!rowTitle){
		columnRC=rowC2;
	}
	var cTitle=DataTitles[columnRC].split("#");
	switch(cTitle[3])
	{
		case "文本" :
		        var tempArray = new Array();
		        for(i=1;i<DataFields.length;i++){		        	
		        	for(j=i+1;j<DataFields.length;j++){
					var tempString = compareString(DataFields[i][columnRC].toString(),DataFields[j][columnRC].toString());
					if( tempString == 1){
					    /*交换数据*/
					    for(h=0;h<DataFields[i].length;h++)
					    {
						tempArray[h] = DataFields[i][h];
						DataFields[i][h] = DataFields[j][h];
						DataFields[j][h] = tempArray[h];
					    }
					}
				}
								
		        }
			if(rowTitle){
				ApplyData();
			}else{
				changeApplyData();
			}
			
			break;
		case "数值" :
			var minIndex = 0;
			var tempArray = new Array();
			for(i=1;i<DataFields.length;i++)
			{
				minIndex = i;
				var leftMin = parseFloat(DataFields[i][columnRC]);
				for(j=i+1;j<DataFields.length;j++)
				{
					if (leftMin>parseFloat(DataFields[j][columnRC]))
					{
						leftMin = parseFloat(DataFields[j][columnRC]);
						minIndex = j;
					}
				}
				if (minIndex != i)
				{
					for(h=0;h<DataFields[i].length;h++)
					{
						tempArray[h] = DataFields[i][h];
						DataFields[i][h] = DataFields[minIndex][h];
						DataFields[minIndex][h] = tempArray[h];
					}
				}
			}
			if(rowTitle){
				ApplyData();
			}else{
				changeApplyData();
			}
			break;
		case "日期" :
			var minIndex = 0;
			var tempArray = new Array();
			for(i=0;i<DataFields.length;i++)
			{
				//日期必须是yyyy-mm-dd格式的。
				minIndex = i;
				var leftMin = DataFields[i][columnRC].split("-");
				for(j=i+1;j<DataFields.length;j++)
				{
					var currentDate = DataFields[j][columnRC].split("-");
					if ( ( parseInt(leftMin[0]) > parseInt(currentDate[0]) ) || ( ( leftMin[0] == currentDate[0] ) && ( parseInt(leftMin[1]) > parseInt(currentDate[1]) ) ) || ( ( leftMin[0] == currentDate[0] ) && ( leftMin[1] == currentDate[1] ) && ( parseInt(leftMin[2]) > parseInt(currentDate[2]) ) ) )
					{
						leftMin[0] = currentDate[0];
						leftMin[1] = currentDate[1];
						leftMin[2] = currentDate[2];
						minIndex = j;
					}
				}
				if (minIndex != i)
				{
					for(h=0;h<DataFields[i].length;h++)
					{
						tempArray[h] = DataFields[i][h];
						DataFields[i][h] = DataFields[minIndex][h];
						DataFields[minIndex][h] = tempArray[h];
					}
				}
			}
			if(rowTitle){
				ApplyData();
			}else{
				changeApplyData();
			}
			break;
	}
}

function SortTableDesc()
{
	if(!rowTitle){
		columnRC=rowC2;
	}
	var cTitle=DataTitles[columnRC].split("#");
	switch(cTitle[3])
	{
		case "文本" :
			var tempArray = new Array();
		        for(i=1;i<DataFields.length;i++){		        	
		        	for(j=i+1;j<DataFields.length;j++){
					var tempString = compareString(DataFields[i][columnRC].toString(),DataFields[j][columnRC].toString());
					if( tempString == -1){
					    /*交换数据*/
					    for(h=0;h<DataFields[i].length;h++)
					    {
						tempArray[h] = DataFields[i][h];
						DataFields[i][h] = DataFields[j][h];
						DataFields[j][h] = tempArray[h];
					    }
					}
				}
								
		        }
			if(rowTitle){
				ApplyData();
			}else{
				changeApplyData();
			}
			break;
		case "数值" :
			var minIndex = 0;
			var tempArray = new Array();
			for(i=1;i<DataFields.length;i++)
			{
				minIndex = i;
				var leftMin = parseFloat(DataFields[i][columnRC]);
				for(j=i+1;j<DataFields.length;j++)
				{
					if (leftMin<parseFloat(DataFields[j][columnRC]))
					{
						leftMin = parseFloat(DataFields[j][columnRC]);
						minIndex = j;
					}
				}
				if (minIndex != i)
				{
					for(h=0;h<DataFields[i].length;h++)
					{
						tempArray[h] = DataFields[i][h];
						DataFields[i][h] = DataFields[minIndex][h];
						DataFields[minIndex][h] = tempArray[h];
					}
				}
			}
			if(rowTitle){
				ApplyData();
			}else{
				changeApplyData();
			}
			break;
		case "日期" :
			var minIndex = 0;
			var tempArray = new Array();
			for(i=0;i<DataFields.length;i++)
			{
				//日期必须是yyyy-mm-dd格式的。
				minIndex = i;
				var leftMin = DataFields[i][columnRC].split("-");
				for(j=i+1;j<DataFields.length;j++)
				{
					var currentDate = DataFields[j][columnRC].split("-");
					if ( ( parseInt(leftMin[0]) < parseInt(currentDate[0]) ) || ( ( leftMin[0] == currentDate[0] ) && ( parseInt(leftMin[1]) < parseInt(currentDate[1]) ) ) || ( ( leftMin[0] == currentDate[0] ) && ( leftMin[1] == currentDate[1] ) && ( parseInt(leftMin[2]) < parseInt(currentDate[2]) ) ) )
					{
						leftMin[0] = currentDate[0];
						leftMin[1] = currentDate[1];
						leftMin[2] = currentDate[2];
						minIndex = j;
					}
				}
				if (minIndex != i)
				{
					for(h=0;h<DataFields[i].length;h++)
					{
						tempArray[h] = DataFields[i][h];
						DataFields[i][h] = DataFields[minIndex][h];
						DataFields[minIndex][h] = tempArray[h];
					}
				}
			}
			if(rowTitle){
				ApplyData();
			}else{
				changeApplyData();
			}
			break;
	}
}

/*判断*/
var table_width=0

function WriteTable(){    // 写入表格
rowTitle = true;
var iBoxWidth=BoxWidth;
var NewHTML="<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>"
if(table_width<=BoxWidth){
	NewHTML += "<div style=\"width:100%;overflow-x:scroll\">"
}else{
	NewHTML += "<div style=\"width:100%;overflow-x:scroll\">"
}

NewHTML += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"leftHead\"><tr>"
for(i=0;i<DataTitles.length;i++){
  if(i<LockCols){
    var cTitle=DataTitles[i].split("#")
    iBoxWidth-=cTitle[1]
    NewHTML+="<th onClick=\"columnRC="+i+";orderChange()\"><div class=\"title\" style=\"width:"+cTitle[1]+"px;height:"+RsHeight+"px\"  align=\"" + cTitle[2] + "\" oncontextmenu=\"HeadRightClick()\">"+cTitle[0]+"</div></th>"
  }
}
NewHTML+="</tr>\
<tr><td colspan=\""+LockCols+"\">\
<div id=\"DataFrame1\" style=\"position:relative;width:100%;overflow:hidden\">\
<div id=\"DataGroup1\" style=\"position:relative\"></div></div>\
</td></tr></table></div></td>"





if(table_width<=BoxWidth){
	NewHTML +="<td valign=\"top\"><div style=\"width:"+iBoxWidth+"px;overflow-x:scroll\">"
}else{
	NewHTML +="<td valign=\"top\"><div style=\"width:"+iBoxWidth+"px;overflow-x:scroll\">"
}
	
NewHTML +="<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"rightHead\"><tr>"

for(i=0;i<DataTitles.length;i++){
  if(i>=LockCols){
    var cTitle=DataTitles[i].split("#")
    NewHTML+="<th onClick=\"columnRC="+i+";orderChange()\"><div class=\"title\" style=\"width:"+cTitle[1]+"px;height:"+RsHeight+"px\"  align=\"" + cTitle[2] + "\" oncontextmenu=\"HeadRightClick()\">"+cTitle[0]+"</div></th>"
  }
}
NewHTML+="</tr>\
<tr><td colspan=\""+(DataTitles.length-LockCols)+"\">\
<div id=\"DataFrame2\" style=\"position:relative;width:100%;overflow:hidden\">\
<div id=\"DataGroup2\" style=\"position:relative\"></div>\
</div></td></tr></table>\
</div></td><td valign=\"top\">\
<div id=\"DataFrame3\" style=\"position:relative;background:#f5f5f5;overflow-y:scroll\" onscroll=\"SYNC_Roll()\"  onMouseWheel=\"SYNC_Roll()\">\
<div id=\"DataGroup3\" style=\"position:relative;width:1px;visibility:hidden\"></div>\
</div></td></tr></table>"

DataTable.innerHTML=NewHTML
ApplyData()
}
function ApplyData(){    // 写入资料
var NewHTML="<table id=\"leftTable\" onMouseOver=\"overcolor()\" onClick=\"clickColor()\" onMouseOut=\"outcolor()\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
for(i=0;i<DataFields.length;i++){
if (i %2 == 0)
  NewHTML+="<tr bgcolor=\"" + RowColor + "\">"
else
  NewHTML+="<tr bgcolor=\"" + RowAlternativeColor + "\">"

  for(j=0;j<DataTitles.length;j++){
    if(j<LockCols){
      var cTitle=DataTitles[j].split("#")
      NewHTML+="<td><div class=\"cdata\" style=\"width:"+cTitle[1]+"px;height:"+RsHeight+"px;text-align:"+cTitle[2]+"\">"+DataFields[i][j]+"</div></td>"
    }
  }
  NewHTML+="</tr>"
}

//填充空格
	if(DataFields.length<20)
	{
		for(i=DataFields.length;i<20;i++)
		{
			if (i %2 == 0)
  				NewHTML+="<tr bgcolor=\"" + RowColor + "\">"
			else
  				NewHTML+="<tr bgcolor=\"" + RowAlternativeColor + "\">"
		
			for(j=0;j<DataTitles.length;j++){
				if(j<LockCols){
				var cTitle=DataTitles[j].split("#")
				 NewHTML+="<td><div class=\"cdata\" style=\"width:"+cTitle[1]+"px;height:"+RsHeight+"px;text-align:"+cTitle[2]+"\"></div></td>"
				}
			}
				NewHTML +="</tr>";
		}
	}

NewHTML+="</table>"
DataGroup1.innerHTML=NewHTML

var NewHTML="<table id=\"rightTable\" onMouseOver=\"overcolor()\" onClick=\"clickColor()\" onMouseOut=\"outcolor()\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
for(i=0;i<DataFields.length;i++){
if (i %2 == 0)
  NewHTML+="<tr bgcolor=\"" + RowColor + "\">"
else
  NewHTML+="<tr bgcolor=\"" + RowAlternativeColor + "\">"
  for(j=0;j<DataTitles.length;j++){
    if(j>=LockCols){
      var cTitle=DataTitles[j].split("#")
      NewHTML+="<td><div class=\"cdata\" style=\"width:"+cTitle[1]+"px;height:"+RsHeight+"px;text-align:"+cTitle[2]+"\">"+DataFields[i][j]+"</div></td>"
    }
  }
  NewHTML+="</tr>"
}

//填充空格
if(DataFields.length<20)
	{
		for(i=DataFields.length;i<20;i++)
		{
			if (i %2 == 0)
  				NewHTML+="<tr bgcolor=\"" + RowColor + "\">"
			else
  				NewHTML+="<tr bgcolor=\"" + RowAlternativeColor + "\">"
		
			for(j=0;j<DataTitles.length;j++){
				if(j>=LockCols){
				var cTitle=DataTitles[j].split("#")
				 NewHTML+="<td><div class=\"cdata\" style=\"width:"+cTitle[1]+"px;height:"+RsHeight+"px;text-align:"+cTitle[2]+"\"></div></td>"
				}
			}
				NewHTML +="</tr>";
		}
	}

NewHTML+="</table>"
DataGroup2.innerHTML=NewHTML
DataFrame1.style.pixelHeight=RsHeight*ShowLine
DataFrame2.style.pixelHeight=RsHeight*ShowLine
DataFrame3.style.pixelHeight=RsHeight*ShowLine+RsHeight
DataGroup3.style.pixelHeight=RsHeight*(DataFields.length+1)

}

function ResetTable(){

var iBoxWidth=0
for(i=0;i<DataTitles.length;i++){
  if(i<(columnRC+1)){
    var cTitle=DataTitles[i].split("#")
    iBoxWidth+=parseInt(cTitle[1])
  }
}

for(i=0;i<DataTitles.length;i++){
	var cTitle=DataTitles[i].split("#")
	table_width+=parseInt(cTitle[1])
}

if(iBoxWidth>BoxWidth){
  var Sure=confirm("\n锁定栏位的宽度大於资料表显示的宽　　\n\n度，这可能会造成版面显示不正常。\n\n\n您确定要继续吗？")
}else{
  Sure=true
}
if(Sure){
  LockCols=(LockCols==columnRC+1)?0:columnRC+1
  WriteTable()
}
  table_width=0;
}



function SYNC_Roll(){
DataGroup1.style.posTop=-DataFrame3.scrollTop
DataGroup2.style.posTop=-DataFrame3.scrollTop
}

function clickColor()
{
	try
	{
    var oEl = event.srcElement;
	if (oEl)
    if (oEl.tagName == "DIV")
    {
        try
        {
            oEl = oEl.parentElement.parentElement;
        }
        catch (e)
        {
            return;
        }
    }
    if (oEl.tagName == "TD")
        oEl = oEl.parentElement;

	var sindex = oEl.rowIndex;
	
	SetRowColor(sindex, RowSelectedColor);
	
	if (rowC >= 0)	
		if (rowC != sindex)
			ResetRowColor(rowC);
			
	rowC = sindex;
	}
	catch (e)
	{}
}

function overcolor()
{
    var oEl = event.srcElement;
	if (oEl)
    if (oEl.tagName == "DIV")
    {
        try
        {
            oEl = oEl.parentElement.parentElement;
        }
        catch (e)
        {
            return;
        }
    }
    if (oEl.tagName == "TD")
        oEl = oEl.parentElement;

	var sindex = oEl.rowIndex;
	
	if (rowC >= 0)
		if (sindex == rowC)
			return;
	
	SetRowColor(sindex, RowMouseOverColor);
}

function outcolor()
{
    var oEl = event.srcElement;
	if (oEl)
    if (oEl.tagName == "DIV")
    {
        try
        {
            oEl = oEl.parentElement.parentElement;
        }
        catch (e)
        {
            return;
        }
    }
    if (oEl.tagName == "TD")
        oEl = oEl.parentElement;

	var sindex = oEl.rowIndex;
		if (rowC >= 0)
		if (sindex == rowC)
			return;
			
	ResetRowColor(sindex);	
}

function HeadRightClick()
{
	var ev = window.event;
	var el = ev.srcElement;
	if (el.tagName == "DIV")
	{
		showMenu(ev.clientX, ev.clientY);
		ev.cancelBubble = true;
		ev.returnValue = false;
		var ep = el.parentElement;
		columnRC = ep.cellIndex;
		ep = el.parentElement.parentElement.parentElement.parentElement;
		if (ep.tagName == "TABLE")
		{
			if (ep.id == "rightHead")
				columnRC += LockCols;
		}
	}
}

function HeadRightClick2()
{
	var ev = window.event;
	var el = ev.srcElement;
	if (el.tagName == "DIV")
	{
		showMenu2(ev.clientX, ev.clientY);
		ev.cancelBubble = true;
		ev.returnValue = false;
		
		var ep = el.parentElement.parentElement;
		rowC2 = ep.rowIndex;		
	}
}

function showMenu(x, y)
{
    var intRightEdge = window.document.body.clientWidth - x;
    var intBottomEdge = window.document.body.clientHeight - y;
    var intScrollLeft = window.document.body.scrollLeft + x;
    var intScrollTop = window.document.body.scrollTop + y;

    if (intRightEdge < objHeadMenu.offsetWidth)
        objHeadMenu.style.left = intScrollLeft - objHeadMenu.offsetWidth;
    else
        objHeadMenu.style.left = intScrollLeft;

    if (intBottomEdge < objHeadMenu.offsetHeight)
        objHeadMenu.style.top = intScrollTop - objHeadMenu.offsetHeight;
    else
        objHeadMenu.style.top = intScrollTop;

    objHeadMenu.style.zIndex = 50;
	
	objHeadMenu.style.filter = "blendTrans(duration=0.50) progid:DXImageTransform.Microsoft.Shadow(color=#323232, direction=135, strength=3)";
	
	if (objHeadMenu.filters.blendTrans.status != 2)
	{
		objHeadMenu.filters.blendTrans.apply();
		objHeadMenu.style.visibility = "visible";
		objHeadMenu.filters.blendTrans.play();
	}
}

function showMenu2(x, y)
{
    var intRightEdge = window.document.body.clientWidth - x;
    var intBottomEdge = window.document.body.clientHeight - y;
    var intScrollLeft = window.document.body.scrollLeft + x;
    var intScrollTop = window.document.body.scrollTop + y;

    if (intRightEdge < objHeadMenu2.offsetWidth)
        objHeadMenu2.style.left = intScrollLeft - objHeadMenu2.offsetWidth;
    else
        objHeadMenu2.style.left = intScrollLeft;

    if (intBottomEdge < objHeadMenu.offsetHeight)
        objHeadMenu2.style.top = intScrollTop - objHeadMenu2.offsetHeight;
    else
        objHeadMenu2.style.top = intScrollTop;

    objHeadMenu2.style.zIndex = 50;
	
	objHeadMenu2.style.filter = "blendTrans(duration=0.50) progid:DXImageTransform.Microsoft.Shadow(color=#323232, direction=135, strength=3)";
	
	if (objHeadMenu2.filters.blendTrans.status != 2)
	{
		objHeadMenu2.filters.blendTrans.apply();
		objHeadMenu2.style.visibility = "visible";
		objHeadMenu2.filters.blendTrans.play();
	}
}

function hideMenu()
{
	objHeadMenu.style.filter = "blendTrans(duration=0.50) progid:DXImageTransform.Microsoft.Shadow(color=#323232, direction=135, strength=3)";
	if (objHeadMenu.filters.blendTrans.status != 2)
	{
		objHeadMenu.filters.blendTrans.apply();
		objHeadMenu.style.visibility = "hidden";
		objHeadMenu.filters.blendTrans.play();
	}
	
	objHeadMenu2.style.filter = "blendTrans(duration=0.50) progid:DXImageTransform.Microsoft.Shadow(color=#323232, direction=135, strength=3)";
	if (objHeadMenu2.filters.blendTrans.status != 2)
	{
		objHeadMenu2.filters.blendTrans.apply();
		objHeadMenu2.style.visibility = "hidden";
		objHeadMenu2.filters.blendTrans.play();
	}
}



//加列宽
function SetCWP(valWidth)
{
	var cTitle = DataTitles[columnRC].split("#");
	var cWidth = parseInt(cTitle[1]) + parseInt(valWidth);
	DataTitles[columnRC] = cTitle[0] + "#" + cWidth + "#" + cTitle[2] + "#" + cTitle[3];

	for(i=0;i<DataTitles.length;i++){
		var cTitle=DataTitles[i].split("#")
		table_width+=parseInt(cTitle[1])
	}
	WriteTable();
	table_width = 0;
}

//减列宽
function SetCWM(valWidth)
{
	var cTitle = DataTitles[columnRC].split("#");
	var cWidth = parseInt(cTitle[1]) - parseInt(valWidth);
	if (cWidth <= 0)	return;
	DataTitles[columnRC] = cTitle[0] + "#" + cWidth + "#" + cTitle[2] + "#" + cTitle[3];
	for(i=0;i<DataTitles.length;i++){
		var cTitle=DataTitles[i].split("#")
		table_width+=parseInt(cTitle[1])
	}
	WriteTable();
	table_width = 0;
}

/*
	右键菜单上的两个调整列宽的文本框的KeyDown事件，用于捕捉"Enter"时调整列宽并隐藏菜单
*/
function CWKeyDown()
{
	if (event.keyCode == 13)
	{
	var oEl = event.srcElement;
	if (oEl.id == "txtCWPlus")
		SetCWP(txtCWPlus.value);
		
	if (oEl.id == "txtCWMinus")
		SetCWM(txtCWMinus.value);
	
	hideMenu();
	}
}


function createMenu()
{
		var txtHTML = "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"SortTableAsc();hideMenu();\"><img src=\"../pages/images/asc.gif\" width=\"11\" height=\"10\">按升序排序</div>";
		txtHTML	+= "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"SortTableDesc();hideMenu();\"><img src=\"../pages/images/desc.gif\" width=\"11\" height=\"10\">按降序排序</div>";
		
			txtHTML +="<div class=\"coolMenuDivider\"></div>";
			txtHTML += "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"ResetTable();hideMenu();\"><img src=\"../pages/images/blank.gif\" width=\"12\" height=\"12\">锁定列</div>";
		
		txtHTML += "<div class=\"coolMenuDivider\"></div>";
		txtHTML += "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"ChangeTable();hideMenu();\"><img src=\"../pages/images/change.gif\" width=\"12\" height=\"11\">行列转置</div>";
		txtHTML += "<div class=\"coolMenuDivider\"></div>";
		txtHTML	+= "<div class=\"coolMenuItem\" style='padding-top: 1px;' onClick=\"SetCWP(txtCWPlus.value);\" onMouseOver=\"txtCWPlus.focus();\"><img src=\"../pages/images/blank.gif\" width=\"12\" height=\"12\">列宽 + <input type=\"text\" id=\"txtCWPlus\" class=\"sinput\" value=\"20\" onKeyDown=\"CWKeyDown();\"> px</div>";
		txtHTML	+= "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"SetCWM(txtCWMinus.value);\" onMouseOver=\"txtCWMinus.focus()\"><img src=\"../pages/images/blank.gif\" width=\"12\" height=\"12\">列宽 - <input type=\"text\" id=\"txtCWMinus\" class=\"sinput\" value=\"20\" onKeyDown=\"CWKeyDown();\"> px</div>";
		
		
    	objHeadMenu.className = "coolMenu";
	    objHeadMenu.innerHTML = txtHTML;
    	window.document.body.insertAdjacentElement("afterBegin", objHeadMenu);
}

function createMenu2()
{
		var txtHTML = "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"SortTableAsc();hideMenu();\"><img src=\"../pages/images/asc.gif\" width=\"11\" height=\"10\">按升序排序</div>";
		txtHTML	+= "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"SortTableDesc();hideMenu();\"><img src=\"../pages/images/desc.gif\" width=\"11\" height=\"10\">按降序排序</div>";
		
			
		txtHTML += "<div class=\"coolMenuDivider\"></div>";
		txtHTML += "<div class=\"coolMenuItem\" style='padding-top: 1px;' onclick=\"WriteTable();hideMenu();\"><img src=\"../pages/images/change.gif\" width=\"12\" height=\"11\">行列转置</div>";
		
		
    	objHeadMenu2.className = "coolMenu";
	    objHeadMenu2.innerHTML = txtHTML;
    	window.document.body.insertAdjacentElement("afterBegin", objHeadMenu2);
}





/*表头最大宽度*/
var maxTitle = 0;
/*行高度*/
var contendHeight=25;

function ChangeTable()
{
	createMenu();
	rowTitle = false;
	getMaxWidth();
	var NewHTML = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
	
/*第1行*/
	NewHTML +="<tr><td>";
	/*加入一个框架div*/
	NewHTML +="<div id=\"ChangeFrame3\" style=\"position:relative;background:#FFFFFF;overflow-x:scroll\" onscroll=\"CHANGE_SYNC_Roll()\"><div id=\"ChangeData3\" style=\"position:relative\">";
		/*加入一个表格*/
	NewHTML +="<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
	    /*加入表头*/
	var titleNum = DataTitles.length;
	
	for(i=0;i<titleNum;i++)
	{
			var cTitle=DataTitles[i].split("#");
			NewHTML +="<tr>";
			NewHTML +="<th><div class=\"title\" style=\"width:"+80+"px;height:"+contendHeight+"px\"  align=\"" + cTitle[2] + "\" oncontextmenu=\"HeadRightClick2()\">"+cTitle[0]+"</div></th>";
			/* 添加数据div */
			if(i==0)
			{
					NewHTML += "<td  valign=\"top\" rowspan=\""+ titleNum +"\"><div id=\"ChangeFrame1\" style=\"position:relative;overflow:hidden\"><div id=\"ChangeData1\" style=\"position:relative\"></div></div></td>";
			}
			NewHTML +="</tr>";
		
	}
		
	NewHTML +="</table>"
	NewHTML +="</div></div>";
	NewHTML +="</td></tr>";
	
	
	
	
	
	NewHTML +="</table>";
	
	
	
	
	DataTable.innerHTML = NewHTML;
	changeApplyData();
}

/*转置后添加数据*/
function changeApplyData()
{
	var tableWidth = 80*DataFields.length;
	var NewHTML="<table  onMouseOver=\"overcolor()\" onClick=\"clickColor()\" onMouseOut=\"outcolor()\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"topTable\" width="+tableWidth+">";
	/* add data */
	for(i=0;i<DataTitles.length;i++)
	{	
		
			var cTitle=DataTitles[i].split("#");
			if (i %2 == 0)
  				NewHTML+="<tr bgcolor=\"" + RowColor + "\">";
			else
  				NewHTML+="<tr bgcolor=\"" + RowAlternativeColor + "\">";
			for(j=0;j<DataFields.length;j++)
			{
				
				NewHTML +="<td><div class=\"cdata\" style=\"width:"+80+"px;height:"+contendHeight+"px;text-align:"+cTitle[2]+"\">"+DataFields[j][i]+"</div></td>";
			}
			NewHTML +="</tr>";
		
	}
	NewHTML+="</table>";
	
	ChangeData1.innerHTML = NewHTML;
	
	
	
	
	ChangeFrame1.style.pixelWidth=BoxWidth-80;
	//ChangeFrame2.style.pixelWidth=BoxWidth-80;
	ChangeFrame3.style.pixelWidth=BoxWidth;
	ChangeData3.style.pixelWidth=tableWidth+80;
}

/*取得表头最大宽度*/
function getMaxWidth()
{
	for(i=0;i<DataTitles.length;i++)
	{
		var cTitle=DataTitles[i].split("#");
		if(maxTitle < cTitle[1])
			maxTitle = cTitle[1];
	}
}

/*横的滚动条移动*/
function CHANGE_SYNC_Roll()
{
	ChangeData1.style.posLeft=-ChangeFrame3.scrollLeft;
    ChangeData3.style.posRight=-ChangeFrame3.scrollLeft;
}


function orderChange(){
	if(flag==true){
		SortTableDesc();
		flag=false;
	}
	else{
		SortTableAsc();
		flag=true;
	}
}