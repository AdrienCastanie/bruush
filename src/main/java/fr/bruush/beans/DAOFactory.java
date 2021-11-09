package fr.bruush.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
	
	private static volatile DAOFactory instance = null;
	private String url;
	private String username;
	private String password;
	
	private DAOFactory() {}
	
	public final static DAOFactory getInstance() {
		if(DAOFactory.instance == null) {
			synchronized(DAOFactory.class) {
				if(DAOFactory.instance == null) {
			        instance = new DAOFactory();
				}
			}
		}
	    return DAOFactory.instance;
	}
	
	public Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(url, username, password);
	}
	
	private void setParamMariaDB(String url, String username, String password) {
	    this.url = url;
	    this.username = username;
	    this.password = password;		
	    try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 	
	}
	
	public DAOBook getDAOBook(String type) {
		switch(type) {
			case "MariaDB":
				setParamMariaDB("jdbc:mysql://localhost:3306/library", "root", "MySQL2021");
				return new DAOBookMariaDB(this);
			default:
				return null;	
		}
	}

}
