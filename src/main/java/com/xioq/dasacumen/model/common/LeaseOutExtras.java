package com.xioq.dasacumen.model.common;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.systemadmin.UserData;

@Entity
@Proxy(lazy=false)
@Table(name = "lease_out_extras")
public class LeaseOutExtras extends VersionControlled implements java.io.Serializable, Comparable<LeaseOutExtras>
{
	private static final long serialVersionUID = -6598806018800070863L;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="lease_out_extras_id_seq", name="lease_out_extras_id_seq")
	@GeneratedValue(generator="lease_out_extras_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
//	@JsonIgnore
//	@ManyToOne
//    @JoinColumn(name="assets_id")
//	private Assets asset;
	
	@ManyToOne
    @JoinColumn(name="lease_out_id")
	private LeaseOut leaseOut;
		
	@Column(name="extras")
	private Long extrasId;
	@ManyToOne
    @JoinColumn(name="extras", insertable=false, updatable=false)
	private UserData leaseOutExtras;
	
	@Column(name="extra_cost")
	private BigDecimal extraCost;
	
	@Transient
	private Boolean include;
	
	@Override
	public String toString()
	{
		return "LeaseOutExtras [id=" + id// + ", asset=" + asset + ", "
				+ "extras=" + leaseOutExtras + ", "
				+ "leaseOut=" + leaseOut + ", "
				+ "extraCost=" + extraCost + "]";
	}

	public LeaseOutExtras() {}

	public LeaseOutExtras(UserData leaseOutExtras, LeaseOut leaseOut)
	{
//		this.setAsset(asset);
		this.setLeaseOutExtras(leaseOutExtras);
		this.setLeaseOut(leaseOut);
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.LEASE_OUT_EXTRAS;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

//	public Assets getAsset()
//	{
//		return this.asset;
//	}
//	
//	public void setAsset(Assets asset)
//	{
//		this.asset = asset;
//	}
	
	public Boolean getInclude() {
		return include;
	}

	public void setInclude(Boolean include) {
		this.include = include;
	}


	public Long getExtrasId()
	{
		return extrasId;
	}

	public void setExtrasId(Long extrasId)
	{
		this.extrasId = extrasId;
	}

	public UserData getLeaseOutExtras()
	{
		return this.leaseOutExtras;
	}

	public void setLeaseOutExtras(UserData leaseOutExtras)
	{
		this.leaseOutExtras = leaseOutExtras;
	}

	public LeaseOut getLeaseOut()
	{
		return leaseOut;
	}

	public void setLeaseOut(LeaseOut leaseOut)
	{
		this.leaseOut = leaseOut;
	}

	public BigDecimal getExtraCost()
	{
		return extraCost;
	}
	public void setExtraCost(BigDecimal extraCost)
	{
		this.extraCost = extraCost;
	}

	@Override
	public int compareTo(LeaseOutExtras leaseOutExtra) {
		return 1;
	}

}
