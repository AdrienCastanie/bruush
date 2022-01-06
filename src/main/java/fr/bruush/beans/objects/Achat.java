package fr.bruush.beans.objects;

import javax.persistence.*;

@Entity
@Table(name="ACHAT")
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_achat")
    private int id;

    @Column(name="id_commande")
    private int idCommande;

    @Column(name="id_article")
    private int idArticle;

    @Column(name="qte")
    private int qte;

    public Achat() {}

    public Achat(int idCommande, int idArticle, int qte) {
        this.idCommande = idCommande;
        this.idArticle = idArticle;
        this.qte = qte;
    }

    public Achat(int id, int idCommande, int idArticle, int qte) {
        this(idCommande, idArticle, qte);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
