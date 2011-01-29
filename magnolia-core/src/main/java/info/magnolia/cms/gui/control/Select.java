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
package info.magnolia.cms.gui.control;

import info.magnolia.cms.core.Content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * @author Vinzenz Wyser
 * @version 2.0
 */
public class Select extends ControlImpl {

    private List options = new ArrayList();

    public Select() {
    }

    public Select(String name, String value) {
        super(name, value);
    }

    public Select(String name, Content websiteNode) {
        super(name, websiteNode);
    }

    public void setOptions(List l) {
        this.options = l;
    }

    public void setOptions(SelectOption option) {
        this.getOptions().add(option);
    }

    public void setOptions(String label, String value) {
        this.getOptions().add(new SelectOption(label, value));
    }

    public List getOptions() {
        return this.options;
    }

    public String getHtml() {
        StringBuffer html = new StringBuffer();
        html.append("<select"); //$NON-NLS-1$
        html.append(" name=\"" + this.getName() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        html.append(" id=\"" + this.getName() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        html.append(this.getHtmlCssClass());
        html.append(this.getHtmlCssStyles());
        html.append(this.getHtmlEvents());
        html.append(">"); //$NON-NLS-1$
        Iterator it = this.getOptions().iterator();
        while (it.hasNext()) {
            SelectOption o = (SelectOption) it.next();
            if (StringUtils.isNotEmpty(this.getValue())) {
                if (this.getValue().equals(o.getValue())) {
                    o.setSelected(true);
                }
                else {
                    o.setSelected(false);
                }
            }
            html.append(o.getHtml());
        }
        html.append("</select>"); //$NON-NLS-1$
        if (this.getSaveInfo()) {
            html.append(this.getHtmlSaveInfo());
        }
        return html.toString();
    }
}