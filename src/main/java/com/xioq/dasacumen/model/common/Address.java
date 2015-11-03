package com.xioq.dasacumen.model.common;

// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * Addresses generated by hbm2java
 */
@Entity 
@Proxy(lazy=false)
@Table(name = "Addresses") 
public class Address extends VersionControlled implements java.io.Serializable {

	private static final long serialVersionUID = -1717106924512535154L;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="addresses_id_seq", name="addresses_id_seq")
	@GeneratedValue(generator="addresses_id_seq", strategy=GenerationType.SEQUENCE)
	// Used this website - look at the bottom anser.
	// http://stackoverflow.com/questions/4979746/mapping-postgresql-serial-type-with-hibernate-annotations
	private Long id;
	
	@Column(name="address_line_1")
	private String addressLine1;
	@Column(name="address_line_2")
	private String addressLine2;
	@Column(name="address_line_3")
	private String addressLine3;
	private String zipPostCode;
	private String country;
	private String typeOfPlace;
	private String city;
	
	//http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/collections.html
	@OneToMany(mappedBy="address")
	private Set<PartyAddress> partyAddress = new HashSet<PartyAddress>(0);
	
	public Address() {
	}

	public Address(Long id) {
		this.id = id;
	}

	public Address(Long id, String addressLine1, String addressLine2,
			String addressLine3, String zipPostCode, String country,
			String typeOfPlace, Set<PartyAddress> partyAddresses) {
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.zipPostCode = zipPostCode;
		this.country = country;
		this.typeOfPlace = typeOfPlace;
		//this.partyAddress = partyAddresses;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.ADDRESS;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return this.addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getZipPostCode() {
		return this.zipPostCode;
	}

	public void setZipPostCode(String zipPostCode) {
		this.zipPostCode = zipPostCode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTypeOfPlace() {
		return this.typeOfPlace;
	}

	public void setTypeOfPlace(String typeOfPlace) {
		this.typeOfPlace = typeOfPlace;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<PartyAddress> getPartyAddress() {
		return this.partyAddress;
	}

	public void setPartyAddress(Set<PartyAddress> partyAddress) {
		this.partyAddress = partyAddress;
	}
}
