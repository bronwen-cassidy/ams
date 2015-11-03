package com.xioq.dasacumen.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * User made Draft holds an object type eg asset and its data in json.
 * @author echhung
 *
 */

@Entity @Proxy(lazy=false)
@Table(name="drafts")
public class Draft<T> extends VersionControlled implements java.io.Serializable {
	
	/**
	 * Generated serialVersion UID
	 */
	private static final Long serialVersionUID = 1565720329261161634L;

	@Override
	public EntityType getModelType()
	{
		return EntityType.DRAFT;
	}
	
	/**
	 * Primary key, this cannot be set when creating and
	 * is determined by db.
	 */
	@Id
	@SequenceGenerator(allocationSize=1, sequenceName="drafts_id_seq", name="drafts_id_seq")
	@GeneratedValue(generator="drafts_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	/**
	 * Id of user owning draft.
	 */
	@Column(name="users_id")
	private Long userId;
	
	/**
	 * Name of draft
	 */
	private String name;
	
	/**
	 * object type
	 */
	private String objectType;
	
	/**
	 * object data held in json
	 */
	private String objectData;
	
	/**
	 * 
	 * @param id this will be changed by the database anyway though via sequence (only used in retrieving, updating and deleting)
	 * @param userId the user who made the asset
	 * @param name the name of the draft
	 * @param objectData The object stored.
	 */
	public Draft(Long id, Long userId, String name, T objectData) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.setObject(objectData);
	}

	public Draft() {
	}

	/**
	 * Get name of draft
	 * @return name of draft
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of draft
	 * @param name of draft
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get id of draft
	 * @return id of draft
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id of draft
	 * @param id of draft
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * use Class.forName(objectType) to get in type class.
	 * @return the fully qualified name of object type
	 */
	private void setObjectType() {
		this.objectType = objectType;
	}

	/**
	 * use Class.forName(objectType) to get in type class.
	 * @return the fully qualified name of object type
	 */
	private String getObjectType() {
		return objectType;
	}

	/**
	 * @param className The fully qualified name of the class.
	 */
	private void setObjectType(String className) {
		this.objectType = className;
	}
	
	/**
	 * Converts the json back to an object and returns it.
	 */
	public T getObject() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Class<?> className = Class.forName(objectType);
			return (T) mapper.readValue(this.objectData, className);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Only will get this far if something wrong happens.
		return null;
	}

	/**
	 * Serialises the object to a json string and stores it.
	 * @param objectData the objectData to set
	 */
	public void setObject(T object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.setObjectType(object.getClass().getName());
			this.objectData = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the object data in json format, use
	 * getObject instead for normal use.
	 * @return the objectData 
	 */
	private String getObjectData() {
		return this.objectData;
	}
	
	/**
	 * Set the object data (this should be in json format), use
	 * setObject instead for normal use.
	 */
	private void setObjectData(String objectData) {
		this.objectData = objectData;
	}

}
