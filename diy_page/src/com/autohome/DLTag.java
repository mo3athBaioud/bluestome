package com.autohome;

import org.htmlparser.tags.CompositeTag;

public class DLTag extends CompositeTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] mIds = new String[] { "DL" };

	private static final String[] mEndTagEnders = new String[] { "DIV" };

	public String[] getIds() {
		return (mIds);
	}

	public String[] getEnders() {
		return (mIds);
	}

	public String[] getEndTagEnders() {
		return (mEndTagEnders);
	}

}
