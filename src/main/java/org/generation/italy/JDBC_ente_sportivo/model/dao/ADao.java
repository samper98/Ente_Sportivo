package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;

public abstract class ADao {
	
	protected Connection jdbcConnectionToDatabase ;

	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}
	

}
