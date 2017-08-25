package it.capone.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// Mi collego alla sorgente dati di TomEE che ho configurato nel file context.xml
public class DBConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException
	{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/qaxDB");
		Connection connection = ds.getConnection();
                System.out.println(connection);

		return connection;
	}

}
