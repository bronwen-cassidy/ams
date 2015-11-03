package com.xioq.dasacumen.model.common;

// Generated 24-Jun-2014 12:24:54 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * PartyLinks generated by hbm2java
 */
@Entity @Proxy(lazy=false)
@Table(name="party_links")
public class PartyLink extends VersionControlled implements java.io.Serializable {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="party_links_id_seq") 
	@SequenceGenerator(name="party_links_id_seq", sequenceName="party_links_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="parties_id_1", nullable=false)
	private Party partyId1;
	
	@ManyToOne
	@JoinColumn(name="parties_id_2", nullable=false)
	private Party partyId2;

	@Column(name="type")
	@JoinColumn(name="type")
	private String type;
	
	@Column(name="description")
	@JoinColumn(name="description")
	private String description;

	public PartyLink() {
	}

	public PartyLink(Long id, Party partyId1,
			Party partyId2) {
		this.id = id;
		this.partyId1 = partyId1;
		this.partyId2 = partyId2;

	}
	
	@Override
	public EntityType getModelType()
	{
		return EntityType.PARTY_LINK;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Party getPartyId2() {
		return this.partyId2;
	}

	public void setPartyId2(Party partyId2) {
		this.partyId2 = partyId2;
	}

	public Party getPartyId1() {
		return this.partyId1;
	}

	public void setPartyId1(Party partyId1) {
		this.partyId1 = partyId1;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}