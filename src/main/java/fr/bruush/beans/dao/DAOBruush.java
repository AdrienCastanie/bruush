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

    /**
     * Get a client by his ID
     *
     * @param id - ID of the client
     * @return a client
     */
    Client getClient(int id);

    /**
     * Get all clients
     *
     * @return All clients
     */
    List<Client> getClients();

    /**
     * Update a client
     *
     * @param id     - The id of the client
     * @param nom    - The nom of the client
     * @param prenom - The prenom of the client
     * @param mail   - The mail of the client
     * @param addr   - The address of the client
     * @param bloque - 1 for bloque 0 for not
     */
    void update(int id, String nom, String prenom, String mail, String addr, int bloque);

    /**
     * Update the client without the mail
     *
     * @param id     - The id of the client
     * @param nom    - The nom of the client
     * @param prenom - The prenom of the client
     * @param addr   - The address of the client
     * @throws ClientUpdateException
     */
    void updateInfos(int id, String nom, String prenom, String addr) throws ClientUpdateException;

    /**
     * Set a client to blocked or not stat
     *
     * @param id     - The ID of the client
     * @param bloque - 1 for bloque 0 for not
     */
    void updateClientBlocked(int id, int bloque);

    /**
     * Delete a client
     *
     * @param id
     */
    void delete(int id);

    /**
     * Delete an article
     *
     * @param idArticle - The ID of the article
     */
    void deleteArticle(int idArticle);

    /**
     * Get a client by mail and mdp
     *
     * @param mail - The mail
     * @param mdp  - The mdp
     * @return A client
     * @throws ClientNotFoundException
     */
    Client getClientByMailAndMdp(String mail, String mdp) throws ClientNotFoundException;

    /**
     * Create a client
     *
     * @param nom    - Nom of the client
     * @param prenom - Prenom of the client
     * @param mail   - Mail of the client
     * @param mdp    - Mdp of the client
     * @return A client
     * @throws ClientCreationException
     */
    Client createClient(String nom, String prenom, String mail, String mdp) throws
            ClientCreationException;

    /**
     * Get all articles
     *
     * @return a list of articles
     */
    List<Article> getArticles();

    /**
     * Create a command
     *
     * @param idClient - Client who make the command
     * @param total    - Total price
     * @param date     - Date, string format
     * @return the command
     * @throws CommandeCreationException
     */
    Commande createCommande(int idClient, int total, String date) throws CommandeCreationException;

    /**
     * Create achat
     *
     * @param idCommande - The commande from whom the achat belongs to
     * @param idArticle  - The article bought
     * @param qte        - The quantity of the article
     * @return The achat
     * @throws CommandeCreationException
     */
    Achat createAchat(int idCommande, int idArticle, int qte) throws CommandeCreationException;

    /**
     * Update an article quantity
     *
     * @param idArticle - The article id
     * @param newQte    - The new quantity
     */
    void changeQteArticle(int idArticle, int newQte);

    /**
     * Get all commands from a client
     *
     * @param idClient - The specific client ID
     * @return A list of commande
     * @throws Exception
     */
    List<Commande> getCommandesByIdClient(int idClient) throws Exception;

    /**
     * Get all achats for a commande ID
     *
     * @param idCommande - The specific commande ID
     * @return A list of achats
     * @throws Exception
     */
    List<Achat> getAchatsByIdCommande(int idCommande) throws Exception;

    /**
     * Get article from id
     *
     * @param id - The article ID
     * @return an article
     */
    Article getArticle(int id);
}
