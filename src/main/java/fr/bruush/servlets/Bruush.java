package fr.bruush.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.bruush.beans.dao.DAOBruush;
import fr.bruush.beans.dao.DAOFactory;
import fr.bruush.beans.objects.Client;
import fr.bruush.beans.objects.Article;
import fr.bruush.exceptions.ClientCreationException;
import fr.bruush.exceptions.ClientNotFoundException;
import fr.bruush.exceptions.ClientUpdateException;

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
			id = "index";
		}
		String nom;
		String prenom;
		String mail;
		String mdp;
		String adresse;
		Client client;
		HttpSession session;
		List<Article> articles = this.daoBruush.getArticles();
		switch(id) {
			case "connexion":
				mail = request.getParameter("email");
				mdp = request.getParameter("mdp");
				try {
					client = this.daoBruush.getClientByMailAndMdp(mail, mdp);
				} catch (ClientNotFoundException e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("/jsp/connexion.jsp").forward(request,response);
					break;
				}

				if (client == null) {
					// Le client n'existe pas
					break;
				}
				session = request.getSession();
				session.setAttribute("nom", client.getNom());
				session.setAttribute("prenom", client.getPrenom());
				if (client.getAddr() == null) {
					session.setAttribute("adresse", "");
				} else {
					session.setAttribute("adresse", client.getAddr());
				}
				session.setAttribute("id", client.getId());
				request.setAttribute("content", "welcome");
				request.getRequestDispatcher("/action?id=index").forward(request,response);
				break;
			case "create_account":
				nom = request.getParameter("nom");
				prenom = request.getParameter("prenom");
				mail = request.getParameter("mail");
				mdp = request.getParameter("mdp");
				try {
					client = this.daoBruush.createClient(nom, prenom, mail, mdp);
				} catch (ClientCreationException e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("/jsp/create_account.jsp").forward(request,response);
					break;
				}
				session = request.getSession();
				session.setAttribute("nom", client.getNom());
				session.setAttribute("prenom", client.getPrenom());
				if (client.getAddr() == null) {
					session.setAttribute("adresse", "");
				} else {
					session.setAttribute("adresse", client.getAddr());
				}
				session.setAttribute("id", client.getId());
				request.getRequestDispatcher("/action?id=index").forward(request,response);

				break;
			case "disconnection":
                session = request.getSession();
                if(session != null)
                    session.invalidate();
				request.getRequestDispatcher("/action?id=index").forward(request,response);

				break;
			case "personal-info":
				String idClient = request.getParameter("idClient");
				prenom = request.getParameter("surname");
				nom = request.getParameter("name");
				adresse = request.getParameter("address");
				try {
					if (idClient == null)
						this.daoBruush.updateInfos(-1, nom, prenom, adresse);
					else
						this.daoBruush.updateInfos(Integer.parseInt(idClient), nom, prenom, adresse);
				} catch (ClientUpdateException e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("/jsp/connexion.jsp").forward(request,response);
					break;
				}
				session = request.getSession();
				session.setAttribute("nom", nom);
				session.setAttribute("prenom", prenom);
				session.setAttribute("adresse", adresse);
				request.getRequestDispatcher("/jsp/profile-user-informations.jsp").forward(request,response);
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
			case "index":
			default:
				request.setAttribute("articles", articles);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
				break;
		}
	}
}
