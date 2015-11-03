package com.xioq.dasacumen.model.common;

import com.xioq.dasacumen.lib.search.Search;
import com.xioq.dasacumen.model.Group;

import com.googlecode.genericdao.search.Filter;

/**
 * 
 * Search for groups
 * 
 * @author BWorsley
 * 
 */
public class GroupsSearch extends Search<Group>
{
	public GroupsSearch()
	{
		super(Group.class);
	}
	
	public void setName(String name)
	{
		if(null != name)
		{
			// TODO Case insensitive searching
			if(name.indexOf('%') == -1)
				name = '%' + name + '%';
			
			super.addFilterLike("name", name);
		}
	}
	
	public void setAssetId(Long assetId)
	{
		if(null != assetId)
		{
			super.addFilterSome("groupAssets", Filter.equal("asset.id", assetId));
		}
	}
	
	public String getAssetId()
	{
		return null;
	}
}
