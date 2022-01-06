package fr.bruush.beans.objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", columnDefinition = "integer")
    private int id;

    @Column(name = "nom", columnDefinition = "varchar(50)")
    private String nom;

    @Column(name = "prenom", columnDefinition = "varchar(50)")
    private String prenom;

    @Column(name = "mail", columnDefinition = "varchar(50)")
    private String mail;

    @Column(name = "mdp", columnDefinition = "varchar(50)")
    private String mdp;

    @Column(name = "addr", columnDefinition = "varchar(100) default null")
    private String addr;

    @Column(name = "bloque", columnDefinition = "integer(1) default 0")
    private int bloque;

    @Column(name = "admin", columnDefinition = "integer(1) default 0")
    private int admin;

    public Client() {
    }

    public Client(String nom, String prenom, String mail, String mdp) {
        this(nom, prenom, mail, null, 0);
        this.mdp = mdp;
    }

    public Client(String nom, String prenom, String mail, String addr, int bloque, int admin) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.addr = addr;
        this.bloque = bloque;
        this.admin = admin;
    }

    public Client(String nom, String prenom, String mail, String addr, int bloque) {
        this(nom, prenom, mail, addr, bloque, 0);
    }

    public Client(int id, String nom, String prenom, String mail, String addr, int bloque) {
        this(nom, prenom, mail, addr, bloque);
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdr) {
        this.mdp = mdp;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + ", mail='" + mail + '\'' + ", mdp='" + mdp + '\'' + ", addr='" + addr + '\'' + ", bloque=" + bloque + '}';
    }
}
