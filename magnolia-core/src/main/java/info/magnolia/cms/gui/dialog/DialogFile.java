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
package info.magnolia.cms.gui.dialog;

import info.magnolia.cms.beans.config.MIMEMapping;
import info.magnolia.cms.core.Content;
import info.magnolia.cms.gui.control.File;
import info.magnolia.cms.gui.misc.CssConstants;
import info.magnolia.cms.gui.misc.Spacer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


/**
 * @author Vinzenz Wyser
 * @version 2.0
 */
public class DialogFile extends DialogBox {

    private List imageExtensions = new ArrayList();

    /**
     * Empty constructor should only be used by DialogFactory.
     */
    protected DialogFile() {
    }

    /**
     * @see info.magnolia.cms.gui.dialog.DialogControl#init(HttpServletRequest, HttpServletResponse, Content, Content)
     */
    public void init(HttpServletRequest request, HttpServletResponse response, Content websiteNode, Content configNode)
        throws RepositoryException {
        super.init(request, response, websiteNode, configNode);
        initImageExtensions();
    }

    public List getImageExtensions() {
        return this.imageExtensions;
    }

    public void setImageExtensions(List l) {
        this.imageExtensions = l;
    }

    public void initImageExtensions() {
        this.getImageExtensions().add("jpg"); //$NON-NLS-1$
        this.getImageExtensions().add("jpeg"); //$NON-NLS-1$
        this.getImageExtensions().add("gif"); //$NON-NLS-1$
        this.getImageExtensions().add("png"); //$NON-NLS-1$
        this.getImageExtensions().add("bpm"); //$NON-NLS-1$
        this.getImageExtensions().add("swf"); //$NON-NLS-1$
    }

    /**
     * @see info.magnolia.cms.gui.dialog.DialogControl#drawHtml(Writer)
     */
    public void drawHtml(Writer out) throws IOException {
        File control = getFileControl();
        control.setType(this.getConfigValue("type", PropertyType.TYPENAME_STRING)); //$NON-NLS-1$
        control.setSaveInfo(false); // set manualy below
        control.setCssClass(CssConstants.CSSCLASS_FILE);
        control.setCssClassFileName(CssConstants.CSSCLASS_EDIT);
        control.setCssStyles("width", this.getConfigValue("width", "100%")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        this.drawHtmlPre(out);

        String width = this.getConfigValue("width", "100%"); //$NON-NLS-1$ //$NON-NLS-2$
        boolean showImage = this.getImageExtensions().contains(control.getExtension().toLowerCase());

        String htmlControlBrowse = control.getHtmlBrowse();
        StringBuffer htmlControlFileName = new StringBuffer();
        htmlControlFileName.append("<span class=\"" //$NON-NLS-1$
            + CssConstants.CSSCLASS_DESCRIPTION
            + "\">" //$NON-NLS-1$
            + getMessage("dialog.file.filename") //$NON-NLS-1$
            + "</span>"); //$NON-NLS-1$
        htmlControlFileName.append(Spacer.getHtml(1, 1));
        htmlControlFileName.append(control.getHtmlFileName() + "<span id=\"" //$NON-NLS-1$
            + this.getName()
            + "_fileNameExtension\">." //$NON-NLS-1$
            + control.getExtension()
            + "</span>"); //$NON-NLS-1$
        String htmlContentEmpty = htmlControlBrowse + Spacer.getHtml(0, 0) + htmlControlFileName;
        out.write("<div id=\"" + this.getName() + "_contentDiv\" style=\"width:100%;\">"); //$NON-NLS-1$ //$NON-NLS-2$
        boolean exists = false;

        if (this.getWebsiteNode() != null) {
            exists = this.getWebsiteNode().getNodeData(this.getName()).isExist();
        }

        if (!exists) {
            out.write(htmlContentEmpty);
            out.write("</div>"); //$NON-NLS-1$
        }
        else {
            if (showImage) {

                out.write("\n<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"" + width + "\">"); //$NON-NLS-1$ //$NON-NLS-2$
                out.write("<tr><td class=\"" + CssConstants.CSSCLASS_FILEIMAGE + "\">"); //$NON-NLS-1$ //$NON-NLS-2$

                if ("swf".equals(control.getExtension().toLowerCase())) {

                    // flash movie
                    out.write("<object type=\"application/x-shockwave-flash\" data=\"");
                    out.write(this.getRequest().getContextPath());
                    out.write(control.getHandle());
                    out.write("\" title=\"");
                    out.write(control.getFileName());
                    out.write("\" ");
                    out.write("width=\"150\" ");
                    out.write("height=\"100\" ");
                    out.write(">");

                    out.write("<param name=\"movie\" value=\"");
                    out.write(this.getRequest().getContextPath());
                    out.write(control.getHandle());
                    out.write("\"/>");

                    out.write("</object>\n");

                }
                else {
                    // standard image

                    // todo: image thumbnail template
                    // out.write("<img src=\""+ this.getRequest().getContextPath()
                    // +THUMB_PATH+"?src="+control.getHandle()+"\"
                    // class=\""+CSSCLASS_FILEIMAGE+"\">");
                    // tmp workaround: resize in html ...

                    out.write("<img width=\"150\" src=\""); //$NON-NLS-1$
                    out.write(this.getRequest().getContextPath());
                    out.write(control.getHandle());
                    out.write("\" class=\""); //$NON-NLS-1$
                    out.write(CssConstants.CSSCLASS_FILEIMAGE);
                    out.write("\" alt=\""); //$NON-NLS-1$
                    out.write(control.getFileName());
                    out.write("\" title=\""); //$NON-NLS-1$
                    out.write(control.getFileName());
                    out.write("\" />\n"); //$NON-NLS-1$

                    String imgwidth = control.getImageWidth();
                    if (StringUtils.isNotEmpty(imgwidth)) {
                        out.write("<em style='white-space:nowrap'>"); //$NON-NLS-1$

                        out.write("width: "); //$NON-NLS-1$
                        out.write(imgwidth);

                        out.write(" height: "); //$NON-NLS-1$
                        out.write(control.getImageHeight());

                        out.write("</em>\n"); //$NON-NLS-1$
                    }

                }

                out.write("</td><td>"); //$NON-NLS-1$
            }
            out.write(htmlControlFileName.toString());
            if (!showImage) {
                String iconPath = MIMEMapping.getMIMETypeIcon(control.getExtension());

                out.write(Spacer.getHtml(0, 0));
                out.write("<a href=" + this.getRequest().getContextPath() + control.getPath() + " target=\"_blank\">"); //$NON-NLS-1$ //$NON-NLS-2$
                out.write("<img src=\"" //$NON-NLS-1$
                    + this.getRequest().getContextPath()
                    + iconPath
                    + "\" class=\"" //$NON-NLS-1$
                    + CssConstants.CSSCLASS_FILEICON
                    + "\" border=\"0\">"); //$NON-NLS-1$
                out.write(control.getFileName() + "." + control.getExtension() + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            out.write(Spacer.getHtml(12, 12));
            out.write(control.getHtmlRemove("mgnlDialogFileRemove('" + this.getName() + "');")); //$NON-NLS-1$ //$NON-NLS-2$
            if (showImage) {
                out.write("</td></tr></table>"); //$NON-NLS-1$
            }
            out.write("</div>\n"); //$NON-NLS-1$
            out.write("<div style=\"position:absolute;top:-500px;left:-500px;visibility:hidden;\">\n<textarea id=\""); //$NON-NLS-1$
            out.write(this.getName());
            out.write("_contentEmpty\">");//$NON-NLS-1$
            out.write(htmlContentEmpty);

            // @todo should be escaped, but we need to test it
            // out.write(StringEscapeUtils.escapeXml(htmlContentEmpty));
            out.write("</textarea>\n</div>\n"); //$NON-NLS-1$
        }
        control.setSaveInfo(true);
        out.write(control.getHtmlSaveInfo());
        control.setNodeDataTemplate(this.getConfigValue("nodeDataTemplate", null)); //$NON-NLS-1$
        out.write(control.getHtmlNodeDataTemplate());
        this.drawHtmlPost(out);
    }

    /**
     */
    protected File getFileControl() {
        File control = new File(this.getName(), this.getWebsiteNode());
        return control;
    }
}
