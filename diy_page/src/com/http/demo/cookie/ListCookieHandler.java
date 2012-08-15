package com.http.demo.cookie;

import java.io.*;
import java.net.*;
import java.util.*;

public class ListCookieHandler extends CookieHandler {

	// "Long" term storage for cookies, not serialized so only
	// for current JVM instance
	private List<Cookie> cache = new LinkedList<Cookie>();

	/**
	 * Saves all applicable cookies present in the response headers into cache.
	 * 
	 * @param uri
	 *            URI source of cookies
	 * @param responseHeaders
	 *            Immutable map from field names to lists of field values
	 *            representing the response header fields returned
	 */

	public void put(URI uri, Map<String, List<String>> responseHeaders)
			throws IOException {

		System.out.println("Cache: " + cache);
		List<String> setCookieList = responseHeaders.get("Set-Cookie");
		if (setCookieList != null) {
			for (String item : setCookieList) {
				Cookie cookie = new Cookie(uri, item);
				// Remove cookie if it already exists
				// New one will replace
				for (Cookie existingCookie : cache) {
					if ((cookie.getURI().equals(existingCookie.getURI()))
							&& (cookie.getName().equals(existingCookie
									.getName()))) {
						cache.remove(existingCookie);
						break;
					}
				}
				System.out.println("Adding to cache: " + cookie);
				cache.add(cookie);
			}
		}
	}

	/**
	 * Gets all the applicable cookies from a cookie cache for the specified uri
	 * in the request header.
	 * 
	 * @param uri
	 *            URI to send cookies to in a request
	 * @param requestHeaders
	 *            Map from request header field names to lists of field values
	 *            representing the current request headers
	 * @return Immutable map, with field name "Cookie" to a list of cookies
	 */

	public Map<String, List<String>> get(URI uri,
			Map<String, List<String>> requestHeaders) throws IOException {

		// Retrieve all the cookies for matching URI
		// Put in comma-separated list
		StringBuilder cookies = new StringBuilder();
		for (Cookie cookie : cache) {
			// Remove cookies that have expired
			if (cookie.hasExpired()) {
				cache.remove(cookie);
			} else if (cookie.matches(uri)) {
				if (cookies.length() > 0) {
					cookies.append(", ");
				}
				cookies.append(cookie.toString());
			}
		}

		// Map to return
		Map<String, List<String>> cookieMap = new HashMap<String, List<String>>(
				requestHeaders);

		// Convert StringBuilder to List, store in map
		if (cookies.length() > 0) {
			List<String> list = Collections.singletonList(cookies.toString());
			cookieMap.put("Cookie", list);
		}
		System.out.println("Cookies: " + cookieMap);
		return Collections.unmodifiableMap(cookieMap);
	}
}