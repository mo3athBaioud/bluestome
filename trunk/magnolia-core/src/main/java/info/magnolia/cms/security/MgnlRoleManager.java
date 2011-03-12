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
package info.magnolia.cms.security;

import info.magnolia.cms.beans.config.ContentRepository;
import info.magnolia.cms.core.Content;
import info.magnolia.cms.core.HierarchyManager;
import info.magnolia.cms.core.ItemType;
import info.magnolia.context.MgnlContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Manages the users stored in magnolia itself.
 * @author philipp
 * @version $Revision: 3255 $ ($Author: philipp $)
 */
public class MgnlRoleManager implements RoleManager {

    public static Logger log = LoggerFactory.getLogger(MgnlRoleManager.class);

    /**
     * Do not instantiate it!
     */
    public MgnlRoleManager() {
    }

    public Role getRole(String name) {
        try {
            return new MgnlRole(getHierarchyManager().getContent(name));
        }
        catch (Exception e) {
            log.info("can't find role [" + name + "]", e);
            return null;
        }
    }

    public Role createRole(String name) {
        try {
            Content node = getHierarchyManager().createContent("/", name, ItemType.CONTENT.getSystemName());
            node.save();
            return new MgnlRole(node);
        }
        catch (Exception e) {
            log.error("can't create role [" + name + "]", e);
            return null;
        }
    }

    /**
     * return the role HierarchyManager
     */
    protected HierarchyManager getHierarchyManager() {
        return MgnlContext.getHierarchyManager(ContentRepository.USER_ROLES);
    }
}