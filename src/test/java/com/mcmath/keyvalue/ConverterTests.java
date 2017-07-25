package com.mcmath.keyvalue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mcmath.keyvalue.converters.KeyvalueToTransferObjectConverter;
import com.mcmath.keyvalue.domain.Keyvalue;
import com.mcmath.keyvalue.domain.Keyvalue.ValueType;
import com.mcmath.keyvalue.domain.KeyvalueTransferObject;
import com.mcmath.keyvalue.domain.ValueItem;

public class ConverterTests {

	private static final String TYPE_A = "TypeA";
	private static final String TYPE_B = "TypeB";

	private List<Keyvalue> stringKeyvalueList = null;
	private List<Keyvalue> mixedKeyvalueList = null;

	@Before
	public void setupTest() {
		stringKeyvalueList = new ArrayList<>();
		stringKeyvalueList.add(createKeyValue(TYPE_A, "firstA-name", "firstA", ValueType.STRING));
		stringKeyvalueList.add(createKeyValue(TYPE_A, "secondA-name", "secondA", ValueType.STRING));
		stringKeyvalueList.add(createKeyValue(TYPE_A, "thirdA-name", "thirdA", ValueType.STRING));

		mixedKeyvalueList = new ArrayList<>();
		mixedKeyvalueList.add(createKeyValue(TYPE_B, "firstB-name", "3", ValueType.INT));
		mixedKeyvalueList.add(createKeyValue(TYPE_B, "secondB-name", "true", ValueType.BOOL));
		mixedKeyvalueList.add(createKeyValue(TYPE_B, "secondB-name", "stringval", ValueType.STRING));
	}

	@Test
	public void converterCopiesOverSelector() {
		KeyvalueTransferObject transferObject = KeyvalueToTransferObjectConverter.convertKeyValues(stringKeyvalueList);
		assertEquals(transferObject.getSelector(), stringKeyvalueList.get(0).getSelector());
	}

	@Test
	public void converterCreatesCorrectNumberOfValueItems() {
		KeyvalueTransferObject transferObject = KeyvalueToTransferObjectConverter.convertKeyValues(stringKeyvalueList);
		assertEquals(3, transferObject.getItems().size());
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void converterCompletelyCopiesOverItemData() {
		KeyvalueTransferObject transferObject = KeyvalueToTransferObjectConverter.convertKeyValues(stringKeyvalueList);
		boolean isEqual = true;

		List<ValueItem> convertedItems = transferObject.getItems();
		Iterator<ValueItem> convertedIterator = convertedItems.iterator();
		ValueItem currentValuePair = null;
		for (Keyvalue currentKeyValue : stringKeyvalueList) {
			currentValuePair = convertedIterator.next();
			isEqual &= currentKeyValue.getName().equals(currentValuePair.getName());
			isEqual &= currentKeyValue.getValue().equals(currentValuePair.getValue());
		}
		assertTrue(isEqual);
	}

	@SuppressWarnings({ "rawtypes" })
	@Test
	public void converterCreatesCorrectValueTypes() {
		KeyvalueTransferObject transferObject = KeyvalueToTransferObjectConverter.convertKeyValues(mixedKeyvalueList);
		boolean isEqual = true;

		List<ValueItem> convertedItems = transferObject.getItems();
		Iterator<ValueItem> convertedIterator = convertedItems.iterator();

		ValueItem currentValuePair = convertedIterator.next();
		isEqual &= currentValuePair.getValue().getClass().equals(Integer.class);

		currentValuePair = convertedIterator.next();
		isEqual &= currentValuePair.getValue().getClass().equals(Boolean.class);

		currentValuePair = convertedIterator.next();
		isEqual &= currentValuePair.getValue().getClass().equals(String.class);
		
		assertTrue(isEqual);
	}

	private Keyvalue createKeyValue(String key, String name, String value, ValueType valueType) {
		return new Keyvalue(key, name, value, valueType);
	}
}
