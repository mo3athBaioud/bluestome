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

import info.magnolia.cms.beans.config.Server;
import info.magnolia.cms.core.Content;
import info.magnolia.cms.core.NodeData;
import info.magnolia.cms.util.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @author Marcel Salathe
 * @version $Revision $ ($Author $)
 * @deprecated
 */
public class FileSrc extends TagSupport {

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 222L;

    /**
     * Logger.
     */
    private static Logger log = LoggerFactory.getLogger(FileSrc.class);

    private transient NodeData nodeData;

    private transient Content contentNode;

    private transient Content actpage;

    private String nodeDataName = StringUtils.EMPTY;

    private String contentNodeName = StringUtils.EMPTY;

    private String fileNameOnly = StringUtils.EMPTY;

    private HttpServletRequest request;

    private String fileExtension;

    private String fileName;

    private String fileExtendedName;

    private String slash = StringUtils.EMPTY;

    /**
     * @deprecated
     */
    public void setAtomName(String name) {
        this.setNodeDataName(name);
    }

    /**
     * @param nodeDataName
     * @deprecated
     */
    public void setNodeDataName(String nodeDataName) {
        this.nodeDataName = nodeDataName;
    }

    /**
     * @deprecated
     */
    public void setContainerName(String name) {
        this.setContentNodeName(name);
    }

    /**
     * @param contentNodeName
     * @deprecated
     */
    public void setContentNodeName(String contentNodeName) {
        this.contentNodeName = contentNodeName;
    }

    /**
     * @param value
     * @deprecated
     */
    public void setFileNameOnly(String value) {
        this.fileNameOnly = "true"; //$NON-NLS-1$
    }

    /**
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() {
        this.request = (HttpServletRequest) pageContext.getRequest();
        this.actpage = Resource.getCurrentActivePage(request);
        if (StringUtils.isNotEmpty(this.contentNodeName)) {
            try {
                this.contentNode = this.actpage.getContent(this.contentNodeName);
            }
            catch (RepositoryException re) {
                writeSrc(StringUtils.EMPTY);
            }
            if (this.contentNode == null) {
                writeSrc(StringUtils.EMPTY);
                return SKIP_BODY;
            }
        } else {
            this.contentNode = Resource.getLocalContentNode(request);
            if (this.contentNode == null) {
                this.contentNode = Resource.getGlobalContentNode(request);
            }
            if (this.contentNode != null) {
                this.contentNodeName = this.contentNode.getName();
            } else {
                writeSrc(StringUtils.EMPTY);
                return SKIP_BODY;
            }
        }
        if (StringUtils.isEmpty(this.nodeDataName)) {
            writeSrc(StringUtils.EMPTY);
            return SKIP_BODY;
        }
        try {
            this.nodeData = this.contentNode.getNodeData(this.contentNodeName);
        }
        catch (Exception e) {
            writeSrc(StringUtils.EMPTY);
            return SKIP_BODY;
        }
        if (this.nodeData == null) {
            writeSrc(StringUtils.EMPTY);
            return SKIP_BODY;
        }
        setFileProperties();

        String contentNodeCollectionName = (String) pageContext.getAttribute("contentNodeCollectionName", //$NON-NLS-1$
                PageContext.REQUEST_SCOPE);
        if (this.fileNameOnly.equals("true")) { //$NON-NLS-1$
            try {
                writeSrc(this.fileExtendedName);
            }
            catch (Exception e) {
                if (log.isDebugEnabled())
                    log.debug(e.getMessage());
            }
        } else {
            if (contentNodeCollectionName == null) {
                // we are not in a loop
                try {
                    writeSrc(this.contentNode.getHandle() + "/" //$NON-NLS-1$
                            + this.nodeDataName
                            + this.slash
                            + this.fileExtendedName);
                }
                catch (Exception e) {
                    if (log.isDebugEnabled())
                        log.debug(e.getMessage());
                }
            } else {
                try {
                    writeSrc(Resource.getLocalContentNode(request).getHandle() + "/" //$NON-NLS-1$
                            + this.nodeDataName
                            + this.slash
                            + this.fileExtendedName);
                }
                catch (Exception e) {
                    if (log.isDebugEnabled())
                        log.debug(e.getMessage());
                }
            }
        }
        return EVAL_PAGE;
    }

    private void writeSrc(String src) {
        JspWriter out = pageContext.getOut();
        try {
            out.print(src);
        }
        catch (Exception e) {
            if (log.isDebugEnabled())
                log.debug("Exception caught: " + e.getMessage(), e); //$NON-NLS-1$
        }
    }

    /**
     * @deprecated
     */
    private void setFileProperties() {
        this.fileExtension = Server.getDefaultExtension();
        Content properties = null;
        String contentNodeCollectionName = (String) pageContext.getAttribute("contentNodeCollectionName", //$NON-NLS-1$
                PageContext.REQUEST_SCOPE);
        if (contentNodeCollectionName == null) {
            // we are not in a loop
            try {
                properties = Resource.getGlobalContentNode(this.request).getContent(this.nodeDataName + "_properties"); //$NON-NLS-1$
            }
            catch (Exception e) {
                if (log.isDebugEnabled())
                    log.debug(e.getMessage());
            }
        } else {
            try {
                properties = Resource.getLocalContentNode(this.request).getContent(this.nodeDataName + "_properties"); //$NON-NLS-1$
            }
            catch (Exception e) {
                if (log.isDebugEnabled())
                    log.debug("Exception caught: " + e.getMessage(), e); //$NON-NLS-1$
            }
        }
        if (properties != null) {
            this.fileName = properties.getNodeData("fileName").getString(); //$NON-NLS-1$
            this.fileExtension = properties.getNodeData("extension").getString(); //$NON-NLS-1$
            if (StringUtils.isEmpty(this.fileName)) {
                this.fileExtendedName = "." + this.fileExtension; //$NON-NLS-1$
            } else {
                this.slash = "/"; //$NON-NLS-1$
                this.fileExtendedName = this.fileName;
                int posLastDot = this.fileName.lastIndexOf("."); //$NON-NLS-1$
                int posExt = this.fileName.lastIndexOf("." + this.fileExtension); //$NON-NLS-1$
                if (posExt == -1 || (posExt != -1 && posExt != posLastDot)) {
                    // magnolia v 1.0: fileName saved with extension
                    this.fileExtendedName += "." + this.fileExtension; //$NON-NLS-1$
                }
            }
        }
    }
}
