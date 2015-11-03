package com.xioq.dasacumen.lib;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xioq.dasacumen.lib.utilities.UidValueGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class UidValueGeneratorTest
{
	@Autowired
	UidValueGenerator uidValueGenerator;
	
	/**
	 * This tests whether the generator value is generated
	 */
	@Test
	public void generateValueTest()
	{
		assertNotNull(uidValueGenerator.generate());
	}
	
	/**
	 * This tests the uniqueness of the number generated
	 */
	@Test
	public void checkRandomValuesTest()
	{
		int iterations = 10000;
		String uid; 
		
		Set<String> generatedValues = new HashSet<String>();
		for (int i = 0; i < iterations; i++)
		{
			uid = uidValueGenerator.generate();
			
			if( generatedValues.add(uid) == false)
			{
				fail("failed due to duplicate value " + uid);
			}
		}
		
	}
	

}
