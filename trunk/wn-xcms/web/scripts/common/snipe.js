slider.names = new Array();

function slider()
{
	this.id = slider.names.length;
	slider.names[this.id] = this;
	this.target	 = document.getElementById(arguments[0]);	//第一个参数：被操作div的id
	this.direction = arguments[1];//第二个参数：div弹出的方向
	this.height = arguments[2];//第三个参数：div的高度
	this.width = arguments[3];//第四个参数：div的宽度
	this.step = arguments[4];//第五个参数：希望动作分解为几步完成
	this.timer = 10 * arguments[5];//第六个参数：每个动作的间隔时间，10ms为一个单位
	this.startopa = arguments[6];//第七个参数：div开始的透明度
	this.sparent = this.target.parentNode;//获取操作div的父容器
	this.intervalid = null;//循环定时的id
	this.i = 0;//循环的计数器
	this.status = 0;//slider层的状态：0-可以展开；1-不可以展开
	this.target.style.display = "none";//先将div隐去
	return this;
}

slider.prototype.initialize = function()
{
	this.sparent.style.overflow = "hidden";//设置父容器overflow
	this.target.style.width = Number(this.width) + 'px';//设置目标div的宽度
	this.target.style.height = Number(this.height) + 'px';//设置目标div的高度
	this.target.style.position = "";//设置目标div的定位方式
	this.target.style.display = "";//设置目标div的显示方式
	this.target.style.filter = 'Alpha(opacity=' + Number(this.startopa) + ')';//设置目标div的透明度为初始透明度
	this.target.style.overflow = "hidden";//设置overflow
	switch(this.direction)//根据弹出方向设定div的margin
	{
		case 1://left to right
			this.target.style.marginLeft = "-" + this.width + "px";
			break;
		case 2://top to bottom
			this.target.style.marginTop = "-" + this.height + "px";
			break;
		case 3://right to left
			this.target.style.marginRight = "-" + this.width + "px";
			break;
	}
}

slider.prototype.show = function()
{
	if (this.status==0)//检查状态是否已经展开
	{
		this.initialize();//操作div及其父容器的初始化
		this.intervalid = window.setInterval("slider.names["+this.id+"].cycle()",this.timer);//设置动作循环

	}
}

slider.prototype.hide = function()
{
	if (this.status==1)//检查状态是否已经展开
	{
		this.intervalid = window.setInterval("slider.names["+this.id+"].decycle()",this.timer);//设置动作循环

	}
}

slider.prototype.cycle = function()	//单步循环动作
{
	var opa = this.target.style.filter.split("=")[1].split(")")[0]//获取目标div的透明度数值
	var opastep = Math.round(((100 - Number(opa)) / this.step)+2.5);//计算每步增加的透明度
	var nopa = Number(opa) + Number(opastep);//当前透明度
	if (nopa>100){this.target.style.filter = 'Alpha(opacity=100)';}else{this.target.style.filter = 'Alpha(opacity=' + String(nopa) + ')';}//给div透明度赋值
	switch(this.direction)//根据弹出方向计算和设定div的动作
	{
		case 1:		//left to right
			var opx = this.target.style.marginLeft.split("px")[0];
			var pxstep = Math.round((this.width / this.step)+0.5);
			var npx = Number(opx) + Number(pxstep);
			if (npx>0){this.target.style.marginLeft = '0px';}else{this.target.style.marginLeft = String(npx) + 'px';}
			break;
		case 2:		//top to bottom
			var opx = this.target.style.marginTop.split("px")[0];
			var pxstep = Math.round((this.height / this.step)+0.5);
			var npx = Number(opx) + Number(pxstep);
			if (npx>0){this.target.style.marginTop = '0px';}else{this.target.style.marginTop = String(npx) + 'px';}
			break;
		case 3:		//right to left
			var opx = this.target.style.marginRight.split("px")[0];
			var pxstep = Math.round((this.width / this.step)+0.5);
			var npx = Number(opx) + Number(pxstep);
			if (npx>0){this.target.style.marginRight = '0px';}else{this.target.style.marginRight = String(npx) + 'px';}
			break;
	}
	this.i++	//计数器+1
	if (this.i>(this.step-1)){window.clearInterval(this.intervalid);this.i=0;this.status=1;}	//循环完毕，清除循环定时
}

slider.prototype.decycle = function()	//单步循环动作
{
	var opa = this.target.style.filter.split("=")[1].split(")")[0]//获取目标div的透明度数值
	var opastep = Math.round(((100 - Number(opa)) / this.step)+2.5)*2;//计算每步增加的透明度
	var nopa = Number(opa) - Number(opastep);//当前透明度
	if (nopa<this.startopa){this.target.style.filter = 'Alpha(opacity=' + this.startopa + ')';}else{this.target.style.filter = 'Alpha(opacity=' + String(nopa) + ')';}//给div透明度赋值

	switch(this.direction)//根据弹出方向计算和设定div的动作
	{
		case 1:		//left to right
			var opx = this.target.style.marginLeft.split("px")[0];
			var pxstep = Math.round((this.width / Math.round(this.step*0.5))+0.5);
			var npx = Number(opx) - Number(pxstep);
			if (Math.abs(npx)>this.width+2){this.target.style.marginLeft = '-' + this.width + 'px';}else{this.target.style.marginLeft = String(npx) + 'px';}
			break;
		case 2:		//top to bottom
			var opx = this.target.style.marginTop.split("px")[0];
			var pxstep = Math.round((this.height / Math.round(this.step*0.5))+0.5);
			var npx = Number(opx) - Number(pxstep);
			if (Math.abs(npx)>this.height+2){this.target.style.marginTop = '-' + this.height + 'px';}else{this.target.style.marginTop = String(npx) + 'px';}
			break;
		case 3:		//right to left
			var opx = this.target.style.marginRight.split("px")[0];
			var pxstep = Math.round((this.width / Math.round(this.step*0.5))+0.5);
			var npx = Number(opx) - Number(pxstep);
			if (Math.abs(npx)>this.width+2){this.target.style.marginRight = '-' + this.width + 'px';}else{this.target.style.marginRight = String(npx) + 'px';}
			break;
	}
	this.i++	//计数器+1
	if (this.i>(Math.round(this.step*0.5)-1)){window.clearInterval(this.intervalid);this.i=0;this.status=0;this.target.style.display = "none";}	//循环完毕，清除循环定时
}