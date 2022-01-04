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

@WebServlet("/action")
public class Bruush extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private DAOBruush daoBruush;

	public void init() throws ServletException {
		this.daoFactory = DAOFactory.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		daoBruush = daoFactory.getDAOClient("JPA");
		String id = request.getParameter("id");
		if(id == null) {
			id = "index";
		}
		String nom;
		String prenom;
		String mail;
		String mdp;
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
				session.setAttribute("id", client.getId());
				request.getRequestDispatcher("/action?id=index").forward(request,response);

				break;
			case "disconnection":
                session = request.getSession();
                if(session != null)
                    session.invalidate();
				request.getRequestDispatcher("/action?id=index").forward(request,response);

				break;
			case "cart":
				request.setAttribute("articles", articles);
				request.getRequestDispatcher("/jsp/cart.jsp").forward(request,response);
        break;
			case "admin_clients":
        String id_client = request.getParameter("id_client");
        String blocked = request.getParameter("blocked");
        if(id_client != null && blocked != null) //Cela signifie que l'on a cliqué pour bloqué/débloqué un client
        {
            this.daoBruush.updateClientBlocked(Integer.parseInt(id_client), (blocked.equals("true"))? 1 : 0);
        }
        List<Client> listClients = this.daoBruush.getClients();
        request.setAttribute("clients", listClients);
        request.getRequestDispatcher("/jsp/profile-admin-users.jsp").forward(request, response);
				break;
			case "buy":
				String[] panier = request.getParameterValues("panier");
				for (int i = 0; i < panier.length; i++) {
					System.out.println(panier[i]);
				}
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
		daoFactory.getEmf().close();
	}
}
