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

/**
 * @author Sameer Charles
 * $Id: Group.java 3457 2006-06-13 10:01:23Z scharles $
 */
public interface Group {

    /**
     * get name of this node
     * @return group name
     * */
    public String getName();

    /**
     * add role to this group
     * @param roleName
     * @throws UnsupportedOperationException if the implementation does not support writing
     * @throws AccessDeniedException if loggen in repository user does not sufficient rights
     * */
    public void addRole(String roleName) throws UnsupportedOperationException, AccessDeniedException;

    /**
     * add subgroup to this group
     * @param groupName
     * @throws UnsupportedOperationException if the implementation does not support writing
     * @throws AccessDeniedException if loggen in repository user does not sufficient rights
     * */
    public void addGroup(String groupName) throws UnsupportedOperationException, AccessDeniedException;

    /**
     * remove role from this group
     * @param roleName
     * @throws UnsupportedOperationException if the implementation does not support writing
     * @throws AccessDeniedException if loggen in repository user does not sufficient rights
     * */
    public void removeRole(String roleName) throws UnsupportedOperationException, AccessDeniedException;

    /**
     * remove subgroup from this group
     * @param groupName
     * @throws UnsupportedOperationException if the implementation does not support writing
     * @throws AccessDeniedException if loggen in repository user does not sufficient rights
     * */
    public void removeGroup(String groupName) throws UnsupportedOperationException, AccessDeniedException;

    /**
     * returns true if role exist in this group
     * @param roleName
     * @throws UnsupportedOperationException if the implementation does not exist
     * @throws AccessDeniedException if loggen in repository user does not sufficient rights
     * */
    public boolean hasRole(String roleName) throws UnsupportedOperationException, AccessDeniedException;

}
