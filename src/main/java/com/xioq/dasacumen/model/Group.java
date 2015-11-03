package com.xioq.dasacumen.model;

import java.util.HashSet;
import java.util.Set;

import com.xioq.dasacumen.lib.annotations.Draggable;
import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

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

/**
 * 
 * Group a way of categorising assets.
 * 
 * @author jmadden
 * 
 */
@Entity
@Proxy(lazy=false)
@Table(name = "groups")
@Draggable(names={"groupDraggable"})
public class Group extends VersionControlled implements java.io.Serializable, ScratchpadDraggable
{

	private static final Long serialVersionUID = 157601018616234495L;
	
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="groups_id_seq", name="groups_id_seq")
	@GeneratedValue(generator="groups_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	@Draggable
	private Long id;
	
	@Draggable
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "group", orphanRemoval = true, cascade = {CascadeType.ALL})
	@LazyCollection(value =LazyCollectionOption.FALSE)
	private Set<GroupAsset> groupAssets = new HashSet<GroupAsset>(0);

	public Group()
	{}

	public Group(Long id, String name, Set<GroupAsset> groupAssets)
	{
		this.id = id;
		this.name = name;
		this.groupAssets = groupAssets;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.GROUP;
	}
	
	/**
	 * @return get the current group id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id set the id of the group
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return name of the group
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 
	 * @param name sets the name of the group.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 
	 * @return groupAssets should return any of the assets associated with this
	 *         group
	 */
	public Set<GroupAsset> getGroupAssets()
	{
		return groupAssets;
	}

	/**
	 * 
	 * @param groupAssets you are able to set what asset/s are in what group
	 */
	public void setGroupAssets(Set<GroupAsset> groupAssets)
	{
		this.groupAssets = groupAssets;
	}

	@Override
	public Set<String> getDraggableNames() 
	{
		Set<String> dragNames = new HashSet<String>();
		dragNames.add("groupDraggable");
		return dragNames;	
	}

	@Override
	public String getDisplayName() 
	{
		return getName();
	}
}
