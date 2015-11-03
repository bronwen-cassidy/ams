package com.xioq.dasacumen.lib.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Convenient model super class to hold all the audit fields.
 * @author Stephen Elliott
 */
@MappedSuperclass
//http://stackoverflow.com/questions/4265454/hibernate-jpa-inheritance-mapping-of-abstract-super-classes
public abstract class ModelBase implements ModelEntity
{
	@Column(name = "created_by", updatable = false)
	private String createdBy;
	
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	
	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;
	
	public String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
	public String getLastUpdatedBy()
	{
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy)
	{
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDate()
	{
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate)
	{
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
