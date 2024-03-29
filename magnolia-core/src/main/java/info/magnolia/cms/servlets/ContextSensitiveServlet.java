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
package info.magnolia.cms.servlets;

import info.magnolia.cms.util.FactoryUtil;
import info.magnolia.context.MgnlContext;
import info.magnolia.context.WebContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet which sets the context properly.
 *
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 */
public abstract class ContextSensitiveServlet extends HttpServlet {

    /**
     * Logger
     */
    Logger log = LoggerFactory.getLogger(ContextSensitiveServlet.class);

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeContext(req);
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeContext(req);
    }

    /**
     * Initialize Magnolia context. It creates a context and initialize the user only if these do not exist yet. <b>Note</b>:
     * the implementation may get changed
     *
     * @param request the current request
     */
    protected void initializeContext(HttpServletRequest request) {
        if (!MgnlContext.hasInstance()) {
            MgnlContext.initAsWebContext(request);
        } else {
            // this will happen if a virtual uri mapping is pointing again to a virtual uri
            if (log.isDebugEnabled())
                log.debug("context of thread was already set");
        }
    }

}
