package fr.bruush.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOFactory {
	
	private static volatile DAOFactory instance = null;
	private EntityManagerFactory emf;
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
	
	private void setParamJPA() {
		emf = Persistence.createEntityManagerFactory("Client");
	}
	
	public DAOClient getDAOClient(String type) {
		switch(type) {
			case "MariaDB":
				setParamMariaDB("jdbc:mysql://127.0.0.1:3306/BRUUSH", "root", "bruush");
				return new DAOClientMariaDB(this);
			case "JPA":
				setParamJPA();
				return new DAOClientJPA(emf);
			default:
				return null;	
		}
	}

}
