package com.mcmath.keyvalue;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mcmath.keyvalue.dao.KeyvalueDao;
import com.mcmath.keyvalue.domain.Keyvalue;
import com.mcmath.keyvalue.domain.Keyvalue.ValueType;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = com.mcmath.keyvalue.dao.KeyvalueDao.class)
@DataJpaTest
public class IntegrationTests {
	private static final String KEY_A = "keyA";
	private static final String KEY_B = "keyB";
	
	@Autowired
	KeyvalueDao keyvalueDao;
	
	@Before
	public void setupRepository() {
		Keyvalue keyValue = new Keyvalue(KEY_A, "firstA-name", "firstA", ValueType.STRING);
		keyvalueDao.save(keyValue);
		
		keyValue = new Keyvalue(KEY_A, "secondA-name", "secondA", ValueType.STRING);
		keyvalueDao.save(keyValue);
		
		keyValue = new Keyvalue(KEY_B, "firstB-name", "firstB", ValueType.STRING);
		keyvalueDao.save(keyValue);
	}
	
	@After
	public void clearRepository() {
		keyvalueDao.removeAll();
	}
	
	@Test
	public void findByKeyFindsAllRecords() {
		Iterable<Keyvalue> foundIter = keyvalueDao.findByKey(KEY_A);
		assertEquals(2, foundIter.spliterator().getExactSizeIfKnown());
	}

}
