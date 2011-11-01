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
        TakeSoon.events.addEvent(window, this.loaded.bind(this), "scroll");
        TakeSoon.events.addEvent(window, this.loaded.bind(this), "touchend");
    },
    loaded: function () {
        var pageheight = getScrlPosites()[0] + getPageSize()[3]; 
        var imgsrc = "";
        var flag = IsLazyLoad();
        for (var img = 0; img < this.options.ImgList.length; img++) {
            if (flag == true) {
                if (this.getXY(this.options.ImgList[img]).Top < pageheight + 1500) {
                    //window.status = this.options.ImgList[img];
					/* srct必须为一个绝对的URL地址 */
					var srct = this.options.ImgList[img].src;
					
					//图片缩略图判断
                    if (srct == "20111101120217918_easyicon_cn_48.png" || srct == "file:///D:/tmp/html/20111101120217918_easyicon_cn_48.png" || srct == "") {
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
        if (this.imageCount == 0) TakeSoon.events.removeEvent(window, this.loaded, "scroll");
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



var TakeSoon = {};
/* 事件集合 */
TakeSoon.events = {};

TakeSoon.events.getEvent = function () {
    return window.event
};

/* 浏览器判断 */
if (!window.isIE) {
    TakeSoon.events.getEvent = function () {
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

/* 删除事件 */
TakeSoon.events.removeEvent = function (obj, func, eventType) {
    var elm = obj;
    if ("function" != typeof func) return;

    if (elm.addEventListener) {
        if (eventType.substr(0, 2) == "on") eventType = eventType.substring(2);
        elm.removeEventListener(eventType, func, false)
    } else if (elm.attachEvent) {
        if (eventType.substr(0, 2) != "on") eventType = "on" + eventType;
        elm.detachEvent(eventType, func)
    }
};
/* 添加事件 */
TakeSoon.events.addEvent = function (obj, func, eventType) {
    if (obj.attachEvent) {
        if (eventType.substr(0, 2) != "on") eventType = "on" + eventType;
        obj.attachEvent(eventType, func);
        return true;
    }
    if (obj.addEventListener) {
        if (eventType.substr(0, 2) == "on") eventType = eventType.substring(2);
        obj.addEventListener(eventType, func, false);
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

/* Scroll 获取页面的滚动条值[] */
getScrlPosites = function (browseDocument) {
    var browseDocument = browseDocument || document;
    return [
			Math.max(browseDocument.documentElement.scrollTop,
					browseDocument.body.scrollTop),
			Math.max(browseDocument.documentElement.scrollLeft,
					browseDocument.body.scrollLeft),
			Math.max(browseDocument.documentElement.scrollWidth,
					browseDocument.body.scrollWidth),
			Math.max(browseDocument.documentElement.scrollHeight,
					browseDocument.body.scrollHeight)]
};

/**
 * 判断是否需要执行懒加载
 */
function IsLazyLoad() {
	/* 通过UA来判断浏览器版本 */
    var ua = navigator.userAgent.toLowerCase();
    alert(' > ua:'+ua);
    if (ua.indexOf("ipad") > -1 || ua.indexOf("ipod") > -1 || ua.indexOf("iphone") > -1 || ua.indexOf("linux i686") > -1) {
        return false;
    }
    else {
        return true;
    }
};