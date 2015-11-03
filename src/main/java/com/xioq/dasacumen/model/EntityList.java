package com.xioq.dasacumen.model;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * 
 * List of entities defined by entity type
 * 
 * @author jmadden
 * 
 */
@Entity(name="com.xioq.dasacumen.model.EntityList")
@Proxy(lazy=false)
@Table(name="lists")
public class EntityList extends VersionControlled implements java.io.Serializable
{
	private static final Long serialVersionUID = -2926635066656119520L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="lists_id_seq", name="lists_id_seq")
	@GeneratedValue(generator="lists_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="users_id")
	private Long userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="entity_type")
	private String entityType;

	@OneToMany(mappedBy="entityList" , orphanRemoval = true, cascade = {CascadeType.ALL})
	@LazyCollection(value =LazyCollectionOption.FALSE)
	private Set<EntityListContent> entityListContents = new HashSet<EntityListContent>(0);

	public EntityList()
	{}

	public EntityList(Long id, String name, Long userId, String entityType, Set<EntityListContent> entityListContents)
	{
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.entityType = entityType;
		this.entityListContents = entityListContents;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.ENTITY_LIST;
	}
	
	/**
	 * 
	 * @return id of list
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * 
	 * @param id set the id of the list
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * 
	 * @return the name of the list
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 
	 * @param name set the name of the list
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 
	 * @return the current entity type set
	 */

	public String getEntityType()
	{
		return entityType;
	}

	/**
	 * 
	 * @param entitytype set the entity type
	 */
	public void setEntityType(String entitytype)
	{
		this.entityType = entitytype;

	}

	/**
	 * 
	 * @return gets the user id
	 */
	public Long getUserId()
	{
		return userId;
	}

	/**
	 * 
	 * @param userId set userId for list/s
	 */
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Set<EntityListContent> getEntityListContents()
	{
		return entityListContents;
	}

	public void setEntityListContents(Set<EntityListContent> entityListContents)
	{
		this.entityListContents = entityListContents;
	}
}
