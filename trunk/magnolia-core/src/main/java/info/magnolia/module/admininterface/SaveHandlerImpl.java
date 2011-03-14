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
package info.magnolia.module.admininterface;

import info.magnolia.cms.beans.config.ContentRepository;
import info.magnolia.cms.beans.runtime.Document;
import info.magnolia.cms.beans.runtime.MultipartForm;
import info.magnolia.cms.core.*;
import info.magnolia.cms.gui.control.ControlImpl;
import info.magnolia.cms.gui.control.File;
import info.magnolia.cms.gui.fckeditor.FCKEditorTmpFiles;
import info.magnolia.cms.gui.misc.FileProperties;
import info.magnolia.cms.security.AccessDeniedException;
import info.magnolia.cms.security.Digester;
import info.magnolia.cms.util.*;
import info.magnolia.context.MgnlContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.commons.lang.time.DateFormatUtils;
import org.devlib.schmidt.imageinfo.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.text.ParseException;


/**
 * This class handels the saving in the dialogs. It uses the mgnlSaveInfo parameters sendend from the browser to store
 * the data in the node.The structure of the parameter is the following: <br>
 * <code>name, type, valueType, isRichEditValue, encoding</code> <p/> To find the consts see ControlImpl <table>
 * <tr>
 * <td>name</td>
 * <td>the name of the field</td>
 * </tr>
 * <tr>
 * <td>type</td>
 * <td>string, boolean, ...</td>
 * </tr>
 * <tr>
 * <td>valueType</td>
 * <td>single, multiple</td>
 * </tr>
 * <tr>
 * <td>isRichEditValue</td>
 * <td>value from an editor</td>
 * </tr>
 * <tr>
 * <td>encoding</td>
 * <td>base64, unix, none</td>
 * </tr>
 * </table>
 * @author Vinzenz Wyser
 * @version 2.0
 */
public class SaveHandlerImpl implements SaveHandler {

    /**
     * Logger
     */
    public static Logger log = LoggerFactory.getLogger(SaveHandlerImpl.class);

    /**
     * The from, containing all the fields and files. This form is generated by magnolia.
     */
    private MultipartForm form;

    /**
     * creates the node if it is not present
     */
    private boolean create;

    private ItemType creationItemType = ItemType.CONTENT;

    /**
     * The name of the repository to store the data. Website is default.
     */
    private String repository = ContentRepository.WEBSITE;

    /**
     * Path to the page
     */
    private String path;

    /**
     * If the saved paragraph is saved to a node collection
     */
    private String nodeCollectionName;

    /**
     * The name of the node saving to. mgnlNew if a new node should get creaetd
     */
    private String nodeName;

    /**
     * The current paragraph type. This information is saved under the node to enable a proper rendering.
     */
    private String paragraph;

    /**
     * Call init to initialize the object
     */
    public SaveHandlerImpl() {
    }

    /**
     * Initialize the SaveHandlerImpl control.
     * @param form the form generated from the request due to handle multipart forms
     */
    public void init(MultipartForm form) {
        this.setForm(form);
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#save()
     */
    public boolean save() {
        synchronized (ExclusiveWrite.getInstance()) {
            String[] saveInfos = getForm().getParameterValues("mgnlSaveInfo"); // name,type,propertyOrNode
            // //$NON-NLS-1$
            String path = this.getPath();

            HierarchyManager hm = MgnlContext.getHierarchyManager(this.getRepository());
            try {
                // get the node to save
                Content page = this.getPageNode(hm);

                if (page == null) {
                    // an error should have been logged in getPageNode() avoid NPEs!
                    return false;
                }

                Content node = this.getSaveNode(hm, page);

                // this value can get used later on to find this node
                this.setNodeName(node.getName());
                if (StringUtils.isEmpty(node.getMetaData().getTemplate())) {
                    node.getMetaData().setTemplate(this.getParagraph());
                }

                // update meta data (e.g. last modified) of this paragraph and the page
                node.updateMetaData();
                page.updateMetaData();

                // loop all saveInfo controls; saveInfo format: name, type, valueType(single|multiple, )
                for (int i = 0; i < saveInfos.length; i++) {
                    String saveInfo = saveInfos[i];
                    processSaveInfo(node, saveInfo);
                }
                if (log.isDebugEnabled()) {
                    log.debug("Saving {}", path); //$NON-NLS-1$
                }
                hm.save();
            }
            catch (RepositoryException re) {
                log.error(re.getMessage(), re);
                return false;
            }
        }
        return true;
    }

    /**
     * This method cears about one mgnlSaveInfo. It adds the value to the node
     * @param node node to add data
     * @param saveInfo <code>name, type, valueType, isRichEditValue, encoding</code>
     * @throws PathNotFoundException exception
     * @throws RepositoryException exception
     * @throws AccessDeniedException no access
     */
    protected void processSaveInfo(Content node, String saveInfo) throws PathNotFoundException, RepositoryException,
        AccessDeniedException {

        String name;
        int type = PropertyType.STRING;
        int valueType = ControlImpl.VALUETYPE_SINGLE;
        int isRichEditValue = 0;
        int encoding = ControlImpl.ENCODING_NO;
        String[] values = {StringUtils.EMPTY};
        if (StringUtils.contains(saveInfo, ',')) {
            String[] info = StringUtils.split(saveInfo, ',');
            name = info[0];
            if (info.length >= 2) {
                type = PropertyType.valueFromName(info[1]);
            }
            if (info.length >= 3) {
                valueType = Integer.valueOf(info[2]).intValue();
            }
            if (info.length >= 4) {
                isRichEditValue = Integer.valueOf(info[3]).intValue();
            }
            if (info.length >= 5) {
                encoding = Integer.valueOf(info[4]).intValue();
            }
        }
        else {
            name = saveInfo;
        }

        if (type == PropertyType.BINARY) {
            processBinary(node, name);
        }
        else {
            values = getForm().getParameterValues(name);
            if (valueType == ControlImpl.VALUETYPE_MULTIPLE) {
                processMultiple(node, name, type, values);
            }
            else if (isRichEditValue != ControlImpl.RICHEDIT_NONE) {
                processRichEdit(node, name, type, isRichEditValue, encoding, values);
            }
            else if(type == PropertyType.DATE) {
                processDate(node, name, type, valueType, encoding, values);
            }
            else {
                processCommon(node, name, type, valueType, encoding, values);
            }
        }
    }

    protected void processDate(Content node, String name, int type, int valueType, int encoding, String[] values) {
        if(StringUtils.isEmpty(values[0]))
            return;
        try {
            Calendar utc = DateUtil.getUTCCalendarFromDialogString(values[0]);
            NodeDataUtil.getOrCreate(node,name).setValue(utc);
        } catch (Exception e) {
            log.error("Could not update date value of node:"+node.getHandle()+" of property:"+name);
        }
    }

    /**
     * Parse the value returned by a rich text editor and update the links and linebreaks.
     * @param node
     * @param name
     * @param type
     * @param isRichEditValue
     * @param encoding
     * @param values
     * @throws PathNotFoundException
     * @throws RepositoryException
     * @throws AccessDeniedException
     */
    protected void processRichEdit(Content node, String name, int type, int isRichEditValue, int encoding,
                                   String[] values) throws PathNotFoundException, RepositoryException, AccessDeniedException {
        String valueStr = StringUtils.EMPTY;
        if (values != null) {
            valueStr = values[0]; // values is null when the expected field would not exis, e.g no
        }

        valueStr = cleanLineBreaks(valueStr,isRichEditValue);

        if (isRichEditValue == ControlImpl.RICHEDIT_FCK) {
            valueStr = updateLinks(node, name, valueStr);
        }

        processString(node, name, type, encoding, values, valueStr);
    }

    /**
     * Clean up the linebreaks and
     * <p>, <br>
     * tags returned by the rich text editors
     * @param valueStr
     * @return the cleaned string
     */
    private String cleanLineBreaks(String valueStr, int isRichEditValue) {
        // encode the internal links to avoid dependences from the contextpath, position of the page
        valueStr = LinkUtil.convertAbsoluteLinksToUUIDs(valueStr);

        valueStr = StringUtils.replace(valueStr, "\r\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
        valueStr = StringUtils.replace(valueStr, "\n", " "); //$NON-NLS-1$ //$NON-NLS-2$

        // ie inserts some strange br...
        valueStr = StringUtils.replace(valueStr, "</br>", StringUtils.EMPTY); //$NON-NLS-1$
        valueStr = StringUtils.replace(valueStr, "<br>", "<br/>"); //$NON-NLS-1$ //$NON-NLS-2$
        valueStr = StringUtils.replace(valueStr, "<BR>", "<br />"); //$NON-NLS-1$ //$NON-NLS-2$
        valueStr = StringUtils.replace(valueStr, "<br/>", "<br />"); //$NON-NLS-1$ //$NON-NLS-2$
        valueStr = StringUtils.replace(valueStr, "<P><br />", "<P>"); //$NON-NLS-1$ //$NON-NLS-2$

        // replace <P>
        if(isRichEditValue != ControlImpl.RICHEDIT_FCK)
            valueStr = replacePByBr(valueStr, "p"); //$NON-NLS-1$
        return valueStr;
    }

    /**
     * Update the links in a string returned by a rich text editor. If there are links to temporary files created due
     * the fckeditor upload mechanism those filese are written to the node.
     * @param node node saving to. used to save the files and fileinfod to
     * @param name the name of the field. used to make a subnode for the files
     * @param valueStr the value containing the links
     * @return the cleaned value
     * @throws AccessDeniedException
     * @throws RepositoryException
     * @throws PathNotFoundException
     */
    private String updateLinks(Content node, String name, String valueStr) throws AccessDeniedException,
        RepositoryException, PathNotFoundException {

        // process the images and uploaded files
        HierarchyManager hm = MgnlContext.getHierarchyManager(this.getRepository());

        Pattern imagePattern = Pattern.compile("(<(a|img)[^>]+(href|src)[ ]*=[ ]*\")([^\"]*)(\"[^>]*>)");
        Pattern uuidPattern = Pattern.compile(MgnlContext.getContextPath() + "/tmp/fckeditor/([^/]*)/[^\"]*");

        Content filesNode = ContentUtil.getOrCreateContent(node, name + "_files", ItemType.CONTENTNODE);

        String pageName = StringUtils.substringAfterLast(this.getPath(), "/");

        // adapt img urls
        Matcher srcMatcher = imagePattern.matcher(valueStr);
        StringBuffer res = new StringBuffer();
        while (srcMatcher.find()) {
            String src = srcMatcher.group(4);

            // the editor creates relative links after moving an absolute path: ../../../ replace them
            src = StringUtils.replace(src, "../../../", MgnlContext.getContextPath() + "/");

            String link = src;

            // process the tmporary uploaded files
            Matcher uuidMatcher = uuidPattern.matcher(src);

            if (uuidMatcher.find()) {
                String uuid = uuidMatcher.group(1);

                Document doc = FCKEditorTmpFiles.getDocument(uuid);
                String fileNodeName = Path.getUniqueLabel(hm, filesNode.getHandle(), "file");
                SaveHandlerImpl.saveDocument(filesNode, doc, fileNodeName, "", "");
                String subpath = StringUtils.removeStart(filesNode.getHandle(), this.getPath() + "/");
                link = pageName + "/" + subpath + "/" + fileNodeName + "/" + doc.getFileNameWithExtension();
                doc.delete();
                try {
                    FileUtils.deleteDirectory(new java.io.File(Path.getTempDirectory() + "/fckeditor/" + uuid));
                }
                catch (IOException e) {
                    log.error("can't delete tmp file [" + Path.getTempDirectory() + "/fckeditor/" + uuid + "]");
                }
            }

            // make internal links relative
            else if (src.startsWith(MgnlContext.getContextPath())) {
                link = pageName + StringUtils.removeStart(src, MgnlContext.getContextPath() + this.getPath());
            }

            // internal uuid links have a leading $
            link = StringUtils.replace(link, "$", "\\$");

            srcMatcher.appendReplacement(res, "$1" + link + "$5"); //$NON-NLS-1$
        }
        srcMatcher.appendTail(res);
        valueStr = res.toString();
        return valueStr;
    }

    /**
     * Process a common value
     * @param node node where the data must be stored
     * @param name name of the field
     * @param type type
     * @param valueType internal value type (according to ControlImpl)
     * @param encoding must we encode (base64)
     * @param values all values belonging to this field
     * @throws PathNotFoundException exception
     * @throws RepositoryException exception
     * @throws AccessDeniedException exception
     */
    protected void processCommon(Content node, String name, int type, int valueType, int encoding, String[] values)
        throws PathNotFoundException, RepositoryException, AccessDeniedException {
        String valueStr = StringUtils.EMPTY;
        if (values != null) {
            valueStr = values[0]; // values is null when the expected field would not exis, e.g no
        }

        processString(node, name, type, encoding, values, valueStr);
    }

    /**
     * Process a string. This method will encode it
     * @param node
     * @param name
     * @param type
     * @param encoding
     * @param values
     * @param valueStr
     * @throws PathNotFoundException
     * @throws RepositoryException
     * @throws AccessDeniedException
     */
    protected void processString(Content node, String name, int type, int encoding, String[] values, String valueStr)
        throws PathNotFoundException, RepositoryException, AccessDeniedException {
        // actualy encoding does only work for control password
        boolean remove = false;
        boolean write = false;
        if (encoding == ControlImpl.ENCODING_BASE64) {
            if (StringUtils.isNotBlank(valueStr)) {
                valueStr = new String(Base64.encodeBase64(valueStr.getBytes()));
                write = true;
            }
        }
        else if (encoding == ControlImpl.ENCODING_UNIX) {
            if (StringUtils.isNotEmpty(valueStr)) {
                valueStr = Digester.getSHA1Hex(valueStr);
                write = true;
            }
        }
        else {
            // no encoding
            if (values == null || StringUtils.isEmpty(valueStr)) {
                remove = true;
            }
            else {
                write = true;
            }
        }
        if (remove) {
            processRemoveCommon(node, name);
        }
        else if (write) {
            processWriteCommon(node, name, valueStr, type);
        }
    }

    /**
     * Remove the specified property on the node.
     */
    protected void processRemoveCommon(Content node, String name) throws PathNotFoundException, RepositoryException {
        NodeData data = node.getNodeData(name);

        if (data.isExist()) {
            node.deleteNodeData(name);
        }
    }

    /**
     * Writes a property value.
     * @param node the node
     * @param name the property name to be written
     * @param valueStr the value of the property
     * @throws AccessDeniedException thrown if the write access is not granted
     * @throws RepositoryException thrown if other repository exception is thrown
     */
    protected void processWriteCommon(Content node, String name, String valueStr, int type)
        throws AccessDeniedException, RepositoryException {
        Value value = this.getValue(valueStr, type);
        if (null != value) {
            NodeData data = NodeDataUtil.getOrCreate(node,name);
            data.setValue(value);
        }
    }

    /**
     * Process a multiple value field
     * @param node
     * @param name
     * @param type
     * @param values
     * @throws RepositoryException
     * @throws PathNotFoundException
     * @throws AccessDeniedException
     */
    protected void processMultiple(Content node, String name, int type, String[] values) throws RepositoryException,
        PathNotFoundException, AccessDeniedException {
        // remove entire content node and (re-)write each
        try {
            node.delete(name);
        }
        catch (PathNotFoundException e) {
            if (log.isDebugEnabled())
                log.debug("Exception caught: " + e.getMessage(), e); //$NON-NLS-1$
        }
        if (values != null && values.length != 0) {
            Content multiNode = node.createContent(name, ItemType.CONTENTNODE);
            try {
                // MetaData.CREATION_DATE has private access; no method to delete it so far...
                multiNode.deleteNodeData("creationdate"); //$NON-NLS-1$
            }
            catch (RepositoryException re) {
                if (log.isDebugEnabled())
                    log.debug("Exception caught: " + re.getMessage(), re); //$NON-NLS-1$
            }
            for (int j = 0; j < values.length; j++) {
                String valueStr = values[j];
                if(StringUtils.isNotEmpty(valueStr)) {
                    Value value = this.getValue(valueStr, type);
                    multiNode.createNodeData(Integer.toString(j)).setValue(value);
                }
            }
        }
    }

    /**
     * Process binary data. File- or imageupload.
     * @param node
     * @param name
     * @throws PathNotFoundException
     * @throws RepositoryException
     * @throws AccessDeniedException
     */
    protected void processBinary(Content node, String name) throws PathNotFoundException, RepositoryException,
        AccessDeniedException {
        Document doc = getForm().getDocument(name);
        if (doc == null && getForm().getParameter(name + "_" + File.REMOVE) != null) { //$NON-NLS-1$
            try {
                node.deleteNodeData(name);
            }
            catch (RepositoryException re) {
                if (log.isDebugEnabled())
                    log.debug("Exception caught: " + re.getMessage(), re); //$NON-NLS-1$
            }
        }
        else {
            String fileName = getForm().getParameter(name + "_" + FileProperties.PROPERTY_FILENAME);
            String template = getForm().getParameter(name + "_" + FileProperties.PROPERTY_TEMPLATE);

            SaveHandlerImpl.saveDocument(node, doc, name, fileName, template);
        }
    }

    /**
     * Get a string value
     * @param s
     * @return the value
     */
    public Value getValue(String s) {
        return this.getValue(s, PropertyType.STRING);
    }

    /**
     * Get the long value
     * @param l
     * @return the value
     */
    public Value getValue(long l) {
        HierarchyManager hm = MgnlContext.getHierarchyManager(this.getRepository());
        ValueFactory valueFactory;
        try {
            valueFactory = hm.getWorkspace().getSession().getValueFactory();
        }
        catch (RepositoryException e) {
            throw new NestableRuntimeException(e);
        }
        return valueFactory.createValue(l);
    }

    /**
     * Get the value for saving in jcr
     * @param valueStr string representation of the value
     * @param type type of the value
     * @return the value
     */
    public Value getValue(String valueStr, int type) {

        ValueFactory valueFactory = null;

        HierarchyManager hm = MgnlContext.getHierarchyManager(this.getRepository());
        try {
            valueFactory = hm.getWorkspace().getSession().getValueFactory();
        }
        catch (RepositoryException e) {
            throw new NestableRuntimeException(e);
        }

        Value value = null;
        if (type == PropertyType.STRING) {
            value = valueFactory.createValue(valueStr);
        }
        else if (type == PropertyType.BOOLEAN) {
            value = valueFactory.createValue(BooleanUtils.toBoolean(valueStr));
        }
        else if (type == PropertyType.DOUBLE) {
            try {
                value = valueFactory.createValue(Double.parseDouble(valueStr));
            }
            catch (NumberFormatException e) {
                value = valueFactory.createValue(0d);
            }
        }
        else if (type == PropertyType.LONG) {
            try {
                value = valueFactory.createValue(Long.parseLong(valueStr));
            }
            catch (NumberFormatException e) {
                value = valueFactory.createValue(0L);
            }
        }
        else if (type == PropertyType.DATE) {
            try {
                Calendar date = new GregorianCalendar();
                try {
                    String newDateAndTime = valueStr;
                    String[] dateAndTimeTokens = newDateAndTime.split("T"); //$NON-NLS-1$
                    String newDate = dateAndTimeTokens[0];
                    String[] dateTokens = newDate.split("-"); //$NON-NLS-1$
                    int hour = 0;
                    int minute = 0;
                    int second = 0;
                    int year = Integer.parseInt(dateTokens[0]);
                    int month = Integer.parseInt(dateTokens[1]) - 1;
                    int day = Integer.parseInt(dateTokens[2]);
                    if (dateAndTimeTokens.length > 1) {
                        String newTime = dateAndTimeTokens[1];
                        String[] timeTokens = newTime.split(":"); //$NON-NLS-1$
                        hour = Integer.parseInt(timeTokens[0]);
                        minute = Integer.parseInt(timeTokens[1]);
                        second = Integer.parseInt(timeTokens[2]);
                    }
                    date.set(year, month, day, hour, minute, second);
                    // this is used in the searching
                    date.set(Calendar.MILLISECOND, 0);
                    date.setTimeZone(TimeZone.getTimeZone("GMT"));
                }
                // todo time zone??
                catch (Exception e) {
                    // ignore, it sets the current date / time
                }
                value = valueFactory.createValue(date);
            }
            catch (Exception e) {
                if (log.isDebugEnabled())
                    log.debug("Exception caught: " + e.getMessage(), e); //$NON-NLS-1$
            }
        }
        else if (type == PropertyType.REFERENCE) {
            try {
                Node referencedNode = hm.getWorkspace().getSession().getNodeByUUID(valueStr);

                value = valueFactory.createValue(referencedNode);
            }
            catch (RepositoryException re) {
                if (log.isDebugEnabled())
                    log.debug("Cannot retrieve the referenced node by UUID: " + valueStr, re);
            }
        }

        return value;
    }

    /**
     * @param value
     * @param tagName
     */
    protected static String replacePByBr(final String value, String tagName) {

        if (StringUtils.isBlank(value)) {
            return value;
        }

        String fixedValue = value;

        String pre = "<" + tagName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
        String post = "</" + tagName + ">"; //$NON-NLS-1$ //$NON-NLS-2$

        // get rid of last </p>
        if (fixedValue.endsWith(post)) {
            fixedValue = StringUtils.substringBeforeLast(fixedValue, post);
        }

        fixedValue = StringUtils.replace(fixedValue, pre + "&nbsp;" + post, "\n "); //$NON-NLS-1$ //$NON-NLS-2$
        fixedValue = StringUtils.replace(fixedValue, pre, StringUtils.EMPTY);
        fixedValue = StringUtils.replace(fixedValue, post, "\n\n "); //$NON-NLS-1$

        if (!tagName.equals(tagName.toUpperCase())) {
            fixedValue = replacePByBr(fixedValue, tagName.toUpperCase());
        }
        return fixedValue;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#isCreate()
     */
    public boolean isCreate() {
        return create;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#setCreate(boolean)
     */
    public void setCreate(boolean create) {
        this.create = create;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#getCreationItemType()
     */
    public ItemType getCreationItemType() {
        return creationItemType;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#setCreationItemType(info.magnolia.cms.core.ItemType)
     */
    public void setCreationItemType(ItemType creationItemType) {
        this.creationItemType = creationItemType;
    }

    /**
     * @return the form containing the values passed
     */
    protected MultipartForm getForm() {
        return form;
    }

    /**
     * set the from
     * @param form containing the sended values
     */
    protected void setForm(MultipartForm form) {
        this.form = form;
    }

    /**
     * set the name of the repository saving to
     * @param repository the name of the repository
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }

    /**
     * get the name of thre repository saving to
     * @return name
     */
    public String getRepository() {
        return repository;
    }

    /**
     * Returns the page. The page is created if not yet existing depending on the property create
     * @param hm
     * @return the node
     * @throws RepositoryException
     * @throws AccessDeniedException
     * @throws PathNotFoundException
     */
    protected Content getPageNode(HierarchyManager hm) throws RepositoryException, AccessDeniedException,
        PathNotFoundException {
        Content page = null;
        String path = this.getPath();
        try {
            page = hm.getContent(path);
        }
        catch (RepositoryException e) {
            if (this.isCreate()) {
                String parentPath = StringUtils.substringBeforeLast(path, "/"); //$NON-NLS-1$
                String label = StringUtils.substringAfterLast(path, "/"); //$NON-NLS-1$
                if (StringUtils.isEmpty(parentPath)) {
                    page = hm.getRoot();
                }
                else {
                    page = hm.getContent(parentPath);
                }
                page = page.createContent(label, this.getCreationItemType());
            }
            else {
                log.error("Tried to save a not existing node with path {}. use create = true to force creation", path); //$NON-NLS-1$
            }
        }
        return page;
    }

    /**
     * Gets or creates the node saving to.
     * @param hm
     * @param rootNode the node containing the saving node. If both the nodeCollectionName and the nodeName are empty
     * this is the returned node.
     * @return the node to which the content is saved
     * @throws AccessDeniedException
     * @throws RepositoryException
     */
    protected Content getSaveNode(HierarchyManager hm, Content rootNode) throws AccessDeniedException,
        RepositoryException {
        Content node = null;

        // get or create nodeCollection
        Content nodeCollection = null;
        if (StringUtils.isNotEmpty(this.getNodeCollectionName())) {
            try {
                nodeCollection = rootNode.getContent(this.getNodeCollectionName());
            }
            catch (RepositoryException re) {
                // nodeCollection does not exist -> create
                nodeCollection = rootNode.createContent(this.getNodeCollectionName(), ItemType.CONTENTNODE);
                if (log.isDebugEnabled()) {
                    log.debug("Create - " + nodeCollection.getHandle()); //$NON-NLS-1$
                }
            }
        }
        else {
            nodeCollection = rootNode;
        }

        // get or create node
        if (StringUtils.isNotEmpty(this.getNodeName())) {
            try {
                node = nodeCollection.getContent(this.getNodeName());
            }
            catch (RepositoryException re) {
                // node does not exist -> create
                if (this.getNodeName().equals("mgnlNew")) { //$NON-NLS-1$
                    this.setNodeName(Path.getUniqueLabel(hm, nodeCollection.getHandle(), "0")); //$NON-NLS-1$
                }
                node = nodeCollection.createContent(this.getNodeName(), ItemType.CONTENTNODE);
            }
        }
        else {
            node = nodeCollection;
        }
        return node;
    }

    /**
     * Saves a uploaded file in the magnolia way. It creates a subnode name_properties where all the information like
     * the mime type is stored.
     * @param node the node under which the data is stored
     * @param name the name of the nodedata to store the data into
     * @param fileName If empty the original filename is used
     * @param template can be empty
     * @throws PathNotFoundException
     * @throws RepositoryException
     * @throws AccessDeniedException
     */
    public static void saveDocument(Content node, Document doc, String name, String fileName, String template)
        throws PathNotFoundException, RepositoryException, AccessDeniedException {

        NodeData data = node.getNodeData(name);
        if (doc != null) {
            if (!data.isExist()) {
                data = node.createNodeData(name, PropertyType.BINARY);

                    log.debug("creating under - {}", node.getHandle()); //$NON-NLS-1$
                    log.debug("creating node data for binary store - {}", name); //$NON-NLS-1$

            }
            data.setValue(doc.getStream());
            if (log.isDebugEnabled())
                log.debug("Node data updated"); //$NON-NLS-1$
        }
        if (data != null) {
            if (fileName == null || fileName.equals(StringUtils.EMPTY)) {
                fileName = doc.getFileName();
            }
            data.setAttribute(FileProperties.PROPERTY_FILENAME, fileName);
            if (doc != null) {
                data.setAttribute(FileProperties.PROPERTY_CONTENTTYPE, doc.getType());

                Calendar value = new GregorianCalendar(TimeZone.getDefault());
                data.setAttribute(FileProperties.PROPERTY_LASTMODIFIES, value);

                data.setAttribute(FileProperties.PROPERTY_SIZE, Long.toString(doc.getLength()));

                data.setAttribute(FileProperties.PROPERTY_EXTENSION, doc.getExtension());

                data.setAttribute(FileProperties.PROPERTY_TEMPLATE, template);

                InputStream raf = null;
                try {
                    ImageInfo ii = new ImageInfo();
                    raf = new FileInputStream(doc.getFile());
                    ii.setInput(raf);
                    if (ii.check()) {
                        data.setAttribute(FileProperties.PROPERTY_WIDTH, Long.toString(ii.getWidth()));
                        data.setAttribute(FileProperties.PROPERTY_HEIGHT, Long.toString(ii.getHeight()));
                        // data.setAttribute(FileProperties.x, Long.toString(ii.getBitsPerPixel()));
                    }
                }
                catch (FileNotFoundException e) {
                    log.error("FileNotFoundException caught when parsing {}, image data will not be available", doc
                        .getFile()
                        .getAbsolutePath());
                }
                finally {
                    IOUtils.closeQuietly(raf);
                }

                doc.delete();
            }
        }

    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#getNodeCollectionName()
     */
    public String getNodeCollectionName() {
        return this.nodeCollectionName;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#setNodeCollectionName(java.lang.String)
     */
    public void setNodeCollectionName(String nodeCollectionName) {
        this.nodeCollectionName = nodeCollectionName;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#getNodeName()
     */
    public String getNodeName() {
        return this.nodeName;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#setNodeName(java.lang.String)
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#getParagraph()
     */
    public String getParagraph() {
        return this.paragraph;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#setParagraph(java.lang.String)
     */
    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#getPath()
     */
    public String getPath() {
        return this.path;
    }

    /**
     * @see info.magnolia.module.admininterface.SaveHandler#setPath(java.lang.String)
     */
    public void setPath(String path) {
        this.path = path;
    }

}