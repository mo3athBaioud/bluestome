var Class = {
    create: function () {
        return function () {
            this.initialize.apply(this, arguments);
        }
    }
}
Object.extend = function (destination, source) {
    for (var property in source) {
        destination[property] = source[property];
    }
    return destination;
}
Function.prototype.bind = function (bindObj, args) {
    var _self = this;
    return function () {
        return _self.apply(bindObj, [].concat(args));
    }
}
var Lazyload = function () { };
Lazyload = Class.create();
Lazyload.prototype = {
    options: {
        defaultimage: "",
        src2: "lazyload",
        ImgList: []
    },
    imageCount: 0,
    $: function (id) {
        if (typeof id != "string") return id;
        return document.getElementById(id);
    },
    initialize: function () {

        Object.extend(this.options, arguments[0]);

        if (typeof this.options.ImgList == 'string') {
            var obj = this.$(this.options.ImgList);
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].getAttribute(this.options.src2) != "") {
                    this.options.ImgList.push(obj[i]);
                }
            }
        }
        if (this.options.ImgList.length == 0) return;
        this.imageCount = this.options.ImgList.length;
        Autohome.events.addEvent(window, this.loaded.bind(this), "scroll");
        Autohome.events.addEvent(window, this.loaded.bind(this), "touchend");
    },
    loaded: function () {
        var pageheight = getScrollPos()[0] + getPageSize()[3]; 
        var imgsrc = "";
        var flag = IsLazyLoad();
        for (var img = 0; img < this.options.ImgList.length; img++) {
            if (flag == true) {
                if (this.getXY(this.options.ImgList[img]).Top < pageheight + 1500) {
                    //window.status = this.options.ImgList[img];
                    if (this.options.ImgList[img].src == "" || IsLoading32(this.options.ImgList[img].src)) {
                        imgsrc = this.options.ImgList[img].getAttribute(this.options.src2);
                        this.options.ImgList[img].src = imgsrc;
                        //delete this.options.ImgList[img];
                        this.imageCount--;
                    }
                }
                else {
                    break;
                }
            }
            else {
                imgsrc = this.options.ImgList[img].getAttribute(this.options.src2);
                this.options.ImgList[img].src = imgsrc;
                this.imageCount--;
            }
        }
        if (this.imageCount == 0) Autohome.events.removeEvent(window, this.loaded, "scroll");
    },
    //得到对象的Left 和 Top
    getXY: function (element) {
        var x = 0;
        var y = 0;
        var el = this.$(element);
        while (el.offsetParent) {
            x += el.offsetLeft;
            y += el.offsetTop;
            el = el.offsetParent
        }
        return { "Left": x, "Top": y };
    }
}



var Autohome = {};
Autohome.events = {}; //事件
Autohome.events.getEvent = function () {
    return window.event
};
if (!window.isIE) {
    Autohome.events.getEvent = function () {
        var o = arguments.callee.caller;
        var e;
        var n = 0;
        while (o != null && n < 40) {
            e = o.arguments[0];
            if (e && (e.constructor == Event || e.constructor == MouseEvent)) {
                return e
            }
            n++;
            o = o.caller
        }
        return e
    }
}

//删除事件
Autohome.events.removeEvent = function (obj, func, evType) {
    var elm = obj;
    if ("function" != typeof func) return;

    if (elm.addEventListener) {
        if (evType.substr(0, 2) == "on") evType = evType.substring(2);
        elm.removeEventListener(evType, func, false)
    } else if (elm.attachEvent) {
        if (evType.substr(0, 2) != "on") evType = "on" + evType;
        elm.detachEvent(evType, func)
    }
};
//加事件
Autohome.events.addEvent = function (obj, func, evType) {
    if (obj.attachEvent) {
        if (evType.substr(0, 2) != "on") evType = "on" + evType;
        obj.attachEvent(evType, func);
        return true;
    }
    if (obj.addEventListener) {
        if (evType.substr(0, 2) == "on") evType = evType.substring(2);
        obj.addEventListener(evType, func, false);
        return true;
    }
};

;
getPageSize = function () {
    var _rootEl = (document.compatMode == "CSS1Compat"
			? document.documentElement
			: document.body);
    var xScroll, yScroll;

    if (window.innerHeight && window.scrollMaxY) {
        xScroll = _rootEl.scrollWidth;
        yScroll = window.innerHeight + window.scrollMaxY
    } else if (_rootEl.scrollHeight > _rootEl.offsetHeight) {
        xScroll = _rootEl.scrollWidth;
        yScroll = _rootEl.scrollHeight + 20
    } else {
        xScroll = _rootEl.offsetWidth;
        yScroll = _rootEl.offsetHeight
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) {
        windowWidth = self.innerWidth;
        windowHeight = self.innerHeight
    } else if (_rootEl && _rootEl.clientHeight) {
        windowWidth = _rootEl.clientWidth;
        windowHeight = _rootEl.clientHeight
    } else if (_rootEl) {
        windowWidth = _rootEl.clientWidth;
        windowHeight = _rootEl.clientHeight
    }
    if (yScroll < windowHeight) {
        pageHeight = windowHeight
    } else {
        pageHeight = _rootEl.scrollHeight
    }
    if (xScroll < windowWidth) {
        pageWidth = windowWidth
    } else {
        pageWidth = xScroll
    }
    return [pageWidth, pageHeight, windowWidth, windowHeight]
};
//获取页面的滚动条值[]
getScrollPos = function (oDocument) {
    var oDocument = oDocument || document;
    return [
			Math.max(oDocument.documentElement.scrollTop,
					oDocument.body.scrollTop),
			Math.max(oDocument.documentElement.scrollLeft,
					oDocument.body.scrollLeft),
			Math.max(oDocument.documentElement.scrollWidth,
					oDocument.body.scrollWidth),
			Math.max(oDocument.documentElement.scrollHeight,
					oDocument.body.scrollHeight)]
};

function IsLazyLoad() {
    var ua = navigator.userAgent.toLowerCase();
    //document.getElementById("msg").innerHTML = ua + "Zhi Shi=" + s;
    if (ua.indexOf("ipad") > -1 || ua.indexOf("ipod") > -1 || ua.indexOf("iphone") > -1 || ua.indexOf("linux i686") > -1) {
        //document.getElementById("msg").innerHTML = ua;
        return false;
    }
    else {
        return true;
    }
};

function IsLoading32(url){
	var end = url.lastIndexOf("/");
	if(end != -1){
		url = url.substring(end+1);
		if(url == 'loading32.gif'){
			return true;
		}
	}
	return false;
}