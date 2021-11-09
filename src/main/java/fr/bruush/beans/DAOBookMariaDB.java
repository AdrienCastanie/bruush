package fr.bruush.beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOBookMariaDB implements DAOBook {
	
	private DAOFactory daoFactory;

	DAOBookMariaDB(DAOFactory daoFactory) {
	    this.daoFactory = daoFactory;
	}

	@Override
	public void add(String title, String author, String description) {
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"INSERT INTO books(title, author, description) VALUES(?, ?, ?);");
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, description);
			preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }		
	}
	
	@Override
    public Book getBook(String id) {
		Book book = null;
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"SELECT * FROM books WHERE id=?;");
			preparedStatement.setString(1, id);
			try ( ResultSet result = preparedStatement.executeQuery() ) {
				result.next();
	        	int code = result.getInt("id");
	        	String title = result.getString("title");
	        	String author = result.getString("author");
	        	String description = result.getString("description");
	        	book = new Book(code,title,author,description);
            }	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return book;		
	}

	@Override
	public List<Book> getBooks() {
		List<Book> catalog = new ArrayList<>();
	    try (Connection connexion = daoFactory.getConnection();
	      Statement statement = connexion.createStatement();
	      ResultSet result = statement.executeQuery(
	    		  "SELECT id, title, author, description FROM books;")) {
	        while (result.next()) {
	        	int id = result.getInt("id");
	        	String title = result.getString("title");
	        	String author = result.getString("author");
	        	String description = result.getString("description");
	        	Book book = new Book(id,title,author,description);
	        	catalog.add(book);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return catalog;
	}

	@Override
	public void update(String id, String title, String author, String description) {
		try (Connection connexion = daoFactory.getConnection()){
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"UPDATE books SET title=?, author=?, description=? WHERE id=?;");
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, description);
			preparedStatement.setString(4, id);
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
}
