package fr.bruush.servlets;

import fr.bruush.beans.dao.DAOBruush;
import fr.bruush.beans.dao.DAOFactory;
import fr.bruush.beans.objects.Achat;
import fr.bruush.beans.objects.Article;
import fr.bruush.beans.objects.Client;
import fr.bruush.beans.objects.Commande;
import fr.bruush.exceptions.ClientCreationException;
import fr.bruush.exceptions.ClientNotFoundException;
import fr.bruush.exceptions.ClientUpdateException;
import fr.bruush.exceptions.CommandeCreationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/action")
public class Bruush extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DAOFactory daoFactory;
    private DAOBruush daoBruush;

    public void init() throws ServletException {
        this.daoFactory = DAOFactory.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        daoBruush = daoFactory.getDAOClient("JPA");
        String id = request.getParameter("id");
        if (id == null) {
            id = "index";
        }
        String nom;
        String prenom;
        String mail;
        String mdp;
        String adresse;
        Client client;
        HttpSession session = null;
        List<Article> articles = this.daoBruush.getArticles();
        switch (id) {
            case "connexion":
                mail = request.getParameter("email");
                mdp = request.getParameter("mdp");
                try {
                    client = this.daoBruush.getClientByMailAndMdp(mail, mdp);
                } catch (ClientNotFoundException e) {
                    request.setAttribute("error", e);
                    request.getRequestDispatcher("/jsp/connexion.jsp").forward(request, response);
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
                session.setAttribute("bloque", client.getBloque());
                request.setAttribute("content", "welcome");
                request.getRequestDispatcher("/action?id=index").forward(request, response);
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
                    request.getRequestDispatcher("/jsp/create_account.jsp").forward(request, response);
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
                session.setAttribute("bloque", client.getBloque());
                request.getRequestDispatcher("/action?id=index").forward(request, response);

                break;
            case "disconnection":
                session = request.getSession();
                if (session != null)
                    session.invalidate();
                request.getRequestDispatcher("/action?id=index").forward(request, response);
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
                    request.getRequestDispatcher("/jsp/connexion.jsp").forward(request, response);
                    break;
                }
                session = request.getSession();
                session.setAttribute("nom", nom);
                session.setAttribute("prenom", prenom);
                session.setAttribute("adresse", adresse);
                request.getRequestDispatcher("/jsp/profile-user-informations.jsp").forward(request, response);
                break;
            case "cart":
                request.setAttribute("articles", articles);
                request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
                break;
            case "admin_clients":
                String id_client = request.getParameter("id_client");
                String blocked = request.getParameter("blocked");
                if (id_client != null && blocked != null) //Cela signifie que l'on a cliqué pour bloqué/débloqué un client
                {
                    this.daoBruush.updateClientBlocked(Integer.parseInt(id_client), (blocked.equals("true")) ? 1 : 0);
                }
                List<Client> listClients = this.daoBruush.getClients();
                request.setAttribute("clients", listClients);
                request.getRequestDispatcher("/jsp/profile-admin-users.jsp").forward(request, response);
                break;
            case "admin_products":
                String idArticle = request.getParameter("id_article");
                String newQte = request.getParameter("new_qte");
                if (idArticle != null && newQte == null) //Cela signifie que l'on a cliqué pour supprimer un article
                {
                    this.daoBruush.deleteArticle(Integer.parseInt(idArticle));
                } else if (idArticle != null && newQte != null) //Cela signifie que l'on change la quantité d'un article en stock
                {
                    this.daoBruush.changeQteArticle(Integer.parseInt(idArticle), Integer.parseInt(newQte));
                }
                List<Article> listArticles = this.daoBruush.getArticles();
                request.setAttribute("articles", listArticles);
                request.getRequestDispatcher("/jsp/profile-admin-products.jsp").forward(request, response);
                break;
            case "buy":
                String[] panier = request.getParameterValues("panier");
                int total = Integer.parseInt(request.getParameter("total"));
                session = request.getSession();
                try {
                    Commande commande = this.daoBruush.createCommande(
                            (int) session.getAttribute("id"), total, new Date().toString());
                    for (int i = 0; i < panier.length; i++) {
                        String[] values = panier[i].split("-");
                        this.daoBruush.createAchat(commande.getId(),
                                                   Integer.parseInt(values[0]),
                                                   Integer.parseInt(values[1]));
                    }
                } catch (CommandeCreationException e) {
                    System.out.println(e);
                }
                break;
            case "history":
                session = request.getSession();
                HashMap historiqueArticle = new HashMap<Commande, List<Article>>();
                HashMap historiqueAchat = new HashMap<Commande, List<Achat>>();
                try {
                    List<Commande> commandes = this.daoBruush.getCommandesByIdClient(
                            (int) session.getAttribute("id"));
                    for (Commande c : commandes) {
                        List<Achat> achatsCommande = this.daoBruush.getAchatsByIdCommande(c.getId());
                        List<Article> articlesCommande = new ArrayList<>();
                        for (Achat a : achatsCommande) {
                            articlesCommande.add(this.daoBruush.getArticle(a.getIdArticle()));
                        }
                        historiqueAchat.put(c, achatsCommande);
                        historiqueArticle.put(c, articlesCommande);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("historiqueArticle", historiqueArticle);
                request.setAttribute("historiqueAchat", historiqueAchat);
                request.getRequestDispatcher("/jsp/profile-command-history.jsp").forward(request, response);
                break;
            case "index":
            default:
                request.setAttribute("articles", articles);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
        // daoFactory.getEmf().close();
    }
}
