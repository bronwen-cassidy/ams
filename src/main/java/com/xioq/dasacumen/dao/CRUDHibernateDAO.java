package com.xioq.dasacumen.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Repository;

import com.xioq.dasacumen.lib.search.SearchModel;
import com.xioq.dasacumen.lib.search.SearchResult;

import com.googlecode.genericdao.search.hibernate.HibernateSearchProcessor;

/**
 * Hibernate implementation of the CRUD DAO.
 * @author Stephen
 */
@Repository
public class CRUDHibernateDAO implements CRUDDAO
{
	/* The @Autowired annotation means that sessionFactory will be instantiated only once across the whole program 
	 * by Spring. This is much more efficient that creating a new one for each controller. Session Factories are also 
	 * heavy on processing due to loading the drivers etc.
	 */
	@Autowired @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * @see com.xioq.dasacumen.dao.CRUDDAO#retrieve(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <M> M retrieve(final Class<M> model, final Serializable id)
	{
		return retrieve(model, id, (String[])null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.xioq.dasacumen.dao.CRUDDAO#retrieve(java.lang.Class, java.io.Serializable, java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <M> M retrieve(final Class<M> model, final Serializable id, String ... eagerLoads)
	{
		try
		{
			M entity = (M)sessionFactory.getCurrentSession().load(model, id);
			
			if(null != eagerLoads)
			{
				ExpressionParser parser = new SpelExpressionParser();
				EvaluationContext context = new StandardEvaluationContext(entity);
				Expression exp;

				for (String string : eagerLoads)
				{
					exp = parser.parseExpression(string);
					Object value = exp.getValue(context);
					Hibernate.initialize(value);
				}
			}
			
			return entity;
		}
		catch (HibernateException e)
		{
			throw e;
		}
	}

	
	/**
	 * @see com.xioq.dasacumen.dao.CRUDDAO#create(java.lang.Object)
	 */
	@Override
	public <M> void create(M modelObject)
	{
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(modelObject);
		}
		catch(HibernateException e)
		{
			throw e;
		}
	}
	
	@Override
	public <M> void update(M modelObject)
	{
		try
		{
			sessionFactory.getCurrentSession().update(modelObject);
		}
		catch(HibernateException e)
		{
			throw e;
		}
	}
	
	/**
	 * @see com.xioq.dasacumen.dao.CRUDDAO#retrieveAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <M> List<M> retrieveAll(Class<M> model) 
	{
		try
		{
			return (List<M>) sessionFactory.getCurrentSession().createCriteria(model).list();
		}
		catch (HibernateException e)
		{
			throw e;
		}
	}
	
	/**
	 * @see com.xioq.dasacumen.dao.CRUDDAO#delete(java.lang.Object)
	 */
	@Override
	public <M> void delete(M model)
	{
		try{
			sessionFactory.getCurrentSession().delete(model);
		}
		catch(HibernateException e){
			throw e;
		}
	}

	/**
	 * A very simple search facility to find all entities that match the set
	 * fields in the example.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <M> List<M> findByExample(M searchExample)
	{
		try
		{
			Criteria createCriteria = sessionFactory.getCurrentSession().createCriteria(searchExample.getClass());
			return (List<M>) createCriteria.add(Example.create(searchExample)).list();
		}
		catch (HibernateException e)
		{
			throw e;
		}
	}
	
	/**
	 * A very simple search facility to find all entities that match the set
	 * fields in the example.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <M> SearchResult<M> generalSearch(SearchModel<M> searchObject)
	{
		try
		{
			HibernateSearchProcessor searchProcessor = HibernateSearchProcessor.getInstanceForSessionFactory(sessionFactory);
			
			com.googlecode.genericdao.search.SearchResult<M> searchAndCount = searchProcessor.searchAndCount(sessionFactory.getCurrentSession(), searchObject.getSearchClass(), searchObject);
			
			return new SearchResult<M>(searchAndCount);

			
//			//building the search criteria
//			Criteria createCriteria = sessionFactory.getCurrentSession().createCriteria(searchObject.getSearchModel())
//					.add(Restrictions.ilike(searchObject.getSearch_by(), (searchObject.getQuery()+"%")))
//					.setFirstResult(searchObject.getOffSet())//sets the beginning of the resultset
//					.setMaxResults(searchObject.getLimit());//sets how many rows to retrieve 
//			
//			//adding all the orders to the criteria. Prioritising order based on the index of the array
//			for(int i =0;i < searchObject.getOrder_by().length; i++){
//				createCriteria.addOrder(Order.asc(searchObject.getOrder_by()[i]));
//			}
//			
//			return (List<M>) createCriteria.list();
		}
		catch (HibernateException e)
		{
			throw e;
		}
	}
}