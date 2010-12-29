package com.sve.timetracker;

import java.util.HashMap;
import java.util.Map;

public class Escaper {

	Map<Character, String> map;

	public Escaper() {
		map = new HashMap<Character, String>();
		map.put('&', "&amp;");
		map.put('<', "&lt;");
		map.put('>', "&gt;");
	}

	public String escapeXML(final String text) {
		final StringBuffer buf = new StringBuffer(text);

		int pos = 0;
		while (pos < buf.length()) {
			final char ch = buf.charAt(pos);
			if (map.containsKey(ch)) {
				final String replacement = map.get(ch);
				buf.replace(pos, pos + 1, replacement);
				pos += replacement.length();
			} else {
				pos += 1;
			}
		}

		return buf.toString();
	}
}
