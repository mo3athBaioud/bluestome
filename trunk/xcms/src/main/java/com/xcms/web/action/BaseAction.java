package com.xcms.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xcms.common.json.JsonObject;

public class BaseAction {

	protected JsonObject json;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
}
