/**
 * 
 */
package com.xioq.dasacumen.lib.search;

/**
 * A search result holds a paged list of the results and a total (unpaged) count
 * of the search.
 * 
 * Simple extension of the 'Generic DAO' SearchResult
 * Just to limit the dependency of a third party library
 * 
 * @author Stephen Elliott
 */
public class SearchResult<T> extends com.googlecode.genericdao.search.SearchResult<T>
{
	public SearchResult(com.googlecode.genericdao.search.SearchResult<T> s)
	{
		this.setResult(s.getResult());
		this.setTotalCount(s.getTotalCount());
	}
}
