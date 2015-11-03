/**
 * 
 */
package com.xioq.lib.dbunit;

import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom data type factory to allow use of the 'citext' database column type.
 * 
 * This is used by DBUnit when reading the column names of a table and need to determine the type.
 * 
 * 'citext' is a case insensitive text type and is specific to Postgresql
 * 
 * @author Stephen Elliott
 */
public class BFPostgresqlDataTypeFactory extends PostgresqlDataTypeFactory
{
    private static final Logger logger = LoggerFactory.getLogger(BFPostgresqlDataTypeFactory.class);

	public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException {
        logger.debug("createDataType(sqlType={}, sqlTypeName={})",
                     String.valueOf(sqlType), sqlTypeName);

        if (sqlType == Types.OTHER)
        {
            // Treat Postgresql UUID types as VARCHARS
            if ("citext".equals(sqlTypeName))
                return  DataType.VARCHAR;
        }
        
        return super.createDataType(sqlType, sqlTypeName);
    }
}
