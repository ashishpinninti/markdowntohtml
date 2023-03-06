package com.ashishpinninti.markdowntohtml;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import com.ashishpinninti.markdowntohtml.services.IConverterService;
import com.ashishpinninti.markdowntohtml.services.MarkdownToHtmlConverterService;
import com.ashishpinninti.markdowntohtml.utils.FileUtility;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@Measurement(iterations = 5)
public class BenchmarkRunner {
	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}

	private static IConverterService converterService = new MarkdownToHtmlConverterService();

	String markdown = "";

	@Setup(Level.Invocation)
	public void setUp() throws IOException {
		markdown = FileUtility.readFileToString(System.getProperty("user.dir") + "/markdownInput.txt");
	}

	@Benchmark
	@Fork(value = 3, warmups = 2)
	@Warmup(iterations = 5)
	@BenchmarkMode(Mode.AverageTime)
	public void testAllRules(Blackhole blackhole) {
		String actualHtml = converterService.convert(markdown);
		blackhole.consume(actualHtml);
	}

	@Benchmark
	@Fork(value = 3, warmups = 2)
	@Warmup(iterations = 5)
	@BenchmarkMode(Mode.AverageTime)
	public void testAllRulesStream(Blackhole blackhole) {
		String actualHtml = converterService.convertStream(markdown);
		blackhole.consume(actualHtml);
	}
}