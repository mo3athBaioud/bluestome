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
package info.magnolia.commands;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;


/**
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 *
 */
public abstract class MgnlCommand implements Command {

    /**
     * Make sure that the context is castable to a magnolia context
     */
    public boolean execute(Context context) throws Exception {
        return execute((info.magnolia.context.Context)context);
    }
    
    public abstract boolean execute(info.magnolia.context.Context context) throws Exception;

}
