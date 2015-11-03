package com.xioq.dasacumen.lib.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Model super class for all model classes that are specific to a tenant, hence require the tenant ID
 * @author Stephen Elliott
 */
@MappedSuperclass
//http://stackoverflow.com/questions/4265454/hibernate-jpa-inheritance-mapping-of-abstract-super-classes
public abstract class TenantModel extends ModelBase
{
	@Column(name = "tenant_id", updatable = false)
	private Integer tenantId;
	
	public Integer getTenantId()
	{
		return tenantId;
	}
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}
}
