package com.xioq.dasacumen.model.common;

// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.systemadmin.UserData;

/**
 * OtherSystemRef generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "other_system_ref")
public class OtherSystemRef extends VersionControlled implements java.io.Serializable {
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="other_system_ref_id_seq", name="other_system_ref_id_seq")
	@GeneratedValue(generator="other_system_ref_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="other_system_name_id", insertable=false, updatable=false, nullable=false)
	private UserData otherSystemName;
	@Column(name="other_system_name_id")
	private Long otherSystemNameId;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="assets_id", nullable=false)
	private Assets assets;
	
	@Column(name="other_system_id", nullable=false)
	private String otherSystemId;
	

	public OtherSystemRef() {
	}

	public OtherSystemRef(Long id, UserData otherSystemName, Assets assets,
			Long otherSystemNameId, String otherSystemId) {
		this.id = id;
		this.otherSystemName = otherSystemName;
		this.otherSystemNameId = otherSystemNameId;
		this.assets = assets;
		this.otherSystemId = otherSystemId;
	}

	public OtherSystemRef(Long id, UserData otherSystemName,Long OtherSystemNameId, Assets assets,
			String otherSystemId, Integer tenantId, String createdBy,
			Date createdDate, String lastUpdatedBy, Date lastUpdatedDate,
			Integer versionNumber) {
		this.id = id;
		this.otherSystemName = otherSystemName;
		this.otherSystemNameId = otherSystemNameId;
		this.assets = assets;
		this.otherSystemId = otherSystemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((otherSystemId == null) ? 0 : otherSystemId.hashCode());
		result = prime
				* result
				+ ((otherSystemNameId == null) ? 0 : otherSystemNameId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OtherSystemRef other = (OtherSystemRef) obj;
		if (otherSystemId == null) {
			if (other.otherSystemId != null)
				return false;
		} else if (!otherSystemId.equals(other.otherSystemId))
			return false;
		if (otherSystemNameId == null) {
			if (other.otherSystemNameId != null)
				return false;
		} else if (!otherSystemNameId.equals(other.otherSystemNameId))
			return false;
		return true;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserData getOtherSystemName() {
		return this.otherSystemName;
	}

	public void setOtherSystemName(UserData otherSystemName) {
		this.otherSystemName = otherSystemName;
	}	

	public Assets getAssets() {
		return this.assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public String getOtherSystemId() {
		return this.otherSystemId;
	}

	public void setOtherSystemId(String otherSystemId) {
		this.otherSystemId = otherSystemId;
	}

	@Override
	public EntityType getModelType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long getOtherSystemNameId()
	{
		return otherSystemNameId;
	}
	public void setOtherSystemNameId(Long otherSystemNameId)
	{
		this.otherSystemNameId = otherSystemNameId;
	}
}