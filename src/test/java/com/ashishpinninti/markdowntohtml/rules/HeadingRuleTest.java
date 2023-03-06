package com.ashishpinninti.markdowntohtml.rules;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ashishpinninti.markdowntohtml.services.rules.HeadingRule;

public class HeadingRuleTest {
	private static HeadingRule headingRule;

	@BeforeClass
	public static void setUp() {
		headingRule = new HeadingRule();
	}

	@Test
	public void testHeader1SingleLine() {
		String markdown = "# header 1";
		String expectedHtml = "<h1>header 1</h1>";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeader2SingleLine() {
		String markdown = "## header 2";
		String expectedHtml = "<h2>header 2</h2>";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeader3SingleLine() {
		String markdown = "### header 3";
		String expectedHtml = "<h3>header 3</h3>";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeader4SingleLine() {
		String markdown = "#### header 4";
		String expectedHtml = "<h4>header 4</h4>";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeader5SingleLine() {
		String markdown = "##### header 5";
		String expectedHtml = "<h5>header 5</h5>";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeader6SingleLine() {
		String markdown = "###### header 6";
		String expectedHtml = "<h6>header 6</h6>";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testHeaderGreaterThan6SingleLine() {
		String markdown = "####### header greater than 6";
		String expectedHtml = "####### header greater than 6";

		String actualHtml = headingRule.apply(markdown);

		assertEquals(expectedHtml, actualHtml);
	}
}