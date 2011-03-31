/*=========================================================
 *功  能：该javascript主要将一个下拉框里的元素添加到另一个下拉框
 		  以及删除已经添加的元素
 =========================================================*/


/*=========================================================
 *功  能：将一个下拉框里的元素单个添加到另一个下拉框
 *参  数：x：添加的下拉框对象
 		  y：被添加的下拉框对象
 =========================================================*/

function Add_one(x,y) 
{	
	var index = x.selectedIndex;
	if(index<0)
	{
		x.focus ();
		x.selectedIndex = 0;
		return false;
	}
	var obj_len = x.length;
	
	var arr_sele=new Array(50);
	var j=0;
	for(var i=0;i<obj_len;i++)
	{
		if(x.options[i].selected==true)
		{
			
			var new_option=document.createElement("OPTION");
			new_option.text=x.options[i].text;
			new_option.value=x.options[i].value;
			var max_index=y.length;
			y.options[max_index] = new_option;
			arr_sele[j]=i;
			x.options[i].selected = false;
			j++;
		}
	}
	for(i=0;i<j;i++)
	{
		x.options[arr_sele[i]-i] = null;
	}
	x.focus ()
	if (index>x.length-1)
	{
		x.selectedIndex = 0;
	}
	else
	{
		x.selectedIndex = index;
	}
	return true;
}
 
 function Add_all(x,y)
 {
	var obj_len = x.length ;
	
	for(var i=0;i<obj_len;i++)
	{
		var new_option=document.createElement("OPTION");
		new_option.text=x.options[0].text;
		new_option.value=x.options[0].value;
		var max_index=y.length ;
		y.options[max_index] = new_option;
		x.options[0]=null;
	}
	return true;
}


 
function Remove_one(x,y) 
{
	var index=y.selectedIndex;
	if(index<0)
	{
		y.focus ();
		y.selectedIndex = 0;
		return false;
	}
	var obj_len = y.length;
	var arr_sele=new Array(100);
	var j=0;
	for(var i=index;i<obj_len;i++)
	{ 
		if(y.options[i].selected==true)
		{
			var new_option=document.createElement("OPTION");
			new_option.text=y.options[i].text;
			new_option.value=y.options[i].value;
			max_index=x.length;
			x.options[max_index] = new_option;
			arr_sele[j]=i;
			y.options[i].selected = false;
			j++;
		}
	}
	
	for(var i=0;i<j;i++)
	{ //将已加入的选项删除
		y.options[arr_sele[i]-i] = null;
	}
	y.focus ()
	if(index>y.length-1)
	{
		y.selectedIndex = 0;
	}
	else
	{
		y.selectedIndex = index;
	}
	return true;
 }
 
 function Remove_all(x,y)
 {
	var obj_len = y.length ;
	
	for(var i=0;i<obj_len;i++)
	{
		var new_option=document.createElement("OPTION");
		new_option.text=y.options[0].text;
		new_option.value=y.options[0].value;
		max_index=x.length ;
		x.options[max_index] = new_option;
		y.options[0]=null;
	}
	return true;
}
