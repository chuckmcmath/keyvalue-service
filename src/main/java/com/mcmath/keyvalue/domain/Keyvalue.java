package com.mcmath.keyvalue.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "key_values")
public class Keyvalue implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum ValueType {
		BOOL, INT, STRING
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String selector;
	@Column
	private String name;
	@Column
	private String value;
	@Column
	private ValueType valueType;

	public Keyvalue() {}
	
	public Keyvalue(String selector, String name, String value, ValueType valueType) {
		this.selector = selector;
		this.name = name;
		this.value = value;
		this.valueType = valueType;
	}
	
	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
	
}
