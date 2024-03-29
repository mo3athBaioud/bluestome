/**
 *
 * Magnolia and its source-code is licensed under the LGPL.
 * You may copy, adapt, and redistribute this file for commercial or non-commercial use.
 * When copying, adapting, or redistributing this document in keeping with the guidelines above,
 * you are required to provide proper attribution to obinary.
 * If you reproduce or distribute the document without making any substantive modifications to its content,
 * please use the following attribution line:
 *
 * Copyright 1993-2006 obinary Ltd. (http://www.obinary.com) All rights reserved.
 *
 */
package info.magnolia.jackrabbit;

import info.magnolia.cms.core.BaseRuntimeException;


/**
 * @author fgiust
 * @version $Revision: 2935 $ ($Author: fgiust $)
 */
public class MissingNodetypesException extends BaseRuntimeException {

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 222L;

    /**
     * @param message
     */
    public MissingNodetypesException() {
        super(
            "No nodetype configuration file found, the default magnolia nodetype config is missing from the classpath");
    }

}
