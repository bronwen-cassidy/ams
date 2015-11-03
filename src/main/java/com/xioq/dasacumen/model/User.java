package com.xioq.dasacumen.model;

// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * Represents a user and holds relevant log in information.
 *
 */
@Entity @Proxy(lazy=false)
@Table(name="users")
public class User extends VersionControlled implements java.io.Serializable 
{
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_id_seq") 
	@SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize=1)
	private Long id;
	
//	@Size(min=4, max=15)
//	@Pattern(regexp="^\\w-{8,}$")
//	@NotBlank(message="User Name Cannot be blank")
	@Column(nullable=false)
	private String userName;
	private String surname;
	private String forenames;

	@Column(nullable=false)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(columnDefinition="boolean default true", nullable = false)
	private Boolean enabled = true; //Default to true so spring security will allow the user to login.
	
	@OneToMany(mappedBy = "user", orphanRemoval=true, cascade={CascadeType.ALL})
	private Set<Authority> authorities= new HashSet<Authority>(0);
	
	//TODO XXX : Possibly add email so when users forget the password etc?
	
	public User(Long id, String userName, String surname, String forenames,
			Integer tenantId, String createdBy, Date createdDate,
			String lastUpdatedBy, Date lastUpdatedDate, Integer versionNumber) {
		this.id = id;
		this.userName = userName;
		this.surname = surname;
		this.forenames = forenames;
	}
	
	public User() {
	}
	
	@Override
	public EntityType getModelType()
	{
		return EntityType.USER;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public User(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getForenames() {
		return this.forenames;
	}

	public void setForenames(String forenames) {
		this.forenames = forenames;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
