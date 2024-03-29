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
package info.magnolia.cms.core;

import java.util.Hashtable;
import java.util.Map;


/**
 * @author Sameer charles
 * @version 2.0
 * $Id: SystemProperty.java 2719 2006-04-27 14:38:44Z scharles $
 */
public final class SystemProperty {

    public static final String MAGNOLIA_REPOSITORIES_CONFIG = "magnolia.repositories.config"; //$NON-NLS-1$

    public static final String MAGNOLIA_EXCHANGE_HISTORY = "magnolia.exchange.history"; //$NON-NLS-1$

    public static final String MAGNOLIA_UPLOAD_TMPDIR = "magnolia.upload.tmpdir"; //$NON-NLS-1$

    public static final String MAGNOLIA_CACHE_STARTDIR = "magnolia.cache.startdir"; //$NON-NLS-1$

    public static final String MAGNOLIA_APP_ROOTDIR = "magnolia.app.rootdir"; //$NON-NLS-1$

    public static final String MAGNOLIA_BOOTSTRAP_ROOTDIR = "magnolia.bootstrap.dir"; //$NON-NLS-1$

    private static Map properties = new Hashtable();

    /**
     * Utility class, don't instantiate.
     */
    private SystemProperty() {
        // unused
    }

    /**
     * @param name
     * @param value
     */
    public static void setProperty(String name, String value) {
        SystemProperty.properties.put(name, value);
    }

    /**
     * @param name
     */
    public static String getProperty(String name) {
        return (String) SystemProperty.properties.get(name);
    }

    /**
     *
     */
    public static Map getPropertyList() {
        return SystemProperty.properties;
    }
}
