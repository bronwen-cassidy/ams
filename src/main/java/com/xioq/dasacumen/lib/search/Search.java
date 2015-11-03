/**
 * 
 */
package com.xioq.dasacumen.lib.search;


/**
 * This is a bright future implementation of the Generic DAO search object.
 * Required to limit our dependency on third party libraries. But currently is
 * simply implemented with the
 * <code>com.googlecode.genericdao.search.Search</code> class.
 * 
 * @author Stephen Elliott
 */
public abstract class Search<M> extends com.googlecode.genericdao.search.Search implements SearchModel<M>
{
	public Search(Class<M> modelClass)
	{
		super(modelClass);
	}

	/*
	 * Paging properties
	 */
	/**
	 * Sets which result to page from within the full list.
	 */
	@Override
	public com.googlecode.genericdao.search.Search setFirstResult(int firstResult)
	{
		return super.setFirstResult(firstResult);
	}
	/** Sets the number of results per page */
	public SearchModel<M> setPageSize(int maxResults)
	{
		super.setMaxResults(maxResults);
		return this;
	}
	/** Sets which page we need. This is used when you want to jump to a particular page.
	 * When just wanting the next page, probably better to use the First Page attribute
	 */
	@Override
	public com.googlecode.genericdao.search.Search setPage(int page)
	{
		return super.setPage(page);
	}
	/*
	 * End Paging properties
	 */
}
