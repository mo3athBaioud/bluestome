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
package info.magnolia.cms.taglibs.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Fabrizio Giustina
 * @version $Revision $ ($Author $)
 */
public class TableTag extends BodyTagSupport {

    /**
     * logger.
     */
    private static Logger log = LoggerFactory.getLogger(TableTag.class);

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private boolean header;

    private Map htmlAttributes = new HashMap();

    /**
     * Setter for <code>header</code>.
     * @param header show header
     */
    public void setHeader(boolean header) {
        this.header = header;
    }

    /**
     * Setter for <code>class</code>.
     * @param value html attribute
     */
    public void setClass(String value) {
        this.htmlAttributes.put("class", value);
    }

    /**
     * Setter for <code>style</code>.
     * @param value html attribute
     */
    public void setStyle(String value) {
        this.htmlAttributes.put("style", value);
    }

    /**
     * Setter for <code>id</code>.
     * @param value html attribute
     */
    public void setId(String value) {
        this.htmlAttributes.put("id", value);
    }

    /**
     * Setter for <code>cellspacing</code>.
     * @param value html attribute
     */
    public void setCellspacing(String value) {
        this.htmlAttributes.put("cellspacing", value);
    }

    /**
     * Setter for <code>cellpadding</code>.
     * @param value html attribute
     */
    public void setCellpadding(String value) {
        this.htmlAttributes.put("cellpadding", value);
    }

    /**
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    public int doEndTag() throws JspException {
        String data = getBodyContent().getString();
        JspWriter out = pageContext.getOut();

        if (StringUtils.isEmpty(data)) {
            return EVAL_PAGE;
        }

        try {
            out.print("<table cellspacing=\"0\" ");
            writeAttributes(out, htmlAttributes);
            out.print(">\n");

            String[] rows = data.split("\n");

            int startingRow = 0;

            if (header && rows.length > 0) {
                startingRow = 1; // for body
                out.print("<thead>\n");
                out.print("<tr>\n");

                String[] cols = StringUtils.split(rows[0], "\t");
                for (int col = 0; col < cols.length; col++) {
                    out.print("<th>");
                    out.print(cols[col]);
                    out.print("</th>\n");
                }

                out.print("</tr>\n");
                out.print("</thead>\n");

            }

            if (rows.length > startingRow) {
                out.print("<tbody>\n");

                for (int row = startingRow; row < rows.length; row++) {

                    out.print("<tr");

                    out.print(" class=\"");
                    out.print(row % 2 == 0 ? "even" : "odd");

                    out.print("\">\n");

                    String[] cols = StringUtils.split(rows[row], "\t");

                    for (int col = 0; col < cols.length; col++) {
                        out.print("<td>");
                        out.print(cols[col]);
                        out.print("</td>\n");
                    }
                    out.print("</tr>\n");

                }
                out.print("</tbody>\n");
            }
            out.print("</table>\n");
        }
        catch (IOException e) {
            // should never happen
            log.debug(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    /**
     * @param out
     * @throws IOException
     */
    private void writeAttributes(JspWriter out, Map attributes) throws IOException {
        for (Iterator iter = attributes.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String value = (String) attributes.get(name);
            if (StringUtils.isNotBlank(value)) {
                out.write(name);
                out.write("=\"");
                out.write(value);
                out.write("\" ");
            }
        }
    }

    /**
     * @see javax.servlet.jsp.tagext.TagSupport#release()
     */
    public void release() {
        super.release();
        header = false;
        htmlAttributes.clear();
    }
}