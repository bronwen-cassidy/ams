package com.xioq.dasacumen.web;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hibernate.SessionFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.model.systemadmin.UserDataCat;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

//@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
@Transactional
/*
 * TransactionDbUnitTestExecutionListener.class,
 * TransactionalTestExecutionListener DbUnitTestExecutionListener.class})
 * AbstractJUnit4SpringContextTests
 */
public class SystemAdminControllerIntegrationTests{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private TestUtil testUtil;

	/**
	 * A mock browser to perform requests to the controllers
	 */
	private MockMvc mockMvc;

	/**
	 * Sets up the mock mvc in setup
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		testUtil.resetSequences("user_data");
	}

	/**
	 * Tests system admin controller gets forwarded correctly
	 */
	@Test
	public void SystemAdminControllerHomeTest() throws Exception {

		// perform a get request
		mockMvc.perform(get("/systemAdmin"))
				// check if status ok
				.andExpect(status().isOk())
				// check if the forwarded url is this (check controller method
				// to verify this)
				.andExpect(forwardedUrl("systemadmin.home"))
				.andExpect(model().hasNoErrors());
	}

	/**
	 * Tests the system admin controller user data method to see if it returns
	 * the correct values. No setup is required because these values should
	 * already be in the user data cat because these particular rows should be
	 * here at all times.
	 */
	@Test
	// value - attribute concerns the data to expect, table attribute is for which
	// table - dbunit checks, and assertion mode makes dbunit only
	// check - the values that were given in the xml file instead of all of them.
	@ExpectedDatabase(value = "classpath:dbunit/userDataCatTestData.xml", table = "acumen.user_data_cat", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void SystemAdminControllerUserDataTest() throws Exception {
		// This is a returned list name
		String modelName = "topLevelUserDataCats";

		mockMvc.perform(get("/systemAdmin/userData"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("systemadmin.userData"))
				.andExpect(model().hasNoErrors())
				// check that the model contains an atrribute with a list
				.andExpect(model().attribute(modelName, any(List.class)))
				// check that it has a size of four
				.andExpect(model().attribute(modelName, hasSize(4)))
				// check that it has items of type user data cat
				.andExpect(
						model().attribute(modelName,
								hasItem(any(UserDataCat.class))))
				// Has 4 items: AssetRegister, Finance, Maintenance, Parts
				.andExpect(model().attribute(modelName, IsIterableContainingInAnyOrder.<UserDataCat> containsInAnyOrder(
												hasProperty("name", is("Asset Register")),
												hasProperty("name", is("Finance")),
												hasProperty("name", is("Maintenance")),
												hasProperty("name", is("Parts")))));
	}

	/**
	 * Tests the system admin controller user data method to see if it returns
	 * the correct values. We use a setup to put in the correct values in the
	 * database. We Then assert that these haven't been touched at the end and
	 * have only been retrieved
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataTestData.xml", table = "acumen.user_data", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void SystemAdminControllerGetListTest() throws Exception {
		// This currently refers to user data type: company
		Long userDataTypeId = 4L;
		String modelName = "listItems";

		mockMvc.perform(get("/systemAdmin/listItems/" + userDataTypeId))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("systemadmin.editlist"))
				.andExpect(model().hasNoErrors())
				.andExpect(
						model().attribute("userDataTypeToEdit",
								any(UserDataTypes.class)))
				// User data type get name
				.andExpect(model().attribute("userDataFieldName", "Company"))
				// returns a list of userdata
				.andExpect(model().attribute(modelName, any(List.class)))
				.andExpect(model().attribute(modelName, hasItem(any(UserData.class))))
				.andExpect(model().attribute(modelName, IsIterableContainingInAnyOrder.<UserData> containsInAnyOrder(
												hasProperty("name", is("USA")),
												hasProperty("name", is("Mining PLC")),
												hasProperty("name", is("Level Crossing Ltd")),
												hasProperty("name", is("Manufacturing")))));
	}

	/**
	 * Tests that the controller returns an empty list field.
	 */
	@Test
	public void SystemAdminControllerGetNewListItemTest() throws Exception {
		mockMvc.perform(get("/systemAdmin/listItem"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("systemAdmin/templates/newListItem"))
				.andExpect(model().hasNoErrors());
	}

	/**
	 * Tests if a list field is added
	 * TODO TEST FAILING BECAUSE OF SEQUENCE STARTING AT 7 OR 8
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataTestDataDeletedOne.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataTestData.xml", table = "acumen.user_data", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void SystemAdminControllerAddListFieldTest() throws Exception {
		/*
		 * This is the row I took out of the user data test data to add.
		 * acumen.user_data id="4" user_data_types_id="4" name="Manufacturing"
		 * ud_order="2" active="true" tenant_id="1" version_number="1"
		 */

		// Make the user data object
		UserData ud = new UserData();
		ud.setId(4L);
		ud.setName("Manufacturing");
		ud.setUserDataTypeId(4L);
		ud.setUdOrder(2);
		ud.setActive(true);
		ud.setVersionNumber(1);

		// Convert the object a json string
		Gson userDataGson = new Gson();
		String json = userDataGson.toJson(ud);

		mockMvc.perform(post("/systemAdmin/listItem")
				// check content is json
				.contentType(MediaType.APPLICATION_JSON)
				// feed the json string
				.content(json))
				//expect a status 200
				.andExpect(status().isOk())
				// check that the name is Manufacturing
				.andExpect(jsonPath("$.name", is("Manufacturing")));

		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

	/**
	 * Tests if a list field is deleted
	 * 
	 * @throws Exception
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataTestDataDeletedOne.xml", table = "acumen.user_data", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void SystemAdminControllerDeleteListFieldTest() throws Exception {

		UserData ud = new UserData();
		ud.setId(4L);
		ud.setName("Manufacturing");
		ud.setUserDataTypeId(4L);
		ud.setUdOrder(2);
		ud.setActive(true);
		ud.setVersionNumber(1);

		Gson userDataGson = new Gson();
		String json = userDataGson.toJson(ud);

		mockMvc.perform(
				delete("/systemAdmin/listItem").contentType(
						MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isOk());

		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

	/**
	 * Tests if a list field is updated
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataTestDataModifiedOne.xml", table = "acumen.user_data", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void SystemAdminControllerUpdateListFieldTest() throws Exception {

		UserData ud = new UserData();
		ud.setId(4L);
		// Changed name from manufacturing to development
		ud.setName("Development");
		ud.setUserDataTypeId(4L);
		ud.setUdOrder(2);
		ud.setActive(true);
		ud.setVersionNumber(1);

		Gson userDataGson = new Gson();
		String json = userDataGson.toJson(ud);

		mockMvc.perform(
				put("/systemAdmin/listItem").contentType(
						MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Development")));

		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

	/**
	 * Tests if multiple list fields are updated
	 */
	@Test
	public void SystemAdminControllerUpdateListFieldsTest() throws Exception {
		fail("Not Implemented");

	}
}
