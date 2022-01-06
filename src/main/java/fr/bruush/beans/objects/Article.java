package fr.bruush.beans.objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ARTICLE")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_article", columnDefinition = "integer")
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="prix")
    private int prix;

    @Column(name="stock")
    private int stock;

    @Column(name="description")
    private String description;

    @Column(name="img")
    private String img;

    public Article() {}

    public Article(String nom, int prix, int stock, String description, String img) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.description = description;
        this.img = img;
    }

    public Article(int id, String nom, int prix, int stock, String description, String img) {
        this(nom, prix, stock, description, img);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
