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
import info.magnolia.cms.core.NodeData;
import info.magnolia.cms.core.Path;
import info.magnolia.cms.util.NodeDataUtil;
import info.magnolia.context.MgnlContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.PathNotFoundException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class wrapps a user content object to provide some nice methods
 * @author philipp
 * @version $Revision: 3504 $ ($Author: philipp $)
 */
public class MgnlUser implements User {

    public static Logger log = LoggerFactory.getLogger(User.class);

    /**
     * Under this subnodes the assigned roles are saved
     */
    private static final String NODE_ROLES = "roles"; //$NON-NLS-1$

    private static final String NODE_GROUPS = "groups"; //$NON-NLS-1$

    /**
     * the content object
     */
    private Content userNode;

    /**
     * @param userNode the Content object representing this user
     */
    protected MgnlUser(Content userNode) {
        this.userNode = userNode;
    }

    /**
     * Is this user in a specified role?
     * @param groupName the name of the role
     * @return true if in role
     */
    public boolean inGroup(String groupName) {
        return this.hasAny(groupName, NODE_GROUPS);
    }

    /**
     * Remove a group. Implementation is optional
     * @param groupName
     */
    public void removeGroup(String groupName) throws UnsupportedOperationException {
        this.remove(groupName, NODE_GROUPS);
    }

    /**
     * Adds this user to a group. Implementation is optional
     * @param groupName
     */
    public void addGroup(String groupName) throws UnsupportedOperationException {
        this.add(groupName, NODE_GROUPS);
    }

    /**
     * Is this user in a specified role?
     * @param roleName the name of the role
     * @return true if in role
     */
    public boolean hasRole(String roleName) {
        return this.hasAny(roleName, NODE_ROLES);
    }

    public void removeRole(String roleName) {
        this.remove(roleName, NODE_ROLES);
    }

    /**
     * Adds a role to this user
     * @param roleName the name of the role
     */
    public void addRole(String roleName) {
        this.add(roleName, NODE_ROLES);
    }

    /**
     * checks is any object exist with the given name under this node
     * @param name
     * @param nodeName
     */
    private boolean hasAny(String name, String nodeName) {
        try {
            HierarchyManager hm;
            if (StringUtils.equalsIgnoreCase(nodeName, NODE_ROLES))
                hm = MgnlContext.getHierarchyManager(ContentRepository.USER_ROLES);
            else
                hm = MgnlContext.getHierarchyManager(ContentRepository.USER_GROUPS);

            Content node = userNode.getContent(nodeName);
            for (Iterator iter = node.getNodeDataCollection().iterator(); iter.hasNext();) {
                NodeData nodeData = (NodeData) iter.next();
                // check for the existence of this ID
                try {
                    if (hm.getContentByUUID(nodeData.getString()).getName().equalsIgnoreCase(name)) {
                        return true;
                    }
                } catch(ItemNotFoundException e) {
                    if (log.isDebugEnabled())
                        log.debug("Role [ "+name+" ] does not exist in the ROLES repository");
                } catch (IllegalArgumentException e) {
                    if (log.isDebugEnabled())
                        log.debug(nodeData.getHandle()+" has invalid value");
                }
            }
        }
        catch (RepositoryException e) {
            log.debug(e.getMessage(), e);
        }
        return false;
    }

    /**
     * removed node
     * @param name
     * @param nodeName
     */
    private void remove(String name, String nodeName) {
        try {
            HierarchyManager hm;
            if (StringUtils.equalsIgnoreCase(nodeName, NODE_ROLES))
                hm = MgnlContext.getHierarchyManager(ContentRepository.USER_ROLES);
            else
                hm = MgnlContext.getHierarchyManager(ContentRepository.USER_GROUPS);
            Content node = userNode.getContent(nodeName);

            for (Iterator iter = node.getNodeDataCollection().iterator(); iter.hasNext();) {
                NodeData nodeData = (NodeData) iter.next();
                // check for the existence of this ID
                try {
                    if (hm.getContentByUUID(nodeData.getString()).getName().equalsIgnoreCase(name)) {
                        nodeData.delete();
                    }
                } catch(ItemNotFoundException e) {
                    if (log.isDebugEnabled())
                        log.debug("Role [ "+name+" ] does not exist in the ROLES repository");
                } catch (IllegalArgumentException e) {
                    if (log.isDebugEnabled())
                        log.debug(nodeData.getHandle()+" has invalid value");
                }
            }
            userNode.save();
        }
        catch (RepositoryException e) {
            log.error("failed to remove " + name + " from user [" + this.getName() + "]", e);
        }
    }

    /**
     * adds a new node under specified node collection
     */
    private void add(String name, String nodeName) {
        try {
            HierarchyManager hm;
            if (StringUtils.equalsIgnoreCase(nodeName, NODE_ROLES))
                hm = MgnlContext.getHierarchyManager(ContentRepository.USER_ROLES);
            else
                hm = MgnlContext.getHierarchyManager(ContentRepository.USER_GROUPS);

            if (!this.hasAny(name, nodeName)) {
                Content node = userNode.getContent(nodeName);
                // add corresponding ID
                try {
                    String value = hm.getContent("/"+name).getUUID(); // assuming that there is a flat hierarchy
                    // used only to get the unique label
                    HierarchyManager usersHM = ContentRepository.getHierarchyManager(ContentRepository.USERS);
                    String newName = Path.getUniqueLabel(usersHM, node.getHandle(), "0");
                    node.createNodeData(newName).setValue(value);
                    userNode.save();
                } catch(PathNotFoundException e) {
                    if (log.isDebugEnabled())
                        log.debug("Role [ "+name+" ] does not exist in the ROLES repository");
                }
            }
        }
        catch (RepositoryException e) {
            log.error("failed to add " + name + " to user [" + this.getName() + "]", e);
        }
    }

    /**
     * The name of the user
     * @return the name of the user
     */
    public String getName() {
        return this.userNode.getName();
    }

    /**
     * get user password
     * @return password string
     */
    public String getPassword() {
        String pswd = this.userNode.getNodeData("pswd").getString().trim();
        return new String(Base64.decodeBase64(pswd.getBytes()));
    }

    /**
     * the language of the current user
     */
    public String getLanguage() {
        return userNode.getNodeData("language").getString(); //$NON-NLS-1$
    }

    public Collection getGroups() {
        ArrayList list = new ArrayList();

        try {
            Content groups = null;
            try {
                // get "groups" node under node "user"
                groups = userNode.getContent("groups");
            }
            catch (javax.jcr.PathNotFoundException e) {
                log.warn("the user " + getName() + " does have not groups node");
            }

            if (groups != null) {
                Collection c = groups.getNodeDataCollection();
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    NodeData nd = (NodeData) it.next();
                    String uuid = nd.getString();
                    Content group = MgnlContext
                        .getSystemContext()
                        .getHierarchyManager(ContentRepository.USER_GROUPS)
                        .getContentByUUID(uuid);
                    list.add(group.getName());
                }
            }

        }
        catch (Exception e) {
            log.warn("cant read groups of user.", e);
        }

        return list;
    }

    public Collection getRoles() {
        ArrayList list = new ArrayList();

        try {
            Content roles = null;
            try {
                // get "groups" node under node "user"
                roles = userNode.getContent("roles");
            }
            catch (javax.jcr.PathNotFoundException e) {
                log.warn("the user " + getName() + " does have not roles node");
            }

            if (roles != null) {
                Collection c = roles.getNodeDataCollection();
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    NodeData nd = (NodeData) it.next();
                    String uuid = nd.getString();
                    Content role = MgnlContext
                        .getSystemContext()
                        .getHierarchyManager(ContentRepository.USER_ROLES)
                        .getContentByUUID(uuid);
                    list.add(role.getName());
                }
            }

        }
        catch (Exception e) {
            log.warn("can't read roles of user.", e);
        }

        return list;
    }

    /**
     * Update the "last access" timestamp.
     */
    public void setLastAccess() {
/*
        NodeData lastaccess;
        try {
            lastaccess = NodeDataUtil.getOrCreate(userNode, "lastaccess", PropertyType.DATE);
            synchronized (lastaccess) {
                lastaccess.setValue(new GregorianCalendar());
                lastaccess.save();
            }
        }
        catch (RepositoryException e) {
            log.error(
                "Unable to set the last access date due to a " + e.getClass().getName() + " - " + e.getMessage(),
                e);
        }
*/
    }
}