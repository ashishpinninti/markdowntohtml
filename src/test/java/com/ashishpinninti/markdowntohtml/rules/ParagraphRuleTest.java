package com.ashishpinninti.markdowntohtml.rules;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ashishpinninti.markdowntohtml.services.rules.ParagraphRule;

public class ParagraphRuleTest {
	private static ParagraphRule paragraphRule;

	@BeforeClass
	public static void setUp() {
		paragraphRule = new ParagraphRule();
	}

	@Test
	public void testUnformattedText() {
		String markdown = "Unformatted text";
		String expectedHtml = "<p>Unformatted text</p>";

		String actualHtml = paragraphRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testFormattedText() {
		String markdown = "<a href=\"https://www.mailchimp.com\">formatted</a>";
		String expectedHtml = "<a href=\"https://www.mailchimp.com\">formatted</a>";

		String actualHtml = paragraphRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}
}