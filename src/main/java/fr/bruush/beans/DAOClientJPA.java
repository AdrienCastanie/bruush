package fr.bruush.beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DAOClientJPA implements DAOClient {
	
	private EntityManagerFactory emf;
	
	public DAOClientJPA(EntityManagerFactory emf) {
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
}
