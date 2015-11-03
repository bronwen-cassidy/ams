package com.xioq.dasacumen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * The level of access a user has is determined by authorities.
 * @author mwalsh
 *
 */
@Entity 
@Proxy(lazy=false)
// Makes a unique constraint for authority and username combination.
@Table(name = "authorities", uniqueConstraints={@UniqueConstraint(columnNames={"authority", "users_id"})})
public class Authority extends VersionControlled implements java.io.Serializable {

	private static final long serialVersionUID = -5153377487424866393L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="authorities_id_seq") 
	@SequenceGenerator(name="authorities_id_seq", sequenceName="authorities_id_seq", allocationSize=1)
	private Long id;
	
	@Column(length=45, nullable=false)
	private String authority;
	
	@ManyToOne
	@JoinColumn(name="users_id", nullable=false, updatable=false)
	private User user;
	
	public Authority() {
	}

	public Authority(User user, String authority) {
		this.user = user;
		this.authority = authority;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public EntityType getModelType() {
		return EntityType.AUTHORITY;
	}

	@Override
	public Long getId() {
		return id;
	}
}
