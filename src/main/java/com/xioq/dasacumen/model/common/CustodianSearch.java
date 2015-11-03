package com.xioq.dasacumen.model.common;

import com.xioq.dasacumen.model.constants.PartyType;

public class CustodianSearch extends PartySearch
{
	public CustodianSearch()
	{
		// Restrict the party search to just custodians.
		setTypeName(PartyType.CUSTODIAN);
	}
	
	/*
	 * TODO Location information for a Custodian. Hmmm. But held on the asset? 
	 * Therefore, should all this also be held on the custodian.
	 */
	/**
	 * Set which country the custodian resides. 
	 * TODO Country is User Data. So a FK?
	 * @param country
	 */
	public void setCountry(String country)
	{
	//		super.addFilterEqual("name", country);
	}

//	public void setCompany(String company)
//	{
//		this.company = company;
//	}
//
//	public void setDivision(String division)
//	{
//		this.division = division;
//	}
//
//	public void setSite(String site)
//	{
//		this.site = site;
//	}
//
//	public void setDepartment(String department)
//	{
//		this.department = department;
//	}
//
//	public void setLocation(String location)
//	{
//		this.location = location;
//	}
	
}
