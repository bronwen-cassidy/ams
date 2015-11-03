package com.xioq.dasacumen.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
/**
 * Asset Register Controller integration tests only. These test the controller to the db 
 * using a mock webservice for the requests. DbUnit is used to setup and tear down from database.
 * @author echhung
 *
 */
public class AssetRegisterControllerIntegrationTests {
	
	@Autowired
	/**
	 * MockMvc needs this to initialise. It is for the web app config beans. 
	 */
	private WebApplicationContext wac;
	
	/**
	 * To mock the calls to the controller.
	 */
    private MockMvc mockMvc;

    @Before
    /**
     * Initialises the MockMvc.
     */
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Tests the asset register controller correctly retrieves a list of 13 assets from db. The
     * ExpectedDatabase annotation checks the db with "value", checks only the table assets from
     * "table" field and only checks the fields which are in the xml file (instead of all of them)
     * according to AssertionMode.  
     */
    @Test
    @Transactional
    @DatabaseSetup("classpath:dbunit/assetTestData.xml")
    @ExpectedDatabase(value = "classpath:dbunit/assetTestData.xml", table = "acumen.assets", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void AssetRegisterControllerHomeTest() throws Exception {
		mockMvc.perform(get("/assetRegister"))
			.andExpect(status().isOk())
			.andExpect(model().hasNoErrors())
			.andExpect(forwardedUrl("asset.register.home"))
			.andExpect(model().attribute("assets", hasSize(2)));
    }
}