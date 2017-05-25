package com.yd.dby.utils.thymeleaf.dialect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

public class YdDialectHelper extends AbstractDialect implements IExpressionEnhancingDialect {
	private static final Map<String, Object> EXPRESSION_OBJECTS;

	static {
		Map<String, Object> objects = new HashMap<>();
//		objects.put("ydTime", YdUtilTime.class);
//		objects.put("ydString", YdUtilString.class);
		EXPRESSION_OBJECTS = Collections.unmodifiableMap(objects);
	}

	@Override
	public String getPrefix() {
		return null;
	}

	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext arg0) {
		return EXPRESSION_OBJECTS;
	}

}