package com.xioq.dasacumen.model.common;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.model.constants.EntityType;


// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0


/**
 * People generated by hbm2java
 */
@PrimaryKeyJoinColumn(name="parties_id")
@Entity @Proxy(lazy=false)
@Table(name="people")
public class Person extends Party implements java.io.Serializable 
{
	private String position;
	private String forenames;
	private String surname;
	private String gender;

	public Person() {
	}

	public Person(Long id, Party parties) {
		super(id);
	}

	public Person(Long id, String forenames, String surname, String position,
			String gender) {
		super(id);
		this.position = position;
		this.forenames = forenames;
		this.surname = surname;
		this.gender = gender;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.PEOPLE;
	}
	
	// Since this method just combines forenames and surname and does not store the actual name. We
	// do not need the json convertor to make a field for it when converting it to json, therfore we
	// ignore it.
	@JsonIgnore
	@Override
	public String getName()
	{
		String tmp = getForenames();
		if(null != tmp && !tmp.isEmpty())
			tmp += " ";
		else
			tmp = "";
		tmp += getSurname();
		return tmp;
	}

	public String getForenames() {
		return this.forenames;
	}

	public void setForenames(String forenames) {
		this.forenames = forenames;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
}
