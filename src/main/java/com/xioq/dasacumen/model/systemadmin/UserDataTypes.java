package com.xioq.dasacumen.model.systemadmin;

// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.ModelBase;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * UserDataTypes generated by hbm2java
 */
@Entity 
@Immutable
@Proxy(lazy=false)
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="userDataTypesCache")
@Table(name = "user_data_Types")
public class UserDataTypes extends ModelBase implements java.io.Serializable {

	private static final Long serialVersionUID = 8070528222053459090L;
	
	@Id
	private Long id;
	
	@ManyToOne 
	@JoinColumn(name="parent_id", nullable=true)
	private UserDataTypes parentType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_data_cat_id", nullable=false)
	private UserDataCat userDataCat;
	
	@Column(name="name")
	private String name;
	
	@Column(name="value_required")
	private Boolean valueRequired;
	
	@Column(name="user_data_value_type")
	private String userDataValueType;
	
	@Column(name="description")
	private String description;
	
	@Column(name="lang_code")
	private String langCode;
	
	@Column(name="desc_code")
	private String descCode;

	public UserDataTypes() {
	}

	public UserDataTypes(Long id, UserDataCat userDataCat, Boolean valueRequired) {
		this.id = id;
		this.userDataCat = userDataCat;
		this.valueRequired = valueRequired;
	}

	public UserDataTypes(Long id, UserDataTypes parentType, UserDataCat userDataCat, String name, Boolean valueRequired, String userDataValueType,
			String description, String langCode, String descCode) {
		this.id = id;
		this.parentType = parentType;
		this.userDataCat = userDataCat;
		this.name = name;
		this.valueRequired = valueRequired;
		this.userDataValueType = userDataValueType;
		this.description = description;
		this.langCode = langCode;
		this.descCode = descCode;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.USER_DATA_TYPE;
	}
	
	public Long getId() {
		return this.id;
	}

	private void setId(Long id) {
		this.id = id;
	}
	
	public UserDataTypes getParentType() {
		return this.parentType;
	}

	public void setParentType(UserDataTypes parentType) {
		this.parentType = parentType;
	}

	public UserDataCat getUserDataCat() {
		return this.userDataCat;
	}

	public void setUserDataCat(UserDataCat userDataCat) {
		this.userDataCat = userDataCat;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLangCode() {
		return this.langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getDescCode() {
		return this.descCode;
	}

	public void setDescCode(String descCode) {
		this.descCode = descCode;
	}

	/**
	 * true - Value is needed with user data.
	 * false - Value is not needed with user data and should be empty.
	 * @return the valueRequred
	 */
	public Boolean getValueRequired() {
		return valueRequired;
	}

	/**
	 * Set to true if user data needs values attached.
	 * @param valueRequred the valueRequred to set
	 */
	public void setValueRequired(Boolean valueRequired) {
		this.valueRequired = valueRequired;
	}

	/**
	 * @return the userDataTypeValue
	 */
	public String getUserDataValueType() {
		return userDataValueType;
	}

	/**
	 * Values set should be from UserDataTypeValueConstants.
	 * @param userDataTypeValue the userDataTypeValue to set
	 */
	public void setUserDataValueType(String userDataValueType) {
		this.userDataValueType = userDataValueType;
	}
}