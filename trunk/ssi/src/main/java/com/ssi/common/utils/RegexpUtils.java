package com.ssi.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.oro.text.regex.MalformedPatternException;
//import org.apache.oro.text.regex.MatchResult;
//import org.apache.oro.text.regex.Pattern;
//import org.apache.oro.text.regex.PatternCompiler;
//import org.apache.oro.text.regex.PatternMatcher;
//import org.apache.oro.text.regex.Perl5Compiler;
//import org.apache.oro.text.regex.Perl5Matcher;


/**
 *缺少ORO包
 */
public class RegexpUtils {
	private static final Log logger = LogFactory.getLog(RegexpUtils.class);

//	public static boolean isRegex(String expression) {
//		try {
//			new Perl5Compiler().compile(expression);
//			return true;
//		} catch (MalformedPatternException e) {
//			return false;
//		}
//	}

	public static boolean matches(String regexp, String input) {
		return matches(regexp, input, false);
	}

	public static boolean matches(String regexp, String input,
			boolean caseSensitive) {
		if (input == null) {
			return false;
		}

		if (StringUtils.isEmpty(regexp)) {
			return true;
		}

//		try {
//			if (caseSensitive) {
//				return new Perl5Matcher().contains(input, new Perl5Compiler()
//						.compile(regexp));
//			} else {
//				return new Perl5Matcher().contains(input, new Perl5Compiler()
//						.compile(regexp, Perl5Compiler.CASE_INSENSITIVE_MASK));
//			}
//		} catch (MalformedPatternException e) {
//			logger.error("containMatch ---Bad pattern.");
//			logger.error(e.getMessage());
			return false;
//		}
	}

	public static String find(String regexp, boolean isMatchedInOneGroup,
			String input, boolean caseSensitive) {
		if (!matches(regexp, input, caseSensitive))
			return "";

		if (StringUtils.isEmpty(regexp))
			return input;

//		try {
//			PatternCompiler compiler = new Perl5Compiler();
//			PatternMatcher matcher = new Perl5Matcher();
//			Pattern pattern = null;
//			if (caseSensitive)
//				pattern = compiler.compile(regexp,
//						Perl5Compiler.CASE_INSENSITIVE_MASK);
//			else
//				pattern = compiler.compile(regexp);
//
//			if (matcher.contains(input, pattern)) {
//				MatchResult result = matcher.getMatch();
//				if (isMatchedInOneGroup) {
//					return result.group(1);
//				} else {
//					return result.group(0);
//				}
//			} else {
//				return "";
//			}
//		} catch (MalformedPatternException e) {
//			logger.error("containMatch --- Bad pattern.");
//			logger.error(e.getMessage());
			return "";
//		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String findIpAddress(String input) {
		String patternStr = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
		return find(patternStr, true, input, false);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static int findPort(String input) {
		String patternStr = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:(\\d{1,5})";
		String portStr = find(patternStr, true, input, false);
		if (StringUtils.isBlank(portStr)) {
			logger.warn("The input string doesn't contains a valid port.");
			return -1;
		}
		return Integer.parseInt(portStr);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String findIpAddressAndPort(String input) {
		String patternStr = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5})";
		return find(patternStr, true, input, false);
	}

	/**
	 * 
	 * @param input
	 * @param with
	 * @return
	 */
	public static String replaceIpAddress(String input, String with) {
		String patternStr = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
		if (!matches(patternStr, input)) {
			if (logger.isWarnEnabled()) {
				logger.warn("The input string doesnot contain an ip address.");
			}
			return input;
		}
		if (!matches(patternStr, with)) {
			if (logger.isWarnEnabled()) {
				logger
						.warn("The replace-with string is not an valid ip address.");
			}
			return input;
		}

		return input.replaceFirst(patternStr, with);
	}

	public static void main(String[] args) {

		System.out.println(RegexpUtils.matches(java.util.regex.Pattern.compile(
				"^是|否$").pattern(), "是"));
		System.out.println(RegexpUtils.matches(java.util.regex.Pattern.compile(
				"^是|否$").pattern(), "否"));
		System.out.println(RegexpUtils.matches(java.util.regex.Pattern.compile(
				"^是|否$").pattern(), "yes"));
	}
}
