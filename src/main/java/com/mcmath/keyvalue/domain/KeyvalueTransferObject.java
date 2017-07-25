package com.mcmath.keyvalue.domain;

import java.util.List;

public class KeyvalueTransferObject {
	private String selector;
	@SuppressWarnings("rawtypes")
	private List<ValueItem> items;

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	@SuppressWarnings("rawtypes")
	public List<ValueItem> getItems() {
		return items;
	}

	@SuppressWarnings("rawtypes")
	public void setItems(List<ValueItem> items) {
		this.items = items;
	}

}
