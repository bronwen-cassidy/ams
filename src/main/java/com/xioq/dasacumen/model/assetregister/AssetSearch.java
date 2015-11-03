package com.xioq.dasacumen.model.assetregister;

import java.util.Date;

import com.xioq.dasacumen.lib.search.Search;
import com.xioq.dasacumen.model.EntityList;
import com.xioq.dasacumen.model.EntityListContent;

import com.googlecode.genericdao.search.Filter;

/**
 * Search object for Assets. 
 * @author Stephen Elliott
 */
public class AssetSearch extends Search<Assets>
{
	public AssetSearch()
	{
		super(Assets.class);
	}

	/**
	 * Find all assets with life expectancy greater than or equal to the two dates passed in, if they are both supplied!
	 * Can be used with with both dates or on their own.
	 * 
	 * @param name : endOfLifeDate.
	 */
	public void setEndOfLifeDateRange(Date from, Date to)
	{
		if(null != from)
		{
			super.addFilterGreaterOrEqual("endOfLifeDate", from);
		}
		if (null!=to)
		{
			super.addFilterLessOrEqual("endOfLifeDate", to);
		}
	}
	public String getEndOfLifeDate()
	{
		return null;
	}
	
	/**
	 * Find all custodian data that is linked to the party.id.
	 * @param name : Name of custodian - could be a person or a company.
	 */
	public void setCustodianName(String custoidanName)
	{
		if(null != custoidanName &&  !custoidanName.isEmpty())
		{
			super.addFilterSome("custodian", Filter.equal("name", custoidanName));
		}
	}
	public String getCustodianName()
	{
		return null;
	}

	/**
	 * Find all supplier data that is linked to the party.id.
	 * @param name : Name of supplier - could be a person or a company.
	 */
	public void setSupplierName(String supplierName)
	{
		if(null != supplierName &&  !supplierName.isEmpty())
		{
			if(supplierName.indexOf('%') == -1)
				supplierName = '%' + supplierName + '%';
			super.addFilterSome("supplier", Filter.like("name", supplierName));
			//			super.addFilterOr("supplier", Filter.equal("name", supplierName), Filter.like("surname", supplierName), Filter.like("forenames", supplierName));
		}
	}
	public String getSupplierName()
	{
		return null;
	}	
	
	/**
	 * Find all manufacturer data that is linked to the party.id.
	 * @param name : Name of manufacturer - could be a person or a company.
	 */
	public void setManufacturerName(String manufacturerName)
	{
		if(null != manufacturerName &&  !manufacturerName.isEmpty())
		{
			super.addFilterSome("manufacturer", Filter.equal("name", manufacturerName));
		}
	}
	public String getManufacturerName()
	{
		return null;
	}
	
	/**
	 * Find all cleint data that is linked to the party.id. A client is basically the lease owner linking to a tenant.
	 * @param type : 
	 */
//	public void setClientName(String clientName)
//	{
//		if(null != clientName &&  !clientName.isEmpty())
//		{
//			if(clientName.indexOf('%') == -1)
//				clientName = '%' + clientName + '%';
//			super.addFilterSome("supplier", Filter.like("name", clientName));
//		}
//	}
//	public String getClientName()
//	{
//		return null;
//	}	
	
	
	public void setAssetNumber(String assetNumber)
	{
		if(null != assetNumber && !assetNumber.isEmpty())
		{
			if(assetNumber.indexOf('%') == -1)
				assetNumber = '%' + assetNumber + '%';
			
			super.addFilterLike("assetNumber", assetNumber);
		}
	}
	public String getAssetNumber()
	{
		return null;
	}

	/**
	 * Find all the specific user data for this id type that makes up part of the asset number.
	 * 
	 * @param assetNumberPart1Id : Id number relating to the specific part of the asset number.
	 */
	public void setAssetNumberPart1Id(Long assetNumberPart1Id)
	{
		if(null != assetNumberPart1Id)
		{
			super.addFilterEqual("assetNumberPart1Id", assetNumberPart1Id);
		}
	}
	public String getAssetNumberPart1Id()
	{
		return null;
	}
	/**
	 * Find all the specific user data for this id type that makes up part of the asset number.
	 * 
	 * @param assetNumberPart2Id : Id number relating to the specific part of the asset number.
	 */
	public void setAssetNumberPart2Id(Long assetNumberPart2Id)
	{
		if(null != assetNumberPart2Id)
		{
			super.addFilterEqual("assetNumberPart2Id", assetNumberPart2Id);
		}
	}
	public String getAssetNumberPart2Id()
	{
		return null;
	}
	/**
	 * Find all the specific user data for this id type that makes up part of the asset number.
	 * 
	 * @param assetNumberPart3Id : Id number relating to the specific part of the asset number.
	 */
	public void setAssetNumberPart3Id(Long assetNumberPart3Id)
	{
		if(null != assetNumberPart3Id)
		{
			super.addFilterEqual("assetNumberPart3Id", assetNumberPart3Id);
		}
	}
	public String getAssetNumberPart3Id()
	{
		return null;
	}
	/**
	 * Find all the specific user data for this id type that makes up part of the asset number.
	 * 
	 * @param assetNumberPart4Id : Id number relating to the specific part of the asset number.
	 */
	public void setAssetNumberPart4Id(Long assetNumberPart4Id)
	{
		if(null != assetNumberPart4Id)
		{
			super.addFilterEqual("assetNumberPart4Id", assetNumberPart4Id);
		}
	}
	public String getAssetNumberPart4Id()
	{
		return null;
	}

	
	public void setName(String name)
	{
		if(null != name && !name.isEmpty())
		{
			// TODO Proper implementation of like search functionality needed
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
	
	public void setCreatedBy(String username)
	{
		if(null != username && !username.isEmpty())
		{
			super.addFilterEqual("createdBy", username);
		}
	}	
	public String getCreatedBy()
	{
		return null;
	}

	public void setSerialNumber(String serialNumber)
	{
		if(null != serialNumber && !serialNumber.isEmpty())
		{
			super.addFilterEqual("serialNumber", serialNumber);
		}
	}
	public String getSerialNumber()
	{
		return null;
	}
	
	public void setAssetStatusId(Long statusUserDataId)
	{
		if(null != statusUserDataId)
		{
			super.addFilterEqual("assetStatusId", statusUserDataId);
		}
	}
	public Long getAssetStatusId()
	{
		return null;
	}
	
	public void setCategoryId(Long categoryUserDataId)
	{
		if(null != categoryUserDataId)
		{
			super.addFilterEqual("categoryId", categoryUserDataId);
		}
	}
	public Long getCategoryId()
	{
		return null;
	}
	
	public void setCountryId(Long countryId)
	{
		if(null != countryId)
		{
			super.addFilterEqual("countryId", countryId);
		}
	}
	public Long getCountryId()
	{
		return null;
	}
	
	public void setDepreciationCodeId(Long depreciationCodeId)
	{
		if(null != depreciationCodeId)
		{
			super.addFilterEqual("depreciationCodeId", depreciationCodeId);
		}
	}
	public Long getDepreciationCodeId()
	{
		return null;
	}
	
	public void setCompanyId(Long companyId)
	{
		if(null != companyId)
		{
			super.addFilterEqual("companyId", companyId);
		}
	}
	public Long getCompanyId()
	{
		return null;
	}
	
	public void setDepartmentId(Long departmentId)
	{
		if(null != departmentId)
		{
			super.addFilterEqual("departmentId", departmentId);
		}
	}
	public Long getDepartmentId()
	{
		return null;
	}
	
	public void setLocationId(Long locationId)
	{
		if(null != locationId)
		{
			super.addFilterEqual("locationId", locationId);
		}
	}
	public Long getLocationId()
	{
		return null;
	}
	
	public void setSiteId(Long siteId)
	{
		if(null != siteId)
		{
			super.addFilterEqual("siteId", siteId);
		}
	}
	public Long getSiteId()
	{
		return null;
	}
	
	public void setDivisionId(Long divisionId)
	{
		if(null != divisionId)
		{
			super.addFilterEqual("divisionId", divisionId);
		}
	}
	public Long getDivisionId()
	{
		return null;
	}

	public void setListId(Long listId)
	{
		if(null != listId)
		{
			String listContents = EntityListContent.class.getName();
			String list = EntityList.class.getName();
			// TODO This should come from the Entity Type enum.
			// But also needs to be set correctly when the list is created. So that should also
			// use the enum
			String listEntityType = "ASSETS";
			super.addFilterCustom("{id} in (select lc.entityId from " + listContents + " lc, " + list + " l where lc.entityList.id  = l.id and l.entityType = ?2 and lc.entityList.id = ?1)", listId, listEntityType);
		}
	}
	
	public void setGroupId(Long groupId)
	{
		if(null != groupId)
		{
			super.addFilterSome("groupAssets", Filter.equal("group.id", groupId));
		}
	}
	
	
	public void getGroupId(Long assetId)
	{
		if(null != assetId)
		{
			super.addFilterSome("groupAssets", Filter.equal("asset.groups", assetId));
		}
	}
	
	public void setCriticalAsset(Boolean criticalAsset)
	{
		if(null != criticalAsset)
		{
			super.addFilterEqual("criticalAsset", criticalAsset);
		}
	}
	public Long getCriticalAsset()
	{
		return null;
	}
	
}
