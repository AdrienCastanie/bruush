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
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE CLIENT SET nom=?, prenom=?, addr=? WHERE id=?;");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(4, addr);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO CLIENT(nom, prenom, mail, mdp, addr) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, mdp);
            preparedStatement.setString(5, "");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public Article createArticle(String nomArticle,
                                 String description,
                                 String imageArticle,
                                 int prixArticle,
                                 int stockArticle) {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO Article(nom, prix, stock, description, image) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, nomArticle);
            preparedStatement.setInt(2, prixArticle);
            preparedStatement.setInt(3, stockArticle);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, imageArticle);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Commande createCommande(int idClient, int total, String date) throws CommandeCreationException {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO Commande(id_client, total, date) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, total);
            preparedStatement.setString(3, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Achat createAchat(int idCommande, int idArticle, int qte) throws CommandeCreationException {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO Achat(id_commande, id_article, qte) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, idCommande);
            preparedStatement.setInt(2, idArticle);
            preparedStatement.setInt(3, qte);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        List<Commande> commandes = new ArrayList<>();
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Commandes WHERE id_client=?;");
            statement.setInt(1, idClient);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int id_client = result.getInt("id_client");
                int total = result.getInt("total");
                String date = result.getString("date");
                commandes.add(new Commande(id, id_client, total, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    @Override
    public List<Achat> getAchatsByIdCommande(int idCommande) throws Exception {
        List<Achat> achats = new ArrayList<>();
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Achats WHERE id_commande=?;");
            statement.setInt(1, idCommande);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int id_commande = result.getInt("id_commande");
                int id_article = result.getInt("id_article");
                int qte = result.getInt("qte");
                achats.add(new Achat(id, id_commande, id_article, qte));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achats;
    }

    @Override
    public Article getArticle(int idArticle) {
        Article a = null;
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Article WHERE id_article=?;");
            statement.setInt(1, idArticle);
            ResultSet result = statement.executeQuery();
            result.next();
            int id = result.getInt("id");
            String nom = result.getString("nom");
            int prix = result.getInt("prix");
            int stock = result.getInt("stock");
            String description = result.getString("description");
            String img = result.getString("img");
            a = new Article(id, nom, prix, stock, description, img);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}
