package com.ashishpinninti.markdowntohtml.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ashishpinninti.markdowntohtml.services.rules.HeadingRule;
import com.ashishpinninti.markdowntohtml.services.rules.IMarkdownRule;
import com.ashishpinninti.markdowntohtml.services.rules.ItalicRule;
import com.ashishpinninti.markdowntohtml.services.rules.LinkRule;
import com.ashishpinninti.markdowntohtml.services.rules.ParagraphRule;
import com.ashishpinninti.markdowntohtml.utils.StringBuilderUtils;

@Service
public class MarkdownToHtmlConverterService implements IConverterService {
	private List<IMarkdownRule> rules;

	public MarkdownToHtmlConverterService() {
		this.rules = new ArrayList<>();
		this.rules.add(new HeadingRule());
		// New rule to showcase extensibility
		this.rules.add(new ItalicRule());
		this.rules.add(new LinkRule());
		this.rules.add(new ParagraphRule());
	}

	/*
	 * Core method that splits markdown into lines, applies rules to each line in
	 * order and combines converted html lines
	 */
	public String convert(String markdown) {
		StringBuilder html = new StringBuilder();
		for (String line : markdown.split("\n")) {
			appendLine(html, convertLine(line));
		}

		return html.toString().trim();
	}

	/*
	 * applies rules to each line in order
	 */
	private String convertLine(String markdown) {
		if (markdown.isBlank()) {
			return markdown + "\n";
		}
		String html = markdown;
		for (IMarkdownRule rule : this.rules) {
			html = rule.apply(html);
		}
		return html + "\n";
	}

	/*
	 * Appends converted html line to final result and takes care of merging
	 * paragraph tags if needed
	 */
	private void appendLine(StringBuilder html, String line) {
		if (line.startsWith("<p>") && StringBuilderUtils.endsWith(html, "</p>\n")) {
			html.setLength(html.length() - 5);
			html.append("\n");
			line = line.replace("<p>", "");
		}
		html.append(line);
	}

	public String convertStream(String markdown) {
		StringBuilder html = new StringBuilder();
		String[] lines = markdown.split("\n");
		List<String> convertedLines = Arrays.stream(lines).parallel().map(line -> convertLine(line))
				.collect(Collectors.toList());
		convertedLines.stream().forEach(line -> appendLine(html, line));

		return html.toString().trim();
	}

	public String processArrayInParallel(String markdown) {
		String[] inputArray = markdown.split("\n");
		StringBuilder html = new StringBuilder();
		int numCores = 2;

		int batchSize = inputArray.length / numCores;

		String[][] batches = new String[numCores][];
		for (int i = 0; i < numCores; i++) {
			int startIndex = i * batchSize;
			int endIndex = (i == numCores - 1) ? inputArray.length : (i + 1) * batchSize;
			batches[i] = Arrays.copyOfRange(inputArray, startIndex, endIndex);
		}

		List<String> convertedLines = Arrays.stream(batches).parallel()
				.flatMap(batch -> Arrays.stream(batch).map(line -> convert(line))).collect(Collectors.toList());
		convertedLines.stream().forEach(line -> appendLine(html, line + "\n"));

		return html.toString().trim();
	}
}
