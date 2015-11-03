/**
 * 
 */
package com.xioq.dasacumen.lib.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Parent model class for all that have a version number to guard against dual updates.
 * See Optimistic Locking.
 * 
 * @author Stephen Elliott
 */
@MappedSuperclass
//http://stackoverflow.com/questions/4265454/hibernate-jpa-inheritance-mapping-of-abstract-super-classes
public abstract class VersionControlled extends TenantModel
{
	@Version
	@Column(name="version_number")
	//http://docs.jboss.org/hibernate/orm/4.0/devguide/en-US/html/ch05.html
	private Integer versionNumber;
	

	public Integer getVersionNumber()
	{
		return versionNumber;
	}

	public void setVersionNumber(Integer versionNumber)
	{
		this.versionNumber = versionNumber;
	}
}
