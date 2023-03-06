package com.ashishpinninti.markdowntohtml.services.rules;

import org.springframework.stereotype.Component;

@Component
public class ParagraphRule implements IMarkdownRule {
	public String apply(String markdown) {
		if (markdown.startsWith("<") && markdown.endsWith(">")) {
			return markdown;
		}
		return String.format("<p>%s</p>", markdown);
	}
}