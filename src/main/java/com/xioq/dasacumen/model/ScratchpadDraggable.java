package com.xioq.dasacumen.model;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public interface ScratchpadDraggable 
{
	/**
	 * Gets all the draggable names that this model object produces
	 * @return
	 */
	public Set<String> getDraggableNames();
	
	/**
	 * Gets the primary key of the draggable. Used to uniquely identify each draggable on 
	 * the page
	 */
	public Long getId();

	/**
	 * What is to be shown in the scratchpad search results
	 * @return
	 */
	public String getDisplayName();
}
