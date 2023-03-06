package com.ashishpinninti.markdowntohtml.services;

public interface IConverterService {
	String convert(String markdown);

	String convertStream(String markdown);

	String processArrayInParallel(String markdown);
}