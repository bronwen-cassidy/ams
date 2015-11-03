package com.xioq.dasacumen.dao;

import java.io.Serializable;
import java.util.List;

import com.xioq.dasacumen.lib.search.SearchModel;
import com.xioq.dasacumen.lib.search.SearchResult;

public interface CRUDDAO
{
	@Deprecated
	public <M> M retrieve(Class<M> model, Serializable id);
	public <M> M retrieve(Class<M> model, Serializable id, String ... eagerLoads);

	public <M> List<M> retrieveAll(Class<M> model);

	public <M> void create(M modelObject);

	public <M> void update(M modelObject);

	public <M> void delete(M model);
	
	/**
	 * A very simple search facility to find all entities that match the set
	 * fields in the example.
	 */
	public <M> List<M> findByExample(M searchExample);
	
	public <M> SearchResult<M> generalSearch(SearchModel<M> searchObject);
}