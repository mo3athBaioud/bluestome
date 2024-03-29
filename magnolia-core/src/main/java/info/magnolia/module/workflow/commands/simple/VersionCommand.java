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

import info.magnolia.cms.core.Content;
import info.magnolia.cms.util.AlertUtil;
import info.magnolia.commands.MgnlCommand;
import info.magnolia.context.Context;
import info.magnolia.context.MgnlContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Creates a version for the passed path in the website repository.
 * @author Philipp Bracher
 * @version $Revision: 3416 $ ($Author: philipp $)
 */
public class VersionCommand extends MgnlCommand {

    private static Logger log = LoggerFactory.getLogger(VersionCommand.class);
    
    /**
     * @see info.magnolia.commands.MgnlCommand#exec(java.util.HashMap, org.apache.commons.chain.Context)
     */
    public boolean execute(Context ctx) {
        String path = (String) ctx.get(Context.ATTRIBUTE_PATH);
        String repository = (String) ctx.get(Context.ATTRIBUTE_REPOSITORY);
        try {
            Content node = MgnlContext.getHierarchyManager(repository).getContent(path);
            node.addVersion();
        }
        catch (Exception e) {
            log.error("can't version", e);
            AlertUtil.setMessage("can't version: " + e.getMessage());
            return true; // end execution
        }
        return false;
    }

}
