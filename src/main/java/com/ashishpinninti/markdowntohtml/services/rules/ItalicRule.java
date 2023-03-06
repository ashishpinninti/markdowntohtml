package com.ashishpinninti.markdowntohtml.services.rules;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ItalicRule implements IMarkdownRule {
	private static final Pattern pattern = Pattern.compile("\\*(.*?)\\*");

	public String apply(String markdown) {
		return markdown.replaceAll(pattern.pattern(), "<em>$1</em>");
	}
}