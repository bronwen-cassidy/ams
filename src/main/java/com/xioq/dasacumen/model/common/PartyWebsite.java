package com.xioq.dasacumen.model.common;

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

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

@Entity
@Table(name="party_websites")
public class PartyWebsite extends VersionControlled implements java.io.Serializable, Comparable<PartyWebsite> {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="party_websites_id_seq") 
	@SequenceGenerator(name="party_websites_id_seq", sequenceName="party_websites_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="websites_id", nullable=false)
	private Website website;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parties_id", nullable=false)
	private Party party;
	private String websiteType;

	public PartyWebsite() {
	}

	public PartyWebsite(Long id, Website website, Party party) {
		this.id = id;
		this.website = website;
		this.party = party;
	}
	
	public PartyWebsite(Long id, Website website ,
			Party party, String websiteType) {
		this.id = id;
		this.website = website;
		this.party = party;
		this.websiteType = websiteType;
	}
	
	@Override
	public EntityType getModelType()
	{
		return EntityType.PARTY_WEBSITE;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Website getWebsite() {
		return this.website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getWebsiteType() {
		return this.websiteType;
	}

	public void setWebsiteType(String websiteType) {
		this.websiteType = websiteType;
	}

	@Override
	public int compareTo(PartyWebsite arg0) {
		return 1;
	}
}
