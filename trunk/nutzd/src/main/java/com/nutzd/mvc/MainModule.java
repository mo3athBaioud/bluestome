package com.nutzd.mvc;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBy(type = ComboIocProvider.class, args = {
		"*org.nutz.ioc.loader.json.JsonLoader", "conf/jdbc.js",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
		"com.nutzd.mvc" })
@Encoding(input = "utf8", output = "utf8")
@Modules(scanPackage = true)
@Localization("msg/")
public class MainModule {
	
}
