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
package info.magnolia.cms.gui.dialog;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Vinzenz Wyser
 * @version 2.0
 */
public class DialogInclude extends DialogBox {

    /**
     * Logger.
     */
    private static Logger log = LoggerFactory.getLogger(DialogInclude.class);

    /**
     * Empty constructor should only be used by DialogFactory.
     */
    protected DialogInclude() {
    }

    /**
     * @see info.magnolia.cms.gui.dialog.DialogControl#drawHtml(Writer)
     */
    public void drawHtml(Writer out) throws IOException {
        this.drawHtmlPre(out);
        HttpServletRequest request = this.getRequest();
        if (request == null) {
            request = this.getTopParent().getRequest();
        }

        try {
            request.setAttribute("dialogObject", this); //$NON-NLS-1$

            String file = this.getConfigValue("file"); //$NON-NLS-1$

            request.getRequestDispatcher(file).include(request, this.getResponse());

            request.removeAttribute("dialogObject"); //$NON-NLS-1$
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        this.drawHtmlPost(out);
    }
}
