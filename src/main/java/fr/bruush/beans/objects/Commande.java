package fr.bruush.beans.objects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="COMMANDE")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande", nullable = false)
    private int id;

    @Column(name="id_client")
    private int idClient;

    @Column(name="total")
    private int total;

    @Column(name="date")
    private String date;

    public Commande() {}

    public Commande(int idClient, int total, String date) {
        this.idClient = idClient;
        this.total = total;
        this.date = date;
    }

    public Commande(int id, int idClient, int total, String date) {
        this(idClient, total, date);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
