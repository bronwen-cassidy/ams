package com.xioq.dasacumen.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xioq.dasacumen.model.constants.UserDataType;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This is the 'userdata' JSP tag class which handles the tags logic and operations.
 * 
 * @author mwaude
 */
public class UserDataTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	/** Reference name passed through JSP tag */
	private String var;
	/**
	 * User data ID passed through JSP tag, this ID is parsed into a Long
	 * <p>(Optional if type is supplied - Required if no type is supplied)
	 */
	private String userDataIdStr;
	/**
	 * UserDataType passed though JSP tag
	 * <p>(Optional if ID is supplied - Required if no ID is supplied)
	 */
	private UserDataType userDataType;

	@Override
	public int doEndTag() throws JspException {
		Long parsedId = null;
		
		if(!StringUtils.isEmpty(userDataIdStr)){			
			parsedId = parseId(userDataIdStr);
		}

		// Checks if the parsedId and UserData type are not null - if they are then an exception will be thrown
		if (!(StringUtils.isEmpty(var))) {
			if (!(parsedId == null && userDataType == null)) {

				// Retrieves the CRUDService bean
				WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
				CRUDService crudService = wac.getBean(CRUDService.class);

				/*
				 * 3 outcomes of this if statement: If the type is null and the ID is not, the crudService will retrieve one row of UserData based on
				 * the ID from the database If the type is not null and the ID is, the crudService will retrive all the rows of UserData which match
				 * the desired type. If both type and ID are not null, the crudService will retrieve one row of UserData based on the ID from the
				 * database. This defaults back to the ID.
				 */
				if (parsedId != null) {
					pageContext.setAttribute(var, crudService.retrieve(UserData.class, parsedId));
				} else {
					UserData searchExample = new UserData();
					searchExample.setUserDataTypeId(userDataType.getId());
					pageContext.setAttribute(var, crudService.findByExample(searchExample));
				}

			} else {
				pageContext.removeAttribute(var);
				//throw new JspException("Invalid or no userDataId or userDataType. Current values (userDataId: '" + parsedId + "', userDataType: '" + userDataType + "')");
			}
		} else {
			throw new JspException("Invalid or no attribute name (var) provided. Current value (ref: '" + var + "')");
		}
		return super.doEndTag();
	}

	/**
	 * A simple String to Long parsing method to handle exceptions thrown whilst parsing a String to a Long.
	 * <p>This method is used to convert the String ID passed by the JSP page to a Long ID used in searching the database.
	 * 
	 * @param toParse
	 * @return parsedValue
	 * @throws JspException
	 */
	private Long parseId(String toParse) throws JspException {
		if (toParse != null) {
			try {
				return Long.parseLong(toParse);
			} catch (NumberFormatException e) {
				throw new JspException("Failed to convert userDataId for attribute '" + var + "'. Must be of type Long. Current value (userDataId: '"
						+ toParse + "').", e);
			}
		}
		return null;
	}

	public String getUserDataId() {
		return userDataIdStr;
	}

	public void setUserDataId(String userDataId) {
		this.userDataIdStr = userDataId;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public UserDataType getUserDataType() {
		return userDataType;
	}

	public void setUserDataType(UserDataType userDataType) {
		this.userDataType = userDataType;
	}
}
