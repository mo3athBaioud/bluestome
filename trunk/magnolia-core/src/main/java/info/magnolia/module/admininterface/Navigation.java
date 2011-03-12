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
package info.magnolia.module.admininterface;

import info.magnolia.cms.beans.config.ContentRepository;
import info.magnolia.cms.core.Content;
import info.magnolia.cms.core.ItemType;
import info.magnolia.cms.i18n.Messages;
import info.magnolia.cms.i18n.MessagesUtil;
import info.magnolia.cms.security.Permission;
import info.magnolia.cms.util.NodeDataUtil;
import info.magnolia.context.MgnlContext;

import java.text.MessageFormat;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is used to create the menud
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 */
public class Navigation {

    Logger log = LoggerFactory.getLogger(Navigation.class);

    /**
     * The node containing the menu and submenupoints
     */
    Content node;

    /**
     * The name of the Javascript variable
     */
    String jsName;

    /**
     * @param path the path to the menu
     */
    public Navigation(String path, String jsName) {
        try {
            // get it with system permission
            this.node = MgnlContext.getSystemContext().getHierarchyManager(ContentRepository.CONFIG).getContent(path);
            this.jsName = jsName;
        }
        catch (Exception e) {
            log.error("can't initialize the menu", e);
        }
    }

    /**
     * Generate the code to initialize the js navigation
     * @param name the name of the javascript menu variable
     * @return the javascript
     */
    public String getJavascript() {
        StringBuffer str = new StringBuffer();

        // name, id, text, link, icon
        String nodePattern = "{0}.addNode (\"{1}\", \"{2}\", \"{3}\", contextPath + \"{4}\");\n";
        // name, parentId, id, text, link, icon
        String subPattern = "{0}.getNode(\"{1}\").addNode (\"{2}\", \"{3}\", \"{4}\", contextPath + \"{5}\");\n";

        // loop over the menupoints
        for (Iterator iter = node.getChildren(ItemType.CONTENTNODE).iterator(); iter.hasNext();) {
            Content mp = (Content) iter.next();
            // check permission
            if(isMenuPointRendered(mp)){
                str.append(MessageFormat.format(nodePattern, new Object[]{
                    jsName,
                    mp.getUUID(),
                    getLabel(mp),
                    NodeDataUtil.getString(mp, "onclick"),
                    NodeDataUtil.getString(mp, "icon")}));
    
                // sub menupoints (2 level only)
                for (Iterator iterator = mp.getChildren(ItemType.CONTENTNODE).iterator(); iterator.hasNext();) {
                    Content sub = (Content) iterator.next();
                    if(isMenuPointRendered(sub)){
                        str.append(MessageFormat.format(subPattern, new Object[]{
                            jsName,
                            mp.getUUID(),
                            sub.getUUID(),
                            getLabel(sub),
                            NodeDataUtil.getString(sub, "onclick"),
                            NodeDataUtil.getString(sub, "icon")}));
                    }
                }
            }
        }

        return str.toString();
    }

    /**
     * @param mp
     * @return
     */
    protected Object getLabel(Content mp) {
        return NodeDataUtil.getI18NString(mp, "label");
    }

    /**
     * @param mp
     * @return
     */
    protected boolean isMenuPointRendered(Content mp) {
        return MgnlContext.getAccessManager(ContentRepository.CONFIG).isGranted(mp.getHandle(), Permission.READ);
    }

    /**
     * Get the first onclick in this menu. Used as the default src in the content iframe
     * @return the href
     */
    public String getFirstId() {
        return getFirstId(node);
    }

    private String getFirstId(Content node) {
        for (Iterator iter = node.getChildren(ItemType.CONTENTNODE).iterator(); iter.hasNext();) {
            Content sub = (Content) iter.next();
            if(isMenuPointRendered(sub)){
                if (StringUtils.isNotEmpty(NodeDataUtil.getString(sub, "onclick"))) {
                    return sub.getUUID();
                }
                String uuid = getFirstId(sub);
                if (StringUtils.isNotEmpty(uuid)) {
                    return uuid;
                }
            }
        }
        return "";
    }

}