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
package info.magnolia.cms.util;

/**
 * @author Sameer Charles
 * @version $Revision: 2027 $ ($Author: scharles $)
 */
public class ExclusiveWrite {

    /**
     * package private
     */
    ExclusiveWrite() {
        // protected
    }

    /**
     * singleton
     */
    private static final ExclusiveWrite instance = new ExclusiveWrite();

    /**
     * get exclusive singleton class object
     * @return instance
     */
    public static ExclusiveWrite getInstance() {
        return instance;
    }

}
