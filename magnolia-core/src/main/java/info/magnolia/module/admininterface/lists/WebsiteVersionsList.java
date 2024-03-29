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
package info.magnolia.module.admininterface.lists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.magnolia.context.MgnlContext;


/**
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 *
 */
public class WebsiteVersionsList extends VersionsList {

    /**
     * @param name
     * @param request
     * @param response
     * @throws Exception
     */
    public WebsiteVersionsList(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super(name, request, response);
    }

    /**
     * Default url used for pages
     * @param versionLabel
     * @return the url used in the link
     */
    public String getOnShowFunction() {
        String url = MgnlContext.getContextPath() + path + ".html?mgnlVersion=";
        return "function(versionLabel){open('" + url + "'+versionLabel, '');}";
    }
    

}
