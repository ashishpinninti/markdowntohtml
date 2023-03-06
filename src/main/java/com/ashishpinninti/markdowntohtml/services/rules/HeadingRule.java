package com.ashishpinninti.markdowntohtml.services.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class HeadingRule implements IMarkdownRule {
	private static final Pattern pattern = Pattern.compile("(^#{1,6})\\s+(.*)");

	public String apply(String markdown) {
		Matcher matcher = pattern.matcher(markdown);
		if (matcher.matches()) {
			int level = matcher.group(1).length();
			String text = matcher.group(2);
			return String.format("<h%d>%s</h%d>", level, text, level);
		}
		return markdown;
	}
}