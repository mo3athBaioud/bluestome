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
package info.magnolia.module.admininterface;

/**
 * Runtime Exception thrown when a dialog handler can't be instantiated.
 * @author Fabrizio Giustina
 * @version $Revision: 1956 $ ($Author: fgiust $)
 */
public class InvalidDialogHandlerException extends RuntimeException {

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 222L;

    /**
     * Instantiates a new exception. Use this constructor when configuration is missing.
     * @param dialogName missing dialog name
     */
    public InvalidDialogHandlerException(String dialogName) {
        super("No dialog handler for [" + dialogName + "] found"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Instantiates a new exception. Use this constructor when the dialog handler can't be instantiated due to a
     * previous exception.
     * @param dialogName dialog name
     * @param cause previous exception
     */
    public InvalidDialogHandlerException(String dialogName, Throwable cause) {
        super("Unable to instantiate a dialog handler for [" //$NON-NLS-1$
            + dialogName
            + "] due to a " //$NON-NLS-1$
            + cause.getClass().getName()
            + " exception", cause); //$NON-NLS-1$
    }

}
