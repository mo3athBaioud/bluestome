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

import info.magnolia.cms.beans.config.ContentRepository;
import info.magnolia.cms.core.Content;
import info.magnolia.cms.core.HierarchyManager;
import info.magnolia.cms.core.ItemType;
import info.magnolia.cms.core.NodeData;
import info.magnolia.cms.gui.misc.FileProperties;
import info.magnolia.cms.util.Resource;
import info.magnolia.context.MgnlContext;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.jcr.AccessDeniedException;
import javax.jcr.PathNotFoundException;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.devlib.schmidt.imageinfo.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Fabrizio Giustina
 */
public abstract class BaseImageTag extends SimpleTagSupport {

    /**
     * The value of the extension nodeData in the properties node.
     */
    protected static final String PROPERTIES_EXTENSION_VALUE = "PNG";

    /**
     * The valye of the contentType nodeData in the properties node.
     */
    protected static final String PROPERTIES_CONTENTTYPE_VALUE = "image/png";

    /**
     * Logger.
     */
    private static Logger log = LoggerFactory.getLogger(BaseImageTag.class);

    /**
     * Attribute: The node where the images are to be saved. If null, the default will be the current active page.
     */
    protected String parentContentNodeName;

    /**
     * Attribute: The name of the new content node to create. The images will be saved under this node. If this name
     * starts with a '/', it will be assumed to be a node handle that is relative to the rooot of the website.
     * Otherwise, it is assumed to be a path relative to the currentActivePage.
     */
    protected String imageContentNodeName;

    /**
     * Setter for the <code>imageContentNodeName</code> tag attribute.
     * @param imageContentNodeName
     */
    public void setImageContentNodeName(String imageContentNodeName) {
        this.imageContentNodeName = imageContentNodeName;
    }

    /**
     * Setter for the <code>parentContentNodeName</code> tag attribute.
     * @param parentContentNodeName
     */
    public void setParentContentNodeName(String parentContentNodeName) {
        this.parentContentNodeName = parentContentNodeName;
    }

    protected abstract String getFilename();

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) ((PageContext) this.getJspContext()).getRequest();
    }

    /**
     * @throws PathNotFoundException
     * @throws RepositoryException
     * @throws AccessDeniedException
     */
    protected Content getImageContentNode() throws PathNotFoundException, RepositoryException,
        info.magnolia.cms.security.AccessDeniedException {

        Content imageContentNode;
        Content currentActivePage = Resource.getCurrentActivePage(getRequest());
        Content paragraph = Resource.getLocalContentNode(getRequest());
        Content parentContentNode = null;
        // set the image parent node
        if (StringUtils.isEmpty(this.parentContentNodeName)) {
            parentContentNode = paragraph != null ? paragraph : currentActivePage;
        }
        else {

            HierarchyManager hm = MgnlContext.getHierarchyManager(ContentRepository.WEBSITE);
            // if this name starts with a '/', then assume it is a node handle
            // otherwise assume that its is a path relative to the current active page
            if (this.parentContentNodeName.startsWith("/")) {
                parentContentNode = hm.getContent(this.parentContentNodeName);
            }
            else {
                parentContentNode = hm.getContent(currentActivePage.getHandle() + "/" + this.parentContentNodeName);
            }
        }
        // set the node under which the images will be saved
        imageContentNode = null;
        if (StringUtils.isEmpty(this.imageContentNodeName)) {
            imageContentNode = parentContentNode;
        }
        else if (parentContentNode.hasContent(this.imageContentNodeName)) {
            imageContentNode = parentContentNode.getContent(this.imageContentNodeName);
        }
        else {
            imageContentNode = parentContentNode.createContent(this.imageContentNodeName, ItemType.CONTENTNODE);
        }
        return imageContentNode;
    }

    /**
     * Replace any special characters that are not letters or numbers with a replacement string. The two exceptions are
     * '-' and '_', which are allowed.
     */
    public String convertToSimpleString(String string) {

        final StringBuffer result = new StringBuffer();

        final StringCharacterIterator iterator = new StringCharacterIterator(string);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            int charType = Character.getType(character);
            if (charType == Character.SPACE_SEPARATOR) {
                result.append("-");
            }
            else if ((charType != Character.UPPERCASE_LETTER)
                && (charType != Character.LOWERCASE_LETTER)
                && (charType != Character.DECIMAL_DIGIT_NUMBER)
                && (charType != Character.CONNECTOR_PUNCTUATION)
                && (charType != Character.DASH_PUNCTUATION)) {
                result.append("u" + (int) character);

            }
            else {
                // the char is not a special one
                // add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Converts HEX color to RGB color.
     * @param The HEX value
     */
    public int[] convertHexToRGB(String hex) {
        hex.trim();
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        if (hex.length() == 3) {
            // allow three digit codes like for css
            hex = String.valueOf(hex.charAt(0))
                + String.valueOf(hex.charAt(0))
                + String.valueOf(hex.charAt(1))
                + String.valueOf(hex.charAt(1))
                + String.valueOf(hex.charAt(2))
                + String.valueOf(hex.charAt(2));
        }

        int[] rgb = new int[3];
        try {
            // Convert rrggbb string to hex ints
            rgb[0] = Integer.parseInt(hex.substring(0, 2), 16);
            rgb[1] = Integer.parseInt(hex.substring(2, 4), 16);
            rgb[2] = Integer.parseInt(hex.substring(4), 16);
        }
        catch (NumberFormatException e) {
            log.error("NumberFormatException occured during text-to-image conversion: "
                + "Attempting to convert Hex ["
                + hex
                + "] color to RGB color: "
                + e.getMessage(), e);
            rgb = new int[]{255, 0, 0}; // red
        }
        return rgb;
    }

    public Color convertHexToColor(String hex) {
        int[] rgb = convertHexToRGB(hex);
        Color color = new Color(rgb[0], rgb[1], rgb[2]);
        return color;
    }

    /**
     * Create a new imageNode with the image in it. The node is saved under a node that groups all image nodes, whose
     * name is set to the value of the attribute imageContentNodeName. The name of the node will be set to a name that
     * is unique for the image. The property that stores the image will be set to the value of
     * PROPERTIES_FILENAME_VALUE. A sub-node is also created that stores the image properties.
     * @param subString The text.
     * @param textImageNode The node that will contain the text images.
     */
    protected void createImageNode(File imageFile, Content imageNode) throws PathNotFoundException,
        AccessDeniedException, RepositoryException, FileNotFoundException, IOException {

        // Create and save the image data
        NodeData data;
        data = imageNode.getNodeData(getFilename());

        if (!data.isExist()) {
            data = imageNode.createNodeData(getFilename(), PropertyType.BINARY);
        }

        InputStream iis = new FileInputStream(imageFile);
        data.setValue(iis);
        IOUtils.closeQuietly(iis);

        data.setAttribute(FileProperties.PROPERTY_FILENAME, getFilename());

        data.setAttribute(FileProperties.PROPERTY_CONTENTTYPE, PROPERTIES_CONTENTTYPE_VALUE);

        Calendar value = new GregorianCalendar(TimeZone.getDefault());
        data.setAttribute(FileProperties.PROPERTY_LASTMODIFIES, value);

        data.setAttribute(FileProperties.PROPERTY_SIZE, Long.toString(imageFile.length()));

        data.setAttribute(FileProperties.PROPERTY_EXTENSION, PROPERTIES_EXTENSION_VALUE);

        InputStream raf = null;
        try {
            ImageInfo ii = new ImageInfo();
            raf = new FileInputStream(imageFile);
            ii.setInput(raf);
            if (ii.check()) {
                data.setAttribute(FileProperties.PROPERTY_WIDTH, Long.toString(ii.getWidth()));
                data.setAttribute(FileProperties.PROPERTY_HEIGHT, Long.toString(ii.getHeight()));

            }
        }
        catch (FileNotFoundException e) {
            log.error("FileNotFoundException caught when parsing {}, image data will not be available", imageFile
                .getAbsolutePath());
        }
        finally {
            IOUtils.closeQuietly(raf);
        }

        // delete the temporary file
        imageFile.delete();

        // save the new image node
        imageNode.save();
    }
}