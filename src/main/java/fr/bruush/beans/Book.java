package fr.bruush.beans;

import java.io.Serializable;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String author;
	private String description;
	
	public Book() {}
	
	public Book(int id, String title, String author, String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
	}	

	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDescription() {
		return description;
	}
}
