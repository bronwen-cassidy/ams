package com.xioq.dasacumen.model.assetregister;

import java.util.Date;

import javax.persistence.Transient;

import com.xioq.dasacumen.lib.annotations.Draggable;
import com.xioq.dasacumen.lib.annotations.Droppable;
import com.xioq.dasacumen.lib.annotations.DroppableModel;
import com.xioq.dasacumen.model.common.Party;

/**
 * Assets by best available for lease out usability.
 * 
 * @author MWalsh
 *
 */
@DroppableModel("assetAvailabilitySearch")
@Draggable(names={"clientDraggable"})
public class AssetAvailabilitySearch extends AssetSearch
{

	
	@Transient
	@Droppable(accepts="clientDraggable", fieldToCopy="name")
	private String clientName;
	
	@Transient
	@Droppable(accepts="clientDraggable", fieldToCopy="id")
	private Long clientId;
	
	public AssetAvailabilitySearch()
	{
//		super(Assets.class);
	}

	/**
	 * Finds all assets that are available with in a specific date range
	 * 
	 * @param name : availableDates.
	 */
	public void setAvailableDates(String assetName, Date from, Date to)
	{
		if(null != assetName)
		{
			super.addFilterLike("name", assetName); 
		}
		if(null != from && null != to)
		{
			String assetSchedule = AssetSchedule.class.getName();
			super.addFilterCustom("{id} Not In (select ash.asset.id from "+ assetSchedule +" ash WHERE ash.asset.id = {id} and (?1 <= ash.leaseExpires) and (?2 >= ash.leaseCommences))",from, to);
		}
	}
	/**
	 * For unavailable assets present a schedule
	 * @param assetName
	 * @return returns the asset schedule to get any bookings and the asset id. 
	 */
	public void setAvailableDatesSp(Long assetId)
	{
		if(null != assetId)
		{
			super.addFilterEqual("id", assetId); 
		}
	}
	
	/**
	 * Find all assets that are not available between two specific dates if they are supplied 
	 * 
	 * @param name : availableDates.
	 */
	public void setNotAvailableDates(String assetName, Date from, Date to)
	{
		if(null != assetName)
		{
			super.addFilterLike("name", assetName); 
		}
		if(null != from && null != to)
		{
			String assetSchedule = AssetSchedule.class.getName();
			super.addFilterCustom("{id} In (select ash.asset.id from "+ assetSchedule +" ash WHERE ash.asset.id = {id} and ('" + from.toLocaleString() + "' <= ash.leaseExpires) and ('" + to.toLocaleString() + "' >= ash.leaseCommences))");
		}
	}
	
	public String getAvailableName()
	{
		return null;
	}
	

	public Party getClient()
	{
		return null;
	}
	@Droppable(accepts="clientDraggable", fieldToCopy="name") 
	public String getClientName()
	{
		if(null != getClient())
			return getClient().getName();
		return clientName;
	}
	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}
	
	@Droppable(accepts="clientDraggable", fieldToCopy="id")
	public Long getClientId()
	{
		if(null != getClient())
			return getClient().getId();
		return clientId;
	}
	public void setClientId(Long clientId)
	{
		this.clientId = clientId;
	}
	
}
