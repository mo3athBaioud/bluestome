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
package info.magnolia.module.admininterface.pages;

import info.magnolia.cms.beans.config.ModuleLoader;
import info.magnolia.cms.i18n.Messages;
import info.magnolia.cms.i18n.MessagesManager;
import info.magnolia.cms.module.Module;
import info.magnolia.module.admininterface.TemplatedMVCHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Fabrizio Giustina
 * @version $Revision: 3446 $ ($Author: fgiust $)
 */
public class RestartPage extends TemplatedMVCHandler {

    /**
     * Required constructor.
     * @param name page name
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public RestartPage(String name, HttpServletRequest request, HttpServletResponse response) {
        super(name, request, response);
    }

    public List getRestartNeedingModules() {
        // is a system restart needed
        List restartNeedingModules = new ArrayList();

        // collect the modules needing a restart
        for (Iterator iter = ModuleLoader.getInstance().getModuleInstances().keySet().iterator(); iter.hasNext();) {
            String moduleName = (String) iter.next();
            Module module = ModuleLoader.getInstance().getModuleInstance(moduleName);
            if (module.isRestartNeeded()) {
                restartNeedingModules.add(module);
            }
        }

        return restartNeedingModules;
    }

    public Messages getMessages() {
        return MessagesManager.getMessages();
    }

}
