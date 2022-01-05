package fr.bruush.beans.dao;

import fr.bruush.beans.objects.Article;
import fr.bruush.beans.objects.Client;
import fr.bruush.exceptions.ClientCreationException;
import fr.bruush.exceptions.ClientNotFoundException;
import fr.bruush.exceptions.ClientUpdateException;

import java.util.List;

public interface DAOBruush {
	
    public abstract void add(String nom, String prenom, String mail, String addr, int bloque);
	
    public abstract Client getClient(int id);
	
    public abstract List<Client> getClients();

    public void update(int id, String nom, String prenom, String mail, String addr, int bloque);

    public void updateInfos(int id, String nom, String prenom, String addr) throws ClientUpdateException;

    public void updateClientBlocked(int id, int bloque);

    public abstract void delete(int id);

    public abstract void deleteArticle(int idArticle);

    public abstract Client getClientByMailAndMdp(String mail, String mdp) throws ClientNotFoundException;

    public abstract Client createClient(String nom, String prenom, String mail, String mdp) throws ClientCreationException;

    public abstract List<Article> getArticles();
}
