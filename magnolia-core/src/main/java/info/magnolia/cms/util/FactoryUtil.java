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
package info.magnolia.cms.util;

import info.magnolia.cms.core.SystemProperty;

import java.util.Properties;

import org.apache.commons.discovery.tools.DiscoverClass;
import org.apache.commons.discovery.tools.DiscoverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Philipp Bracher
 * @version $Revision$ ($Author$)
 */
public class FactoryUtil {

    /**
     * Logger.
     */
    protected static Logger log = LoggerFactory.getLogger(FactoryUtil.class);

    private static Properties props = new Properties();

    static {
        // add the magnolia properties
        props.putAll(SystemProperty.getPropertyList());
    }

    private FactoryUtil() {

    }

    public static Object getInstance(Class interf) {
        try {
            return new DiscoverClass().newInstance(interf, props);
        }
        catch (Exception e) {
            log.error("can't instantiate an implementation of this class [" + interf.getName() + "]");
        }
        return null;
    }

    public static Object getSingleton(Class interf) {
        return DiscoverSingleton.find(interf, props);
    }

    public static void setDefaultImplementation(Class interf, String impl) {
        props.setProperty(interf.getName(), impl);
    }

    public static void setImplementation(Class interf, String impl) {
        props.setProperty(interf.getName(), impl);
    }
}
