package com.xioq.dasacumen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xioq.dasacumen.model.User;

@Repository
public class SomeJDBCDAO implements RowMapper<User>
{
	@Autowired
	private DataSource ds;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xioq.dasacumen.dao.SomeDAO#retrieve(java.lang.Class,
	 * java.io.Serializable)
	 */
	public User retrieve(final Long id)
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		User user = jdbcTemplate.queryForObject("select * from users where id_pk = ?", this, id);
		return user;
	}

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException
	{
		User result = new User();
		result.setSurname(arg0.getString("surname"));
		return null;
	}
}
