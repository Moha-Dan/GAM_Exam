package liaisonappliBDopta;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class Examen {

	public static int num = 0;
	private int id = 0 ;
	private String nom;
	
	private String matiere;
	private int duree;
	private int type;
	private String materiel;
	private List<Etudiant> etudiants;
	private List<ContrainteExam> contraintesExam;
	private List<ContrainteSalle> contraintesSalle;
	
	
	String url="jdbc:mysql://localhost/proj_exam"; 
	String login="root";
	//String password="root"; 
	String password=""; // par défaut

	// Creation d'une connexion avec DriverManager
	Connection cn=null;
	

	Examen(String n , String mat ,int duree , int type , String materiel ) throws NamingException, SQLException {
		this.nom = n;
		this.matiere = mat;
		this.duree = duree;
		this.type = type;
		this.materiel = materiel;
		this.id = num;
		this.etudiants = new ArrayList();
		this.contraintesExam = new ArrayList();
		this.contraintesSalle = new ArrayList();
		num++;
		

		
		String requete = "INSERT INTO examen(ExamenTitre, ExamenLength) VALUES ('"+n+"',"+duree+")";
		Connexion a = new Connexion();
		a.RequeteDB(requete);
	}
	
	
	public void addEtudiant(Etudiant Etu) {
		etudiants.add(Etu);
		String sqlQuery = "INSERT INTO contraintes(ExamID,ContrainteType,ContrainteArgument) VALUES ("+id+","+4+","+Etu.getId()+")";
		Connexion c = new Connexion();
		c.RequeteDB(sqlQuery);
	}
	
	public void addListEtudiant(List<Etudiant> E) {
		for (Etudiant etu : E) {
			this.addEtudiant(etu); 
		}
	}
	public void addContrainteExam(ContrainteExam c) {
		contraintesExam.add(c);
		String sqlQuery = "INSERT INTO contraintes(ExamID,ContrainteType,ContrainteArgument) VALUES ("+id+","+1+","+c.getArg()+")";
		Connexion con = new Connexion();
		con.RequeteDB(sqlQuery);
		}
		
			
		
		
	
	
	public void addListContrainteExam(List<ContrainteExam> E) {
		for (ContrainteExam c : E) {
			this.addContrainteExam(c); 
		}
	}
	
	public void addContrainteSalle(ContrainteSalle c) {
		contraintesSalle.add(c);
		String sqlQuery = "INSERT INTO contraintes(ExamID,ContrainteType,ContrainteArgument) VALUES ("+id+","+0+","+c.getArg()+")";
		Connexion con = new Connexion();
		con.RequeteDB(sqlQuery);
		
	}
	
	public void addListContrainteSalle(List<ContrainteSalle> E) {
		for (ContrainteSalle c : E) {
			this.addContrainteSalle(c); 
		}
	}
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getId() {
		return id;
	}

	public String getMatiere() {
		return matiere;
	}


	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getMateriel() {
		return materiel;
	}


	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}


	public List<Etudiant> getEtudiants() {
		return etudiants;
	}


	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}


	public static int getNum() {
		return num;
	}


	public static void setNum(int num) {
		Examen.num = num;
	}


	


	public List<ContrainteExam> getContraintesExam() {
		return contraintesExam;
	}


	public void setContraintesExam(List<ContrainteExam> contraintesExam) {
		this.contraintesExam = contraintesExam;
	}


	public List<ContrainteSalle> getContraintesSalle() {
		return contraintesSalle;
	}


	public void setContraintesSalle(List<ContrainteSalle> contraintesSalle) {
		this.contraintesSalle = contraintesSalle;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Connection getCn() {
		return cn;
	}


	public void setCn(Connection cn) {
		this.cn = cn;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
