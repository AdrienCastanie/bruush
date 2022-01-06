package fr.bruush.beans.dao;

import fr.bruush.beans.objects.Achat;
import fr.bruush.beans.objects.Article;
import fr.bruush.beans.objects.Client;
import fr.bruush.beans.objects.Commande;
import fr.bruush.exceptions.ClientCreationException;
import fr.bruush.exceptions.ClientNotFoundException;
import fr.bruush.exceptions.ClientUpdateException;
import fr.bruush.exceptions.CommandeCreationException;

import java.util.List;

public interface DAOBruush {

    void add(String nom, String prenom, String mail, String addr, int bloque);

    Client getClient(int id);

    List<Client> getClients();

    void update(int id, String nom, String prenom, String mail, String addr, int bloque);

    void updateInfos(int id, String nom, String prenom, String addr) throws ClientUpdateException;

    void updateClientBlocked(int id, int bloque);

    void delete(int id);

    void deleteArticle(int idArticle);
    public abstract Article createArticle(String nomArticle,String description, String imageArticle, int prixArticle, int stockArticle);

    public abstract List<Article> getArticles();

    Client getClientByMailAndMdp(String mail, String mdp) throws ClientNotFoundException;

    Client createClient(String nom, String prenom, String mail, String mdp) throws
            ClientCreationException;

    List<Article> getArticles();

    Commande createCommande(int idClient, int total, String date) throws CommandeCreationException;

    Achat createAchat(int idCommande, int idArticle, int qte) throws CommandeCreationException;

    void changeQteArticle(int idArticle, int newQte);

    List<Commande> getCommandesByIdClient(int idClient) throws Exception;

    List<Achat> getAchatsByIdCommande(int idCommande) throws Exception;

    Article getArticle(int id);
}
