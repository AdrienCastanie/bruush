package fr.bruush.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.bruush.beans.dao.DAOBruush;
import fr.bruush.beans.dao.DAOFactory;
import fr.bruush.beans.objects.Client;

@WebServlet("/action")
public class Bruush extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOBruush daoBruush;
	
	public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        daoBruush = daoFactory.getDAOClient("JPA");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id == null) {
			id = "welcome";
		}
		String nom;
		String prenom;
		String mail;
		String mdp;
		Client client;
		HttpSession session;
		switch(id) {
			case "connexion":
				mail = request.getParameter("email");
				mdp = request.getParameter("mdp");
				client = this.daoBruush.getClientByMailAndMdp(mail, mdp);
				if (client == null) {
					// Le client n'existe pas
					break;
				}
				session = request.getSession();
				session.setAttribute("nom", client.getNom());
				session.setAttribute("prenom", client.getPrenom());
				session.setAttribute("id", client.getId());
				request.setAttribute("content", "welcome");
				break;
			case "create_account":
				nom = request.getParameter("nom");
				prenom = request.getParameter("prenom");
				mail = request.getParameter("mail");
				mdp = request.getParameter("mdp");
				client = this.daoBruush.createClient(nom, prenom, mail, mdp);
				if (client == null) {
					// Le client n'existe pas
					break;
				}
				session = request.getSession();
				session.setAttribute("nom", client.getNom());
				session.setAttribute("prenom", client.getPrenom());
				session.setAttribute("id", client.getId());
				request.setAttribute("content", "welcome");
				break;
			case "disconnection":
                session = request.getSession();
                if(session != null)
                    session.invalidate();
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
