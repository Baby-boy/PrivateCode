package com.yd.dby.utils.thymeleaf.dialect.element;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Macro;
import org.thymeleaf.processor.IElementNameProcessorMatcher;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class YdDialectElementAjax extends AbstractElementProcessor {

	private static final String ELEMENT_NAME = "ajax";
	private static final int PRECEDENCE = 300;

	protected YdDialectElementAjax(IElementNameProcessorMatcher matcher) {
		super(matcher);
	}

	public YdDialectElementAjax() {
		super(ELEMENT_NAME);
	}

	public final ProcessorResult processElement(Arguments arguments, Element element) {

		String key = element.getAttributeValue("key");

		String value = element.getAttributeValue("value");

		String output = null;

		output = String.format("$.ajax({'type':'POST','url':%s.url(self),'data':JSON.stringify(%s.hasOwnProperty('tpl')?{conf:{'tpl':%s.tpl(self)},'val':params}:{'val':params}),'dataType':'json','contentType':'application/json'}).always(function(f98e09fd){%s.callback(self,f98e09fd)})", value, value, value, value);

		output = String.format("<script>var %s = function(self){var params=%s.params(self);if(%s.verification(self, params)){%s}}</script>", key, value, value, output);

		System.out.println(String.format("%s-%s", key, value));

		final Macro node = new Macro(output);

		node.setProcessable(false);

		element.addChild(node);

		element.getParent().extractChild(element);

		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

}
