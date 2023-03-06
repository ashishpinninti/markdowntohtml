package com.ashishpinninti.markdowntohtml.rules;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ashishpinninti.markdowntohtml.services.IConverterService;
import com.ashishpinninti.markdowntohtml.services.MarkdownToHtmlConverterService;
import com.ashishpinninti.markdowntohtml.utils.FileUtility;

public class MarkdownToHtmlConverterServiceTest {
	private static IConverterService converterService;

	@BeforeClass
	public static void setUp() {
		converterService = new MarkdownToHtmlConverterService();
	}

	@Test
	public void testHeadersMultiLine() {
		String markdown = """
				# Header 1
				###### Header 6

				### Header 3

				## Header 2

				##### Header 5


				#### Header 4""";

		String expectedHtml = """
				<h1>Header 1</h1>
				<h6>Header 6</h6>

				<h3>Header 3</h3>

				<h2>Header 2</h2>

				<h5>Header 5</h5>


				<h4>Header 4</h4>""";

		String actualHtml = converterService.convert(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testUnformattedTextMultiLine() {
		String markdown = """
				How are you?
				What's going on?""";

		String expectedHtml = """
				<p>How are you?
				What's going on?</p>""";

		String actualHtml = converterService.convert(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testAllRules() {
		String markdown = """
				# Header one

				Hello *italics* there

				How are you?
				What's going on?

				## Another Header

				This is a paragraph [with an inline link](http://google.com). Neat, eh?

				## This is a header [with a link](http://yahoo.com)""";

		String expectedHtml = """
				<h1>Header one</h1>

				<p>Hello <em>italics</em> there</p>

				<p>How are you?
				What's going on?</p>

				<h2>Another Header</h2>

				<p>This is a paragraph <a href="http://google.com">with an inline link</a>. Neat, eh?</p>

				<h2>This is a header <a href="http://yahoo.com">with a link</a></h2>""";

		String actualHtml = converterService.convert(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testAllRulesStream() {
		String markdown = """
				# Header one

				Hello *italics* there

				How are you?
				What's going on?

				## Another Header

				This is a paragraph [with an inline link](http://google.com). Neat, eh?

				## This is a header [with a link](http://yahoo.com)""";

		String expectedHtml = """
				<h1>Header one</h1>

				<p>Hello <em>italics</em> there</p>

				<p>How are you?
				What's going on?</p>

				<h2>Another Header</h2>

				<p>This is a paragraph <a href="http://google.com">with an inline link</a>. Neat, eh?</p>

				<h2>This is a header <a href="http://yahoo.com">with a link</a></h2>""";

		String actualHtml = converterService.convertStream(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testAllRulesProcessArrayInParallel() {
		String markdown = """
				# Header one

				Hello *italics* there

				How are you?
				What's going on?

				## Another Header

				This is a paragraph [with an inline link](http://google.com). Neat, eh?

				## This is a header [with a link](http://yahoo.com)""";

		String expectedHtml = """
				<h1>Header one</h1>

				<p>Hello <em>italics</em> there</p>

				<p>How are you?
				What's going on?</p>

				<h2>Another Header</h2>

				<p>This is a paragraph <a href="http://google.com">with an inline link</a>. Neat, eh?</p>

				<h2>This is a header <a href="http://yahoo.com">with a link</a></h2>""";

		String actualHtml = converterService.processArrayInParallel(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

	@Test
	public void testAllRulesLargeInput() throws IOException {
		String markdown = FileUtility.readFileToString(System.getProperty("user.dir") + "/markdownInput.txt");
		String actualHtml = converterService.convert(markdown);
		FileUtility.writeStringToFile(System.getProperty("user.dir") + "/htmlOutput.txt", actualHtml);
		String expectedHtml = FileUtility.readFileToString(System.getProperty("user.dir") + "/htmlOutput.txt");

//		System.out.println(expectedHtml);
//		System.out.println(actualHtml);
		assertEquals(expectedHtml.trim(), actualHtml.trim());
	}
}