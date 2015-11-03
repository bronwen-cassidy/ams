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

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.TenantModel;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * EntityListContent one entity to a list
 * @author jmadden
 *
 */
@Entity(name="com.xioq.dasacumen.model.EntityListContent")
@Proxy(lazy=false)
@Table(name="list_contents")
public class EntityListContent extends TenantModel implements java.io.Serializable
{
	private static final Long serialVersionUID = 7709016686161204949L;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="list_contents_id_seq", name="list_contents_id_seq")
	@GeneratedValue(generator="list_contents_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="lists_id")
	private EntityList entityList;
	
	@Column(name="entity_id")
	private Long entityId;
	
	public EntityListContent()
	{}

	public  EntityListContent(Long id, EntityList entityList, Long entityId)
	{
		this.id = id;
		this.entityList = entityList;
		this.entityId = entityId;
		
	}
	
	@Override
	public EntityType getModelType()
	{
		return EntityType.ENTITY_LIST_CONTENT;
	}
	
	/**
	 * 
	 * @return get the id attached to content
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * 
	 * @param id    set the id of the list contents
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * 
	 * @return gets the entity id for the contents.
	 */
	public Long getEntityId()
	{
		return entityId;
	}
	
	/**
	 * 
	 * @param entityId
	 */
	public void setEntityId(Long entityId)
	{
		this.entityId = entityId;
	}

	public EntityList getEntityList()
	{
		return entityList;
	}

	public void setEntityList(EntityList entityList)
	{
		this.entityList = entityList;
	}

}
