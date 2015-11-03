package com.xioq.dasacumen.model.common;

@SuppressWarnings("serial")
public class ClientSearch extends PartySearch
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
}
