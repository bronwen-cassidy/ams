package com.xioq.dasacumen.lib.search;

import com.googlecode.genericdao.search.IMutableSearch;


/**
 * The a search model is a bright future interface that extends the 'Generic DAO' 
 * IMutableSearch interface to limit our dependency on third party classes.
 * 
 * IMutableSearch is a much more complete implementation of a generic search model 
 * and as and when we need to expand out functionality, this interface can implement more
 * of the IMutableSearch.
 * @author Stephen Elliott
 */
public interface SearchModel<M> extends IMutableSearch
{
	public SearchModel<M> setPageSize(int maxResults);
}
