package com.xioq.dasacumen.test;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Holds utilities for helping in tests.
 * 
 * @author echhung
 */
public class TestUtil extends JdbcDaoSupport
{
	private static final String SCHEMA = "acumen";

	/**
	 * Used to reset the sequences to a particular value
	 * 
	 * @param table table name without schema (schema will be appended
	 *        automatically)
	 * @param startValue start value of sequence
	 */
	public void resetSequences(String table, Integer startValue)
	{
		// Make connection and set schema and table variable
		String schemaAndTable = SCHEMA + "." + table;
		String schemaAndTableAndSeq = SCHEMA + "." + table + "_id_seq";

		Integer nextval = getJdbcTemplate().queryForObject("select nextval(?)", Integer.class, schemaAndTableAndSeq);

		System.out.println("TestUtil nextval:" + nextval);
		
		// if start value = null it means that tester wants to use max(id)
		if (startValue == null)
		{
			Integer setVal = getJdbcTemplate().queryForObject("SELECT setval(?, coalesce((select max(id)+1 from "+schemaAndTable+"), 1), false)", Integer.class, schemaAndTableAndSeq);
			System.out.println("TestUtil setVal:" + setVal);

		}
		else
		{
			Integer setVal = getJdbcTemplate().queryForObject("SELECT setval(?, ?, false)", Integer.class, schemaAndTableAndSeq, startValue);
			System.out.println("TestUtil setVal:" + setVal);
		}
	}

	/**
	 * Used to reset the sequences to the max(id)
	 * 
	 * @param table table name without schema (schema will be appended
	 *        automatically)
	 */
	public void resetSequences(String table)
	{
		resetSequences(table, null);
	}
	
	/**
	 * Used to reset the sequences to the max(id)
	 * 
	 * @param table table name without schema (schema will be appended
	 *        automatically)
	 */
	public void resetSequences(DatabaseTable table)
	{
		resetSequences(table.toString(), null);
	}
}
