/**
 * 
 */
package com.xioq.dasacumen.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xioq.dasacumen.dao.CRUDDAO;
import com.xioq.dasacumen.lib.search.SearchModel;
import com.xioq.dasacumen.lib.search.SearchResult;

/**
 * Implementation of the generic CRUD service.
 * 
 * For CRUD (create, retrieve, update, delete) operations on the database.
 * 
 * @author Stephen
 */
@Service
@Transactional
public class CRUDServiceImpl implements CRUDService
{
	/**
	 * The DAO responsible for the database CRUD operations
	 */
	@Autowired
	private CRUDDAO genericDAO;

	/**
	 * @see com.xioq.dasacumen.service.CRUDService#retrieve(java.lang.Class,
	 *      java.io.Serializable)
	 */
	@Override
	public <M> M retrieve(Class<M> model, Long id)
	{
		return retrieve(model, id, (String[]) null);
	}
	@Override
	public <M> M retrieve(Class<M> model, Long id, String... eagerLoads)
	{
		return genericDAO.retrieve(model, id, eagerLoads);
	}

	/**
	 * @see com.xioq.dasacumen.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public <M> void create(M modelObject)
	{
		genericDAO.create(modelObject);
	}
	
	@Override
	public <M> void update(M modelObject)
	{
		genericDAO.update(modelObject);
	}

	/**
	 * @see com.xioq.dasacumen.service.CRUDService#retrieveAll(java.lang.Class)
	 */
	@Override
	public <M> List<M> retrieveAll(Class<M> model)
	{
		return genericDAO.retrieveAll(model);
	}

	/**
	 * @see com.xioq.dasacumen.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public <M> void delete(M model)
	{
		genericDAO.delete(model);
	}

	@Override
	public <M> List<M> findByExample(M searchExample)
	{
		return genericDAO.findByExample(searchExample);
	}
	
	@Override
	public <M> SearchResult<M> generalSearch(SearchModel<M> searchObject){
		return genericDAO.generalSearch(searchObject);
	}

	@Override
	public <M> SearchResult<M> generalSearch(SearchModel<M> searchObject, String ... eagerLoads)
	{
		searchObject.setFetches(Arrays.asList(eagerLoads));
		return genericDAO.generalSearch(searchObject);
	}

}
