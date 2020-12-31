package liaisonappliBDopta;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class compteSecretariat extends Personne{
	
	

	compteSecretariat(String n, String mail, String mdp) throws NamingException, SQLException {
		super(n, mail, mdp); 
		String sqlQuery = "INSERT INTO login (LoginUser, LoginPass,LoginStatus,LoginMail) VALUES ('"+n+"','"+mdp+"',1,'"+mail+"')";
    	Connexion a = new Connexion();
		a.RequeteDB(sqlQuery);
		
	}
	
}
