package com.ashishpinninti.markdowntohtml.rules;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ashishpinninti.markdowntohtml.services.rules.LinkRule;

public class LinkRuleTest {
	private static LinkRule linkRule;

	@BeforeClass
	public static void setUp() {
		linkRule = new LinkRule();
	}

	@Test
	public void testOnlyLink() {
		String markdown = "[Mailchimp](https://www.mailchimp.com)";
		String expectedHtml = "<a href=\"https://www.mailchimp.com\">Mailchimp</a>";

		String actualHtml = linkRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testLinkWithSurroundingText() {
		String markdown = "surrounding text [Mailchimp](https://www.mailchimp.com) surrounding text.";
		String expectedHtml = "surrounding text <a href=\"https://www.mailchimp.com\">Mailchimp</a> surrounding text.";

		String actualHtml = linkRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeader3SingleLine() {
		String markdown = "[Long link](https://thisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurl.com/img/icon256.png)";
		String expectedHtml = "<a href=\"https://thisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurlthisislongurl.com/img/icon256.png\">Long link</a>";

		String actualHtml = linkRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}
}