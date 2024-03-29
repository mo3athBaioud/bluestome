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
import info.magnolia.cms.core.NodeData;
import info.magnolia.cms.security.SecureURI;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sameer Charles
 * @version 1.1
 */
public final class Server {

    public static final String CONFIG_PAGE = "server"; //$NON-NLS-1$

    public static final String[] MAIL_SETTINGS = {"smtpServer", "smtpPort", "smtpUser", "smtpPassword"};

    /**
     * Logger.
     */
    protected static Logger log = LoggerFactory.getLogger(Server.class);

    private static Map cachedContent = new Hashtable();

    private static Map cachedURImapping = new Hashtable();

    private static Map cachedCacheableURIMapping = new Hashtable();

    private static Map cachedMailSettings = new Hashtable();

    private static long uptime = System.currentTimeMillis();

    /**
     * Utility class, don't instantiate.
     */
    private Server() {
        // unused
    }

    /**
     * @throws ConfigurationException if basic config nodes are missing
     */
    public static void init() throws ConfigurationException {
        load();
        registerEventListener();
    }

    /**
     * Load the server configuration.
     * @throws ConfigurationException
     */
    public static void load() throws ConfigurationException {
        Server.cachedContent.clear();
        Server.cachedURImapping.clear();
        Server.cachedCacheableURIMapping.clear();
        Server.cachedMailSettings.clear();
        try {
            log.info("Config : loading Server"); //$NON-NLS-1$
            Content startPage = ContentRepository.getHierarchyManager(ContentRepository.CONFIG).getContent(CONFIG_PAGE);
            cacheServerConfiguration(startPage);
            cacheSecureURIList(startPage);
            cacheMailSettings();
            log.info("Config : Server config loaded"); //$NON-NLS-1$
        }
        catch (RepositoryException re) {
            log.error("Config : Failed to load Server config: " + re.getMessage(), re); //$NON-NLS-1$
            throw new ConfigurationException("Config : Failed to load Server config: " + re.getMessage(), re); //$NON-NLS-1$
        }
    }

    private static void cacheMailSettings() {

        Content mailContent;
        try {
            mailContent = ContentRepository.getHierarchyManager(ContentRepository.CONFIG).getContent(
                CONFIG_PAGE + "/mail");
        }
        catch (PathNotFoundException e) {
            log.debug("Mail settings not initialized, configuration not found");
            return;
        }
        catch (RepositoryException e) {
            log.error("Mail settings not initialized, an error occurred", e);
            return;
        }

        Iterator iter = mailContent.getNodeDataCollection().iterator();
        while (iter.hasNext()) {
            NodeData data = (NodeData) iter.next();
            addPossiblyNullKeyToMap(data.getName(), data.getString(), cachedMailSettings, false);
        }

        if (log.isDebugEnabled()) {
            for (int i = 0; i < MAIL_SETTINGS.length; i++) {
                if (cachedMailSettings.containsKey(MAIL_SETTINGS[i])) {
                    log.debug("Mail setting:{}={}", MAIL_SETTINGS[i], cachedMailSettings.get(MAIL_SETTINGS[i]));
                }
            }
        }
    }

    private static void addPossiblyNullKeyToMap(String key, String value, Map map, boolean putKeyIfNull) {
        try {
            map.put(key, value);
        }
        catch (Exception e) {
            if (putKeyIfNull) {
                map.put(key, StringUtils.EMPTY);
            }
        }
    }

    /**
     * Reload the server configuration: simply calls load().
     * @throws ConfigurationException
     */
    public static void reload() throws ConfigurationException {
        log.info("Config : re-loading Server config"); //$NON-NLS-1$
        Server.load();
    }

    /**
     * Cache server content from the config repository.
     */
    private static void cacheServerConfiguration(Content page) {

        boolean isAdmin = page.getNodeData("admin").getBoolean(); //$NON-NLS-1$
        Server.cachedContent.put("admin", BooleanUtils.toBooleanObject(isAdmin)); //$NON-NLS-1$

        String ext = page.getNodeData("defaultExtension").getString(); //$NON-NLS-1$
        Server.cachedContent.put("defaultExtension", ext); //$NON-NLS-1$

        String basicRealm = page.getNodeData("basicRealm").getString(); //$NON-NLS-1$
        Server.cachedContent.put("basicRealm", basicRealm); //$NON-NLS-1$

        Server.cachedContent.put("404URI", page.getNodeData("ResourceNotAvailableURIMapping").getString()); //$NON-NLS-1$ //$NON-NLS-2$

        boolean visibleToObinary = page.getNodeData("visibleToObinary").getBoolean(); //$NON-NLS-1$
        Server.cachedContent.put("visibleToObinary", BooleanUtils.toBooleanObject(visibleToObinary)); //$NON-NLS-1$

    }

    /**
     * Cache server content from the config repository.
     */
    private static void cacheSecureURIList(Content page) {
        try {
            addToSecureList(page.getContent("secureURIList")); //$NON-NLS-1$
        }
        catch (RepositoryException re) {
            log.error(re.getMessage(), re);
        }

        try {
            if (page.hasContent("unsecureURIList")) {
                addToUnsecureList(page.getContent("unsecureURIList")); //$NON-NLS-1$
            }
        }
        catch (javax.jcr.PathNotFoundException pn) {
            log.info("No unsecure uri found");
        }
        catch (RepositoryException re) {
            log.error(re.getMessage(), re);
        }
    }

    private static void addToUnsecureList(Content node) {
        if (node == null) {
            return;
        }
        Iterator childIterator = node.getChildren(ItemType.CONTENTNODE).iterator();
        while (childIterator.hasNext()) {
            Content sub = (Content) childIterator.next();
            String uri = sub.getNodeData("URI").getString(); //$NON-NLS-1$
            log.warn("Adding new unsecure uri:" + uri);
            SecureURI.addUnsecure(uri);
        }
    }

    /**
     * Register an event listener: reload server configuration when something changes. todo split reloading of base
     * server configuration and secure URI list
     */
    private static void registerEventListener() {

        log.info("Registering event listener for server"); //$NON-NLS-1$

        // server properties, only on the root server node
        try {
            ObservationManager observationManager = ContentRepository
                .getHierarchyManager(ContentRepository.CONFIG)
                .getWorkspace()
                .getObservationManager();

            observationManager.addEventListener(new EventListener() {

                public void onEvent(EventIterator iterator) {
                    // reload everything
                    try {
                        reload();
                    }
                    catch (ConfigurationException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }, Event.PROPERTY_ADDED | Event.PROPERTY_CHANGED | Event.PROPERTY_REMOVED, "/" + CONFIG_PAGE, //$NON-NLS-1$
                false,
                null,
                null,
                false);
        }
        catch (RepositoryException e) {
            log.error("Unable to add event listeners for server", e); //$NON-NLS-1$
        }

        // secure URI list
        try {
            ObservationManager observationManager = ContentRepository
                .getHierarchyManager(ContentRepository.CONFIG)
                .getWorkspace()
                .getObservationManager();

            observationManager.addEventListener(new EventListener() {

                public void onEvent(EventIterator iterator) {
                    // reload everything
                    try {
                        reload();
                    }
                    catch (ConfigurationException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }, Event.NODE_ADDED
                | Event.NODE_REMOVED
                | Event.PROPERTY_ADDED
                | Event.PROPERTY_CHANGED
                | Event.PROPERTY_REMOVED, "/" + CONFIG_PAGE + "/secureURIList", true, null, null, false); //$NON-NLS-1$ //$NON-NLS-2$
        }
        catch (RepositoryException e) {
            log.error("Unable to add event listeners for server", e); //$NON-NLS-1$
        }
    }

    private static void addToSecureList(Content node) {

        if (node == null) {
            return;
        }
        Iterator childIterator = node.getChildren(ItemType.CONTENTNODE).iterator();
        while (childIterator.hasNext()) {
            Content sub = (Content) childIterator.next();
            String uri = sub.getNodeData("URI").getString(); //$NON-NLS-1$
            SecureURI.addSecure(uri);
        }
    }

    /**
     * @return resource not available URI mapping as specifies in serverInfo, else /
     */
    public static String get404URI() {
        String uri = (String) Server.cachedContent.get("404URI"); //$NON-NLS-1$
        if (StringUtils.isEmpty(uri)) {
            return "/"; //$NON-NLS-1$
        }
        if (log.isDebugEnabled()) {
            log.debug("404URI is \"" + uri + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return uri;
    }

    /**
     * @return default URL extension as configured
     */
    public static String getDefaultExtension() {
        String defaultExtension = (String) Server.cachedContent.get("defaultExtension"); //$NON-NLS-1$
        if (defaultExtension == null) {
            return StringUtils.EMPTY;
        }
        return defaultExtension;
    }

    /**
     * @return default mail server
     */
    public static String getDefaultMailServer() {
        return (String) Server.cachedMailSettings.get("smtpServer");
    }

    /**
     * @return basic realm string
     */
    public static String getBasicRealm() {
        return (String) Server.cachedContent.get("basicRealm"); //$NON-NLS-1$
    }

    /**
     * @return true if the instance is configured as an admin server
     */
    public static boolean isAdmin() {
        return Boolean.TRUE.equals(Server.cachedContent.get("admin")); //$NON-NLS-1$
    }

    /**
     *
     */
    public static boolean isVisibleToObinary() {
        return Boolean.TRUE.equals(Server.cachedContent.get("visibleToObinary")); //$NON-NLS-1$
    }

    /**
     * Time in ms since the server was started
     * @return
     */
    public static long getUptime() {
        return uptime;
    }

}
