package com.mcmath.keyvalue.domain;

import java.io.Serializable;

public class ValueItem<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private T value;

	public ValueItem() {}
	
	public ValueItem(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
}
