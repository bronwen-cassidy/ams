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
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * Linking object that connects insurance policy and insurance type.
 * @author echhung
 *
 */
@Entity
@Proxy(lazy=false)
@Table(name = "insurance_type_policy_links")
public class InsuranceTypePolicyLink extends VersionControlled implements java.io.Serializable {
	
	private static final long serialVersionUID = -952477049644777700L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="insurance_type_policy_links_id_seq", name="insurance_type_policy_links_id_seq")
	@GeneratedValue(generator="insurance_type_policy_links_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="insurance_policy_id ")
	private InsurancePolicy insurancePolicy;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="asset_insurance_type_id")
	private AssetInsuranceType assetInsuranceType;
	
	/**
	 * @param insurancePolicy to connect with type
	 * @param insuranceType to connect with policy
	 */
	public InsuranceTypePolicyLink(InsurancePolicy insurancePolicy,
			AssetInsuranceType assetInsuranceType) {
		this.insurancePolicy = insurancePolicy;
		this.assetInsuranceType = assetInsuranceType;
	}

	public InsuranceTypePolicyLink() {}

	@Override
	public EntityType getModelType()
	{
		return EntityType.INSURANCE_TYPE_POLICY_LINK;
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
	 * @return the insurancePolicy
	 */
	public InsurancePolicy getInsurancePolicy() {
		return insurancePolicy;
	}

	/**
	 * @param insurancePolicy the insurancePolicy to set
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	/**
	 * @return the insuranceType
	 */
	public AssetInsuranceType getAssetInsuranceType() {
		return assetInsuranceType;
	}

	/**
	 * @param assetinsuranceType the insuranceType to set
	 */
	public void setAssetInsuranceType(AssetInsuranceType assetInsuranceType) {
		this.assetInsuranceType = assetInsuranceType;
	}
}
