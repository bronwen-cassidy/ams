package com.xioq.dasacumen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xioq.dasacumen.test.HibernateDAOTestBase;

/**
 * To make controller tests simpler to implement
 * @author echhung
 *
 */
public class ControllerIntegrationTestBase extends HibernateDAOTestBase{

	@Autowired
	protected WebApplicationContext wac;
	
	/**
	 * A mock browser to perform requests to the controllers
	 */
	protected MockMvc mockMvc;
	
	protected void initialSetup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
