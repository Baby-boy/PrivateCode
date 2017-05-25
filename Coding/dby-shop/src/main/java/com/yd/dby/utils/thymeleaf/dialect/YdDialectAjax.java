package com.yd.dby.utils.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import com.yd.dby.utils.thymeleaf.dialect.attr.YdDialectAttrAjax;
import com.yd.dby.utils.thymeleaf.dialect.element.YdDialectElementAjax;

public class YdDialectAjax extends AbstractDialect {

	public YdDialectAjax() {
		super();
	}

	public String getPrefix() {
		return "yd";
	}

	@Override
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new YdDialectAttrAjax());
		processors.add(new YdDialectElementAjax());
		return processors;
	}

}