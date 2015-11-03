package com.xioq.dasacumen.model;

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

import com.xioq.dasacumen.lib.model.TenantModel;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * an asset gets put into one or more groups depending on what asset it is.
 * 
 * @author jmadden
 * 
 */
@Entity
@Table(name = "groups_assets")
public class GroupAsset extends TenantModel implements java.io.Serializable
{

	private static final Long serialVersionUID = 2047048695942181471L;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="groups_assets_id_seq", name="groups_assets_id_seq")
	@GeneratedValue(generator="groups_assets_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="assets_id")
	private Long assetId;
	@ManyToOne()
	@JoinColumn(name="assets_id", nullable=false, insertable=false, updatable=false)
	private Assets asset;
	
	@ManyToOne
	@JoinColumn(name="groups_id", nullable=false)
	private Group group;

	public GroupAsset()
	{}

	public GroupAsset(Long id, Long asset, Group group)
	{
		this.id = id;
		this.assetId = asset;
		this.group = group;
	}

	@Override
	@JsonIgnore
	public EntityType getModelType()
	{
		return EntityType.GROUP_ASSET;
	}
	
	/**
	 * 
	 * @return id of the grouped assets
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * 
	 * @param id of the grouped assets
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * 
	 * @return asset in a specific group
	 */
	public Long getAssetId()
	{
		return assetId;
	}

	/**
	 * 
	 * @param Long1 in an appropriate group
	 */
	public void setAssetId(Long asset)
	{
		this.assetId = asset;
	}
	
	/**
	 * 
	 * @return asset in a specific group
	 */
	public Assets getAsset()
	{
		return asset;
	}

	/**
	 * 
	 * @param Asset in an appropriate group
	 */
	public void setAsset(Assets asset)
	{
		this.asset = asset;
	}

	/**
	 * 
	 * @return group
	 */
	public Group getGroup()
	{
		return group;
	}

	/**
	 * 
	 * @param group
	 */
	public void setGroup(Group group)
	{
		this.group = group;
	}
}
