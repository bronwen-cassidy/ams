package com.xioq.dasacumen.model.common;

// Generated 24-Jun-2014 12:24:54 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * Emails generated by hbm2java
 */
@Entity
@Table(name="emails")
public class Email extends VersionControlled implements java.io.Serializable {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="emails_id_seq") 
	@SequenceGenerator(name="emails_id_seq", sequenceName="emails_id_seq", allocationSize=1)
	private Long id;
	private String emailAddress;
	
	@OneToMany(mappedBy="email")
	private Set<PartyEmail> partyEmails = new HashSet<PartyEmail>(0);

	public Email() {
	}

	public Email(Long id) {
		this.id = id;
	}

	public Email(Long id, String emailAddress,
			Set<PartyEmail> partyEmails) {
		this.id = id;
		this.emailAddress = emailAddress;

		this.partyEmails = partyEmails;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.EMAIL;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public Set<PartyEmail> getPartyEmails() {
		return this.partyEmails;
	}

	public void setPartyEmails(Set<PartyEmail> partyEmails) {
		this.partyEmails = partyEmails;
	}
}
