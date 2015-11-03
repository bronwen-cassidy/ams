package com.xioq.dasacumen.model.assetregister;

import com.xioq.dasacumen.lib.search.Search;

/**
 * Search object for AssetProposal. 
 * @author MWalsh
 */
public class AssetProposalSearch extends Search<AssetProposal>
{
	public AssetProposalSearch()
	{
		super(AssetProposal.class);
	}

	public void setCurrentStatus(String currentStatus)
	{
		if(null != currentStatus && !currentStatus.isEmpty())
		{
			super.addFilterEqual("currentStatus", currentStatus);
		}
	}
	public String getCurrentStatus()
	{
		return null;
	}
	
}
