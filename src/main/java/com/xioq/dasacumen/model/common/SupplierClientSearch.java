package com.xioq.dasacumen.model.common;

@SuppressWarnings("serial")
public class SupplierClientSearch extends PartySearch
{
	@Override
	public void setName(String name)
	{
		if(null != name && !name.isEmpty())
		{
			// TODO Case insensitive searching
			if(name.indexOf('%') == -1)
				name = '%' + name + '%';
			
			super.addFilterLike("name", name);
		}
	}
	public String getName()
	{
		return null;
	}

// TODO: We could possibly change the current workings that set typeName to use the below constructor and boolean instead. TBC.
//	public SupplierClientSearch()
//	{
//		// TODO Retrict the party search to just suppliers or clients.
////		super.addFilter("partyType", "");
//			
//	}
//	/**
//	 * @param supplier true for a supplier search, false for client.
//	 */
//	public SupplierClientSearch(boolean supplier)
//	{
//		this();
//	}
	
}
