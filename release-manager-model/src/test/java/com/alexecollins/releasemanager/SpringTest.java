package com.alexecollins.releasemanager;

import com.alexecollins.releasemanager.model.ComponentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/alexecollins/releasemanager/model/applicationContext.xml")
public class SpringTest {

	@Autowired
	ComponentRepository db;

	@Test
	public void testContext() throws Exception {
		assertNotNull(db);
	}
}