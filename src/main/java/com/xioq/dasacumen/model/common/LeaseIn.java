package com.xioq.dasacumen.model.common;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.systemadmin.UserData;

@Entity
@Proxy(lazy=false)
@Table(name = "lease_in")
public class LeaseIn extends VersionControlled implements java.io.Serializable
{
	private static final long serialVersionUID = -6598806018800070863L;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="lease_in_id_seq", name="lease_in_id_seq")
	@GeneratedValue(generator="lease_in_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="party_id")
	private Long partyId;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="party_id",insertable=false, updatable=false, nullable=false)
	private Party party;
	
	@Transient
	private String partyName;
	
	@Column(name="lease_type")
	private Long leaseTypeId;	
	@ManyToOne
	@JoinColumn(name="lease_type", insertable=false, updatable=false)
	private UserData leaseType;
	
	@Column(name="vat_code")
	private Long vatCodeId;
	@ManyToOne
    @JoinColumn(name="vat_code", insertable=false, updatable=false)
	private UserData vatCode;
	
	@Column(name="charge_period")
	private Long chargePeriodId;
	@ManyToOne
    @JoinColumn(name="charge_period", insertable=false, updatable=false)
	private UserData chargePeriod;
	
	@JsonIgnore
	@OneToOne(optional=false)
    @JoinColumn(name="assets_id", nullable=false)
	private Assets asset;
	
	private BigDecimal leaseCharge;
	private Date leaseCommences;
	private Date leaseExpires;
	private String leasePeriod;
	private BigDecimal leaseCost;
	private BigDecimal leaseValue;
	private BigDecimal leaseOutMargin;
	private Boolean maintenanceIncluded;
	private Boolean warrantyIncluded;
	private String  locationPostcode;
	
	@Override
	public String toString()
	{
		return "LeaseIn [id=" + id + ", party=" + party + ", /*partyId=" + partyId + ", partyName=" + partyName + ", leaseType=" + leaseType + ", vatCode=" + vatCode 
				+ ", chargePeriod=" + chargePeriod + ", asset=" + asset
				+ ", leaseCharge=" + leaseCharge
				+ ", leaseCommences=" + leaseCommences + ", leaseExpires=" + leaseExpires + ", leasePeriod="
				+ leasePeriod + ", leaseCost=" + leaseCost + ", leaseValue=" + leaseValue + ", leaseOutMargin="
				+ leaseOutMargin + ", maintenanceIncluded=" + maintenanceIncluded + ", warrantyIncluded="
				+ warrantyIncluded + "]";
	}

	public LeaseIn() {}

	public LeaseIn(Party party, UserData leaseType, UserData vatCode, 
			UserData chargePeriod, Assets asset)
	{
		this.setParty(party);
		this.setLeaseType(leaseType);
		this.setVatCode(vatCode);
		this.setChargePeriod(chargePeriod);
		this.setAsset(asset);
	}

	/**
	 * Minimum fields to make lease in
	 */
	public LeaseIn(Assets asset, Long partyId, Long leaseTypeId, 
			Long vatCodeId, Long chargePeriodId) {
		super();
		this.asset = asset;
		this.partyId = partyId;
		this.leaseTypeId = leaseTypeId;
		this.vatCodeId = vatCodeId;
		this.chargePeriodId = chargePeriodId;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.LEASE_IN;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Party getParty()
	{
		return party;
	}
	public void setParty(Party party)
	{
		this.party = party;
	}
	public Long getPartyId()
	{
		if(null != getParty())
			return getParty().getId();
		return partyId;
	}
	public void setPartyId(Long partyId)
	{
		this.partyId = partyId;
	}
	public String getPartyName()
	{
		if(null != getParty())
			return getParty().getName();
		return partyName;
	}
	public void setPartyName(String partyName)
	{
		this.partyName = partyName;
	}
	
	/**
	 * @return the leaseTypeId
	 */
	public Long getLeaseTypeId() {
		return leaseTypeId;
	}

	/**
	 * @param leaseTypeId the leaseTypeId to set
	 */
	public void setLeaseTypeId(Long leaseTypeId) {
		this.leaseTypeId = leaseTypeId;
	}

	/**
	 * @return the vatCodeId
	 */
	public Long getVatCodeId() {
		return vatCodeId;
	}

	/**
	 * @param vatCodeId the vatCodeId to set
	 */
	public void setVatCodeId(Long vatCodeId) {
		this.vatCodeId = vatCodeId;
	}

	/**
	 * @return the chargePeriodId
	 */
	public Long getChargePeriodId() {
		return chargePeriodId;
	}

	/**
	 * @param chargePeriodId the chargePeriodId to set
	 */
	public void setChargePeriodId(Long chargePeriodId) {
		this.chargePeriodId = chargePeriodId;
	}

	public UserData getLeaseType()
	{
		return leaseType;
	}

	public void setLeaseType(UserData leaseType)
	{
		this.leaseType = leaseType;
	}

	public UserData getVatCode()
	{
		return vatCode;
	}

	public void setVatCode(UserData vatCode)
	{
		this.vatCode = vatCode;
	}
	
	public UserData getChargePeriod()
	{
		return chargePeriod;
	}
	public void setChargePeriod(UserData chargePeriod)
	{
		this.chargePeriod = chargePeriod;
	}
	public Assets getAsset()
	{
		return this.asset;
	}
	
	public void setAsset(Assets asset)
	{
		this.asset = asset;
	}

	public BigDecimal getLeaseCharge()
	{
		return leaseCharge;
	}

	public void setLeaseCharge(BigDecimal leaseCharge)
	{
		this.leaseCharge = leaseCharge;
	}

	public Date getLeaseCommences()
	{
		return leaseCommences;
	}

	public void setLeaseCommences(Date leaseCommences)
	{
		this.leaseCommences = leaseCommences;
	}

	public Date getLeaseExpires()
	{
		return leaseExpires;
	}

	public void setLeaseExpires(Date leaseExpires)
	{
		this.leaseExpires = leaseExpires;
	}
	
	public String getLeasePeriod()
	{
		return leasePeriod;
	}
	public void setLeasePeriod(String leasePeriod)
	{
		this.leasePeriod = leasePeriod;
	}
	public BigDecimal getLeaseCost()
	{
		return leaseCost;
	}
	public void setLeaseCost(BigDecimal leaseCost)
	{
		this.leaseCost = leaseCost;
	}
	public BigDecimal getLeaseValue()
	{
		return leaseValue;
	}
	public void setLeaseValue(BigDecimal leaseValue)
	{
		this.leaseValue = leaseValue;
	}
	
	public BigDecimal getLeaseOutMargin()
	{
		return leaseOutMargin;
	}

	public void setLeaseOutMargin(BigDecimal leaseOutMargin)
	{
		this.leaseOutMargin = leaseOutMargin;
	}
	
	public Boolean getMaintenanceIncluded()
	{
		return maintenanceIncluded;
	}
	public void setMaintenanceIncluded(Boolean maintenanceIncluded)
	{
		this.maintenanceIncluded = maintenanceIncluded;
	}
	public Boolean getWarrantyIncluded()
	{
		return warrantyIncluded;
	}
	public void setWarrantyIncluded(Boolean warrantyIncluded)
	{
		this.warrantyIncluded = warrantyIncluded;
	}

	public String getLocationPostcode()
	{
		return locationPostcode;
	}

	public void setLocationPostcode(String locationPostcode)
	{
		this.locationPostcode = locationPostcode;
	}

}
