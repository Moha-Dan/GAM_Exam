package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;


public class Etudiant {
	
	public static int num = 0;
	private int id = 0 ;
	private int numeroetu;
	private String nom;
	private String prenom;
	private String promo;
	private int annee;
	

	
	
	Etudiant(int numero , String nom, String prenom, String filliere ,int annee) throws NamingException, SQLException {
		this.nom = nom;
		this.numeroetu = numero;
		this.prenom = prenom;
		this.promo = filliere;
		this.annee = annee;
		this.id = num;
		num ++;
		
		Connexion a = new Connexion();
		String sqlQuery = "INSERT INTO eleves(EleveNom, ElevePrenom, EleveNum, Promo, Annee) VALUES ('"+nom+"','"+prenom+"',"+numeroetu+",'"+promo+"',"+annee+")";
		a.RequeteDB(sqlQuery);
		
	}


	public int getId() {
		return id;
	}


	

	public int getNumeroetu() {
		return numeroetu;
	}


	public void setNumeroetu(int numeroetu) {
		this.numeroetu = numeroetu;
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


	public String getPromo() {
		return promo;
	}


	public void setPromo(String promo) {
		this.promo = promo;
	}
}
