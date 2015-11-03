
package com.xioq.dasacumen.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.systemadmin.UserData;

/**
 * Object allows assets to be assigned a number of insurance types that
 * tell the user what insurance an assets needs.
 * @author echhung
 *
 */
@Entity
@Proxy(lazy=false)
@Table(name = "asset_insurance_types")
public class AssetInsuranceType extends VersionControlled implements java.io.Serializable {
	
	private static final long serialVersionUID = 1578198796261407199L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="asset_insurance_types_id_seq", name="asset_insurance_types_id_seq")
	@GeneratedValue(generator="asset_insurance_types_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="insurance_type_id")
	private Long insuranceTypeId;
	
	@ManyToOne
	@JoinColumn(name="insurance_type_id", updatable = false, insertable = false)
	private UserData insuranceType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="assets_id")
	private Assets asset;
	
	private Boolean mandatory;
	
	/**
	 * All InsuranceTypePolicyLink connected to assetinsurancetype
	 */
	
	@OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, mappedBy="assetInsuranceType", fetch=FetchType.EAGER)
	public Set<InsuranceTypePolicyLink> insuranceTypePolicyLinks = new HashSet<InsuranceTypePolicyLink>(0);
	
	@Override
	public String toString()
	{
		return "AssetInsuranceType [id=" + id + ", userData=" + insuranceType + ", assets=" + asset + ", mandatory="
				+ mandatory + ", insuranceTypePolicyLink=" + insuranceTypePolicyLinks + "]";
	}
	
	
	/**
	 * Minimum fields to set to make an asset insurance type (id and tenantid set by system)
	 * @param insuranceTypeId - user data id.
	 * @param assetsId - the asset id .
	 * @param mandatory - is this insurance type mandatory for the asset.
	 */
	public AssetInsuranceType(UserData insuranceType, Assets asset,
			Boolean mandatory) {
		this.insuranceType = insuranceType;
		this.asset = asset;
		this.mandatory = mandatory;
	}
	
	public AssetInsuranceType(Long id, UserData insuranceType, Assets asset, Boolean mandatory, Set<InsuranceTypePolicyLink> insuranceTypePolicyLinks)
	{
		this.setId(id);
		this.setInsuranceType(insuranceType);
		this.setAsset(asset);
		this.setMandatory(mandatory);
		this.setInsuranceTypePolicyLinks(insuranceTypePolicyLinks);
	}

	public AssetInsuranceType() { }

	@Override
	public EntityType getModelType()
	{
		return EntityType.ASSETS_INSURANCE_TYPE;
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

	/**
	 * @return the insuranceTypeId
	 */
	public UserData getInsuranceType() {
		return insuranceType;
	}

	/**
	 * @param insuranceTypeId the insuranceTypeId to set
	 */
	public void setInsuranceType(UserData insuranceType) {
		this.insuranceType = insuranceType;
	}

	/**
	 * @return the assetsId
	 */
	public Assets getAsset() {
		return asset;
	}

	/**
	 * @param assetsId the assetsId to set
	 */
	public void setAsset(Assets asset) {
		this.asset = asset;
	}

	/**
	 * @return the mandatory
	 */
	public Boolean getMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * @return the insuranceTypePolicyLinks
	 */
	public Set<InsuranceTypePolicyLink> getInsuranceTypePolicyLinks() {
		return insuranceTypePolicyLinks;
	}

	/**
	 * @param insuranceTypePolicyLinks the insuranceTypePolicyLinks to set
	 */
	public void setInsuranceTypePolicyLinks(Set<InsuranceTypePolicyLink> insuranceTypePolicyLinks) {
		this.insuranceTypePolicyLinks = insuranceTypePolicyLinks;
	}
	/**
	 * Adds a new Insurance policy link, linking this insurance type and the specified policy
	 * @param insurancePolicy
	 */
	public void addInsuranceTypePolicyLink(InsurancePolicy insurancePolicy) {
		InsuranceTypePolicyLink policyLink = new InsuranceTypePolicyLink(insurancePolicy, this);
		insuranceTypePolicyLinks.add(policyLink);
	}
	
	
	public InsuranceTypePolicyLink getInsuranceTypePolicyLink()
	{
		if(null == this.insuranceTypePolicyLinks)
			return null;

		for (InsuranceTypePolicyLink aInsuranceTypePolicyLink : this.insuranceTypePolicyLinks)
		{
			if(true)
			{
				return aInsuranceTypePolicyLink;
			}
		}
		return null;
	}
	
	
	public void setInsuranceTypePolicyLink(InsuranceTypePolicyLink insuranceTypePolicyLink)
	{
		Set<InsuranceTypePolicyLink> tmp = getInsuranceTypePolicyLinks();
		if(null == tmp)
		{
			tmp = new HashSet<InsuranceTypePolicyLink>();
			setInsuranceTypePolicyLinks(tmp);
		}
		insuranceTypePolicyLink.setAssetInsuranceType(this);
		tmp.add(insuranceTypePolicyLink);
	}


	/**
	 * @return the insuranceTypeId
	 */
	public Long getInsuranceTypeId() {
		return insuranceTypeId;
	}


	/**
	 * @param insuranceTypeId the insuranceTypeId to set
	 */
	public void setInsuranceTypeId(Long insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}

}
