package fr.bruush.servlets;

import fr.bruush.beans.Book;
import fr.bruush.beans.DAOBook;
import fr.bruush.beans.DAOFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bibliotheque")
public class Library extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOBook daoBook;
	
	public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        daoBook = daoFactory.getDAOBook("MariaDB");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String code;
		if(id == null) {
			id = "welcome";			
		}
		switch(id) {
			case "welcome":
				request.setAttribute("content", "welcome");	
				break;
			case "display":
				List<Book> catalog = daoBook.getBooks();
				request.setAttribute("catalog", catalog);				
				request.setAttribute("content", "display");	
				break;
			case "add":
				String bookAdded = request.getParameter("bookadded");
				if(bookAdded != null) {
					if(bookAdded.equals("true")) {
			        	String title = request.getParameter("title");
			        	String author = request.getParameter("author");
			        	String description = request.getParameter("description");
						daoBook.add(title, author, description);
					}
					else {			
						request.getRequestDispatcher("bibliotheque?id=display").forward(request,response);
					}
				}
				request.setAttribute("content", "add");	
				break;
			case "edit":
				code = request.getParameter("code");
				request.setAttribute("content", "edit");
				request.setAttribute("book", daoBook.getBook(code));	
				break;
			case "delete":
				daoBook.delete(Integer.parseInt(request.getParameter("code")));
				request.getRequestDispatcher("bibliotheque?id=display").forward(request,response);
			case "update":
				String bookUpdated = request.getParameter("bookupdated");
				if(bookUpdated != null && bookUpdated.equals("true")) {
					code = request.getParameter("code");
		        	String title = request.getParameter("title");
		        	String author = request.getParameter("author");
		        	String description = request.getParameter("description");
		        	daoBook.update(code, title, author, description);
				}
				request.getRequestDispatcher("bibliotheque?id=display").forward(request,response);
			default:
				request.setAttribute("content", "welcome");			
		}
		request.getRequestDispatcher("/jsp/site/template.jsp").forward(request,response);
	}
}
