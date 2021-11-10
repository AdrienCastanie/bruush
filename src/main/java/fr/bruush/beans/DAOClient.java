package fr.bruush.beans;

import java.util.List;

public interface DAOClient {
	
    public abstract void add(String nom, String prenom, String mail, String addr, int bloque);
	
    public abstract Client getClient(int id);
	
    public abstract List<Client> getClients();

    public void update(int id, String nom, String prenom, String mail, String addr, int bloque);

    public abstract void delete(int id);

}
