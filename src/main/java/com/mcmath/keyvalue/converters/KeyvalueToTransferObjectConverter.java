package com.mcmath.keyvalue.converters;

import java.util.ArrayList;
import java.util.List;

import com.mcmath.keyvalue.domain.Keyvalue;
import com.mcmath.keyvalue.domain.KeyvalueTransferObject;
import com.mcmath.keyvalue.domain.ValueItem;

public class KeyvalueToTransferObjectConverter {

	private KeyvalueToTransferObjectConverter() {
		// prohibited
	}

	@SuppressWarnings("rawtypes")
	public static KeyvalueTransferObject convertKeyValues(Iterable<Keyvalue> keyValues) {
		KeyvalueTransferObject keyValueTransferObject = new KeyvalueTransferObject();
		List<ValueItem> items = new ArrayList<>();

		ValueItem theValueItem = null;
		for (Keyvalue current : keyValues) {
			keyValueTransferObject.setSelector(current.getSelector());
			switch (current.getValueType()) {
			case INT:
				theValueItem = new ValueItem<Integer>(current.getName(), Integer.parseInt(current.getValue()));
				break;
			case STRING:
				theValueItem = new ValueItem<String>(current.getName(), current.getValue());
				break;
			case BOOL:
				theValueItem = new ValueItem<Boolean>(current.getName(), Boolean.parseBoolean(current.getValue()));
				break;
			default:
				theValueItem = new ValueItem<String>(current.getName(), current.getValue());
				break;
			}
			items.add(theValueItem);
		}
		keyValueTransferObject.setItems(items);
		return keyValueTransferObject;
	}
}
