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
package info.magnolia.exchange;

import java.util.Set;


/**
 * Date: May 4, 2004 Time: 11:14:00 AM
 * @author Sameer Charles
 */
public interface PacketHeader {

    /**
     * <p>
     * add new header field, overwrite is header exist with the same name
     * </p>
     * @param name
     * @param value
     */
    void addHeader(String name, String value);

    /**
     * @return header field value
     */
    String getValueByName(String name);

    /**
     * @return Collection of fieldNames as String
     */
    Set getKeys();
}