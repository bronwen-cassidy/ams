package com.xioq.dasacumen.lib.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.xioq.dasacumen.model.constants.EntityType;

/**
 * Interface that all model classes implement so that they can be generically
 * identified by their type and id.
 * 
 * @author Stephen Elliott
 */
public interface ModelEntity
{
	/**
	 * The entity type - Asset, List, Group etc..
	 * @return
	 */
	@JsonIgnore
	public EntityType getModelType();
	/**
	 * The primary key id of the entity
	 * @return
	 */
	public Long getId();
}
