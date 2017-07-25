package com.mcmath.keyvalue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcmath.keyvalue.converters.KeyvalueToTransferObjectConverter;
import com.mcmath.keyvalue.dao.KeyvalueDao;
import com.mcmath.keyvalue.domain.Keyvalue;
import com.mcmath.keyvalue.domain.KeyvalueTransferObject;

import com.mcmath.keyvalue.domain.Keyvalue.ValueType;

@RestController
public class KeyvalueController {
	
	@Autowired
	KeyvalueDao keyvalueDao;
	
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public void initDatabase() {
		keyvalueDao.removeAll();
		
		Keyvalue kv = new Keyvalue("typeA", "First A Name", "first-A-value", ValueType.STRING);
		keyvalueDao.save(kv);
		
		kv = new Keyvalue("typeA", "Second A Name", "second-A-value", ValueType.STRING);
		keyvalueDao.save(kv);
		
		kv = new Keyvalue("typeB", "First B", "1", ValueType.INT);
		keyvalueDao.save(kv);
		
		kv = new Keyvalue("typeA", "Third A Name", "third-A-value", ValueType.STRING);
		keyvalueDao.save(kv);

		kv = new Keyvalue("typeB", "Second B is 45", "45", ValueType.INT);
		keyvalueDao.save(kv);
		
}
	
	@RequestMapping(value = "/getByKey/{desiredKey}")
	public KeyvalueTransferObject getValuesByKey(@PathVariable String desiredKey) {
		Iterable<Keyvalue> foundValues = keyvalueDao.findByKey(desiredKey);
		return KeyvalueToTransferObjectConverter.convertKeyValues(foundValues);
	}
}
