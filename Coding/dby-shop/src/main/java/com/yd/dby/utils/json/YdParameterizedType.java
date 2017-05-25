package com.yd.dby.utils.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class YdParameterizedType implements ParameterizedType {
	private final Class<?> class0;
	private final Type[] types0;

	public YdParameterizedType(Class<?> class0, Type type0) {
		this.class0 = class0;
		this.types0 = new Type[] { type0 };
	}

	public YdParameterizedType(Class<?> class0, Type[] types0) {
		this.class0 = class0;
		this.types0 = types0;
	}

	@Override
	public Type[] getActualTypeArguments() {
		return types0;
	}

	@Override
	public Type getRawType() {
		return class0;
	}

	@Override
	public Type getOwnerType() {
		return null;
	}

}