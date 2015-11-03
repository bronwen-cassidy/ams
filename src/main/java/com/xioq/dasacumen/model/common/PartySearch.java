package com.xioq.dasacumen.model.common;

import com.googlecode.genericdao.search.Filter;

import com.xioq.dasacumen.lib.search.Search;
import com.xioq.dasacumen.model.constants.PartyType;

public abstract class PartySearch extends Search<Party>
{
	public PartySearch()
	{
		super(Party.class);
	}
	
	/**
	 * Find all supplier Parties that are linked as a type of SUPPLIER or CLIENT.
	 * @param type : SUPPLIER or CLIENT.
	 * @see PartyType
	 */
	public void setTypeName(String typeName)
	{
		if(null != typeName)
		{
			// Check if correct. Note will get a 400 error if not.
			PartyType.valueOf(typeName);
			super.addFilterSome("partyTypes", Filter.equal("typeName", typeName));
		}
	}
	
	public void setTypeName(PartyType partyType)
	{
		setTypeName(partyType.name());
	}

	/**
	 * Find all Parties using partyId.
	 * @param partyId The primary key of the parties Table
	 */
	public void setPartyId(Long partyId){
		if(null != partyId)
		{
			super.addFilterEqual("id", partyId);
		}
	}

	public void setName(String name)
	{
		if(null != name)
		{
			// TODO Proper implementation of like search functionality needed
			// TODO Case insensitive searching
			if(name.indexOf('%') == -1)
				name = '%' + name + '%';
			
			super.addFilterOr(Filter.like("name", name), Filter.like("surname", name), Filter.like("forenames", name));
		}
	}

	/**
	 * Find all Parties that are linked as a custodian of a particular asset.
	 * @param assetId The primary key of the asset
	 */
	public void setCustodianAssetId(Long assetId)
	{
		if(null != assetId)
		{
			super.addFilterSome("custodianAssets", Filter.equal("id", assetId));
		}
	}
	
	/**
	 * Find all supplier Parties that are linked as a custodian of a particular asset.
	 * @param assetId The primary key of the asset
	 */
	public void setSupplierAssetId(Long assetId)
	{
		if(null != assetId)
		{
			super.addFilterSome("supplierAssets", Filter.equal("id", assetId));
		}
	}
	/**
	 * Find all clients that are linked to a lease out tenure
	 */
	public void setTenureClientAssetId(Long assetId)
	{
		if(null != assetId)
		{
			super.addFilterSome("assetTenureClients", Filter.equal("asset.id", assetId));
		}
	}

	public void setUid(String uid)
	{
		if(null != uid && !uid.isEmpty())
		{
			super.addFilterEqual("uid", uid);
		}
	}
	public String getUid()
	{
		return null;
	}

}