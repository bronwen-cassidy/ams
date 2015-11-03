package com.xioq.dasacumen.model.common;

// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0

import javax.persistence.CascadeType;
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
 * PartyAddresses generated by hbm2java
 */
@Entity @Proxy(lazy=false)
@Table(name="party_addresses")
public class PartyAddress extends VersionControlled implements java.io.Serializable, Comparable<PartyAddress> {

	@Id
	@SequenceGenerator(allocationSize=1, sequenceName="party_addresses_id_seq", name="party_addresses_id_seq")
	@GeneratedValue(generator="party_addresses_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="addresses_id", nullable=false)
	private Address address;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parties_id", nullable=false)
	private Party party;
	
	private String addressType;

	public PartyAddress() {
	}

	public PartyAddress(Long id, Address address, Party party) {
		this.id = id;
		this.address = address;
		this.party = party;
	}

	public PartyAddress(Long id, Address address, Party parties,
			String addressType) {
		this.id = id;
		this.address = address;
		this.party = parties;
		this.addressType = addressType;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.PARTY_ADDRESS;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public int compareTo(PartyAddress partyAddress) {
		// TODO Auto-generated method stub
		return 1;

	}
}
