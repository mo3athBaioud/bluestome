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
package info.magnolia.module.admininterface.trees;

import info.magnolia.cms.beans.config.Template;
import info.magnolia.cms.beans.config.TemplateManager;
import info.magnolia.cms.core.Content;
import info.magnolia.cms.gui.control.TreeColumn;
import info.magnolia.cms.gui.control.TreeColumnHtmlRenderer;
import info.magnolia.cms.i18n.TemplateMessagesUtil;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;


/**
 * @author philipp
 */
public class TemplateTreeColumnHtmlRenderer implements TreeColumnHtmlRenderer {

    /**
     * @see info.magnolia.cms.gui.control.TreeColumnHtmlRenderer#renderHtml(TreeColumn, Content)
     */
    public String renderHtml(TreeColumn treeColumn, Content content) {
        String templateName = content.getMetaData().getTemplate();
        String strKey = this.findTemplateKey(templateName);
        // TODO enable an individual message bundle for the templates
        return TemplateMessagesUtil.getMessages().getWithDefault(strKey, strKey);
    }

    private String findTemplateKey(String templateName) {
        for (Iterator iter = TemplateManager.getInstance().getAvailableTemplates(); iter.hasNext();) {
            Template template = (Template) iter.next();
            if (StringUtils.equals(templateName, template.getName())) {
                return template.getTitle();
            }
        }
        return templateName;
    }

}