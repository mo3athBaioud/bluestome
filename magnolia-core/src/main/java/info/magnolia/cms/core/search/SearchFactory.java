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
package info.magnolia.cms.core.search;

import info.magnolia.cms.security.AccessManager;


/**
 * @author Sameer Charles
 */

public final class SearchFactory {

    /**
     * don't instantiate.
     */
    private SearchFactory() {
        // unused
    }

    public static QueryManager getAccessControllableQueryManager(javax.jcr.query.QueryManager queryManager,
        AccessManager accessManager) {
        return (new QueryManagerImpl(queryManager, accessManager));
    }

}
