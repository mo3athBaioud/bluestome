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
package info.magnolia.module.admininterface.trees;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import info.magnolia.cms.beans.config.Server;
import info.magnolia.cms.beans.config.Subscriber;
import info.magnolia.cms.core.ItemType;
import info.magnolia.cms.core.MetaData;
import info.magnolia.cms.gui.control.ContextMenu;
import info.magnolia.cms.gui.control.ContextMenuItem;
import info.magnolia.cms.gui.control.FunctionBarItem;
import info.magnolia.cms.gui.control.Tree;
import info.magnolia.cms.gui.control.TreeColumn;
import info.magnolia.cms.i18n.Messages;
import info.magnolia.cms.i18n.MessagesManager;
import info.magnolia.module.admininterface.AdminTreeConfiguration;


/**
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 *
 */
public class UsersTreeConfiguration implements AdminTreeConfiguration {

    /**
     * @see info.magnolia.module.admininterface.AdminTreeConfiguration#prepareTree(info.magnolia.cms.gui.control.Tree, boolean, javax.servlet.http.HttpServletRequest)
     */
    public void prepareTree(Tree tree, boolean browseMode, HttpServletRequest request) {
        Messages msgs = MessagesManager.getMessages();

        tree.setDrawShifter(false);
        // context path is already added by Tree
        tree.setIconPage(Tree.ICONDOCROOT + "pawn_glass_yellow.gif"); //$NON-NLS-1$
        tree.setIconOndblclick("mgnlTreeMenuOpenDialog(" + tree.getJavascriptTree() //$NON-NLS-1$
            // + ",'.magnolia/adminCentral/users/dialog.html');");
            + ",'.magnolia/dialogs/useredit.html');"); //$NON-NLS-1$
        tree.addItemType(ItemType.USER);

        TreeColumn column0 = new TreeColumn(tree.getJavascriptTree(), request);
        column0.setIsLabel(true);
        column0.setHtmlEdit();
        column0.setTitle(msgs.get("tree.users.name")); //$NON-NLS-1$
        column0.setWidth(2);

        TreeColumn column1 = new TreeColumn(tree.getJavascriptTree(), request);
        column1.setName("title"); //$NON-NLS-1$
        column1.setHtmlEdit();
        column1.setTitle(msgs.get("tree.users.fullname")); //$NON-NLS-1$
        column1.setWidth(2);

        TreeColumn columnIcons = new TreeColumn(tree.getJavascriptTree(), request);
        columnIcons.setCssClass(StringUtils.EMPTY);
        columnIcons.setWidth(1);
        columnIcons.setIsIcons(true);
        columnIcons.setIconsActivation(true);
        
        TreeColumn column2 = new TreeColumn(tree.getJavascriptTree(), request);
        column2.setName(MetaData.LAST_MODIFIED);
        column2.setIsMeta(true);
        column2.setDateFormat("yyyy-MM-dd, HH:mm"); //$NON-NLS-1$
        column2.setTitle(msgs.get("tree.users.date")); //$NON-NLS-1$
        column2.setWidth(2);

        tree.addColumn(column0);

        if (!browseMode) {
            tree.addColumn(column1);
            if (Server.isAdmin() || Subscriber.isSubscribersEnabled()) {
                tree.addColumn(columnIcons);
            }
            tree.addColumn(column2);
        }
    }

    /**
     * @see info.magnolia.module.admininterface.AdminTreeConfiguration#prepareContextMenu(info.magnolia.cms.gui.control.Tree, boolean, javax.servlet.http.HttpServletRequest)
     */
    public void prepareContextMenu(Tree tree, boolean browseMode, HttpServletRequest request) {
        Messages msgs = MessagesManager.getMessages();

        ContextMenuItem menuOpen = new ContextMenuItem("edit");
        menuOpen.setLabel(msgs.get("tree.users.menu.edit")); //$NON-NLS-1$
        menuOpen.setIcon(request.getContextPath() + "/.resources/icons/16/pawn_glass_yellow.gif"); //$NON-NLS-1$
        menuOpen.setOnclick("mgnlTreeMenuOpenDialog(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ",'.magnolia/dialogs/useredit.html');"); //$NON-NLS-1$
        menuOpen.addJavascriptCondition("new mgnlTreeMenuItemConditionSelectedNotRoot(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ")"); //$NON-NLS-1$

        ContextMenuItem menuNew = new ContextMenuItem("new");
        menuNew.setLabel(msgs.get("tree.users.menu.new")); //$NON-NLS-1$
        menuNew.setIcon(request.getContextPath() + "/.resources/icons/16/pawn_glass_yellow_add.gif"); //$NON-NLS-1$
        menuNew.setOnclick(tree.getJavascriptTree() + ".createRootNode('" //$NON-NLS-1$
            + ItemType.USER.getSystemName() + "');"); //$NON-NLS-1$

        ContextMenuItem menuDelete = new ContextMenuItem("delete");
        menuDelete.setLabel(msgs.get("tree.users.menu.delete")); //$NON-NLS-1$
        menuDelete.setIcon(request.getContextPath() + "/.resources/icons/16/delete2.gif"); //$NON-NLS-1$
        menuDelete.setOnclick(tree.getJavascriptTree() + ".deleteNode();"); //$NON-NLS-1$
        menuDelete.addJavascriptCondition("new mgnlTreeMenuItemConditionSelectedNotRoot(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ")"); //$NON-NLS-1$
        
        ContextMenuItem menuMove = new ContextMenuItem("move");
        menuMove.setLabel(msgs.get("tree.users.menu.move")); //$NON-NLS-1$
        menuMove.setIcon(request.getContextPath() + "/.resources/icons/16/up_down.gif"); //$NON-NLS-1$
        menuMove.setOnclick(tree.getJavascriptTree() + ".cutNode();"); //$NON-NLS-1$
        menuMove.addJavascriptCondition("new mgnlTreeMenuItemConditionSelectedNotRoot(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ")"); //$NON-NLS-1$
        
        ContextMenuItem menuCopy = new ContextMenuItem("copy");
        menuCopy.setLabel(msgs.get("tree.users.menu.copy")); //$NON-NLS-1$
        menuCopy.setIcon(request.getContextPath() + "/.resources/icons/16/copy.gif"); //$NON-NLS-1$
        menuCopy.setOnclick(tree.getJavascriptTree() + ".copyNode();"); //$NON-NLS-1$
        menuCopy.addJavascriptCondition("new mgnlTreeMenuItemConditionSelectedNotRoot(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ")"); //$NON-NLS-1$
        
        ContextMenuItem menuActivateExcl = new ContextMenuItem("activate");
        menuActivateExcl.setLabel(msgs.get("tree.users.menu.activate")); //$NON-NLS-1$
        menuActivateExcl.setIcon(request.getContextPath() + "/.resources/icons/16/arrow_right_green.gif"); //$NON-NLS-1$
        menuActivateExcl.setOnclick(tree.getJavascriptTree() + ".activateNode(" + Tree.ACTION_ACTIVATE + ",false);"); //$NON-NLS-1$ //$NON-NLS-2$
        menuActivateExcl.addJavascriptCondition("new mgnlTreeMenuItemConditionSelectedNotRoot(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ")"); //$NON-NLS-1$
        
        ContextMenuItem menuDeActivate = new ContextMenuItem("deactivate");
        menuDeActivate.setLabel(msgs.get("tree.users.menu.deactivate")); //$NON-NLS-1$
        menuDeActivate.setIcon(request.getContextPath() + "/.resources/icons/16/arrow_left_red.gif"); //$NON-NLS-1$
        menuDeActivate.setOnclick(tree.getJavascriptTree() + ".deActivateNode(" + Tree.ACTION_DEACTIVATE + ");"); //$NON-NLS-1$ //$NON-NLS-2$
        menuDeActivate.addJavascriptCondition("new mgnlTreeMenuItemConditionSelectedNotRoot(" //$NON-NLS-1$
            + tree.getJavascriptTree() + ")"); //$NON-NLS-1$

        if (!Subscriber.isSubscribersEnabled()) {
            menuActivateExcl.addJavascriptCondition("new mgnlTreeMenuItemConditionBoolean(false)"); //$NON-NLS-1$
            menuDeActivate.addJavascriptCondition("new mgnlTreeMenuItemConditionBoolean(false)"); //$NON-NLS-1$
        }

        if (!browseMode
            ) {
            tree.addMenuItem(menuOpen);
            tree.addMenuItem(menuNew);
            tree.addMenuItem(null); // line
            tree.addMenuItem(menuDelete);

            tree.addMenuItem(null); // line
            tree.addMenuItem(menuActivateExcl);
            tree.addMenuItem(menuDeActivate);
        }
        else {
            ContextMenuItem menuRefresh = new ContextMenuItem("refresh");
            menuRefresh.setLabel(msgs.get("tree.menu.refresh")); //$NON-NLS-1$
            menuRefresh.setIcon(request.getContextPath() + "/.resources/icons/16/refresh.gif"); //$NON-NLS-1$
            menuRefresh.setOnclick(tree.getJavascriptTree() + ".refresh();"); //$NON-NLS-1$
            tree.addMenuItem(menuRefresh);
        }
    }

    /**
     * @see info.magnolia.module.admininterface.AdminTreeConfiguration#prepareFunctionBar(info.magnolia.cms.gui.control.Tree, boolean, javax.servlet.http.HttpServletRequest)
     */
    public void prepareFunctionBar(Tree tree, boolean browseMode, HttpServletRequest request) {
        Messages msgs = MessagesManager.getMessages();
        ContextMenu cm = tree.getMenu();
        ContextMenuItem cmItem = cm.getMenuItemByName("edit");
        if (cmItem != null) {
            tree.addFunctionBarItem(new FunctionBarItem(cmItem));
        }
        cmItem = cm.getMenuItemByName("new");
        if (cmItem != null) {
            tree.addFunctionBarItem(new FunctionBarItem(cmItem));
        }
        // null is separator :)
        tree.addFunctionBarItem(null);
        cmItem = cm.getMenuItemByName("activate");
        if (cmItem != null) {
            tree.addFunctionBarItem(new FunctionBarItem(cmItem));
        }
        cmItem = cm.getMenuItemByName("deactivate");
        if (cmItem != null) {
            tree.addFunctionBarItem(new FunctionBarItem(cmItem));
        }
        tree.addFunctionBarItem(null);
        ContextMenuItem menuRefresh = new ContextMenuItem("refresh");
        menuRefresh.setLabel(msgs.get("tree.menu.refresh")); //$NON-NLS-1$
        menuRefresh.setIcon(request.getContextPath() + "/.resources/icons/16/refresh.gif"); //$NON-NLS-1$
        menuRefresh.setOnclick(tree.getJavascriptTree() + ".refresh();"); //$NON-NLS-1$
        tree.addFunctionBarItem(new FunctionBarItem(menuRefresh));
    }

}
