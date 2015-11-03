package com.xioq.dasacumen.model.common;

// Generated 24-Jun-2014 12:24:54 by Hibernate Tools 4.0.0

import javax.persistence.CascadeType;
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
 * PartyEmails generated by hbm2java
 */
@Entity @Proxy(lazy=false)
@Table(name="party_emails")
public class PartyEmail extends VersionControlled implements java.io.Serializable, Comparable<PartyEmail> {

	@Id
	@SequenceGenerator(allocationSize=1, sequenceName="party_emails_id_seq", name="party_emails_id_seq")
	@GeneratedValue(generator="party_emails_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="emails_id", nullable=false)
	private Email email;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parties_id", nullable=false)
	private Party party;
	
	@Column(name="emails_type")
	private Long emailType;
	
	public PartyEmail() {
	}

	public PartyEmail(Long id, Email email, Party party) {
		this.id = id;
		this.email = email;
		this.party = party;
	}

	public PartyEmail(Long id, Email email, Party party,
			Long emailType) {
		this.id = id;
		this.email = email;
		this.party = party;
		this.emailType = emailType;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.PARTY_EMAIL;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Email getEmail() {
		return this.email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Long getEmailType() {
		return this.emailType;
	}

	public void setEmailType(Long emailType) {
		this.emailType = emailType;
	}

	@Override
	public int compareTo(PartyEmail o) {
		return 1;
	}
}