package com.xioq.dasacumen.model.common;

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
 * Linking object that connects warranty policy and warranty type.
 * @author nmarlor
 *
 */
@Entity
@Proxy(lazy=false)
@Table(name = "asset_warranties")
public class AssetWarranty extends VersionControlled implements java.io.Serializable {
	
	private static final long serialVersionUID = -952477049644777700L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="asset_warranties_id_seq", name="asset_warranties_id_seq")
	@GeneratedValue(generator="asset_warranties_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="warranty_policy_id")
	private Long warrantyPolicyId;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="warranty_policy_id",  updatable=false, insertable=false)
	private WarrantyPolicy warrantyPolicy;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="assets_id", nullable=false)
	private Assets asset;
	
	@Column(name = "asset_warranties_type_id")
	private Long assetWarrantyTypeId;
	@ManyToOne
    @JoinColumn(name="asset_warranties_type_id", nullable=false, updatable=false, insertable=false)
	private UserData assetWarrantyType;
	
	/**
	 * @param warrantyPolicy to connect with type
	 */
	public AssetWarranty(WarrantyPolicy warrantyPolicy,
			UserData assetWarrantyType, Assets asset, Boolean om) {
		this.warrantyPolicy = warrantyPolicy;
		this.asset = asset;
		this.assetWarrantyType = assetWarrantyType;
	}

	public AssetWarranty() {}

	@Override
	public EntityType getModelType()
	{
		return EntityType.WARRANTY_TYPE_POLICY_LINK;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getWarrantyPolicyId()
	{
		return warrantyPolicyId;
	}
	public void setWarrantyPolicyId(Long warrantyPolicyId)
	{
		this.warrantyPolicyId = warrantyPolicyId;
	}
	/**
	 * @return the warrantyPolicy
	 */
	public WarrantyPolicy getWarrantyPolicy() {
		return warrantyPolicy;
	}
	/**
	 * @param warrantyPolicy the warrantyPolicy to set
	 */
	public void setWarrantyPolicy(WarrantyPolicy warrantyPolicy) 
	{
		this.warrantyPolicy = warrantyPolicy;
	}
	
	/**
	 * @return the asset
	 */
	public Assets getAsset() {
		return asset;
	}

	/**
	 * @param assetId the assetId to set
	 */
	public void setAsset(Assets asset) {
		this.asset = asset;
	}
	
	/**
	 * @return the assetWarrantyTypeId (userdata id)
	 */
	public Long getAssetWarrantyTypeId() {
		return assetWarrantyTypeId;
	}

	/**
	 * @param assetWarrantyTypeId the assetWarrantiesTypeId to set (userdata id)
	 */
	public void setAssetWarrantyTypeId(Long assetWarrantyTypeId) {
		this.assetWarrantyTypeId = assetWarrantyTypeId;
	}
	
	/**
	 * @return the type
	 */
	public UserData getAssetWarrantyType() {
		return assetWarrantyType;
	}

	/**
	 * @param type the type to set
	 */
	public void setAssetWarrantyType(UserData assetWarrantyType) {
		this.assetWarrantyType = assetWarrantyType;
	}
}
