package org.generation.italy.JDBC_ente_sportivo.model1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private Connection dbConnection;

    private static JdbcConnection jdbcConnectionSingletonInstance = null;

    private JdbcConnection() {} // costruttore
    
    public 
    static 
    JdbcConnection readJdbcConnectionInstance(String _driverName
    										, String _driverClassPath
    										, String _serverName
    										, String _serverPort 
    										, String _databaseName
			                                , String _username
			                                , String _password) 
    											throws EnteSportivoModelException {

        if (jdbcConnectionSingletonInstance==null) {

        	jdbcConnectionSingletonInstance = new JdbcConnection();				//auto-istanziazione della classe
            
            String jdbcUrl = 													//formatta la stringa jdbcUrl inserendo i valori al posto dei parametri tra {}
        		String.format("jdbc:%s://%s:%s/%s"
        					, _driverName
        					, _serverName
        					, _serverPort
        					, _databaseName);

            try {
                Class.forName(_driverClassPath);								//verifica la presenza della classe principale del driver JDBC (vedi import via pom.xml esezione del progetto eclipse denominata 'Maven Dependencies')
                jdbcConnectionSingletonInstance.dbConnection
                        = DriverManager.getConnection(jdbcUrl,
                                                    _username,
                                                    _password);

            } catch (SQLException sqlException) {										//eccezione catturata per mancata connessione al database
                throw new EnteSportivoModelException("JdbcConnection -> readJdbcConnectionInstance -> " + sqlException.getMessage());
            } catch (ClassNotFoundException classExcpetion) {					//eccezione per mancanto rilevamento del driver JDBC (driver JDBC non presente)
                throw new EnteSportivoModelException("JdbcConnection -> readJdbcConnectionInstance -> " + classExcpetion.getMessage());
            }
        }
        
        return jdbcConnectionSingletonInstance;
    }


	public Connection getDbConnection() {
		return this.dbConnection;
	}

	/** Test per verfifica implementazione connection jdbc con pattern singleton: 
     * le connessioni fanno riferimento allo stesso oggetto dbConnection di tipo Connection**/
    public static void main(String[] args) {

    	try {
			JdbcConnection jdbcConnection1 = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost", "3306", "ente_sportivo", "root", "");
	    	JdbcConnection jdbcConnection2 = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost", "3306", "ente_sportivo", "root", "");
	    	JdbcConnection jdbcConnection3 = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost", "3306", "ente_sportivo", "root", "");
	    	JdbcConnection jdbcConnection4 = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost", "3306", "ente_sportivo", "root", "");
	    	JdbcConnection jdbcConnection5 = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost", "3306", "ente_sportivo", "root", "");
	    	JdbcConnection jdbcConnection6 = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost", "3306", "ente_sportivo", "root", "");

	        System.out.println(jdbcConnection1);
	        System.out.println(jdbcConnection2);
	        System.out.println(jdbcConnection3);
	        System.out.println(jdbcConnection4);
	        System.out.println(jdbcConnection5);
	        System.out.println(jdbcConnection6);
	    	
		} catch (EnteSportivoModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
}
