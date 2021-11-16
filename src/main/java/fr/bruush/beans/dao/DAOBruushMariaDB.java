package fr.bruush.beans.dao;
import fr.bruush.beans.objects.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOBruushMariaDB implements DAOBruush {
	
	private DAOFactory daoFactory;

	DAOBruushMariaDB(DAOFactory daoFactory) {
	    this.daoFactory = daoFactory;
	}

	@Override
	public void add(String nom, String prenom, String mail, String addr, int bloque) {
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"INSERT INTO CLIENT(nom, prenom, mail, addr, bloque) VALUES(?, ?, ?, ?, ?);");
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
			preparedStatement.setString(3, mail);
			preparedStatement.setString(4, addr);
			preparedStatement.setInt(5, bloque);
			preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }		
	}
	
	@Override
    public Client getClient(int id) {
		Client client = null;
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"SELECT * FROM CLIENT WHERE id=?;");
			preparedStatement.setInt(1, id);
			try ( ResultSet result = preparedStatement.executeQuery() ) {
				result.next();
	        	int code = result.getInt("id");
	        	String nom = result.getString("nom");
	        	String prenom = result.getString("prenom");
	        	String mail = result.getString("mail");
				String addr = result.getString("addr");
				int bloque = result.getInt("bloque");
	        	client = new Client(code, nom, prenom, mail, addr, bloque);
            }	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return client;
	}

	@Override
	public List<Client> getClients() {
		List<Client> clients = new ArrayList<>();
	    try (Connection connexion = daoFactory.getConnection();
	      Statement statement = connexion.createStatement();
	      ResultSet result = statement.executeQuery("SELECT * FROM CLIENT;")) {
	        while (result.next()) {
	        	int id = result.getInt("id");
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				String mail = result.getString("mail");
				String addr = result.getString("addr");
				int bloque = result.getInt("bloque");
	        	clients.add(new Client(id, nom, prenom, mail, addr, bloque));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return clients;
	}

	@Override
	public void update(int id, String nom, String prenom, String mail, String addr, int bloque) {
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"UPDATE CLIENT SET nom=?, prenom=?, mail=?, addr=?, bloque=? WHERE id=?;");
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
			preparedStatement.setString(3, mail);
			preparedStatement.setString(4, addr);
			preparedStatement.setInt(5, bloque);
			preparedStatement.setInt(6, id);
			preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }		
	}

	@Override
	public void delete(int id) {
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
				"DELETE FROM books WHERE id=?;");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Client getClientByMailAndMdp(String mail, String mdp) {
		return null;
	}
}
