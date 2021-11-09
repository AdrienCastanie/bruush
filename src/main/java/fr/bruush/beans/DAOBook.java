package fr.bruush.beans;

import java.util.List;

public interface DAOBook {
	
    public abstract void add(String title, String author, String description);
	
    public abstract Book getBook(String id);
	
    public abstract List<Book> getBooks();
    
    public abstract void update(String id, String title, String author, String description);

    public abstract void delete(int id);

}
