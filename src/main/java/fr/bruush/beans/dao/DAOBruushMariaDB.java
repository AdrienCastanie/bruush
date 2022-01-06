package fr.bruush.beans.dao;

import fr.bruush.beans.objects.Achat;
import fr.bruush.beans.objects.Article;
import fr.bruush.beans.objects.Client;
import fr.bruush.beans.objects.Commande;
import fr.bruush.exceptions.CommandeCreationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOBruushMariaDB implements DAOBruush {

    private final DAOFactory daoFactory;

    DAOBruushMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(String nom, String prenom, String mail, String addr, int bloque) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO CLIENT(nom, prenom, mail, addr, bloque) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, addr);
            preparedStatement.setInt(5, bloque);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getClient(int id) {
        Client client = null;
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM CLIENT WHERE id=?;");
            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                result.next();
                int code = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String mail = result.getString("mail");
                String addr = result.getString("addr");
                int bloque = result.getInt("bloque");
                client = new Client(code, nom, prenom, mail, addr, bloque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM CLIENT;")) {
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String mail = result.getString("mail");
                String addr = result.getString("addr");
                int bloque = result.getInt("bloque");
                clients.add(new Client(id, nom, prenom, mail, addr, bloque));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void update(int id, String nom, String prenom, String mail, String addr, int bloque) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE CLIENT SET nom=?, prenom=?, mail=?, addr=?, bloque=? WHERE id=?;");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, addr);
            preparedStatement.setInt(5, bloque);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClientBlocked(int id, int bloque) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE CLIENT SET bloque=? WHERE id=?;");
            preparedStatement.setInt(1, bloque);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInfos(int id, String nom, String prenom, String addr) {
        System.out.println("update");
    }

    @Override
    public void delete(int id) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "DELETE FROM CLIENT WHERE id=?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(int idArticle) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "DELETE FROM ARTICLE WHERE id=?;");
            preparedStatement.setInt(1, idArticle);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getClientByMailAndMdp(String mail, String mdp) {
        return null;
    }

    @Override
    public Client createClient(String nom, String prenom, String mail, String mdp) {
        return null;
    }

    @Override
    public List<Article> getArticles() {
        List<Article> articles = new ArrayList<>();
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM ARTICLE;")) {
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                int prix = result.getInt("prix");
                int stock = result.getInt("stock");
                String description = result.getString("description");
                String img = result.getString("img");
                articles.add(new Article(id, nom, prix, stock, description, img));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
    @Override
    public Article createArticle(String nomArticle,String description, String imageArticle, int prixArticle, int stockArticle) {
        return null;
    }

    @Override
    public Commande createCommande(int idClient, int total, String date) throws CommandeCreationException {
        return null;
    }

    @Override
    public Achat createAchat(int idCommande, int idArticle, int qte) throws CommandeCreationException {
        return null;
    }

    @Override
    public void changeQteArticle(int id, int newQte) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE ARTICLE SET stock=? WHERE id=?;");
            preparedStatement.setInt(1, newQte);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commande> getCommandesByIdClient(int idClient) throws Exception {
        return null;
    }

    @Override
    public List<Achat> getAchatsByIdCommande(int idCommande) throws Exception {
        return null;
    }

    @Override
    public Article getArticle(int id) {
        return null;
    }
}
