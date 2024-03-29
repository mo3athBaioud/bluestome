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

import java.io.InputStream;
import java.util.Calendar;


/**
 * @author Sameer Charles
 */
public interface PacketBody {

    /**
     * <p>
     * set packet type, supported types
     * <ul>
     * <li><code>PacketType.STRING</code>
     * <li><code>PacketType.BINARY</code>
     * <li><code>PacketType.LONG</code>
     * <li><code>PacketType.DOUBLE</code>
     * <li><code>PacketType.DATE</code>
     * <li><code>PacketType.BOOLEAN</code>
     * </ul>
     * </p>
     * @param type
     */
    void setType(int type);

    /**
     * <p>
     * one of these packet types
     * <ul>
     * <li><code>PacketType.STRING</code>
     * <li><code>PacketType.BINARY</code>
     * <li><code>PacketType.LONG</code>
     * <li><code>PacketType.DOUBLE</code>
     * <li><code>PacketType.DATE</code>
     * <li><code>PacketType.BOOLEAN</code>
     * </ul>
     * </p>
     */
    int getType();

    /**
     * @param size
     */
    void setLength(long size);

    /**
     * @return lenth of the packet body
     */
    long getLength();

    /**
     * <p>
     * set packet body, Packet type also must be set here
     * </p>
     * @param data
     */
    void setBody(String data) throws PacketIOException;

    /**
     * <p>
     * set packet body, Packet type must be set here
     * </p>
     * @param data
     */
    void setBody(InputStream data) throws PacketIOException;

    /**
     * <p>
     * set packet body, Packet type must be set here
     * </p>
     * @param data
     */
    void setBody(Long data) throws PacketIOException;

    /**
     * <p>
     * set packet body, Packet type must be set here
     * </p>
     * @param data
     */
    void setBody(Double data) throws PacketIOException;

    /**
     * <p>
     * set packet body, Packet type must be set here
     * </p>
     * @param data
     */
    void setBody(Calendar data) throws PacketIOException;

    /**
     * <p>
     * set packet body, Packet type must be set here
     * </p>
     * @param data
     */
    void setBody(Boolean data) throws PacketIOException;

    /**
     * <p>
     * set packet body, Packet type must be set here
     * </p>
     * @param data
     */
    void setBody(Object data) throws PacketIOException;

    /**
     * <p>
     * returns string representation of the data set by any data type
     * </p>
     */
    String toString();

    /**
     * <p>
     * gets data as object
     * </p>
     */
    Object getObject();
}
