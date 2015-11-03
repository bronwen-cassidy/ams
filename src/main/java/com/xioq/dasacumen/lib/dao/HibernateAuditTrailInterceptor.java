/**
 * 
 */
package com.xioq.dasacumen.lib.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xioq.dasacumen.lib.model.TenantModel;
import com.xioq.dasacumen.lib.utilities.UidModel;
import com.xioq.dasacumen.lib.utilities.UidValueGenerator;
import com.xioq.dasacumen.model.assetregister.Assets;

/**
 * Hibernate interceptor to set the audit fields on all model objects when saving to the DB
 * @author Stephen Elliott
 */
@Component("hibernateAuditTrailInterceptor")
public class HibernateAuditTrailInterceptor extends EmptyInterceptor
{
	@Autowired 
	UidValueGenerator uidValueGenerator;
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	private static final long serialVersionUID = 8464943841292850085L;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
	{
		// TODO Get the user from the local thread
		setValue(currentState, propertyNames, "lastUpdatedBy", "audit");
		setValue(currentState, propertyNames, "lastUpdatedDate", new Date());
		return true;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		if (entity instanceof TenantModel)
		{
			// TODO Get the tenant ID from the user 
			setValue(state, propertyNames, "tenantId", 1);
		}
		
		if (entity instanceof UidModel)
		{
			setValue(state, propertyNames, "uid", uidValueGenerator.generate());
		}
		
		if(entity instanceof Assets) // Set part 4 of the asset number
		{
			// TODO Need a generator for the asset number. A new sequence for each unique combination of of the first three parts of asset number
			Integer nextPart4Num = jdbcTemplate.queryForObject("select nextval ('acumen.asset_part4_seq')", Integer.class);
			setValue(state, propertyNames, "assetNumberPart4", nextPart4Num);
		}
		
		// TODO Get the user from the local thread
		setValue(state, propertyNames, "createdBy", "audit");
		setValue(state, propertyNames, "createdDate", new Date());
		// TODO Get the user from the local thread
		setValue(state, propertyNames, "lastUpdatedBy", "audit");
		setValue(state, propertyNames, "lastUpdatedDate", new Date());
		return true;
	}

	private void setValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object value)
	{
		int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		if (index >= 0)
		{
			currentState[index] = value;
		}
	}
}
