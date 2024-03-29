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
package info.magnolia.cms.gui.query;

import org.apache.commons.lang.StringUtils;

/**
 * @author Sameer Charles
 * $Id: SearchQueryOperator.java 2677 2006-04-19 15:22:23Z philipp $
 */
public class SearchQueryOperator extends AbstractExpressionImpl {

    /**
     * Operator value AND
     * */
    public static final String AND = "and";

    /**
     * Operator value OR
     * */
    public static final String OR = "or";

    /**
     * Operator value NOT
     * */
    public static final String NOT = "not";

    /**
     * operator
     * */
    private String operator;

    /**
     * @param operator
     * */
    public SearchQueryOperator(String operator) {
        this.operator = operator;
    }

    /**
     * get operator name
     * @return String
     * */
    public String getOperator() {
        return operator;
    }

    /**
     * set operator name
     * @param operator
     * */
    public void setOperator(String operator) {
        this.operator = operator;
    }
}
