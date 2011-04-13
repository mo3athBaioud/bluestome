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
import info.magnolia.cms.core.NodeData;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.PropertyType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * @author Vinzenz Wyser
 * @version 2.0
 */
public class ControlImpl implements Control {

    public static final int BUTTONTYPE_PUSHBUTTON = 0;

    public static final int BUTTONTYPE_CHECKBOX = 1;

    public static final int BUTTONTYPE_RADIO = 2;

    public static final int BUTTONSTATE_NORMAL = 0;

    public static final int BUTTONSTATE_MOUSEOVER = 1;

    public static final int BUTTONSTATE_MOUSEDOWN = 2;

    public static final int BUTTONSTATE_PUSHED = 3;

    public static final int BUTTONSTATE_DISABLED = 4; // not yet supported

    public static final int VALUETYPE_SINGLE = 0;

    public static final int VALUETYPE_MULTIPLE = 1;

    public static final int ENCODING_NO = 0;

    public static final int ENCODING_BASE64 = 1;

    public static final int ENCODING_UNIX = 2;

    public static final int RICHEDIT_NONE = 0;

    public static final int RICHEDIT_KUPU = 1;

    public static final int RICHEDIT_FCK = 2;

    public static final String CSSCLASS_CONTROLBUTTON = "mgnlControlButton"; //$NON-NLS-1$

    public static final String CSSCLASS_CONTROLBUTTONSMALL = "mgnlControlButtonSmall"; //$NON-NLS-1$

    public static final String CSSCLASS_CONTROLBAR = "mgnlControlBar"; //$NON-NLS-1$

    public static final String CSSCLASS_CONTROLBARSMALL = "mgnlControlBarSmall"; //$NON-NLS-1$

    private int valueType = VALUETYPE_SINGLE;

    private int encoding = ENCODING_NO;

    private int isRichEditValue = RICHEDIT_NONE;

    private String label;

    private String name;

    private String id;

    private String value;

    private List values = new ArrayList(); // mulitple values (checkbox)

    private Map events = new Hashtable();

    private Content websiteNode;

    private String htmlPre;

    private String htmlInter;

    private String htmlPost;

    private int type = PropertyType.STRING;

    private boolean saveInfo = true;

    private String cssClass = StringUtils.EMPTY;

    private Map cssStyles = new Hashtable();

    private String path;

    private String nodeCollectionName;

    private String nodeName;

    private String paragraph;

    private HttpServletRequest request;

    public ControlImpl() {
    }

    public ControlImpl(String name, String value) {
        this.setName(name);
        this.setValue(value);
    }

    public ControlImpl(String name, List values) {
        this.setName(name);
        this.setValues(values);
    }

    public ControlImpl(String name, Content websiteNode) {
        this.setName(name);
        this.setWebsiteNode(websiteNode);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setNodeCollectionName(String nodeCollectionName) {
        this.nodeCollectionName = nodeCollectionName;
    }

    public String getNodeCollectionName() {
        return this.nodeCollectionName;
    }

    public String getNodeCollectionName(String nullOrEmptyValue) {
        if (StringUtils.isEmpty(this.getNodeCollectionName())) {
            return nullOrEmptyValue;
        }

        return this.getNodeCollectionName();
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public String getNodeName(String nullOrEmptyValue) {
        if (StringUtils.isEmpty(this.getNodeName())) {
            return nullOrEmptyValue;
        }

        return this.getNodeName();
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getParagraph() {
        return this.paragraph;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String s) {
        this.id = s;
    }

    public String getId() {
        return this.id;
    }

    public String getHtmlId() {
        if (StringUtils.isNotEmpty(this.getId())) {
            return " id=\"" + this.getId() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        }

        return StringUtils.EMPTY;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        if (this.value != null) {
            return this.value;
        }

        try {
            return this.getWebsiteNode().getNodeData(this.getName()).getString();
        }
        catch (Exception e) {
            return StringUtils.EMPTY;
        }

    }

    public void setValues(List values) {
        this.values = values;
    }

    public List getValues() {
        if (this.values.size() != 0) {
            return this.values;
        }
        try {
            Iterator it = this.getWebsiteNode().getContent(this.getName()).getNodeDataCollection().iterator();
            List l = new ArrayList();
            while (it.hasNext()) {
                NodeData data = (NodeData) it.next();
                l.add(data.getString());
            }
            return l;
        }
        catch (Exception re) {
            return this.values;
        }
    }

    public void setWebsiteNode(Content c) {
        this.websiteNode = c;
    }

    public Content getWebsiteNode() {
        return this.websiteNode;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setEvent(String event, String action) {
        setEvent(event, action, false);
    }

    public void setEvent(String event, String action, boolean removeExisting) {
        String eventLower = event.toLowerCase();
        String existing = null;
        if (!removeExisting) {
            existing = (String) this.getEvents().get(eventLower);
        }
        if (existing == null) {
            existing = StringUtils.EMPTY;
        }

        this.getEvents().put(eventLower, existing + action);
    }

    public void setEvents(Map h) {
        this.events = h;
    }

    public Map getEvents() {
        return this.events;
    }

    public String getHtmlEvents() {
        StringBuffer html = new StringBuffer();
        Iterator en = this.getEvents().keySet().iterator();
        while (en.hasNext()) {
            String key = (String) en.next();
            html.append(" " + key + "=\"" + this.getEvents().get(key) + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return html.toString();
    }

    /**
     * Returns an empty string.
     * @see info.magnolia.cms.gui.control.Control#getHtml()
     */
    public String getHtml() {
        return StringUtils.EMPTY;
    }

    public void setHtmlPre(String s) {
        this.htmlPre = s;
    }

    public String getHtmlPre() {
        return this.getHtmlPre(StringUtils.EMPTY);
    }

    public String getHtmlPre(String nullValue) {
        if (this.htmlPre != null) {
            return this.htmlPre;
        }

        return nullValue;
    }

    public void setHtmlInter(String s) {
        this.htmlInter = s;
    }

    public String getHtmlInter() {
        return this.getHtmlInter(StringUtils.EMPTY);
    }

    public String getHtmlInter(String nullValue) {
        if (this.htmlInter != null) {
            return this.htmlInter;
        }

        return nullValue;
    }

    public void setHtmlPost(String s) {
        this.htmlPost = s;
    }

    public String getHtmlPost() {
        return this.getHtmlPost(StringUtils.EMPTY);
    }

    public String getHtmlPost(String nullValue) {
        if (this.htmlPost != null) {
            return this.htmlPost;
        }

        return nullValue;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setType(String s) {
        this.type = PropertyType.valueFromName(s);
    }

    public int getType() {
        return this.type;
    }

    public void setSaveInfo(boolean b) {
        this.saveInfo = b;
    }

    public boolean getSaveInfo() {
        return this.saveInfo;
    }

    public void setCssClass(String s) {
        this.cssClass = s;
    }

    public String getCssClass() {
        return this.cssClass;
    }

    public String getHtmlCssClass() {
        if (StringUtils.isNotEmpty(this.getCssClass())) {
            return " class=\"" + this.getCssClass() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        }

        return StringUtils.EMPTY;
    }

    public String getHtmlSaveInfo() {
        StringBuffer html = new StringBuffer();
        if (this.getSaveInfo()) {
            html.append("<input type=\"hidden\""); //$NON-NLS-1$
            html.append(" name=\"mgnlSaveInfo\""); //$NON-NLS-1$
            html.append(" value=\"" //$NON-NLS-1$
                + this.getName()
                + "," //$NON-NLS-1$
                + PropertyType.nameFromValue(this.getType())
                + "," //$NON-NLS-1$
                + this.getValueType()
                + "," //$NON-NLS-1$
                + this.getIsRichEditValue()
                + "," //$NON-NLS-1$
                + this.getEncoding()
                + "\""); //$NON-NLS-1$
            html.append(" />"); //$NON-NLS-1$
        }
        return html.toString();
    }

    public void setCssStyles(Map h) {
        this.cssStyles = h;
    }

    public void setCssStyles(String key, String value) {
        this.getCssStyles().put(key, value);
    }

    public Map getCssStyles() {
        return this.cssStyles;
    }

    public String getCssStyles(String key, String nullValue) {
        if (this.getCssStyles().containsKey(key)) {
            return (String) this.getCssStyles().get(key);
        }
        return nullValue;
    }

    public String getCssStyles(String key) {
        return this.getCssStyles(key, StringUtils.EMPTY);
    }

    public String getHtmlCssStyles() {
        StringBuffer html = new StringBuffer();
        Iterator en = this.getCssStyles().keySet().iterator();
        while (en.hasNext()) {
            String key = (String) en.next();
            html.append(key + ":" + this.getCssStyles().get(key) + ";"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (html.length() > 0) {
            return " style=\"" + html + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return StringUtils.EMPTY;
    }

    public void setValueType(int i) {
        this.valueType = i;
    }

    public int getValueType() {
        return this.valueType;
    }

    public void setEncoding(int i) {
        this.encoding = i;
    }

    public int getEncoding() {
        return this.encoding;
    }

    public void setIsRichEditValue(int i) {
        this.isRichEditValue = i;
    }

    public int getIsRichEditValue() {
        return this.isRichEditValue;
    }

    public static String escapeHTML(String str) {
        return str.replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    }

}