package com.xioq.dasacumen.model.constants;

/**
 * File to hold constant/lookup values for any search information.
 * 
 * @author Stephen Elliott
 * 
 */
public enum PartyType
{
	CUSTODIAN, SUPPLIER, CLIENT, ENGINEER;

	@Override
	public String toString()
	{
		// only capitalise the first letter
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}
