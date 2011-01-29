/**
 *
 * Magnolia and its source-code is licensed under the LGPL.
 * You may copy, adapt, and redistribute this file for commercial or non-commercial use.
 * When copying, adapting, or redistributing this document in keeping with the guidelines above,
 * you are required to provide proper attribution to obinary.
 * If you reproduce or distribute the document without making any substantive modifications to its content,
 * please use the following attribution line:
 *
 * Copyright 1993-2004 obinary Ltd. (http://www.obinary.com) All rights reserved.
 *
 */
package info.magnolia.cms.taglibs;

/**
 * @author Marcel Salathe
 * @version $Revision: 1536 $ ($Author: fgiust $)
 */
public class IfNotExisting extends IfExisting {

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 222L;

    /**
     * @see info.magnolia.cms.taglibs.IfEmpty#condition()
     */
    protected boolean condition() {
        return !super.condition();
    }
}