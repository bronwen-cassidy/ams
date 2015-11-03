package com.xioq.dasacumen.model.common;

import java.util.HashSet;
import java.util.Set;

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

@Entity @Proxy(lazy = false)
@Table(name = "websites")
public class Website extends VersionControlled implements java.io.Serializable {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="websites_id_seq") 
	@SequenceGenerator(name="websites_id_seq", sequenceName="websites_id_seq", allocationSize=1)
	private Long id;
	private String websiteUrl;

	@OneToMany(mappedBy="website")
	private Set<PartyWebsite> partyWebsites = new HashSet<PartyWebsite>(0);

	public Website() {
	}

	public Website(Long id) {
		this.id = id;
	}

	public Website(Long id, String websiteUrl, Set<PartyWebsite> partyWebsites) {
		this.id = id;
		this.websiteUrl = websiteUrl;
		this.partyWebsites = partyWebsites;
	}

	@Override
	public EntityType getModelType() {
		return EntityType.WEBSITE;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWebsiteUrl() {
		return this.websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Set<PartyWebsite> getPartyWebsites() {
		return this.partyWebsites;
	}

	public void setPartyWebsites(Set<PartyWebsite> partyWebsites) {
		this.partyWebsites = partyWebsites;
	}

}
