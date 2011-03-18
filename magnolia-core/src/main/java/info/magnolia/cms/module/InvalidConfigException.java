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
package info.magnolia.cms.module;

import info.magnolia.cms.core.BaseException;


/**
 * @author Sameer Charles
 * @version 2.0
 */
public class InvalidConfigException extends BaseException {

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 222L;

    /**
     * @param message message
     */
    public InvalidConfigException(String message) {
        super(message);
    }

    /**
     * @param message message
     * @param cause cause
     */
    public InvalidConfigException(String message, Exception cause) {
        super(message, (cause instanceof InvalidConfigException) ? ((InvalidConfigException) cause).getCause() : cause);
    }

    /**
     * @param root root
     */
    public InvalidConfigException(Exception root) {
        super(root);
    }
}