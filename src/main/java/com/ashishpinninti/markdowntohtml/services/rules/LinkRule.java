package com.ashishpinninti.markdowntohtml.services.rules;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class LinkRule implements IMarkdownRule {
	private static final Pattern pattern = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");

	public String apply(String markdown) {
		return markdown.replaceAll(pattern.pattern(), "<a href=\"$2\">$1</a>");
	}
}