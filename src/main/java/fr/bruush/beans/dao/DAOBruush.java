package fr.bruush.beans.dao;

import fr.bruush.beans.objects.Client;
import fr.bruush.exceptions.ClientCreationException;
import fr.bruush.exceptions.ClientNotFoundException;

import java.util.List;

public interface DAOBruush {
	
    public abstract void add(String nom, String prenom, String mail, String addr, int bloque);
	
    public abstract Client getClient(int id);
	
    public abstract List<Client> getClients();

    public void update(int id, String nom, String prenom, String mail, String addr, int bloque);

    public abstract void delete(int id);

    public abstract Client getClientByMailAndMdp(String mail, String mdp);

    public abstract Client createClient(String nom, String prenom, String mail, String mdp) throws ClientCreationException;

}
