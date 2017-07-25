package com.mcmath.keyvalue.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcmath.keyvalue.domain.Keyvalue;
import com.mcmath.keyvalue.repository.KeyvalueRepository;

@Service
@Transactional
public class KeyvalueDao  {

	@Autowired
	KeyvalueRepository keyvalueRepository;
	
	public Iterable<Keyvalue> findByKey(String key) {
		return keyvalueRepository.findBySelector(key);
	}
	
	public void save(Keyvalue keyValue) {
		keyvalueRepository.save(keyValue);
	}
	
	public void removeAll() {
		keyvalueRepository.deleteAll();
	}
}
