package com.mcmath.keyvalue.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcmath.keyvalue.domain.Keyvalue;

@Repository
public interface KeyvalueRepository extends CrudRepository<Keyvalue, Integer> {

	public Iterable<Keyvalue> findBySelector(String selector);
}
