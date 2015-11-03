package com.xioq.dasacumen.model.common;

// Generated 24-Jun-2014 12:24:54 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.annotations.Draggable;
import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.ScratchpadDraggable;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.systemadmin.UserData;

/**
 * Abstract class party which is to be extended for companies, people and ir.
 * @author echhung
 */
@Entity @Proxy(lazy=false)
@Table(name="parties")
@Inheritance(strategy = InheritanceType.JOINED)
@Draggable(names={"clientDraggable", "supplierDraggable", "custodianDraggable"})
public abstract class Party extends VersionControlled implements ScratchpadDraggable,java.io.Serializable
{
	/** By default everything is lazy loaded. This is the full list of party dependencies to load eagerly if
	 * everything is required. Note this should ONLY be used when editing an asset.
	 */
	public static final String[] ALL_EAGER = {"partyAddresses", "partyBankDetails", "partyTelephoneNumbers", 
			"partyWebsites", "partyEmails", "otherDetails", "linkingParties"};

	private static final String CLIENT_DRAGGABLE = "clientDraggable";
	private static final String SUPPLIER_DRAGGABLE = "supplierDraggable";
	private static final String CUSTODIAN_DRAGGABLE = "custodianDraggable";
	
	//supplier/client references
	private String referenceForUs;
	private String referenceForThem;

	@Column(name="currency_accepted_id")
	private Long currencyAcceptedId;
	@ManyToOne()
    @JoinColumn(name="currency_accepted_id",insertable=false,updatable=false, nullable=true)
	private UserData currencyAccepted;
	
	@Override
	public Set<String> getDraggableNames()
	{
		Set<String> dragNames = new HashSet<String>();
		for (PartyType type : getPartyTypes())
		{
			switch(com.xioq.dasacumen.model.constants.PartyType.valueOf(type.getTypeName()))
			{
			case SUPPLIER:
				dragNames.add(SUPPLIER_DRAGGABLE);
				break;
			case CLIENT:
				dragNames.add(CLIENT_DRAGGABLE);
				break;
			case CUSTODIAN:
				dragNames.add(CUSTODIAN_DRAGGABLE);
				break;
			}
		}
		
		return dragNames;
	}
	
	@Id
	@SequenceGenerator(allocationSize=1, sequenceName="parties_id_seq", name="parties_id_seq")
	@GeneratedValue(generator="parties_id_seq", strategy=GenerationType.SEQUENCE)
	@Draggable()
	private Long id;
	
	@Column(name="uid", nullable=true)
	private String uid;
	
	// XXX fake field to allow getting at the party name for draggables. 
	// Should change the DnD config to allow methods
	@Draggable()
	@Transient //Required transient - fake field.
	private String name;
	
	
	/**
	 * Party can have multiple types.
	 */
	@OneToMany(mappedBy = "party", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<PartyType> partyTypes = new HashSet<PartyType>(0);
	
	/**
	 * Holds main contact details for party: 1 email, 1 telephone, 1 address
	 */
	@OneToOne(cascade=CascadeType.ALL, mappedBy="party")
	private PartyContactDetail partyContactDetails;
	
	/**
	 * Holds all addresses for party
	 */
	@OneToMany(mappedBy = "party", cascade=CascadeType.ALL)
	@OrderBy
	private SortedSet<PartyAddress> partyAddresses = new TreeSet<PartyAddress>();
	
	/**
	 * Holds all bank account details
	 */
	@OneToMany(mappedBy = "party", cascade=CascadeType.ALL)
	@OrderBy
	private SortedSet<PartyBankDetail> partyBankDetails = new TreeSet<PartyBankDetail>();
	
	/** 
	 * Holds all telephone numbers for party
	 */
	@OneToMany(mappedBy = "party", cascade=CascadeType.ALL)
	@OrderBy
	private SortedSet<PartyTelephoneNumber> partyTelephoneNumbers = new TreeSet<PartyTelephoneNumber>();
	
	/** 
	 * Holds all websites for party
	 */
	@OneToMany(mappedBy = "party", cascade=CascadeType.ALL)
	@OrderBy
	private SortedSet<PartyWebsite> partyWebsites = new TreeSet<PartyWebsite>();
	
	/** 
	 * Holds all email numbers for party
	 */
	@OneToMany(mappedBy = "party", cascade=CascadeType.ALL)
	@OrderBy
	private SortedSet<PartyEmail> partyEmails = new TreeSet<PartyEmail>();
	
	/**
	 * Holds all extra details
	 */
	@OneToMany(mappedBy = "party")
	private Set<OtherDetail> otherDetails = new HashSet<OtherDetail>(0);
	
	/**
	 *  Holds all parties which are linked to this party.
	 */
	@OneToMany(mappedBy = "partyId1", cascade=CascadeType.ALL)
	private Set<PartyLink> linkingParties = new HashSet<PartyLink>(0);
	
	/** Mapped but never loaded as only used for searching */
	@OneToMany(mappedBy = "supplier")
	@Transient
	private Set<Assets> supplierAssets = new HashSet<Assets>(0);
	
	@OneToMany(mappedBy = "party")
	@Transient
	private Set<LeaseIn> assetLeaseInClients = new HashSet<LeaseIn>(0);
	
	@OneToMany(mappedBy = "party")
	@Transient
	private Set<LeaseOut> assetLeaseOutClients = new HashSet<LeaseOut>(0);
	
	@OneToMany(mappedBy = "custodian")
	@Transient
	private Set<Assets> custodianAssets = new HashSet<Assets>(0);

	public Party() {
	}

	public Party(Long id) {
		this.id = id;
	}

	public Party(Long id,
			String uid,
			SortedSet<PartyAddress> partyAddresses, Set<PartyType> partyTypes,
			SortedSet<PartyBankDetail> partyBankDetails,
			PartyContactDetail partyContactDetails,
			SortedSet<PartyTelephoneNumber> partyTelephoneNumbers,
			SortedSet<PartyWebsite> partyWebsites,
			Set<OtherDetail> otherDetails,
			Set<PartyLink> linkingParties,
			SortedSet<PartyEmail> partyEmails) {
		
		this.id = id;
		this.setUid(uid);
		this.partyTypes = partyTypes;

		this.partyContactDetails = partyContactDetails;
		
		this.partyAddresses = partyAddresses;
		this.partyBankDetails = partyBankDetails;
		this.partyEmails = partyEmails;
		this.partyTelephoneNumbers = partyTelephoneNumbers;
		this.partyWebsites = partyWebsites;
		
		this.otherDetails = otherDetails;

		this.linkingParties = linkingParties;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	@JsonIgnore
	public String getDisplayName()
	{
		return getName();
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/** Name of the party. For example, Either forename surname or the company name */
	@Draggable
	public abstract String getName();

	public Set<PartyType> getPartyTypes() {
		return this.partyTypes;
	}

	public void setPartyTypes(Set<PartyType> partyTypes) {
		this.partyTypes = partyTypes;
	}

	public SortedSet<PartyAddress> getPartyAddresses() 
	{
		return this.partyAddresses;
	}

	public void setPartyAddresses(SortedSet<PartyAddress> partyAddresses) {
		this.partyAddresses = partyAddresses;
	}
	
	public void addPartyAddress(PartyAddress partyAddress){
		this.partyAddresses.add(partyAddress);
	}

	public SortedSet<PartyBankDetail> getPartyBankDetails() 
	{
		return this.partyBankDetails;
	}

	public void setPartyBankDetails(SortedSet<PartyBankDetail> partyBankDetails) {
		this.partyBankDetails = partyBankDetails;
	}
	
	public void addPartyBankDetail(PartyBankDetail partyBankDetail)
	{
		this.partyBankDetails.add(partyBankDetail);
	}

	public PartyContactDetail getPartyContactDetails() 
	{
//		if (partyContactDetails.isEmpty())
//		{
//			//partyContactDetail set needs a party, a partyaddress, a partytelephonenumber, and a party email 
//			//to not be null so we make them not null here 
//			PartyContactDetail partyContactDetail = new PartyContactDetail();
//			
//			//We make a partyaddress and set an empty address because it needs an address
//			PartyAddress partyAddress = new PartyAddress();
//			Address address = new Address();
//			partyAddress.setParty(this);
//			partyAddress.setAddress(address);
//			
//			//We make a partytelephonenumber and set an empty telephonenumber because it needs a telephone number
//			PartyTelephoneNumber partyTelephoneNumber = new PartyTelephoneNumber();
//			TelephoneNumber telephoneNumber = new TelephoneNumber();
//			partyTelephoneNumber.setParty(this);
//			partyTelephoneNumber.setTelephoneNumber(telephoneNumber);
//			
//			//We make a partyemail and set an empty email because it needs an email
//			PartyEmail partyEmail = new PartyEmail();
//			Email email = new Email();
//			partyEmail.setParty(this);
//			partyEmail.setEmail(email);
//			
//			//We then set the party, party address, party telephone number, and party email and add the party contact detail to the set
//			partyContactDetail.setParty(this);
//			partyContactDetail.setpartyAddress(partyAddress);
//			partyContactDetail.setPartyTelephoneNumber(partyTelephoneNumber);
//			partyContactDetail.setPartyEmail(partyEmail);	
//			partyContactDetails.add(partyContactDetail);
//			
//			return partyContactDetails;
//		}
			
		return this.partyContactDetails;
	}

	public void setPartyContactDetails(PartyContactDetail partyContactDetails) {
		this.partyContactDetails = partyContactDetails;
	}

	public SortedSet<PartyTelephoneNumber> getPartyTelephoneNumbers() 
	{
		return this.partyTelephoneNumbers;
	}

	public void setPartyTelephoneNumbers(SortedSet<PartyTelephoneNumber> partyTelephoneNumbers) {
		this.partyTelephoneNumbers = partyTelephoneNumbers;
	}
	
	public void addPartyTelephoneNumber(PartyTelephoneNumber telNo){
		this.partyTelephoneNumbers.add(telNo);
	}

	public Set<OtherDetail> getOtherDetails() {
		return this.otherDetails;
	}

	public void setOtherDetails(Set<OtherDetail> otherDetails) {
		this.otherDetails = otherDetails;
	}

	public Set<PartyLink> getLinkingParties() {
		return this.linkingParties;
	}

	public void setLinkingParties(
			Set<PartyLink> linkingParties) {
		this.linkingParties = linkingParties;
	}

	public SortedSet<PartyEmail> getPartyEmails() 
	{
		return this.partyEmails;
	}

	public void setPartyEmails(SortedSet<PartyEmail> partyEmails) {
		this.partyEmails = partyEmails;
	}
	
	public void addPartyEmail(PartyEmail email){
		this.partyEmails.add(email);
	}
	
	public SortedSet<PartyWebsite> getPartyWebsites() 
	{	
		return this.partyWebsites;
	}

	public void setPartyWebsites(SortedSet<PartyWebsite> partyWebsites) 
	{
		this.partyWebsites = partyWebsites;
	}
	
	public void addPartyWebsite(PartyWebsite website)
	{
		this.partyWebsites.add(website);
	}
	
	/*
	 * Below are mapped but unretrievable collections. These are important to
	 * allow searching to other entities.
	 */
	private Set<Assets> getSupplierAssets()
	{
		return supplierAssets;
	}
	private void setSupplierAssets(Set<Assets> supplierAssets)
	{
		this.supplierAssets = supplierAssets;
	}
	private Set<Assets> getCustodianAssets()
	{
		return custodianAssets;
	}
	private void setCustodianAssets(Set<Assets> custodianAssets)
	{
		this.custodianAssets = custodianAssets;
	}
	public Set<LeaseIn> getAssetLeaseInClients()
	{
		return assetLeaseInClients;
	}
	public void setAssetLeaseInClients(Set<LeaseIn> assetLeaseInClients)
	{
		this.assetLeaseInClients = assetLeaseInClients;
	}
	
	public Set<LeaseOut> getAssetLeaseOutClients()
	{
		return assetLeaseOutClients;
	}
	public void setAssetLeaseOutClients(Set<LeaseOut> assetLeaseOutClients)
	{
		this.assetLeaseOutClients = assetLeaseOutClients;
	}
	// End of mapped but unretrievable collections

	public String getReferenceForUs() {
		return referenceForUs;
	}

	public void setReferenceForUs(String referenceForUs) {
		this.referenceForUs = referenceForUs;
	}
	
	public String getReferenceForThem() {
		return referenceForThem;
	}

	public void setReferenceForThem(String referenceForThem) {
		this.referenceForThem = referenceForThem;
	}

	public UserData getCurrencyAccepted() {
		return currencyAccepted;
	}

	public void setCurrencyAccepted(UserData currencyAccepted) {
		this.currencyAccepted = currencyAccepted;
	}
	
	public Long getCurrencyAcceptedId() {
		return currencyAcceptedId;
	}

	public void setCurrencyAcceptedId(Long currencyAcceptedId) {
		this.currencyAcceptedId = currencyAcceptedId;
	}

}



