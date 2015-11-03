package com.xioq.dasacumen.test;


/**
 * Helps to test crud functionality. Does not have a retrieve test
 * because it is used in the other tests anyway.
 * @author echhung
 *
 */
public interface CrudTests {
	
	/**
	 * Tests if the object can be retrieved by the DAO
	 */
	public void retrieveTest();
	
	/**
	 * Tests if the object can be created by the DAO
	 */
	public void createTest();
	
	/**
	 * Tests if the object can be updated by the DAO
	 */
	public void updateTest();
	
	/**
	 * Tests if the object can be deleted by the DAO. A "system.out.println" is used
	 * in most of tests to trigger the object not found exception this is due to lazy loading.
	 */
	public void deleteTest();
	
	/**
	 * Tests if the object throws a stale state exception. This is done by retrieving the same
	 * object with two different instances. Then update the first and persist that to the database, then try
	 * to update the second and persist. When the database tries to persist the object it will throw a 
	 * stale state exception. 
	 */
	public void staleStateExceptionTest();
}
