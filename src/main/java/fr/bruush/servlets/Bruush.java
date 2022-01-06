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

    private DAOBruush daoBruush;

    /**
     * Init the servlet
     *
     * @throws ServletException
     */
    public void init() throws ServletException {
        this.daoBruush = DAOFactory.getInstance().getDAOClient("JPA");
    }

    /**
     * Called when there is a HTTP GET
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        processRequest(request, response);
    }

    /**
     * Called when there is a HTTP POST
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        processRequest(request, response);
    }

    /**
     * Request handler for both GET & POST
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        String id = request.getParameter("id");
        if (id == null) {
            id = "index";
        }
        List<Article> articles = this.daoBruush.getArticles();
        switch (id) {
            case "connexion":
                this.processConnexion(request, response);
                break;
            case "create_account":
                this.processCreateAccount(request, response);
                break;
            case "disconnection":
                this.removeSession(request);
                request.getRequestDispatcher("/action?id=index").forward(request, response);
                break;
            case "personal-info":
                this.processPersonalInfo(request, response);
                break;
            case "cart":
                request.setAttribute("articles", articles);
                request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
                break;
            case "admin_clients":
                this.processAdminClients(request, response);
                break;
            case "admin_products":
                this.processAdminProducts(request, response);
                break;
            case "buy":
                this.processBuy(request, response);
                break;
            case "history":
                this.processHistory(request, response);
                break;
            case "index":
            default:
                request.setAttribute("articles", articles);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }

    public void processConnexion(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        String mail = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        Client client = null;
        try {
            client = this.daoBruush.getClientByMailAndMdp(mail, mdp);
        } catch (ClientNotFoundException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("/jsp/connexion.jsp").forward(request, response);
            return;
        }
        if (client == null) {
            // Le client n'existe pas
            return;
        }
        this.getSession(request, client);
        request.getRequestDispatcher("/action?id=index").forward(request, response);
    }

    public void processCreateAccount(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        Client client = null;
        try {
            client = this.daoBruush.createClient(nom, prenom, mail, mdp);
        } catch (ClientCreationException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("/jsp/create_account.jsp").forward(request, response);
            return;
        }
        this.getSession(request, client);
        request.getRequestDispatcher("/action?id=index").forward(request, response);
    }

    public void processPersonalInfo(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        String idClient = request.getParameter("idClient");
        String prenom = request.getParameter("surname");
        String nom = request.getParameter("name");
        String adresse = request.getParameter("address");
        if (idClient != null) {
            try {
                this.daoBruush.updateInfos(Integer.parseInt(idClient), nom, prenom, adresse);
            } catch (ClientUpdateException e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher("/jsp/connexion.jsp").forward(request, response);
                return;
            }
        }
        Client client = this.daoBruush.getClient(this.getSessionID(request));
        this.getSession(request, client);
        request.getRequestDispatcher("/jsp/profile-user-informations.jsp").forward(request, response);
    }

    public void processAdminClients(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        String id_client = request.getParameter("id_client");
        String blocked = request.getParameter("blocked");
        if (id_client != null && blocked != null) {
            //Cela signifie que l'on a cliqué pour bloqué/débloqué un client
            this.daoBruush.updateClientBlocked(Integer.parseInt(id_client), (blocked.equals("true")) ? 1 : 0);
        }
        List<Client> listClients = this.daoBruush.getClients();
        request.setAttribute("clients", listClients);
        request.getRequestDispatcher("/jsp/profile-admin-users.jsp").forward(request, response);
    }

    public void processAdminProducts(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        String idArticle = request.getParameter("id_article");
        String newQte = request.getParameter("new_qte");
        String nomArticle = request.getParameter("nom");
        if (idArticle != null && newQte == null) {
            //Cela signifie que l'on a cliqué pour supprimer un article
            this.daoBruush.deleteArticle(Integer.parseInt(idArticle));
        } else if (idArticle != null && newQte != null) {
            //Cela signifie que l'on change la quantité d'un article en stock
            this.daoBruush.changeQteArticle(Integer.parseInt(idArticle), Integer.parseInt(newQte));
        } else if (nomArticle != null) {
            String descriptionArticle = request.getParameter("description");
            String imageArticle = request.getParameter("image");
            String prixArticle = request.getParameter("prix");
            String stockArticle = request.getParameter("stock");
            this.daoBruush.createArticle(nomArticle,
                                         descriptionArticle,
                                         imageArticle,
                                         Integer.parseInt(prixArticle),
                                         Integer.parseInt(stockArticle));
        }
        List<Article> listArticles = this.daoBruush.getArticles();
        request.setAttribute("articles", listArticles);
        request.getRequestDispatcher("/jsp/profile-admin-products.jsp").forward(request, response);
    }

    public void processBuy(HttpServletRequest request, HttpServletResponse response) {
        String[] panier = request.getParameterValues("panier");
        int total = Integer.parseInt(request.getParameter("total"));
        try {
            Commande commande = this.daoBruush.createCommande(
                    this.getSessionID(request), total, new Date().toString());
            for (int i = 0; i < panier.length; i++) {
                String[] values = panier[i].split("-");
                this.daoBruush.createAchat(commande.getId(),
                                           Integer.parseInt(values[0]),
                                           Integer.parseInt(values[1]));
            }
        } catch (CommandeCreationException e) {
            System.out.println(e);
        }
    }

    public void processHistory(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        HashMap historiqueArticle = new HashMap<Commande, List<Article>>();
        HashMap historiqueAchat = new HashMap<Commande, List<Achat>>();
        try {
            List<Commande> commandes = this.daoBruush.getCommandesByIdClient(this.getSessionID(request));
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
        this.getSession(request, this.daoBruush.getClient(this.getSessionID(request)));
        request.setAttribute("historiqueArticle", historiqueArticle);
        request.setAttribute("historiqueAchat", historiqueAchat);
        request.getRequestDispatcher("/jsp/profile-command-history.jsp").forward(request, response);
    }

    public HttpSession getSession(HttpServletRequest r, Client c) {
        HttpSession session = r.getSession();
        session.setAttribute("id", c.getId());
        session.setAttribute("nom", c.getNom());
        session.setAttribute("prenom", c.getPrenom());
        if (c.getAddr() == null) {
            session.setAttribute("adresse", "");
        } else {
            session.setAttribute("adresse", c.getAddr());
        }
        session.setAttribute("bloque", c.getBloque());
        session.setAttribute("admin", c.getAdmin());
        return session;
    }

    public void removeSession(HttpServletRequest r) {
        HttpSession session = r.getSession();
        if (session != null)
            session.invalidate();
    }

    public int getSessionID(HttpServletRequest r) {
        HttpSession session = r.getSession();
        return (int) session.getAttribute("id");
    }
}
