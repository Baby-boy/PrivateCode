package com.yd.dby.utils.thymeleaf.dialect.attr;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.IAttributeNameProcessorMatcher;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;

public final class YdDialectAttrAjax extends AbstractAttrProcessor {

	public static final int ATTR_PRECEDENCE = 1000;
	public static final String ATTR_NAME = "ajax";

	protected YdDialectAttrAjax(IAttributeNameProcessorMatcher matcher) {
		super(matcher);
	}

	public YdDialectAttrAjax() {
		super(ATTR_NAME);
	}

	@Override
	protected ProcessorResult processAttribute(Arguments arguments, Element element, String attributeName) {

		String[] values = element.getAttributeValue(attributeName).split(",");

		String output = null;

		output = String.format("$.ajax({'type':'POST','url':%s.url(this),'data':JSON.stringify(%s.hasOwnProperty('tpl')?{conf:{'tpl':%s.tpl(this)},'val':%s.params(this)}:{'val':%s.params(this)}),'dataType':'json','contentType':'application/json'}).always(function(f98e09fd){%s.callback(this,f98e09fd)})", values[1], values[1], values[1], values[1], values[1], values[1]);

		output = String.format("if(%s.verification(this, %s.params(this))){%s}", values[1], values[1], output);

		element.setAttribute(values[0], false, output, true);

		element.removeAttribute(element.getAttributeOriginalNameFromNormalizedName(attributeName));

		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return ATTR_PRECEDENCE;
	}

}