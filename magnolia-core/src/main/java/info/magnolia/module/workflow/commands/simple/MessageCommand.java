/**
 *
 * Magnolia and its source-code is licensed under the LGPL.
 * You may copy, adapt, and redistribute this file for commercial or non-commercial use.
 * When copying, adapting, or redistributing this document in keeping with the guidelines above,
 * you are required to provide proper attribution to obinary.
 * If you reproduce or distribute the document without making any substantive modifications to its content,
 * please use the following attribution line:
 *
 * Copyright 1993-2005 obinary Ltd. (http://www.obinary.com) All rights reserved.
 *
 */
package info.magnolia.module.workflow.commands.simple;

import org.apache.commons.lang.StringUtils;

import info.magnolia.cms.i18n.Messages;
import info.magnolia.cms.i18n.MessagesManager;
import info.magnolia.cms.i18n.MessagesUtil;
import info.magnolia.cms.util.AlertUtil;
import info.magnolia.commands.MgnlCommand;
import info.magnolia.context.Context;


/**
 * A command setting a message using the AlertUtil
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 *
 */
public class MessageCommand extends MgnlCommand {
    
    /**
     * The message
     */
    private String message = "";
    
    private String i18nBasename = MessagesManager.DEFAULT_BASENAME;
    
    /**
     * @see info.magnolia.commands.MgnlCommand#execute(info.magnolia.context.Context)
     */
    public boolean execute(Context context) throws Exception {
        String msg = this.getMessage(context);
        if(StringUtils.isNotEmpty(msg)){
            Messages msgs = MessagesUtil.chainWithDefault(this.getI18nBasename());
            AlertUtil.setMessage(msgs.getWithDefault(msg, msg));
        }
        return false;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage(Context context) {
        return StringUtils.defaultIfEmpty((String)context.getAttribute(Context.ATTRIBUTE_MESSAGE), this.getMessage());
    }
    
    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    
    /**
     * @return Returns the i18nBasename.
     */
    public String getI18nBasename() {
        return this.i18nBasename;
    }

    
    /**
     * @param basename The i18nBasename to set.
     */
    public void setI18nBasename(String basename) {
        this.i18nBasename = basename;
    }

}
