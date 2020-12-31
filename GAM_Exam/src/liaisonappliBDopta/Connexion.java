package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	
	 	String url="jdbc:mysql://localhost/proj_exam";
		String login="root"; 
		String password="";
		Connection cn=null;
		
		Connexion(){
			
		}
		
		public void RequeteDB(String s) {
			
			Statement st = null;
			int rs = 0;
			try {
				cn= DriverManager.getConnection(url, login, password);
				st = cn.createStatement();
				rs = st.executeUpdate(s);
			}
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

}
