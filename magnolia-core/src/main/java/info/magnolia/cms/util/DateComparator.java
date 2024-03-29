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

import info.magnolia.cms.core.Content;

import java.util.Comparator;
import java.util.Date;


/**
 * @author Marcel Salathe
 * @version 1.1
 */
public class DateComparator implements Comparator {

    public int compare(Object o, Object o1) throws ClassCastException {
        Date date1 = ((Content) o).getMetaData().getCreationDate().getTime();
        Date date2 = ((Content) o1).getMetaData().getCreationDate().getTime();
        return date1.compareTo(date2);
    }
}
