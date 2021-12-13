package fr.bruush.beans.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id_client", columnDefinition = "integer")
	private int id;
	
	@Column(name="nom", columnDefinition = "varchar(50)")
	private String nom;
	
	@Column(name="prenom", columnDefinition = "varchar(50)")
	private String prenom;
	
	@Column(name="mail", columnDefinition = "varchar(50)")
	private String mail;

	@Column(name="mdp", columnDefinition = "varchar(50)")
	private String mdp;

	@Column(name="addr", columnDefinition = "varchar(100) default null")
	private String addr;

	@Column(name="bloque", columnDefinition = "integer(1) default 0")
	private int bloque;
	
	public Client() {}

	public Client(String nom, String prenom, String mail, String mdp) {
		this(nom, prenom, mail, null, 0);
		this.mdp = mdp;
	}

	public Client(String nom, String prenom, String mail, String addr, int bloque) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.addr = addr;
		this.bloque = bloque;
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
}
