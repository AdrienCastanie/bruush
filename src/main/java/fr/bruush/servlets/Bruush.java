package fr.bruush.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.bruush.beans.Client;
import fr.bruush.beans.DAOClient;
import fr.bruush.beans.DAOFactory;

@WebServlet("/")
public class Bruush extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOClient daoClient;
	
	public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        daoClient = daoFactory.getDAOClient("JPA");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		String id = request.getParameter("id");
		String code;
		if(id == null) {
			id = "welcome";
		}
		switch(id) {
			case "welcome":
//				request.setAttribute("content", "welcome");
				break;
			case "display":
//				List<Book> catalog = dao.getBooks();
//				request.setAttribute("catalog", catalog);
//				request.setAttribute("content", "display");
				break;
			case "add":
//				String bookAdded = request.getParameter("bookadded");
//				if(bookAdded != null) {
//					if(bookAdded.equals("true")) {
//			        	String title = request.getParameter("title");
//			        	String author = request.getParameter("author");
//			        	String description = request.getParameter("description");
//						daoBook.add(title, author, description);
//					}
//					else {
//						request.getRequestDispatcher("bibliotheque?id=display").forward(request,response);
//					}
//				}
//				request.setAttribute("content", "add");
				break;
			case "edit":
//				code = request.getParameter("code");
//				request.setAttribute("content", "edit");
//				request.setAttribute("book", daoBook.getBook(code));
				break;
			case "delete":
//				daoBook.delete(Integer.parseInt(request.getParameter("code")));
//				request.getRequestDispatcher("bibliotheque?id=display").forward(request,response);
				break;
			case "update":
//				String bookUpdated = request.getParameter("bookupdated");
//				if(bookUpdated != null && bookUpdated.equals("true")) {
//					code = request.getParameter("code");
//		        	String title = request.getParameter("title");
//		        	String author = request.getParameter("author");
//		        	String description = request.getParameter("description");
//		        	daoBook.update(code, title, author, description);
//				}
//				request.getRequestDispatcher("bibliotheque?id=display").forward(request,response);
				break;
			default:
//				request.setAttribute("content", "welcome");
				break;
		}
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}
}
