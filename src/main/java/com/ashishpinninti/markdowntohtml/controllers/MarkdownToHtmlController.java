package com.ashishpinninti.markdowntohtml.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ashishpinninti.markdowntohtml.services.IConverterService;

/**
 *
 * A MarkdownToHtml controller to process markdown and return html
 */
@RestController
public class MarkdownToHtmlController {

	@Autowired
	private IConverterService converterService;

	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String convert(@RequestBody String markdown) {
		return converterService.convert(markdown);
	}

	@RequestMapping(value = "/convertStream", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String convertStream(@RequestBody String markdown) {
		return converterService.convertStream(markdown);
	}
}
