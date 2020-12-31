package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login {
	
	private String user;
	private String mdp;
	 String url="jdbc:mysql://localhost/proj_exam";
		String login="root"; 
		String password="";
		Connection cn=null;
	private int statut ;
	
	private Boolean Autorise = false ;

	Login(String u , String m){
		this.user = u;
		this.mdp = m;
		ResultSet resultats = null ;
		
		String requete = "SELECT LoginUser, LoginStatus , LoginPass FROM login WHERE ( LoginUser = '"+user+"' AND LoginPass = '"+mdp+"')";
		System.out.println(requete);
		Statement st = null;
		int rs = 0;
		try {
			cn= DriverManager.getConnection(url, login, password);
			st = cn.createStatement();
			resultats = st.executeQuery(requete);
			
			if (resultats.next()) {
				String a = resultats.getString("LoginUser") +resultats.getString("LoginPass") ;
				String b = user + mdp ;
				b = b.intern();
				a = a.intern();
			if (a == b) {
				this.Autorise = true;
				System.out.println(this.Autorise) ;
			}
		}}
		catch(SQLException e) {
			System.err.println("Erreur requète SQL");
			e.printStackTrace(); 
		}

		
		finally {
			try {
				cn.close();
				st.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public Boolean getAutorise() {
		return Autorise;
	}

	public void setAutorise(Boolean autorise) {
		Autorise = autorise;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}
	
}
