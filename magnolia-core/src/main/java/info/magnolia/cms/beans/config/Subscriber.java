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
package info.magnolia.cms.beans.config;

import info.magnolia.cms.core.Content;
import info.magnolia.cms.core.ItemType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sameer Charles
 * @version 1.1
 */
public final class Subscriber {

    /**
     * Logger.
     */
    private static Logger log = LoggerFactory.getLogger(Subscriber.class);

    private static final String START_PAGE = "subscribers"; //$NON-NLS-1$

    private static Hashtable cachedContent = new Hashtable();

    /**
     * <code>true</code> if at least one subscriber is configured and enabled.
     */
    private static boolean subscribersEnabled;

    private String name;

    private boolean active;

    private boolean requestConfirmation;

    private String url;

    private String senderURL;

    private Map context;

    /**
     * Returns <code>true</code> if at least an enabled subscriber is configured, <code>false</code> if there are no
     * subscriber or none of them is enabled
     * @return <code>true</code> if at least an enabled subscriber exists
     */
    public static boolean isSubscribersEnabled() {
        return subscribersEnabled;
    }

    /**
     * constructor
     */
    private Subscriber() {
    }

    /**
     * <p>
     * reads listener config from the config repository and caches its content in to the hash table
     * </p>
     */
    public static void init() {
        Subscriber.cachedContent.clear();

        log.info("Config : loading Subscribers"); //$NON-NLS-1$

        Collection children = Collections.EMPTY_LIST;

        try {
            Content startPage = ContentRepository.getHierarchyManager(ContentRepository.CONFIG).getContent(START_PAGE);
            Content subscriberConfig = startPage.getContent("SubscriberConfig");
            children = subscriberConfig.getChildren(ItemType.CONTENTNODE); //$NON-NLS-1$
        }
        catch (PathNotFoundException re) {
            log.info("No subscribers configured"); //$NON-NLS-1$
        }
        catch (RepositoryException re) {
            log.error("Config : Failed to load Subscribers"); //$NON-NLS-1$
            log.error(re.getMessage(), re);
        }

        if (children != null) {
            Subscriber.cacheContent(children);
        }

        log.info("Config : Subscribers loaded"); //$NON-NLS-1$

    }

    public static void reload() {
        log.info("Config : re-loading Subscribers"); //$NON-NLS-1$
        Subscriber.init();
    }

    /**
     * Cache listener content from the config repository.
     */
    private static void cacheContent(Collection subs) {

        Iterator ipList = subs.iterator();

        // start by setting the subscribersEnabled property to false, will be reset when an active subscriber is found
        subscribersEnabled = false;

        while (ipList.hasNext()) {
            Content c = (Content) ipList.next();
            Subscriber si = new Subscriber();

            si.url = c.getNodeData("URL").getString(); //$NON-NLS-1$

            if (StringUtils.isEmpty(si.url)) {
                String address = c.getNodeData("address").getString(); //$NON-NLS-1$
                String protocol = c.getNodeData("protocol").getString(); //$NON-NLS-1$

                log
                    .warn("Deprecated: subscriber is missing the URL property. Please use URL instead of address and domain");

                if (StringUtils.isEmpty(protocol)) {
                    protocol = "http";
                    si.url = protocol + "://" + address;
                }
            }

            if (!si.url.endsWith("/")) {
                si.url = si.url + "/";
            }

            si.senderURL = c.getNodeData("senderURL").getString(); //$NON-NLS-1$
            si.requestConfirmation = c.getNodeData("requestConfirmation").getBoolean(); //$NON-NLS-1$
            si.name = c.getName();

            // don't use getBoolean since subscribers without an "active" node should be enabled by default
            String activeString = c.getNodeData("active").getString(); //$NON-NLS-1$

            if (StringUtils.isNotEmpty(activeString)) {
                si.active = BooleanUtils.toBoolean(activeString);
            }
            else {
                si.active = true;
            }

            if (si.active) {
                // at least one subscriber is enabled
                subscribersEnabled = true;
            }

            // all context info
            try {
                addContext(si, c);
            }
            catch (RepositoryException e) {
                // valid
            }
            Subscriber.cachedContent.put(c.getName(), si);
        }
        ipList = null;
    }

    /**
     * Adds context datail to cache.
     * @param subscriberInfo
     * @param contentNode
     */
    private static void addContext(Subscriber subscriberInfo, Content contentNode) throws RepositoryException {
        subscriberInfo.context = new Hashtable();
        Content contextList = contentNode.getContent("Context"); //$NON-NLS-1$
        Iterator it = contextList.getChildren().iterator();
        while (it.hasNext()) {
            Content context = (Content) it.next();
            Iterator contextDetails = context.getChildren().iterator();
            List list = new ArrayList();
            while (contextDetails.hasNext()) {
                Content map = (Content) contextDetails.next();
                list.add(map.getNodeData("subscribedURI").getString()); //$NON-NLS-1$
            }
            subscriberInfo.context.put(context.getName(), list);
        }
    }

    /**
     * Get list of all configured ip.
     * @return Enumeration
     */
    public static Enumeration getList() {
        return Subscriber.cachedContent.elements();
    }

    /**
     * Getter for <code>active</code>.
     * @return Returns the active.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Getter for <code>requestConfirmation</code>.
     * @return Returns the requestConfirmation.
     */
    public boolean getRequestConfirmation() {
        return this.requestConfirmation;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return context details
     */
    public List getContext(String name) {
        if (this.context.get(name) == null) {
            return new ArrayList();
        }
        return (List) this.context.get(name);
    }

    /**
     * Getter for <code>senderURL</code>.
     * @return Returns the senderURL.
     */
    public String getSenderURL() {
        return this.senderURL;
    }

    /**
     * Returns the url of the subscriber, in the form <code>protocol://server:port/context/</code> (always with the
     * leading "/")
     * @return Returns the url.
     */
    public String getURL() {
        return this.url;
    }

}
