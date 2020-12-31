package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class Personne {
	
	public static int num = 0;
	private int id = 0 ;
	private String nom;
	
	private String mail;
	private String motDePasse;
	 String url="jdbc:mysql://localhost/proj_exam";
		String login="root"; 
		String password="";
		Connection cn=null;
	
	

	
	Personne(String n , String mail , String mdp) throws NamingException, SQLException {
		this.setNom(n);
		this.setMail(mail);
		this.setMotDePasse(mdp);
		this.id = num;
		num++; 
		
		
	}
	
	

	
	public void EnvoyerMail(Personne P) {
		
	}
	
	
	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getId() {
		return nom;
	}
	
	

}
