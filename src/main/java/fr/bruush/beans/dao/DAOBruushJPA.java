package fr.bruush.beans.dao;

import fr.bruush.beans.objects.Article;
import fr.bruush.beans.objects.Client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class DAOBruushJPA implements DAOBruush {
	
	private EntityManagerFactory emf;
	
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
		}
		finally {
		  entityManager.close();
		}
	}
	
	@Override
    public Client getClient(int id) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		try {
			return entityManager.find(Client.class, id);
		}
		finally {
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
		}
		finally {
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
		}
		finally {
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
		}
		finally {
		  entityManager.close();
		}
	}

	@Override
	public Client getClientByMailAndMdp(String mail, String mdp) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		try {
			Query q = entityManager.createNativeQuery(
					"SELECT * FROM CLIENT WHERE mail = :mail AND mdp = :mdp",
					Client.class);
			q.setParameter("mail", mail);
			q.setParameter("mdp", mdp);
			return (Client)q.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Client createClient(String nom, String prenom, String mail, String mdp) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		try {
			Client c = new Client(nom, prenom, mail, mdp);
			entityManager.persist(c);
			return c;
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
		}
		finally {
			entityManager.close();
		}
		return catalog;
	}
}
