package fr.bruush.beans.dao;

import fr.bruush.beans.objects.Achat;
import fr.bruush.beans.objects.Article;
import fr.bruush.beans.objects.Client;
import fr.bruush.beans.objects.Commande;
import fr.bruush.exceptions.ClientCreationException;
import fr.bruush.exceptions.ClientNotFoundException;
import fr.bruush.exceptions.ClientUpdateException;
import fr.bruush.exceptions.CommandeCreationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class DAOBruushJPA implements DAOBruush {

    private final EntityManagerFactory emf;

    public DAOBruushJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(String nom, String prenom, String mail, String addr, int bloque) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(new Client(nom, prenom, mail, addr, bloque));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Client getClient(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            return entityManager.find(Client.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> catalog;
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            catalog = entityManager.createQuery("from Client", Client.class).getResultList();
        } finally {
            entityManager.close();
        }
        return catalog;
    }

    @Override
    public void update(int id, String nom, String prenom, String mail, String addr, int bloque) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Client client = new Client(id, nom, prenom, mail, addr, bloque);
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateInfos(int id, String nom, String prenom, String addr) throws ClientUpdateException {
        if (id == -1)
            throw new ClientUpdateException("Vous devez vous connecter.");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Client client = entityManager.find(Client.class, id);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setAddr(addr);
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateClientBlocked(int id, int bloque) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Client client = entityManager.find(Client.class, id);
            client.setBloque(bloque);
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Client client = entityManager.find(Client.class, id);
            entityManager.remove(client);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteArticle(int idArticle) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Article article = entityManager.find(Article.class, idArticle);
            entityManager.remove(article);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Client getClientByMailAndMdp(String mail, String mdp) throws ClientNotFoundException {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Query q = entityManager.createNativeQuery(
                    "SELECT * FROM CLIENT WHERE mail = :mail AND mdp = :mdp",
                    Client.class);
            q.setParameter("mail", mail);
            q.setParameter("mdp", mdp);
            return (Client) q.getSingleResult();
        } catch (Exception e) {
            throw new ClientNotFoundException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Transactional
    @Override
    public Client createClient(String nom, String prenom, String mail, String mdp) throws ClientCreationException {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Client c = new Client(nom, prenom, mail, mdp);
            transaction.begin();
            entityManager.persist(c);
            transaction.commit();
            return c;
        } catch (Exception e) {
            transaction.rollback();
            throw new ClientCreationException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Article> getArticles() {
        List<Article> catalog;
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            catalog = entityManager.createQuery("from Article", Article.class).getResultList();
        } finally {
            entityManager.close();
        }
        return catalog;
    }

    @Override
    public Commande createCommande(int idClient, int total, String date) throws CommandeCreationException {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Commande c = new Commande(idClient, total, date);
            transaction.begin();
            entityManager.persist(c);
            transaction.commit();
            return c;
        } catch (Exception e) {
            transaction.rollback();
            throw new CommandeCreationException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Achat createAchat(int idCommande, int idArticle, int qte) throws CommandeCreationException {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Achat a = new Achat(idCommande, idArticle, qte);
            transaction.begin();
            entityManager.persist(a);
            transaction.commit();
            return a;
        } catch (Exception e) {
            transaction.rollback();
            throw new CommandeCreationException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void changeQteArticle(int id, int newQte) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Article article = entityManager.find(Article.class, id);
            article.setStock(newQte);
            entityManager.merge(article);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Commande> getCommandesByIdClient(int idClient) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Query q = entityManager.createNativeQuery(
                    "SELECT * FROM COMMANDE WHERE id_client = :idClient",
                    Commande.class);
            q.setParameter("idClient", idClient);
            return (List<Commande>) q.getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Achat> getAchatsByIdCommande(int idCommande) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Query q = entityManager.createNativeQuery(
                    "SELECT * FROM ACHAT WHERE id_commande = :idCommande",
                    Achat.class);
            q.setParameter("idCommande", idCommande);
            return (List<Achat>) q.getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Article getArticle(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            return entityManager.find(Article.class, id);
        } finally {
            entityManager.close();
        }
    }
}
