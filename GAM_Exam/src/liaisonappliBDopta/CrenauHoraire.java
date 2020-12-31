package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrenauHoraire {

	private String date;
	public static int num = 0;
	private int id = 0 ;
    private int duree; 
    private String heure;

    String url="jdbc:mysql://localhost/proj_exam";
	String login="root"; 
	String password="";
	Connection cn=null;
    
	CrenauHoraire(String date,String heure,int duree) throws NamingException, SQLException{
    	this.date = date;
    	this.duree = duree;
    	this.setHeure(heure);
    	this.id = num;
    	num++;
    	
    	String sqlQuery = "INSERT INTO creneaux(CreneauxDT, CreneauxLength) VALUES ('"+this.formate()+"',"+duree+")";
    	Connexion a = new Connexion();
		a.RequeteDB(sqlQuery);
    
    }
    
    public String formate() {
    	
    	 //Entrée du fichier exam
  
        String[] DATE_split = date.split(":"); //On split la date depuis le charactère ":"
    	String s = DATE_split[2]+"-"+DATE_split[1]+"-"+DATE_split[0]+" "+ heure;
    	
    	return s;
    	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		CrenauHoraire.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}
}
