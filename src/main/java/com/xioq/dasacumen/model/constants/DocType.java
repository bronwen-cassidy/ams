package com.xioq.dasacumen.model.constants;

/**
 * Used with doc and doc link objects for doc type.
 * @author echhung
 *
 */
public enum DocType {
	MAINIMAGE,
	DOCUMENT,
	LEASEAGREEMENT;
	
	
	@Override
	public String toString()
	{
		// only capitalise the first letter
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}

}
